package Controllers;

import Models.Product;
import Utils.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    public boolean addProduct (Product product){
        String sql = "INSERT INTO PRODUCT (NAME , DESCRIPTION ,STOCK ,PRICE) VALUES (?,?,?,?)";
        try{
            DBConnect conn = new DBConnect();
            Connection con = conn.dbCon();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1,product.getName());
            stm.setString(2,product.getDescription());
            stm.setInt(3, product.getStock());
            stm.setInt(4,product.getPrice());

            stm.executeUpdate();
            return true;



        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public List<Product> getAllProducts (){
        String sql = "SELECT NAME NAME, DESCRIPTION, STOCK, PRICE FROM PRODUCT";
        try{
            DBConnect connect = new DBConnect();
            Connection con = connect.dbCon();
            Statement stm = con.createStatement();
            List <Product> list = new ArrayList<>();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                list.add(new Product(rs.getString("NAME"),rs.getString("DESCRIPTION"),rs.getInt("STOCK"),rs.getInt("PRICE")));
            }
            return list;

        } catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public boolean deleteProduct(int id ){
        String sql = "DELETE FROM PRODUCT WHERE p_id = ?";
        try{
            DBConnect con = new DBConnect();
            Connection conc = con.dbCon();
            PreparedStatement stm = conc.prepareStatement(sql);
            stm.setInt(1,id);
            stm.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
