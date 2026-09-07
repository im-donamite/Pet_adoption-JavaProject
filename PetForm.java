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

public class PetForm extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtPetId;
    private JTextField txtPetName;
    private JTextField txtSpecies;
    private JTextField txtBreed;
    private JTextField txtAge;
    private JTextField txtGender;
    private JTextField txtStatus;
    private JTextField txtShelterId;

    private JTable table;


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {

                PetForm frame = new PetForm();

                frame.setVisible(true);

            } catch (Exception e) {

                e.printStackTrace();

            }

        });

    }


    public PetForm() {

        setTitle("Manage Pets");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setBounds(100, 100, 850, 600);


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
        // PET ID
        // =========================

        JLabel lblPetId =
                new JLabel("Pet ID");

        lblPetId.setBounds(
                30, 30, 100, 25
        );

        contentPane.add(lblPetId);


        txtPetId =
                new JTextField();

        txtPetId.setBounds(
                140, 30, 170, 25
        );

        contentPane.add(txtPetId);


        // =========================
        // PET NAME
        // =========================

        JLabel lblPetName =
                new JLabel("Pet Name");

        lblPetName.setBounds(
                30, 70, 100, 25
        );

        contentPane.add(lblPetName);


        txtPetName =
                new JTextField();

        txtPetName.setBounds(
                140, 70, 170, 25
        );

        contentPane.add(txtPetName);


        // =========================
        // SPECIES
        // =========================

        JLabel lblSpecies =
                new JLabel("Species");

        lblSpecies.setBounds(
                30, 110, 100, 25
        );

        contentPane.add(lblSpecies);


        txtSpecies =
                new JTextField();

        txtSpecies.setBounds(
                140, 110, 170, 25
        );

        contentPane.add(txtSpecies);


        // =========================
        // BREED
        // =========================

        JLabel lblBreed =
                new JLabel("Breed");

        lblBreed.setBounds(
                30, 150, 100, 25
        );

        contentPane.add(lblBreed);


        txtBreed =
                new JTextField();

        txtBreed.setBounds(
                140, 150, 170, 25
        );

        contentPane.add(txtBreed);


        // =========================
        // AGE
        // =========================

        JLabel lblAge =
                new JLabel("Age");

        lblAge.setBounds(
                380, 30, 100, 25
        );

        contentPane.add(lblAge);


        txtAge =
                new JTextField();

        txtAge.setBounds(
                490, 30, 170, 25
        );

        contentPane.add(txtAge);


        // =========================
        // GENDER
        // =========================

        JLabel lblGender =
                new JLabel("Gender");

        lblGender.setBounds(
                380, 70, 100, 25
        );

        contentPane.add(lblGender);


        txtGender =
                new JTextField();

        txtGender.setBounds(
                490, 70, 170, 25
        );

        contentPane.add(txtGender);


        // =========================
        // STATUS
        // =========================

        JLabel lblStatus =
                new JLabel("Status");

        lblStatus.setBounds(
                380, 110, 100, 25
        );

        contentPane.add(lblStatus);


        txtStatus =
                new JTextField();

        txtStatus.setBounds(
                490, 110, 170, 25
        );

        contentPane.add(txtStatus);


        // =========================
        // SHELTER ID
        // =========================

        JLabel lblShelterId =
                new JLabel("Shelter ID");

        lblShelterId.setBounds(
                380, 150, 100, 25
        );

        contentPane.add(lblShelterId);


        txtShelterId =
                new JTextField();

        txtShelterId.setBounds(
                490, 150, 170, 25
        );

        contentPane.add(txtShelterId);


        // =========================
        // ADD BUTTON
        // =========================

        JButton btnAdd =
                new JButton("ADD");

        btnAdd.setBounds(
                680, 30, 100, 30
        );

        contentPane.add(btnAdd);

        btnAdd.addActionListener(
                e -> addPet()
        );


        // =========================
        // UPDATE BUTTON
        // =========================

        JButton btnUpdate =
                new JButton("UPDATE");

        btnUpdate.setBounds(
                680, 70, 100, 30
        );

        contentPane.add(btnUpdate);

        btnUpdate.addActionListener(
                e -> updatePet()
        );


        // =========================
        // DELETE BUTTON
        // =========================

        JButton btnDelete =
                new JButton("DELETE");

        btnDelete.setBounds(
                680, 110, 100, 30
        );

        contentPane.add(btnDelete);

        btnDelete.addActionListener(
                e -> deletePet()
        );


        // =========================
        // SHOW BUTTON
        // =========================

        JButton btnShow =
                new JButton("SHOW");

        btnShow.setBounds(
                680, 150, 100, 30
        );

        contentPane.add(btnShow);

        btnShow.addActionListener(
                e -> showPets()
        );


        // =========================
        // CLEAR BUTTON
        // =========================

        JButton btnClear =
                new JButton("CLEAR");

        btnClear.setBounds(
                570, 190, 100, 30
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
                30, 240, 750, 280
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

                            txtPetId.setText(
                                    table.getValueAt(row, 0).toString()
                            );

                            txtPetName.setText(
                                    table.getValueAt(row, 1).toString()
                            );

                            txtSpecies.setText(
                                    table.getValueAt(row, 2).toString()
                            );

                            txtBreed.setText(
                                    table.getValueAt(row, 3).toString()
                            );

                            txtAge.setText(
                                    table.getValueAt(row, 4).toString()
                            );

                            txtGender.setText(
                                    table.getValueAt(row, 5).toString()
                            );

                            txtStatus.setText(
                                    table.getValueAt(row, 6).toString()
                            );

                            txtShelterId.setText(
                                    table.getValueAt(row, 7).toString()
                            );

                        } catch (Exception ex) {

                            ex.printStackTrace();

                        }

                    }

                }
        );

    }


    // =====================================================
    // ADD PET
    // =====================================================

    private void addPet() {

        String sql =
                "INSERT INTO PET "
                + "(PET_NAME, SPECIES, BREED, AGE, GENDER, STATUS, SHELTER_ID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setString(
                    1,
                    txtPetName.getText().trim()
            );

            pst.setString(
                    2,
                    txtSpecies.getText().trim()
            );

            pst.setString(
                    3,
                    txtBreed.getText().trim()
            );

            pst.setInt(
                    4,
                    Integer.parseInt(
                            txtAge.getText().trim()
                    )
            );

            pst.setString(
                    5,
                    txtGender.getText().trim()
            );

            pst.setString(
                    6,
                    txtStatus.getText().trim()
            );

            pst.setInt(
                    7,
                    Integer.parseInt(
                            txtShelterId.getText().trim()
                    )
            );


            pst.executeUpdate();


            JOptionPane.showMessageDialog(
                    this,
                    "Pet added successfully!"
            );


            pst.close();
            con.close();


            clearFields();

            showPets();


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age and Shelter ID must be numbers."
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
    // SHOW PETS
    // =====================================================

    private void showPets() {

        String sql =
                "SELECT * FROM PET";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();


            // =========================
            // rs2xml
            // =========================

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
    // UPDATE PET
    // =====================================================

    private void updatePet() {

        String sql =
                "UPDATE PET SET "
                + "PET_NAME = ?, "
                + "SPECIES = ?, "
                + "BREED = ?, "
                + "AGE = ?, "
                + "GENDER = ?, "
                + "STATUS = ?, "
                + "SHELTER_ID = ? "
                + "WHERE PET_ID = ?";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setString(
                    1,
                    txtPetName.getText().trim()
            );

            pst.setString(
                    2,
                    txtSpecies.getText().trim()
            );

            pst.setString(
                    3,
                    txtBreed.getText().trim()
            );

            pst.setInt(
                    4,
                    Integer.parseInt(
                            txtAge.getText().trim()
                    )
            );

            pst.setString(
                    5,
                    txtGender.getText().trim()
            );

            pst.setString(
                    6,
                    txtStatus.getText().trim()
            );

            pst.setInt(
                    7,
                    Integer.parseInt(
                            txtShelterId.getText().trim()
                    )
            );

            pst.setInt(
                    8,
                    Integer.parseInt(
                            txtPetId.getText().trim()
                    )
            );


            int rows =
                    pst.executeUpdate();


            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet updated successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet ID not found."
                );

            }


            pst.close();

            con.close();


            clearFields();

            showPets();


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pet ID, Age and Shelter ID must be numbers."
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
    // DELETE PET
    // =====================================================

    private void deletePet() {

        String sql =
                "DELETE FROM PET "
                + "WHERE PET_ID = ?";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setInt(
                    1,
                    Integer.parseInt(
                            txtPetId.getText().trim()
                    )
            );


            int rows =
                    pst.executeUpdate();


            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet deleted successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet ID not found."
                );

            }


            pst.close();

            con.close();


            clearFields();

            showPets();


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Pet ID."
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
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        txtPetId.setText("");

        txtPetName.setText("");

        txtSpecies.setText("");

        txtBreed.setText("");

        txtAge.setText("");

        txtGender.setText("");

        txtStatus.setText("");

        txtShelterId.setText("");

        table.clearSelection();

    }

}