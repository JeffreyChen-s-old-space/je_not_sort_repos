package gui.main;


import gui.GUIFather;
import gui.all_data.ShowAllDataForm;
import gui.buy_record.BuyRecord;
import gui.sale.SaleSearch;
import gui.stock.StockGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Objects;

public class MainForm extends GUIFather {

    private JPanel jPanel;
    private JButton mainOpenFormButton;
    private JComboBox<String> mainComboBox;
    private final Connection connection;

    public MainForm(String windowName, Connection connection) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
        this.connection = connection;
        mainComboBox.addItem("全部資料查詢");
        mainComboBox.addItem("客戶購買紀錄查詢");
        mainComboBox.addItem("商店銷售查詢");
        mainComboBox.addItem("商店存貨查詢");
        mainOpenFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openForm((Objects.requireNonNull(mainComboBox.getSelectedItem()).toString()));
            }
        });
    }

    private void openForm(String mainComboBoxChooseData) {
        switch (mainComboBoxChooseData) {
            case "全部資料查詢":
                new ShowAllDataForm("全部資料查詢",connection);
                break;
            case "客戶購買紀錄查詢":
                new BuyRecord("客戶購買紀錄查詢",connection);
                break;
            case "商店銷售查詢":
                new SaleSearch("商店銷售查詢",connection);
                break;
            case "商店存貨查詢":
                new StockGUI("商店存貨查詢",connection);
                break;
        }
    }

}
