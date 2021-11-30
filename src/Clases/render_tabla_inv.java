/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Formularios.Inventario;
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
public class render_tabla_inv extends DefaultTableCellRenderer {

    JTable tabla;

    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        setBackground(null);

        //COnstructor de la clase DefaultTableCellRenderer
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //JTableHeader Theader = tabla.getTableHeader();

        boolean oddRow = (row % 2 == 0);
        boolean rowcolor = (row % 2 == 0);

        //Creamos un color para las filas. El 200, 200, 200 en RGB es un color gris
        Color c = new Color(230, 230, 230);
        Color b = new Color(128, 230, 121);

        //Establecemos las filas que queremos cambiar el color. == 0 para pares y != 0 para impares
        boolean oddColu = (column == 0);
        boolean oddColum1 = (column == 1);
        boolean oddColum2 = (column == 2);
        boolean oddColum3 = (column == 3);
        boolean oddColum4 = (column == 4);
        boolean oddColum5 = (column == 5);
        boolean oddColum6 = (column == 6);
        boolean oddColum7 = (column == 7);

//Creamos un color para las filas. El 200, 200, 200 en RGB es un color gris
        //Si las filas son pares, se cambia el color a gris
        if (oddRow) {
           // setBackground(c);
            //Inventario.tb_inventario.setSelectionBackground(b);
        }

        if (oddColu) {
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("HelveticaRounded LT Std Bd", Font.PLAIN, 13) {
            });

        }

        if (oddColum1) {
            setHorizontalAlignment(SwingConstants.LEFT);
            setFont(new Font("HelveticaRounded LT Std Bd", Font.PLAIN, 13) {
            });

        }
        if (oddColum2) {
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("HelveticaRounded LT Std Bd", Font.PLAIN, 13) {
            });
        }

        if (oddColum3) {
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("HelveticaRounded LT Std Bd", Font.PLAIN, 13) {
            });
        }

        if (oddColum4) {
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("HelveticaRounded LT Std Bd", Font.PLAIN, 13) {
            });
        }
        if (oddColum5) {
            setHorizontalAlignment(SwingConstants.RIGHT);
            setFont(new Font("HelveticaRounded LT Std Bd", Font.PLAIN, 13) {
            });
        }
        if (oddColum6) {
            setHorizontalAlignment(SwingConstants.RIGHT);
            setFont(new Font("HelveticaRounded LT Std Bd", Font.PLAIN, 13) {
            });
        }
            if (oddColum7) {
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("HelveticaRounded LT Std Bd", Font.PLAIN, 13) {
            });
        }

        return this;
//To change body of generated methods, choose Tools | Templates.
    }

}
