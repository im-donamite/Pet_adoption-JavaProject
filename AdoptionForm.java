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

public class AdoptionForm extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtAdoptionId;
    private JTextField txtPetId;
    private JTextField txtAdopterId;
    private JTextField txtDate;
    private JTextField txtStatus;

    private JTable table;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            AdoptionForm frame =
                    new AdoptionForm();

            frame.setVisible(true);
        });
    }

    public AdoptionForm() {

        setTitle("Manage Adoptions");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setBounds(100, 100, 800, 550);

        contentPane = new JPanel();

        contentPane.setBorder(
                new EmptyBorder(5, 5, 5, 5)
        );

        setContentPane(contentPane);

        contentPane.setLayout(null);

        JLabel lblId =
                new JLabel("Adoption ID");

        lblId.setBounds(30, 30, 100, 25);
        contentPane.add(lblId);

        txtAdoptionId =
                new JTextField();

        txtAdoptionId.setBounds(
                140, 30, 180, 25
        );

        contentPane.add(txtAdoptionId);

        JLabel lblPet =
                new JLabel("Pet ID");

        lblPet.setBounds(30, 70, 100, 25);
        contentPane.add(lblPet);

        txtPetId =
                new JTextField();

        txtPetId.setBounds(
                140, 70, 180, 25
        );

        contentPane.add(txtPetId);

        JLabel lblAdopter =
                new JLabel("Adopter ID");

        lblAdopter.setBounds(30, 110, 100, 25);
        contentPane.add(lblAdopter);

        txtAdopterId =
                new JTextField();

        txtAdopterId.setBounds(
                140, 110, 180, 25
        );

        contentPane.add(txtAdopterId);

        JLabel lblDate =
                new JLabel("Adoption Date");

        lblDate.setBounds(30, 150, 100, 25);
        contentPane.add(lblDate);

        txtDate =
                new JTextField();

        txtDate.setBounds(
                140, 150, 180, 25
        );

        contentPane.add(txtDate);

        JLabel lblStatus =
                new JLabel("Status");

        lblStatus.setBounds(30, 190, 100, 25);
        contentPane.add(lblStatus);

        txtStatus =
                new JTextField();

        txtStatus.setBounds(
                140, 190, 180, 25
        );

        contentPane.add(txtStatus);

        JButton btnAdd =
                new JButton("ADD");

        btnAdd.setBounds(
                370, 30, 100, 30
        );

        contentPane.add(btnAdd);

        btnAdd.addActionListener(
                e -> addAdoption()
        );

        JButton btnUpdate =
                new JButton("UPDATE");

        btnUpdate.setBounds(
                480, 30, 100, 30
        );

        contentPane.add(btnUpdate);

        btnUpdate.addActionListener(
                e -> updateAdoption()
        );

        JButton btnDelete =
                new JButton("DELETE");

        btnDelete.setBounds(
                590, 30, 100, 30
        );

        contentPane.add(btnDelete);

        btnDelete.addActionListener(
                e -> deleteAdoption()
        );

        JButton btnShow =
                new JButton("SHOW");

        btnShow.setBounds(
                370, 70, 100, 30
        );

        contentPane.add(btnShow);

        btnShow.addActionListener(
                e -> showAdoptions()
        );

        JButton btnClear =
                new JButton("CLEAR");

        btnClear.setBounds(
                480, 70, 100, 30
        );

        contentPane.add(btnClear);

        btnClear.addActionListener(
                e -> clearFields()
        );

        JScrollPane scrollPane =
                new JScrollPane();

        scrollPane.setBounds(
                30, 250, 720, 220
        );

        contentPane.add(scrollPane);

        table = new JTable();

        scrollPane.setViewportView(table);
    }

    private void addAdoption() {

        String sql =
                "INSERT INTO adoption " +
                "(pet_id, adopter_id, adoption_date, status) " +
                "VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(
                    1,
                    Integer.parseInt(
                            txtPetId.getText()
                    )
            );

            pst.setInt(
                    2,
                    Integer.parseInt(
                            txtAdopterId.getText()
                    )
            );

            pst.setString(
                    3,
                    txtDate.getText()
            );

            pst.setString(
                    4,
                    txtStatus.getText()
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

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void showAdoptions() {

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(
                            "SELECT * FROM adoption"
                    );

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
        }
    }

    private void updateAdoption() {

        String sql =
                "UPDATE adoption SET " +
                "pet_id=?, adopter_id=?, " +
                "adoption_date=TO_DATE(?, 'YYYY-MM-DD'), " +
                "status=? " +
                "WHERE adoption_id=?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(
                    1,
                    Integer.parseInt(
                            txtPetId.getText()
                    )
            );

            pst.setInt(
                    2,
                    Integer.parseInt(
                            txtAdopterId.getText()
                    )
            );

            pst.setString(
                    3,
                    txtDate.getText()
            );

            pst.setString(
                    4,
                    txtStatus.getText()
            );

            pst.setInt(
                    5,
                    Integer.parseInt(
                            txtAdoptionId.getText()
                    )
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

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void deleteAdoption() {

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(
                            "DELETE FROM adoption " +
                            "WHERE adoption_id=?"
                    );

            pst.setInt(
                    1,
                    Integer.parseInt(
                            txtAdoptionId.getText()
                    )
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

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void clearFields() {

        txtAdoptionId.setText("");
        txtPetId.setText("");
        txtAdopterId.setText("");
        txtDate.setText("");
        txtStatus.setText("");
    }
}