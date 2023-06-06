package TECMIS.Lecturer.RemoveCourseMaterials;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;
import TECMIS.Student.Student;
import TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class RemoveCourseMaterial extends User{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCID;
    private JButton deleteButton;
    private JButton backButton;
    private JButton clearButton;
    private JPanel pnlRemoveCourse;
    private JLabel lblDisplay;
    private JTextField txtCM_id;
    private JButton searchButton;
    private JTable table1;
    private String CID;
    private String acc;
    private String User;
    private String CM_Id;



    public void RemoveCourse(){

        acc = getAcc();
        User = getUserId();

        add(pnlRemoveCourse);
        setSize(750, 500);
        setTitle("Remove Course");
        setLocationRelativeTo(null);
        lblDisplay.setVisible(false);
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

        searchButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                CID = txtCID.getText();
                String srch = "SELECT C_Material_Id, Material_Name FROM Course_materials WHERE Lecturer_id = ? AND Course_id = ?";

                try(PreparedStatement pstmt = conn.prepareStatement(srch)) {
                    pstmt.setString(1,User);
                    pstmt.setString(2,CID);
                    ResultSet rs = pstmt.executeQuery();

                    DefaultTableModel tableModel2 = new DefaultTableModel();
                    table1.setModel(tableModel2);

                    ResultSetMetaData rsmd2 = rs.getMetaData();
                    int columntCount2 = rsmd2.getColumnCount();

                    for (int i = 1; i <= columntCount2; i++) {
                        tableModel2.addColumn(rsmd2.getColumnName(i));
                    }

                    while (rs.next()) {
                        Object[] rowData = new Object[columntCount2];
                        for (int i = 1; i <= columntCount2; i++) {
                            rowData[i - 1] = rs.getObject(i);
                        }
                        tableModel2.addRow(rowData);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CID = txtCID.getText();
                CM_Id = txtCM_id.getText();


                String sql = "DELETE FROM Course_Materials WHERE C_Material_Id = ? ";

                try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                    pstmt.setString(1,CM_Id);
                    int rows = pstmt.executeUpdate();

                    lblDisplay.setVisible(true);
                    lblDisplay.setText(rows + " row(s) deleted.");



                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acc.equals("lecturer")) {
                    Lecturer lecBack = new Lecturer();
                    lecBack.setVisible(true);
                    setVisible(false);
                    lecBack.methodLecturer();
                }
                else if(acc.equals("student")){
                    Student stuBack = new Student();
                    stuBack.setVisible(true);
                    setVisible(false);
                    stuBack.methodStudent();
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCID.setText("");

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);
                tableModel.setColumnCount(0);
                table1.setModel(tableModel);

                txtCM_id.setText("");

            }
        });



    }
}
