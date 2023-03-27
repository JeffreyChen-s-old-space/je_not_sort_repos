package select;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

public class TestSelectCustomerBuysFood {

    static final String jdbcDriver = "org.mariadb.jdbc.Driver";
    static final String dbURL = "jdbc:mariadb://140.127.74.170/410877027";
    static final String user = "410877027";
    static final String password = "410877027";
    static Connection connection;

    @BeforeClass
    public static void init() {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testSelectCustomerBuysFood() throws SQLException {
        String sql = "SELECT food_name,purchase_amount " +
                "FROM customer,shop_order,purchase_record,food " +
                "WHERE shop_order.customer_id = customer.customer_id " +
                "AND customer.customer_id = ? " +
                "AND shop_order.shop_order_id = purchase_record.shop_order_id " +
                "AND food.commodity_id = purchase_record.commodity_id";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("food_name"));
            System.out.println(resultSet.getInt("purchase_amount"));
        }
    }

}
