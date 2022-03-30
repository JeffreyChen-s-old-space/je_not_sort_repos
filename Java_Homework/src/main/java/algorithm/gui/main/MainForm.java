package algorithm.gui.main;

import algorithm.gui.GUIFather;
import algorithm.gui.lcs.LisGUI;
import algorithm.gui.sort_gui.SortGUI;
import algorithm.gui.sudent_data_gui.add.AddStudentDataGUI;
import algorithm.gui.sudent_data_gui.remove.RemoveStudentDataGUI;
import algorithm.gui.sudent_data_gui.show.ShowStudentDataGUI;
import algorithm.gui.tological.ToplogicalGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainForm extends GUIFather {
    private JTextField main_input_choose;
    private JPanel jPanel;
    private JButton main_choose_button;


    public MainForm(String windowName) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);

        main_choose_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFunction(main_input_choose.getText());
            }
        });

        main_input_choose.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    selectFunction(main_input_choose.getText());
                }
            }
        });
    }

    private void selectFunction(String selectString) {
        switch (selectString) {
            case "1":
                new ShowStudentDataGUI("查詢學生單科成績頁面", "請先選擇檔案後  輸入學生學號及科目");
                break;
            case "2":
                new ShowStudentDataGUI("查詢學生所有成績頁面", "請先選擇檔案後 輸入學生學號");
                break;
            case "3":
                new AddStudentDataGUI("新增學生成績頁面", "請先選擇檔案後 輸入學生學號及科目成績");
                break;
            case "4":
                new RemoveStudentDataGUI("刪除學生成績頁面", "請先選擇檔案後 輸入學生學號");
                break;
            case "5":
                new SortGUI("排序頁面");
                break;
            case "6":
                new LisGUI("最長遞增成績子序列頁面");
                break;
            case "7":
                new ToplogicalGUI("排課頁面");
                break;
            case "8":
                this.dispose();
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "未知輸入", "未知輸入錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }

}
