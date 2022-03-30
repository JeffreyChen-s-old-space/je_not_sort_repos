package insert.necessary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class InsertNecessaryData {
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
            for(temp = 101;temp < 126;temp++)
                createData(
                        connection,
                        temp-100,
                        temp,
                        "日用品" + (temp - 100),
                        random.nextInt(500)+1,
                        random.nextInt(1000)+1,
                        random.nextInt(500)+1
                        );

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void createData(Connection connection,
                                   int necessary_id,
                                   int commodity_id,
                                   String necessary_name,
                                   int necessary_amount,
                                   int necessary_sale_amount,
                                   int necessary_price) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("REPLACE INTO " +
                        "necessary(necessary_id," +
                        "commodity_id,necessary_name," +
                        "necessary_amount," +
                        "necessary_sale_amount," +
                        "necessary_price) " +
                        "VALUES (?,?,?,?,?,?)");
        preparedStatement.setInt(1,necessary_id);
        preparedStatement.setInt(2,commodity_id);
        preparedStatement.setString(3,necessary_name);
        preparedStatement.setInt(4,necessary_amount);
        preparedStatement.setInt(5,necessary_sale_amount);
        preparedStatement.setInt(6,necessary_price);
        preparedStatement.executeUpdate();
    }

}
