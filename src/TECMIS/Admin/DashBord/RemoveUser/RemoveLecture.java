package TECMIS.Admin.DashBord.RemoveUser;

import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveLecture extends JFrame {

    Connection conn = MySqlCon.MysqlMethod();

    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel RemovelecturePnl;
    private JTextField lecId;
    private JButton submitButton;
    private JPanel succsPnl;
    private JButton backButton;
    private JButton clearButton;
    private String LecId;





    public void RemoveLectuMethod(){

        add(RemovelecturePnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        succsPnl.setVisible(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LecId = lecId.getText();

                String sql = "DELETE FROM lecturer WHERE User_id = ? ";

                PreparedStatement pstmt=null;
                try {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,LecId);
                    int rows = pstmt.executeUpdate();

                    succsPnl.setVisible(true);

                } catch (SQLException ex) {
                    System.out.printf("sql arr" + ex.getMessage());
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
                lecId.setText("");
            }
        });
    }
}
