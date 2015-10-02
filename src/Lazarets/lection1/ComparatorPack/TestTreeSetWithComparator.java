package Lazarets.lection1.ComparatorPack;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * User: Tony
 * Date: 18.09.2015
 * Time: 11:24
 */
public class TestTreeSetWithComparator {
    public static void main(String[] args) {
    Comparator comparator = new GeometricObjectComparator();
    Set<GeometricObject> set = new TreeSet<GeometricObject>(comparator);
        set.add(new Rectangle(4, 5));
        set.add(new Circle(40));
        set.add(new Circle(40));
        set.add(new Rectangle(4, 1));
        System.out.println("A sorted set of geometric objects");
        for (GeometricObject element : set) {
            System.out.println("area = " + element.getArea());
        }


    }

}
