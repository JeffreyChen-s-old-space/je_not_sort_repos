package util.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RadixSort implements SortAlgorithm {


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
