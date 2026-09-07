package petadoption;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {

        // WINDOW SETTINGS
        setTitle("Pet Adoption Management System");

        setSize(500, 550);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);


        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(new Color(245, 247, 250));

        mainPanel.setBorder(
                new EmptyBorder(40, 60, 40, 60)
        );


        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel();

        headerPanel.setBackground(new Color(245, 247, 250));

        headerPanel.setLayout(
                new GridLayout(3, 1)
        );


        JLabel iconLabel = new JLabel(
                "🐾",
                SwingConstants.CENTER
        );

        iconLabel.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 40)
        );


        JLabel titleLabel = new JLabel(
                "PET ADOPTION SYSTEM",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        titleLabel.setForeground(
                new Color(45, 70, 55)
        );


        JLabel subtitleLabel = new JLabel(
                "Find a friend. Give a home.",
                SwingConstants.CENTER
        );

        subtitleLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        subtitleLabel.setForeground(
                new Color(100, 100, 100)
        );


        headerPanel.add(iconLabel);

        headerPanel.add(titleLabel);

        headerPanel.add(subtitleLabel);


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =========================
        // LOGIN CARD
        // =========================

        JPanel loginPanel = new JPanel();

        loginPanel.setBackground(Color.WHITE);

        loginPanel.setLayout(
                new GridLayout(6, 1, 10, 10)
        );

        loginPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(25, 30, 25, 30)
                )
        );


        // USERNAME LABEL
        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );


        // USERNAME FIELD
        usernameField = new JTextField();

        usernameField.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        usernameField.setPreferredSize(
                new Dimension(300, 40)
        );


        // PASSWORD LABEL
        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );


        // PASSWORD FIELD
        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );


        // LOGIN BUTTON
        loginButton =
                new JButton("LOGIN");

        loginButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        loginButton.setFocusPainted(false);

        loginButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        loginButton.setBackground(
                new Color(60, 110, 80)
        );

        loginButton.setForeground(
                Color.WHITE
        );


        loginPanel.add(usernameLabel);

        loginPanel.add(usernameField);

        loginPanel.add(passwordLabel);

        loginPanel.add(passwordField);

        loginPanel.add(new JLabel(""));

        loginPanel.add(loginButton);


        mainPanel.add(
                loginPanel,
                BorderLayout.CENTER
        );


        // ADD MAIN PANEL
        add(mainPanel);


        // LOGIN BUTTON ACTION
        loginButton.addActionListener(
                e -> login()
        );


        // PRESS ENTER TO LOGIN
        passwordField.addActionListener(
                e -> login()
        );


        setVisible(true);
    }


    // =========================
    // LOGIN METHOD
    // =========================

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );


        // CHECK EMPTY FIELDS
        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password."
            );

            return;
        }


        // SQL QUERY
        String sql =
                "SELECT * FROM users "
                + "WHERE username = ? "
                + "AND password = ?";


        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {


            statement.setString(
                    1,
                    username
            );

            statement.setString(
                    2,
                    password
            );


            ResultSet result =
                    statement.executeQuery();


            if (result.next()) {


                JOptionPane.showMessageDialog(
                        this,
                        "Login successful!"
                );


                // OPEN DASHBOARD
                new Dashboard();


                // CLOSE LOGIN WINDOW
                dispose();


            } else {


                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password!"
                );

                passwordField.setText("");

            }


        } catch (SQLException e) {


            JOptionPane.showMessageDialog(
                    this,
                    "Database error! "
                    + "Please check the connection."
            );

            e.printStackTrace();

        }

    }

}