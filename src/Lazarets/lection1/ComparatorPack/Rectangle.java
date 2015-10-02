package Lazarets.lection1.ComparatorPack;

/**
 * User: Tony
 * Date: 18.09.2015
 * Time: 11:23
 */
public class Rectangle extends GeometricObject {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double getArea() {
        return sideA * sideB;
    }
}
