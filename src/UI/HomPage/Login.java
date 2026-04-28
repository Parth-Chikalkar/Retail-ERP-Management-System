package UI.HomPage;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {

    public JTextField username;
    public JPasswordField password;
    JButton login;
    JLabel title;

    public Login() {


        setBackground(new Color(249, 250, 251));
        setLayout(new GridBagLayout());


        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(300, 300));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        title = new JLabel("QuickCart Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        username = new JTextField();
        username.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        username.setBorder(BorderFactory.createTitledBorder("Email"));
        username.setFont(new Font("Segoe UI", Font.PLAIN, 14));


        password = new JPasswordField();
        password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        password.setBorder(BorderFactory.createTitledBorder("Password"));
        password.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        login = new JButton("Login");
        login.setBackground(new Color(79, 70, 229));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setFont(new Font("Segoe UI", Font.BOLD, 14));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));


        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(username);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(password);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(login);

        add(card);
    }
    //Getters
    public String getEmail () {
        return username.getText();
    }
    public String getPassword (){
        return password.getText();
    }
    //EveListen
    public void addLoginListener(java.awt.event.ActionListener listener) {
        login.addActionListener(listener);
    }
}