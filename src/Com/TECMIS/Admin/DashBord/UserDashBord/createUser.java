package Com.TECMIS.Admin.DashBord.UserDashBord;

import Com.TECMIS.Admin.DashBord.Dashbord;
import Com.TECMIS.Admin.DashBord.UserDashBord.CreateUser.createAdmin;
import Com.TECMIS.Admin.DashBord.UserDashBord.CreateUser.createLecture;
import Com.TECMIS.Admin.DashBord.UserDashBord.CreateUser.createStudent;
import Com.TECMIS.Admin.DashBord.UserDashBord.CreateUser.createTechnicalOffice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
