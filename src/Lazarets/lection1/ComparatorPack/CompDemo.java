package Lazarets.lection1.ComparatorPack;

import java.util.TreeSet;

/**
 * User: Tony
 * Date: 18.09.2015
 * Time: 11:11
 */
public class CompDemo {
    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<String>(new MyComp());

        ts.add("C");
        ts.add("A");
        ts.add("B");
        ts.add("E");
        ts.add("F");
        ts.add("D");

        for (String element : ts) {
            System.out.print(element + " ");
        }
        System.out.println();

    }
}
