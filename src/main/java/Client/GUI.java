/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Diana
 */
public class GUI extends javax.swing.JFrame {

    String username;
    Socket sock;
    private Scanner input;
    PrintWriter writer;
    BufferedReader reader;
    Boolean isConnected = false;
    //  ArrayList<String> userList = new ArrayList[];

    public GUI() {
        initComponents();
    }

//    private void ListenThread() {
//        Thread IncomingReader = new Thread(new IncomingReader());
//        IncomingReader.start();
//    }

    public class incomingReader implements Runnable {

        public void run() {
            String stream;
            String[] data;
            String chat = "";
            String connect = "";

            try {
                while ((stream = reader.readLine()) != null) {
                    data = stream.split(":");

                    if (data[2].equals(chat)) {
                        OutputChat.append(data[0] + "MSG:" + data[1] + "\n");
                    } else if (data[2].equals(connect)) {
                        OutputChat.removeAll();
                    } else if (data[2].equals(chat)) {
                        //                    userList.setText("");
                        writeUsers();
                        //                    userList.clear();
                    }
                }
            } catch (Exception ex) {

            }

        }

        private void writeUsers() {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        OutputText = new javax.swing.JScrollPane();
        OutputChat = new javax.swing.JTextArea();
        InputText = new javax.swing.JScrollPane();
        InputChat = new javax.swing.JTextArea();
        ConnectButton = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        send = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("enter text below");

        OutputChat.setEditable(false);
        OutputChat.setColumns(20);
        OutputChat.setLineWrap(true);
        OutputChat.setRows(5);
        OutputText.setViewportView(OutputChat);

        InputChat.setColumns(20);
        InputChat.setLineWrap(true);
        InputChat.setRows(5);
        InputText.setViewportView(InputChat);

        ConnectButton.setText("connect");
        ConnectButton.setActionCommand("Connect");
        ConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectButtonActionPerformed(evt);
            }
        });

        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Username:");

        send.setText("send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OutputText)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(ConnectButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(InputText, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ConnectButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OutputText, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InputText, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectButtonActionPerformed

        if (isConnected == false) {
            username = usernameField.getText();
            usernameField.setEditable(false);

            try {
                sock = new Socket("localhost", 9999);
                InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamReader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + "was connected"); // displays to everyone that user is connected
                writer.flush();
                isConnected = true;

            } catch (Exception ex) {
                OutputChat.append("cant connect");
                usernameField.setEditable(true);
            }
//            ListenThread();

        } else if (isConnected == true) {
            OutputChat.append("you are already connected");
        }


    }//GEN-LAST:event_ConnectButtonActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:
        String nothing = "";
        if ((InputChat.getText()).equals(nothing)) {
            InputChat.setText("");
            InputChat.requestFocus();
        }else{
            try{
                writer.println(username+"Y"+InputChat.getText()+"Y"+"chat");
                writer.flush();
            }catch(Exception ex){
                OutputChat.append("message not send ");
            }
            InputChat.setText("");
            InputChat.requestFocus();
        }
        InputChat.setText("");
        InputChat.requestFocus();
    }//GEN-LAST:event_sendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConnectButton;
    private javax.swing.JTextArea InputChat;
    private javax.swing.JScrollPane InputText;
    private javax.swing.JTextArea OutputChat;
    private javax.swing.JScrollPane OutputText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton send;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables

}
