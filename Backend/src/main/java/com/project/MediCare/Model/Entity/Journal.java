package com.project.MediCare.Model.Entity;

import com.project.MediCare.Model.Enum.Mood;

import java.sql.Date;
import java.util.UUID;

/*
Journal Functions:
1. Write Journal (DONE)
2. Get Journal (DONE)
3. Edit Journal (DONE)
4. Delete Journal (DONE)
 */

public class Journal {
    public int journalID;
    public UUID accountID;
    public String title;
    public String content;
    public Mood mood;
    public Date date;

    public Journal(){

    }
    public Journal (int journalID, UUID accountID, String title, String content, Mood mood, Date date){
        this.journalID = journalID;
        this.accountID = accountID;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.date = date;
    }


    //For Write Journal
    public Journal (UUID accountID, String title, String content, Mood mood, Date date){
        this.accountID = accountID;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.date = date;
    }

}
