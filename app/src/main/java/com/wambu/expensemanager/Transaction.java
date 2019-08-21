package com.wambu.expensemanager;

import java.util.Date;

public class Transaction {

    private  String incomeType;
    private String description;
    private String date;
    private String amount;
    //private int amount;


    public Transaction(String incomeType, String description, String date, String amount) {
        this.incomeType = incomeType;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
