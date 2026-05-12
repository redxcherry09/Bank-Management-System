package Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    String pin;
    TextField textField;
    JButton b1, b2;

    Deposit(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("connection/icon1/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 400, 35);
        l3.add(label1);

        textField = new TextField();
        textField.setBounds(460, 230, 320, 25);
        l3.add(textField);

        b1 = new JButton("DEPOSIT");
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

        String amount = textField.getText().trim();

        if (!amount.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Enter valid amount");
            return;
        }

        try {
            Conn c = new Conn();

            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date());

            PreparedStatement ps = c.connection.prepareStatement(
                    "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, pin);
            ps.setString(2, date);
            ps.setString(3, "Deposit");
            ps.setString(4, amount);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");

            setVisible(false);
            new main_Class(pin);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("1234");
    }
}
