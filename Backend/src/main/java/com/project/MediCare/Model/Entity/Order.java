package com.project.MediCare.Model.Entity;

import com.project.MediCare.Model.Enum.OrderStatus;
import com.project.MediCare.Model.Enum.ShipmentType;

import java.util.UUID;


/*
Order Functions:
1. Create new order
2. Create new order based on recipe
3. Get order by account id
4. Update order status
5. Payment (Reduce user's balance)
 */
public class Order {

    public int orderID;
    public int recipeId;
    public String[] medicineName = new String[200];
    public Double[] medicinePrice = new Double[200];
    public Integer[] medicineQuantity = new Integer[200];
    public double medicineTotalPrice;
    public String receiverName, receiverNumber;
    public UUID accountID;
    public String receiverAddress, city, province, postalCode;
    public ShipmentType shipmentType;
    public OrderStatus orderStatus;


    public Order(int orderID, int recipeId, String[] medicineName, Double[] medicinePrice, Integer[] medicineQuantity, double medicineTotalPrice, String receiverName, String receiverNumber, UUID accountID, String receiverAddress, String city, String province, String postalCode, ShipmentType shipmentType, OrderStatus orderStatus){
        this.orderID = orderID;
        this.recipeId = recipeId;
        this.medicineName = medicineName;
        this.medicinePrice = medicinePrice;
        this.medicineQuantity = medicineQuantity;
        this.medicineTotalPrice = medicineTotalPrice;
        this.receiverName = receiverName;
        this.receiverNumber = receiverNumber;
        this.accountID = accountID;
        this.receiverAddress = receiverAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.shipmentType = shipmentType;
        this.orderStatus = orderStatus;
    }
}
