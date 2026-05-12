package Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class mini extends JFrame implements ActionListener {
    String pin;
    JButton button;

    mini(String pin) {
        this.pin = pin;

        getContentPane().setBackground(new Color(255, 204, 204));
        setSize(400, 600);
        setLocation(20, 20);
        setLayout(null);

        JLabel label1 = new JLabel("<html><b>Transaction History:</b><br><br></html>");
        label1.setBounds(20, 140, 350, 200);
        add(label1);

        JLabel label2 = new JLabel("TechCoder A.V");
        label2.setFont(new Font("System", Font.BOLD, 15));
        label2.setBounds(150, 20, 200, 20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20, 80, 350, 20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20, 400, 350, 20);
        add(label4);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery(
                    "SELECT * FROM login WHERE pin = '" + pin + "'"
            );
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("card_number");
                label3.setText("Card Number:  " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            double balance = 0.0;
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery(
                    "SELECT * FROM bank WHERE pin = '" + pin + "'"
            );

            StringBuilder history = new StringBuilder("<html>");

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String type = resultSet.getString("type");
                String amountStr = resultSet.getString("amount").trim();
                double amount;

                try {
                    amount = Double.parseDouble(amountStr);
                } catch (Exception ex) {
                    continue; // skip bad data
                }

                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amount;
                } else if (type.equalsIgnoreCase("Withdraw") || type.equalsIgnoreCase("Withdrawl")) {
                    balance -= amount;
                }

                // Append transaction to history
                history.append(date)
                        .append("&nbsp;&nbsp;&nbsp;")
                        .append(type)
                        .append("&nbsp;&nbsp;&nbsp;Rs. ")
                        .append(String.format("%.2f", amount))
                        .append("<br><br>");
            }

            history.append("</html>");
            label1.setText(history.toString());
            label4.setText("Your Total Balance is Rs " + String.format("%.2f", balance));

        } catch (Exception e) {
            e.printStackTrace();
        }

        button = new JButton("Exit");
        button.setBounds(20, 500, 100, 25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
