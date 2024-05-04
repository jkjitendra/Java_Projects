package org.example.dao;

import org.example.model.Product;

import java.sql.*;
import java.util.List;

public class ProductDAO {
    private static final String INSERT_SQL = "INSERT INTO products (title, price, description, category, image, rate, count) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String dbUrl;
    private final String user;
    private final String pwd;

    public ProductDAO(String dbUrl, String user, String pwd) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.pwd = pwd;
    }

    public void insertProducts(List<Product> products) {
        if (products == null || products.isEmpty()) return;
        try (Connection connection = DriverManager.getConnection(dbUrl, user, pwd);
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            for (Product product : products) {
                statement.setString(1, product.getTitle());
                statement.setDouble(2, product.getPrice());
                statement.setString(3, product.getDescription());
                statement.setString(4, product.getCategory());
                statement.setString(5, product.getImage());
                statement.setDouble(6, product.getRating().getRate());
                statement.setInt(7, product.getRating().getCount());

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating product failed, no rows affected.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error inserting products into DB: " + e.getMessage());
        }
    }
}
