package TECMIS.Admin.DashBord.Notice;

import TECMIS.Admin.DashBord.Dashbord;
import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class createNotice extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private String user;
    private String acc;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel createNotiPnl;
    private JTextField Nid;
    private JTextField Sid;
    private JTextArea Descript;
    private JTextField ADmin;
    private JButton submitButton;
    private JButton clearButton;
    private JButton backButton;
    private JLabel lblDisplay;
    private JComboBox dropAssign;
    private JTextField txtAssign;

    private String No_id;
    private String description;
    private String Sname;
    private String assign;
    private String ac;
    private String assignTo;


    public void creatNoticeMethod(){
        add(createNotiPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                Nid.setText("");
                Descript.setText("");
                ADmin.setText("");
                Sid.setText("");
            }
        });
        dropAssign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assign = dropAssign.getSelectedItem().toString();
                if(assign.equals("Lecturer")){
                    ac = "Lecturer_Notice";
                }else if(assign.equals("Student")){
                    ac = "Student_Notice";
                }else if(assign.equals("Technical Officer")){
                    ac = "Tech_OfficerNotice";
                }
            }
        });


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = User.getUserId();
                acc = User.getAcc();

                No_id = Nid.getText();
                description = Descript.getText();
                Sname = Sid.getText();
                assignTo = txtAssign.getText();


                String sql = "INSERT INTO Notice " +
                        "(Notice_id, Subject_, Description_, Admin_id) " +
                        "VALUES (?,?,?,?)";

                try{
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,No_id);
                    pstmt.setString(2,Sname);
                    pstmt.setString(3, description);
                    pstmt.setString(4,user);

                    int rowsInserted = pstmt.executeUpdate();

                    if(ac.equals("Lecturer_Notice")){
                        String sql2 = "INSERT INTO Lecturer_Notice " +
                                "(Lecturer_id,Notice_id) " +
                                "VALUES (?,?)";
                        try{
                            PreparedStatement ccc = conn.prepareStatement(sql2);
                            ccc.setString(1,assignTo);
                            ccc.setString(2,No_id);

                            ccc.executeUpdate();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }else if(ac.equals("Student_Notice")){
                        String sql3 = "INSERT INTO Student_Notice " +
                                "(Student_id,Notice_id) " +
                                "VALUES (?,?)";
                        try{
                            PreparedStatement ccc = conn.prepareStatement(sql3);
                            ccc.setString(1,assignTo);
                            ccc.setString(2,No_id);
                            ccc.executeUpdate();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }else if(ac.equals("Tech_OfficerNotice")){
                        String sql4 = "INSERT INTO Tech_OfficerNotice " +
                                "(T_Officer_Id, Notice_id) " +
                                "VALUES (?,?) ";
                        try{
                            PreparedStatement ccc = conn.prepareStatement(sql4);
                            ccc.setString(1,assignTo);
                            ccc.setString(2,No_id);
                            ccc.executeUpdate();

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    System.out.println(rowsInserted + "Rows inserted");

                    lblDisplay.setText(" New notice successfully added to database ! ");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });

    }
}
