package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveMedical extends TechnicalOfficer{

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlRemoveMedical;
    private JLabel lblMedicalID;
    private JTextField textFieldMedicalID;
    private JLabel lblRmvMedSuccess;


    private JButton btnBack;
    private JButton btnRemove;
    private JButton btnClear;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField textFieldStuID;


    private String userId;
    private String acc;
    private String MedicalID;
    private String StudentID;





    public void RemoveMedical() {

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlRemoveMedical);
        setSize(700, 600);
        setTitle(" Remove Medicals");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textFieldMedicalID.setText("");
                textFieldStuID.setText(" ");
            }
        });


        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficer TOBack = new TechnicalOfficer();
                TOBack.setVisible(true);
                setVisible(false);
                TOBack.methodTechnicalOfficer();

                try {
                    conn.close();
                    System.out.println(" Connection is Closed ");

                } catch (SQLException ex) {
                    System.out.println(" Connection close is Unsuccessfully "+ex.getMessage());
                }
            }
        });


        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalID = textFieldMedicalID.getText();

                if ((MedicalID.isEmpty())) {

                    lblRmvMedSuccess.setText(" Please fill out the field ");

                } else {

                    String rmvMedID = " DELETE FROM Medical  WHERE Medical_id = ? ";

                    try (PreparedStatement pstmt = conn.prepareStatement(rmvMedID)) {

                        pstmt.setString(1, MedicalID);


                        int rows = pstmt.executeUpdate();

                        lblRmvMedSuccess.setVisible(true);
                        lblRmvMedSuccess.setText(rows + "  Medical Successfully Removed. ");

                    } catch (SQLException ex) {
                        System.out.println(" Medical Remove is Unsuccessful " + ex.getMessage());
                    }


                }
            }
        });
    }


    }

