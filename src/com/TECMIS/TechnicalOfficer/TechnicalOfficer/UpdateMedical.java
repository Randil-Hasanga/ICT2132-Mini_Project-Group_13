package com.TECMIS.TechnicalOfficer.TechnicalOfficer;

import com.TECMIS.MySqlCon;
import com.TECMIS.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMedical extends TechnicalOfficer {

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlUpdateMedical;
    private JLabel lblUpdateMedicalID;
    private JTextField textFieldupdMedicalID;
    private JLabel lblupdStartDate;

    private JLabel lblupdEndDate;

    private JLabel lblupdStudentID;
    private JTextField textFieldupdStudentID;
    private JLabel lblupdMedCondition;
    private JTextField textFieldupdMedCondition;
    private JLabel lblupdSuccess;


    private JButton btnBack;
    private JButton btnUpdate;
    private JButton btnClr;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JDateChooser JDateChooser1;
    private JDateChooser JDateChooser2;


    private String userId;
    private String acc;
    private String MedicalID;
    private String Start_Date;
    private String End_Date;
    private String StudentID;
    private String MedCondition;


    public void UpdateMedical() {

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUpdateMedical);
        setSize(700, 600);
        setTitle("Update Medicals");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        btnClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldupdMedicalID.setText("");
                JDateChooser1.setDateFormatString("");
                JDateChooser2.setDateFormatString("");
                textFieldupdStudentID.setText("");
                textFieldupdMedCondition.setText("");
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


        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalID = textFieldupdMedicalID.getText();
                Start_Date = JDateChooser1.getDateFormatString();
                End_Date = JDateChooser2.getDateFormatString();
                StudentID = textFieldupdStudentID.getText();
                MedCondition = textFieldupdMedCondition.getText();

                String updMed = "UPDATE Medical SET  Student_id = ?,Start_Date = ?,End_Date = ? ,Medical_Condition = ?) WHERE Medical_id = ? ";

                try (PreparedStatement stmt = conn.prepareStatement(updMed)) {

                    stmt.setString(1, MedicalID);
                    stmt.setString(2, Start_Date);
                    stmt.setString(3, End_Date);
                    stmt.setString(4, StudentID);
                    stmt.setString(5, MedCondition);
                    stmt.setString(6, userId);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblupdSuccess.setText(" New Medical successfully Updated to database ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


        });


    }

}
