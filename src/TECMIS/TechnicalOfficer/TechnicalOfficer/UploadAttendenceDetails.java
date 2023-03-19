package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.MySqlCon;
import TECMIS.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UploadAttendenceDetails extends TechnicalOfficer {

    Connection conn = MySqlCon.MysqlMethod();



    private JTextField textFieldAttendenceID;
    private JLabel lblAdid;
    private JTextField textFieldStatus;
    private JLabel lblStatus;
    private JTextField TextFieldStatus;
    private JLabel lblCourseID;
    private JTextField textFieldCourseID;
    private JLabel lblDate;
    private JTextField textFieldDate;
    private JLabel lblSID;
    private JTextField textFieldSID;

    private JLabel lblSuccess2;
    private JButton btnBack;
    private JButton btnUpload;
    private JPanel pnlUploadAttendenceDetails;
    private JButton ButnClear;
    private JDateChooser JDateChooser1;


    private String userId;
    private String acc;
    private String AttendenceID;
    private String Status;
    private String Date;
    private String CourseID;
    private String StudentID;





    public void uploadAttendence() {
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUploadAttendenceDetails);
        setSize(700, 600);
        setTitle("Upload AttendenceDetails");
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        ButnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldAttendenceID.setText("");
                textFieldStatus.setText("");
                textFieldCourseID.setText("");
                JDateChooser1.setDateFormatString("");
                textFieldSID.setText("");

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
                AttendenceID = textFieldAttendenceID.getText();
                Status = textFieldStatus.getText();
                CourseID = textFieldCourseID.getText();
                Date = JDateChooser1.getDateFormatString() ;
                StudentID = textFieldSID.getText();



                String upAD = "INSERT INTO Attendence (Attendence_id, Student_id,Date_,Course_id,Status_) VALUES (?,?,?,?,?)";

                try(PreparedStatement stmt = conn.prepareStatement(upAD)){

                    stmt.setString(1,AttendenceID);
                    stmt.setString(2,Status);
                    stmt.setString(3,CourseID);
                    stmt.setString(4, Date);
                    stmt.setString(5,StudentID);
                    stmt.setString(6,userId);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess2.setText(" New Attendence Details successfully added to database ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


    }



}
