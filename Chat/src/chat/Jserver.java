/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

/**
 *
 * @author ALKODS
 */
public class Jserver extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Jserver
     */
    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dos;

    static ArrayList send;

    static String key;

    static int caesarkey;
    static String playfairkey;
    static String aeskey;

    static String hashedCaesar;
    static String hashedPlayfairkey;
    static String hashedAES;

    String mod;
    String ms;

    public Jserver() {
        initComponents();
    }

    public Jserver(Socket so) throws IOException {
        dis = new DataInputStream(so.getInputStream());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws IOException, Exception {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
            * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Jserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jserver().setVisible(true);
            }
        });

        ss = new ServerSocket(6666);
        generatKeys();
        send = new ArrayList();
        while (true) {
            s = ss.accept();
            dos = new DataOutputStream(s.getOutputStream());
            send.add(dos);
            Keys();

            new Thread(new Jserver(s)).start();
        }
    }

    @Override
    public void run() {
        try {

            while ((mod = dis.readUTF()) != null) {
                if (!mod.equals("re")) {
                    System.out.println(mod);
                    ms = dis.readUTF();
                    System.out.println(ms);
                    DataOutputStream temp;
                    for (int i = 0; i < send.size(); i++) {
                        temp = (DataOutputStream) send.get(i);
                        temp.writeUTF(mod);
                        temp.writeUTF(ms);
                        temp.flush();
                    }
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Jserver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void generatKeys() throws Exception {
        Random rand = new Random();

        caesarkey = rand.nextInt(9) + 1;

        playfairkey = null;
        for (int i = 0; i < 5; i++) {
            playfairkey += (char) (rand.nextInt(25) + 'a');
        }

        A_E_S aes = new A_E_S();
        aeskey = (aes.GeneratKey()).toString();
    }

    static void Keys() throws IOException, NoSuchAlgorithmException, Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update((byte) caesarkey);
        hashedCaesar = new String(md.digest());

        md.update((playfairkey.toString()).getBytes());
        hashedPlayfairkey = new String(md.digest());

        md.update(aeskey.getBytes());
        hashedAES = new String(md.digest());

        dos.writeInt(caesarkey);
        dos.writeUTF(hashedCaesar);
        dos.writeUTF(playfairkey);
        dos.writeUTF(hashedPlayfairkey);
        dos.writeUTF(aeskey);
        dos.writeUTF(hashedAES);
        dos.flush();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}
