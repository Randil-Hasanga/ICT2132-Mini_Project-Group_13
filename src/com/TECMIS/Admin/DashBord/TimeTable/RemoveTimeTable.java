package com.TECMIS.Admin.DashBord.TimeTable;

import com.TECMIS.Admin.DashBord.Dashbord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveTimeTable extends JFrame{
    private JPanel ReTiTaPnl;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField TiTaId;
    private JButton backButton;
    private JButton clearButton;
    private JButton submitButton;
    private JPanel succ;
    private String TTId;



public void RemoveTimeTableMethod() {

    add(ReTiTaPnl);
    setVisible(true);
    setSize(750,500);
    setTitle("LMS Software");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    succ.setVisible(false);

    TTId = TiTaId.getText();


    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dashbord back = new Dashbord();
            back.setVisible(true);
            setVisible(false);
            back.methodAdmin();
        }
    });
    clearButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TiTaId.setText("");
        }
    });
}
}
