package algorithm.gui.sort_gui;

import algorithm.gui.GUIFather;
import algorithm.util.sort.InsertionSort;
import algorithm.util.sort.MergeSort;
import algorithm.util.sort.RadixSort;
import algorithm.util.sort.SortAlgorithm;
import algorithm.student.StudentData;
import algorithm.student.StudentDataProcess;
import algorithm.util.file.FileChooser;
import algorithm.util.file.FileInput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;


public class SortGUI extends GUIFather {
    private final FileInput fileInput = new FileInput();
    private final FileChooser fileChooser = new FileChooser();
    private final StudentDataProcess studentDataProcess = new StudentDataProcess();
    private JButton sortGUI_determine;
    private JTextField sortGUI_input;
    private JPanel jPanel;
    private JLabel sortGUILabel;
    private JTextArea sortOutput;
    private StudentData<String, TreeMap<String, Integer>, String, Integer>[] studentDataArray;

    public SortGUI(String windowName) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);

        sortGUI_determine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFunction(sortGUI_input.getText());
            }
        });

        sortGUI_input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    selectFunction(sortGUI_input.getText());
                }
            }
        });

        studentDataArray = new StudentData[3];

        HashMap<String, TreeMap<String, Integer>> studentHashMap = new HashMap<>();
        TreeMap<String, Integer> student1TreeMap = new TreeMap<>();
        student1TreeMap.put("DS", 80);
        student1TreeMap.put("DM", 76);
        student1TreeMap.put("LA", 63);
        TreeMap<String, Integer> student2TreeMap = new TreeMap<>();
        student2TreeMap.put("DS", 53);
        student2TreeMap.put("DM", 79);
        student2TreeMap.put("LA", 98);
        TreeMap<String, Integer> student3TreeMap = new TreeMap<>();
        student3TreeMap.put("DS", 83);
        student3TreeMap.put("DM", 49);
        student3TreeMap.put("LA", 78);
        studentHashMap.put("97501", student1TreeMap);
        studentDataArray[0] = (new StudentData<>(student1TreeMap, "97501", "DS"));
        studentHashMap.put("97502", student2TreeMap);
        studentDataArray[1] = (new StudentData<>(student2TreeMap, "97502", "DS"));
        studentHashMap.put("97523", student3TreeMap);
        studentDataArray[2] = (new StudentData<>(student3TreeMap, "97523", "DS"));

        /*
        97501 DS 80 DM 76 LA 63
        97502 DS 53 DM 79 LA 98
        97523 DS 83 DM 49 LA 78
         */
    }

    private void setSortGUILabel() {
        sortGUILabel.setText("" +
                "<html>請選擇排序方法：" +
                "<br> 1.Insertion Sort" +
                "<br> 2.Merge Sort" +
                "<br> 3.Radix Sort" +
                "<br> 或是輸入 0 讀取檔案" +
                "<br>也可輸入科目名稱設定排序科目" +
                "</html>");
    }

    private void setSortType(String sortType) {
        for (StudentData<String, TreeMap<String, Integer>, String, Integer> tempStudentData : studentDataArray)
            tempStudentData.setSortUseType(sortType);
    }

    private void sort(SortAlgorithm sortAlgorithm) {
        sortAlgorithm.sort(studentDataArray);
        studentDataArray[0].printStudentData(studentDataArray, sortOutput);
        sortOutput.append(sortAlgorithm.getSortData() + "\n");
    }

    private void selectFunction(String selectString) {
        switch (selectString) {

            case "0":
                try {
                    File file = fileChooser.chooseFile();
                    if (file != null) {
                        String rawStudentDataString = fileInput.readFile(file);
                        studentDataArray = studentDataProcess.processRawString(rawStudentDataString);
                    } else
                        JOptionPane.showMessageDialog(null, "未知輸入", "未知輸入錯誤", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "1":
                InsertionSort insertionSort = new InsertionSort();
                sort(insertionSort);
                break;

            case "2":
                MergeSort mergeSort = new MergeSort();
                sort(mergeSort);
                break;

            case "3":
                RadixSort radixSort = new RadixSort();
                sort(radixSort);
                break;

            case "DS":
                setSortType("DS");
                setSortGUILabel();
                Arrays.sort(studentDataArray, Comparator.reverseOrder());
                studentDataArray[0].printStudentData(studentDataArray, sortOutput);
                break;
            case "DM":
                setSortType("DM");
                setSortGUILabel();
                Arrays.sort(studentDataArray, Comparator.reverseOrder());
                studentDataArray[0].printStudentData(studentDataArray, sortOutput);
                break;
            case "LA":
                setSortType("LA");
                setSortGUILabel();
                Arrays.sort(studentDataArray, Comparator.reverseOrder());
                studentDataArray[0].printStudentData(studentDataArray, sortOutput);
                break;
        }
    }

}
