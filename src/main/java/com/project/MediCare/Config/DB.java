package com.project.MediCare.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DB {
    @Bean
    public static Connection connection() throws SQLException{
        try {


            var url = DatabaseConfig.getDbURL();
            var user = DatabaseConfig.getDbUser();
            var password = DatabaseConfig.getDbPassword();

            System.out.println("Connecting to database...");
            return DriverManager.getConnection(url, user, password);
        }
        catch (SQLException error){
            System.err.println(error.getErrorCode());
            System.err.println(error.getMessage());
            return null;
        }
    }
}
