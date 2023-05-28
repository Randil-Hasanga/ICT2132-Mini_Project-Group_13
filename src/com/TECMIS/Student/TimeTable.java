package com.TECMIS.Student;

import com.TECMIS.MySqlCon;
import com.TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TimeTable extends Student{

    Connection conn = MySqlCon.MysqlMethod();

    private JPanel pnlTimeTable;
    private JComboBox subDrop;
    private JButton searchButton;
    private JButton clearButton;
    private JTable tbltimeTable;
    private JButton backButton;
    private JTextArea facultyOfTechnologyManagementTextArea;

    private String UserId;

    private String acc;

    private int T_table_ID;
    String TimeTable;

    public void viewTimeTable() {
        acc = User.getAcc();

        UserId = User.getUserId();

        add(pnlTimeTable);
        setSize(600, 600);
        setTitle("Time Table");
        tbltimeTable.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                try (PreparedStatement pstmt = conn.prepareStatement(TimeTable)) {
                    pstmt.setInt(1,T_table_ID);
                    ResultSet rs = pstmt.executeQuery();

                    DefaultTableModel tableModel = new DefaultTableModel();
                    tbltimeTable.setModel(tableModel);

                    tableModel.addColumn("");
                    tableModel.addColumn("");

                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columntCount = rsmd.getColumnCount();

                    while(rs.next()){
                        for(int i = 1; i <= columntCount; i++){
                            Object[] rowData = new Object[2];
                            rowData[0] = rsmd.getColumnName(i);
                            rowData[1] = rs.getObject(i);
                            tableModel.addRow(rowData);
                        }
                    }
                    rs.close();
                    pstmt.close();
                    conn.close();


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });





    }
}
