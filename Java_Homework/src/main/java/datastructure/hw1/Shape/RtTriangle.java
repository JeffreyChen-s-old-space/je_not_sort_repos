package datastructure.hw1.Shape;

public class RtTriangle extends Shape {

    private double base, height;

    public RtTriangle(double base, double height, String shapeName) {
        this.base = base;
        this.height = height;
        this.shapeName = shapeName;
    }

    @Override
    public String getShapeName() {
        return this.shapeName;
    }

    @Override
    public double computeArea() {
        return (this.base * this.height) / 2;
    }

    @Override
    public double computePerimeter() {
        return base * 3;
    }

    @Override
    public void readShapeData(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public String toString() {
        return this.shapeName + " base:" + base + " height:" + height;
    }
}
