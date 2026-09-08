package petadoption;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Pet Adoption Management System - Dashboard");

        setSize(650, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);


        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel =
                new JPanel(new BorderLayout(20, 20));

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        mainPanel.setBorder(
                new EmptyBorder(35, 50, 35, 50)
        );


        // =========================
        // HEADER
        // =========================

        JPanel headerPanel =
                new JPanel(new GridLayout(3, 1));

        headerPanel.setBackground(
                new Color(245, 247, 250)
        );


        JLabel iconLabel =
                new JLabel(
                        "🐾",
                        SwingConstants.CENTER
                );

        iconLabel.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        38
                )
        );


        JLabel titleLabel =
                new JLabel(
                        "PET ADOPTION MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        titleLabel.setForeground(
                new Color(45, 70, 55)
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Manage shelters, pets, adopters and adoptions",
                        SwingConstants.CENTER
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
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
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );

        buttonPanel.setBackground(
                new Color(245, 247, 250)
        );


        JButton shelterButton =
                createDashboardButton(
                        "🏠  MANAGE SHELTERS"
                );


        JButton petButton =
                createDashboardButton(
                        "🐶  MANAGE PETS"
                );


        JButton adopterButton =
                createDashboardButton(
                        "👤  MANAGE ADOPTERS"
                );


        JButton adoptionButton =
                createDashboardButton(
                        "❤️  MANAGE ADOPTIONS"
                );


        buttonPanel.add(shelterButton);

        buttonPanel.add(petButton);

        buttonPanel.add(adopterButton);

        buttonPanel.add(adoptionButton);


        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );


        // =========================
        // FOOTER
        // =========================

        JLabel footerLabel =
                new JLabel(
                        "Pet Adoption Management System",
                        SwingConstants.CENTER
                );

        footerLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        footerLabel.setForeground(
                new Color(130, 130, 130)
        );


        mainPanel.add(
                footerLabel,
                BorderLayout.SOUTH
        );


        // =========================
        // ADD MAIN PANEL
        // =========================

        add(mainPanel);


        // =========================
        // BUTTON ACTIONS
        // =========================


        // MANAGE SHELTERS

        shelterButton.addActionListener(e -> {

            ShelterForm shelterForm =
                    new ShelterForm();

            shelterForm.setVisible(true);

        });


        // MANAGE PETS

        petButton.addActionListener(e -> {

            PetForm petForm =
                    new PetForm();

            petForm.setVisible(true);

        });


        // MANAGE ADOPTERS

        adopterButton.addActionListener(e -> {

            AdopterForm adopterForm =
                    new AdopterForm();

            adopterForm.setVisible(true);

        });


        // MANAGE ADOPTIONS

        adoptionButton.addActionListener(e -> {

            AdoptionForm adoptionForm =
                    new AdoptionForm();

            adoptionForm.setVisible(true);

        });


        // SHOW DASHBOARD

        setVisible(true);

    }


    // =========================
    // CREATE DASHBOARD BUTTON
    // =========================

    private JButton createDashboardButton(
            String text
    ) {

        JButton button =
                new JButton(text);


        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );


        button.setForeground(
                Color.WHITE
        );


        button.setBackground(
                new Color(60, 110, 80)
        );


        button.setFocusPainted(false);


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.setPreferredSize(
                new Dimension(
                        200,
                        80
                )
        );


        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );


        return button;

    }

}