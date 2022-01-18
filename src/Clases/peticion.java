/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro Aquino
 */
public class peticion implements Runnable {
 private String Host;
 private int puerto;
 private String mensaje;  
 
public peticion (String Host,int puerto, String mensaje){
this.Host = Host;
    this.puerto = puerto;
 this.mensaje = mensaje;
 

}

  @Override
    public void run() {
             DataOutputStream out; 
             
             try {
            Socket sc = new Socket(Host,puerto);
            
            out = new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF(mensaje);
            sc.close();

        } catch (Exception e) {
        }
    }
    
    
}
