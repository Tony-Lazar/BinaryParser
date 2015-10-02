package Lazarets.lection1.ComparatorPack;

import java.util.Comparator;

/**
 * User: Tony
 * Date: 18.09.2015
 * Time: 11:11
 */
public class MyComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String aStr, bStr;
        aStr = o1;
        bStr = o2;
        return bStr.compareTo(aStr);
    }
}
