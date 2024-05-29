package com.avi.Virtualbank;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L; // Ensure this remains the same unless there are incompatible changes

    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String name;
    private String mobileNumber;
    private String ifscCode;
    private String state;
    private String city;
    
    public Account(String accountNumber, String accountHolder, String name, String mobileNumber, String ifscCode, String state, String city) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.ifscCode = ifscCode;
        this.state = state;
        this.city = city;
        this.balance = 0.0;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid withdraw amount.");
        }
    }
   


   

}
