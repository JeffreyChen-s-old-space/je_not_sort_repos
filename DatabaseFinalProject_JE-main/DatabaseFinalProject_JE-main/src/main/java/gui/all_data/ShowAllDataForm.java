package gui.all_data;

import gui.GUIFather;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class ShowAllDataForm extends GUIFather {
    private JPanel jPanel;
    private JTable showAllDataTable;
    private JButton showAllDataButton;
    private JScrollPane jScrollPane;
    private JComboBox<String> showDataSelectComboBox;
    private final Connection connection;

    public ShowAllDataForm(String windowName, Connection connection) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
        this.connection = connection;
        showDataSelectComboBox.addItem("公司");
        showDataSelectComboBox.addItem("連鎖店");
        showDataSelectComboBox.addItem("客戶");
        showDataSelectComboBox.addItem("訂單");
        showDataSelectComboBox.addItem("購買紀錄");
        showDataSelectComboBox.addItem("全商品編號及類別");
        showDataSelectComboBox.addItem("食品");
        showDataSelectComboBox.addItem("飲品");
        showDataSelectComboBox.addItem("娛樂用品");
        showDataSelectComboBox.addItem("日常用品");
        showDataSelectComboBox.addItem("家電");

        showAllDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setShowAllDataTable((Objects.requireNonNull(showDataSelectComboBox.getSelectedItem()).toString()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    JOptionPane.showMessageDialog(null, "資料取得錯誤", "資料取得錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setShowAllDataTable(String selectData) throws SQLException {
        DefaultTableModel model;
        String sql;
        Statement statement;
        ResultSet resultSet;
        switch (selectData) {
            case "公司":
                model = new DefaultTableModel(new String[]{"公司編號", "公司名稱"}, 0);
                sql = "SELECT * FROM company";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int company_id = resultSet.getInt("company_id");
                    String company_name = resultSet.getString("company_name");
                    model.addRow(new Object[]{company_id, company_name});
                }
                showAllDataTable.setModel(model);
                break;
            case "連鎖店":
                model = new DefaultTableModel(new String[]{"連鎖店編號", "隸屬公司編號", "連鎖店名稱"}, 0);
                sql = "SELECT * FROM chain_store";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int store_id = resultSet.getInt("store_id");
                    int company_id = resultSet.getInt("company_id");
                    String store_name = resultSet.getString("store_name");
                    model.addRow(new Object[]{store_id, company_id, store_name});
                }
                showAllDataTable.setModel(model);
                break;
            case "客戶":
                model = new DefaultTableModel(new String[]{"客戶編號", "哪間商店的客戶", "客戶名稱", "客戶電話", "客戶地址"}, 0);
                sql = "SELECT * FROM customer";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int customer_id = resultSet.getInt("customer_id");
                    int store_id = resultSet.getInt("store_id");
                    String customer_name = resultSet.getString("customer_name");
                    String customer_telphone = resultSet.getString("customer_telphone");
                    String customer_address = resultSet.getString("customer_address");
                    model.addRow(new Object[]{customer_id, store_id, customer_name, customer_telphone, customer_address});
                }
                showAllDataTable.setModel(model);
                break;
            case "訂單":
                model = new DefaultTableModel(new String[]{"訂單編號", "訂單開立時間", "客戶編號"}, 0);
                sql = "SELECT * FROM shop_order";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int shop_order_id = resultSet.getInt("shop_order_id");
                    Timestamp shop_order_time = resultSet.getTimestamp("shop_order_time");
                    int customer_id = resultSet.getInt("customer_id");
                    model.addRow(new Object[]{shop_order_id, shop_order_time, customer_id});
                }
                showAllDataTable.setModel(model);
                break;
            case "購買紀錄":
                model = new DefaultTableModel(new String[]{"購買編號", "屬於的訂單編號", "屬於的物品編號", "購買數量"}, 0);
                sql = "SELECT * FROM purchase_record";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int purchase_id = resultSet.getInt("purchase_id");
                    int shop_order_id = resultSet.getInt("shop_order_id");
                    int commodity_id = resultSet.getInt("commodity_id");
                    int purchase_amount = resultSet.getInt("purchase_amount");
                    model.addRow(new Object[]{purchase_id, shop_order_id, commodity_id, purchase_amount});
                }
                showAllDataTable.setModel(model);
                break;
            case "全商品編號及類別":
                model = new DefaultTableModel(new String[]{"商品編號", "商品種類名", "商品隸屬商店編號"}, 0);
                sql = "SELECT * FROM store_commodity";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int commodity_id = resultSet.getInt("commodity_id");
                    String type_name = resultSet.getString("type_name");
                    int store_id = resultSet.getInt("store_id");
                    model.addRow(new Object[]{commodity_id, type_name, store_id});
                }
                showAllDataTable.setModel(model);
                break;
            case "食品":
                model = new DefaultTableModel(new String[]{"食品編號", "物品編號", "食品名稱",
                        "食品剩餘數量", "食品銷售數量", "食品價格"}, 0);
                sql = "SELECT * FROM food";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int food_id = resultSet.getInt("food_id");
                    int commodity_id = resultSet.getInt("commodity_id");
                    String food_name = resultSet.getString("food_name");
                    int food_amount = resultSet.getInt("food_amount");
                    int food_sale_amount = resultSet.getInt("food_sale_amount");
                    int food_price = resultSet.getInt("food_price");
                    model.addRow(new Object[]{food_id, commodity_id, food_name, food_amount, food_sale_amount, food_price});
                }
                showAllDataTable.setModel(model);
                break;
            case "飲品":
                model = new DefaultTableModel(new String[]{"飲品編號", "物品編號", "飲品名稱",
                        "飲品剩餘數量", "飲品銷售數量", "飲品價格"}, 0);
                sql = "SELECT * FROM drink";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int drink_id = resultSet.getInt("drink_id");
                    int commodity_id = resultSet.getInt("commodity_id");
                    String drink_name = resultSet.getString("drink_name");
                    int drink_amount = resultSet.getInt("drink_amount");
                    int drink_sale_amount = resultSet.getInt("drink_sale_amount");
                    int drink_price = resultSet.getInt("drink_price");
                    model.addRow(new Object[]{drink_id, commodity_id, drink_name, drink_amount, drink_sale_amount, drink_price});
                }
                showAllDataTable.setModel(model);
                break;
            case "娛樂用品":
                model = new DefaultTableModel(new String[]{"娛樂用品編號", "物品編號", "娛樂用品名稱",
                        "娛樂用品剩餘數量", "娛樂用品銷售數量", "娛樂用品價格"}, 0);
                sql = "SELECT * FROM entertainment";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int entertainment_id = resultSet.getInt("entertainment_id");
                    int commodity_id = resultSet.getInt("commodity_id");
                    String entertainment_name = resultSet.getString("entertainment_name");
                    int entertainment_amount = resultSet.getInt("entertainment_amount");
                    int entertainment_sale_amount = resultSet.getInt("entertainment_sale_amount");
                    int entertainment_price = resultSet.getInt("entertainment_price");
                    model.addRow(new Object[]{entertainment_id, commodity_id, entertainment_name, entertainment_amount,
                            entertainment_sale_amount, entertainment_price});
                }
                showAllDataTable.setModel(model);
                break;
            case "日常用品":
                model = new DefaultTableModel(new String[]{"日常用品編號", "物品編號", "日常用品名稱",
                        "日常用品剩餘數量", "日常用品銷售數量", "日常用品價格"}, 0);
                sql = "SELECT * FROM necessary";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int necessary_id = resultSet.getInt("necessary_id");
                    int commodity_id = resultSet.getInt("commodity_id");
                    String necessary_name = resultSet.getString("necessary_name");
                    int necessary_amount = resultSet.getInt("necessary_amount");
                    int necessary_sale_amount = resultSet.getInt("necessary_amount");
                    int necessary_price = resultSet.getInt("necessary_price");
                    model.addRow(new Object[]{necessary_id, commodity_id, necessary_name, necessary_amount,
                            necessary_sale_amount, necessary_price});
                }
                showAllDataTable.setModel(model);
                break;
            case "家電":
                model = new DefaultTableModel(new String[]{"家電編號", "物品編號", "家電名稱",
                        "家電剩餘數量", "家電銷售數量", "家電價格"}, 0);
                sql = "SELECT * FROM household_appliances";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int household_appliances_id = resultSet.getInt("household_appliances_id");
                    int commodity_id = resultSet.getInt("commodity_id");
                    String household_appliances_name = resultSet.getString("household_appliances_name");
                    int household_appliances_amount = resultSet.getInt("household_appliances_amount");
                    int household_appliances_sale_amount = resultSet.getInt("household_appliances_amount");
                    int household_appliances_price = resultSet.getInt("household_appliances_price");
                    model.addRow(new Object[]{household_appliances_id, commodity_id, household_appliances_name, household_appliances_amount,
                            household_appliances_sale_amount, household_appliances_price});
                }
                showAllDataTable.setModel(model);
                break;
        }
    }

}
