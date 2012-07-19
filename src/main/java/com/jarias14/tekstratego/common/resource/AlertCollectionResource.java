package com.jarias14.tekstratego.common.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jarias14.tekstratego.common.model.AbstractBase;
import com.jarias14.tekstratego.common.utilities.ConstantsUtility;
import com.jarias14.tekstratego.common.utilities.ConverterUtility;
import com.jarias14.tekstratego.service.manager.model.Alert;

public class AlertCollectionResource extends BaseResource {
    
    private static final long serialVersionUID = 1L;
    
    private SortedMap<String, List<AlertResource>> alerts;
    
    public AlertCollectionResource() {
        alerts = new TreeMap<String, List<AlertResource>>();
    }
    
    public <T> AlertCollectionResource(SortedMap<Calendar,List<T>> model) {
        
        this.alerts = new TreeMap<String, List<AlertResource>>();
        
        SimpleDateFormat fmtr = new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT);
        
        for (Calendar cal : model.keySet()) {
            String newKey = fmtr.format(cal.getTime());
            
            this.alerts.put(newKey, new ArrayList<AlertResource>());
            
            for (Object alert : model.get(cal)) {
                
                AlertResource newValue = (AlertResource)((AbstractBase)alert).toResource(); 
                
                if (!this.getAlerts().containsKey(newKey)) {
                    this.getAlerts().put(newKey, new ArrayList<AlertResource>());
                }
                
                this.getAlerts().get(newKey).add(newValue);
            }
        }
    }
    
    public SortedMap<Calendar,List<Alert>> toModel() {

        SimpleDateFormat fmtr = new SimpleDateFormat(ConstantsUtility.DATE_TIME_FORMAT);
        SortedMap<Calendar,List<Alert>> model = new TreeMap<Calendar,List<Alert>>();
        
        for (String key : this.getAlerts().keySet()) {
            
            Calendar keyCalendar = ConverterUtility.toCalendar(key); 
            
            model.put(keyCalendar, new ArrayList<Alert>());
            
            for (AlertResource alertResource : this.getAlerts().get(key)) {
                
                Alert alert = new Alert(alertResource);
                                
                model.get(keyCalendar).add(alert);
            }
        }
        
        return model;
    }

    public SortedMap<String, List<AlertResource>> getAlerts() {
        return alerts;
    }

    public void setAlerts(SortedMap<String, List<AlertResource>> alerts) {
        this.alerts = alerts;
    }

}
