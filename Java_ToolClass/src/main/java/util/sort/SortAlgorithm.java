package util.sort;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public interface SortAlgorithm {

    /**
     * @param unsorted the array we want to sort
     * @return a sorted array
     */
    <T extends Comparable<T>> T[] sort(T[] unsorted);

    /**
     * @param unsorted the array we want to sort
     * @return a sorted array
     */
    default <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
        return Arrays.asList(sort(unsorted.toArray((T[]) new Comparable[unsorted.size()])));
    }

    String getSortData();

}
