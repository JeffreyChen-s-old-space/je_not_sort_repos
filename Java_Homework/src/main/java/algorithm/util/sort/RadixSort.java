package algorithm.util.sort;

import algorithm.student.StudentData;

import java.util.*;

import static algorithm.util.sort.SortUtils.print;

public class RadixSort implements SortAlgorithm {

    private static StudentData<String, TreeMap<String, Integer>, String, Integer>[] studentData = new StudentData[4];

    public static int getMax(int[] array, int arrayLength) {
        int maxNumber = array[0];
        for (int i = 0; i < arrayLength; i++)
            if (array[i] > maxNumber)
                maxNumber = array[i];
        return maxNumber;
    }

    public static void countSort(int[] array, int arrayLength, int exp) {
        int[] outputArray = new int[arrayLength];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);
        for (i = 0; i < arrayLength; i++) {
            count[(array[i] / exp) % 10]++;
        }
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (i = arrayLength - 1; i >= 0; i--) {
            outputArray[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }
        for (i = 0; i < arrayLength; i++)
            array[i] = outputArray[i];
    }

    public static void main(String[] argv) {
        RadixSort radixSort = new RadixSort();
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};
        int arrayLength = array.length;
        radixSort.sort(array, arrayLength);
        print(array, arrayLength);
        System.out.println();

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
        radixSort.sort(studentData);
        studentData[0].printStudentData(studentData);
    }

    public void sort(int[] array, int arrayLength) {
        int maxNumber = getMax(array, arrayLength);
        for (int exp = 1; maxNumber / exp > 0; exp *= 10)
            countSort(array, arrayLength, exp);
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        List<T> unsortedList = Arrays.asList(unsorted);
        unsortedList.sort(Comparator.reverseOrder());
        for (int i = 0; i < unsorted.length; i++) {
            unsorted[i] = unsortedList.get(i);
        }
        return unsorted;
    }

    @Override
    public String getSortData() {
        return "使用RadixSort排序";
    }

}
