package create.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    static final String jdbcDriver = "org.mariadb.jdbc.Driver";
    static final String dbURL = "jdbc:mariadb://140.127.74.170/410877027";
    static final String user = "410877027";
    static final String password = "410877027";

    public static void main(String[] argv) {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(dbURL, user, password);
            Statement statement = connection.createStatement();

            String sqlCommand = "CREATE TABLE IF NOT EXISTS company(" +
                    "company_id INTEGER  PRIMARY KEY," +
                    "company_name VARCHAR(255)" +
                    ")";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "CREATE TABLE IF NOT EXISTS chain_store(" +
                    "store_id INTEGER PRIMARY KEY," +
                    "company_id INTEGER," +
                    "store_name VARCHAR(255)," +
                    "FOREIGN KEY(company_id) REFERENCES company(company_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "CREATE TABLE IF NOT EXISTS store_commodity(" +
                    "commodity_id INTEGER PRIMARY KEY ," +
                    "type_name VARCHAR(20)," +
                    "store_id INTEGER ," +
                    "FOREIGN KEY(store_id) REFERENCES chain_store(store_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "CREATE TABLE IF NOT EXISTS customer(" +
                    "customer_id INTEGER PRIMARY KEY," +
                    "store_id INTEGER," +
                    "customer_name VARCHAR (50)," +
                    "customer_telphone VARCHAR (20)," +
                    "customer_address VARCHAR (50)," +
                    "FOREIGN KEY(store_id) REFERENCES chain_store(store_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);


            sqlCommand = "CREATE TABLE IF NOT EXISTS shop_order(" +
                    "shop_order_id INTEGER PRIMARY KEY," +
                    "shop_order_time TIMESTAMP," +
                    "customer_id INTEGER," +
                    "FOREIGN KEY(customer_id) REFERENCES customer(customer_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "CREATE TABLE IF NOT EXISTS purchase_record(" +
                    "purchase_id INTEGER PRIMARY KEY," +
                    "shop_order_id INTEGER ," +
                    "commodity_id INTEGER," +
                    "purchase_amount INTEGER," +
                    "FOREIGN KEY(commodity_id) REFERENCES store_commodity(commodity_id)," +
                    "FOREIGN KEY(shop_order_id) REFERENCES shop_order(shop_order_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);


            sqlCommand = "CREATE TABLE IF NOT EXISTS food(" +
                    "food_id INTEGER PRIMARY KEY ," +
                    "commodity_id INTEGER ," +
                    "food_name VARCHAR(30)," +
                    "food_amount INTEGER ," +
                    "food_sale_amount INTEGER ," +
                    "food_price INTEGER ," +
                    "FOREIGN KEY(commodity_id) REFERENCES store_commodity(commodity_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "CREATE TABLE IF NOT EXISTS drink(" +
                    "drink_id INTEGER PRIMARY KEY ," +
                    "commodity_id INTEGER ," +
                    "drink_name VARCHAR(30)," +
                    "drink_amount INTEGER ," +
                    "drink_sale_amount INTEGER ," +
                    "drink_price INTEGER ," +
                    "FOREIGN KEY(commodity_id) REFERENCES store_commodity(commodity_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "CREATE TABLE IF NOT EXISTS necessary(" +
                    "necessary_id INTEGER PRIMARY KEY ," +
                    "commodity_id INTEGER ," +
                    "necessary_name VARCHAR(30)," +
                    "necessary_amount INTEGER ," +
                    "necessary_sale_amount INTEGER ," +
                    "necessary_price INTEGER ," +
                    "FOREIGN KEY(commodity_id) REFERENCES store_commodity(commodity_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "CREATE TABLE IF NOT EXISTS household_appliances(" +
                    "household_appliances_id INTEGER PRIMARY KEY ," +
                    "commodity_id INTEGER ," +
                    "household_appliances_name VARCHAR(30)," +
                    "household_appliances_amount INTEGER ," +
                    "household_appliances_sale_amount INTEGER ," +
                    "household_appliances_price INTEGER ," +
                    "FOREIGN KEY(commodity_id) REFERENCES store_commodity(commodity_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);

            sqlCommand = "CREATE TABLE IF NOT EXISTS entertainment(" +
                    "entertainment_id INTEGER PRIMARY KEY ," +
                    "commodity_id INTEGER ," +
                    "entertainment_name VARCHAR(30)," +
                    "entertainment_amount INTEGER ," +
                    "entertainment_sale_amount INTEGER ," +
                    "entertainment_price INTEGER ," +
                    "FOREIGN KEY(commodity_id) REFERENCES store_commodity(commodity_id)" +
                    ")";
            statement.executeUpdate(sqlCommand);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
