package algorithm.gui.sudent_data_gui.show;

import algorithm.gui.GUIFather;
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
import java.util.Objects;
import java.util.TreeMap;

public class ShowStudentDataGUI extends GUIFather {
    private static StudentData<String, TreeMap<String, Integer>, String, Integer>[] studentDataArray;
    private final FileInput fileInput = new FileInput();
    private final FileChooser fileChooser = new FileChooser();
    private JPanel jPanel;
    private JTextArea showStudentDataArea;
    private JButton clickToShow;
    private JComboBox<String> checkOneOrAllDataChooser;
    private JTextField subjectInput;
    private JButton readFileButton;

    public ShowStudentDataGUI(String windowName, String tip) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
        checkOneOrAllDataChooser.addItem("單科");
        checkOneOrAllDataChooser.addItem("全部");

        showStudentDataArea.append(tip + "\n");

        readFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
            }
        });

        clickToShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentDataArray != null)
                    checkGrade(subjectInput.getText());
                else
                    JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        subjectInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (studentDataArray != null)
                        checkGrade(subjectInput.getText());
                    else
                        JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void readFile() {
        try {
            File file = fileChooser.chooseFile();
            if (file != null) {
                String rawStudentDataString = fileInput.readFile(file);
                StudentDataProcess studentDataProcess = new StudentDataProcess();
                studentDataArray = studentDataProcess.processRawString(rawStudentDataString);
                System.out.println(Arrays.toString(studentDataArray));
            } else
                JOptionPane.showMessageDialog(null, "未知輸入", "未知輸入錯誤", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkGrade(String sourceString) {
        boolean findStudentNumberDataFlag = false;
        boolean findStudentSubjectDataFlag = false;
        boolean noCourseFlag = false;
        switch (Objects.requireNonNull(checkOneOrAllDataChooser.getSelectedItem()).toString()) {
            case "單科":
                String[] dataTemp = sourceString.split(" ");
                if (dataTemp.length != 2) {
                    JOptionPane.showMessageDialog(null, "未知輸入", "未知輸入錯誤", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String studentNumber = dataTemp[0];
                String subject = dataTemp[1];
                for (StudentData<String, TreeMap<String, Integer>, String, Integer> stringTreeMapStringIntegerStudentData : studentDataArray) {
                    if (stringTreeMapStringIntegerStudentData.getStudentTreeMap().size() == 0) {
                        showStudentDataArea.append(studentNumber + " 此學生並無修課\n");
                        noCourseFlag = true;
                        break;
                    }
                    if (stringTreeMapStringIntegerStudentData.getStudentNumber().equals(studentNumber)) {
                        if (stringTreeMapStringIntegerStudentData.getStudentTreeMap().get(subject) != null) {
                            String temp = studentNumber +
                                    " 學生的 " +
                                    subject +
                                    " 分數為 " +
                                    stringTreeMapStringIntegerStudentData.getStudentTreeMap().get(subject) +
                                    "\n";
                            showStudentDataArea.append(temp);
                            findStudentSubjectDataFlag = true;
                        }
                        findStudentNumberDataFlag = true;
                    }
                }
                if (!findStudentNumberDataFlag && !noCourseFlag) {
                    showStudentDataArea.append("無 " + sourceString + " 此資料\n");
                } else if (!findStudentSubjectDataFlag && !noCourseFlag)
                    showStudentDataArea.append("無 " + studentNumber + " 的 " + subject + " 科目 成績\n");
                break;
            case "全部":
                for (StudentData<String, TreeMap<String, Integer>, String, Integer> stringTreeMapStringIntegerStudentData : studentDataArray) {
                    if (stringTreeMapStringIntegerStudentData.getStudentTreeMap().size() == 0) {
                        showStudentDataArea.append(sourceString + " 此學生並無修課\n");
                        noCourseFlag = true;
                        break;
                    }
                    if (stringTreeMapStringIntegerStudentData.getStudentNumber().equals(sourceString)) {
                        findStudentNumberDataFlag = true;
                        String temp = sourceString +
                                " 學生的 " +
                                " 分數為 " +
                                stringTreeMapStringIntegerStudentData.getAllDataNoStudentNumber() +
                                "\n";
                        showStudentDataArea.append(temp);
                    }
                }
                if (!findStudentNumberDataFlag && !noCourseFlag)
                    showStudentDataArea.append("無 " + sourceString + " 此資料\n");
        }
    }
}
