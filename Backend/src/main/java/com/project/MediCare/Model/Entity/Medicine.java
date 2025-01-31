package com.project.MediCare.Model.Entity;


import com.project.MediCare.Model.Enum.MedicineType;
import com.project.MediCare.Model.Enum.MedicineUse;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

/*
Medicine Functions:
1. Get all Medicine (DONE)
2. Get medicine by code (DONE)
3. Decrement quantity of each medicine when there is order (DONE)
 */

public class Medicine {
    public String code, name, description;
    public MedicineType medicineType;
    public MedicineUse medicineUse;
    public String quantity;
    public String frequency;
    public Date expiredDate;
    public Double price;
    public String medicineImage;
    public int availableQuantity;


    public Medicine(){

    }

    public Medicine(String code, String name, String description, MedicineType medicineType, MedicineUse medicineUse, String quantity, String frequency,
                    Date expiredDate, Double price, String medicineImage){
        this.code = code;
        this.name = name;
        this.description = description;
        this.medicineType = medicineType;
        this.medicineUse = medicineUse;
        this.quantity = quantity;
        this.frequency = frequency;
        this.expiredDate = expiredDate;
        this.price = price;
        this.medicineImage = medicineImage;
    }


    public Medicine(String code, String name, String description, MedicineType medicineType, MedicineUse medicineUse, String quantity, String frequency,
                    Date expiredDate, Double price, String medicineImage, int availableQuantity){
        this.code = code;
        this.name = name;
        this.description = description;
        this.medicineType = medicineType;
        this.medicineUse = medicineUse;
        this.quantity = quantity;
        this.frequency = frequency;
        this.expiredDate = expiredDate;
        this.price = price;
        this.medicineImage = medicineImage;
        this.availableQuantity = availableQuantity;
    }

}
