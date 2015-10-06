package Lazarets.SPNet;

/**
 * User: Tony
 * Date: 04.10.2015
 * Time: 21:29
 */
public class BinaryParser {
    /*
        stringToBinary принимает строку текста, которую нужно перевести в бинарный код (дальше б.к.).
        возвращает массив строк, состоящий из бинарных кодов каждого символа.
    */

    public static String[] stringToBinary(String message) {
        int[] letters = new int[message.length()];       //массив, который будет содержать численный код каждого символа в сообщении
        for (int i = 0; i < letters.length; i++) {
            letters[i] = message.charAt(i);
        }
        String[] binaryCodes = new String[letters.length];  //делаем массив строк, который впоследствии будет содержать б.к. символов сообщения
        for (int i = 0; i < letters.length; i++) {          //проход по каждой букве
            StringBuilder line = new StringBuilder();
            do {
                int temp = letters[i] % 2;
                line.append(temp);
                letters[i] /= 2;
            } while (letters[i] != 0);          //здесь переводим численное представление символа в двоичный код

            while (line.length() < 12) {  //делаем длину б.к. кратную 4-ем для возможности дальнейшего шифрования
                line.append("0");
            }
            binaryCodes[i] = line.reverse().toString();
        }
        return binaryCodes;
    }

    public static String binaryToString(String[] binaryCodes) {     //метод принимает массив строк содержащих б.к. символов будущего сообщения
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binaryCodes.length; i++) {
            int sum = 0;    //переменная, которая будет содержать значение символа переведенного из 2-ой в 10-ую систему
            String rev = new StringBuilder(binaryCodes[i]).reverse().toString();
            for (int j = 0; j < rev.length(); j++) {        //переводим в 10-тиричную систему исчисления
                int temp = Integer.parseInt(String.valueOf(rev.charAt(j)));
                if (temp % 2 == 1)
                    sum += Math.pow(2, j);
            }
            result.append(Character.valueOf((char) sum));
        }
        return result.toString();
    }
}
