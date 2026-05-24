package UI.HomPage;

import Controllers.ProductController;

import javax.swing.*;
import java.awt.*;

public class DeleteProductForm extends JPanel {

    public DeleteProductForm() {
        setLayout(new FlowLayout());

        JTextField id = new JTextField(10);
        JButton delete = new JButton("Delete");

        add(new JLabel("Product ID:"));
        add(id);
        add(delete);

        delete.addActionListener(e -> {
             int idVal = Integer.parseInt(id.getText()) ;
             ProductController con = new ProductController();
             boolean bool = con.deleteProduct(idVal);
             if(bool){
                 JOptionPane.showMessageDialog(this, "Product Deleted Sucessfully ");
                 id.setText("");
             }
             else{
                 JOptionPane.showMessageDialog(this, "Failed To DELETE Product ");
             }
        });
    }
}