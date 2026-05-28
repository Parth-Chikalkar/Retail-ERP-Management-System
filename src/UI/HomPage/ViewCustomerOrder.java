package UI.HomPage;

import Controllers.OrderController;
import Models.ViewOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewCustomerOrder extends JPanel {

    public ViewCustomerOrder() {

        setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("Enter Customer Id"));

        JTextField tf = new JTextField(10);
        topPanel.add(tf);

        JButton submit = new JButton("Submit");
        topPanel.add(submit);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Name : ");
        label.setVisible(false);

        centerPanel.add(label, BorderLayout.NORTH);

        String[] col = {
                "Products Ordered",
                "Quantity",
                "Total"
        };

        DefaultTableModel model = new DefaultTableModel(col, 0);

        JTable table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);

        pane.setVisible(false);

        centerPanel.add(pane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();

        JLabel totalLabel = new JLabel("Total Purchase Order : ");

        bottomPanel.add(totalLabel);

        bottomPanel.setVisible(false);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button Action
        submit.addActionListener(e -> {

            try {

                int cu_id = Integer.parseInt(tf.getText());

                ArrayList<ViewOrder> order =
                        new OrderController().getSingleCutomerData(cu_id);

                if(order == null || order.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "No Orders Found"
                    );

                    return;
                }

                // clear old rows
                model.setRowCount(0);

                String name = order.get(0).getCustomer_name();

                label.setText("Name : " + name);

                int totalPurchase = 0;

                for (ViewOrder ord : order) {

                    model.addRow(new Object[]{
                            ord.getProduct_name(),
                            ord.getQuantity(),
                            ord.getTotal()
                    });

                    totalPurchase += ord.getTotal();
                }

                totalLabel.setText(
                        "Total Purchase Order : " + totalPurchase
                );

                pane.setVisible(true);
                bottomPanel.setVisible(true);
                label.setVisible(true);

                revalidate();
                repaint();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Customer ID"
                );
            }
        });
    }
}