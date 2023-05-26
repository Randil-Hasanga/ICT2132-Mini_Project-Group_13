package Com.TECMIS.Admin.DashBord.RemoveUser;

import Com.TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveTechnicalOffice extends JFrame{
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField tecOffRe;
    private JButton submitButton;
    private JPanel succsPnl;
    private JPanel RemoveTechOffiPnl;
    private JButton backButton;
    private JButton clearButton;
    private String TecID;






    public void RemoveTechOffMethod(){

        Connection conn = MySqlCon.MysqlMethod();


        add(RemoveTechOffiPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        succsPnl.setVisible(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TecID = tecOffRe.getText();

                String sql = "DELETE FROM technical_officer WHERE User_id = ? ";

                PreparedStatement pstmt = null;

                try {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,TecID);
                    int rows = pstmt.executeUpdate();

                    succsPnl.setVisible(true);

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
                tecOffRe.setText("");
            }
        });

    }
}
