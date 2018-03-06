package com.codingdojo;

import java.util.Random;
import java.util.Arrays;

public class BankAccount {
    String accountNumber;
    private double checkingBalance = 0;
    private double savingsBalance = 0;

    private static int numberAccounts = 0;
    private static double totalMoney = 0;

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public double getCheckingBalance() {

        return checkingBalance;
    }

    public BankAccount() {
        this.accountNumber = randomNumber();
        numberAccounts++;
    }

    private String randomNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for(int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public void deposit(double amount, String account) {
        if (account == "checking") {
            checkingBalance += amount;
        } else {
            savingsBalance += amount;
        }
        totalMoney += amount;
    }

    public void withdraw(double amount, String account) {
        if (account == "checking") {
            if (amount <= checkingBalance) {
                checkingBalance -= amount;
                totalMoney -= amount;
            }
        } else {
            if (amount <= savingsBalance) {
                savingsBalance -= amount;
                totalMoney -= amount;
            }
        }
    }

    public static double getTotalMoney() {
        return totalMoney;
    }

    public static int getNumberAccounts() {
        return numberAccounts;
    }

    public void print() {
        System.out.println(accountNumber + ":\n   Checking: " + checkingBalance + "\n   Savings: " + savingsBalance);
    }

}
