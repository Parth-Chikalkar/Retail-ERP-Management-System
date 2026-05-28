package UI.HomPage;
import javax.swing.*;
import java.awt.*;

public class Admin extends JPanel {

    private JPanel content;

    public Admin() {
        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(10, 1, 10, 10));
        sidebar.setBackground(new Color(17, 24, 39));
        sidebar.setPreferredSize(new Dimension(180, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        content = new JPanel(new BorderLayout());
        content.setBackground(new Color(249, 250, 251));



        sidebar.add(createButton("Add Product", e -> showPanel(new AddProductForm())));
        sidebar.add(createButton("Delete Product", e -> showPanel(new DeleteProductForm())));
        sidebar.add(createButton("Update Product", e -> showPanel(new UpdateProductForm())));
        sidebar.add(createButton("View Products", e -> showPanel(new ViewProducts())));
        sidebar.add(createButton("View Orders", e -> showPanel(new ViewOrders())));
        sidebar.add(createButton("Add Customer", e -> showPanel(new AddCustomerForm())));
        sidebar.add(createButton("View Customers", e -> showPanel(new ViewCustomers())));
        sidebar.add(createButton("Update Customer",e -> showPanel(new UpdateCustomerFrom())));
        sidebar.add(createButton("Delete Customer", e-> showPanel(new DeleteCustomerForm())));
        sidebar.add(createButton("View Customer Order",e->showPanel(new ViewCustomerOrder())));

        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        showPanel(new JLabel("Admin Dashboard", SwingConstants.CENTER));
    }

    private void showPanel(Component panel) {
        content.removeAll();
        content.add(panel, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(31, 41, 55));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btn.addActionListener(action);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(55, 65, 81));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(31, 41, 55));
            }
        });

        return btn;
    }
}