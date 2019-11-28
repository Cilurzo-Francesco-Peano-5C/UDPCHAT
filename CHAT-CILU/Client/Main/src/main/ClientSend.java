/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author utente
 */
public class ClientSend implements Runnable{
    DatagramSocket s;
    InetAddress indirizzo;
    int porta;

    public ClientSend(DatagramSocket socket, InetAddress ip, int porta) {
        this.s = s;
        this.indirizzo = indirizzo;
        this.porta = porta;
    }
    
    
   @Override
    public void run() {
        byte[] buffer;
        String mex;
        Scanner tastiera = new Scanner(System.in);
        DatagramPacket pacchetto;

        try {
            
            do {

                mex = tastiera.nextLine();

                buffer = mex.getBytes("UTF-8");

                pacchetto = new DatagramPacket(buffer, buffer.length, indirizzo, porta);

                s.send(pacchetto);
            } while (mex.compareTo("quit") != 0);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
