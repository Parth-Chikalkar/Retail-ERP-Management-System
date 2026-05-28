package Controllers;

import Models.ViewOrder;
import Utils.DBConnect;
import Utils.HelperFunctions;

import javax.swing.text.View;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    public boolean addOrder(String name ,String description , int quantity , int cu_id ){

//Pahila Orders madhe and oid generate krnar ;

        String sql1 = "INSERT INTO ORDERS (cu_id , total) VALUES ( ? ,?) ";

        String sql = "INSERT INTO ORDER_ITEMS (o_id, p_id, quantity , price ) values (?,?,?, ?)";
        ///Product cha stock pn update kr re parth obv

        String sql2 = "UPDATE PRODUCT SET STOCK = ? WHERE P_ID = ? ";
      ///price = quantity * single price



        try{
            Connection con = new DBConnect().dbCon();

            PreparedStatement stm = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1,cu_id);
            // Calculation of total ;
            HelperFunctions functions = new HelperFunctions();
            // Retrival of product data
            List<String > p_data = new ProductController().getProductIdAndStockAndPrice(name);
            int p_id =  Integer.parseInt(p_data.get(0));
            int pricePerProduct = Integer.parseInt(p_data.get(1));
            int stock =  Integer.parseInt(p_data.get(2));

            // check
            if(quantity> stock) return false;

            int total = functions.calculateTotal(quantity,pricePerProduct);

            // Insert in tb1 orders
            stm.setInt(2,total);
            stm.executeUpdate();
            ResultSet set = stm.getGeneratedKeys();
            int oid = -1;
            if (set.next()){
                oid = set.getInt(1);
            }

            // Step2 insert into order_items
            PreparedStatement stm2 = con.prepareStatement(sql);
            stm2.setInt(1,oid);
            stm2.setInt(2,p_id);
            stm2.setInt(3,quantity);
            stm2.setInt(4,total);

            stm2.executeUpdate();

            // Step3 reduce the stock

            PreparedStatement stm3 = con.prepareStatement(sql2);
            stm3.setInt(1,stock-quantity);
            stm3.setInt(2,p_id);
            stm3.executeUpdate();

            return true;
        }
        catch (SQLException e){

            System.out.println(e.getMessage());
            return false;
        }

    }

    public ArrayList<ViewOrder> getAllOrder(){
        String sqlQuery = " SELECT o.O_ID , u.cu_id , s.name as customer , p.name as product , i.quantity , i.price FROM ORDER_ITEMS i JOIN ORDERS o ON i.O_ID = o.O_ID " +
                "JOIN customers u ON o.cu_id = u.cu_id "+
                "JOIN USERS s ON s.u_id = u.u_id "+
                "JOIN PRODUCT p ON i.p_id = p.p_id; ";

        try{
           Connection con = new DBConnect().dbCon();
           PreparedStatement stm = con.prepareStatement(sqlQuery);
           ResultSet set = stm.executeQuery();
            ArrayList<ViewOrder> list = new ArrayList<>();

            while (set.next()){
                list.add(new ViewOrder(
                        set.getInt("O_ID"),
                        set.getString("customer"),
                        set.getString("product"),
                        set.getInt("quantity"),
                        set.getInt("price"),
                        set.getInt("cu_id")
                ));



            }
            return list;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;

        }

    }

    public ArrayList<ViewOrder> getSingleCutomerData(int cu_id){
        String sql = "SELECT NAME FROM USERS WHERE u_id IN (select u_id from CUSTOMERS WHERE cu_id = ? ) ";
String sql2 = " SELECT P.name, i.QUANTITY , i.PRICE from ORDER_ITEMS i JOIN PRODUCT p ON i.p_id = p.p_id WHERE O_ID in (SELECT O_ID FROM ORDERS WHERE cu_id = ?);";
        try{
            Connection con = new DBConnect().dbCon();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,cu_id);
            ResultSet set = stm.executeQuery();
            String name = null;

            if(set.next()){
                name = set.getString("NAME");
            }
            PreparedStatement stm2 = con.prepareStatement(sql2);
            stm2.setInt(1,cu_id);
            ResultSet set2 = stm2.executeQuery();
            ArrayList <ViewOrder> list = new ArrayList<>();
           while (set2.next()){
               list.add(new ViewOrder(set2.getString("NAME"),set2.getInt("QUANTITY"),set2.getInt("PRICE"),name));
           }

            return list;
        } catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
