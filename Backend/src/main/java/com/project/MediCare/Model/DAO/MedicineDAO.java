package com.project.MediCare.Model.DAO;

import com.project.MediCare.Model.Entity.Medicine;
import com.project.MediCare.Model.Enum.MedicineType;
import com.project.MediCare.Model.Enum.MedicineUse;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class MedicineDAO {
    private final Connection connection;

    public MedicineDAO(Connection connection){
        this.connection = connection;
    }

    public ArrayList<Medicine> getAllMedicine(){
        var sql = "select * from \"Medicine\"";

        try (var s = connection.createStatement()){
            var rs = s.executeQuery(sql);

            ArrayList<Medicine> allMedicine = new ArrayList<>();
            while (rs.next()){
                allMedicine.add(new Medicine(rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        MedicineType.valueOf((String) rs.getObject("medicineType")),
                        MedicineUse.valueOf((String) rs.getObject("medicineUse")),
                        rs.getString("quantity"),
                        rs.getString("frequency"),
                        rs.getDate("expiredDate"),
                        rs.getDouble("price"),
                        rs.getString("medicineImage")
                        ));
            }
            return allMedicine;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Medicine getMedicineByCode(String code){
        var sql = "select * from \"Medicine\" where code = ? ";
        try(var s = connection.prepareStatement(sql)){
            s.setString(1, code);

            var rs = s.executeQuery();

            if (rs.next()){
                return new Medicine(rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        MedicineType.valueOf((String) rs.getObject("medicineType")),
                        MedicineUse.valueOf((String) rs.getObject("medicineUse")),
                        rs.getString("quantity"),
                        rs.getString("frequency"),
                        rs.getDate("expiredDate"),
                        rs.getDouble("price"),
                        rs.getString("medicineImage"),
                        rs.getInt("availableQuantity"));
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

    public Medicine decrementMedicine(String code, Medicine medQuantity){
        var sql = "update \"Medicine\" set \"availableQuantity\" = \"availableQuantity\" - ? where code = ? returning *";

        try (var s = connection.prepareStatement(sql)){
            s.setInt(1, medQuantity.availableQuantity);
            s.setString(2, code);

            var rs = s.executeQuery();

            if (rs.next()){
                return getMedicineByCode(code);
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
}
