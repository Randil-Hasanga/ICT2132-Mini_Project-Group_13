package TECMIS.Admin.DashBord.TimeTable;

import TECMIS.Admin.DashBord.Dashbord;
import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveTimeTable extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();

    private JPanel ReTiTaPnl;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField TiTaId;
    private JButton backButton;
    private JButton clearButton;
    private JButton submitButton;
    private JLabel scc;
    private JPanel succ;
    private String TTId;





    public void RemoveTimeTableMethod() {

    add(ReTiTaPnl);
    setVisible(true);
    setSize(750,500);
    setTitle("LMS Software");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    succ.setVisible(false);



        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    TTId = TiTaId.getText();

                    String sql = "DELETE FROM t_table WHERE TT_id = ? ";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,TTId);
                    int rows = pstmt.executeUpdate();

                    scc.setText("Successful Remove Time Table !");

                } catch (SQLException ex) {
                    System.out.println("Error in sql" + ex.getMessage());
                }

            }
        });


    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dashbord back = new Dashbord();
            back.setVisible(true);
            setVisible(false);
            back.methodAdmin();
        }
    });
    clearButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TiTaId.setText("");
        }
    });
}
}
