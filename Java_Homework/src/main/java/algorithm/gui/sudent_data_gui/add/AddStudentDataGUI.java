package algorithm.gui.sudent_data_gui.add;

import algorithm.gui.GUIFather;
import algorithm.student.StudentData;
import algorithm.util.file.FileChooser;
import algorithm.util.file.FileOutput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class AddStudentDataGUI extends GUIFather {
    private File tempFile;
    private static StudentData<String, TreeMap<String, Integer>, String, Integer>[] studentDataArray;
    private final FileOutput fileOutput = new FileOutput();
    private final FileChooser fileChooser = new FileChooser();
    private JTextArea outputTextArea;
    private JPanel panel1;
    private JButton readFileButton;
    private JTextField inputAddTextField;
    private JButton addDataButton;

    //TODO Add data gui
    public AddStudentDataGUI(String windowName, String tip) {
        super(windowName);
        setContentPane(panel1);
        setVisible(true);

        readFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
            }
        });

        addDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tempFile != null)
                    addData(inputAddTextField.getText());
                else
                    JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputAddTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String data = inputAddTextField.getText();
                    if (!data.equals("") && tempFile != null)
                        addData(data);
                    else
                        JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void readFile() {
        tempFile = fileChooser.chooseFile();
        if (tempFile != null) {
            outputTextArea.append("讀檔成功\n");
        } else
            JOptionPane.showMessageDialog(null, "未知輸入", "未知輸入錯誤", JOptionPane.ERROR_MESSAGE);
    }

    private void addData(String data) {
        try {
            fileOutput.appendFile(tempFile, data, true);
            outputTextArea.append("新增成功\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
