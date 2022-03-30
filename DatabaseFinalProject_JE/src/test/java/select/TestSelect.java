package select;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestSelect {

    static final String jdbcDriver = "org.mariadb.jdbc.Driver";
    static final String dbURL = "jdbc:mariadb://140.127.74.170/410877027";
    static final String user = "410877027";
    static final String password = "410877027";
    static Connection connection;

    @BeforeClass
    public static void init(){
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
    public void testSelectChainStore() throws SQLException {
        String sql = "SELECT * FROM chain_store";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectCompany() throws SQLException {
        String sql = "SELECT * FROM company";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectCustomer() throws SQLException {
        String sql = "SELECT * FROM customer";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectDrink() throws SQLException {
        String sql = "SELECT * FROM drink";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectEntertainment() throws SQLException {
        String sql = "SELECT * FROM entertainment";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectFood() throws SQLException {
        String sql = "SELECT * FROM food";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectHouseholdAppliances() throws SQLException {
        String sql = "SELECT * FROM household_appliances";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectNecessary() throws SQLException {
        String sql = "SELECT * FROM necessary";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectPurchaseRecord() throws SQLException {
        String sql = "SELECT * FROM purchase_record";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectShopOrder() throws SQLException {
        String sql = "SELECT * FROM shop_order";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

    @Test
    public void testSelectStoreCommodity() throws SQLException {
        String sql = "SELECT * FROM store_commodity";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }

}
