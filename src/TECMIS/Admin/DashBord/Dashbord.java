package TECMIS.Admin.DashBord;

import TECMIS.Admin.DashBord.Course.CreateCourse;
import TECMIS.Admin.DashBord.Course.RemoveCourse;
import TECMIS.Admin.DashBord.Course.UpdateCouse;
import TECMIS.Admin.DashBord.Notice.UpdateNotice;
import TECMIS.Admin.DashBord.Notice.createNotice;
import TECMIS.Admin.DashBord.RemoveUser.RemoveUserDashbord;
import TECMIS.Admin.DashBord.TimeTable.CreateTimeTable.CreateTimeTable;
import TECMIS.Admin.DashBord.UpdateUser.UpdateUserDashbord;
import TECMIS.Admin.DashBord.UserDashBord.createUser;
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

public class Dashbord extends User {

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel pnlPic;
    private JLabel lblPic;
    private JButton logOutButton;
    private JButton UpdateUserButton;
    private JButton UpdateCourseButton2;
    private JButton updateNoticeButton;
    private JButton RemoveUserButton;
    private JButton CreateUserButton;
    private JButton courseButton;
    private JPanel pnlAdmin;
    private JButton noticeButton1;
    private JButton timeTableButton;
    private JButton UpdateTimeTableButton;
    private JButton RemoveCourseButton;
    private JButton removeNoticeButton;
    private JButton removeTimeTableButton;
    private String userId;
    private String acc;
    private byte[] dImg;
    private String Fname;
    private String Lname;




    public void methodAdmin(){

        userId = getUserId();
        acc = getAcc();

        add(pnlAdmin);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
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


        //Insert data in table----------------------------
        CreateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser admin = new createUser();
                admin.methodUser();
                admin.setVisible(true);
                setVisible(false);

            }
        });


        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CreateCourse course = new CreateCourse();
                course.createCourse();
                course.setVisible(true);
                setVisible(false);

            }
        });

        noticeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNotice notice = new createNotice();
                notice.creatNoticeMethod();
                notice.setVisible(true);
                setVisible(false);
            }
        });


        timeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateTimeTable table = new CreateTimeTable();
                table.setVisible(true);
                setVisible(false);
                table.CreateTableMethod();
            }
        });


        //Update Details-------------------------------------------

        UpdateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateUserDashbord UpdateUser= new UpdateUserDashbord();
                UpdateUser.UpdateUserMethod();
                UpdateUser.setVisible(true);
                setVisible(false);
            }
        });

        UpdateCourseButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCouse CUpdate = new UpdateCouse();
                CUpdate.UpdateCourseMethod();
                CUpdate.setVisible(true);
                setVisible(false);
            }
        });

        updateNoticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateNotice CN = new UpdateNotice();
                CN.setVisible(true);
                setVisible(false);
                CN.updateNotice();
            }
        });


        //Remove Details--------------------------------------
        RemoveUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveUserDashbord RemoveUser= new RemoveUserDashbord();
                RemoveUser.RemoveUserMethod();
                RemoveUser.setVisible(true);
                setVisible(false);
            }
        });


        RemoveCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveCourse RemCou = new RemoveCourse();
                RemCou.RemoveCourseDetails();
                RemCou.setVisible(true);
                setVisible(false);
            }
        });
    }
}
