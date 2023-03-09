package TECMIS.Lecturer;

import TECMIS.Lecturer.StudentDetails.StudentDetails;
import TECMIS.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Lecturer extends User {
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

    private String email;
    private String acc;
    String Fname;
    String Lname;

    public Lecturer(String email,String acc) {
        this.email = email;
        this.acc = acc;

        add(pnlLecturer);
        setSize(600, 600);
        setTitle("Lecturer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String sql = "SELECT FName,LName FROM " + acc + " WHERE Email = ?";

        ResultSet rs;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LMSDB", "root", "")) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                rs = pstmt.executeQuery();

                while(rs.next()){
                    Fname = rs.getString("FName");
                    Lname = rs.getString("LName");
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblWelcome.setText("Welcome Dr." + Fname +" "+ Lname +"!");
        studentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentDetails sd = new StudentDetails(email,acc);
                sd.setVisible(true);
                setVisible(false);

            }
        });
        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notice LecNotice = new Notice(email,acc);
                LecNotice.setVisible(true);
                setVisible(false);
            }
        });
    }

}
