package TECMIS.Admin.DashBord.RemoveUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveLecture extends JFrame {
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel RemovelecturePnl;
    private JTextField textField1;
    private JButton submitButton;
    private JPanel succsPnl;
    private JButton backButton;



    public void RemoveLectuMethod(){

        add(RemovelecturePnl);
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
