package datastructure.exam1.interfaceimplements;

public class JE_Chen implements Human {

    public static void main(String[] argv) {
        Human human = new JE_Chen();
        human.printType();
        human = new Boy() {
            @Override
            public void printType() {
                System.out.println("Boy");
            }
        };
        human.printType();
        human = new Girl() {
            @Override
            public void printType() {
                System.out.println("Girl");
            }
        };
        human.printType();
        human = new Another();
        human.printType();
    }
}