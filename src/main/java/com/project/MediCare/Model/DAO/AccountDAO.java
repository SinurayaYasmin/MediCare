package com.project.MediCare.Model.DAO;

import com.project.MediCare.MainApplication;
import com.project.MediCare.Model.Entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class AccountDAO {
    private final Connection connection;

    public AccountDAO(Connection connection){
        this.connection = connection;
    }

    public Account signUp(Account account){
        var sql = "insert into \"Account\"(email, password, username, \"firstName\", \"lastName\", gender, \"userType\") values (?, ?, ?, ?, ?, ?::\"Gender\", ?::\"UserType\") returning *";
        try (var s = connection.prepareStatement(sql)){
            s.setString(1, account.email);
            s.setString(2, account.password);
            s.setString(3, account.username);
            s.setString(4, account.firstName);
            s.setString(5, account.lastName);
            s.setString(6, account.gender);
            s.setString(7, account.userType);

            var rs = s.executeQuery();

            if (rs.next()) {
                UUID ID = (UUID) rs.getObject("accountID");
                return new Account(ID,
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"),
                        rs.getDate("birthday"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getDouble("balance"),
                        rs.getString("profilePicture"),
                        rs.getString("userType"));


            }
            else{
                return null;
            }
        }

        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Account signIn (Account account){
        if (account.username != null){
            var sql = "Select * from \"Account\" where username = ? and password = ? ";
            try (var s = connection.prepareStatement(sql)){
                s.setString(1, account.username);
                s.setString(2, account.password);

                var rs = s.executeQuery();

                if (rs.next()){
                    UUID ID = (UUID) rs.getObject("accountID");
                    return getAccountByID(ID);
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

        else if (account.email != null){
            var sql = "Select * from \"Account\" where email = ? and password = ?";

            try (var s = connection.prepareStatement(sql)){

                s.setString(1, account.email);
                s.setString(2, account.password);

                var rs = s.executeQuery();
                if (rs.next()){
                    return getAccountByID(account.accountID);
                }
                else{
                    return null;
                }
            }
            catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        else{
            return null;
        }
    }

    public Account forgotPassword(Account account){
        if (account.email != null) {
            var sql = "update \"Account\" set password = ? where email =  ?  returning *";

            try (var s = connection.prepareStatement(sql)){
                s.setString(1, account.password);
                s.setString(2, account.email);

                var rs = s.executeQuery();

                if (rs.next()){
                    UUID ID = (UUID) rs.getObject("accountID");
                    return getAccountByID(ID);
                }
                else{
                    return null;
                }
            }

            catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        else if (account.username != null){
            var sql = "update \"Account\" set password = ? where username =  ?  returning *";

            try (var s = connection.prepareStatement(sql)){
                s.setString(1, account.password);
                s.setString(2, account.username);

                var rs = s.executeQuery();

                if (rs.next()){
                    UUID ID = (UUID) rs.getObject("accountID");
                    return getAccountByID(ID);
                }
                else{
                    return null;
                }
            }

            catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        else {
            return null;
        }
    }

    public Account getAccountByID(UUID ID){
        var sql = "select * from \"Account\" where \"accountID\" = ?";

        try(var s = connection.prepareStatement(sql)){
            s.setObject(1, ID);

            var rs = s.executeQuery();

            if (rs.next()){
                return new Account(ID,
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"),
                        rs.getDate("birthday"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getDouble("balance"),
                        rs.getString("profilePicture"),
                        rs.getString("userType"));
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

    public Account editProfile(UUID accountID, Account account){
        var sql = "update \"Account\" set email = ?, username = ?, \"firstName\" = ?, \"lastName\" = ?, birthday = ?, address = ?, \"phoneNumber\" = ?, \"profilePicture\" = ? where \"accountID\" = ? returning *";
        try (var s = connection.prepareStatement(sql)){
            s.setString(1, account.email);
            s.setString(2, account.username);
            s.setString(3, account.firstName);
            s.setString(4, account.lastName);
            s.setDate(5, account.birthday);
            s.setString(6, account.address);
            s.setString(7, account.phoneNumber);
            s.setString(8, account.profilePicture);
            s.setObject(9, accountID);

            var rs = s.executeQuery();

            if (rs.next()){
                return getAccountByID(accountID);
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

    public Account topUp(UUID accountID,  Account balance){
        var sql = "update \"Account\" set balance = ? where \"accountID\" = ? returning *";

        try (var s = connection.prepareStatement(sql)){
            s.setDouble(1, balance.balance);
            s.setObject(2, accountID);

            var rs = s.executeQuery();
            if (rs.next()){
                return getAccountByID(accountID);
            }
            else{
                return null;
            }
        }

        catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

}
