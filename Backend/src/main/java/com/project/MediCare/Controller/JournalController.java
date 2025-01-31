package com.project.MediCare.Controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.project.MediCare.BaseResponse;
import com.project.MediCare.Model.DAO.JournalDAO;
import com.project.MediCare.Model.Entity.Journal;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/MediCare")

public class JournalController {

    private final JournalDAO journalDAO;

    public JournalController(JournalDAO j){
        this.journalDAO = j;
    }

    @PostMapping("/writeJournal")
    public BaseResponse<Journal> writeJournal(@RequestBody Journal journal){
    Journal j = journalDAO.writeJournal(journal);

    if (j != null){
        return new BaseResponse<>(true, "Write Journal Success", j);
    }
    else {
        return new BaseResponse<>(false, "Write Journal Failed");
    }

    }

    @GetMapping("/getJournal")
    public BaseResponse<ArrayList<Journal>> getJournal (@RequestParam(name =  "userID") UUID accountID){
        ArrayList<Journal> j = journalDAO.getJournal(accountID);

        if (j != null){
            return new BaseResponse<>(true, "Get Journal Success", j);
        }

        else {
            return new BaseResponse<>(false, "Get Journal Failed");
        }

    }

    @PutMapping("/editJournal")
    public BaseResponse<Journal> editJournal (@RequestParam(name = "userID") int journalID, @RequestBody Journal journal){
        Journal j = journalDAO.editJournal(journalID, journal);

        if (j != null){
            return new BaseResponse<>(true, "Edit Journal Success", j);
        }
        else {
            return new BaseResponse<>(false, "Edit Journal Failed");
        }
    }

    @DeleteMapping("/deleteJournal")
    public BaseResponse<Void> deleteJournal (@RequestParam(name = "journalID") int journalID){
        Boolean b = journalDAO.deleteJournal(journalID);

        if (b){
            return new BaseResponse<>(b, "Delete Journal Success");
        }

        else {
            return new BaseResponse<>(b, "Delete Journal Failed");
        }
    }
}
