package TECMIS.Student;

import TECMIS.Common_classes.ViewGradeGPA;
import TECMIS.Common_classes.Medical.Medical;
import TECMIS.MySqlCon;
import TECMIS.User;
import TECMIS.Common_classes.viewAttendance.ViewStudentAttendance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends User {

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlStudent;
    private JLabel lblWelcome;
    private JButton noticeButton;
    private JButton courseDetailsButton;
    private JButton attendanceButton;
    private JButton medicalButton;
    private JButton gradeAndGPAButton;
    private JButton timeTableButton;
    private JButton contactDetailsButton;
    private JButton profilePictureButton;
    private JPanel pnlPic;
    private JButton logOutButton;
    private JTextArea facultyOfTechnologyManagementTextArea;

    private String userId;

    private String acc;

    String Fname;
    String Lname;


    private byte[] dImg;

    private JLabel lblPic;


    public void methodStudent() {
        userId = getUserId();
        acc = getAcc();

        add(pnlStudent);
        setSize(750, 500);
        pnlPic.setSize(200, 200);
        setTitle("Student");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String sql = "SELECT FName,LName,Pro_pic FROM " + acc + " WHERE User_id = ?";
        String dfIcon = "SELECT img FROM DefaulImg WHERE imgId = 0";
        ResultSet rs;
        ResultSet rs2;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            PreparedStatement pstmt2 = conn.prepareStatement(dfIcon);
            rs2 = pstmt2.executeQuery();
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs2.next()) {
                dImg = rs2.getBytes("img");
            }

            while (rs.next()) {
                Fname = rs.getString("FName");
                Lname = rs.getString("LName");
                byte[] imageData = rs.getBytes("Pro_pic");

                if (imageData == null) {
                    ImageIcon defaultIcon = new ImageIcon(dImg);
                    Image image = defaultIcon.getImage();
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = bufferedImage.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setClip(new Ellipse2D.Float(0, 0, image.getWidth(null), image.getHeight(null)));
                    g2.drawImage(image, 0, 0, null);
                    lblPic.setIcon(new ImageIcon(bufferedImage));
                } else {
                    ImageIcon icon = new ImageIcon(imageData);
                    Image image = icon.getImage();
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = bufferedImage.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setClip(new Ellipse2D.Float(0, 0, image.getWidth(null), image.getHeight(null)));
                    g2.drawImage(image, 0, 0, null);
                    lblPic.setIcon(new ImageIcon(bufferedImage));
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblWelcome.setText("Welcome " + Fname + " " + Lname + "!");


        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentNotice StuNotice = new StudentNotice();
                Student S1 = new Student();
                StuNotice.setVisible(true);
                setVisible(false);
                StuNotice.viewStudentNotice();
            }

        });


            courseDetailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CourseDetails cd = new CourseDetails();
                    Student S1 = new Student();
                    cd.setVisible(true);
                    setVisible(false);
                    cd.viewCourseDetails();
                }
            });

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewStudentAttendance vAttendance = new ViewStudentAttendance();
                vAttendance.viewAttendance();
                vAttendance.setVisible(true);
                setVisible(false);

            }
        });

        medicalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentMedical stMed = new StudentMedical();
                stMed.StudentMedicals();
                stMed.setVisible(true);
                setVisible(false);

            }
        });

        gradeAndGPAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewGradeGPA gp = new ViewGradeGPA();
                gp.setVisible(true);
                setVisible(false);
                gp.viewGrades();

            }
        });

        timeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeTable timeTable = new TimeTable();
                timeTable.setVisible(true);
                setVisible(false);
                timeTable.viewTimeTable();

            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u1 = new User();
                u1.setVisible(true);
                setVisible(false);
                u1.Login();

            }
        });

        contactDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactDetails contactDetails= new ContactDetails();
                contactDetails.ContactDetails();
                contactDetails.setVisible(true);
                setVisible(false);

            }
        });

        profilePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfilePicture profilePicture;
                profilePicture = new ProfilePicture();
                profilePicture.upProfilePicture();
                profilePicture.setVisible(true);
                setVisible(false);

            }
        });



    }
}
