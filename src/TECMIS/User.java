package TECMIS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class User extends JFrame{

    int User_id;
    String FName;
    String LName;
    String mobile;
    String gender;
    String email;
    String DBemail;

    String pwd;
    String DBpwd;
    Date DOB;


    String address_L1;
    String getAddress_L2;
    String acc;


    private JComboBox<String> userDrop;
    private JTextField txtEmail;
    private JPasswordField txtPwd;
    private JButton btnClear;
    private JButton btnOK;
    private JLabel lblpwd;
    private JLabel lblemail;
    private JTextArea loginDisplay;
    private JTextPane facultyOfTechnologyManagementTextPane;
    private JPanel pnlLogin;

    public User() {
        add(pnlLogin);
        setVisible(true);
        setSize(1000,350);
        setTitle("LMS Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtEmail.setText("");
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
                email = txtEmail.getText();

                String sql = "SELECT Password FROM " + acc + " WHERE Email = ?";

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LMSDB", "root", "");
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, email);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()){
                        DBpwd = rs.getString("password");
                    }
                    if(DBpwd != null && DBpwd.equals(pwd)){
                        loginDisplay.setText("Password correct");
                    } else {
                        loginDisplay.setText("Incorrect email or password");
                    }

                } catch (SQLException ex) {
                    System.out.println("Connection failed !");
                    ex.printStackTrace();
                }
            }
        });
    }


}
