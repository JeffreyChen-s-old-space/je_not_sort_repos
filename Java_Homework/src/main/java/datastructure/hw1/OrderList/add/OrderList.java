package datastructure.hw1.OrderList.add;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class OrderList<E extends Comparable<? super E>> {

    private final List<E> myList = new LinkedList<>();

    public void add(E element) {
        ListIterator<E> listIterator = myList.listIterator(myList.size());
        while (listIterator.hasPrevious()) {
            if (element.compareTo(listIterator.previous()) > 0) {
                listIterator.next();
                listIterator.add(element);
                return;
            }
        }
        listIterator.add(element);
    }

    @Override
    public String toString() {
        return myList.toString();
    }
}

