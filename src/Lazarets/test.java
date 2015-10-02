package Lazarets;

import Lazarets.SPNet.Decrypt;
import Lazarets.SPNet.Menu;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: Tony
 * Date: 29.09.2015
 * Time: 15:09
 */
public class test{
    private static String decryptFileName = "Lazarets/SPNet/decrypt.txt";
    private static String encryptFileName = "Lazarets/SPNet/encrypt.txt";
    private static String keyFileName = "Lazarets/SPNet/key.txt";

    public static void main(String[] args) {
        SPNet spNet = new SPNet();
        Menu.menu(keyFileName, encryptFileName, decryptFileName, spNet);
    }

    static class SPNet implements Decrypt {

        String[][] Sblock = {
                {"1101", "0101", "0100", "1001", "0000", "0111", "1010", "0011", "0010", "0110", "0001", "1100", "1110", "1111", "1000", "1011"},
                {"1011", "1101", "0101", "0100", "1001", "0000", "0111", "1010", "0011", "0010", "0110", "0001", "1100", "1110", "1111", "1000"},
                {"1000", "1011", "1101", "0101", "0100", "1001", "0000", "0111", "1010", "0011", "0010", "0110", "0001", "1100", "1110", "1111"},
                {"1111", "1000", "1011", "1101", "0101", "0100", "1001", "0000", "0111", "1010", "0011", "0010", "0110", "0001", "1100", "1110"},
                {"1110", "1111", "1000", "1011", "1101", "0101", "0100", "1001", "0000", "0111", "1010", "0011", "0010", "0110", "0001", "1100"},
                {"1100", "1110", "1111", "1000", "1011", "1101", "0101", "0100", "1001", "0000", "0111", "1010", "0011", "0010", "0110", "0001"},
                {"0001", "1100", "1110", "1111", "1000", "1011", "1101", "0101", "0100", "1001", "0000", "0111", "1010", "0011", "0010", "0110"},
                {"0110", "0001", "1100", "1110", "1111", "1000", "1011", "1101", "0101", "0100", "1001", "0000", "0111", "1010", "0011", "0010"},
                {"0010", "0110", "0001", "1100", "1110", "1111", "1000", "1011", "1101", "0101", "0100", "1001", "0000", "0111", "1010", "0011"},
                {"0011", "0010", "0110", "0001", "1100", "1110", "1111", "1000", "1011", "1101", "0101", "0100", "1001", "0000", "0111", "1010"},
                {"1010", "0011", "0010", "0110", "0001", "1100", "1110", "1111", "1000", "1011", "1101", "0101", "0100", "1001", "0000", "0111"},
                {"0111", "1010", "0011", "0010", "0110", "0001", "1100", "1110", "1111", "1000", "1011", "1101", "0101", "0100", "1001", "0000"},
                {"0000", "0111", "1010", "0011", "0010", "0110", "0001", "1100", "1110", "1111", "1000", "1011", "1101", "0101", "0100", "1001"},
                {"1001", "0000", "0111", "1010", "0011", "0010", "0110", "0001", "1100", "1110", "1111", "1000", "1011", "1101", "0101", "0100"},
                {"0100", "1001", "0000", "0111", "1010", "0011", "0010", "0110", "0001", "1100", "1110", "1111", "1000", "1011", "1101", "0101"},
                {"0101", "0100", "1001", "0000", "0111", "1010", "0011", "0010", "0110", "0001", "1100", "1110", "1111", "1000", "1011", "1101"}
        };

//        ArrayList<String> SBArray = new ArrayList<String>(Arrays.asList(Sblock));
        ArrayList<String> SBArray;

        String[] Xblock = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

        ArrayList<String> XBArray = new ArrayList<String>(Arrays.asList(Xblock));

        @Override
        public void encrypt(String message, String key) {
            binaryMagic(message, key, true);
        }

        @Override
        public void decrypt(String message, String key) {
            binaryMagic(message, key, false);

        }

        public void binaryMagic(String message, String key, boolean mode) /* mode == true - зашифровать, else - расшифровать */{
            int[] letters = new int[message.length()];
            for (int i = 0; i < letters.length; i++) {
                letters[i] = message.charAt(i);
            }
            String[] binaryCodes = new String[letters.length];
            for (int i = 0; i < letters.length; i++) {
                StringBuilder line = new StringBuilder();
                do {
                    int temp = letters[i] % 2;
                    line.append(temp);
                    letters[i] /= 2;
                } while (letters[i] != 0);
                if (line.length() % 4 != 0) {
                    do {
                        line.append("0", 0, 1);
                    } while (line.length() % 4 != 0);
                }
                binaryCodes[i] = line.reverse().toString();
            }

            for (int i = 0; i < binaryCodes.length; i++) {
                String[] temp4 = new String[binaryCodes[i].length() / 4];
                for (int j = 0; j < temp4.length; j++) {
                    StringBuilder temp = new StringBuilder();
                    temp.append(binaryCodes[i].charAt(4 * j))
                            .append(binaryCodes[i].charAt(4 * j + 1))
                            .append(binaryCodes[i].charAt(4 * j + 2))
                            .append(binaryCodes[i].charAt(4 * j + 3));
                    temp4[j] = temp.toString();
                }
                if (mode) {
                    for (int j = 0; j < temp4.length; j++) {
                        int tempIndex = XBArray.indexOf(temp4[j]);
//                        temp4[j] = Sblock[tempIndex];
                    }
                } else {
                    for (int j = 0; j < temp4.length; j++) {
                        int tempIndex = SBArray.indexOf(temp4[j]);
                        temp4[j] = Xblock[tempIndex];
                    }
                }
                StringBuilder resLine = new StringBuilder();
                for (int j = 0; j < temp4.length; j++) {
                    resLine.append(temp4[j]);
                }
                binaryCodes[i] = resLine.toString();
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < binaryCodes.length; i++) {
                int sum = 0;
                String rev = new StringBuilder(binaryCodes[i]).reverse().toString();
                for (int j = 0; j < rev.length(); j++) {
                    int temp = Integer.parseInt(String.valueOf(rev.charAt(j)));
                    if (temp % 2 == 1)
                        sum += Math.pow(2, j);
                }
                result.append(Character.valueOf((char) sum));
            }
            System.out.println(result.toString());
        }
    }
}

