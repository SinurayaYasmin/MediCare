package com.project.MediCare.Controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.project.MediCare.BaseResponse;
import com.project.MediCare.MainApplication;
import com.project.MediCare.Model.DAO.AccountDAO;
import com.project.MediCare.Model.Entity.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

}
