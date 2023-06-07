package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveAttendenceDetails extends TechnicalOfficer {

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlRemoveAttendenceDetails;
    private JLabel lblrmvAttendenceID;
    private JTextField textFieldrmvAttendenceID;
    private JLabel lblRmvSuccess;


    private JButton btnBack;
    private JButton btnRemove;
    private JButton btnClr;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JLabel lblStuID;
    private JTextField textField1StuID;


    private String userId;
    private String acc;
    private String AttendenceID;
    private String StuID;


    public void RemoveAttendence() {
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlRemoveAttendenceDetails);
        setSize(700, 600);
        setTitle(" Remove AttendanceDetails");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        btnClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldrmvAttendenceID.setText("");
                textField1StuID.setText("");

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
                    System.out.println(" Connection is closed");
                } catch (SQLException ex) {
                    System.out.println(" Connection close is Unsuccessfully "+ex.getMessage());
                }
            }
        });


        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendenceID = textFieldrmvAttendenceID.getText();
                StuID = textField1StuID.getText();

                if ((AttendenceID.isEmpty() )||(StuID.isEmpty())) {
                    lblRmvSuccess.setText(" Please Fill the field ");
                } else {


                    String rmvADid = " DELETE FROM attendance WHERE Attendance_id = ? AND Student_id = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(rmvADid)) {

                        stmt.setString(1, AttendenceID);
                        stmt.setString(2,StuID);
                        int rows = stmt.executeUpdate();

                        lblRmvSuccess.setVisible(true);
                        lblRmvSuccess.setText(rows + "  Attendance Successfully Removed. ");

                    } catch (SQLException ex) {
                        System.out.println(" Attendance Unsuccessfully Removed!!! " + ex.getMessage());
                    }

                }
            }
        });

    }
}




