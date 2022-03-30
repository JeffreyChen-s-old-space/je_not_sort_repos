package algorithm.util.sort;


import static algorithm.util.sort.SortUtils.print;

public class MergeSort implements SortAlgorithm {

    private int compareCount = 0, swapCount = 0;

    public static void main(String[] argv) {

        MergeSort mergeSort = new MergeSort();

        Integer[] integers = {3, 555, 4, 8, 6, 33, 22, 115, 3, 777, 55, 66, 123, 5, 65, 4, 654, 654, 65, 46, 51, 65, 16, 54, 89, 7, 8941, 4, 132, 869, 4, 65, 3, 64};
        mergeSort.sort(integers);
        print(integers);

        Integer[] sortedIntegers = {5, 4, 3};
        mergeSort.sort(sortedIntegers);
        print(sortedIntegers);

        Character[] characters = {'e', 'k', 'e', 'u', 'n'};
        mergeSort.sort(characters);
        print(characters);

    }

    /**
     * @param array the array we want to sort
     * @param left  array's first index
     * @param right array's last index
     */
    private <T extends Comparable<T>> void mergeSort(T[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    /**
     * @param array  the array to be sorted
     * @param left   first index of the array
     * @param middle middle index of the array
     * @param right  last index of the array
     */
    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> void merge(T[] array, int left, int middle, int right) {
        int length = right - left + 1;
        T[] temp = (T[]) new Comparable[length];
        int i = left;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= right) {
            if (array[i].compareTo(array[j]) > 0) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
            swapCount++;
            compareCount += 2;
        }
        while (i <= middle) {
            temp[k++] = array[i++];
            swapCount++;
            compareCount++;
        }
        while (j <= right) {
            temp[k++] = array[j++];
            compareCount++;
            swapCount++;
        }
        System.arraycopy(temp, 0, array, left, length);
    }

    /**
     * @param unsorted the array we want to sort
     * @return sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        mergeSort(unsorted, 0, unsorted.length - 1);
        /* when exec this class
        swapCount = 0;
        compareCount = 0;
         */
        return unsorted;
    }

    @Override
    public String getSortData() {
        String sortData = String.format("使用MergeSort Sort排序，系統完成排序共比較%d次，交換元素%d次", compareCount, swapCount);
        swapCount = 0;
        compareCount = 0;
        return sortData;
    }

}
