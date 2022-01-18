/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Leaquino
 */
public class servidor extends Observable implements Runnable {

private int puerto;

    public servidor(int puerto){
    this.puerto = puerto;
    }


@Override
    public void run() {
ServerSocket servidor = null;
    Socket sc = null;
    DataInputStream in;
    
        try {
            servidor = new ServerSocket(puerto);
            
            while(true){
                sc = servidor.accept();
                in = new DataInputStream(sc.getInputStream());
                               
                
                String mensaje = in.readUTF();
                
                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                sc.close();  
            }
            
        } catch (Exception e) {
        }
}
}
