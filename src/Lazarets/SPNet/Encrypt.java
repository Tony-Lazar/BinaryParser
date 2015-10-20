package Lazarets.SPNet;

/**
 * User: Tony
 * Date: 29.09.2015
 * Time: 17:17
 */
public interface Encrypt {
    void encrypt(String message, String key);
    void checkKey(String key) throws ReadingKeyException;
}
