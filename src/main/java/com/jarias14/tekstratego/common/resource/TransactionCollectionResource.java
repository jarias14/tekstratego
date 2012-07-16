package com.jarias14.tekstratego.common.resource;

import java.util.List;

public class TransactionCollectionResource extends BaseResource {

    private static final long serialVersionUID = -3121285150955585016L;
    
    private List<TransactionResource> transactions;

    public List<TransactionResource> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResource> transactions) {
        this.transactions = transactions;
    }
}
