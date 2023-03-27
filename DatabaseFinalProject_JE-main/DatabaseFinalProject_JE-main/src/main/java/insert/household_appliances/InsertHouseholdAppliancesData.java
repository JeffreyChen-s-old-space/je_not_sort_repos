package insert.household_appliances;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class InsertHouseholdAppliancesData {
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
            for (temp = 76; temp < 101; temp++)
                createData(
                        connection,
                        temp - 75,
                        temp,
                        "家電" + (temp - 75),
                        random.nextInt(200) + 1,
                        random.nextInt(400) + 1,
                        random.nextInt(3000) + 500
                );

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void createData(Connection connection,
                                   int household_appliances_id,
                                   int commodity_id,
                                   String household_appliances_name,
                                   int household_appliances_amount,
                                   int household_appliances_sale_amount,
                                   int household_appliances_price) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("REPLACE INTO " +
                        "household_appliances(" +
                        "household_appliances_id," +
                        "commodity_id," +
                        "household_appliances_name," +
                        "household_appliances_amount," +
                        "household_appliances_sale_amount," +
                        "household_appliances_price) " +
                        "VALUES (?,?,?,?,?,?)");
        preparedStatement.setInt(1, household_appliances_id);
        preparedStatement.setInt(2, commodity_id);
        preparedStatement.setString(3, household_appliances_name);
        preparedStatement.setInt(4, household_appliances_amount);
        preparedStatement.setInt(5, household_appliances_sale_amount);
        preparedStatement.setInt(6, household_appliances_price);
        preparedStatement.executeUpdate();
    }

}
