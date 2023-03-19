package TECMIS.Admin.DashBord.UpdateUser;

import TECMIS.Admin.DashBord.Dashbord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserDashbord extends JFrame {
    private JPanel UserUpdatePanel;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JButton adminButton;
    private JButton technicalOfficerButton;
    private JButton lecturerButton;
    private JButton studentButton;
    private JPanel UpdateUserPnl;
    private JButton backButton;




    public void UpdateUserMethod(){
        add(UpdateUserPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateAdmin UpAdmin = new UpdateAdmin();
                UpAdmin.UpdateAdminMethod();
                UpAdmin.setVisible(true);
                setVisible(false);
            }
        });

        technicalOfficerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateTechOffice UpTecOff = new UpdateTechOffice();
                UpTecOff.UpdateTecOffMethod();
                UpTecOff.setVisible(true);
                setVisible(false);
            }
        });


        lecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateLecture UpLect = new UpdateLecture();
                UpLect.UpdateLectureMethod();
                UpLect.setVisible(true);
                setVisible(false);
            }
        });


        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateStudent UpStudent = new UpdateStudent();
                UpStudent.UpdateStudentMethod();
                UpStudent.setVisible(true);
                setVisible(false);
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
    }


}
