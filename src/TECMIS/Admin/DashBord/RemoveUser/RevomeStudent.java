package TECMIS.Admin.DashBord.RemoveUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RevomeStudent extends JFrame{
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField textField1;
    private JButton submitButton;
    private JPanel RemoveStudentPnl;
    private JPanel succsPnl;
    private JButton backButton;


    public void RemoveStudenMethod(){
        add(RemoveStudentPnl);
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
