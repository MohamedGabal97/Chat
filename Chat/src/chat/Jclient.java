/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import static chat.Jserver.caesarkey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

/**
 *
 * @author ALKODS
 */
public class Jclient extends javax.swing.JFrame {

    /**
     * Creates new form Jclient
     */
    Control con = new Control();

    static Socket so;
    static DataInputStream in;
    static DataOutputStream out;

    Thread IncomingReader;

    public Jclient() {
        initComponents();
        ListenThread();
    }

    String text;
    String key ;

    int keycaesar;
    String keyplayfair;
    String keyaes;
    String hashcaesar;
    String hashplayfair;
    String hashaes;

    static String hashedCaesarkey;
    static String hashedPlayfairkey;
    static String hashedAESkey;

    String message = "";
    String model;
    String msgg;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        show = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        Caesar = new javax.swing.JRadioButton();
        Playfair = new javax.swing.JRadioButton();
        AES = new javax.swing.JRadioButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        show.setColumns(20);
        show.setLineWrap(true);
        show.setRows(5);
        show.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(show);

        jLabel1.setText("Message");

        jButton3.setText("Send");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(Caesar);
        Caesar.setText("Caesar");
        Caesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaesarActionPerformed(evt);
            }
        });

        buttonGroup1.add(Playfair);
        Playfair.setText("Playfair");

        buttonGroup1.add(AES);
        AES.setText("AES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Caesar)
                .addGap(18, 18, 18)
                .addComponent(Playfair)
                .addGap(18, 18, 18)
                .addComponent(AES)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Caesar)
                    .addComponent(Playfair)
                    .addComponent(AES))
                .addGap(0, 26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            text = jTextField1.getText();
            String msg = null;

            if (buttonGroup1.getSelection().equals(Caesar.getModel())) {
                key = String.valueOf(keycaesar);
                System.out.println("key1 " + key);
                msg = con.manage(text, key, "Encrypt", "Caeser");
                out.writeUTF("Caeser");
                out.writeUTF(msg);
                out.flush();
            } else if (buttonGroup1.getSelection().equals(Playfair.getModel())) {
                key = keyplayfair;
                msg = con.manage(text, key, "Encrypt", "PlayFair");
                out.writeUTF("PlayFair");
                out.writeUTF(msg);
                out.flush();
            } else if (buttonGroup1.getSelection().equals(AES.getModel())) {
                key = keyaes;
                msg = con.manage(text, key, "Encrypt", "AES");
                out.writeUTF("AES");
                out.writeUTF(msg);
                out.flush();
            } else {
            }
            jTextField1.setText("");

        } catch (IOException ex) {
            Logger.getLogger(Jclient.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void CaesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaesarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CaesarActionPerformed

    public void getkey() throws IOException {
        keycaesar = in.readInt();
        hashcaesar = in.readUTF();
        keyplayfair = in.readUTF();
        hashplayfair = in.readUTF();
        keyaes = in.readUTF();
        hashaes = in.readUTF();
    }

    public boolean hashkeys() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update((byte) keycaesar);
        hashedCaesarkey = new String(md.digest());
        if (!hashedCaesarkey.equals(hashcaesar)) {
            return false;
        }

        md.update((keyplayfair).getBytes());
        hashedPlayfairkey = new String(md.digest());
        if (!hashedPlayfairkey.equals(hashplayfair)) {
            return false;
        }

        md.update((keyaes).getBytes());
        hashedAESkey = new String(md.digest());

        if (!hashedAESkey.equals(hashaes)) {
            return false;
        }

        return true;
    }

    public void ListenThread() {
        IncomingReader = new Thread(new Incoming());
        IncomingReader.start();
    }

    public class Incoming implements Runnable {

        @Override
        public void run() {
            try {
                getkey();
                boolean x = hashkeys();
                if (x) {

                    while (!(model = in.readUTF()).equals(null)) {
                        System.out.println("mo " + model);
                        msgg = in.readUTF();

                        if (model.equals("Caeser")) {
                            String kkey = String.valueOf(keycaesar);
                            message = con.manage(msgg, kkey, "Decrypt", "Caeser");
                            show.append(message + "\n");
                        } else if (model.equals("PlayFair")) {
                            message = con.manage(msgg, keyplayfair, "Decrypt", "PlayFair");
                            show.append(message + "\n");
                        } else if (model.equals("AES")) {
                            message = con.manage(msgg, keyaes, "Decrypt", "AES");
                            show.append(message + "\n");
                        }
                    }
                }
                else{}

            } catch (IOException ex) {
                Logger.getLogger(Jclient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Jclient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
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
                java.util.logging.Logger.getLogger(Jclient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Jclient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Jclient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Jclient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {

                    new Jclient().setVisible(true);

                }

            });
            so = new Socket("localhost", 6666);

            in = new DataInputStream(so.getInputStream());
            out = new DataOutputStream(so.getOutputStream());

        } catch (IOException ex) {
            Logger.getLogger(Jclient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AES;
    private javax.swing.JRadioButton Caesar;
    private javax.swing.JRadioButton Playfair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea show;
    // End of variables declaration//GEN-END:variables

}
