package TECMIS.Lecturer;

import TECMIS.GradeAndGPA.ViewGradeGPA;
import TECMIS.Medical.Medical;
import TECMIS.Lecturer.StudentDetails.StudentDetails;
import TECMIS.Lecturer.UploadCourseMaterials.UploadCourseMaterials;
import TECMIS.MySqlCon;
import TECMIS.Notice;
import TECMIS.User;
import TECMIS.viewAttendance.ViewStudentAttendance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
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
    private JButton gradesAndGPAButton;
    private JButton medicalButton;
    private JButton attendanceButton;
    private JButton noticeButton;
    private JButton courseMaterialsButton1;
    private JPanel pnlLecturer;
    private JLabel lblWelcome;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JLabel lblPic;
    private JPanel pnlPic;
    private JButton logOutButton;
    private JButton updateMarks;

    private String userId;
    private String acc;
    String Fname;
    String Lname;

    private byte[] dImg;

    public Lecturer() {



    }

    public void methodLecturer() {
        userId = getUserId();
        acc = getAcc();

        add(pnlLecturer);
        setSize(500, 500);
        pnlPic.setSize(200,200);
        setTitle("Lecturer");
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

            while (rs2.next()){
                dImg = rs2.getBytes("img");
            }

            while (rs.next()) {
                Fname = rs.getString("FName");
                Lname = rs.getString("LName");
                byte[] imageData = rs.getBytes("Pro_pic");

                if(imageData == null){
                    ImageIcon defaultIcon = new ImageIcon(dImg);
                    Image image = defaultIcon.getImage();
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = bufferedImage.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setClip(new Ellipse2D.Float(0,0, image.getWidth(null), image.getHeight(null)));
                    g2.drawImage(image, 0, 0, null);
                    lblPic.setIcon(new ImageIcon(bufferedImage));
                }
                else{
                    ImageIcon icon = new ImageIcon(imageData);
                    Image image = icon.getImage();
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = bufferedImage.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setClip(new Ellipse2D.Float(0,0, image.getWidth(null), image.getHeight(null)));
                    g2.drawImage(image, 0, 0, null);
                    lblPic.setIcon(new ImageIcon(bufferedImage));
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblWelcome.setText("Welcome Dr." + Fname + " " + Lname + "!");

        gradesAndGPAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewGradeGPA gp = new ViewGradeGPA();
                gp.setVisible(true);
                setVisible(false);
                gp.viewGrades();

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
                Medical lecMed = new Medical();
                lecMed.viewMedicals();
                lecMed.setVisible(true);
                setVisible(false);
            }
        });

    }
}
