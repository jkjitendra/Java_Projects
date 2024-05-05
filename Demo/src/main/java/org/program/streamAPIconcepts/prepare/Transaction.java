package org.program.streamAPIconcepts.prepare;

public class Transaction {
    private int year;
    private double value;

    public Transaction(int year, double value) {
        this.year = year;
        this.value = value;
    }

    public int getYear() {
        return year;
    }

    public double getValue() {
        return value;
    }
}

