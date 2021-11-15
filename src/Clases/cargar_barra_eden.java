
package Clases;

import javax.swing.JProgressBar;

public class cargar_barra_eden extends Thread{
    JProgressBar progreso;
    
    public cargar_barra_eden (JProgressBar progreso) {
        
        super();
        this.progreso = progreso;
        
    }
    @Override
    public void run(){
        
        for(int a = 1; a<=100; a++)
        {
            progreso.setValue(a);
            pausa(60);
        }
    }
    
    public void pausa(int mlseg){
        try {
            Thread.sleep(mlseg);
        } catch (Exception e) {
        }
    }
}
