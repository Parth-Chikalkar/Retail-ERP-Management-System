package UI.HomPage;

import Controllers.UserController;
import Models.User;
import javax.swing.*;

public class Window extends JFrame {
    public Window (){
        this.setSize(500,500);
        this.setTitle("E-COMM");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Login log = new Login();
        log.addLoginListener(e -> {
            String email = log.getEmail();
            String pass = log.getPassword();
            UserController controller = new UserController();
            User user = controller.Login(email,pass);
            if(user == null){
                JOptionPane.showMessageDialog(null, "Invalid credentials");
            }
            else{
                System.out.println(user.getRole());
            }
        });
        this.add(log);

        this.setVisible(true);
    }
}
