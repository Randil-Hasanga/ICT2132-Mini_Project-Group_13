package Com.TECMIS.TechnicalOfficer.TechnicalOfficer;

import Com.TECMIS.MySqlCon;
import Com.TECMIS.User;
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
    private JLabel lblCourseID;
    private JLabel lblDate;
    private JLabel lblStudentID;
    private JButton BtnBack;
    private JButton BtnUpdate;
    private JButton BtnClear;
    private JLabel LblSuccess;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JButton chooseDateButton;
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
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            formattedDate = sdf.format(selectedDate);
                            System.out.println(selectedDate);
                            System.out.println(formattedDate);
                            frame.dispose(); // Close the frame after selecting the date
                        }
                    }
                });
            }
        });

        BtnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldAttendenceID.setText("");
                textFieldStatus.setText("");
                textFieldCourseID.setText("");
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
                StudentID =textFieldStudentID.getText();

                String updAD = "UPDATE Attendance SET  Student_id = ?,Date_ = ?,Course_id = ? ,Status_ = ? WHERE Attendance_id = ? ";

                try(PreparedStatement stmt = conn.prepareStatement(updAD)){

                    stmt.setString(1,AttendanceID);
                    stmt.setString(5,Status);
                    stmt.setString(4,CourseID);
                    stmt.setString(3,formattedDate);
                    stmt.setString(2,StudentID);


                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    LblSuccess.setText(" New Attendance Details successfully Updated to database ! ");

                } catch (SQLException ex) {
                    System.out.println(" Update is Unsuccessful"+ex.getMessage());
                }
                finally {
                    try {
                        conn.close();
                        System.out.println(" Connection is Closed ");
                    } catch (SQLException ex) {
                        System.out.println(" Connection Closed is Unsuccessful "+ex.getMessage());
                    }
                }
            }


        });


    }
}
