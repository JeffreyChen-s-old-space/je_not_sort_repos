import gui.main.MainForm;

import javax.swing.*;
import java.sql.*;

public class Main {

    static final String jdbcDriver = "org.mariadb.jdbc.Driver";
    static final String dbURL = "jdbc:mariadb://140.127.74.170/410877027";
    static final String user = "410877027";
    static final String password = "410877027";
    static Connection connection;

    public static void main(String[] argv) {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            JOptionPane.showMessageDialog(null, "無法連線至資料庫", "無法連線至資料庫", JOptionPane.ERROR_MESSAGE);
        }
        new MainForm("主介面", connection);
    }

}
