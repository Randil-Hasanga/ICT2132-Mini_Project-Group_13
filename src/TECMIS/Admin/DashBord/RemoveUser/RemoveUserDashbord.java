package TECMIS.Admin.DashBord.RemoveUser;

import TECMIS.Admin.DashBord.Dashbord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
