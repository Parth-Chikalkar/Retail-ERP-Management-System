package Utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public Connection dbCon (){
        try{
            Dotenv enn = Dotenv.configure().load();
            String url = enn.get("DB_URL");
            String user = enn.get("DB_USER");
            String pass = enn.get("DB_PASS");
            Connection con = DriverManager.getConnection(url,user,pass);
            System.out.println("Connected Succesfully");
            return con;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
