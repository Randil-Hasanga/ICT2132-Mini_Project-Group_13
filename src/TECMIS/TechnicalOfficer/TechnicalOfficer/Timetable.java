package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Timetable extends TechnicalOfficer {

    Connection conn = MySqlCon.MysqlMethod();
    private JComboBox TimetableBox;
    private JButton BtnSearch;
    private JTable tableTimetable;
    private JButton BtnBack;
    private JPanel PnlTimetable;
    private JTextField txtDepID;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JLabel lblTimetableID;
    private JTextField textFieldT_tableID;
    private JLabel lblLevel;
    private JTextField textFieldLevel;
    private JButton btnClear;


    private int T_id;
    private int level;
    private int semester;




    public void viewTimetable() {

        add(PnlTimetable);
        setSize(600, 600);
        setTitle(" Time Table ");
        tableTimetable.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        BtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficer TOBack = new TechnicalOfficer();
                TOBack.setVisible(true);
                setVisible(false);
                TOBack.methodTechnicalOfficer();

                try{

                    conn.close();
                    System.out.println(" Connection is Successfully Closed ");

                } catch (SQLException ex) {
                    System.out.println(" Unsuccessfully Connection Closed "+ex.getMessage());
                }
            }
        });

        BtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                T_id = Integer.parseInt(textFieldT_tableID.getText());
                level = Integer.parseInt(textFieldLevel.getText());






            }

        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  textFieldT_tableID.setText(" ");
                  textFieldLevel.setText(" ");
            }
        });



    }



}
