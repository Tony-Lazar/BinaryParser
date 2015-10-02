package Lazarets.FeistelNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * User: Tony
 * Date: 15.09.2015
 * Time: 18:21
 */
public class FeistelNetwork {

    static Character[] abc = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н',
            'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
            'э', 'ю', 'я',
            '_', ' ', '.', ',', '!', '-',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    static ArrayList<Character> abcArray = new ArrayList<Character>(Arrays.asList(abc));
    static Scanner scanner = new Scanner(System.in);
    static StringBuilder result;
    static StringBuilder message;
    static StringBuilder key;
    static int multiplicity = 4;
    static int keyLength = 2;
    static int minimalMessageLength = 4;
    static String encryptFileName = "encrypt.txt";
    static String decryptFileName = "decrypt.txt";
    static String keyFileName = "key.txt";
    static int rounds;



    public static void main(String[] args) {
        menu();
    }


    public static void menu() {
        System.out.println("Введите кол-во раундов");
        rounds = Integer.parseInt(scanner.nextLine());
        System.out.println("Что будете делать?");
        System.out.println("1 - зашифровать сообщение");
        System.out.println("2 - расшифровать сообщение");
        String result = scanner.nextLine();
        if (result.equals("1")) {
            menuFromWhere(1);
            encrypt();
        }
        else if (result.equals("2"))
            menuFromWhere(2);
        else
            menu();
    }

    public static void menuFromWhere(int mode) {
        System.out.println("Откуда брать сообщение?");
        System.out.println("1 - будет введено с клавиатуры");
        System.out.println("2 - будет считано из файла");
        String result = scanner.nextLine();
        if (result.equals("1"))
            if (mode == 1)
                menuEncryptFromConsole();
            else
                menuDecryptFromConsole();
        else if (result.equals("2"))
            if (mode == 1)
                menuEncryptFromFile();
            else
                menuDecryptFromFile();
        else
            menuFromWhere(mode);
    }

    public static void menuEncryptFromConsole() {
        System.out.println("Введите сообщение для зашифровки");
        message = new StringBuilder().append(scanner.nextLine());

        checkMessageLength(message, 1);
        checkMessageMultiplicity(message);

        System.out.println("Сообщение считано успешно!");
        menuKeyFromWhere();
    }

    public static void menuEncryptFromFile() {
        System.out.println("Сообщение для зашифровки считывается из файла...");
        try {
            if (new File(decryptFileName).exists()) {
                message.append(new Scanner(new File(decryptFileName)).nextLine());
                checkMessageLength(message, 2);
                checkMessageMultiplicity(message);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Сообщение считано успешно!");
        menuKeyFromWhere();
    }

    public static void menuDecryptFromConsole() {
        System.out.println("Введите сообщение для расшифровки");
        message = new StringBuilder().append(scanner.nextLine());

        checkMessageLength(message, 3);
        checkMessageMultiplicity(message);

        System.out.println("Введите ключ");
    }

    public static void menuDecryptFromFile() {
        System.out.println("Сообщение для расшифровки считывается из файла...");
        try {
            if (new File(encryptFileName).exists()) {
                message.append(new Scanner(new File(encryptFileName)).nextLine());
                checkMessageLength(message, 4);
                checkMessageMultiplicity(message);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Сообщение считано успешно!");
        menuKeyFromWhere();
    }

    public static void checkMessageLength(StringBuilder message, int mode) {
        if (message.length() < minimalMessageLength) {
            System.out.println("Сообщение слишком короткое!");
            message.delete(0, message.length());

            switch (mode) {
                case 1: menuEncryptFromConsole(); return;
                case 2: menuEncryptFromFile(); return;
                case 3: menuDecryptFromConsole(); return;
                case 4: menuDecryptFromFile(); return;
            }
        }
    }

    public static void checkMessageMultiplicity(StringBuilder message) {
        if (message.length() % multiplicity != 0) {
            for (int i = 0; i < multiplicity - message.length() % multiplicity; i++) {
                message.append("0");
            }
            System.out.println("Сообщение было дополнено чтобы длина стала кратной " + multiplicity);
        }
    }

    public static void menuKeyFromWhere() {
        System.out.println("Откуда брать ключ?");
        System.out.println("1 - будет введен с клавиатуры");
        System.out.println("2 - будет считан из файла");
        String result = scanner.nextLine();
        if (result.equals("1"))
            menuKeyFromConsole();
        else if (result.equals("2"))
            menuKeyFromFile();
        else
            menuKeyFromWhere();
    }

    public static void menuKeyFromConsole() {
        System.out.println("Введите ключ длиной в " + keyLength + " символа");
        key = new StringBuilder().append(scanner.nextLine());
        checkKeyLength(key);
        System.out.println("Ключ считан успешно!");
    }

    public static void menuKeyFromFile() {
        System.out.println("Ключ считывается из файла...");
        try {
            if (new File(keyFileName).exists()) {
                key.append(new Scanner(new File(keyFileName)).nextLine());
                checkKeyLength(key);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Ключ считан успешно!");
    }

    public static void checkKeyLength(StringBuilder key) {
        if (key.length() != keyLength) {
            System.out.println("Длина ключа не равна " + keyLength + " символа");
            key.delete(0 , key.length());
            menuKeyFromConsole();
        }
    }

    public static void encrypt() {
        String[] messageParts = new String[message.length() / 4];
        String[] keyParts = new String[key.length() / 2];
        int keyIndex = 0;
        int curRounds = rounds;
        int indexA0 = 0;
        int indexA1 = 0;
        int indexB0 = 0;
        int indexB1 = 0;
        int keyIndex0 = 0;
        int keyIndex1 = 0;
        for (int i = 0; i < messageParts.length; i++) {
            messageParts[i] = String.valueOf(message.charAt(i * 4)) +
                    String.valueOf(message.charAt(i * 4 + 1)) +
                    String.valueOf(message.charAt(i * 4 + 2)) +
                    String.valueOf(message.charAt(i * 4 + 3));
        }

        for (int i = 0; i < keyParts.length; i++) {
            keyParts[i] = String.valueOf(key.charAt(i * 2)) +
                    String.valueOf(key.charAt(i * 2 + 1));
        }

        for (int i = 0; i < messageParts.length; i++) {
            String A = messageParts[i].substring(0, 2);
            String B = messageParts[i].substring(2, 4);

            while (curRounds > 0) {
                indexA0 = abcArray.indexOf(A.charAt(0));
                indexA1 = abcArray.indexOf(A.charAt(1));
                keyIndex0 = abcArray.indexOf(keyParts[keyIndex].charAt(0));
                keyIndex1 = abcArray.indexOf(keyParts[keyIndex].charAt(1));
                String newA;
                if (indexA0 + keyIndex0 >= abcArray.size() && indexA1 + keyIndex1 >= abcArray.size()) {
                    newA = abcArray.get(keyIndex0 - (abcArray.size() - 1 - indexA0)) + "" +
                            abcArray.get(keyIndex1 - (abcArray.size() - 1 - indexA1));
                } else if (indexA0 + keyIndex0 >= abcArray.size() && indexA1 + keyIndex1 < 0) {
                    newA = abcArray.get(keyIndex0 - (abcArray.size() - 1 - indexA0)) + "" +
                            abcArray.get(abcArray.size() - 1 + indexA1 + keyIndex1);
                } else if (indexA0 + keyIndex0 >= abcArray.size()) {
                    newA = abcArray.get(keyIndex0 - (abcArray.size() - 1 - indexA0)) + "" +
                            abcArray.get(indexA1 + keyIndex1);
                } else if (indexA0 + keyIndex0 < 0 && indexA1 + keyIndex1 >= abcArray.size()) {
                    newA = abcArray.get(abcArray.size() - 1 + indexA0 + keyIndex0) + "" +
                            abcArray.get(keyIndex1 - (abcArray.size() - 1 - indexA1));
                } else if (indexA0 + keyIndex0 < 0 && indexA1 + keyIndex1 < 0) {
                    newA = abcArray.get(abcArray.size() - 1 + indexA0 + keyIndex0) + "" +
                            abcArray.get(abcArray.size() - 1 + indexA1 + keyIndex1);
                } else if (indexA0 + keyIndex0 < 0) {
                    newA = abcArray.get(abcArray.size() - 1 + indexA0 + keyIndex0) + "" +
                            abcArray.get(indexA1 + keyIndex1);
                } else if (indexA1 + keyIndex1 >= abcArray.size()) {
                    newA = abcArray.get(indexA0 + keyIndex0) + "" +
                            abcArray.get(keyIndex1 - (abcArray.size() - 1 - indexA1));
                } else if (indexA1 + keyIndex1 < 0) {
                    newA = abcArray.get(indexA0 + keyIndex0) + "" +
                            abcArray.get(abcArray.size() - 1 + indexA1 + keyIndex1);
                } else {
                    newA = abcArray.get(indexA0 + keyIndex0) + "" +
                            abcArray.get(indexA1 + keyIndex1);
                }

                keyIndex++;
                if (keyIndex >= keyParts.length)
                    keyIndex = 0;

                indexA0 = abcArray.indexOf(newA.charAt(0));
                indexA1 = abcArray.indexOf(newA.charAt(1));
                indexB0 = abcArray.indexOf(B.charAt(0));
                indexB1 = abcArray.indexOf(B.charAt(1));

                if (indexA0 + indexB0 >= abcArray.size() && indexA1 + indexB1 >= abcArray.size()) {
                    newA = abcArray.get(indexB0 - (abcArray.size() - 1 - indexA0)) + "" +
                            abcArray.get(indexB1 - (abcArray.size() - 1 - indexA1));
                } else if (indexA0 + indexB0 >= abcArray.size() && indexA1 + indexB1 < 0) {
                    newA = abcArray.get(indexB0 - (abcArray.size() - 1 - indexA0)) + "" +
                            abcArray.get(abcArray.size() - 1 + indexA1 + indexB1);
                } else if (indexA0 + indexB0 >= abcArray.size()) {
                    newA = abcArray.get(indexB0 - (abcArray.size() - 1 - indexA0)) + "" +
                            abcArray.get(indexA1 + indexB1);
                } else if (indexA0 + indexB0 < 0 && indexA1 + indexB1 >= abcArray.size()) {
                    newA = abcArray.get(abcArray.size() - 1 + indexA0 + indexB0) + "" +
                            abcArray.get(indexB1 - (abcArray.size() - 1 - indexA1));
                } else if (indexA0 + indexB0 < 0 && indexA1 + indexB1 < 0) {
                    newA = abcArray.get(abcArray.size() - 1 + indexA0 + indexB0) + "" +
                            abcArray.get(abcArray.size() - 1 + indexA1 + indexB1);
                } else if (indexA0 + indexB0 < 0) {
                    newA = abcArray.get(abcArray.size() - 1 + indexA0 + indexB0) + "" +
                            abcArray.get(indexA1 + indexB1);
                } else if (indexA1 + indexB1 >= abcArray.size()) {
                    newA = abcArray.get(indexA0 + indexB0) + "" +
                            abcArray.get(indexB1 - (abcArray.size() - 1 - indexA1));
                } else if (indexA1 + indexB1 < 0) {
                    newA = abcArray.get(indexA0 + indexB0) + "" +
                            abcArray.get(abcArray.size() - 1 + indexA1 + indexB1);
                } else {
                    newA = abcArray.get(indexA0 + indexB0) + "" +
                            abcArray.get(indexA1 + indexB1);
                }
                String newB = A;
                if (curRounds != 1) {
                    B = newA;
                    A = newB;
                } else {
                    B = newB;
                    A = newA;
                }
                curRounds--;
            }
            curRounds = rounds;
            messageParts[i] = A + B;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < messageParts.length; i++) {
            for (int j = 0; j < 4; j++) {
                result.append(messageParts[i].charAt(j));
            }
        }
        writeToFile(result, 2);
    }

    public static void decrypt() {

    }

    public static void writeToFile(StringBuilder message, int mode) {
        String filename;
        if (mode == 1)
            filename = decryptFileName;
        else if (mode == 2)
            filename = encryptFileName;
        else
            filename = keyFileName;


        try {
            if (!new File(filename).exists())
                new File(filename).createNewFile();
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < message.length(); i++) {
                writer.write(message.charAt(i));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//говно, залупа, пенис, хер, давалка, хуй, блядина. Головка, шлюха, жопа, член
//раз два три четыре пять шесть семь восемь девять десять 58 61 64