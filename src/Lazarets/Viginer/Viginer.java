package Lazarets.Viginer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tony on 02.09.2015.
 */
public class Viginer {
    static Scanner scanner = new Scanner(System.in);
    static String encryptFileName = "encryptText.txt";
    static String decryptFileName = "decryptText.txt";
    static StringBuilder key = new StringBuilder();
    static StringBuilder text = new StringBuilder();
    static StringBuilder readedText = new StringBuilder();

    static Character[] abc = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л',
            'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч',
            'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '_', ' ', '.', ',', '!', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static ArrayList<Character> abcArray = new ArrayList<Character>(Arrays.asList(abc));

    public static void main(String[] args) {
        Menu();
    }

    public static void Menu() {
        System.out.println("Что делать?");
        System.out.println("1 - для зашифровки");
        System.out.println("2 - для расшифровки");
        System.out.println("3 - для чтения из файла");
        String result = scanner.nextLine();
        if (result.equals("1")) {
            menuEncrypt();
        } else if (result.equals("2")) {
            menuDecrypt();
        } else if (result.equals("3")) {
            menuReadFromFile();
            System.out.println(readedText);
        } else {
            Menu();
        }
    }

    public static void menuEncrypt() {
        key.delete(0, key.length());
        System.out.println("Введите ключ шифровки");
        key.append(scanner.nextLine().toLowerCase());

        System.out.println("Введите текст для шифровки");
        text.append(scanner.nextLine().toLowerCase());

        text.append(encrypt(text, key, true).toString());
        System.out.println(text);

        WriteToFile(text.toString(), encryptFileName);
    }

    public static void menuDecrypt() {
        key.delete(0, key.length());
        System.out.println("Введите ключ шифровки");
        key.append(scanner.nextLine().toLowerCase());

        System.out.println("Откуда брать текст?");
        System.out.println("1 - Из файла");
        System.out.println("2 - Из консоли");
        String result = scanner.nextLine();
        if(result.equals("1")) {
            menuDecryptFromFile();
        } else if (result.equals("2")) {
            menuDecryptFromConsole();
        } else menuDecrypt();

        WriteToFile(text.toString(), decryptFileName);
    }

    public static void menuDecryptFromFile() {
        text.append(ReadFromFile(encryptFileName).toString().toLowerCase());
        text = encrypt(text, key, false);
        System.out.println(text);
    }

    public static void menuDecryptFromConsole() {
        System.out.println("Введите текст для расшифровки");
        text.append(scanner.nextLine().toLowerCase());
        text = encrypt(text, key, false);
        System.out.println(text);
    }

    public static void menuReadFromFile() {
        System.out.println("Из какого файла читать?");
        System.out.println("1 - закодированный файл");
        System.out.println("2 - раскодированный файл");
        String result = scanner.nextLine();
        if (result.equals("1")) {
            readedText.append(ReadFromFile(encryptFileName));
        } else if (result.equals("2")) {
            readedText.append(ReadFromFile(decryptFileName));
        }
    }

    public static StringBuilder encrypt(StringBuilder text, StringBuilder key, boolean mode) {
        StringBuilder result = new StringBuilder();
        int iMode;
        int temp = 0;

        if (mode) iMode = 1;
        else iMode = -1;

        while (text.length() > 0) {
            if (temp >= key.length())
                temp = 0;
            int keyIndex = iMode * abcArray.indexOf(key.charAt(temp));
            int index  = abcArray.indexOf(text.charAt(0));
            if (index + keyIndex >= abcArray.size())
                result.append(abcArray.get(keyIndex - (abcArray.size() - 1 - index)));
            else if (index + keyIndex < 0)
                result.append(abcArray.get(abcArray.size() - 1 + index + keyIndex));
            else
                result.append(abcArray.get(index + keyIndex));
            temp++;
            text.deleteCharAt(0);
        }
        return result;
    }

    public static void WriteToFile(String text, String fileName) {
        try {
            if (!new File(fileName).exists())
                new File(fileName).createNewFile();

            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < text.length(); i++) {
                writer.write(text.charAt(i));
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder ReadFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        if (new File(fileName).exists()) {
            try {
                Scanner reader = new Scanner(new File(fileName));
                while (reader.hasNextLine()) {
                    stringBuilder.append(reader.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder;
    }


    public static void asdfg() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.indexOf(String.valueOf(stringBuilder.charAt(stringBuilder.length() - 1)));
    }
}