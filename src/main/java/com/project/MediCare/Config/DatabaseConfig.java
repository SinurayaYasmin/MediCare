package com.project.MediCare.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DatabaseConfig {
    private static final Properties properties = new Properties();


    static {
        try (InputStream input = DatabaseConfig.class.getResourceAsStream("/db.properties")) {
            if (input == null) {
                System.out.println("Cannot find db.properties");
                System.exit(1);
            }

            properties.load(input);
        }
        catch (IOException error){
            error.printStackTrace();
        }


    }

    public static String getDbURL(){
        return properties.getProperty("db.url");
    }

    public static String getDbUser(){
        return properties.getProperty("db.username");
    }

    public static String getDbPassword(){
        return properties.getProperty("db.password");
    }
}
