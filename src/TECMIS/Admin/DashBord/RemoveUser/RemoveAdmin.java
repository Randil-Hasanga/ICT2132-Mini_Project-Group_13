package TECMIS.Admin.DashBord.RemoveUser;

import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RemoveAdmin extends JFrame{
    Connection conn = MySqlCon.MysqlMethod();


    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField textAdminId;
    private JButton submitButton;
    private JButton clearButton;
    private JButton backButton;
    private JPanel succ;
    private JPanel RemAddPnl;
    private String adId;







    public void RemoveAdminMethod(){

        add(RemAddPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        succ.setVisible(false);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Close the application
                    System.exit(0);
                }else {
                    // Do nothing (prevent the window from closing)
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adId = textAdminId.getText();


                try {


//                    String CouseIDSQL = " SELECT Course_id FROM course_detail WHERE Admin_id=?";//---------------
//                    PreparedStatement selectCourseIdStmt = conn.prepareStatement(CouseIDSQL);
//                    selectCourseIdStmt.setString(1, adId);
//                    ResultSet rs = selectCourseIdStmt.executeQuery(CouseIDSQL);
//                    String coId= rs.getString("Course_id");
//
//                    System.out.println(coId);





                    // Delete the Admin from the course_detail table
                    String deleteCourseSQL = "DELETE FROM course_detail WHERE Admin_id= ?";
                    PreparedStatement deleteCourseStmt = conn.prepareStatement(deleteCourseSQL);
                    deleteCourseStmt.setString(1, adId);
                    int rows1 = deleteCourseStmt.executeUpdate();

//                    // Delete Admin for the attendance table-----------------------------
//                    String deleteAttendanceSQL = "DELETE FROM attendance WHERE Course_id = coId";
//                    PreparedStatement deleteAttendanceStmt = conn.prepareStatement(deleteAttendanceSQL);
//                    int rows2 = deleteAttendanceStmt.executeUpdate();


                    //Delete the Admin from the Admin table
                    String sql = "DELETE FROM admin WHERE User_Id = ? ";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,adId);
                    int rows = pstmt.executeUpdate();



                    succ.setVisible(true);

                } catch (SQLException ex) {
                    System.out.println("Error in sql" + ex.getMessage());
                }



            }
        });








        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveUserDashbord RemAdmin = new RemoveUserDashbord();
                RemAdmin.RemoveUserMethod();
                RemAdmin.setVisible(true);
                setVisible(false);
            }

        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAdminId.setText("");
            }
        });
    }
}
