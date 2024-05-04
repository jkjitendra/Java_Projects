package org.example.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.model.Product;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class HttpClientUtil {
    private final String apiUrl;

    public HttpClientUtil(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public List<Product> fetchProducts() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet getRequest = new HttpGet(apiUrl);
            HttpResponse response = client.execute(getRequest);
            String json = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            Type productListType = new TypeToken<List<Product>>(){}.getType();
            return gson.fromJson(json, productListType);
        } catch (IOException | ParseException e) {
            System.out.println("Error fetching Products: " + e.getMessage());
            return null;
        }
    }
}

