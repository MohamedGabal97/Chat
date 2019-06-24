/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

/**
 *
 * @author ALKODS
 */
public class Caese {
    
    public String Decode(String msg, int key) {
        int oper = 0;
        String decode = "";

        char[] ctext = msg.toCharArray();

        String alph = "abcdefghijklmonpqrstuvwxyz";
        char[] calph = alph.toCharArray();
        for (int i = 0; i < ctext.length; i++) {
            for (int j = 0; j < alph.length(); j++) {
                if (ctext[i] == calph[j]) {
                    oper = (j - key);
                    if (oper < 0) {
                        oper += 26;
                        decode += (calph[oper]);
                        break;
                    }
                    decode += (calph[oper]);
                    break;
                }
            }
        }
        return decode;
    }
    
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
    public void aa(){
        
    }
    
}
