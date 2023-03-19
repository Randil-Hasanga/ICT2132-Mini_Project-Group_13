package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.MySqlCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Timetable extends TechnicalOfficer {

    Connection conn = MySqlCon.MysqlMethod();
    private JLabel lblTimetableID;
    private JComboBox TimetableBox;
    private JButton BtnSearch;
    private JTable tableTimetable;
    private JButton BtnBack;
    private JPanel PnlTimetable;
    private JTextField txtDepID;
    private JLabel lblDepID;
    private JTextArea facultyOfTechnologyManagementTextArea;

    private int T_table_ID;
    private int DepID;



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
            }
        });

        BtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                T_table_ID = TimetableBox.getModel().getSize();
                DepID = Integer.parseInt(txtDepID.getText());
                String TimeTable = "SELECT * FROM TimeTable,Department WHERE T_table_ID = ? AND Dep_id = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(TimeTable)) {
                    pstmt.setInt(1,T_table_ID);
                    pstmt.setString(2, String.valueOf(DepID));
                    ResultSet rs = pstmt.executeQuery();

                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableTimetable.setModel(tableModel);

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
