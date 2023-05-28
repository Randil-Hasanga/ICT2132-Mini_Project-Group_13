package com.TECMIS;

import com.TECMIS.Admin.DashBord.Dashbord;
import com.TECMIS.Lecturer.Lecturer;
import com.TECMIS.Student.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class User extends JFrame{

    Connection conn;

    {
        conn = MySqlCon.MysqlMethod();
    }

    private static String userId;
    private static String acc;



    String pwd;
    String DBpwd;
    Date DOB;


    String address_L1;
    String getAddress_L2;



    private JComboBox<String> userDrop;
    private JTextField txtUserId;
    private JPasswordField txtPwd;
    private JButton ClearButton;
    private JButton btnOK;
    private JLabel lblpwd;
    private JLabel lblemail;
    private JTextArea loginDisplay;
    private JPanel pnlLogin;
    private JTextArea facultyOfTechnologyManagementTextarea;
    private JLabel icon;
    private JLabel lblDisplay;
    private JPanel pnl123;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public static String getUserId(){
        return userId;
    }

    public void setAcc(String acc){
        this.acc = acc;
    }

    public static String getAcc(){
        return acc;
    }


    public void Login() {
        add(pnlLogin);
        icon.setSize(100,100);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        lblDisplay.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUserId.setText("");
                txtPwd.setText("");
                if(loginDisplay != null) {
                    loginDisplay.setText("");
                }
            }
        });
        userDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acc = userDrop.getSelectedItem().toString();
            }
        });
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pwd = String.valueOf(txtPwd.getPassword());
                userId = txtUserId.getText();

                if((userId.isEmpty()) || (pwd.isEmpty())){
                    lblDisplay.setVisible(true);
                    lblDisplay.setText("Fill all the fields to proceed !");
                }

                String sql = "SELECT Password FROM " + acc + " WHERE User_id = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, userId);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()){
                        DBpwd = rs.getString("password");
                    }

                    if(acc.equals("lecturer")){


                        if(DBpwd != null && DBpwd.equals(pwd)){
                            Statement stmt = conn.createStatement();

                            String query1 = "CREATE USER IF NOT EXISTS 'lecturer'@'localhost' IDENTIFIED BY 'lecturer123'";
                            String query2 = "GRANT SELECT,INSERT,UPDATE (User_id, FName, LName, Gender, Address_L1, Address_L2, DOB, Pro_pic, Position) ON LMSDB.Lecturer TO 'lecturer'@'localhost'";

                            stmt.executeUpdate(query1);
                            stmt.executeUpdate(query2);

                            lblDisplay.setText("Password correct");

                            Lecturer lecturer = new Lecturer();
                            lecturer.setUserId(userId);
                            lecturer.setAcc(acc);
                            lecturer.methodLecturer();
                            lecturer.setVisible(true);
                            setVisible(false);

                        } else {
                            lblDisplay.setVisible(true);
                            lblDisplay.setText("Incorrect email or password");
                        }

                    }else if (acc.equals("student")){

                        if (DBpwd != null && DBpwd.equals(pwd)){
                            Statement stmt = conn.createStatement();

                            String query5 = "CREATE USER IF NOT EXISTS 'student'@'localhost' IDENTIFIED BY 'student123'";
                            String query6 = "GRANT SELECT,INSERT,UPDATE (User_id,FName,LName,Gender,Address_L1, Address_L2,DOB, Email,Pro_pic,S_type) ON LMSDB.Student TO 'student'@'localhost'";

                            stmt.executeUpdate(query5);
                            stmt.executeUpdate(query6);

                            lblDisplay.setText("Password correct");

                            Student student = new Student();
                            student.setUserId(userId);
                            student.setAcc(acc);
                            student.methodStudent();
                            student.setVisible(true);
                            setVisible(false);
                        }


                    }else if(acc.equals("admin")){

                        if(DBpwd !=null && DBpwd.equals(pwd)){
                            Statement stmt = conn.createStatement();

                            String query3 = "CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'admin123'";
                            String query4 = "GRANT ALL PRIVILEGES ON LMSDB.Admin TO 'admin'@'localhost'";

                            stmt.executeUpdate(query3);
                            stmt.executeUpdate(query4);

                            lblDisplay.setText("Password correct");

                            Dashbord admin= new Dashbord();
                            admin.setUserId(userId);
                            admin.setAcc(acc);
                            admin.methodAdmin();
                            admin.setVisible(true);
                            setVisible(false);
                        }else{
                            lblDisplay.setVisible(true);
                            lblDisplay.setText("Incorrect email or password");
                        }
                    }
                }catch (SQLException ex) {
                    System.out.println("Connection failed !");
                    ex.printStackTrace();
                }
            }
        });
    }


}
