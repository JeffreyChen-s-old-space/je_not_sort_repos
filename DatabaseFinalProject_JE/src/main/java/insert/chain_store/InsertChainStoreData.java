package insert.chain_store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertChainStoreData {
    static final String jdbcDriver = "org.mariadb.jdbc.Driver";
    static final String dbURL = "jdbc:mariadb://140.127.74.170/410877027";
    static final String user = "410877027";
    static final String password = "410877027";

    static ArrayList<Integer> storeIdArrayList = new ArrayList<>();
    static ArrayList<String> storeNameArrayList = new ArrayList<>();
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
                storeIdArrayList.add(temp);

            for (temp = 1; temp < 6; temp++)
                storeNameArrayList.add("沃爾瑪分店" + temp);

            for (temp = 0; temp < 5; temp++) {
                PreparedStatement preparedStatement =
                        connection.prepareStatement("REPLACE INTO " +
                                "chain_store(store_id,company_id,store_name) " +
                                "VALUES (?,?,?)");
                preparedStatement.setInt(1, storeIdArrayList.get(temp));
                preparedStatement.setInt(2, 1);
                preparedStatement.setString(3, storeNameArrayList.get(temp));
                preparedStatement.executeUpdate();
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
