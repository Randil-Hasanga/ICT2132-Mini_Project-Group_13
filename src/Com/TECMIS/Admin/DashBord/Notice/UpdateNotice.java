package Com.TECMIS.Admin.DashBord.Notice;

import Com.TECMIS.Admin.DashBord.Dashbord;
import Com.TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateNotice extends JFrame{
    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtNoticeid;
    private JButton submitButton;
    private JTextField txtSubjectName;
    private JTextArea txtDescription;
    private JButton backButton;
    private JButton clearButton;
    private JButton updateButton;
    private JPanel newPnl;
    private JLabel lblDisplay;
    private JPanel pnlUpNotice;

    private String notice_id;
    private String sub;
    private String desc;
    private String A_id;

    public void updateNotice(){

        add(pnlUpNotice);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        newPnl.setVisible(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notice_id = txtNoticeid.getText();
                newPnl.setVisible(true);

                String sql = "SELECT * FROM Notice WHERE Notice_id = ?";

                try {
                    PreparedStatement ss = conn.prepareStatement(sql);
                    ss.setString(1, notice_id);
                    ResultSet rs = ss.executeQuery();

                    while (rs.next()) {

                        sub = rs.getString("Subject_");
                        desc = rs.getString("Description_");
                        A_id = rs.getString("Admin_id");
                    }

                    txtSubjectName.setText(sub);
                    txtDescription.setText(desc);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sub = txtSubjectName.getText();
                desc = txtDescription.getText();

                String updCD = "UPDATE Notice " +
                        "SET  Subject_ = ? , Description_ = ? , Admin_id = ? " +
                        "WHERE Notice_id = ? ";

                try (PreparedStatement stmt = conn.prepareStatement(updCD)) {


                    stmt.setString(1, sub);
                    stmt.setString(2, desc);
                    stmt.setString(3, A_id);
                    stmt.setString(4,notice_id);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows Updated");

                    lblDisplay.setText("Database has been updated !");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
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

    }
}
