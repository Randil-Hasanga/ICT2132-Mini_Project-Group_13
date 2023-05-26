package Com.TECMIS.Admin.DashBord.Course;

import Com.TECMIS.Admin.DashBord.Dashbord;
import Com.TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateCouse extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCid;
    private JButton submitButton;
    private JTextField txtN;
    private JTextField txtCre;
    private JTextField txtAdm;
    private JTextField txtle;
    private JTextField txtSe;
    private JTextField txtLec;
    private JPanel newpnl;
    private JPanel UpdateCourse;
    private JButton submitButton1;
    private JButton clearButton;
    private JLabel lbldisplay;
    private JButton backButton;
    private String Course_id;
    private String Cname;
    private int Credit;
    private String AdminId;
    private  int  Level;
    private  int Semester;
    private String Lec_id;





    public void UpdateCourseMethod(){

        add(UpdateCourse);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        newpnl.setVisible(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course_id = txtCid.getText();
                newpnl.setVisible(true);

                String sql = "SELECT * FROM Course_Detail WHERE Course_id = ?";

                try{
                    PreparedStatement ss = conn.prepareStatement(sql);
                    ss.setString(1,Course_id);
                    ResultSet rs = ss.executeQuery();

                    while(rs.next()){
                        Cname = rs.getString("Course_Name");
                        Credit = rs.getInt("Credit");
                        AdminId = rs.getString("Admin_id");
                        Level = rs.getInt("Level");
                        Semester = rs.getInt("Semester");
                        Lec_id = rs.getString("Lecturer_id");


                    }

                    txtN.setText(Cname);
                    txtCre.setText(String.valueOf(Credit));
                    txtAdm.setText(AdminId);
                    txtle.setText(String.valueOf(Level));
                    txtSe.setText(String.valueOf(Semester));
                    txtLec.setText(Lec_id);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cname = txtN.getText();
                Credit = Integer.parseInt(txtCre.getText());
                AdminId = txtAdm.getText();
                Level = Integer.parseInt(txtle.getText());
                Semester = Integer.parseInt(txtSe.getText());
                Lec_id = txtLec.getText();

                String updCD = "UPDATE Course_Detail " +
                        "SET Course_Name = ? , Credit = ? , Admin_id = ? , Level = ? , Semester = ? " +
                        "WHERE Course_id = ? ";

                try (PreparedStatement stmt = conn.prepareStatement(updCD)) {


                    stmt.setString(1, Cname);
                    stmt.setInt(2, Credit);
                    stmt.setString(3, AdminId);
                    stmt.setInt(4, Level);
                    stmt.setInt(5, Semester);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows Updated");

                    lbldisplay.setText("Database has been updated !");

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
