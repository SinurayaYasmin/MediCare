package com.project.MediCare.Controller;

import com.project.MediCare.BaseResponse;
import com.project.MediCare.Model.DAO.AccountDAO;
import com.project.MediCare.Model.Entity.Account;
import com.project.MediCare.Model.Entity.Cart;
import com.project.MediCare.Model.Entity.Order;
import com.project.MediCare.Model.Entity.Recipe;
import com.project.MediCare.Model.Enum.OrderStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/MediCare")
public class AccountController {

    private final AccountDAO accountDao;

    public AccountController(AccountDAO a){
        this.accountDao = a;
    }

    @PostMapping("/signUp")
    public BaseResponse<Account> signUp(@RequestBody Account newAccount){
        Account x = accountDao.signUp(newAccount);

        if (x != null){
            return new BaseResponse<>(true, "Sign Up Success", x);
        }
        else{
            return new BaseResponse<>(false, "Sign Up Failed'");
        }
    }


    @PostMapping("/signIn")
    public BaseResponse<Account> signIn(@RequestBody Account account){
        Account x = accountDao.signIn(account);
        if (x != null){
            return new BaseResponse<>(true, "Sign In Success", x);
        }
        else {
            return new BaseResponse<>(false, "Sign In Failed", "Email or Username not exist");

        }
    }

    @PutMapping("/forgotPassword")
    public BaseResponse<Account> forgotPassword(@RequestBody Account account){
        Account x = accountDao.forgotPassword(account);
        if (x != null){
            return new BaseResponse<>(true, "Change Password Success", x);
        }
        else{
            return new BaseResponse<>(false, "Change Password Failed");
        }
    }

    @GetMapping("/getAccountByID")
    public BaseResponse<Account> getAccountByID(@RequestParam(name = "userID") UUID accountID){
        Account x = accountDao.getAccountByID(accountID);

        if (x != null){
            return new BaseResponse<>(true, "Get Account Success", x);
        }
        else{
            return new BaseResponse<>(false, "Get Account Failed");
        }
    }

    @PutMapping("/editProfile")
    public BaseResponse<Account> editProfile(@RequestParam(name = "userID") UUID accountID, @RequestBody Account account){
        Account x = accountDao.editProfile(accountID, account);

        if (x != null){
            return new BaseResponse<>(true, "Edit Profile Success", x);
        }
        else{
            return new BaseResponse<>(false, "Edit Profile Failed");
        }
    }

    @PutMapping("/topUp")
    public BaseResponse<Account> topUp(@RequestParam(name = "userID") UUID accountID, @RequestBody Account account){
        Account x = accountDao.topUp(accountID, account);

        if (x != null){
            return new BaseResponse<>(true, "Top Up Success", x);
        }
        else {
            return new BaseResponse<>(false, "Top Up Failed");
        }
    }



    @GetMapping("/orderHistory")
    public BaseResponse<ArrayList<Order>> orderHistory(@RequestParam(name = "userID") UUID accountID){
        ArrayList<Order> o = accountDao.orderHistory(accountID);

        if (o != null){
            return new BaseResponse<>(true, "Get Order History Success", o);
        }
        else {
            return new BaseResponse<>(false, "Get Order History Failed");
        }
    }

    @GetMapping("/recipeHistory")
    public BaseResponse<ArrayList<Recipe>> recipeHistory(@RequestParam(name = "userID") UUID accountID){
        ArrayList<Recipe> r = accountDao.recipeHistory(accountID);

        if (r != null){
            return new BaseResponse<>(true, "Get Recipe History Success", r);
        }
        else {
            return new BaseResponse<>(false, "Get Recipe History Failed");
        }
    }



}
