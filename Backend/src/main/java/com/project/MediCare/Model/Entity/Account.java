package com.project.MediCare.Model.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.project.MediCare.MainApplication;

import java.sql.SQLException;
import java.sql.Date;
import java.util.UUID;

/*
Account Functions:
1. Sign Up (DONE)
2. Sign In (DONE)
3. Forgot Password (DONE)
4. Edit account (update birthday, address, phonenumber, profilepicture) (DONE)
5. top up (DONE)
6. Get account by id (DONE)
7. Notifications
8. Show Cart
9. Order History
10. Chat History
11. Change Password -- Same as Forgot Password (DONE)
12. Log out
 */
public class Account {

    public UUID accountID;
    public String email;
    public String password;
    public String username;
    public String firstName;
    public String lastName;
    public String gender;
    public Date birthday;
    public int age;
    public String address, phoneNumber;
    public double balance;
    public String profilePicture, userType;

    public Account(){
    }

    public Account(UUID accountID, String email, String password, String username, String firstName, String lastName, String gender, Date birthday, int age, String address, String phoneNumber, double balance, String profilePicture, String userType){
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.profilePicture = profilePicture;
        this.userType = userType;
    }


    //For Sign Up
    public Account(UUID accountID, String email, String password, String username, String firstName, String lastName, String gender, String userType){
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this. gender = gender;
        this.userType = userType;
    }

    //For Sign In
    public Account(String email, String username, String password){
    this.email = email;
    this.username = username;
    this.password = password;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }



}
