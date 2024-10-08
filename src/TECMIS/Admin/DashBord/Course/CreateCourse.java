package TECMIS.Admin.DashBord.Course;

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

public class CreateCourse extends JFrame{
    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCName;
    private JTextField txtCredit;
    private JTextField txtAdminId;
    private JButton backButton;
    private JButton createButton;
    private JButton clearButton;
    private JTextField txtCID;
    private JLabel lblSuccess2;
    private JTextField txtLevel;
    private JTextField txtSem;
    private JTextField txtLecId;
    private JPanel pnlCreateC;

    private String userId;
    private String acc;
    private String CID;
    private String CourseName;
    private String AdminId;
    private int Credit;
    private String lecId;
    private int level;
    private int semester;


    public void createCourse(){

        add(pnlCreateC);
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
                txtCID.setText("");
                txtAdminId.setText("");
                txtCredit.setText("");
                txtCName.setText("");
                txtLecId.setText("");
                txtLevel.setText("");
                txtSem.setText("");
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CID = txtCID.getText();
                Credit = Integer.parseInt(txtCredit.getText());
                AdminId = txtAdminId.getText();
                CourseName = txtCName.getText();
                lecId = txtLecId.getText();
                level = Integer.parseInt(txtLevel.getText());
                semester = Integer.parseInt(txtSem.getText());

                String upCD = "INSERT INTO Course_Detail (Course_id, Course_Name, Credit, Admin_id, Lecturer_id, Level, Semester) VALUES (?,?,?,?,?,?,?)";

                try(PreparedStatement stmt = conn.prepareStatement(upCD)){

                    stmt.setString(1,CID);
                    stmt.setString(2,CourseName);
                    stmt.setInt(3,Credit);
                    stmt.setString(4,AdminId);
                    stmt.setString(5,lecId);
                    stmt.setInt(6,level);
                    stmt.setInt(7,semester);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess2.setText(" New course material successfully added to database ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
