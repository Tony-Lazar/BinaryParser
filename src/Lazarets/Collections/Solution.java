package Lazarets.Collections;

import java.util.*;

/**
 * User: Tony
 * Date: 24.09.2015
 * Time: 19:10
 */
public class Solution {
    static LinkedList<String> linkedList = new LinkedList<String>();
    static ArrayList<String> arrayList = new ArrayList<String>();
    static int count = 100000;
    public static void main(String[] args) {
        // LINKED LIST ADD TO END
        Date linked = new Date();
        System.out.println("Начало добавления " + count + " эл-ов в конец linkedList");
        for (int i = 0; i < count; i++) {
            linkedList.add("i = " + i);
        }
        System.out.println("Добавление окончено, заняло " + (new Date().getTime() - linked.getTime()) + " миллисекунд");
        System.out.println();

        //ARRAY LIST ADD TO END
        Date array = new Date();
        System.out.println("Начало добавления " + count + " эл-ов в конец arrayList");
        for (int i = 0; i < count; i++) {
            arrayList.add("i = " + i);
        }
        System.out.println("Добавление окончено, заняло " + (new Date().getTime() - array.getTime()) + " миллисекунд");
        System.out.println();


        //LINKED LIST ADD TO BEGINNING
        linked = new Date();
        System.out.println("Начало добавления " + count + " эл-ов в начало linkedList");
        for (int i = 0; i < count; i++) {
            linkedList.add(0, "i = " + i);
        }
        System.out.println("Добавление окончено, заняло " + (new Date().getTime() - linked.getTime()) + " миллисекунд");
        System.out.println();

        //ARRAY LIST ADD TO BEGINNING
        array = new Date();
        System.out.println("Начало добавления " + count + " эл-ов в начало arrayList");
        for (int i = 0; i < count; i++) {
            arrayList.add(0, "i = " + i);
        }
        System.out.println("Добавление окончено, заняло " + (new Date().getTime() - array.getTime()) + " миллисекунд");
        System.out.println();

        //LINKED LIST ADD TO CENTER
        linked = new Date();
        System.out.println("Начало добавления " + count + " эл-ов в центр linkedList");
        ArrayList<String> newList = new ArrayList<String>(linkedList);
        for (int i = 0; i < count; i++) {
            newList.add(newList.size() / 2, "i = " + i);
        }
        linkedList = new LinkedList<String>(newList);
        System.out.println("Добавление окончено, заняло " + (new Date().getTime() - linked.getTime()) + " миллисекунд");
        System.out.println();

        //ARRAY LIST ADD TO CENTER
        array = new Date();
        System.out.println("Начало добавления " + count + " эл-ов в центр arrayList");
        for (int i = 0; i < count; i++) {
            arrayList.add(arrayList.size() / 2, "i = " + i);
        }
        System.out.println("Добавление окончено, заняло " + (new Date().getTime() - array.getTime()) + " миллисекунд");
        System.out.println();


        //LINKED LIST REMOVE FROM END
        linked = new Date();
        System.out.println("Начало удаления " + count + " эл-ов c конца linkedList");
        for (int i = 0; i < count; i++) {
            linkedList.removeLast();
        }
        System.out.println("Удаление окончено, заняло " + (new Date().getTime() - linked.getTime()) + " миллисекунд");
        System.out.println();

        //ARRAY LIST REMOVE FROM END
        array = new Date();
        System.out.println("Начало удаления " + count + " эл-ов c конца arrayList");
        for (int i = 0; i < count; i++) {
            arrayList.remove(arrayList.size() - 1);
        }
        System.out.println("Удаление окончено, заняло " + (new Date().getTime() - array.getTime()) + " миллисекунд");
        System.out.println();


        //LINKED LIST REMOVE FROM BEGINNING
        linked = new Date();
        System.out.println("Начало удаления " + count + " эл-ов из начала linkedList");
        for (int i = 0; i < count; i++) {
            linkedList.remove(0);
        }
        System.out.println("Удаление окончено, заняло " + (new Date().getTime() - linked.getTime()) + " миллисекунд");
        System.out.println();

        //ARRAY LIST REMOVE FROM BEGINNING
        array = new Date();
        System.out.println("Начало удаления " + count + " эл-ов из начала arrayList");
        for (int i = 0; i < count; i++) {
            arrayList.remove(0);
        }
        System.out.println("Удаление окончено, заняло " + (new Date().getTime() - array.getTime()) + " миллисекунд");
        System.out.println();


        //LINKED LIST REMOVE FROM CENTER
        linked = new Date();
        System.out.println("Начало удаления " + count + " эл-ов из середины linkedList");
        for (int i = 0; i < count; i++) {
            linkedList.remove(linkedList.size() / 2);
        }
        System.out.println("Удаление окончено, заняло " + (new Date().getTime() - linked.getTime()) + " миллисекунд");
        System.out.println();

        //ARRAY LIST REMOVE FROM CENTER
        array = new Date();
        System.out.println("Начало удаления " + count + " эл-ов из середины arrayList");
        for (int i = 0; i < count; i++) {
            arrayList.remove(arrayList.size() / 2);
        }
        System.out.println("Удаление окончено, заняло " + (new Date().getTime() - array.getTime()) + " миллисекунд");
        System.out.println();


    }
}
