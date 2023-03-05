package TECMIS;

import java.sql.*;

public class MySqlCon {
    public void MysqlMethod() {
        String url = "jdbc:mysql://localhost:3306/LMSDB";
        String username = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Student")) {

            while (rs.next()) {
                System.out.println(rs.getString("Fname"));
            }

        } catch (SQLException e) {
            System.out.println("Connection failed !");
            e.printStackTrace();
        }
    }
}
