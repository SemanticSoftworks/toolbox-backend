package com.example.domain;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by dani on 2017-02-06.
 */
@Entity(name = "Transaction")
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = false)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    private String desciption;
    private int sum;

    public Long  getTransactionId() { return transactionId; }

    public void setTransactionId(Long  transactionId) { this.transactionId = transactionId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Calendar getDate() { return date; }

    public void setDate(Calendar date) { this.date = date; }

    public String getDesciption() { return desciption; }

    public void setDesciption(String desciption) { this.desciption = desciption; }

    public int getSum() { return sum; }

    public void setSum(int sum) { this.sum = sum; }
}
