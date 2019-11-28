/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author utente
 */
public class ClientReceive implements Runnable{
     DatagramSocket s;

    public ClientReceive(DatagramSocket socket) {
        this.s = s;
    }
    
    
    
    @Override
    public void run() {
        byte[] buffer = new byte[100];
        String ricevuto;
        DatagramPacket pacchetto;

        try {

            pacchetto = new DatagramPacket(buffer, buffer.length);

            while (!Thread.interrupted()) {
                s.receive(pacchetto);
                ricevuto = new String(pacchetto.getData(), 0, pacchetto.getLength(), "ISO-8859-1");
                System.out.println("Il server ha inviato /n" + ricevuto);
                
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
