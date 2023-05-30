package TECMIS.Admin.DashBord.Course;

import TECMIS.Admin.DashBord.Dashbord;
import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveCourse extends JFrame{
    private JPanel RemCouPnl;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField CourId;
    private JButton backButton;
    private JButton clearButton;
    private JButton submitButton;
    private JLabel scc;
    private String coId;




    public void RemoveCourseDetails(){

        Connection conn = MySqlCon.MysqlMethod();

        add(RemCouPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);





        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coId = CourId.getText();

                try {

                    // Delete the course from the exam_marks table
                    String deleteMarksSQL = "DELETE FROM Exam_mark WHERE Course_id = ?";
                    PreparedStatement deleteMarksStmt = conn.prepareStatement(deleteMarksSQL);
                    deleteMarksStmt.setString(1, coId);
                    int rows1 = deleteMarksStmt.executeUpdate();

                    // Delete attendance records for the specified Course_id
                    String deleteAttendanceSQL = "DELETE FROM attendance WHERE Course_id = ?";
                    PreparedStatement deleteAttendanceStmt = conn.prepareStatement(deleteAttendanceSQL);
                    deleteAttendanceStmt.setString(1, coId);
                    int rows2 = deleteAttendanceStmt.executeUpdate();

                    // Delete the course from the course_detail table
                    String deleteCourseSQL = "DELETE FROM course_detail WHERE Course_id = ?";
                    PreparedStatement deleteCourseStmt = conn.prepareStatement(deleteCourseSQL);
                    deleteCourseStmt.setString(1, coId);
                    int rows = deleteCourseStmt.executeUpdate();

                    scc.setText("Successful Remove Course !");
                } catch (SQLException ex) {
                    System.out.println("Error in sql" + ex.getMessage());
                }
            }
        });






        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashbord courBank = new Dashbord();
                courBank.methodAdmin();
                courBank.setVisible(true);
                setVisible(false);



            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourId.setText("");

            }
        });

    }
}
