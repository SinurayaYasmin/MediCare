package com.project.MediCare.Controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.project.MediCare.BaseResponse;
import com.project.MediCare.Model.DAO.AccountDAO;
import com.project.MediCare.Model.DAO.CartDAO;
import com.project.MediCare.Model.Entity.Cart;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/MediCare")
public class CartController {

    private final CartDAO cartDAO;

    public CartController(CartDAO c){
        this.cartDAO = c;
    }

    @GetMapping("/getCart")
    public BaseResponse<Cart> cartHistory(@RequestParam(name = "userID") UUID accountID){
        Cart c = cartDAO.getCart(accountID);

        if (c != null){
            return new BaseResponse<>(true, "Get Cart History Success", c);
        }
        else {
            return new BaseResponse<>(false, "Get Cart History Failed");
        }
    }

    @PutMapping("/removeCartItem")
    public BaseResponse<Cart> removeCartItem(@RequestParam(name = "userID") UUID accountID, @RequestBody Cart removedItem){
        Cart c = cartDAO.removeItemFromCart(accountID, removedItem.removedItem);
        if (c != null){
            return new BaseResponse<>(true, "Edit Cart Success", c);
        }
        else {
            return new BaseResponse<>(false, "Edit Cart Failed");
        }
    }

    @PostMapping("/createCart")
    public BaseResponse<Cart> createCart(@RequestParam(name = "userID") UUID accountID){
        Cart c = cartDAO.createCart(accountID);

        if (c != null){
            return new BaseResponse<>(true, "Create Cart Success", c);
        }
        else{
            return new BaseResponse<>(false, "Create Cart Failed");
        }
    }

    @PutMapping("/addItemCart")
    public BaseResponse<Cart> addItemCart(@RequestParam(name = "userID") UUID accountID, @RequestBody Cart cart){
        Cart c = cartDAO.addItemToCart(accountID, cart.addedItem, cart.quantityAddItem);

        if (c!= null){
            return new BaseResponse<>(true, "Add Item To Cart Success", c);
        }
        else {
            return new BaseResponse<>(false, "Add Item To Cart Failed");
        }
    }

    @PutMapping("/incrementMed")
    public BaseResponse<Cart> incrementMed(@RequestParam(name = "userID") UUID accountID, @RequestBody Cart cart){
        Cart c = cartDAO.incrementMed(accountID, cart.addedItem);

        if (c!= null){
            return new BaseResponse<>(true, "Increment Med Success", c);
        }
        else {
            return new BaseResponse<>(false, "Increment Med Failed");
        }
    }

    @PutMapping("/decrementMed")
    public BaseResponse<Cart> decrementMed(@RequestParam(name = "userID") UUID accountID, @RequestBody Cart cart){
        Cart c = cartDAO.decrementMed(accountID, cart.addedItem);

        if (c!= null){
            return new BaseResponse<>(true, "Decrement Med Success", c);
        }
        else {
            return new BaseResponse<>(false, "Decrement Med Failed");
        }
    }
}
