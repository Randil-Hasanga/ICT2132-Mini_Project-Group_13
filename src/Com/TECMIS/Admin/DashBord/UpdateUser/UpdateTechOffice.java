package Com.TECMIS.Admin.DashBord.UpdateUser;

import Com.TECMIS.MySqlCon;
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

public class UpdateTechOffice extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtTecId;
    private JButton submitButton;
    private JTextField txtDepId;
    private JTextField txtFname;
    private JTextField txtLname;
    private JTextField txtAd1;
    private JTextField txtAd2;
    private JTextField txtEmail;
    private JPasswordField txtPwd;
    private JButton profileButton;
    private JButton dateButton;
    private JPanel NewTecPnl;
    private JButton submitButton1;
    private JButton clearButton;
    private JPanel UpdateTecOffiPnl;
    private JButton backButton;
    private JLabel JlaDisply;

    private Date selectedDate;
    private String formattedDate;
    private File proPic;
    private String gender;

    private String UserId;
    private String DepId;
    private String fname;
    private String lname;
    private String Ad1;
    private String Ad2;
    private String email;
    private String password;
    private byte[] imageData;













    public void UpdateTecOffMethod(){
        add(UpdateTecOffiPnl);
        setVisible(true);
        setSize(800,600);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        NewTecPnl.setVisible(false);



        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserId = txtTecId.getText();
                NewTecPnl.setVisible(true);

                String sql = "SELECT * FROM Technical_officer WHERE User_id = ?";

                try{
                    PreparedStatement ss = conn.prepareStatement(sql);
                    ss.setString(1,UserId);
                    ResultSet rs = ss.executeQuery();

                    while(rs.next()){
                        DepId = rs.getString("Dep_id");
                        fname = rs.getString("FName");
                        lname = rs.getString("LName");
                        Ad1 = rs.getString("Address_L1");
                        Ad2 = rs.getString("Address_L2");
                        email = rs.getString("Email");
                        password = rs.getString("Password");


                    }
                    txtDepId.setText(DepId);
                    txtFname.setText(fname);
                    txtLname.setText(lname);
                    txtAd1.setText(Ad1);
                    txtAd2.setText(Ad2);
                    txtEmail.setText(email);
                    txtPwd.setText(password);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        dateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new JFrame("Choose Date");
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
                DepId = txtDepId.getText();
                fname = txtFname.getText();
                lname = txtLname.getText();
                Ad1 = txtAd1.getText();
                Ad2 = txtAd2.getText();
                email = txtEmail.getText();
                password = String.valueOf(txtPwd.getPassword());

                String up = "Update Technical_officer " +
                        "SET Dep_id = ?, FName = ?, LName = ?, Address_L1 = ?, Address_L2 = ?, DOB = ?, Email = ?, Pro_pic = ?, Password = ? " +
                        "WHERE User_id = ?";

                try{
                    imageData = Files.readAllBytes(proPic.toPath());
                    PreparedStatement sss = conn.prepareStatement(up);
                    sss.setString(1,DepId);
                    sss.setString(2,fname);
                    sss.setString(3,lname);
                    sss.setString(4,Ad1);
                    sss.setString(5,Ad2);
                    sss.setString(6,formattedDate);
                    sss.setString(7,email);
                    sss.setBytes(8,imageData);
                    sss.setString(9,password);
                    sss.setString(10,UserId);

                    sss.executeUpdate();

                    JlaDisply.setText("Database successfully updated !");

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
                txtDepId.setText("");
                txtFname.setText("");
                txtLname.setText("");
                txtAd1.setText("");
                txtAd2.setText("");
                txtEmail.setText("");
                txtPwd.setText("");
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
