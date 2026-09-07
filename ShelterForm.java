package petadoption;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class ShelterForm extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtShelterId;
    private JTextField txtShelterName;
    private JTextField txtLocation;
    private JTextField txtPhone;

    private JTable table;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {

                ShelterForm frame = new ShelterForm();

                frame.setVisible(true);

            } catch (Exception e) {

                e.printStackTrace();

            }

        });

    }

    public ShelterForm() {

        setTitle("Manage Shelters");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 750, 520);

        contentPane = new JPanel();

        contentPane.setBorder(
                new EmptyBorder(5, 5, 5, 5)
        );

        setContentPane(contentPane);

        contentPane.setLayout(null);


        // =========================
        // SHELTER ID
        // =========================

        JLabel lblShelterId =
                new JLabel("Shelter ID");

        lblShelterId.setBounds(
                30, 30, 100, 25
        );

        contentPane.add(lblShelterId);


        txtShelterId =
                new JTextField();

        txtShelterId.setBounds(
                140, 30, 170, 25
        );

        contentPane.add(txtShelterId);


        // =========================
        // SHELTER NAME
        // =========================

        JLabel lblShelterName =
                new JLabel("Shelter Name");

        lblShelterName.setBounds(
                30, 70, 100, 25
        );

        contentPane.add(lblShelterName);


        txtShelterName =
                new JTextField();

        txtShelterName.setBounds(
                140, 70, 170, 25
        );

        contentPane.add(txtShelterName);


        // =========================
        // LOCATION
        // =========================

        JLabel lblLocation =
                new JLabel("Location");

        lblLocation.setBounds(
                30, 110, 100, 25
        );

        contentPane.add(lblLocation);


        txtLocation =
                new JTextField();

        txtLocation.setBounds(
                140, 110, 170, 25
        );

        contentPane.add(txtLocation);


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
        // ADD BUTTON
        // =========================

        JButton btnAdd =
                new JButton("ADD");

        btnAdd.setBounds(
                350, 30, 100, 30
        );

        contentPane.add(btnAdd);

        btnAdd.addActionListener(
                e -> addShelter()
        );


        // =========================
        // UPDATE BUTTON
        // =========================

        JButton btnUpdate =
                new JButton("UPDATE");

        btnUpdate.setBounds(
                460, 30, 100, 30
        );

        contentPane.add(btnUpdate);

        btnUpdate.addActionListener(
                e -> updateShelter()
        );


        // =========================
        // DELETE BUTTON
        // =========================

        JButton btnDelete =
                new JButton("DELETE");

        btnDelete.setBounds(
                570, 30, 100, 30
        );

        contentPane.add(btnDelete);

        btnDelete.addActionListener(
                e -> deleteShelter()
        );


        // =========================
        // SHOW BUTTON
        // =========================

        JButton btnShow =
                new JButton("SHOW");

        btnShow.setBounds(
                350, 70, 100, 30
        );

        contentPane.add(btnShow);

        btnShow.addActionListener(
                e -> showShelters()
        );


        // =========================
        // CLEAR BUTTON
        // =========================

        JButton btnClear =
                new JButton("CLEAR");

        btnClear.setBounds(
                460, 70, 100, 30
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
                30, 210, 640, 240
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

                        txtShelterId.setText(
                                table.getValueAt(row, 0).toString()
                        );

                        txtShelterName.setText(
                                table.getValueAt(row, 1).toString()
                        );

                        txtLocation.setText(
                                table.getValueAt(row, 2).toString()
                        );

                        txtPhone.setText(
                                table.getValueAt(row, 3).toString()
                        );
                    }
                }
        );

    }


    // =========================
    // ADD SHELTER
    // =========================

    private void addShelter() {

        String sql =
                "INSERT INTO shelter "
                + "(shelter_name, location, phone) "
                + "VALUES (?, ?, ?)";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(
                    1,
                    txtShelterName.getText().trim()
            );

            pst.setString(
                    2,
                    txtLocation.getText().trim()
            );

            pst.setString(
                    3,
                    txtPhone.getText().trim()
            );

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Shelter added successfully!"
            );

            pst.close();
            con.close();

            clearFields();

            showShelters();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

            e.printStackTrace();
        }
    }


    // =========================
    // SHOW SHELTERS
    // =========================

    private void showShelters() {

        String sql =
                "SELECT * FROM shelter";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();


            // rs2xml converts ResultSet
            // into JTable's TableModel

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


    // =========================
    // UPDATE SHELTER
    // =========================

    private void updateShelter() {

        String sql =
                "UPDATE shelter SET "
                + "shelter_name = ?, "
                + "location = ?, "
                + "phone = ? "
                + "WHERE shelter_id = ?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setString(
                    1,
                    txtShelterName.getText().trim()
            );

            pst.setString(
                    2,
                    txtLocation.getText().trim()
            );

            pst.setString(
                    3,
                    txtPhone.getText().trim()
            );

            pst.setInt(
                    4,
                    Integer.parseInt(
                            txtShelterId.getText().trim()
                    )
            );


            int rows =
                    pst.executeUpdate();


            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Shelter updated successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Shelter ID not found."
                );
            }


            pst.close();
            con.close();

            clearFields();

            showShelters();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Shelter ID."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

            e.printStackTrace();
        }
    }


    // =========================
    // DELETE SHELTER
    // =========================

    private void deleteShelter() {

        String sql =
                "DELETE FROM shelter "
                + "WHERE shelter_id = ?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setInt(
                    1,
                    Integer.parseInt(
                            txtShelterId.getText().trim()
                    )
            );


            int rows =
                    pst.executeUpdate();


            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Shelter deleted successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Shelter ID not found."
                );
            }


            pst.close();
            con.close();

            clearFields();

            showShelters();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Shelter ID."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

            e.printStackTrace();
        }
    }


    // =========================
    // CLEAR FIELDS
    // =========================

    private void clearFields() {

        txtShelterId.setText("");

        txtShelterName.setText("");

        txtLocation.setText("");

        txtPhone.setText("");

        table.clearSelection();
    }

}