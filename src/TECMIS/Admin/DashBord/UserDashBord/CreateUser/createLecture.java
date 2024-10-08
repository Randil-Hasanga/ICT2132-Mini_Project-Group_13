package TECMIS.Admin.DashBord.UserDashBord.CreateUser;

import TECMIS.Admin.DashBord.UserDashBord.createUser;
import TECMIS.MySqlCon;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class createLecture extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtUid;
    private JTextField txtFname;
    private JTextField txtLname;
    private JComboBox dropGender;
    private JTextField txtAd1;
    private JTextField txtAd2;
    private JTextField txtEmail;
    private JTextField txtPwd;
    private JTextField txtPos;
    private JButton submitButton;
    private JButton clearButton;
    private JButton chooseDateButton;
    private JButton chooseFileButton;
    private JButton backButton;
    private JPanel createLec;
    private JLabel suc;

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
    private String pos;



    public void methodCreateLec(){
    add(createLec);
    setVisible(true);
    setSize(750,600);
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

        dropGender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gender = dropGender.getSelectedItem().toString();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFname.setText("");
                txtLname.setText("");
                txtAd1.setText("");
                txtAd2.setText("");
                txtUid.setText("");
                txtEmail.setText("");
                txtPwd.setText("");
                txtPos.setText("");
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

        chooseFileButton.addActionListener(new ActionListener() {
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
            });        }
    });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user_id = txtUid.getText();
                fname = txtFname.getText();
                lname = txtLname.getText();
                Ad1 = txtAd1.getText();
                Ad2 = txtAd2.getText();
                email = txtEmail.getText();
                password = txtPwd.getText();
                pos = txtPos.getText();



                String sql = "INSERT INTO Lecturer " +
                        "(User_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password,Position ) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

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
                    pstmt.setString(11,pos);

                    pstmt.executeUpdate();

                    suc.setText("Successful Inserted data!...");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
}
}
