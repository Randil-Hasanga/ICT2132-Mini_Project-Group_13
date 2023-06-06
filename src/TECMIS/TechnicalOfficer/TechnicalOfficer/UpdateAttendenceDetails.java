package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.MySqlCon;
import TECMIS.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateAttendenceDetails extends TechnicalOfficer{

    Connection conn = MySqlCon.MysqlMethod();


    private JPanel pnlUpdateAttendenceDetails;
    private JLabel lblAttendenceID;
    private JTextField textFieldAttendenceID;
    private JLabel lblStatus;
    private JTextField textFieldStatus;
    private JTextField textFieldCourseID;
    private JTextField textFieldDate;
    private Date selectedDate;
    private String formattedDate;
    private JTextField textFieldStudentID;
    private JLabel lblStudentID;
    private JButton BtnBack;
    private JButton BtnUpdate;
    private JButton BtnClear;
    private JLabel LblSuccess;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JButton chooseDateButton;
    private JPanel pnlAttendenceID;
    private JButton btnSubmit;
    private JPanel pnlDetails;
    private JLabel lblCourseIDDisplay;
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
        setTitle("Update AttendanceDetails");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pnlDetails.setVisible(false);
        pnlAttendenceID.setVisible(true);


        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendanceID = textFieldAttendenceID.getText();
                StudentID = textFieldStudentID.getText();
                pnlDetails.setVisible(true);

                String sql = " SELECT * FROM Attendance WHERE Attendance_id = ? AND Student_id = ? ";
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,AttendanceID);
                    pstmt.setString(2,StudentID);
                    ResultSet rs = pstmt.executeQuery();

                    while(rs.next())
                    {
                        CourseID = rs.getString("Course_id");
                        Date = rs.getString("Date_");
                        lblCourseIDDisplay.setText("Course ID :"+ CourseID+ " Date :" + Date );
                        Status = rs.getString("Status_");

                    }
                    textFieldStatus.setText(Status);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        BtnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textFieldStatus.setText("");

            }
        });


        BtnBack.addActionListener(new ActionListener() {
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
                    System.out.println(" Connection closed is Unsuccessfully "+ex.getMessage());
                }
            }
        });


        BtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Status = textFieldStatus.getText();

                if (Status.isEmpty()) {
                    LblSuccess.setText(" Please fill out the all fields !");

                } else {

                    String updAD = "UPDATE Attendance SET Status_ = ? WHERE Attendance_id = ? AND Student_id = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(updAD)) {

                        stmt.setString(1, Status);
                        stmt.setString(2, AttendanceID);
                        stmt.setString(3, StudentID);


                        int rowsInserted = stmt.executeUpdate();
                        System.out.println(rowsInserted + "Rows inserted");

                        LblSuccess.setText(" New Attendance Details successfully Updated to database ! ");

                    } catch (SQLException ex) {
                        System.out.println(" Update is Unsuccessful" + ex.getMessage());
                    }

                }
            }

        });


    }
}
