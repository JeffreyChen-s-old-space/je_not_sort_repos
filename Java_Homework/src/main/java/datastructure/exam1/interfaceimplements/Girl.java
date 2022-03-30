package datastructure.exam1.interfaceimplements;

public interface Girl extends Human {

    @Override
    default void printType() {
        System.out.println("Girl");
    }

}
