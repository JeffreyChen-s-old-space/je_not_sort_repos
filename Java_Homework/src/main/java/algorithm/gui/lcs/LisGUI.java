package algorithm.gui.lcs;

import algorithm.gui.GUIFather;
import algorithm.student.StudentData;
import algorithm.student.StudentDataProcess;
import algorithm.util.dp.Lis;
import algorithm.util.file.FileChooser;
import algorithm.util.file.FileInput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

public class LisGUI extends GUIFather {

    private File tempFile;
    private final FileChooser fileChooser = new FileChooser();
    private final FileInput fileInput = new FileInput();
    private static StudentData<String, TreeMap<String, Integer>, String, Integer>[] studentDataArray;
    private ArrayList<Integer> tempIntegerArrayList = new ArrayList<>();
    private final Lis lis = new Lis();
    private JPanel jPanel;
    private JTextArea outputTextArea;
    private JButton calculator;
    private JButton readFileButton;
    private JTextField inputTextField;

    public LisGUI(String windowName) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);

        readFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
            }
        });

        calculator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tempFile != null && studentDataArray != null) {
                    int temp = calculatorLIS(inputTextField.getText());
                    outputTextArea.append(inputTextField.getText() + " 成績序列為: " + tempIntegerArrayList.toString() + "，最長遞增子序列長度為 " + temp + "\n");
                } else
                    JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (tempFile != null && studentDataArray != null) {
                        int temp = calculatorLIS(inputTextField.getText());
                        outputTextArea.append(inputTextField.getText() + " 成績序列為: " + tempIntegerArrayList.toString() + "，最長遞增子序列長度為 " + temp + "\n");
                    } else
                        JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void readFile() {
        tempFile = fileChooser.chooseFile();
        if (tempFile != null) {
            try {
                String rawStudentDataString = fileInput.readFile(tempFile);
                StudentDataProcess studentDataProcess = new StudentDataProcess();
                studentDataArray = studentDataProcess.processRawString(rawStudentDataString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(Arrays.toString(studentDataArray));
            outputTextArea.append("讀檔成功\n");
        } else
            JOptionPane.showMessageDialog(null, "未知輸入", "未知輸入錯誤", JOptionPane.ERROR_MESSAGE);
    }


    private int calculatorLIS(String subjectName) {
        tempIntegerArrayList = new ArrayList<>();
        for (StudentData<String, TreeMap<String, Integer>, String, Integer> stringTreeMapStringIntegerStudentData : studentDataArray) {
            if (stringTreeMapStringIntegerStudentData.getStudentTreeMap().get(subjectName) != null)
                tempIntegerArrayList.add(stringTreeMapStringIntegerStudentData.getStudentTreeMap().get(subjectName));
        }
        return lis.lengthOfLIS(tempIntegerArrayList);
    }

    public static void main(String[] args) {
        Integer[] test = new Integer[]{60, 49, 20, 50, 30, 70, 100, 18};
        ArrayList<Integer> tempIntegerArrayList = new ArrayList<>();
        Collections.addAll(tempIntegerArrayList, test);
        Lis lis = new Lis();
        System.out.println(lis.lengthOfLIS(tempIntegerArrayList));
    }

}
