package Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    String pin;
    TextField textField;
    JButton b1, b2;

    Withdrawl(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("connection/icon1/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        label1.setForeground(Color.WHITE);
        label1.setBounds(460, 180, 400, 35);
        l3.add(label1);

        textField = new TextField();
        textField.setBounds(460, 230, 320, 25);
        l3.add(textField);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(700, 362, 150, 35);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b2) {
            setVisible(false);
            new main_Class(pin);
            return;
        }

        String amountStr = textField.getText().trim();

        if (!amountStr.matches("\\d+(\\.\\d{1,2})?")) { // ✅ accept 1000 or 1000.00
            JOptionPane.showMessageDialog(null, "Enter valid numeric amount");
            return;
        }

        double withdrawAmount = Double.parseDouble(amountStr);

        if (withdrawAmount > 10000) {
            JOptionPane.showMessageDialog(null, "Max limit 10,000");
            return;
        }

        try {
            Conn c = new Conn();

            PreparedStatement ps = c.connection.prepareStatement(
                    "SELECT type, amount FROM bank WHERE pin=?"
            );
            ps.setString(1, pin);

            ResultSet rs = ps.executeQuery();

            double balance = 0;

            while (rs.next()) {
                String type = rs.getString("type");
                double amt = Double.parseDouble(rs.getString("amount").trim());

                if (type != null && type.trim().equalsIgnoreCase("Deposit")) {
                    balance += amt;
                } else if (type != null && type.trim().equalsIgnoreCase("Withdraw")) {
                    balance -= amt;
                }
            }

            if (balance < withdrawAmount) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            PreparedStatement ps2 = c.connection.prepareStatement(
                    "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, ?, ?)"
            );

            ps2.setString(1, pin);
            ps2.setString(2, date);
            ps2.setString(3, "Withdraw");
            ps2.setString(4, String.format("%.2f", withdrawAmount));

            ps2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Rs. " + amountStr + " Withdrawn Successfully");

            setVisible(false);
            new main_Class(pin);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Withdrawl("1234");
    }
}
