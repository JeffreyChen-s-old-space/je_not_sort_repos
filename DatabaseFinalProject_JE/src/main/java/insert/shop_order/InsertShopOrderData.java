package insert.shop_order;

import java.sql.*;
import java.util.Random;

public class InsertShopOrderData {
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
            long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            Connection connection = DriverManager.getConnection(dbURL, user, password);
            for (temp = 1; temp < 26; temp++)
                createData(
                        connection,
                        temp,
                        timestamp,
                        temp
                );

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void createData(Connection connection,
                                   int shop_order_id,
                                   Timestamp shop_order_time,
                                   int customer_id
    ) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("REPLACE INTO " +
                        "shop_order(" +
                        "shop_order_id," +
                        "shop_order_time," +
                        "customer_id)" +
                        "VALUES (?,?,?)");
        preparedStatement.setInt(1, shop_order_id);
        preparedStatement.setTimestamp(2, shop_order_time);
        preparedStatement.setInt(3, customer_id);
        preparedStatement.executeUpdate();
    }

}
