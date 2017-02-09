package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dani on 2017-02-09.
 */
public class IncomingTransactionDTO {

    private String date;
    private String description;
    private int sum;

    @JsonCreator
    public IncomingTransactionDTO(@JsonProperty("date") String date,@JsonProperty("description") String description,@JsonProperty("sum") int sum) {
        this.date = date;
        this.description = description;
        this.sum = sum;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getSum() { return sum; }

    public void setSum(int sum) { this.sum = sum; }
}
