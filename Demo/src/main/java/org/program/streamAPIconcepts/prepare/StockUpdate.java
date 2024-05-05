package org.program.streamAPIconcepts.prepare;

public class StockUpdate {
    private String symbol;
    private double price;

    public StockUpdate(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

