package Com.TECMIS.TechnicalOfficer.TechnicalOfficer;

import Com.TECMIS.MySqlCon;
import Com.TECMIS.User;

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
    private JLabel lblError;


    private String userId;
    private String acc;
    private String AttendenceID;


    public void RemoveAttendence() {
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlRemoveAttendenceDetails);
        setSize(700, 600);
        setTitle(" Remove AttendanceDetails");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        btnClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldrmvAttendenceID.setText("");

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
                    System.out.println(" Connection is closed ");
                } catch (SQLException ex) {
                    System.out.println(" Connection Closed is Unsuccessful! "+ex.getMessage());
                }
            }
        });


        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendenceID = textFieldrmvAttendenceID.getText();

                if(AttendenceID.isEmpty())
                {
                    lblError.setVisible(true);
                    lblError.setText(" Please Fill out the Field!");
                }

                String rmvADid = " DELETE FROM Attendance  WHERE Attendance_id = ? ";

                try (PreparedStatement stmt = conn.prepareStatement(rmvADid)) {

                    stmt.setString(1, AttendenceID);
                    int rows = stmt.executeUpdate();

                    lblRmvSuccess.setVisible(true);
                    lblRmvSuccess.setText(rows + "  Attendance Successfully Removed. ");

                } catch (SQLException ex) {
                    System.out.println(" Attendance Unsuccessfully Removed!!! "+ex.getMessage());
                }

            }

        });

    }
}




