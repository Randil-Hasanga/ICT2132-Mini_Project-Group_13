package com.TECMIS.TechnicalOfficer.TechnicalOfficer;

import com.TECMIS.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadMedical extends TechnicalOfficer {


    private JPanel pnlUploadMedical;
    private JTextArea informationSystemTextArea;
    private JLabel lblMedID;
    private JTextField txtMedID;
    private JLabel lblStartDate;
    private JDateChooser JDateChooserStartDate;
    private JLabel lblEndDate;
    private JDateChooser JDateChooserEndDate;
    private JLabel lblSID;
    private JTextField txtSID;
    private JLabel lblMedCon;
    private JTextField txtMedCon;
    private JLabel lblSuccess;
    private JButton btnBack;
    private JButton btnUpload;
    private JButton btnClr;


    private String userId;
    private String acc;
    private String MedicalID;
    private String Start_Date;
    private String End_Date;
    private String StudentID;
    private String MedCondition;






    public void uploadMedical() {

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUploadMedical);
        setSize(700, 600);
        setTitle("Upload Medicals");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMedID.setText("");
                JDateChooserStartDate.setDateFormatString("");
                JDateChooserEndDate.setDateFormatString("");
                txtSID.setText("");
                txtMedCon.setText("");
            }
        });


        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficer TOBack = new TechnicalOfficer();
                TOBack.setVisible(true);
                setVisible(false);
                TOBack.methodTechnicalOfficer();
            }
        });


        btnUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalID = txtMedID.getText();
                Start_Date = JDateChooserStartDate.getDateFormatString();
                End_Date = JDateChooserEndDate.getDateFormatString();
                StudentID = txtSID.getText();
                MedCondition = txtMedCon.getText();

                String uploadMed = "INSERT INTO Medical (Medical_id, Student_id,Start_Date,End_Date,Medical_Condition) VALUES (?,?,?,?,?)";

                try (PreparedStatement stmt = conn.prepareStatement(uploadMed)) {

                    stmt.setString(1, MedicalID);
                    stmt.setString(2, Start_Date);
                    stmt.setString(3, End_Date);
                    stmt.setString(4, StudentID);
                    stmt.setString(5, MedCondition);
                    stmt.setString(6, userId);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess.setText(" New Medical successfully Upload to database ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }
}
