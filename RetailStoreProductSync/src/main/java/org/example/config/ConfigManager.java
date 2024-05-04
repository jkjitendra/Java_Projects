package org.example.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (Exception e) {
            System.err.println("Unable to load config properties");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
