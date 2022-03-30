package insert.food;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class InsertFoodData {
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
            for (temp = 1; temp < 26; temp++)
                createData(
                        connection,
                        temp,
                        temp,
                        "食品" + temp,
                        random.nextInt(3000) + 1,
                        random.nextInt(1000) + 1,
                        random.nextInt(200) + 50
                );

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void createData(Connection connection,
                                   int food_id,
                                   int commodity_id,
                                   String food_name,
                                   int food_amount,
                                   int food_sale_amount,
                                   int food_price) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("REPLACE INTO " +
                        "food(" +
                        "food_id," +
                        "commodity_id," +
                        "food_name," +
                        "food_amount," +
                        "food_sale_amount," +
                        "food_price) " +
                        "VALUES (?,?,?,?,?,?)");
        preparedStatement.setInt(1, food_id);
        preparedStatement.setInt(2, commodity_id);
        preparedStatement.setString(3, food_name);
        preparedStatement.setInt(4, food_amount);
        preparedStatement.setInt(5, food_sale_amount);
        preparedStatement.setInt(6, food_price);
        preparedStatement.executeUpdate();
    }

}
