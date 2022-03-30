package insert.entertainment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class InsertEntertainmentData {
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
            for (temp = 51; temp < 76; temp++)
                createData(
                        connection,
                        temp - 50,
                        temp,
                        "娛樂用品" + (temp- 50) ,
                        random.nextInt(1000) + 1,
                        random.nextInt(400) + 1,
                        random.nextInt(800) + 400
                );

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void createData(Connection connection,
                                   int entertainment_id,
                                   int commodity_id,
                                   String entertainment_name,
                                   int entertainment_amount,
                                   int entertainment_sale_amount,
                                   int entertainment_price) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("REPLACE INTO " +
                        "entertainment(" +
                        "entertainment_id," +
                        "commodity_id," +
                        "entertainment_name," +
                        "entertainment_amount," +
                        "entertainment_sale_amount," +
                        "entertainment_price) " +
                        "VALUES (?,?,?,?,?,?)");
        preparedStatement.setInt(1, entertainment_id);
        preparedStatement.setInt(2, commodity_id);
        preparedStatement.setString(3, entertainment_name);
        preparedStatement.setInt(4, entertainment_amount);
        preparedStatement.setInt(5, entertainment_sale_amount);
        preparedStatement.setInt(6, entertainment_price);
        preparedStatement.executeUpdate();
    }

}
