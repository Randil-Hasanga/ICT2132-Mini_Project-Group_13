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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JButton chooseDateButton;


    private String userId;
    private String acc;
    private String AttendenceID;
    private String Status;
    private String Date;
    private String CourseID;
    private String StudentID;
    private Date selectedDate;
    private String formattedDate;


    public void uploadAttendance() {
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUploadAttendenceDetails);
        setSize(700, 600);
        setTitle("Upload AttendanceDetails");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        chooseDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Choose Date");
                JDateChooser dateChooser = new JDateChooser();
                frame.add(dateChooser);
                frame.setType(Window.Type.UTILITY);
                frame.pack();
                frame.setLocationRelativeTo(null); // Center the frame on the screen
                frame.setVisible(true);

                dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals("date")) {
                            selectedDate = dateChooser.getDate(); // get selected date

                            // Format the selected date as YYYY-MM-DD
                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                            formattedDate = sdf1.format(selectedDate);
                            System.out.println(selectedDate);
                            System.out.println(formattedDate);
                            frame.dispose(); // Close the frame after selecting the date
                        }
                    }
                });
            }
        });

        ButnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldAttendenceID.setText("");
                textFieldStatus.setText("");
                textFieldCourseID.setText("");
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
                StudentID = textFieldSID.getText();



                String upAD = "INSERT INTO Attendance (Attendance_id, Student_id,Date_,Course_id,Status_) VALUES (?,?,?,?,?)";

                try(PreparedStatement stmt = conn.prepareStatement(upAD)){

                    stmt.setString(1,AttendenceID);
                    stmt.setString(2,StudentID);
                    stmt.setString(3,formattedDate);
                    stmt.setString(4,CourseID);
                    stmt.setString(5,Status);





                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess2.setText(" New Attendance Details successfully added to database ! ");

                } catch (SQLException ex) {
                    System.out.println("Error in sql" + ex.getMessage());
                }

            }
        });


    }



}
