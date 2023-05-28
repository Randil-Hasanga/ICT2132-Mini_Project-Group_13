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

public class UpdateAttendenceDetails extends TechnicalOfficer{

    Connection conn = MySqlCon.MysqlMethod();


    private JPanel pnlUpdateAttendenceDetails;
    private JLabel lblAttendenceID;
    private JTextField textFieldAttendenceID;
    private JLabel lblStatus;
    private JTextField textFieldStatus;
    private JTextField textFieldCourseID;
    private JTextField textFieldDate;
    private JTextField textFieldStudentID;
    private JLabel lblCourseID;
    private JLabel lblDate;
    private JLabel lblStudentID;
    private JButton BtnBack;
    private JButton BtnUpdate;
    private JButton BtnClear;
    private JLabel LblSuccess;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JDateChooser JDateChooser2;


    private String userId;
    private String acc;
    private String AttendanceID;
    private String Status;
    private String Date;
    private String CourseID;
    private String StudentID;


    public void UpdateAttendence() {
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUpdateAttendenceDetails);
        setSize(700, 600);
        setTitle("Update AttendenceDetails");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        BtnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldAttendenceID.setText("");
                textFieldStatus.setText("");
                textFieldCourseID.setText("");
                JDateChooser2.setDateFormatString("");
                textFieldStudentID.setText("");
            }
        });


        BtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficer TOBack = new TechnicalOfficer();
                TOBack.setVisible(true);
                setVisible(false);
                TOBack.methodTechnicalOfficer();
            }
        });


        BtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             AttendanceID = textFieldAttendenceID.getText();
                Status = textFieldStatus.getText();
                CourseID = textFieldCourseID.getText();
                Date = JDateChooser2.getDateFormatString();
                StudentID =textFieldStudentID.getText();

                String updAD = "UPDATE Attendence SET  Student_id = ?,Date = ?,Course_id = ? ,Status_ = ?) WHERE Attendence_id = ? ";

                try(PreparedStatement stmt = conn.prepareStatement(updAD)){

                    stmt.setString(1,AttendanceID);
                    stmt.setString(2,Status);
                    stmt.setString(3,CourseID);
                    stmt.setString(4,Date);
                    stmt.setString(5,StudentID);
                    stmt.setString(6,userId);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    LblSuccess.setText(" New Attendence Details successfully Updated to database ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


        });


    }
}
