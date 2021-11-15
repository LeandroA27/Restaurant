/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Thealex7
 */
public class render_tabla_usuario extends DefaultTableCellRenderer{
 JTable tabla;
    
    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
            boolean hasFocus, int row, int column) {
        
setBackground(null);


        //COnstructor de la clase DefaultTableCellRenderer
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //JTableHeader Theader = tabla.getTableHeader();
        
        
        
//        Theader.setBackground(Color.red); // change the Background color
//        Theader.setForeground(Color.WHITE); // change the Foreground
//         ((DefaultTableCellRenderer)Theader.getDefaultRenderer())
//                .setHorizontalAlignment(JLabel.CENTER);
//Theader.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
         // font name style size
        
 
        //Establecemos las filas que queremos cambiar el color. == 0 para pares y != 0 para impares
        boolean oddColu = (column == 0);
        boolean oddColum1 = (column == 1);
        boolean oddColum2 = (column == 2);
        boolean oddColum3 = (column == 3);
        
//Creamos un color para las filas. El 200, 200, 200 en RGB es un color gris
        Color c = new Color(164, 229, 129);

 
        //Si las filas son pares, se cambia el color a gris
         if (oddColu) {
            setHorizontalAlignment(SwingConstants.CENTER);
            
               }
        
        if (oddColum1) {
        setHorizontalAlignment(SwingConstants.LEFT);
        
        }
        if (oddColum2) {
        setHorizontalAlignment(SwingConstants.LEFT);
        }
                if (oddColum3) {
        setHorizontalAlignment(SwingConstants.CENTER);
        }

                                
        return this;
//To change body of generated methods, choose Tools | Templates.
    }

}
