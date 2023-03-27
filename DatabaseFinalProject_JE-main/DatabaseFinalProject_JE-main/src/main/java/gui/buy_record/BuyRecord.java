package gui.buy_record;

import gui.GUIFather;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class BuyRecord extends GUIFather {

    private JPanel jPanel;
    private JTable showRecordJTable;
    private JButton searchButton;
    private JComboBox<String> chooseTypeComboBox;
    private JTextField customerIdInput;

    private Connection connection;

    public BuyRecord(String windowName, Connection connection) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
        this.connection = connection;
        chooseTypeComboBox.addItem("食品");
        chooseTypeComboBox.addItem("飲品");
        chooseTypeComboBox.addItem("娛樂用品");
        chooseTypeComboBox.addItem("日常用品");
        chooseTypeComboBox.addItem("家電");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setShowRecordJTable((Objects.requireNonNull(chooseTypeComboBox.getSelectedItem()).toString()));
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "資料取得錯誤請檢察輸入是否正確",
                            "資料取得錯誤請檢察輸入是否正確", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        DefaultTableModel model = new DefaultTableModel(new String[]{"客戶編號", "會員商店編號", "會員名稱",
                "會員手機", "會員地址"}, 0);
        String sql = "SELECT * FROM customer";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int customer_id = resultSet.getInt("customer_id");
                int store_id = resultSet.getInt("store_id");
                String customer_name = resultSet.getString("customer_name");
                String customer_telphone = resultSet.getString("customer_telphone");
                String customer_address = resultSet.getString("customer_address");
                model.addRow(new Object[]{customer_id, store_id, customer_name, customer_telphone, customer_address});
            }
            showRecordJTable.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "資料取得錯誤", "資料取得錯誤", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void setShowRecordJTable(String selectString) throws SQLException {
        DefaultTableModel model;
        String sql;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        switch (selectString) {
            case "食品":
                model = new DefaultTableModel(new String[]{"食品名稱", "購買數量"}, 0);
                sql = "SELECT food_name,purchase_amount " +
                        "FROM customer,shop_order,purchase_record,food " +
                        "WHERE shop_order.customer_id = customer.customer_id " +
                        "AND customer.customer_id = ? " +
                        "AND shop_order.shop_order_id = purchase_record.shop_order_id " +
                        "AND food.commodity_id = purchase_record.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(customerIdInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String food_name = resultSet.getString("food_name");
                    int purchase_amount = resultSet.getInt("purchase_amount");
                    model.addRow(new Object[]{food_name, purchase_amount});
                }
                showRecordJTable.setModel(model);
                break;

            case "飲品":
                model = new DefaultTableModel(new String[]{"飲品名稱", "購買數量"}, 0);
                sql = "SELECT drink_name,purchase_amount " +
                        "FROM customer,shop_order,purchase_record,drink " +
                        "WHERE shop_order.customer_id = customer.customer_id " +
                        "AND customer.customer_id = ? " +
                        "AND shop_order.shop_order_id = purchase_record.shop_order_id " +
                        "AND drink.commodity_id = purchase_record.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(customerIdInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String drink_name = resultSet.getString("drink_name");
                    int purchase_amount = resultSet.getInt("purchase_amount");
                    model.addRow(new Object[]{drink_name, purchase_amount});
                }
                showRecordJTable.setModel(model);
                break;

            case "娛樂用品":
                model = new DefaultTableModel(new String[]{"娛樂用品名稱", "購買數量"}, 0);
                sql = "SELECT entertainment_name,purchase_amount " +
                        "FROM customer,shop_order,purchase_record,entertainment " +
                        "WHERE shop_order.customer_id = customer.customer_id " +
                        "AND customer.customer_id = ? " +
                        "AND shop_order.shop_order_id = purchase_record.shop_order_id " +
                        "AND entertainment.commodity_id = purchase_record.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(customerIdInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String entertainment_name = resultSet.getString("entertainment_name");
                    int purchase_amount = resultSet.getInt("purchase_amount");
                    model.addRow(new Object[]{entertainment_name, purchase_amount});
                }
                showRecordJTable.setModel(model);
                break;

            case "日常用品":
                model = new DefaultTableModel(new String[]{"日常用品名稱", "購買數量"}, 0);
                sql = "SELECT necessary_name,purchase_amount " +
                        "FROM customer,shop_order,purchase_record,necessary " +
                        "WHERE shop_order.customer_id = customer.customer_id " +
                        "AND customer.customer_id = ? " +
                        "AND shop_order.shop_order_id = purchase_record.shop_order_id " +
                        "AND necessary.commodity_id = purchase_record.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(customerIdInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String necessary_name = resultSet.getString("necessary_name");
                    int purchase_amount = resultSet.getInt("purchase_amount");
                    model.addRow(new Object[]{necessary_name, purchase_amount});
                }
                showRecordJTable.setModel(model);
                break;

            case "家電":
                model = new DefaultTableModel(new String[]{"家電名稱", "購買數量"}, 0);
                sql = "SELECT household_appliances_name,purchase_amount " +
                        "FROM customer,shop_order,purchase_record,household_appliances " +
                        "WHERE shop_order.customer_id = customer.customer_id " +
                        "AND customer.customer_id = ? " +
                        "AND shop_order.shop_order_id = purchase_record.shop_order_id " +
                        "AND household_appliances.commodity_id = purchase_record.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(customerIdInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String household_appliances_name = resultSet.getString("household_appliances_name");
                    int purchase_amount = resultSet.getInt("purchase_amount");
                    model.addRow(new Object[]{household_appliances_name, purchase_amount});
                }
                showRecordJTable.setModel(model);
                break;
        }

    }


}
