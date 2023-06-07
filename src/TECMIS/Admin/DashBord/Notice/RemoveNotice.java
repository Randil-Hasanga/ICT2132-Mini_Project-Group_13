package TECMIS.Admin.DashBord.Notice;

import TECMIS.Admin.DashBord.Dashbord;
import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveNotice extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();

    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel reNoPnl;
    private JTextField NoId;

    private JButton backButton;
    private JButton submitButton;
    private JButton clearButton;
    private JLabel scc;
    private String NotId;






    public void RemoveNotiMethod(){

        add(reNoPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

                NotId = NoId.getText();


                try {
                    String sql = "DELETE FROM notice WHERE Notice_id = ? ";
                    PreparedStatement pstmt = null;
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,NotId);
                    int rows = pstmt.executeUpdate();

                    scc.setText("Successful Remove Notice !");

                } catch (SQLException ex) {
                    System.out.println("Error in sql" + ex.getMessage());
                }


            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashbord NoticBack = new Dashbord();
                NoticBack.methodAdmin();
                NoticBack.setVisible(true);
                setVisible(false);

            }
        });



        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoId.setText("");
            }
        });
    }
}
