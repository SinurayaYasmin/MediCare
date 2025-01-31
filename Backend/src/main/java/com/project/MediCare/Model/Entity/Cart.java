package com.project.MediCare.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.UUID;

/*
Car Functions:
1. Add item to cart (DONE)
2. Get all cart (DONE)
3. Update cart -- Like inc or dec item quantity (DONE)
4. Delete cart (DONE)
 */


public class Cart {

    public int cartID;
    public UUID accountID;
    public ArrayList<String> medicineCode = new ArrayList<>();
    public ArrayList<Integer> medQuantity = new ArrayList<>();
    public ArrayList<Double> pricePerMed = new ArrayList<>();
    public ArrayList<Double> totalPricePerMed = new ArrayList<>();
    public double totalPrice;

    public ArrayList<String> removedItem = new ArrayList<>();
    public String addedItem;
    public int quantityAddItem;


    public Cart(){

    }
    public Cart(int cartID, UUID accountID, ArrayList<String> medicineCode){
        this.cartID = cartID;
        this.accountID = accountID;
        this.medicineCode = medicineCode;
    }

    public Cart(int cartID, UUID accountID){
        this.cartID = cartID;
        this.accountID = accountID;
    }

    public Cart(UUID accountID, ArrayList<String> medicineCode, ArrayList<Integer> medQuantity, ArrayList<Double> pricePerMed, ArrayList<Double> totalPricePerMed, double totalPrice){
        this.accountID = accountID;
        this.medicineCode = medicineCode;
        this.medQuantity = medQuantity;
        this.pricePerMed = pricePerMed;
        this.totalPricePerMed = totalPricePerMed;
        this.totalPrice = totalPrice;
    }
    public Cart(int cartID, UUID accountID, ArrayList<String> medicineCode, ArrayList<Integer> medQuantity, ArrayList<Double> pricePerMed, ArrayList<Double> totalPricePerMed, double totalPrice){
        this.cartID = cartID;
        this.accountID = accountID;
        this.medicineCode = medicineCode;
        this.medQuantity = medQuantity;
        this.pricePerMed = pricePerMed;
        this.totalPricePerMed = totalPricePerMed;
        this.totalPrice = totalPrice;
    }
}
