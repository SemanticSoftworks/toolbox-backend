package com.example.model;

import java.util.Calendar;

/**
 * Created by dani on 2017-02-09.
 */
public class TransactionDetailDTO {

    private Long transactionId;
    private UserDTO user;
    private Calendar date;
    private String description;
    private int sum;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Calendar getDate() { return date; }

    public void setDate(Calendar date) {
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
