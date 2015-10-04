package Lazarets.SPNet;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: Tony
 * Date: 29.09.2015
 * Time: 15:09
 */
public class Main{
    private static String decryptFileName = "Lazarets/SPNet/decrypt.txt";
    private static String encryptFileName = "Lazarets/SPNet/encrypt.txt";
    private static String keyFileName = "Lazarets/SPNet/key.txt";

    public static void main(String[] args) {
        SPNet spNet = new SPNet();
        Menu.menu(keyFileName, encryptFileName, decryptFileName, spNet);
    }

    static class SPNet implements Decrypt {
        String[][] Sblocks = {
                {"1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110"},
                {"1110", "1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101"},
                {"1101", "1110", "1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100"},
                {"1100", "1101", "1110", "1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011"},
                {"1011", "1100", "1101", "1110", "1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010"},
                {"1010", "1011", "1100", "1101", "1110", "1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001"},
                {"1001", "1010", "1011", "1100", "1101", "1110", "1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000"},
                {"1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111"},
                {"0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110"},
                {"0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "0000", "0001", "0010", "0011", "0100", "0101"},
                {"0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "0000", "0001", "0010", "0011", "0100"},
                {"0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "0000", "0001", "0010", "0011"},
                {"0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "0000", "0001", "0010"},
                {"0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "0000", "0001"},
                {"0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111", "0000"},
                {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"}
        };

//        ArrayList<String> SBArray = new ArrayList<String>(Arrays.asList(Sblocks));

        String[] Xblock = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

        ArrayList<String> XBArray = new ArrayList<String>(Arrays.asList(Xblock));

        @Override
        public void encrypt(String message, String key) {
            sPart(message, key, true);
        }

        @Override
        public void decrypt(String message, String key) {
            sPart(message, key, false);
        }

        public void sPart(String message, String key, boolean mode) /* mode == true - зашифровать, else - расшифровать */ {
            String[] binaryCodes = BinaryParser.stringToBinary(message);

            int[] keys = new int[key.split(" ").length];
            for (int i = 0; i < keys.length; i++) {
                keys[i] = Integer.parseInt(key.split(" ")[i]);
            }

            int currentKey = 0;
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
                if (currentKey >= keys.length) currentKey = 0;
                ArrayList<String> SBArray = new ArrayList<>(Arrays.asList(Sblocks[keys[currentKey]]));
                if (mode)
                    for (int j = 0; j < temp4.length; j++) {
                        int tempIndex = XBArray.indexOf(temp4[j]);
                        temp4[j] = Sblocks[keys[currentKey]][tempIndex];
                    }
                else {
                    for (int j = 0; j < temp4.length; j++) {
                        int tempIndex = SBArray.indexOf(temp4[j]);
                        temp4[j] = Xblock[tempIndex];
                    }
                }
                currentKey++;
                StringBuilder resLine = new StringBuilder();
                for (int j = 0; j < temp4.length; j++) {
                    resLine.append(temp4[j]);
                }

                binaryCodes[i] = resLine.toString();
            }

            String result = BinaryParser.binaryToString(binaryCodes);
            System.out.println(result);
        }

        public void pPart() {

        }
    }
}

