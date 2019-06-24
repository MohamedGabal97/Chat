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
public class Play_Fair {
    
    String key;
    char[] ckey;
    String msg;
    char[] cmsg;
    String alph;
    String nextofchar;
    String search = "";
    int end;

    String text;
    char[] chartext;
    char[][] matrextext = new char[5][5];
    int count = 0;

    char[][] inputmatrex;
    String next;
    int endOfMsg;
    char[] liter;
    int indexliter;
    int[] indexrow = new int[2];
    int[] indexcol = new int[2];
    String cipher = "";

    
    Play_Fair(String key, String msg) {
        this.key = key;
        this.msg = msg;
        alph = "abcdefghiklmnopqrstuvwxyz";

    }

    public String alphabet() {
        //convert j --> i
        ckey = this.key.toCharArray();
        cmsg = this.msg.toCharArray();

        for (int i = 0; i < ckey.length; i++) {
            if (ckey[i] == 'j') {
                ckey[i] = 'i';
            }
        }

        for (int i = 0; i < cmsg.length; i++) {
            if (cmsg[i] == 'j') {
                cmsg[i] = 'i';
            }
        }
        this.msg = String.valueOf(cmsg);
        //singl letter
        String fulltext = key + alph;
        char[] arrtext = fulltext.toCharArray();
        end = 1;
        while (fulltext.length() > 0) {

            nextofchar = fulltext.substring(0, end);
            if (search.indexOf(nextofchar) == -1) {
                search += nextofchar;

            }
            fulltext = fulltext.substring(end);

        }

        return search;
    }

    public char[][] matrex() {

        text = alphabet();
        chartext = text.toCharArray();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                matrextext[row][col] = chartext[count];
                count++;
            }
        }
        
        return matrextext;

    }

    public String cipherMessage() {

        inputmatrex = matrex();

        while (msg.length() > 0) {
            //choase 2 letter
            endOfMsg = 2;

            if (msg.length() == 1) {
                msg += 'x';
            }

            next = msg.substring(0, endOfMsg);
            liter = next.toCharArray();

            if (liter[0] == liter[1]) {
                liter[1] = 'x';
                msg = msg.substring(endOfMsg - 1);
            } else {
                msg = msg.substring(endOfMsg);
            }
            //determine the index of letter in a matrex
            indexliter = 0;
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (indexliter < 2) {
                        if (liter[indexliter] == inputmatrex[row][col]) {
                            indexrow[indexliter] = row;
                            indexcol[indexliter] = col;
                            indexliter++;
                        }

                    }
                }
            }
            if (indexliter < 2) {
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 5; col++) {
                        if (liter[indexliter] == inputmatrex[row][col]) {
                            indexrow[indexliter] = row;
                            indexcol[indexliter] = col;

                        }
                    }
                }
            }
            //check two index in same row or not
            if (indexrow[0] == indexrow[1]) {
                indexcol[0]++;
                indexcol[1]++;

                if (indexcol[0] == 5) {
                    indexcol[0] = 0;
                }
                if (indexcol[1] == 5) {
                    indexcol[1] = 0;
                }

                cipher += inputmatrex[indexrow[0]][indexcol[0]];
                cipher += inputmatrex[indexrow[1]][indexcol[1]];
            }
            //check two index in same row or not
            if (indexcol[0] == indexcol[1]) {
                indexrow[0]++;
                indexrow[1]++;

                if (indexrow[0] == 5) {
                    indexrow[0] = 0;
                }
                if (indexrow[1] == 5) {
                    indexrow[1] = 0;
                }

                cipher += inputmatrex[indexrow[0]][indexcol[0]];
                cipher += inputmatrex[indexrow[1]][indexcol[1]];
            }

            if (indexcol[0] != indexcol[1] && indexrow[0] != indexrow[1]) {
                cipher += inputmatrex[indexrow[0]][indexcol[1]];
                cipher += inputmatrex[indexrow[1]][indexcol[0]];
            }
        }
        return cipher;

    }
    
    public String decipherMessage() {

        inputmatrex = matrex();

        while (msg.length() > 0) {
            //choase 2 letter
            endOfMsg = 2;

            if (msg.length() == 1) {
                msg += 'x';
            }

            next = msg.substring(0, endOfMsg);
            liter = next.toCharArray();

            if (liter[0] == liter[1]) {
                liter[1] = 'x';
                msg = msg.substring(endOfMsg - 1);
            } else {
                msg = msg.substring(endOfMsg);
            }
            //determine the index of letter in a matrex
            indexliter = 0;
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (indexliter < 2) {
                        if (liter[indexliter] == inputmatrex[row][col]) {
                            indexrow[indexliter] = row;
                            indexcol[indexliter] = col;
                            indexliter++;
                        }

                    }
                }
            }
            if (indexliter < 2) {
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 5; col++) {
                        if (liter[indexliter] == inputmatrex[row][col]) {
                            indexrow[indexliter] = row;
                            indexcol[indexliter] = col;

                        }
                    }
                }
            }
            //check two index in same row or not
            if (indexrow[0] == indexrow[1]) {
                indexcol[0]--;
                indexcol[1]--;

                if (indexcol[0] == -1) {
                    indexcol[0] = 4;
                }
                if (indexcol[1] == -1) {
                    indexcol[1] = 4;
                }

                cipher += inputmatrex[indexrow[0]][indexcol[0]];
                cipher += inputmatrex[indexrow[1]][indexcol[1]];
            }
            //check two index in same row or not
            if (indexcol[0] == indexcol[1]) {
                indexrow[0]--;
                indexrow[1]--;

                if (indexrow[0] == -1) {
                    indexrow[0] = 4;
                }
                if (indexrow[1] == -1) {
                    indexrow[1] = 4;
                }

                cipher += inputmatrex[indexrow[0]][indexcol[0]];
                cipher += inputmatrex[indexrow[1]][indexcol[1]];
            }

            if (indexcol[0] != indexcol[1] && indexrow[0] != indexrow[1]) {
                cipher += inputmatrex[indexrow[0]][indexcol[1]];
                cipher += inputmatrex[indexrow[1]][indexcol[0]];
            }
        }
        return cipher;

    }
    
}
