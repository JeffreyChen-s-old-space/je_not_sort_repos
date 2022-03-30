package algorithm.student;

import javax.swing.*;
import java.util.HashMap;
import java.util.TreeMap;

public class StudentData<HashMapKey, HashMapValue extends TreeMap<TreeMapKey, TreeMapValue>, TreeMapKey, TreeMapValue>
        extends HashMap<String, TreeMap<TreeMapKey, TreeMapValue>>
        implements Comparable<StudentData<HashMapKey, HashMapValue, TreeMapKey, TreeMapValue>> {

    private final TreeMap<TreeMapKey, TreeMapValue> studentTreeMap;
    private final String studentNumber;
    private String sortUseType;

    public StudentData(TreeMap<TreeMapKey, TreeMapValue> studentTreeMap, String studentNumber, String sortUseType) {
        this.studentTreeMap = studentTreeMap;
        this.studentNumber = studentNumber.trim();
        this.sortUseType = sortUseType.trim();
        this.put(studentNumber, studentTreeMap);
    }


    /**
     * @param toPrint the StudentData we want to print
     */
    public void printStudentData(StudentData<HashMapKey, TreeMap<TreeMapKey, TreeMapValue>, TreeMapKey, TreeMapValue>[] toPrint) {
        for (StudentData<HashMapKey, TreeMap<TreeMapKey, TreeMapValue>, TreeMapKey, TreeMapValue> studentData : toPrint) {
            System.out.println(studentData.getAllData());
        }
        System.out.println();
    }

    /**
     * @param toPrint   the StudentData we want to print
     * @param jTextArea the textarea we want to show result
     */
    public void printStudentData(StudentData<HashMapKey, TreeMap<TreeMapKey, TreeMapValue>, TreeMapKey, TreeMapValue>[] toPrint, JTextArea jTextArea) {
        for (StudentData<HashMapKey, TreeMap<TreeMapKey, TreeMapValue>, TreeMapKey, TreeMapValue> studentData : toPrint) {
            jTextArea.append(studentData.getAllData() + "\n");
        }
        jTextArea.append("\n");
    }

    public void setSortUseType(String sortUseType) {
        this.sortUseType = sortUseType;
    }

    public Integer getSortUseGrade() {
        return (Integer) this.studentTreeMap.get(this.sortUseType);
    }

    public String getStudentNumber() {
        return this.studentNumber;
    }

    public TreeMap<TreeMapKey, TreeMapValue> getStudentTreeMap() {
        return this.studentTreeMap;
    }

    public String getAllData() {
        StringBuilder studentDataBuilder = new StringBuilder();
        studentDataBuilder.append(studentNumber).append(" ");
        for (TreeMapKey treeMapKey : studentTreeMap.keySet()) {
            studentDataBuilder.append(treeMapKey).append(" ");
            studentDataBuilder.append(studentTreeMap.get(treeMapKey)).append(" ");
        }
        return studentDataBuilder.toString();
    }

    public String getAllDataNoStudentNumber() {
        StringBuilder studentDataBuilder = new StringBuilder();
        for (TreeMapKey treeMapKey : studentTreeMap.keySet()) {
            studentDataBuilder.append(treeMapKey).append(" ");
            studentDataBuilder.append(studentTreeMap.get(treeMapKey)).append(" ");
        }
        return studentDataBuilder.toString();
    }

    @Override
    public int compareTo(StudentData studentData) {
        int thisCompareGrade = 0;
        int anotherCompareGrade = 0;
        if (this.studentTreeMap.get(this.sortUseType) != null)
            thisCompareGrade = (Integer) this.studentTreeMap.get(this.sortUseType);
        if (studentData.studentTreeMap.get(studentData.sortUseType) != null)
            anotherCompareGrade = (Integer) studentData.studentTreeMap.get(studentData.sortUseType);

        switch (this.sortUseType) {
            case "DS":
            case "DM":
            case "LA":
            case "OS":
            case "EN":
                return thisCompareGrade - anotherCompareGrade;
        }
        return thisCompareGrade - anotherCompareGrade;
    }

}
