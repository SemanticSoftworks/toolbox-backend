package com.example.model;

import java.util.List;

/**
 * Created by dani on 2017-02-09.
 */
public class TransactionListingDTO {

    private List<TransactionDTO> transactionDTOList;

    public List<TransactionDTO> getTransactionDTOList() { return transactionDTOList; }

    public void setTransactionDTOList(List<TransactionDTO> transactionDTOList) { this.transactionDTOList = transactionDTOList; }
}
