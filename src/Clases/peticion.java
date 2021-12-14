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
 private int puerto;
 private String mensaje;  
 
public peticion (int puerto, String mensaje){
 this.puerto = puerto;
 this.mensaje = mensaje;

}

  @Override
    public void run() {
             String HOST = "127.0.0.1";
             DataOutputStream out; 
             
             try {
            Socket sc = new Socket(HOST,puerto);
            
            out = new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF(mensaje);
            sc.close();

        } catch (Exception e) {
        }
    }
    
    
}
