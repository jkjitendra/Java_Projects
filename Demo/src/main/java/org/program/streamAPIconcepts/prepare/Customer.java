package org.program.streamAPIconcepts.prepare;

import java.util.List;

public class Customer {
    private String name;
    private int age;
    private List<Account> accounts;

    public Customer(String name, int age, List<Account> accounts) {
        this.name = name;
        this.age = age;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public double getTotalBalance() {
        return accounts.stream().mapToDouble(Account::getBalance).sum();
    }
}


