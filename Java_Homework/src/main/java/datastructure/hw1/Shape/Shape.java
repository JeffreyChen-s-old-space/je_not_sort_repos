package datastructure.hw1.Shape;

public abstract class Shape {

    protected String shapeName;

    public abstract String getShapeName();

    public abstract double computeArea();

    public abstract double computePerimeter();

    public abstract void readShapeData(double data1, double data2);

    @Override
    public String toString() {
        return this.shapeName;
    }
}
