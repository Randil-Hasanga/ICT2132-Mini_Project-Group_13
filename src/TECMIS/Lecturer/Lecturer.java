package TECMIS.Lecturer;

import TECMIS.Lecturer.StudentDetails.StudentDetails;
import TECMIS.Lecturer.UploadCourseMaterials.UploadCourseMaterials;
import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Lecturer extends User {

    Connection conn = MySqlCon.MysqlMethod();
    private JButton profileButton;
    private JButton courceMaterialsButton;
    private JButton courseMaterialsButton;
    private JButton uploadMarksButton;
    private JButton studentDetailsButton;
    private JButton studentEligibilityButton;
    private JButton marksButton;
    private JButton gradeButton;
    private JButton GPAButton;
    private JButton medicalButton;
    private JButton attendanceButton;
    private JButton noticeButton;
    private JButton courseMaterialsButton1;
    private JPanel pnlLecturer;
    private JLabel lblWelcome;
    private JTextArea facultyOfTechnologyManagementTextArea;

    private String userId;
    private String acc;
    String Fname;
    String Lname;

    public void methodLecturer() {
        userId = getUserId();
        acc = getAcc();

        add(pnlLecturer);
        setSize(600, 600);
        setTitle("Lecturer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String sql = "SELECT FName,LName FROM " + acc + " WHERE User_id = ?";

        ResultSet rs;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Fname = rs.getString("FName");
                Lname = rs.getString("LName");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblWelcome.setText("Welcome Dr." + Fname + " " + Lname + "!");
        studentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentDetails sd = new StudentDetails();
                sd.viewStudentDetails();
                sd.setVisible(true);
                setVisible(false);

            }
        });
        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notice LecNotice = new Notice();
                Lecturer l2 = new Lecturer();
                LecNotice.setVisible(true);
                setVisible(false);
                LecNotice.viewNotice();
            }
        });
        uploadMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadMarks up = new UploadMarks();
                up.upMarks();
                up.setVisible(true);
                setVisible(false);
            }
        });
        courseMaterialsButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadCourseMaterials upCourse = new UploadCourseMaterials();
                upCourse.upCourseMaterials();
                upCourse.setVisible(true);
                setVisible(false);
            }
        });
    }
}
