package TECMIS;

import java.sql.*;

public class MySqlCon {
    public static Connection MysqlMethod() {
        String url = "jdbc:mysql://localhost:3306/LMSDB";
        String username = "root";
        String password = "";
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
            System.out.println("done");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
