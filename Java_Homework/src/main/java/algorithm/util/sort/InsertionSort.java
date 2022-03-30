package algorithm.util.sort;

import algorithm.student.StudentData;

import java.util.HashMap;
import java.util.TreeMap;

import static algorithm.util.sort.SortUtils.more;
import static algorithm.util.sort.SortUtils.print;

public class InsertionSort implements SortAlgorithm {

    private static StudentData<String, TreeMap<String, Integer>, String, Integer>[] studentData = new StudentData[4];
    private int swapCount = 0, compareCount = 0;

    public static void main(String[] argv) {

        InsertionSort insertionSort = new InsertionSort();

        Integer[] integers = {3, 555, 4, 8, 6, 33, 354, 453, 3, 777, 55, 66, 123, 5, 65, 4, 654, 654, 65, 789, 51, 879, 16, 54, 89, 7, 8941, 4, 132, 869, 4, 65, 3, 64};
        insertionSort.sort(integers);
        print(integers);

        String[] strings = {"a", "x", "y", "z", "w"};
        insertionSort.sort(strings);
        print(strings);

        Integer[] sortedIntegers = {5, 4, 3};
        insertionSort.sort(sortedIntegers);
        print(sortedIntegers);

        Float[] floats = {3.7f, 8.5f, 7.7f, 6.5f};
        insertionSort.sort(floats);
        print(floats);

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
        TreeMap<String, Integer> student4TreeMap = new TreeMap<>();
        student4TreeMap.put("DS", 78);
        student4TreeMap.put("DM", 49);
        student4TreeMap.put("LA", 78);
        studentHashMap.put("97501", student1TreeMap);
        studentData[0] = (new StudentData<>(student1TreeMap, "97501", "DS"));
        studentHashMap.put("97502", student2TreeMap);
        studentData[1] = (new StudentData<>(student2TreeMap, "97502", "DS"));
        studentHashMap.put("97523", student3TreeMap);
        studentData[2] = (new StudentData<>(student3TreeMap, "97523", "DS"));
        studentHashMap.put("97511", student4TreeMap);
        studentData[3] = (new StudentData<>(student4TreeMap, "97511", "DS"));
        insertionSort.sort(studentData);
        studentData[0].printStudentData(studentData);
    }

    /**
     * @param unsorted the array we want to sort
     * @return sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        for (int index = 1; index < unsorted.length; index++) {
            T key = unsorted[index];
            int forward = index - 1;
            while (forward >= 0 && more(key, unsorted[forward])) {
                swapCount++;
                compareCount++;
                unsorted[forward + 1] = unsorted[forward];
                forward--;
            }
            compareCount++;
            unsorted[forward + 1] = key;
        }
        /* when exec this class
        swapCount = 0;
        compareCount = 0;
        */
        return unsorted;
    }

    @Override
    public String getSortData() {
        String sortData = String.format("使用Insertion Sort排序，系統完成排序共比較%d次，交換元素%d次", compareCount, swapCount);
        swapCount = 0;
        compareCount = 0;
        return sortData;
    }

}
