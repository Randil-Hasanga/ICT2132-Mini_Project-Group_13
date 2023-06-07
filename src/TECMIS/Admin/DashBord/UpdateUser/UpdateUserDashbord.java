package TECMIS.Admin.DashBord.UpdateUser;

import TECMIS.Admin.DashBord.Dashbord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
