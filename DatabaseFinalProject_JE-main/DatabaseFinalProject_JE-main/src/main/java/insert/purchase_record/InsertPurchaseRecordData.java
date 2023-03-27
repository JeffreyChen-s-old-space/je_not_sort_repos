package insert.purchase_record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class InsertPurchaseRecordData {
    static final String jdbcDriver = "org.mariadb.jdbc.Driver";
    static final String dbURL = "jdbc:mariadb://140.127.74.170/410877027";
    static final String user = "410877027";
    static final String password = "410877027";
    static Random random = new Random();
    static int temp = 1;

    public static void main(String[] argv) {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(dbURL, user, password);
            for (int customer = 1; customer < 26; customer++) {
                createData(
                        connection,
                        customer + 175,
                        customer,
                        random.nextInt(125) + 1,
                        random.nextInt(20) + 1
                );
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void createData(Connection connection,
                                   int purchase_id,
                                   int shop_order_id,
                                   int commodity_id,
                                   int purchase_amount
    ) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("REPLACE INTO " +
                        "purchase_record(" +
                        "purchase_id," +
                        "shop_order_id," +
                        "commodity_id," +
                        "purchase_amount)" +
                        "VALUES (?,?,?,?)");
        preparedStatement.setInt(1, purchase_id);
        preparedStatement.setInt(2, shop_order_id);
        preparedStatement.setInt(3, commodity_id);
        preparedStatement.setInt(4, purchase_amount);
        preparedStatement.executeUpdate();
    }

}
