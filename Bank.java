package com.avi.Virtualbank;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {
    private Map<String, Account> accounts;
    private static final String FILE_NAME = "accounts.dat";
    private static final int ACCOUNT_NUMBER_LENGTH = 12;

    public Bank() {
        accounts = new HashMap<>();
        loadAccounts();
    }

    public String createAccount(String accountHolder, String name, String mobileNumber, String ifscCode, String state, String city) {
        String accountNumber;
        do {
            accountNumber = generateRandomAccountNumber();
        } while (accounts.containsKey(accountNumber));
        
        accounts.put(accountNumber, new Account(accountNumber, accountHolder, name, mobileNumber, ifscCode, state, city));
        saveAccounts();
        return accountNumber;
    }


    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void deleteAccount(String accountNumber) {
        accounts.remove(accountNumber);
        saveAccounts();
    }

    public void updateAccountHolder(String accountNumber, String newAccountHolder) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.setAccountHolder(newAccountHolder);
            saveAccounts();
        }
    }

    public void transferFunds(String fromAccount, String toAccount, double amount) throws Exception {
        Account src = accounts.get(fromAccount);
        Account dest = accounts.get(toAccount);
        if (src != null && dest != null) {
            src.withdraw(amount);
            dest.deposit(amount);
            saveAccounts();
        } else {
            throw new Exception("One or both accounts do not exist");
        }
    }

    public void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (HashMap<String, Account>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, starting fresh
            accounts = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateRandomAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        return accountNumber.toString();
    }
}
