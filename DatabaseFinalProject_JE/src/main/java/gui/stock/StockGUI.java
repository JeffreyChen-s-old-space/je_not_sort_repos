package gui.stock;

import gui.GUIFather;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class StockGUI extends GUIFather {
    private JPanel jPanel;
    private JTable showStockJTable;
    private JButton selectStockButton;
    private JTextField storeNumberInput;
    private JComboBox<String> stockTypeChooseComboBox;

    private Connection connection;

    public StockGUI(String windowName, Connection connection) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
        this.connection = connection;
        stockTypeChooseComboBox.addItem("食品存貨");
        stockTypeChooseComboBox.addItem("飲品存貨");
        stockTypeChooseComboBox.addItem("娛樂用品存貨");
        stockTypeChooseComboBox.addItem("日常用品存貨");
        stockTypeChooseComboBox.addItem("家電存貨");
        DefaultTableModel model = new DefaultTableModel(new String[]{"商店編號", "隸屬公司", "商店名稱"}, 0);
        String sql = "SELECT * FROM chain_store";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int store_id = resultSet.getInt("store_id");
                int company_id = resultSet.getInt("company_id");
                String store_name = resultSet.getString("store_name");
                model.addRow(new Object[]{store_id, company_id, store_name});
            }
            showStockJTable.setModel(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "資料取得錯誤", "資料取得錯誤", JOptionPane.ERROR_MESSAGE);
        }

        selectStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setShowStockJTable((Objects.requireNonNull(stockTypeChooseComboBox.getSelectedItem()).toString()));
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "資料取得錯誤請檢察輸入是否正確",
                            "資料取得錯誤請檢察輸入是否正確", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void setShowStockJTable(String selectData) throws SQLException {
        DefaultTableModel model;
        String sql;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        switch (selectData) {
            case "食品存貨":
                model = new DefaultTableModel(new String[]{"食品名稱", "食品存貨量"}, 0);
                sql = "SELECT food_name,food_amount " +
                        "FROM food,store_commodity " +
                        "WHERE  store_commodity.store_id = ?" +
                        "  AND store_commodity.commodity_id = food.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(storeNumberInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String food_name = resultSet.getString("food_name");
                    int food_amount = resultSet.getInt("food_amount");
                    model.addRow(new Object[]{food_name, food_amount});
                }
                showStockJTable.setModel(model);
                break;
            case "飲品存貨":
                model = new DefaultTableModel(new String[]{"飲品名稱", "飲品存貨量"}, 0);
                sql = "SELECT drink_name,drink_amount " +
                        "FROM drink,store_commodity " +
                        "WHERE  store_commodity.store_id = ?" +
                        "  AND store_commodity.commodity_id = drink.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(storeNumberInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String drink_name = resultSet.getString("drink_name");
                    int drink_amount = resultSet.getInt("drink_amount");
                    model.addRow(new Object[]{drink_name, drink_amount});
                }
                showStockJTable.setModel(model);
                break;
            case "娛樂用品存貨":
                model = new DefaultTableModel(new String[]{"娛樂用品名稱", "娛樂用品存貨量"}, 0);
                sql = "SELECT entertainment_name,entertainment_amount " +
                        "FROM entertainment,store_commodity " +
                        "WHERE  store_commodity.store_id = ?" +
                        "  AND store_commodity.commodity_id = entertainment.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(storeNumberInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String entertainment_name = resultSet.getString("entertainment_name");
                    int entertainment_amount = resultSet.getInt("entertainment_amount");
                    model.addRow(new Object[]{entertainment_name, entertainment_amount});
                }
                showStockJTable.setModel(model);
                break;
            case "日常用品存貨":
                model = new DefaultTableModel(new String[]{"日常用品名稱", "日常用品存貨量"}, 0);
                sql = "SELECT necessary_name,necessary_amount " +
                        "FROM necessary,store_commodity " +
                        "WHERE  store_commodity.store_id = ?" +
                        "  AND store_commodity.commodity_id = necessary.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(storeNumberInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String necessary_name = resultSet.getString("necessary_name");
                    int necessary_amount = resultSet.getInt("necessary_amount");
                    model.addRow(new Object[]{necessary_name, necessary_amount});
                }
                showStockJTable.setModel(model);
                break;
            case "家電存貨":
                model = new DefaultTableModel(new String[]{"家電名稱", "家電存貨量"}, 0);
                sql = "SELECT household_appliances_name,household_appliances_amount " +
                        "FROM household_appliances,store_commodity " +
                        "WHERE  store_commodity.store_id = ?" +
                        "  AND store_commodity.commodity_id = household_appliances.commodity_id";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(storeNumberInput.getText()));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String household_appliances_name = resultSet.getString("household_appliances_name");
                    int household_appliances_amount = resultSet.getInt("household_appliances_amount");
                    model.addRow(new Object[]{household_appliances_name, household_appliances_amount});
                }
                showStockJTable.setModel(model);
                break;
        }
    }


}
