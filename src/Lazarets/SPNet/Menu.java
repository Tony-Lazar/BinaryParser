package Lazarets.SPNet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * User: Tony
 * Date: 29.09.2015
 * Time: 16:52
 */
public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static StringBuilder message = new StringBuilder();
    private static StringBuilder key = new StringBuilder();
    private static String keyFileName;
    private static String encryptFileName;
    private static String decryptFileName;
    private static Decrypt encryptClass;

    public static void menu(String innerKeyFileName, String innerEncryptFileName, String innerDecryptFileName, Decrypt innerClass) {
        keyFileName = innerKeyFileName;
        encryptFileName = innerEncryptFileName;
        decryptFileName = innerDecryptFileName;
        encryptClass = innerClass;
        System.out.println("Что будете делать?");
        System.out.println("1 - зашифровать сообщение");
        System.out.println("2 - расшифровать сообщение");
        String result = scanner.nextLine();
        if (result.equals("1")) {
            menuFromWhere(1);
        }
        else if (result.equals("2"))
            menuFromWhere(2);
        else
            menu(innerKeyFileName, innerEncryptFileName, innerDecryptFileName, innerClass);
    }

    private static void menuFromWhere(int mode) {
        System.out.println("Откуда брать сообщение?");
        System.out.println("1 - будет введено с клавиатуры");
        System.out.println("2 - будет считано из файла");
        String result = scanner.nextLine();
        if (result.equals("1"))
            if (mode == 1) {
                menuEncryptFromConsole();
                encryptClass.encrypt(message.toString(), key.toString());
            }
            else {
                menuDecryptFromConsole();
                encryptClass.decrypt(message.toString(), key.toString());
            }
        else if (result.equals("2"))
            if (mode == 1) {
                menuEncryptFromFile();
                encryptClass.encrypt(message.toString(), key.toString());
            }
            else {
                menuDecryptFromFile();
                encryptClass.decrypt(message.toString(), key.toString());
            }
        else
            menuFromWhere(mode);
    }

    private static void menuEncryptFromConsole() {
        System.out.println("Введите сообщение для зашифровки");
        message = new StringBuilder().append(scanner.nextLine());

        System.out.println("Сообщение считано успешно!");
        menuKeyFromWhere();
    }

    private static void menuEncryptFromFile() {
        System.out.println("Сообщение для зашифровки считывается из файла...");
        try {
            if (new File(decryptFileName).exists()) {
                message.append(new Scanner(new File(decryptFileName)).nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Сообщение считано успешно!");
        menuKeyFromWhere();
    }

    private static void menuDecryptFromConsole() {
        System.out.println("Введите сообщение для расшифровки");
        message = new StringBuilder().append(scanner.nextLine());
        System.out.println("Сообщение считано успешно!");
        menuKeyFromWhere();
    }

    private static void menuDecryptFromFile() {
        System.out.println("Сообщение для расшифровки считывается из файла...");
        try {
            if (new File(encryptFileName).exists()) {
                message.append(new Scanner(new File(encryptFileName)).nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Сообщение считано успешно!");
        menuKeyFromWhere();
    }

    private static void menuKeyFromWhere() {
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

    private static void menuKeyFromConsole() {
        System.out.println("Введите ключ");
        key = new StringBuilder().append(scanner.nextLine());
        System.out.println("Ключ считан успешно!");
    }

    private static void menuKeyFromFile() {
        System.out.println("Ключ считывается из файла...");
        try {
            if (new File(keyFileName).exists()) {
                Scanner tempScan = new Scanner(new File(keyFileName));
                while(tempScan.hasNextLine())
                    key.append(tempScan.nextLine());
            }
            encryptClass.checkKey(key.toString());
            System.out.println("Ключ считан успешно!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ReadingKeyException e) {
            System.out.println("Ошибка при чтении ключа");
        }
    }

    private static void writeToFile(StringBuilder message, int mode) {
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
