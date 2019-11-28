/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author utente
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         new Runnable(){
         
                  @Override
            public void run() {
                try{
                    new Gui();
                }catch(SocketException | UnknownHostException e){
                      Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }.run();
    }
    }
    

