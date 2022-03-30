package datastructure.exam1.interfaceimplements;

public interface Human {

    default void printType() {
        System.out.println("Human");
    }

}
