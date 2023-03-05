import TECMIS.MySqlCon;
import TECMIS.User;

public class Main {
    public static void main(String[] args) {
        MySqlCon con = new MySqlCon();
        con.MysqlMethod();

        User user = new User();
    }

}