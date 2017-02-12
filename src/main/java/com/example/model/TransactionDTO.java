package com.example.model;

/**
 * Created by alica on 2017-02-08.
 * Good luck, Commander!
 */
public class TransactionDTO {
    private Long transactionId;
    private String date;
    private String description;
    private int sum;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
