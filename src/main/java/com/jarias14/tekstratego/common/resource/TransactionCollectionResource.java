package com.jarias14.tekstratego.common.resource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jarias14.tekstratego.common.utilities.ConverterUtility;
import com.jarias14.tekstratego.service.thinker.model.Position;

public class TransactionCollectionResource extends BaseResource {

    private static final long serialVersionUID = -3121285150955585016L;
    
    private SortedMap<String, List<TransactionResource>> transactions;

    public TransactionCollectionResource(Map<Calendar, List<Position>> model) {
        this.transactions = new TreeMap<String, List<TransactionResource>>();
        
        for (Entry<Calendar, List<Position>> entry : model.entrySet()){
            List<TransactionResource> entryTransactions = new ArrayList<TransactionResource>();
            
            for (Position position : entry.getValue()) {
                TransactionResource trans = new TransactionResource();
                trans.setSharesNumber(String.valueOf(position.getNumberOfShares()));
                trans.setSharesPrice(position.getPurchaseValue().toString());
                entryTransactions.add(trans);
            }
            this.transactions.put(ConverterUtility.toString(entry.getKey()), entryTransactions);
        }
    }

    public SortedMap<String, List<TransactionResource>> getTransactions() {
        return transactions;
    }

    public void setTransactions(SortedMap<String, List<TransactionResource>> transactions) {
        this.transactions = transactions;
    }

}
