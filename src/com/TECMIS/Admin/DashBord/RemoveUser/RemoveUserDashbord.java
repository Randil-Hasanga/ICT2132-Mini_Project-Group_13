package com.TECMIS.Admin.DashBord.RemoveUser;

import com.TECMIS.Admin.DashBord.Dashbord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveUserDashbord extends JFrame {
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JButton adminButton;
    private JButton technicalOfficerButton;
    private JButton lecturerButton;
    private JButton studentButton;
    private JPanel removeUserDashbordPnl;
    private JButton backButton;




    public void RemoveUserMethod(){

        add(removeUserDashbordPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               RemoveAdmin RemAdmin = new RemoveAdmin();
               RemAdmin.RemoveAdminMethod();
               RemAdmin.setVisible(true);
               setVisible(false);
            }
        });


        technicalOfficerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveTechnicalOffice RemTech = new RemoveTechnicalOffice();
                RemTech.RemoveTechOffMethod();
                RemTech.setVisible(true);
                setVisible(false);
            }
        });


        lecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveLecture RemLect = new RemoveLecture();
                RemLect.RemoveLectuMethod();
                RemLect.setVisible(true);
                setVisible(false);
            }
        });


        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RevomeStudent RemStude = new RevomeStudent();
                RemStude.RemoveStudenMethod();
                RemStude.setVisible(true);
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
