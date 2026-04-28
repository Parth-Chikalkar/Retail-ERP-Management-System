package Controllers;

import Models.User;
import Utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    public User Login (String email , String password){

        try{
            DBConnect connect = new DBConnect();
            String query = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";
            Connection con = connect.dbCon();
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1,email);
            stm.setString(2,password);

           ResultSet set =  stm.executeQuery();
           while (set.next()){
               return new User(set.getInt("u_id"),
                       set.getString("NAME"),
                       set.getString("EMAIL"),
                       set.getString("PASSWORD"),
                       set.getString("ROLE"));
           }


        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
return null;
    }
}
