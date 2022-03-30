package algorithm.util.sort;

import java.util.*;

public final class SortUtils {

    /**
     * @param array  the array which element we want to swap
     * @param index1 first element
     * @param index2 second element
     * @return true always
     */
    static <T> boolean swap(T[] array, int index1, int index2) {
        T swap = array[index1];
        array[index1] = array[index2];
        array[index2] = swap;
        return true;
    }

    /**
     * @param first  first element
     * @param second second element
     * @return true if the first element is less than the second element
     */
    static <T extends Comparable<T>> boolean less(T first, T second) {
        return first.compareTo(second) < 0;
    }

    /**
     * @param first  first element
     * @param second second element
     * @return true if the first element is more than the second element
     */
    static <T extends Comparable<T>> boolean more(T first, T second) {
        return first.compareTo(second) > 0;
    }

    /**
     * @param toPrint the array we want to print
     */
    static void print(List<?> toPrint) {
        for (Object object : toPrint) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    /**
     * @param toPrint the array we want to print
     */
    static void print(Object[] toPrint) {
        System.out.println(Arrays.toString(toPrint));
        System.out.println();
    }

    public static void print(int[] array, int arrayLength) {
        for (int i = 0; i < arrayLength; i++)
            System.out.print(array[i] + " ");
    }

    /**
     * @param unSortMap the map we want to sort
     * @param <Key>     generic type key
     * @param <Value>   generic type value
     * @return sorted map
     */
    public static <Key, Value extends Comparable<? super Value>> Map<Key, Value> sortMapByValue(Map<Key, Value> unSortMap) {
        List<Map.Entry<Key, Value>> entryArrayList = new ArrayList<>(unSortMap.entrySet());
        entryArrayList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        Map<Key, Value> result = new LinkedHashMap<>();
        for (Map.Entry<Key, Value> entry : entryArrayList) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
