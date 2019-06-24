/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALKODS
 */
public class Chat {

    /**
     * @param text
     * @param key
     * @return 
     */
    public String Encode(String text ,int key){
        int oper=0;
        String encode="";
       
        char[] ctext=text.toCharArray();
 
       String alph="abcdefghijklmonpqrstuvwxyz";
       char[] calph=alph.toCharArray();
       for(int i=0; i<ctext.length; i++){
           for(int j=0; j<alph.length(); j++){
               if(ctext[i] == calph[j]){
                   oper=(j+key)%26;
                   encode+=(calph[oper]);
                   break;
               }
           }
       }
       
       return encode;
    }
    public static void main(String[] args) {
        Chat encrypt = new Chat();
        String msg;
        
        try {
            Scanner sc = new Scanner(System.in);
            Socket so = new Socket("localhost" , 6666);
            
            DataInputStream in = new DataInputStream(so.getInputStream());
            DataOutputStream out= new DataOutputStream(so.getOutputStream());
            
            System.out.println("Enter Text");
            String text = sc.next();
            
            System.out.println("Enter key");
            int key=sc.nextInt();
            
            msg=encrypt.Encode(text, key);
            
            out.writeUTF(msg);
            out.writeInt(key);
            
            String x=in.readUTF();
            String y=in.readUTF();
            System.out.println(x+y);
            
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
