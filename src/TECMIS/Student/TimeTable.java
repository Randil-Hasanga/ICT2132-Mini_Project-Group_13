package TECMIS.Student;

import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TimeTable extends Student{

    Connection conn = MySqlCon.MysqlMethod();

    private JPanel pnlTimeTable;
    private JButton searchButton;
    private JButton backButton;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JLabel lblDep;
    private JTextField txtDepId;
    private JLabel lblImg;
    private JButton btnclear;
    private String UserId;

    private String acc;


    String TimeTable;




    public void viewTimeTable() {
        acc = User.getAcc();

        UserId = User.getUserId();

        add(pnlTimeTable);
        setSize(600, 600);
        setTitle("Time Table");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student stuBack = new Student();
                stuBack.setVisible(true);
                setVisible(false);
                stuBack.methodStudent();

            }
        });

        btnclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDepId.setText("");

            }
        });








    }
}
