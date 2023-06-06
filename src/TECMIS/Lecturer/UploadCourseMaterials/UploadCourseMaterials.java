package TECMIS.Lecturer.UploadCourseMaterials;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadCourseMaterials extends Lecturer{

    private Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlUploadCM;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCMname;
    private JButton backButton;
    private JButton uploadButton;
    private JButton clearButton;
    private JTextField txtCID;
    private JLabel lblSuccess2;
    private JButton clickHereButton;
    private JTextField txtCM_id;
    private String userId;
    private String acc;
    private String CID;
    private String CM_Name;
    private String CM_id;
    private File Material;


    public void upCourseMaterials(){
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUploadCM);
        setSize(750, 500);
        setTitle("Upload Course Materials");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }else {
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
                        Material = mt.getSelectedFile();
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCID.setText("");
                txtCMname.setText("");
                txtCM_id.setText("");
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


        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CID = txtCID.getText();
                CM_Name = txtCMname.getText();
                CM_id = txtCM_id.getText();

                String upCD = "INSERT INTO Course_materials (C_Material_Id,Course_id,Material_Name,Lecturer_id,File_) VALUES (?,?,?,?,?)";

                try(PreparedStatement stmt = conn.prepareStatement(upCD)){

                    byte[] FileData = Files.readAllBytes(Material.toPath());

                    stmt.setString(1,CM_id);
                    stmt.setString(2, CID);
                    stmt.setString(3,CM_Name);
                    stmt.setString(4,userId);
                    stmt.setBytes(5,FileData);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess2.setText(" New course material successfully added to database ! ");

                    stmt.close();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
