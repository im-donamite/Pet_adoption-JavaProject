package petadoption;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

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

public class AdoptionForm extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtAdoptionId;
    private JTextField txtPetId;
    private JTextField txtAdopterId;
    private JTextField txtAdoptionDate;
    private JTextField txtStatus;

    private JTable table;


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {

                AdoptionForm frame = new AdoptionForm();

                frame.setVisible(true);

            } catch (Exception e) {

                e.printStackTrace();

            }

        });

    }


    public AdoptionForm() {

        setTitle("Manage Adoptions");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setBounds(100, 100, 850, 550);


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
        // ADOPTION ID
        // =========================

        JLabel lblAdoptionId =
                new JLabel("Adoption ID");

        lblAdoptionId.setBounds(
                30, 30, 110, 25
        );

        contentPane.add(lblAdoptionId);


        txtAdoptionId =
                new JTextField();

        txtAdoptionId.setBounds(
                150, 30, 180, 25
        );

        contentPane.add(txtAdoptionId);


        // =========================
        // PET ID
        // =========================

        JLabel lblPetId =
                new JLabel("Pet ID");

        lblPetId.setBounds(
                30, 70, 110, 25
        );

        contentPane.add(lblPetId);


        txtPetId =
                new JTextField();

        txtPetId.setBounds(
                150, 70, 180, 25
        );

        contentPane.add(txtPetId);


        // =========================
        // ADOPTER ID
        // =========================

        JLabel lblAdopterId =
                new JLabel("Adopter ID");

        lblAdopterId.setBounds(
                30, 110, 110, 25
        );

        contentPane.add(lblAdopterId);


        txtAdopterId =
                new JTextField();

        txtAdopterId.setBounds(
                150, 110, 180, 25
        );

        contentPane.add(txtAdopterId);


        // =========================
        // ADOPTION DATE
        // =========================

        JLabel lblAdoptionDate =
                new JLabel("Adoption Date");

        lblAdoptionDate.setBounds(
                400, 30, 110, 25
        );

        contentPane.add(lblAdoptionDate);


        txtAdoptionDate =
                new JTextField();

        txtAdoptionDate.setBounds(
                520, 30, 180, 25
        );

        contentPane.add(txtAdoptionDate);


        // =========================
        // STATUS
        // =========================

        JLabel lblStatus =
                new JLabel("Status");

        lblStatus.setBounds(
                400, 70, 110, 25
        );

        contentPane.add(lblStatus);


        txtStatus =
                new JTextField();

        txtStatus.setBounds(
                520, 70, 180, 25
        );

        contentPane.add(txtStatus);


        // =========================
        // ADD
        // =========================

        JButton btnAdd =
                new JButton("ADD");

        btnAdd.setBounds(
                400, 120, 100, 35
        );

        contentPane.add(btnAdd);

        btnAdd.addActionListener(
                e -> addAdoption()
        );


        // =========================
        // UPDATE
        // =========================

        JButton btnUpdate =
                new JButton("UPDATE");

        btnUpdate.setBounds(
                510, 120, 100, 35
        );

        contentPane.add(btnUpdate);

        btnUpdate.addActionListener(
                e -> updateAdoption()
        );


        // =========================
        // DELETE
        // =========================

        JButton btnDelete =
                new JButton("DELETE");

        btnDelete.setBounds(
                620, 120, 100, 35
        );

        contentPane.add(btnDelete);

        btnDelete.addActionListener(
                e -> deleteAdoption()
        );


        // =========================
        // SHOW
        // =========================

        JButton btnShow =
                new JButton("SHOW");

        btnShow.setBounds(
                400, 165, 100, 35
        );

        contentPane.add(btnShow);

        btnShow.addActionListener(
                e -> showAdoptions()
        );


        // =========================
        // CLEAR
        // =========================

        JButton btnClear =
                new JButton("CLEAR");

        btnClear.setBounds(
                510, 165, 100, 35
        );

        contentPane.add(btnClear);

        btnClear.addActionListener(
                e -> clearFields()
        );


        // =========================
        // DATE FORMAT LABEL
        // =========================

        JLabel lblDateFormat =
                new JLabel("Format: YYYY-MM-DD");

        lblDateFormat.setBounds(
                520, 95, 180, 20
        );

        contentPane.add(lblDateFormat);


        // =========================
        // SCROLL PANE
        // =========================

        JScrollPane scrollPane =
                new JScrollPane();

        scrollPane.setBounds(
                30, 230, 690, 240
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

                            txtAdoptionId.setText(
                                    table.getValueAt(row, 0).toString()
                            );

                            txtPetId.setText(
                                    table.getValueAt(row, 1).toString()
                            );

                            txtAdopterId.setText(
                                    table.getValueAt(row, 2).toString()
                            );

                            txtAdoptionDate.setText(
                                    table.getValueAt(row, 3).toString()
                            );

                            txtStatus.setText(
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
    // ADD ADOPTION
    // =====================================================

    private void addAdoption() {

        String sql =
                "INSERT INTO ADOPTION "
                + "(PET_ID, ADOPTER_ID, ADOPTION_DATE, STATUS) "
                + "VALUES (?, ?, ?, ?)";


        try {

            Connection con =
                    DBConnection.getConnection();


            // Check PET
            if (!petExists(
                    con,
                    Integer.parseInt(txtPetId.getText().trim())
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet ID does not exist."
                );

                con.close();

                return;
            }


            // Check ADOPTER
            if (!adopterExists(
                    con,
                    Integer.parseInt(txtAdopterId.getText().trim())
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Adopter ID does not exist."
                );

                con.close();

                return;
            }


            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setInt(
                    1,
                    Integer.parseInt(
                            txtPetId.getText().trim()
                    )
            );

            pst.setInt(
                    2,
                    Integer.parseInt(
                            txtAdopterId.getText().trim()
                    )
            );

            pst.setDate(
                    3,
                    Date.valueOf(
                            txtAdoptionDate.getText().trim()
                    )
            );

            pst.setString(
                    4,
                    txtStatus.getText().trim()
            );


            pst.executeUpdate();


            JOptionPane.showMessageDialog(
                    this,
                    "Adoption added successfully!"
            );


            pst.close();

            con.close();


            clearFields();

            showAdoptions();


        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please check your IDs and date.\n"
                    + "Date must be YYYY-MM-DD."
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
    // SHOW ADOPTIONS
    // =====================================================

    private void showAdoptions() {

        String sql =
                "SELECT * FROM ADOPTION";


        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();


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
    // UPDATE ADOPTION
    // =====================================================

    private void updateAdoption() {

        String sql =
                "UPDATE ADOPTION SET "
                + "PET_ID = ?, "
                + "ADOPTER_ID = ?, "
                + "ADOPTION_DATE = ?, "
                + "STATUS = ? "
                + "WHERE ADOPTION_ID = ?";


        try {

            Connection con =
                    DBConnection.getConnection();


            int petId =
                    Integer.parseInt(
                            txtPetId.getText().trim()
                    );

            int adopterId =
                    Integer.parseInt(
                            txtAdopterId.getText().trim()
                    );

            int adoptionId =
                    Integer.parseInt(
                            txtAdoptionId.getText().trim()
                    );


            if (!petExists(con, petId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pet ID does not exist."
                );

                con.close();

                return;
            }


            if (!adopterExists(con, adopterId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Adopter ID does not exist."
                );

                con.close();

                return;
            }


            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setInt(1, petId);

            pst.setInt(2, adopterId);

            pst.setDate(
                    3,
                    Date.valueOf(
                            txtAdoptionDate.getText().trim()
                    )
            );

            pst.setString(
                    4,
                    txtStatus.getText().trim()
            );

            pst.setInt(
                    5,
                    adoptionId
            );


            int rows =
                    pst.executeUpdate();


            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Adoption updated successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Adoption ID not found."
                );

            }


            pst.close();

            con.close();


            clearFields();

            showAdoptions();


        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please check your IDs and date.\n"
                    + "Date must be YYYY-MM-DD."
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
    // DELETE ADOPTION
    // =====================================================

    private void deleteAdoption() {

        String sql =
                "DELETE FROM ADOPTION "
                + "WHERE ADOPTION_ID = ?";


        try {

            Connection con =
                    DBConnection.getConnection();


            int adoptionId =
                    Integer.parseInt(
                            txtAdoptionId.getText().trim()
                    );


            PreparedStatement pst =
                    con.prepareStatement(sql);


            pst.setInt(
                    1,
                    adoptionId
            );


            int rows =
                    pst.executeUpdate();


            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Adoption deleted successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Adoption ID not found."
                );

            }


            pst.close();

            con.close();


            clearFields();

            showAdoptions();


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Adoption ID."
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
    // CHECK PET EXISTS
    // =====================================================

    private boolean petExists(
            Connection con,
            int petId
    ) throws Exception {

        String sql =
                "SELECT PET_ID FROM PET "
                + "WHERE PET_ID = ?";


        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setInt(1, petId);

        ResultSet rs =
                pst.executeQuery();


        boolean exists =
                rs.next();


        rs.close();

        pst.close();


        return exists;

    }


    // =====================================================
    // CHECK ADOPTER EXISTS
    // =====================================================

    private boolean adopterExists(
            Connection con,
            int adopterId
    ) throws Exception {

        String sql =
                "SELECT ADOPTER_ID FROM ADOPTER "
                + "WHERE ADOPTER_ID = ?";


        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setInt(1, adopterId);

        ResultSet rs =
                pst.executeQuery();


        boolean exists =
                rs.next();


        rs.close();

        pst.close();


        return exists;

    }


    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        txtAdoptionId.setText("");

        txtPetId.setText("");

        txtAdopterId.setText("");

        txtAdoptionDate.setText("");

        txtStatus.setText("");

        table.clearSelection();

    }

}