package TECMIS;

import TECMIS.Admin.DashBord.Dashbord;
import TECMIS.Lecturer.Lecturer;
import TECMIS.Student.Student;
import TECMIS.TechnicalOfficer.TechnicalOfficer.TechnicalOfficer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Base64;


public class User extends JFrame{

    private Connection conn;
    {
        conn = MySqlCon.MysqlMethod();
    }
    private static String userId;
    private static String acc;
    private String pwd;
    private String DBpwd;
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


    public User() {
        add(pnlLogin);
        icon.setSize(100,100);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        lblDisplay.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

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

                // password encryption
                String EncryptedPwd = null;
                String secretKey = "ThisIsASecretKey";

                try {
                    SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(),"AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, keySpec);

                    byte[] encryptedBytes = cipher.doFinal(pwd.getBytes());
                    EncryptedPwd = Base64.getEncoder().encodeToString(encryptedBytes);

                } catch (Exception ex){
                    ex.printStackTrace();
                }

                String sql = "SELECT Password FROM " + acc + " WHERE User_id = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, userId);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()){
                        DBpwd = rs.getString("password");
                    }

                    if(acc.equals("lecturer")){
                        if(DBpwd != null && DBpwd.equals(EncryptedPwd)){
                            Statement stmt = conn.createStatement();

                            String query1 = "CREATE USER IF NOT EXISTS 'lecturer'@'localhost' IDENTIFIED BY 'lecturer123'";
                            String query2 = "GRANT SELECT,INSERT,UPDATE (User_id, FName, LName, Gender, Address_L1, Address_L2, DOB, Pro_pic, Position) ON LMSDB.Lecturer TO 'lecturer'@'localhost'";

                            stmt.executeUpdate(query1);
                            stmt.executeUpdate(query2);
                            stmt.close();

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

                    }else if (acc.equals("technical_officer")){

                        if (DBpwd != null && DBpwd.equals(pwd)){
                            Statement stmt = conn.createStatement();

                            String query7 = "CREATE USER IF NOT EXISTS 'technical_officer'@'localhost' IDENTIFIED BY 'technical_officer123'";
                            String query8 = "GRANT SELECT,INSERT,UPDATE (User_id,FName,LName,Gender,Address_L1, Address_L2,DOB, Email,Pro_pic) ON LMSDB.Technical_officer TO 'technical_officer'@'localhost'";

                            stmt.executeUpdate(query7);
                            stmt.executeUpdate(query8);
                            stmt.close();

                            lblDisplay.setText("Password correct");

                            TechnicalOfficer technicalOfficer = new TechnicalOfficer();
                            technicalOfficer.setUserId(userId);
                            technicalOfficer.setAcc(acc);
                            technicalOfficer.methodTechnicalOfficer();
                            technicalOfficer.setVisible(true);
                            setVisible(false);
                        }else {
                            lblDisplay.setVisible(true);
                            lblDisplay.setText(" Incorrect email or password ");
                        }
                    }
                    else if (acc.equals("student")){

                        if (DBpwd != null && DBpwd.equals(pwd)){
                            Statement stmt = conn.createStatement();

                            String query5 = "CREATE USER IF NOT EXISTS 'student'@'localhost' IDENTIFIED BY 'student123'";
                            String query6 = "GRANT SELECT,INSERT,UPDATE (User_id,FName,LName,Gender,Address_L1, Address_L2,DOB, Email,Pro_pic,S_type) ON LMSDB.Student TO 'student'@'localhost'";

                            stmt.executeUpdate(query5);
                            stmt.executeUpdate(query6);
                            stmt.close();

                            lblDisplay.setText("Password correct");

                            Student student = new Student();
                            student.setUserId(userId);
                            student.setAcc(acc);
                            student.methodStudent();
                            student.setVisible(true);
                            setVisible(false);
                        }
                    }
                    else if(acc.equals("admin")){

                        if(DBpwd !=null && DBpwd.equals(pwd)){
                            Statement stmt = conn.createStatement();

                            String query3 = "CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'admin123'";
                            String query4 = "GRANT ALL PRIVILEGES ON LMSDB.Admin TO 'admin'@'localhost'";

                            stmt.executeUpdate(query3);
                            stmt.executeUpdate(query4);
                            stmt.close();

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
                    pstmt.close();
                    rs.close();
                }catch (SQLException ex) {
                    System.out.println("Connection failed !");
                    ex.printStackTrace();
                }
            }
        });
    }
}
