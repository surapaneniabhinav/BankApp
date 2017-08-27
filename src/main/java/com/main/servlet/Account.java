package com.main.servlet;

public class Account {
    private String firstName;
    private String lastName;
    private Double balance;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public Double getBalance(){
        return balance;
    }
}