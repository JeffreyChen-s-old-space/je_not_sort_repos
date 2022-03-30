package datastructure.hw1.OrderList.add;

public class OrderList_Test {

    public static void main(String[] argv) {
        OrderList<String> orderList = new OrderList<>();
        orderList.add("H");
        orderList.add("G");
        orderList.add("D");
        orderList.add("C");
        orderList.add("B");
        orderList.add("A");
        orderList.add("E");
        orderList.add("F");
        orderList.add("I");
        orderList.add("Z");
        orderList.add("Y");
        orderList.add("X");
        System.out.println(orderList.toString());

        OrderList<Integer> orderList1 = new OrderList<>();
        orderList1.add(50);
        orderList1.add(30);
        orderList1.add(40);
        orderList1.add(10);
        orderList1.add(20);
        orderList1.add(90);
        orderList1.add(100);
        orderList1.add(80);
        orderList1.add(60);
        orderList1.add(70);
        System.out.println(orderList1.toString());

        OrderList<Character> characterOrderList = new OrderList<>();
        characterOrderList.add('c');
        characterOrderList.add('d');
        characterOrderList.add('a');
        characterOrderList.add('e');
        System.out.println(characterOrderList.toString());

    }
}
