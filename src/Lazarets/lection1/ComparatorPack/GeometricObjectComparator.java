package Lazarets.lection1.ComparatorPack;

import java.util.Comparator;

/**
 * User: Tony
 * Date: 18.09.2015
 * Time: 11:25
 */
public class GeometricObjectComparator implements Comparator<GeometricObject> {
    @Override
    public int compare(GeometricObject o1, GeometricObject o2) {
        double area1 = o1.getArea();
        double area2 = o2.getArea();
        if (area1 < area2)
            return -1;
        else if (area1 == area2)
            return 0;
        else
            return 1;
    }
}
