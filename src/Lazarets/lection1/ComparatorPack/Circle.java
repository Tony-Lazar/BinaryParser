package Lazarets.lection1.ComparatorPack;

/**
 * User: Tony
 * Date: 18.09.2015
 * Time: 11:24
 */
public class Circle extends GeometricObject {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return 2 * 3.14 * radius * radius;
    }
}
