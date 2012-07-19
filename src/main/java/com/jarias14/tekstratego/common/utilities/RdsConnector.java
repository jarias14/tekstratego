package com.jarias14.tekstratego.common.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClient;
import com.amazonaws.services.rds.model.DescribeDBInstancesRequest;
import com.amazonaws.services.rds.model.DescribeDBInstancesResult;
import com.jarias14.tekstratego.common.model.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.model.SizeOfBarsEnum;

public class RdsConnector {
    private AmazonRDS rds;
    private Connection con;
    private String secretKey;
    private String accessKey;
    private String username;
    private String password;
    //int dbPriceBars = 3600;

    public RdsConnector() {
        
    }
    
    public void init() {
        rds = new AmazonRDSClient(new BasicAWSCredentials(accessKey, secretKey));
        open();
    }
    
    public void shutdown() {
        this.close();
    }
    
    public void open() {
        DescribeDBInstancesRequest dir = new DescribeDBInstancesRequest();
        dir.setDBInstanceIdentifier("tekstratego");
        DescribeDBInstancesResult inst = rds.describeDBInstances(dir);
        String endpoint = inst.getDBInstances().get(0).getEndpoint()
                .getAddress();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + endpoint + ":3306/tekstratego";
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet select(String query) {
        ResultSet rs = null;
        Statement stmt;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    
    public SortedMap<Calendar, Double> getPrices(String exchange, String symbol, SizeOfBarsEnum sizeOfBar, PriceOfBarsEnum priceOfBar, Calendar startDate, Calendar endDate) {

        SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT);
        
        String queryStartDate = formatter.format(startDate.getTime()).split("T")[0];
        String queryEndDate = formatter.format(endDate.getTime()).split("T")[0];
        
        SortedMap<Calendar, Double> priceBars = new TreeMap<Calendar, Double>();
        
        int sizeOfBarIndex = mapSizeOfBar(sizeOfBar);
        
        String query = "SELECT date, " + priceOfBar.toString().toLowerCase() + " "
                            + "FROM `PriceBars` WHERE "
                                + "size = '"  + sizeOfBarIndex + "' AND "
                                + "symbol = '"  + symbol + "' AND "
                                + "DATE(date) >= '"   + queryStartDate + "' AND "
                                + "DATE(date) <= '"   + queryEndDate + "' "
                            + "ORDER BY date ASC";
        
        ResultSet rs = select(query);
        
        try {
            while (rs.next()) {
                Calendar cal = Calendar.getInstance();
                java.sql.Date date = rs.getDate("date");
                cal.setTimeInMillis(date.getTime());
                priceBars.put(cal, rs.getDouble(priceOfBar.toString().toLowerCase()));
            }
        } catch (Exception e) {
            System.out.println("error with query " + query);
        }
        
        if (priceBars.size()==0) {
            System.out.println(query);
        }
        
        return priceBars;
    }

    
    private int mapSizeOfBar(SizeOfBarsEnum sizeOfBar) {
        return 10;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getMarketDate(SizeOfBarsEnum sizeOfBars, Calendar date, int numberOfBars) {

        SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT);
        
        String queryDate = formatter.format(date.getTime()).split("T")[0];
        
        int sizeOfBarIndex = mapSizeOfBar(sizeOfBars);

        String order, operator;
        
        if (numberOfBars >= 0) {
            operator = ">=";
            order = "ASC";
        } else {
            operator = "<=";
            order = "DESC";
        }
        
        numberOfBars = Math.abs(numberOfBars);
        
        String query = "SELECT date "
                            + "FROM `PriceBars` WHERE "
                                + "size = '"  + sizeOfBarIndex + "' AND "
                                + "symbol = 'ED' AND "
                                + "DATE(date) "+ operator + " '" + queryDate + "' "
                            + "ORDER BY date " + order +" LIMIT "+ (numberOfBars+1);
        
        ResultSet rs = select(query);
        
        
        Calendar resultCal = Calendar.getInstance();
        
        try {
            rs.last();
            if (rs.getRow() != numberOfBars+1) {
                throw new Exception("number of rows and number of bars do not match for numberOfBars:" + numberOfBars + " and request " + query);
            }
            java.sql.Date resultDate = rs.getDate("date");
            resultCal.setTimeInMillis(resultDate.getTime());
            
        } catch (Exception e) {
            System.out.println("error with query " + query);
        } finally {
            
        }
        
        return resultCal;
    }

    public SortedSet<Calendar> getMarketDates(Calendar startDate, Calendar endDate) {
        
        SortedSet<Calendar> dates = new TreeSet<Calendar>();
        
        String queryStartDate = ConverterUtility.toString(startDate).split("T")[0];
        String queryEndDate = ConverterUtility.toString(endDate).split("T")[0];

        int sizeOfBarIndex = mapSizeOfBar(SizeOfBarsEnum.ONE_DAY);

        String query = "SELECT date "
                            + "FROM `PriceBars` WHERE "
                                + "size = '"  + sizeOfBarIndex + "' AND "
                                + "symbol = 'ED' AND "
                                + "DATE(date) >= '" + queryStartDate + "' AND "
                                + "DATE(date) <= '" + queryEndDate + "' "
                            + "ORDER BY date ASC";
        
        ResultSet rs = select(query);
        
        try {
            while (rs.next()) {
                Calendar cal = ConverterUtility.toCalendar(rs.getDate("date"));
                dates.add(cal);
            }
            
        } catch (Exception e) {
            System.out.println("error with query " + query);
        } finally {
            
        }
        
        return dates;
    }

}
