    package TECMIS.Admin.DashBord.UserDashBord.CreateUser;

import TECMIS.Admin.DashBord.UserDashBord.createUser;
import TECMIS.MySqlCon;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class createAdmin extends JFrame {

        Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
        private JTextField txtUID;
    private JTextField txtFname;
    private JTextField txtlname;
    private JTextField txtAd1;
    private JTextField txtAd2;
        private JTextField txtEmail;
        private JComboBox comboGender;
    private JPasswordField txtPWD;
    private JButton clearButton;
    private JButton submitButton1;
    private JPanel AdminPnl;
    private JButton backButton;
        private JButton choosePictureButton;
        private JButton chooseDateButton;
    private Date selectedDate;
    private String formattedDate;
    private File proPic;
    private String gender;

    private String user_id;
    private String fname;
    private String lname;
    private String Ad1;
    private String Ad2;
    private String email;
    private String password;


        public void methodAdmin(){

        add(AdminPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

            clearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    txtFname.setText("");
                    txtlname.setText("");
                    txtAd1.setText("");
                    txtAd2.setText("");
                    txtUID.setText("");
                    txtEmail.setText("");
                    txtPWD.setText("");
                }
            });
            comboGender.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gender = comboGender.getSelectedItem().toString();
                }
            });

            choosePictureButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        JFileChooser picOpen = new JFileChooser();
                        int result = picOpen.showOpenDialog(null);
                        if(result == JFileChooser.APPROVE_OPTION){
                            proPic = picOpen.getSelectedFile();
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });


            chooseDateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new JFrame("Choose Date");
                    JDateChooser dateChooser = new JDateChooser();
                    frame.add(dateChooser);
                    frame.setType(Window.Type.UTILITY);
                    frame.pack();
                    frame.setLocationRelativeTo(null); // Center the frame on the screen
                    frame.setVisible(true);

                    dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getPropertyName().equals("date")) {
                                selectedDate = dateChooser.getDate(); // get selected date

                                // Format the selected date as YYYY-MM-DD
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                formattedDate = sdf.format(selectedDate);
                                System.out.println(selectedDate);
                                System.out.println(formattedDate);
                                frame.dispose(); // Close the frame after selecting the date
                            }
                        }
                    });
                }
            });



        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_id = txtUID.getText();
                fname = txtFname.getText();
                lname = txtlname.getText();
                Ad1 = txtAd1.getText();
                Ad2 = txtAd2.getText();
                email = txtEmail.getText();
                password = String.valueOf(txtPWD.getPassword());

                String sql = "INSERT INTO Admin " +
                        "(User_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?)";

                try{
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    byte[] imageData = Files.readAllBytes(proPic.toPath());
                    pstmt.setString(1,user_id);
                    pstmt.setString(2,fname);
                    pstmt.setString(3,lname);
                    pstmt.setString(4,gender);
                    pstmt.setString(5,Ad1);
                    pstmt.setString(6,Ad2);
                    pstmt.setString(7,formattedDate);
                    pstmt.setString(8,email);
                    pstmt.setBytes(9,imageData);
                    pstmt.setString(10,password);


                    pstmt.executeUpdate();

                } catch (SQLException ex) {
                    System.out.println("problem error 1 : " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("problem error 2: " + ex.getMessage());                }
            }
        });






        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser back = new createUser();
                back.methodUser();
                back.setVisible(true);
                setVisible(false);
            }
        });
    }
}


