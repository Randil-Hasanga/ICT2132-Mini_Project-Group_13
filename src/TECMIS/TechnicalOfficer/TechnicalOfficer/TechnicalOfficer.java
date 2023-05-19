package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.MySqlCon;
import TECMIS.User;

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

public class TechnicalOfficer extends User {
    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlTechnicalOfficer;
    private JLabel lblWelcome;
    private JButton AddBtnMedical;
    private JButton AddBtnAttendenceDetails;
    private JButton UpdateAttendenceDetails;
    private JButton UpdateBtnMedicals;
    private JButton UpdateBtnProfile;
    private JButton RemoveBtnAttendenceDetails;
    private JButton RemoveBtnMedicals;
    private JButton ViewBtnNotice;
    private JButton viewBtnTimetable;
    private JPanel pnlPic;
    private JButton BtnLogout;

    private String userId;
    private String acc;

    String Fname;
    String Lname;

    private byte[] dImg;
    private JLabel lblPic;
    private JTextArea facultyOfTechnologyManagementTextArea;


    public void methodTechnicalOfficer() {
        userId = getUserId();
        acc = getAcc();

        add(pnlTechnicalOfficer);
        setSize(750, 500);
        pnlPic.setSize(200, 200);
        setLocationRelativeTo(null);
        setTitle("TechnicalOfficer");
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
                    final ImageIcon imageIcon = new ImageIcon(bufferedImage);
                    final ImageIcon imageIcon1 = new ImageIcon(bufferedImage);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lblWelcome.setText("Welcome Dr." + Fname + " " + Lname + "!");

        //View Buttons
        ViewBtnNotice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tech_OfficerNotice TONotice = new Tech_OfficerNotice();
                TechnicalOfficer TO = new TechnicalOfficer();
                TONotice.setVisible(true);
                setVisible(false);
                TONotice.viewTONotice();
            }
        });

        viewBtnTimetable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timetable TOTimetable = new Timetable();
                TechnicalOfficer TO = new TechnicalOfficer();
                TOTimetable.setVisible(true);
                setVisible(false);
                TOTimetable.viewTimetable();
            }
        });

        //Log out Button
        BtnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User ur = new User();
                ur.setVisible(true);
                setVisible(false);
                ur.Login();
            }
        });

        // Add Buttons
        AddBtnMedical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadMedical upMedical = new UploadMedical();
                upMedical.uploadMedical();
                upMedical.setVisible(true);
                setVisible(false);
            }
        });

        AddBtnAttendenceDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadAttendenceDetails upAttendence = new UploadAttendenceDetails();
                upAttendence.uploadAttendance();
                upAttendence.setVisible(true);
                setVisible(false);
            }
        });

        // Update Buttons
        UpdateAttendenceDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 UpdateAttendenceDetails updAttendence = new UpdateAttendenceDetails();
                 updAttendence.UpdateAttendence();
                 updAttendence.setVisible(true);
                 setVisible(false);

            }
        });

        UpdateBtnMedicals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateMedical updMedical = new UpdateMedical();
                updMedical.UpdateMedical();
                updMedical.setVisible(true);
                setVisible(false);
            }
        });

        UpdateBtnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateProfile updProf = new UpdateProfile();
                updProf.UpdateProfile();
                updProf.setVisible(true);
                setVisible(false);
            }
        });


        //Remove Buttons
        RemoveBtnAttendenceDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveAttendenceDetails rmvAttendence = new RemoveAttendenceDetails();
                rmvAttendence.RemoveAttendence();
                rmvAttendence.setVisible(true);
                setVisible(false);
            }
        });

        RemoveBtnMedicals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveMedical rmvMedical = new RemoveMedical();
                rmvMedical.RemoveMedical();
                rmvMedical.setVisible(true);
                setVisible(false);
            }
        });


    }
}
