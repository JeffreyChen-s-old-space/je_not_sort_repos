package lab;

public class Lab3 {

    private static int binarySearchForSortedArray(int[] sortedArray, int target, int start, int end) {
        if (start < 0 || end >= sortedArray.length) {
            return -1;
        }
        while (start <= end) {
            int mid = (start + end) / 2;
            if (sortedArray[mid] == target) {
                return mid;
            } else if (target > sortedArray[mid]) {
                start = mid + 1;
            } else if (target < sortedArray[mid]) {
                end = mid - 1;
            }
        }
        return -1;
    }

}
