package Lazarets.Ceasar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Tony on 02.09.2015.
 */
public class Ceasar {
    static Character[] abc = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л',
            'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч',
            'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '_', ' ', '.', ',', '!', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static ArrayList<Character> abcArray = new ArrayList<Character>(Arrays.asList(abc));
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ключ шифровки");
        int key = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите текст для шифровки");
        String line = scanner.nextLine();

        String result = encrypt(line, key).toString();
        System.out.println(result);
        result = encrypt(result, -key).toString();
        System.out.println(result);
    }

    public static StringBuilder encrypt(String line, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char current = line.charAt(i);
            if(current == ' ')
                current = '_';
            current = Character.toLowerCase(current);
            int index = abcArray.indexOf(current);
            if (index + key >=  abcArray.size()) {
                result.append(abcArray.get(key - (abcArray.size() - 1 - index)));
            } else if (index + key < 0) {
                result.append(abcArray.get(abcArray.size() - 1 + (index + key)));
            } else {
                result.append(abcArray.get(index + key));
            }
        }

        return result;
    }
}
