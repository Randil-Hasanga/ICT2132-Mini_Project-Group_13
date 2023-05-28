package TECMIS.Admin.DashBord.RemoveUser;

import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adId = textAdminId.getText();

                String sql = "DELETE FROM admin WHERE User_Id = ? ";

                PreparedStatement pstmt = null;
                try {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,adId);
                    int rows = pstmt.executeUpdate();

                    succ.setVisible(true);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
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
