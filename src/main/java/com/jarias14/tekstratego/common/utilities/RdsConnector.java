package com.jarias14.tekstratego.common.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import java.sql.SQLException;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClient;
import com.amazonaws.services.rds.model.DescribeDBInstancesRequest;
import com.amazonaws.services.rds.model.DescribeDBInstancesResult;
import com.jarias14.tekstratego.common.models.PriceOfBarsEnum;
import com.jarias14.tekstratego.common.models.SizeOfBarsEnum;

public class RdsConnector {
    AmazonRDS rds;
    Connection con;
    String secretKey = "F7WppV4mhnzekvQ54Yd2Ftfu6evZ0O/HNxd5UrNJ";
    String accessKey = "AKIAI5T2T7ZRHOCXAZAQ";
    String dbUser = "jarias14";
    String dbPass = "powerDatabas3";
    int dbPriceBars = 3600;

    public RdsConnector() {
        rds = new AmazonRDSClient(new BasicAWSCredentials(accessKey, secretKey));
        open();
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
            con = DriverManager.getConnection(url, dbUser, dbPass);
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
    
    
    public SortedMap<Date, Double> getPrices(String exchange, String symbol, SizeOfBarsEnum sizeOfBar, PriceOfBarsEnum priceOfBar,Date startDate, int numberOfBars) {

        java.sql.Date queryStartDate = new java.sql.Date(startDate.getTime());
        
        SortedMap<Date, Double> priceBars = new TreeMap<Date, Double>();
        
        int sizeOfBarIndex = mapSizeOfBar(sizeOfBar);
        
        String query = "SELECT date, " + priceOfBar.toString().toLowerCase() + " "
                            + "FROM `PriceBars` WHERE "
                                + "size = '"  + sizeOfBarIndex + "' AND "
                                + "symbol = '"  + symbol + "' AND "
                                + "date >= '"   + queryStartDate + "' "
                            + "ORDER BY date ASC LIMIT "+ numberOfBars;
        
        ResultSet rs = select(query);
        
        try {
            while (rs.next()) {
                
                priceBars.put((Date)(rs.getDate("date")), rs.getDouble(priceOfBar.toString().toLowerCase()));
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
    
    
    
    /*
    public void insert(ArrayList<String> queries) {

    }

    
    public void addPrices(ArrayList<Indicator> _priceBars, String _symbol) {
        try {
            PreparedStatement prepStmt = con
                    .prepareStatement("INSERT INTO PriceBars(symbol,date,"
                            + "size,open,low,high,close,adj,volume) VALUES "
                            + "(?,?,?,?,?,?,?,?,?)");
            for (PriceBar pb : _priceBars) {
                prepStmt.setString(1, _symbol);
                prepStmt.setTimestamp(2, new java.sql.Timestamp(pb.getStamp()
                        .getTimestamp().getTime().getTime()));
                prepStmt.setInt(3, pb.getWidth().getID());
                prepStmt.setDouble(4, pb.getOpen());
                prepStmt.setDouble(5, pb.getLow());
                prepStmt.setDouble(6, pb.getHigh());
                prepStmt.setDouble(7, pb.getClose());
                prepStmt.setDouble(8, pb.getAdj());
                prepStmt.setDouble(9, pb.getVolume());
                prepStmt.executeUpdate();
                prepStmt.clearParameters();
            }
            prepStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertPrices(ArrayList<PriceBar> _bars, String _symbol) {

    }*/



    /*
    @Override
    public void addEarnings(ArrayList<Earning> _earnings) {
        // TODO Auto-generated method stub

    }

    @Override
    public ArrayList<Earning> getEarnings(String _symbolID,
            TimeStamp startTimestamp, int numOfEarnings) {
        java.sql.Date startDate = new java.sql.Date(startTimestamp
                .getTimestamp().getTime().getTime());
        return selectEarnings(Integer.valueOf(_symbolID), startDate,
                numOfEarnings);
    }

    private ArrayList<Earning> selectEarnings(int _symbol, Date _start,
            int _limit) {
        ArrayList<Earning> earnings = new ArrayList<Earning>();
        String query = "SELECT * FROM `Earnings_full` WHERE symbol_id = "
                + _symbol + " AND stamp >= " + _start + " LIMIT " + _limit;
        ResultSet rs = select(query);
        try {
            while (rs.next()) {
                Earning earn = new Earning();
                earn.setActual(rs.getDouble("actual"));
                earn.setEstimate(rs.getDouble("estimate"));
                earn.setPeriod(rs.getString("period"));
                earn.setDate(new TimeStamp(rs.getDate("stamp")));
                earnings.add(earn);
            }

        } catch (Exception e) {

        }
        return earnings;
    }

    @Override
    public void addDividends(ArrayList<Dividend> _dividends) {
        // TODO Auto-generated method stub

    }

    @Override
    public ArrayList<Dividend> getDividends(String _symbolID,
            TimeStamp _startDate, int _numOfDividends) {
        java.sql.Date startDate = new java.sql.Date(_startDate.getTimestamp()
                .getTime().getTime());
        return selectDividends(Integer.valueOf(_symbolID), startDate,
                _numOfDividends);
    }

    private ArrayList<Dividend> selectDividends(int _symbol, Date _start,
            int _limit) {
        ArrayList<Dividend> dividends = new ArrayList<Dividend>();
        String query = "SELECT * FROM `Dividends_full` WHERE symbol_id = "
                + _symbol + " AND stamp >= " + _start + " LIMIT " + _limit;
        ResultSet rs = select(query);
        try {
            while (rs.next()) {
                Dividend div = new Dividend();
                div.setAmount(rs.getDouble("amount"));
                div.setDeclaration(new TimeStamp(rs.getDate("declaration")));
                div.setExdate(new TimeStamp(rs.getDate("exdate")));
                div.setPayable(new TimeStamp(rs.getDate("payable")));
                dividends.add(div);
            }

        } catch (Exception e) {

        }
        return dividends;
    }*/


}
