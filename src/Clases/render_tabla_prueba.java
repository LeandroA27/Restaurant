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
public class render_tabla_prueba extends DefaultTableCellRenderer{

    
    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
            boolean hasFocus, int row, int column) {
        
setBackground(null);


        //COnstructor de la clase DefaultTableCellRenderer
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        JTableHeader Theader = table.getTableHeader();
        
        
        
        boolean oddRow = (row % 2 == 0);
 
        //Creamos un color para las filas. El 200, 200, 200 en RGB es un color gris
        Color c = new Color(230, 230, 230);
        
        boolean oddColu = (column == 0);
        boolean oddColum = (column == 2);
        boolean oddColum1 = (column == 3);
        boolean oddColum2 = (column == 4);
        boolean oddColum3 = (column == 5);
        

 
        //Si las filas son pares, se cambia el color a gris
                 if (oddRow) {
            //setBackground(c);
            
               }
        
         if (oddColu) {
            setHorizontalAlignment(SwingConstants.CENTER);
            
               }
        
        if (oddColum) {
            setHorizontalAlignment(SwingConstants.RIGHT);
            
               }
        if (oddColum1) {
        setHorizontalAlignment(SwingConstants.CENTER);
        
        }
        if (oddColum2) {
        setHorizontalAlignment(SwingConstants.RIGHT);
        }
        if (oddColum3) {
        setHorizontalAlignment(SwingConstants.RIGHT);
        }
                                
        return this;
//To change body of generated methods, choose Tools | Templates.
    }

}
