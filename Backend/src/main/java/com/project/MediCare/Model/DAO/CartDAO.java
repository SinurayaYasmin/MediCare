package com.project.MediCare.Model.DAO;

import com.project.MediCare.Model.Entity.Cart;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Repository
public class CartDAO {

    private final Connection connection;

    public CartDAO (Connection connection){
        this.connection = connection;
    }

    public Cart getCart(UUID accountID){
        var sql = "select * from \"Cart\" where \"accountID\" = ? ";

        try (var s = connection.prepareStatement(sql)){
            s.setObject(1, accountID);
            var rs = s.executeQuery();



            if (rs.next()){
                ArrayList<String> code = new ArrayList<>(Arrays.asList((String[]) rs.getArray("medicineCode").getArray()));
                ArrayList<Integer> quantity = new ArrayList<>(Arrays.asList((Integer[]) rs.getArray("medQuantity").getArray()));
                ArrayList<Double> price = new ArrayList<>(Arrays.asList((Double[]) rs.getArray("medPrice").getArray()));
                ArrayList<Double> pricePerMed = new ArrayList<>(Arrays.asList((Double[]) rs.getArray("pricePerMed").getArray()));
                return new Cart(rs.getInt("cartID"),
                        (UUID) rs.getObject("accountID"),
                        code,
                        quantity,
                        price,
                        pricePerMed,
                        rs.getDouble("totalPrice")
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


    public Cart removeItemFromCart(UUID accountID, ArrayList<String> removedItem){
        var sql = "UPDATE \"Cart\"SET \"medicineCode\" = ?, \"medQuantity\" = ? WHERE \"accountID\" = ? returning *";
        Cart c = getCart(accountID);

        int index = removedItem.size();

        System.out.println("Index: " + index);
            for (int j = 0; j < index; j++) {
                if (c.medicineCode.contains(removedItem.get(j))){
                    //System.out.println("Find: " + removedItem.get(j));
                    //System.out.println("E: " + c.medicineCode.indexOf(removedItem.get(j)));
                    c.medQuantity.remove(c.medicineCode.indexOf(removedItem.get(j)));
                    c.medicineCode.remove(removedItem.get(j));
                }
                System.out.println("Medcoden: " +  removedItem.get(j));
        }

            String[] array1 = new String[c.medicineCode.size()];
            c.medicineCode.toArray(array1);
        Integer[] array2 = new Integer[c.medQuantity.size()];
        c.medQuantity.toArray(array2);

            try (var s = connection.prepareStatement(sql)){
                s.setArray(1, connection.createArrayOf("varchar", array1));
                s.setArray(2, connection.createArrayOf("int", array2));
                s.setObject(3, accountID);

                var rs = s.executeQuery();

                if (rs.next()){
                    return getCart(accountID);
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

    public Cart addItemToCart(UUID accountID, String addedItem, int quantityAdd){
        var sqlNew = "UPDATE \"Cart\" SET   \"medicineCode\" = array_append(\"medicineCode\", ?),  \"medQuantity\" = array_append(\"medQuantity\", ?) WHERE \"accountID\" = ? RETURNING *";
        var sqlExist = "UPDATE \"Cart\" SET \"medQuantity\" =? WHERE \"accountID\" = ? RETURNING *";


        Cart c = getCart(accountID);
        int index = c.medicineCode.size();

        if (c.medicineCode.contains(addedItem)){
            int indexData = c.medicineCode.indexOf(addedItem);
            int data = c.medQuantity.get(indexData);
            c.medQuantity.set(indexData, data + quantityAdd);
            Integer[] array2 = new Integer[c.medQuantity.size()];
            c.medQuantity.toArray(array2);

            try(var s = connection.prepareStatement(sqlExist)){
                s.setArray(1, connection.createArrayOf("int",array2));
                s.setObject(2, accountID);

                var rs = s.executeQuery();

                if (rs.next()){
                    return getCart(accountID);
                }
                else {
                    return null;
                }


            }
            catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        else {
            try(var s = connection.prepareStatement(sqlNew)){
                s.setString(1, addedItem);
                s.setInt(2, quantityAdd);
                s.setObject(3, accountID);

                var rs = s.executeQuery();

                if (rs.next()){
                    return getCart(accountID);
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
    }

    public Cart createCart(UUID accountID){
        var sql = "insert into \"Cart\" (\"accountID\") values (?) returning * ";
        try (var s = connection.prepareStatement(sql)){
            s.setObject(1, accountID);

            var rs = s.executeQuery();

            if (rs.next()){
                return new Cart(rs.getInt("cartID"),
                        (UUID) rs.getObject("accountID"));
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

    public Cart incrementMed(UUID accountID, String addedItem){
        var sqlExist = "UPDATE \"Cart\" SET \"medQuantity\" =? WHERE \"accountID\" = ? RETURNING *";

        Cart c = getCart(accountID);


            int indexData = c.medicineCode.indexOf(addedItem);
            int data = c.medQuantity.get(indexData);
            c.medQuantity.set(indexData, data + 1);
            Integer[] array2 = new Integer[c.medQuantity.size()];
            c.medQuantity.toArray(array2);

            try(var s = connection.prepareStatement(sqlExist)){
                s.setArray(1, connection.createArrayOf("int",array2));
                s.setObject(2, accountID);

                var rs = s.executeQuery();

                if (rs.next()){
                    return getCart(accountID);
                }
                else {
                    return null;
                }


            }
            catch(SQLException e){
                e.printStackTrace();
                return null;
            }



    }

    public Cart decrementMed(UUID accountID, String addedItem){
        var sqlExist = "UPDATE \"Cart\" SET \"medQuantity\" =? WHERE \"accountID\" = ? RETURNING *";

        Cart c = getCart(accountID);


        int indexData = c.medicineCode.indexOf(addedItem);
        int data = c.medQuantity.get(indexData);
        c.medQuantity.set(indexData, data - 1);
        Integer[] array2 = new Integer[c.medQuantity.size()];
        c.medQuantity.toArray(array2);

        try(var s = connection.prepareStatement(sqlExist)){
            s.setArray(1, connection.createArrayOf("int",array2));
            s.setObject(2, accountID);

            var rs = s.executeQuery();

            if (rs.next()){
                return getCart(accountID);
            }
            else {
                return null;
            }


        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }



    }

}

