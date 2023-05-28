package com.TECMIS.Student;

import javax.swing.*;


import com.TECMIS.MySqlCon;
import com.TECMIS.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactDetails extends Student{

    Connection conn = MySqlCon.MysqlMethod();

    private JPanel pnlUpdateCD;
    private JTextField textSID;
    private JTextField textAl1;
    private JTextField textAl2;
    private JTextField textEmail;
    private JButton backButton;
    private JButton updateButton;
    private JButton clearButton;
    private JLabel lblSID;
    private JLabel lblAL1;
    private JLabel lblAL2;
    private JLabel lblEmail;
    private JLabel lblSuccess;
    private JTextArea facultyOfTechnologyManagementTextArea;

    private String userId;
    private String acc;

    String SID;

    String Al1;

    String Al2;

    String Email;

    public void upCourseDetails() {

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUpdateCD);
        setSize(600, 600);
        setTitle("Update Contact Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        lblAL1.setVisible(false);
        lblAL2.setVisible(false);
        lblEmail.setVisible(false);
        textAl1.setVisible(false);
        textAl2.setVisible(false);
        textEmail.setVisible(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student stBack = new Student();
                stBack.setVisible(true);
                setVisible(false);
                stBack.methodStudent();

            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textSID.setText("");
                textAl1.setText("");
                textAl2.setText("");
                textEmail.setText("");

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SID = textSID.getText();
                Al1 = textAl1.getText();
                Al2 = textAl2.getText();
                Email = textEmail.getText();

                String updateCD = "INSERT INTO Student (Address_L1, Address_L2, Email) VALUES (?,?,?)";

                try(PreparedStatement stmt = conn.prepareStatement(updateCD)){

                    stmt.setString(1,SID);
                    stmt.setString(2,Al1);
                    stmt.setString(3,Al2);


                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess.setText(" New Contact Details Successfully Added To Database ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });





    }
}
