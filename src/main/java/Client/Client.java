/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author skole
 */
public class Client extends javax.swing.JFrame {

    Socket socket;
    private int port;
    private InetAddress IpAddress;
    private Scanner input;
    private PrintWriter writer;


    public static void main(String[] args) {
        // TODO code application logic here
        new GUI().setVisible(true);
    }

    public void connect(String address, int port) throws UnknownHostException, IOException {
        this.port = port;
        IpAddress = InetAddress.getByName(address);
        socket = new Socket(IpAddress, port);
        input = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);  //Set to true, to get auto flush behaviour
    }

    public void send(String msg) {
        writer.println(msg);
    }

}
