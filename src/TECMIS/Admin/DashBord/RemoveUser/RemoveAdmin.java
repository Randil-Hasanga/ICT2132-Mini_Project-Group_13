package TECMIS.Admin.DashBord.RemoveUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveAdmin extends JFrame{
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField textAdminId;
    private JButton submitButton;
    private JButton clearButton;
    private JButton backButton;
    private JPanel succ;
    private JPanel RemAddPnl;



    public void RemoveAdminMethod(){

        add(RemAddPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);






        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveUserDashbord RemAdmin = new RemoveUserDashbord();
                RemAdmin.RemoveUserMethod();
                RemAdmin.setVisible(true);
                setVisible(false);
            }

        });
    }
}
