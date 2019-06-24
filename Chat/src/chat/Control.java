/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ALKODS
 */
public class Control {

    byte[] citxt;
    public String manage(String text, String key, String type, String model) {
        Caese ca = new Caese();
        A_E_S aes = new A_E_S();
        String msg = null;
        if (model.equals("Caeser")) {
            int k = Integer.parseInt(key);
            switch (type) {
                case "Decrypt":
                    msg = ca.Decode(text, k);
                    System.out.println("msgggg "+msg);
                    break;
                case "Encrypt":
                    msg = ca.Encode(text, k);
                    break;
                default:
                    break;
            }
        }
        else if (model.equals("AES")) {
            try {
                //SecretKey k = aes.GeneratKey();

                byte[] ciphettxt;
                byte[] plan = text.getBytes();
                
                switch (type) {
                    case "Decrypt":
                        msg = aes.decrypt(citxt, key);
                        break;
                    case "Encrypt":
                        ciphettxt = aes.encrypt(text , key);
                        citxt=ciphettxt;
                        msg = new String(ciphettxt);
                        break;
                    default:
                        break;
                }

            } catch (Exception ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (model.equals("PlayFair")) {
            Play_Fair pf = new Play_Fair(key , text);
            switch (type) {
                case "Decrypt":
                    msg = pf.decipherMessage();
                    System.out.println("text "+text);
                    System.out.println("msg "+msg);
                    break;
                case "Encrypt":
                    msg = pf.cipherMessage();
                    System.out.println("text "+text);
                    System.out.println("msg "+msg);
                    break;
                default:
                    break;
            }
        }
        else{}
        return msg;
    }

}
