package gui.sale;

import gui.GUIFather;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SaleSearch extends GUIFather {
    private JPanel jPanel;
    private JButton searchButton;
    private JComboBox<String> selectComboBox;
    private JTextArea showDataArea;

    private Connection connection;

    public SaleSearch(String windowName, Connection connection) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
        this.connection = connection;
        selectComboBox.addItem("查詢販賣食品總數最多的商店");
        selectComboBox.addItem("查詢販賣飲料總數最多的商店");
        selectComboBox.addItem("查詢販賣娛樂用品總數最多的商店");
        selectComboBox.addItem("查詢販賣日常用品總數最多的商店");
        selectComboBox.addItem("查詢販賣家電總數最多的商店");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setShowDataArea((Objects.requireNonNull(selectComboBox.getSelectedItem()).toString()));
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "資料取得錯誤請檢察輸入是否正確",
                            "資料取得錯誤請檢察輸入是否正確", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setShowDataArea(String selectData) throws SQLException {
        String sql;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        HashMap<String, Integer> temp;
        String key;
        switch (selectData) {
            case "查詢販賣食品總數最多的商店":
                temp = new HashMap<>();
                for (int i = 1; i < 6; i++) {
                    sql = "SELECT SUM(food_sale_amount),store_id " +
                            "FROM food, " +
                            "store_commodity " +
                            "WHERE food.commodity_id = store_commodity.commodity_id " +
                            "AND store_id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, i);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int saleNumber = resultSet.getInt("SUM(food_sale_amount)");
                        int store_id = resultSet.getInt("store_id");
                        temp.put(String.valueOf(store_id), saleNumber);
                    }
                }
                key = Collections.max(temp.entrySet(), Map.Entry.comparingByValue()).getKey();
                showDataArea.append("銷售最多食品的店編號: " + key + " 銷售數量: " + temp.get(key) + "\n");
                break;

            case "查詢販賣飲料總數最多的商店":
                temp = new HashMap<>();
                for (int i = 1; i < 6; i++) {
                    sql = "SELECT SUM(drink_sale_amount),store_id " +
                            "FROM drink, " +
                            "store_commodity " +
                            "WHERE drink.commodity_id = store_commodity.commodity_id " +
                            "AND store_id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, i);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int saleNumber = resultSet.getInt("SUM(drink_sale_amount)");
                        int store_id = resultSet.getInt("store_id");
                        temp.put(String.valueOf(store_id), saleNumber);
                    }
                }
                key = Collections.max(temp.entrySet(), Map.Entry.comparingByValue()).getKey();
                showDataArea.append("銷售最多飲品的店編號: " + key + " 銷售數量: " + temp.get(key) + "\n");
                break;

            case "查詢販賣娛樂用品總數最多的商店":
                temp = new HashMap<>();
                for (int i = 1; i < 6; i++) {
                    sql = "SELECT SUM(entertainment_sale_amount),store_id " +
                            "FROM entertainment, " +
                            "store_commodity " +
                            "WHERE entertainment.commodity_id = store_commodity.commodity_id " +
                            "AND store_id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, i);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int saleNumber = resultSet.getInt("SUM(entertainment_sale_amount)");
                        int store_id = resultSet.getInt("store_id");
                        temp.put(String.valueOf(store_id), saleNumber);
                    }
                }
                key = Collections.max(temp.entrySet(), Map.Entry.comparingByValue()).getKey();
                showDataArea.append("銷售最多娛樂用品的店編號: " + key + " 銷售數量: " + temp.get(key) + "\n");
                break;

            case "查詢販賣日常用品總數最多的商店":
                temp = new HashMap<>();
                for (int i = 1; i < 6; i++) {
                    sql = "SELECT SUM(necessary_sale_amount),store_id " +
                            "FROM necessary, " +
                            "store_commodity " +
                            "WHERE necessary.commodity_id = store_commodity.commodity_id " +
                            "AND store_id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, i);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int saleNumber = resultSet.getInt("SUM(necessary_sale_amount)");
                        int store_id = resultSet.getInt("store_id");
                        temp.put(String.valueOf(store_id), saleNumber);
                    }
                }
                key = Collections.max(temp.entrySet(), Map.Entry.comparingByValue()).getKey();
                showDataArea.append("銷售最多日常用品的店編號: " + key + " 銷售數量: " + temp.get(key) + "\n");
                break;

            case "查詢販賣家電總數最多的商店":
                temp = new HashMap<>();
                for (int i = 1; i < 6; i++) {
                    sql = "SELECT SUM(household_appliances_sale_amount),store_id " +
                            "FROM household_appliances, " +
                            "store_commodity " +
                            "WHERE household_appliances.commodity_id = store_commodity.commodity_id " +
                            "AND store_id = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, i);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int saleNumber = resultSet.getInt("SUM(household_appliances_sale_amount)");
                        int store_id = resultSet.getInt("store_id");
                        temp.put(String.valueOf(store_id), saleNumber);
                    }
                }
                key = Collections.max(temp.entrySet(), Map.Entry.comparingByValue()).getKey();
                showDataArea.append("銷售最多家電的店編號: " + key + " 銷售數量: " + temp.get(key) + "\n");
                break;

        }
    }

}
