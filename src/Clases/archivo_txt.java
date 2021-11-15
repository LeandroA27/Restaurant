
package Clases;
import java.io.*;
import javax.swing.JOptionPane;

public class archivo_txt {
    
    
    public String leerTxt(String direccion){
        String texto="";
        String texto1="";
        String texto2="";
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String temp1 = "";
            String temp2 = "";
            int valor1= 0;
            String bfread;
            while((bfread=bf.readLine()) !=null){
                valor1=valor1 + 1;
                if (valor1==1){
                 temp = bfread;   
                }
                if (valor1==2){
                 temp1 = bfread;   
                }
                if (valor1==3){
                 temp2 = bfread;   
                }
            }
            
           texto = temp;
           texto1 =temp1;
           texto2 =temp2;
        } catch (Exception e)  {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo de conexión a la base de datos");
        }
        return texto;
    }
public String leerTxt1(String direccion){
        String texto="";
        String texto1="";
        String texto2="";
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String temp1 = "";
            String temp2 = "";
            int valor1= 0;
            String bfread;
            while((bfread=bf.readLine()) !=null){
                valor1=valor1 + 1;
                if (valor1==1){
                 temp = bfread;   
                }
                if (valor1==2){
                 temp1 = bfread;   
                }
                if (valor1==3){
                 temp2 = bfread;   
                }
            }
            
           texto = temp;
           texto1 =temp1;
           texto2 =temp2;
        } catch (Exception e)  {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo de conexión a la base de datos");
        }
        return texto1;
    }
    public String leerTxt2(String direccion){
        String texto="";
        String texto1="";
        String texto2="";
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String temp1 = "";
            String temp2 = "";
            int valor1= 0;
            String bfread;
            while((bfread=bf.readLine()) !=null){
                valor1=valor1 + 1;
                if (valor1==1){
                 temp = bfread;   
                }
                if (valor1==2){
                 temp1 = bfread;   
                }
                if (valor1==3){
                 temp2 = bfread;   
                }
            }
            
           texto = temp;
           texto1 =temp1;
           texto2 =temp2;
        } catch (Exception e)  {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo de conexión a la base de datos");
        }
        return texto2;
    }
}
