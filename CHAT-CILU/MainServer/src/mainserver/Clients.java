/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainserver;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author albig
 */
public class Clients {
       int porta;
    InetAddress ip;
    String ip2 = "127.0.0.1";

      public Clients(InetAddress indirizzo, int porta) throws UnknownHostException {
        this.porta = porta;
        indirizzo = InetAddress.getByName(ip2);
    }
      
       Clients(int porta) {
        this.porta = porta;
    }
    
}
