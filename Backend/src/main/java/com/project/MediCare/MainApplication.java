package com.project.MediCare;

import com.project.MediCare.Config.DB;
import com.project.MediCare.Model.DAO.AccountDAO;
import com.project.MediCare.Model.Entity.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	public static Connection connection;
	public static void main(String[] args) {

		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String args[]) throws Exception
	{
		System.out.println("Connected to database" );
	}

}
