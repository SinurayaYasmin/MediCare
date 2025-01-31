package com.project.MediCare.Controller;


import com.project.MediCare.BaseResponse;
import com.project.MediCare.Model.DAO.MedicineDAO;
import com.project.MediCare.Model.Entity.Medicine;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.ArrayList;

@RestController
@RequestMapping("/MediCare")
public class MedicineController {
    MedicineDAO medicineDAO;

    public MedicineController(MedicineDAO m){
        this.medicineDAO = m;
    }

    @GetMapping("/getAllMedicine")
    public BaseResponse<ArrayList<Medicine>> getAllMedicine(){
        ArrayList<Medicine> m = medicineDAO.getAllMedicine();

        if (m != null){
            return new BaseResponse<>(true, "Get All Medicine Success", m);
        }
        else {
            return new BaseResponse<>(false, "Get All Medicine Failed");
        }

    }

    @GetMapping("/getMedicineByCode")
    public BaseResponse<Medicine> getMedicineByCode(@RequestParam(name = "medCode") String code){
        Medicine m = medicineDAO.getMedicineByCode(code);

        if (m != null){
            return new BaseResponse<>(true, "Get Medicine By Code Success", m);
        }
        else {
            return new BaseResponse<>(false, "Get Medicine By Code Failed");
        }
    }

    @PutMapping("/decrementMedicine")
    public BaseResponse<Medicine> decrementMedicine(@RequestParam(name = "medCode") String code, @RequestBody Medicine medQuantity){
        Medicine m = medicineDAO.decrementMedicine(code, medQuantity);

        if (m != null){
            return new BaseResponse<>(true, "Decrement Medicine Success", m);
        }
        else {
            return new BaseResponse<>(false, "Decrement Medicine False");
        }
    }
}
