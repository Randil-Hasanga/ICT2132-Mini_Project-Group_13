package TECMIS.Admin.DashBord.RemoveUser;

import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RevomeStudent extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();

    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField ReStu;
    private JButton submitButton;
    private JPanel RemoveStudentPnl;
    private JPanel succsPnl;
    private JButton backButton;
    private JButton clearButton;
    private String sId;





    public void RemoveStudenMethod(){
        add(RemoveStudentPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        succsPnl.setVisible(false);


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

                sId = ReStu.getText();

                String sql = "DELETE FROM student WHERE User_id = ? ";

                PreparedStatement pstmt=null;

                try {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,sId);
                    int rows = pstmt.executeUpdate();

                    succsPnl.setVisible(true);



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
                ReStu.setText("");
            }
        });
    }
}
