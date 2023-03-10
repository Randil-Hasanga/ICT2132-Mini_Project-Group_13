package TECMIS;

import TECMIS.Lecturer.Lecturer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class User extends JFrame{

    protected String userId;



    String pwd;
    String DBpwd;
    Date DOB;


    String address_L1;
    String getAddress_L2;
    String acc;


    private JComboBox<String> userDrop;
    private JTextField txtUserId;
    private JPasswordField txtPwd;
    private JButton btnClear;
    private JButton btnOK;
    private JLabel lblpwd;
    private JLabel lblemail;
    private JTextArea loginDisplay;
    private JTextPane facultyOfTechnologyManagementTextPane;
    private JPanel pnlLogin;


    public void Login() {
        add(pnlLogin);
        setVisible(true);
        setSize(1000,350);
        setTitle("LMS Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUserId.setText("");
                txtPwd.setText("");
                loginDisplay.setText("");
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

                String sql = "SELECT Password FROM " + acc + " WHERE User_id = ?";

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LMSDB", "root", "");
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

                            loginDisplay.setText("Password correct");

                            Lecturer lecturer = new Lecturer(userId,acc);
                            lecturer.setVisible(true);
                            setVisible(false);

                        } else {
                            loginDisplay.setText("Incorrect email or password");
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Connection failed !");
                    ex.printStackTrace();
                }
            }
        });
    }

}
