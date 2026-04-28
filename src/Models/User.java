package Models;

public class User {
    private int u_id;
    private String name ;
    private String password ;
    private String email ;
    private String role ;

    public User(int u_id , String name , String password , String email , String role ){
        this.u_id = u_id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getEmail (){
        return this.email;
    }
    public String getPaaword (){
        return this.password;
    }
    public String getRole (){
        return this.role;
    }
}
