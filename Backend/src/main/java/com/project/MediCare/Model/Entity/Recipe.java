package com.project.MediCare.Model.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/*
Recipe Functions:
1. Get Recipe
2. Create Recipe -- For Doctor Only

 */
public class Recipe {
    public int recipeID;
    public UUID patientID, doctorID;
    public Date date;
    public String diagnose;
    public ArrayList<String> medicationName = new ArrayList<>();
    public ArrayList<String> dosage = new ArrayList<>();
    public ArrayList<String> frequency = new ArrayList<>();
    public ArrayList<Integer> quantity = new ArrayList<>();
    public ArrayList<Double> price = new ArrayList<>();
    public double totalPrice;


    public Recipe(int recipeID, UUID patientID, UUID doctorID, Date date, String diagnose, ArrayList<String> medicationName, ArrayList<String> dosage,
    ArrayList<String> frequency, ArrayList<Integer> quantity, ArrayList<Double> price, double totalPrice) {
        this.recipeID = recipeID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.diagnose = diagnose;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

}
