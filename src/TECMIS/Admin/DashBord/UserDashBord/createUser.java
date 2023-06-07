package TECMIS.Admin.DashBord.UserDashBord;

import TECMIS.Admin.DashBord.Dashbord;
import TECMIS.Admin.DashBord.UserDashBord.CreateUser.createAdmin;
import TECMIS.Admin.DashBord.UserDashBord.CreateUser.createLecture;
import TECMIS.Admin.DashBord.UserDashBord.CreateUser.createStudent;
import TECMIS.Admin.DashBord.UserDashBord.CreateUser.createTechnicalOffice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class createUser extends JFrame{
    private JPanel UserPanel;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel pnlPic;
    private JLabel lblPic;
    private JButton logOutButton;
    private JButton adminButton;
    private JButton technicalOfficerButton;
    private JButton lecturerButton;
    private JButton studentButton;
    private JButton backButton;


    public createUser() {



    }

    public void methodUser(){

        add(UserPanel);
        setVisible(true);
        setSize(750,500);
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashbord back = new Dashbord();
                back.setVisible(true);
                setVisible(false);
                back.methodAdmin();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAdmin admin = new createAdmin();
                admin.methodAdmin();
                admin.setVisible(true);
                setVisible(false);

            }
        });

        technicalOfficerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTechnicalOffice admin = new createTechnicalOffice();
                admin.methodTecOffi();
                admin.setVisible(true);
                setVisible(false);
            }
        });

        lecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createLecture lecturer = new createLecture();
                lecturer.setVisible(true);
                setVisible(false);
                lecturer.methodCreateLec();

            }
        });

        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createStudent student = new createStudent();
                student.setVisible(true);
                setVisible(false);
                student.methodCreateStudent();
            }
        });


    }

}
