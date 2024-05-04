package org.example.service;

import org.example.dao.ProductDAO;
import org.example.model.Product;
import org.example.util.HttpClientUtil;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;
    private final HttpClientUtil httpClientUtil;

    public ProductService(ProductDAO productDAO, HttpClientUtil httpClientUtil) {
        this.productDAO = productDAO;
        this.httpClientUtil = httpClientUtil;
    }

    public void fetchAndStoreProducts() {
        List<Product> products = httpClientUtil.fetchProducts();
        if (products != null) {
            System.out.println("Records Fetched Successfully");
            productDAO.insertProducts(products);
            System.out.println("Records Inserted Successfully");
        }
    }
}
