package algorithm.gui.sudent_data_gui.remove;

import algorithm.gui.GUIFather;
import algorithm.util.file.FileChooser;
import algorithm.util.file.FileInput;
import algorithm.util.file.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class RemoveStudentDataGUI extends GUIFather {
    private String tempString = "";
    private File tempFile;
    private FileUtils fileUtils = new FileUtils();
    private final FileInput fileInput = new FileInput();
    private final FileChooser fileChooser = new FileChooser();
    private JTextArea outputTextArea;
    private JPanel panel1;
    private JButton removeButton;
    private JButton readFileButton;
    private JTextField inputRemoveTextField;

    //TODO Remove data gui
    public RemoveStudentDataGUI(String windowName, String tip) {
        super(windowName);
        setContentPane(panel1);
        setVisible(true);

        readFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tempString.equals("") && tempFile != null)
                    removeData(inputRemoveTextField.getText());
                else
                    JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputRemoveTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!tempString.equals(""))
                        removeData(inputRemoveTextField.getText());
                    else
                        JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void readFile() {
        try {
            tempFile = fileChooser.chooseFile();
            if (tempFile != null) {
                tempString = fileInput.readFile(tempFile);
                outputTextArea.append("讀檔成功\n");
            } else
                JOptionPane.showMessageDialog(null, "未知輸入", "未知輸入錯誤", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeData(String removeStudentNumber) {
        try {
            outputTextArea.append("刪除 " + (fileUtils.removeUsingNewFile(tempFile, removeStudentNumber) ? "成功" : "失敗") + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
