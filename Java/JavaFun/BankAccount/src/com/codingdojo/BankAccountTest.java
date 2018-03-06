package com.codingdojo;

public class BankAccountTest {
    public static void main(String[] args) {
        BankAccount b = new BankAccount();
        b.print();

        b.deposit(10, "checking");
        b.deposit(50, "savings");
        b.deposit(25, "checking");
        b.print();

        System.out.println(BankAccount.getTotalMoney());
        b.withdraw(10, "checking");
        b.print();
        System.out.println(BankAccount.getTotalMoney());
        b.withdraw(35, "checking");
        b.print();
        System.out.println(BankAccount.getTotalMoney());

        BankAccount c = new BankAccount();
        c.deposit(100, "checking");

        BankAccount d = new BankAccount();

        System.out.println(BankAccount.getNumberAccounts());
        System.out.println(BankAccount.getTotalMoney());
    }
}
