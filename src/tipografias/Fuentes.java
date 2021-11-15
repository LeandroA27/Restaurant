/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipografias;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author theal
 */
public class Fuentes {
    
    private Font font = null;
    public String RIO = "HelveticaRoundedLTStd-Bd.otf";
    public String HEL = "Helveticamazing.ttf";
    
   public Font fuente(String fontName, int estilo, float tamanio) 
   {
       
       //PLAINT = 0   Bold = 1 Italic = 2
       
       try {
           //se carga la fuente
           InputStream is = getClass().getResourceAsStream(fontName);
           font = Font.createFont(Font.TRUETYPE_FONT, is);
           
       } catch (Exception e) {
           System.err.println(fontName +" no se cargo la fuente");
           font = new Font("Dialog", Font.PLAIN,13);
       }
       Font tfont = font.deriveFont(estilo, tamanio);
       return tfont;
   }   
}
