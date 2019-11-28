/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainserver;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albig
 */
public class MainServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           new Runnable() {
                @Override
                public void run() {
                    try {
                        new Gui(9999);
                    } catch (SocketException | UnknownHostException ex) {
                        Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.run();
            
        
    }
    }
    

