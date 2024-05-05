package org.program.streamAPIconcepts.prepare;

public class Product {
    private String productId;
    private double price;

    public Product(String productId, double price) {
        this.productId = productId;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }
}