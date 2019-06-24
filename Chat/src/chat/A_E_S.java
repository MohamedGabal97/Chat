/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ALKODS
 */
public class A_E_S {

    public static String GeneratKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        SecretKey key = generator.generateKey();
        String strkey = Base64.getEncoder().encodeToString(key.getEncoded());
        return strkey;
    }

    public static byte[] encrypt(String plan, String key) throws Exception {
        byte[] encodedKey = Base64.getDecoder().decode(key);
        SecretKey aesKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        Cipher aes_alg = Cipher.getInstance("AES");
        aes_alg.init(Cipher.ENCRYPT_MODE,aesKey);
        byte[] bct = aes_alg.doFinal(plan.getBytes());
        return bct;

    }
    public static String decrypt(byte[] cipher, String key) throws Exception {
        byte[] encodedKey = Base64.getDecoder().decode(key);
        SecretKey aesKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        Cipher aes_alg = Cipher.getInstance("AES");
        aes_alg.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] bpt = aes_alg.doFinal(cipher);
        String x = new String(bpt);
        return x;
    }
}
