package TECMIS.Lecturer.UpdateCourseMaterials;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;

public class UpdateCourseMaterials extends Lecturer{
    private Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlUploadCM;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCMid;
    private JTextField txtCMName;
    private JTextField txtAdminId;
    private JButton backButton;
    private JButton updateButton;
    private JButton clearButton;
    private JTextField txtCID;
    private JLabel lblSuccess2;
    private JButton clickHereButton;
    private JButton searchButton;
    private JTable table1;
    private JScrollPane tablePane;
    private JLabel lblCMid;
    private JLabel lblCMName;
    private JLabel lblUp;
    private JTextField txtLevel;
    private JTextField txtSem;
    private String CID;
    private String CourseName;
    private String AdminId;
    private int level;
    private int semester;
    private int Credit;
    private String User_id;
    private String CM_id;
    private File material;
    private String CM_Name;

    public UpdateCourseMaterials() {

    }

    public void UpdateCourse(){
        add(pnlUploadCM);
        setSize(750, 500);
        setTitle("Update Course Materials");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        lblCMid.setVisible(false);
        lblCMName.setVisible(false);
        lblUp.setVisible(false);
        clickHereButton.setVisible(false);
        txtCMid.setVisible(false);
        txtCMName.setVisible(false);

        User_id = getUserId();

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

        clickHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser mt = new JFileChooser();
                    int result = mt.showOpenDialog(null);
                    if(result == JFileChooser.APPROVE_OPTION){
                        material = mt.getSelectedFile();
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CID = txtCID.getText();

                String srch = "SELECT C_Material_Id, Material_Name FROM Course_materials WHERE (Lecturer_id = ?) AND (Course_id = ?)";

                try(PreparedStatement pstmt = conn.prepareStatement(srch)) {
                    pstmt.setString(1,User_id);
                    pstmt.setString(2, CID);
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
                lblCMid.setVisible(true);
                lblCMName.setVisible(true);
                lblUp.setVisible(true);
                clickHereButton.setVisible(true);
                txtCMid.setVisible(true);
                txtCMName.setVisible(true);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                CID = txtCID.getText();
                CM_id = txtCMid.getText();
                CM_Name = txtCMName.getText();

                String updCD = "UPDATE Course_Materials " +
                        "SET Material_Name = ? , File_ = ? " +
                        "WHERE C_Material_Id = ? ";

                try (PreparedStatement stmt = conn.prepareStatement(updCD)) {
                    byte[] FileData = Files.readAllBytes(material.toPath());

                    stmt.setString(1, CM_Name);
                    stmt.setBytes(2, FileData);
                    stmt.setString(3, CM_id);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows Updated");

                    lblSuccess2.setText(" Course material successfully updated ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCID.setText("");
                txtCMName.setText("");

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);
                tableModel.setColumnCount(0);
                table1.setModel(tableModel);

                txtCMid.setText("");

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Lecturer lecBack = new Lecturer();
                lecBack.setVisible(true);
                setVisible(false);
                lecBack.methodLecturer();

                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


    }
}
