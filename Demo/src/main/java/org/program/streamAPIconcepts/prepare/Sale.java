package org.program.streamAPIconcepts.prepare;

public class Sale {
    private String category;
    private double amount;

    public Sale(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}

