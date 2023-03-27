package insert.store_commodity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertStoreCommodityData {
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

            for (temp =101; temp < 106; temp++) {
                createData(connection, temp, "日用品", 1);
            }

            for (temp = 106; temp < 111; temp++) {
                createData(connection, temp, "日用品", 2);
            }

            for (temp = 111; temp < 116; temp++) {
                createData(connection, temp, "日用品", 3);
            }

            for (temp = 116; temp < 121; temp++) {
                createData(connection, temp, "日用品", 4);
            }

            for (temp = 121; temp < 126; temp++) {
                createData(connection, temp, "日用品", 5);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void createData(Connection connection,int commodity_id,String type_name,int store_id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("REPLACE INTO " +
                        "store_commodity(commodity_id,type_name,store_id) " +
                        "VALUES (?,?,?)");
        preparedStatement.setInt(1,commodity_id);
        preparedStatement.setString(2,type_name);
        preparedStatement.setInt(3,store_id);
        preparedStatement.executeUpdate();
    }

}
