package Clases;


import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorearFilas extends DefaultTableCellRenderer {

    private final int columna_patron;

    public ColorearFilas(int Colpatron) {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int column) {
        //Font font = new Font("Courier", Font.BOLD, 16);
        boolean oddRow = (row % 2 == 0);
        //boolean oddColum6 = (column == 7);
        Color c = new Color(230, 230, 230);
        switch (table.getValueAt(row, columna_patron).toString()) {
            
            case "Pendiente":              
                setBackground(new Color(255,92,82));
                setForeground(Color.BLACK);
                setHorizontalAlignment(SwingConstants.CENTER);
                //setFont(font);
                break;
            case "Pagada":
                setBackground(new Color(129,231,122));
                setForeground(Color.BLACK);
                setHorizontalAlignment(SwingConstants.CENTER);
                //setFont(font);
                break;
                case "Pagado":
                setBackground(new Color(129,231,122));
                setForeground(Color.BLACK);
                setHorizontalAlignment(SwingConstants.CENTER);
                //setFont(font);
                break;
                case "Abonada":
                setBackground(new Color(212,255,0));
                setForeground(Color.BLACK);
                setHorizontalAlignment(SwingConstants.CENTER);
                //setFont(font);
                break;
                case "Activo":
                setBackground(new Color(129,231,122));
                setForeground(Color.BLACK);
                setHorizontalAlignment(SwingConstants.CENTER);
                //setFont(font);
                break;
                case "Inactivo":
                setBackground(new Color(255,92,82));
                setForeground(Color.BLACK);
                setHorizontalAlignment(SwingConstants.CENTER);
                //setFont(font);
                break;
            default:
                break;
        }
               if (oddRow) {
            
               }
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, column);
        return this;
    }

}
