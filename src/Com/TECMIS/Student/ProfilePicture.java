package Com.TECMIS.Student;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfilePicture extends Student{
    private JPanel pnlProfile;
    private JButton chooseImageButton;
    private JButton backButton;
    private JButton updateButton;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private File proPic;

    private String user_id = Student.getUserId();;
    private String acc = Student.getAcc();

    

    public void upProfilePicture() {
        add(pnlProfile);
        setSize(700,500);
        setTitle("Update profile");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JFileChooser picOpen = new JFileChooser();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student stuBack = new Student();
                stuBack.setVisible(true);
                setVisible(false);
                stuBack.methodStudent();

            }
        });

        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser picOpen = new JFileChooser();
                    int result = picOpen.showOpenDialog(null);
                    if(result == JFileChooser.APPROVE_OPTION){
                        proPic = picOpen.getSelectedFile();
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "UPDATE Student SET Pro_pic=? WHERE User_id=?";
                DatabaseMetaData MySqlCon = null;
                try (PreparedStatement pstmt = MySqlCon.getConnection().prepareStatement(sql)) {
                    byte[] imageData = Files.readAllBytes(proPic.toPath());
                    pstmt.setBytes(1, imageData);
                    pstmt.setString(2, user_id);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Profile picture updated successfully.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    
    }

}
