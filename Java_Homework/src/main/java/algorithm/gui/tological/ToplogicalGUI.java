package algorithm.gui.tological;

import algorithm.gui.GUIFather;
import algorithm.util.graph.ToplogicalSort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ToplogicalGUI extends GUIFather {
    private ToplogicalSort toplogicalSort = new ToplogicalSort();
    private JPanel jPanel;
    private JTextArea outputTextArea;
    private JButton calculator;
    private JTextField inputTextField;

    public ToplogicalGUI(String windowName) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);

        calculator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputTextField.getText() != null && !inputTextField.getText().equals("")) {
                    outputTextArea.append(calculatorGraph(inputTextField.getText()) + "\n");
                } else
                    JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (inputTextField.getText() != null && !inputTextField.getText().equals("")) {
                        outputTextArea.append(calculatorGraph(inputTextField.getText()) + "\n");
                    } else
                        JOptionPane.showMessageDialog(null, "未知資料", "資料錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private String calculatorGraph(String source) {
        source = source.trim();
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        String[] temp = source.replaceFirst(",", ":").split(":");
        int numOfCourse = Integer.parseInt(temp[0]);
        for (int i = 0; i < numOfCourse; ++i)
            arrayList.add(new ArrayList<>());
        String data = temp[1].trim();
        data = data.replaceFirst("\\[", "");
        int last = data.lastIndexOf("]");
        data = data.substring(0, last);
        data = data.replace("],[", ":").replace("[", "").replace("]", "");
        temp = data.split(":");
        for (String a : temp) {
            String[] tempCourse = a.split(",");
            arrayList.get(Integer.parseInt(tempCourse[0])).add(Integer.valueOf(tempCourse[1]));
        }
        toplogicalSort = new ToplogicalSort();
        String answer = Arrays.toString(toplogicalSort.find(numOfCourse, arrayList));
        System.out.println(answer);
        return answer;
    }


    public static void main(String[] argv) {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        //String arr = "2, [[1,0]]";
        String arr = "4, [[1,0],[2,0],[3,1],[3,2]]";
        arr = arr.replaceFirst(",", ":");
        String[] temp = arr.split(":");
        System.out.println(Arrays.toString(temp));
        int numOfCourse = Integer.parseInt(temp[0]);
        for (int i = 0; i < numOfCourse; ++i)
            arrayList.add(new ArrayList<>());
        String data = temp[1].trim();
        data = data.replaceFirst("\\[", "");
        int last = data.lastIndexOf("]");
        data = data.substring(0, last);
        data = data.replace("],[", ":").replace("[", "").replace("]", "");
        temp = data.split(":");
        for (String a : temp) {
            String[] tempCourse = a.split(",");
            arrayList.get(Integer.parseInt(tempCourse[0])).add(Integer.valueOf(tempCourse[1]));
        }
        ToplogicalSort toplogicalSort = new ToplogicalSort();
        System.out.println(Arrays.toString(toplogicalSort.find(numOfCourse, arrayList)));

    }
}
