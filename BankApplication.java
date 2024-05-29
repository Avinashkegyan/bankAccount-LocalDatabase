package com.avi.Virtualbank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApplication {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
        	System.out.println("============ Bank Menu ============");
            System.out.println("1. Create account");
            System.out.println("2. Deposit funds");
            System.out.println("3. Withdraw funds");
            System.out.println("4. Transfer funds");
            System.out.println("5. Check balance");
            System.out.println("6. View profile");
            System.out.println("7. Update account holder");
            System.out.println("8. Delete account");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.nextLine();  // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositFunds();
                    break;
                case 3:
                    withdrawFunds();
                    break;
                case 4:
                    transferFunds();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    viewProfile();
                    break;
                case 7:
                    updateAccountHolder();
                    break;
                case 8:
                    deleteAccount();
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String accountHolder = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter mobile number: ");
        String mobileNumber = scanner.nextLine();
        System.out.print("Enter IFSC code: ");
        String ifscCode = scanner.nextLine();
        System.out.print("Enter state: ");
        String state = scanner.nextLine();
        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        String accountNumber = bank.createAccount(accountHolder, name, mobileNumber, ifscCode, state, city);
        System.out.println("Account created successfully. \nYour account number is " + accountNumber);
    }

    private static void depositFunds() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = bank.getAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            try {
                double amount = scanner.nextDouble();
                account.deposit(amount);
                bank.saveAccounts();
                System.out.println("Deposit successful.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
                scanner.nextLine();  // Clear the invalid input
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawFunds() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = bank.getAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            try {
                double amount = scanner.nextDouble();
                try {
                    account.withdraw(amount);
                    bank.saveAccounts();
                    System.out.println("Withdrawal successful.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
                scanner.nextLine();  // Clear the invalid input
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transferFunds() {
        System.out.print("Enter source account number: ");
        String fromAccount = scanner.nextLine();
        System.out.print("Enter destination account number: ");
        String toAccount = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        try {
            double amount = scanner.nextDouble();
            try {
                bank.transferFunds(fromAccount, toAccount, amount);
                System.out.println("Transfer successful.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine();  // Clear the invalid input
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = bank.getAccount(accountNumber);
        if (account != null) {
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewProfile() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = bank.getAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolder());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void updateAccountHolder() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter new account holder name: ");
        String newAccountHolder = scanner.nextLine();
        bank.updateAccountHolder(accountNumber, newAccountHolder);
        System.out.println("Account holder updated successfully.");
    }

    private static void deleteAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        bank.deleteAccount(accountNumber);
        System.out.println("Account deleted successfully.");
    }
}
