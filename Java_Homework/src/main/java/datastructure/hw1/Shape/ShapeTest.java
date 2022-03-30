package datastructure.hw1.Shape;

import java.util.Scanner;

public class ShapeTest {
    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);
        Shape shape;
        shape = new RtTriangle(scanner.nextDouble(), scanner.nextDouble(), scanner.next());
        System.out.println(shape.getShapeName());
        System.out.println(shape.computeArea());
        System.out.println(shape.computePerimeter());
        shape.readShapeData(scanner.nextDouble(), scanner.nextDouble());
        System.out.println(shape.toString());
        scanner.close();
    }
}
