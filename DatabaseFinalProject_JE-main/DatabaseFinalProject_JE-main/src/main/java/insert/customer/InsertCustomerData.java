package insert.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class InsertCustomerData {
    static final String jdbcDriver = "org.mariadb.jdbc.Driver";
    static final String dbURL = "jdbc:mariadb://140.127.74.170/410877027";
    static final String user = "410877027";
    static final String password = "410877027";
    static int temp = 1;

    public static void main(String[] argv) {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {

            Connection connection = DriverManager.getConnection(dbURL, user, password);
            for (temp = 1; temp < 6; temp++)
                createData(
                        connection,
                        temp,
                        1,
                        "客戶" + temp,
                        "0900880" + temp,
                        "燕師大-燕窩區-第" + temp + "號"
                );
            for (temp = 6; temp < 11; temp++)
                createData(
                        connection,
                        temp,
                        2,
                        "客戶" + temp,
                        "0900880" + temp,
                        "燕師大-燕窩區-第" + temp + "號"
                );
            for (temp = 11; temp < 16; temp++)
                createData(
                        connection,
                        temp,
                        3,
                        "客戶" + temp,
                        "0900880" + temp,
                        "燕師大-燕窩區-第" + temp + "號"
                );
            for (temp = 16; temp < 21; temp++)
                createData(
                        connection,
                        temp,
                        4,
                        "客戶" + temp,
                        "0900880" + temp,
                        "燕師大-燕窩區-第" + temp + "號"
                );
            for (temp = 21; temp < 26; temp++)
                createData(
                        connection,
                        temp,
                        5,
                        "客戶" + temp,
                        "0900880" + temp,
                        "燕師大-燕窩區-第" + temp + "號"
                );
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void createData(Connection connection,
                                   int customer_id,
                                   int store_id,
                                   String customer_name,
                                   String customer_telphone,
                                   String customer_address
                                   ) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("REPLACE INTO " +
                        "customer(" +
                        "customer_id," +
                        "store_id," +
                        "customer_name," +
                        "customer_telphone," +
                        "customer_address)" +
                        "VALUES (?,?,?,?,?)");
        preparedStatement.setInt(1, customer_id);
        preparedStatement.setInt(2, store_id);
        preparedStatement.setString(3, customer_name);
        preparedStatement.setString(4, customer_telphone);
        preparedStatement.setString(5, customer_address);
        preparedStatement.executeUpdate();
    }

}
