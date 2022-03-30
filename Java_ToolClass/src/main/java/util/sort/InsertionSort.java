package util.sort;

import static util.sort.SortUtils.more;

public class InsertionSort implements SortAlgorithm {

    private int swapCount = 0, compareCount = 0;


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
