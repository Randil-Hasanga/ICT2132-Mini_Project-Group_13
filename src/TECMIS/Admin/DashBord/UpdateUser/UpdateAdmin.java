package TECMIS.Admin.DashBord.UpdateUser;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateAdmin extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel UpdateAdminPnl;
    private JTextField txtAid;
    private JButton submitButton;
    private JTextField txtFname;
    private JTextField txtLname;
    private JTextField txtAd1;
    private JTextField txtAd2;
    private JTextField txtemail;
    private JButton dateButton;
    private JPanel NewPnl;
    private JButton profileButton;
    private JButton submitButton1;
    private JButton clearButton;
    private JButton backButton;
    private JPasswordField txtpwd;
    private JLabel lblDisplay;

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
    private byte[] imageData;




    public void UpdateAdminMethod(){
        add(UpdateAdminPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        NewPnl.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user_id = txtAid.getText();
                NewPnl.setVisible(true);

                String sql = "SELECT * FROM Admin WHERE User_id = ?";

                try{
                    PreparedStatement ss = conn.prepareStatement(sql);
                    ss.setString(1,user_id);
                    ResultSet rs = ss.executeQuery();

                    while(rs.next()){
                        fname = rs.getString("FName");
                        lname = rs.getString("LName");
                        Ad1 = rs.getString("Address_L1");
                        Ad2 = rs.getString("Address_L2");
                        email = rs.getString("Email");
                        password = rs.getString("Password");


                    }

                    txtFname.setText(fname);
                    txtLname.setText(lname);
                    txtAd1.setText(Ad1);
                    txtAd2.setText(Ad2);
                    txtemail.setText(email);
                    txtpwd.setText(password);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        dateButton.addActionListener(new ActionListener() {
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
        profileButton.addActionListener(new ActionListener() {
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


        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                fname = txtFname.getText();
                lname = txtLname.getText();
                Ad1 = txtAd1.getText();
                Ad2 = txtAd2.getText();
                email = txtemail.getText();
                password = String.valueOf(txtpwd.getPassword());

                String up = "Update Admin " +
                        "SET FName = ?, LName = ?, Address_L1 = ?, Address_L2 = ?, DOB = ?, Email = ?, Pro_pic = ?, Password = ? " +
                        "WHERE User_id = ?";

                try{
                    imageData = Files.readAllBytes(proPic.toPath());
                    PreparedStatement sss = conn.prepareStatement(up);
                    sss.setString(1,fname);
                    sss.setString(2,lname);
                    sss.setString(3,Ad1);
                    sss.setString(4,Ad2);
                    sss.setString(5,formattedDate);
                    sss.setString(6,email);
                    sss.setBytes(7,imageData);
                    sss.setString(8,password);
                    sss.setString(9,user_id);

                    sss.executeUpdate();

                    lblDisplay.setText("Database successfully updated !");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFname.setText("");
                txtLname.setText("");
                txtAd1.setText("");
                txtAd2.setText("");
                txtAid.setText("");
                txtemail.setText("");
                txtpwd.setText("");
            }
        });




        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UpdateUserDashbord back = new UpdateUserDashbord();
                back.setVisible(true);
                setVisible(false);
                back.UpdateUserMethod();
            }
        });
    }
}
