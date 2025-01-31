package com.project.MediCare.Model.DAO;

import com.project.MediCare.Model.Entity.Journal;
import com.project.MediCare.Model.Enum.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class JournalDAO {
    private final Connection connection;

    public JournalDAO(Connection connection){
        this.connection = connection;
    }

    public Journal writeJournal(Journal journal){
        var sql = "insert into \"Journal\" (\"accountID\", title, content, mood, date) values (?, ?, ?, ?::\"Mood\", ?) returning *";
        try (var s = connection.prepareStatement(sql)){
            s.setObject(1, journal.accountID);
            s.setString(2, journal.title);
            s.setString(3, journal.content);
            s.setString(4, journal.mood.toString());
            s.setDate(5, journal.date);

            var rs = s.executeQuery();
            if (rs.next()){
                return new Journal(rs.getInt("journalID"),
                        (UUID) rs.getObject("accountID"),
                        rs.getString("title"),
                        rs.getString("content"),
                        Mood.valueOf((String) rs.getObject("mood")),
                        rs.getDate("date")
                        );
            }

            else {
                return null;
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Journal> getJournal (UUID accountID){
        var sql = " select * from \"Journal\" where \"accountID\" = ? ";

        try (var s = connection.prepareStatement(sql)){
            s.setObject(1, accountID);

            var rs = s.executeQuery();
            ArrayList<Journal> allJournal = new ArrayList<>();

            while (rs.next()){
                allJournal.add(new Journal(rs.getInt("journalID"),
                        (UUID) rs.getObject("accountID"),
                        rs.getString("title"),
                        rs.getString("content"),
                        Mood.valueOf((String) rs.getObject("mood")),
                        rs.getDate("date")
                        ));
            }
            return allJournal;

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Journal editJournal (int journalID, Journal journal){
        var sql = "update \"Journal\" set title = ?, content = ?, mood = ?::\"Mood\", date = ? where \"journalID\" = ? returning * ";

        try (var s = connection.prepareStatement(sql)){

            s.setString(1, journal.title);
            s.setString(2, journal.content);
            s.setString(3, journal.mood.toString());
            s.setDate(4, journal.date);
            s.setInt(5, journalID);

            var rs = s.executeQuery();

            if (rs.next()){
                return new Journal(rs.getInt("journalID"),
                        (UUID) rs.getObject("accountID"),
                        rs.getString("title"),
                        rs.getString("content"),
                        Mood.valueOf((String) rs.getObject("mood")),
                        rs.getDate("date")
                );
            }
            else {
                return null;
            }


        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean deleteJournal (int journalID){
        var sql = "delete from \"Journal\" where \"journalID\" = ? returning *";

        try (var s = connection.prepareStatement(sql)){
            s.setInt(1, journalID);

            var rs = s.executeQuery();

            if (rs.next()){
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
