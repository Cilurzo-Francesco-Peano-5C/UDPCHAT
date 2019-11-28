/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author utente
 */
public class Gui extends JFrame implements ActionListener{

    
        private JTextField Field; 
     private JTextField Field2;
     private JTextArea area; 
      byte [] buffer;
     byte [] buffer2;
     private JButton bottone = new JButton("INVIO");
    
    
     DatagramSocket client;
     String messaggio;
    
     String ip = "127.0.1";
     
     
     JPanel pannello;
     JPanel pannello2;
     JPanel pannello3;
     InetAddress address = InetAddress.getByName(ip);     
     InetAddress indirizzo = InetAddress.getByName(ip);
     
      public Gui () throws  SocketException,UnknownHostException{
    
           setTitle("CHAT-UDP");
      
        Field = new JTextField();
        Field2 = new JTextField();
        buffer = new byte[1024];
        buffer2 = new byte[1024];
        area = new JTextArea();
        
        
       
        pannello = new JPanel();
        pannello.setBorder(new TitledBorder("USERNAME && MESSAGGIO"));
        pannello.setLayout(new GridLayout(1, 3));
        pannello.add(Field2);
        pannello.add(Field);
        pannello.add(bottone);
        
        
        
        
        
        pannello2 = new JPanel();
        
        pannello2.setLayout(new GridLayout(1, 1));
        pannello2.add(area);
                
                
                
        pannello3 = new JPanel();
        JScrollPane p = new JScrollPane(pannello3);
        
        pannello3.setLayout(new GridLayout(5, 5));
        this.setLayout(new GridLayout(5, 5));
        this.add(pannello);
        pannello3.add(pannello2);
        
        this.add(p);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        this.getContentPane().add(pannello3, BorderLayout.NORTH);
        bottone.addActionListener(this);
        pack();
        setSize(700, 500);
        setVisible(true);
     
     
    
    new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new DatagramSocket();
                    while (true) {
                        DatagramPacket pacchetto = new DatagramPacket(buffer, buffer.length);
                        client.receive(pacchetto);
                        String messaggio = new String(pacchetto.getData());
                        area.append("Server: " + messaggio );
                    }
                } catch (Exception e) {
                }
            }
        }).start();

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     @Override
    public void actionPerformed(ActionEvent event) {
       if (event.getSource().equals(bottone)) {
            try {
                String message = Field.getText();
                String username1 = Field2.getText();
                String messaggio = message + " & " + username1;
                buffer = messaggio.getBytes();
                DatagramPacket sendpack = new DatagramPacket(buffer, buffer.length, InetAddress.getLoopbackAddress(), 9999);
                client.send(sendpack);
                area.append("l'utente e' " + username1 + " ed il messaggio e' : " + message );
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
      }
    
   
    

