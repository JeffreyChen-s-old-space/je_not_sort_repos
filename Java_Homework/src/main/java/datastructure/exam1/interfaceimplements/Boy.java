package datastructure.exam1.interfaceimplements;

public interface Boy extends Human {
    @Override
    default void printType() {
        System.out.println("Boy");
    }
}
