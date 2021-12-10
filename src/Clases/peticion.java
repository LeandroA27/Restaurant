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
    @Override
    public void run() {
        String HOST = "192.168.1.17";
             int PUERTO = 5000;
             DataInputStream in;
             DataOutputStream out; 
             
             try {
            Socket sc = new Socket(HOST,PUERTO);
            
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF("Ejecuta el asunto");
            String mensaje = in.readUTF();
            
            //JOptionPane.showMessageDialog(null, mensaje);
            
            sc.close();
            
        } catch (Exception e) {
        }
    }
    
    
}
