/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
/**
 *
 * @author albig
 */
public class Gui extends JFrame implements ActionListener {
    Clients client = new Clients(4567);
     private JTextArea area; 
    
     DatagramSocket Client;
     DatagramSocket server;
     byte [] buffer;
     String mex;
     JPanel panel;
     JPanel panel2;
     ArrayList<String> messaggi = new ArrayList<>();
     private HashMap<InetAddress, Integer> porte = new HashMap<>();
     
     
     public Gui(int porta) throws SocketException, UnknownHostException{
        
         
         setTitle("CILURZO CHAT");
      
        area = new JTextArea(6,6);
        area.setEditable(true);
        
        
         panel = new JPanel();
         panel.setLayout(new BorderLayout());
         panel.setBorder(new TitledBorder("CONVERSAZIONE CON TUTTI I CLIENT"));
         panel.add(area, BorderLayout.NORTH);
         
         
         panel2 = new JPanel();
         panel2.setLayout(new GridLayout(1, 1));
         panel2.add(panel);
         buffer = new byte[1024];
         
         
        
         this.getContentPane().setBackground(Color.BLUE);
         this.getContentPane().add(panel2, BorderLayout.CENTER);
         

         

        
         setSize(700, 500);
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         
         
         
         
         
         new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server = new DatagramSocket(9999);
                    while (true) {
                        DatagramPacket pacchetto = new DatagramPacket(buffer, buffer.length);
                        server.receive(pacchetto);
                        String messaggio = new String(pacchetto.getData());
                        area.append("ricevuto:" + messaggio + " da: " + pacchetto.getAddress() + "" + pacchetto.getPort());
                        server.send(new DatagramPacket(pacchetto.getData(), pacchetto.getLength(), pacchetto.getAddress(), pacchetto.getPort()));
                        area.append("inviato:" + messaggio + " a: " + pacchetto.getAddress() + "" + pacchetto.getPort());
                        if (!porte.containsKey(pacchetto.getAddress()) && !porte.containsValue(pacchetto.getPort())) {
                            area.append("L'IP non risulta nell'elenco,ora lo salvo!");
                            porte.put(pacchetto.getAddress(), pacchetto.getPort());
                            if (messaggi.size() > 10) {
                                for (int i = messaggi.size() - 11; i < messaggi.size(); i++) {
                                    byte[] buff = messaggi.get(i).getBytes();
                                    DatagramPacket ultimiMex = new DatagramPacket(buff, buff.length, pacchetto.getAddress(), pacchetto.getPort());
                                    server.send(ultimiMex);
                                    area.append("inviato:" + new String(buff) + " a: " + pacchetto.getAddress() + " " + pacchetto.getPort());
                                }
                            } else {
                                for (int i = 0; i < messaggi.size(); i++) {
                                    byte[] buffer2 = messaggi.get(i).getBytes();
                                    DatagramPacket ultimiMex = new DatagramPacket(buffer2, buffer2.length, pacchetto.getAddress(), pacchetto.getPort());
                                    server.send(ultimiMex);
                                    area.append("inviato:" + new String(buffer2) + " a: " + pacchetto.getAddress() + " " + pacchetto.getPort());
                                }
                            }
                        }
                        messaggi.add(messaggio);
                    }
                } catch (IOException ex) {
                }
            }
        }).start();
         
         
         
        
        
 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
