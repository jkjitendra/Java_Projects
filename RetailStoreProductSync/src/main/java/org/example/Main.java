package org.example;

import org.example.config.ConfigManager;
import org.example.dao.ProductDAO;
import org.example.service.ProductService;
import org.example.util.HttpClientUtil;


public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = ConfigManager.getProperty("db.url");
            String dbUser = ConfigManager.getProperty("db.user");
            String dbPassword = ConfigManager.getProperty("db.password");

            ProductDAO productDAO = new ProductDAO(dbUrl, dbUser, dbPassword);
            HttpClientUtil httpClientUtil = new HttpClientUtil(ConfigManager.getProperty("api.url"));
            ProductService productService = new ProductService(productDAO, httpClientUtil);

            productService.fetchAndStoreProducts();

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

}