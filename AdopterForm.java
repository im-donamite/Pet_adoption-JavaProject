package petadoption;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;

import net.proteanit.sql.DbUtils;

public class AdopterForm extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtAdopterId;
    private JTextField txtAdopterName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtAddress;

    private JTable table;


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {

                AdopterForm frame = new AdopterForm();

                frame.setVisible(true);

            } catch (Exception e) {

                e.printStackTrace();

            }

        });

    }


    public AdopterForm() {

        setTitle("Manage Adopters");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setBounds(100, 100, 750, 550);


        // =========================
        // CONTENT PANE
        // =========================

        contentPane = new JPanel();

        contentPane.setBorder(
                new EmptyBorder(5, 5, 5, 5)
        );

        setContentPane(contentPane);

        contentPane.setLayout(null);


        // =========================
        // ADOPTER ID
        // =========================

        JLabel lblAdopterId =
                new JLabel("Adopter ID");

        lblAdopterId.setBounds(
                30, 30, 100, 25
        );

        contentPane.add(lblAdopterId);


        txtAdopterId =
                new JTextField();

        txtAdopterId.setBounds(
                140, 30, 170, 25
        );

        contentPane.add(txtAdopterId);


        // =========================
        // ADOPTER NAME
        // =========================

        JLabel lblAdopterName =
                new JLabel("Adopter Name");

        lblAdopterName.setBounds(
                30, 70, 100, 25
        );

        contentPane.add(lblAdopterName);


        txtAdopterName =
                new JTextField();

        txtAdopterName.setBounds(
                140, 70, 170, 25
        );

        contentPane.add(txtAdopterName);


        // =========================
        // EMAIL
        // =========================

        JLabel lblEmail =
                new JLabel("Email");

        lblEmail.setBounds(
                30, 110, 100, 25
        );

        contentPane.add(lblEmail);


        txtEmail =
                new JTextField();

        txtEmail.setBounds(
                140, 110, 170, 25
        );

        contentPane.add(txtEmail);


        // =========================
        // PHONE
        // =========================

        JLabel lblPhone =
                new JLabel("Phone");

        lblPhone.setBounds(
                30, 150, 100, 25
        );

        contentPane.add(lblPhone);


        txtPhone =
                new JTextField();

        txtPhone.setBounds(
                140, 150, 170, 25
        );

        contentPane.add(txtPhone);


        // =========================
        // ADDRESS
        // =========================

        JLabel lblAddress =
                new JLabel("Address");

        lblAddress.setBounds(
                360, 30, 100, 25
        );

        contentPane.add(lblAddress);


        txtAddress =
                new JTextField();

        txtAddress.setBounds(
                460, 30, 200, 60
        );

        contentPane.add(txtAddress);


        // =========================
        // ADD BUTTON
        // =========================

        JButton btnAdd =
                new JButton("ADD");

        btnAdd.setBounds(
                360, 110, 100, 30
        );

        contentPane.add(btnAdd);

        btnAdd.addActionListener(
                e -> addAdopter()
        );


        // =========================
        // UPDATE BUTTON
        // =========================

        JButton btnUpdate =
                new JButton("UPDATE");

        btnUpdate.setBounds(
                470, 110, 100, 30
        );

        contentPane.add(btnUpdate);

        btnUpdate.addActionListener(
                e -> updateAdopter()
        );


        // =========================
        // DELETE BUTTON
        // =========================

        JButton btnDelete =
                new JButton("DELETE");

        btnDelete.setBounds(
                580, 110, 100, 30
        );

        contentPane.add(btnDelete);

        btnDelete.addActionListener(
                e -> deleteAdopter()
        );


        // =========================
        // SHOW BUTTON
        // =========================

        JButton btnShow =
                new JButton("SHOW");

        btnShow.setBounds(
                360, 150, 100, 30
        );

        contentPane.add(btnShow);

        btnShow.addActionListener(
                e -> showAdopters()
        );


        // =========================
        // CLEAR BUTTON
        // =========================

        JButton btnClear =
                new JButton("CLEAR");

        btnClear.setBounds(
                470, 150, 100, 30
        );

        contentPane.add(btnClear);

        btnClear.addActionListener(
                e -> clearFields()
        );


        // =========================
        // SCROLL PANE
        // =========================

        JScrollPane scrollPane =
                new JScrollPane();

        scrollPane.setBounds(
                30, 210, 650, 250
        );

        contentPane.add(scrollPane);


        // =========================
        // TABLE
        // =========================

        table =
                new JTable();

        scrollPane.setViewportView(table);


        // =========================
        // TABLE ROW CLICK
        // =========================

        table.getSelectionModel().addListSelectionListener(
                e -> {

                    if (!e.getValueIsAdjusting()
                            && table.getSelectedRow() != -1) {

                        int row =
                                table.getSelectedRow();

                        try {

                            txtAdopterId.setText(
                                    table.getValueAt(row, 0).toString()
                            );

                            txtAdopterName.setText(
                                    table.getValueAt(row, 1).toString()
                            );

                            txtEmail.setText(
                                    table.getValueAt(row, 2).toString()
                            );

                            txtPhone.setText(
                                    table.getValueAt(row, 3).toString()
                            );

                            txtAddress.setText(
                                    table.getValueAt(row, 4).toString()
                            );

                        } catch (Exception ex) {

                            ex.printStackTrace();

                        }

                    }

                }
        );

    }


    // =====================================================
    // ADD ADOPTER
    // =====================================================

    private void addAdopter() {

        String sql =
                "INSERT INTO ADOPTER "
                + "(ADOPTER_NAME, EMAIL, PHONE, ADDRESS) "
                + "VALUES (?, ?, ?, ?)";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setString(
                    1,
                    txtAdopterName.getText().trim()
            );

            pst.setString(
                    2,
                    txtEmail.getText().trim()
            );

            pst.setString(
                    3,
                    txtPhone.getText().trim()
            );

            pst.setString(
                    4,
                    txtAddress.getText().trim()
            );


            pst.executeUpdate();


            JOptionPane.showMessageDialog(
                    this,
                    "Adopter added successfully!"
            );


            pst.close();

            con.close();


            clearFields();

            showAdopters();


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

            e.printStackTrace();

        }

    }


    // =====================================================
    // SHOW ADOPTERS
    // =====================================================

    private void showAdopters() {

        String sql =
                "SELECT * FROM ADOPTER";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();


            // rs2xml converts ResultSet into JTable model

            table.setModel(
                    DbUtils.resultSetToTableModel(rs)
            );


            rs.close();

            pst.close();

            con.close();


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

            e.printStackTrace();

        }

    }


    // =====================================================
    // UPDATE ADOPTER
    // =====================================================

    private void updateAdopter() {

        String sql =
                "UPDATE ADOPTER SET "
                + "ADOPTER_NAME = ?, "
                + "EMAIL = ?, "
                + "PHONE = ?, "
                + "ADDRESS = ? "
                + "WHERE ADOPTER_ID = ?";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setString(
                    1,
                    txtAdopterName.getText().trim()
            );

            pst.setString(
                    2,
                    txtEmail.getText().trim()
            );

            pst.setString(
                    3,
                    txtPhone.getText().trim()
            );

            pst.setString(
                    4,
                    txtAddress.getText().trim()
            );

            pst.setInt(
                    5,
                    Integer.parseInt(
                            txtAdopterId.getText().trim()
                    )
            );


            int rows =
                    pst.executeUpdate();


            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Adopter updated successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Adopter ID not found."
                );

            }


            pst.close();

            con.close();


            clearFields();

            showAdopters();


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Adopter ID must be a number."
            );


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

            e.printStackTrace();

        }

    }


    // =====================================================
    // DELETE ADOPTER
    // =====================================================

    private void deleteAdopter() {

        String sql =
                "DELETE FROM ADOPTER "
                + "WHERE ADOPTER_ID = ?";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setInt(
                    1,
                    Integer.parseInt(
                            txtAdopterId.getText().trim()
                    )
            );


            int rows =
                    pst.executeUpdate();


            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Adopter deleted successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Adopter ID not found."
                );

            }


            pst.close();

            con.close();


            clearFields();

            showAdopters();


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Adopter ID."
            );


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

            e.printStackTrace();

        }

    }


    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        txtAdopterId.setText("");

        txtAdopterName.setText("");

        txtEmail.setText("");

        txtPhone.setText("");

        txtAddress.setText("");

        table.clearSelection();

    }

}