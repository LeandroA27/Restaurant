/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.render_tabla_servicios_consulta;
import static Formularios.Articulo.*;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Consulta_Producto extends javax.swing.JFrame {

Fuentes tipofuente;
    public Consulta_Producto() {
        initComponents();
                this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        setCellRender(tabla_servicios);
        //pintarColumna();
        tipofuente = new Fuentes();
        Buscar_serv_in.setFont(tipofuente.fuente(tipofuente.RIO,0,18));
        Buscar_serv_in.requestFocus();
        try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            cargar();
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//////////////////////////////////////////////////////////////////////////////////
    void pintarColumna(){
    Clases.ColorearFilas obj = new  Clases.ColorearFilas(2);
    tabla_servicios.getColumnModel().getColumn(2).setCellRenderer(obj);

} 
    //////////////////////////////////////////////////////////////////////////////
    public void selecion_art(){
       String es="";
       
        
        String[] registros = new String[16];
        String sql = "SELECT cod_art,descripcion,suplidor,marca,ubicacion,costo,ganancia,porciento,precio_sin,precio,oferta,"
                + "existencia,maximo,minimo,reorden,tipo FROM articulo where cod_art='"+codigo_serv_out.getText()+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                registros[0] = rs.getString("cod_art");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("suplidor");
                registros[3] = rs.getString("marca");
                registros[4] = rs.getString("ubicacion");
                registros[5] = rs.getString("costo");
                registros[6] = rs.getString("ganancia");
                registros[7] = rs.getString("porciento");
                registros[8] = rs.getString("precio_sin");
                registros[9] = rs.getString("precio");
                registros[10] = rs.getString("oferta");
                registros[11] = rs.getString("existencia");
                registros[12] = rs.getString("maximo");
                registros[13] = rs.getString("minimo");
                registros[14] = rs.getString("reorden");
                registros[15] = rs.getString("tipo");
                
               cod_art_modif.setText(registros[0]);
               Cocdigo_inv.setText(registros[0]);
                Descrip_inv.setText(registros[1]);
                Suplidor_inv.setText(registros[2]);
                Marca_inv.setText(registros[3]);
                Costo_inv.setText(registros[5]);
                Ganancia_inv.setText(registros[6]);
                Porciento_inv.setText(registros[7]);
                Preciosin_inv.setText(registros[8]);
                Precio_inv.setText(registros[9]);
                PrecioOferta_inv.setText(registros[10]);
                Existencia_inv.setText(registros[11]);
                Maximo_inv.setText(registros[12]);
                Minimo_inv.setText(registros[13]);
                Reorden_inv.setText(registros[14]);
                Tipo_articulo.setSelectedItem(registros[15]);
                
                 es = registros[4];
                if(es.equals("Bar")){
                slcBar_art.setSelected(true);
                }
                if(es.equals("Restaurante")){
                slcRest_art.setSelected(true);
                }
                
               
            int valor1;
            int valor2;
            int valor3;
            int valor4;
            
            valor1 = Integer.parseInt(Existencia_inv.getText());
            valor2 = Integer.parseInt(Maximo_inv.getText());
            valor3 = Integer.parseInt(Minimo_inv.getText());
            valor4 = Integer.parseInt(Reorden_inv.getText());
            
            if(valor1 <= 0){
                Azul_pnl.setVisible(false);
                Verde_pnl.setVisible(false);
                Amarillo_pnl.setVisible(false);
                Naranja_pnl.setVisible(false);
                Rojo_pnl.setVisible(true);
            }
            if(valor1 >= valor2){
                Azul_pnl.setVisible(true);
                Verde_pnl.setVisible(false);
                Amarillo_pnl.setVisible(false);
                Naranja_pnl.setVisible(false);
                Rojo_pnl.setVisible(false);
            }
                if((valor1 < valor2) && (valor1 > valor3)){
                Azul_pnl.setVisible(false);
                Verde_pnl.setVisible(true);
                Amarillo_pnl.setVisible(false);
                Naranja_pnl.setVisible(false);
                Rojo_pnl.setVisible(false);
            }
                if(valor1 == valor4){
                Azul_pnl.setVisible(false);
                Verde_pnl.setVisible(false);
                Amarillo_pnl.setVisible(true);
                Naranja_pnl.setVisible(false);
                Rojo_pnl.setVisible(false);
            }
               if((valor1 < valor4) && (valor1 > 0)){
                Azul_pnl.setVisible(false);
                Verde_pnl.setVisible(false);
                Amarillo_pnl.setVisible(false);
                Naranja_pnl.setVisible(true);
                Rojo_pnl.setVisible(false);
            } 
            
                }     
        }catch(SQLException ex){
              JOptionPane.showMessageDialog(null, ex);
                    
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

void cargar_pro1() throws SQLException {
        String[] registros = new String[12];
        String sql = "SELECT cod_art,descripcion,precio FROM articulo where cod_art='"+codigo_serv_out.getText()+"'";
        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                registros[0] = rs.getString("cod_art");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("precio");
               
                
                Facturacion.cod_serv_fact.setText( registros[0]);
                Facturacion.descrii_fact.setText(registros[1]);
                Facturacion.precio_serv.setText(registros[2]);
                }
            Facturacion.cant_fact.requestFocus();
                    
        }catch(SQLException ex){
              JOptionPane.showMessageDialog(null, ex);
                    
        } 
    }
////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////
    public void seleccionar(){
         int clic1 = Clase_Variable_Publica.art;
             
        //JButton clic1 = factura_caja.btn1_caja;
        
        if (clic1 == 1){
            int fila = tabla_servicios.getSelectedRow();
            if (fila >=0){
                try {
                    cargar_pro1();
                } catch (SQLException ex) {
                    Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.dispose();
                //Facturacion.cant_fact.requestFocus();
            }else{
                JOptionPane.showMessageDialog(null,"debe seleccionar un campo","advertencia",JOptionPane.WARNING_MESSAGE);
            }
        }
        if (clic1 == 2){
            int fila = tabla_servicios.getSelectedRow();
            if (fila >=0){
                selecion_art();
                this.dispose();

            }else{
                JOptionPane.showMessageDialog(null,"debe seleccionar un campo","advertencia",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    void cargar() throws SQLException {
    DefaultTableModel modelo2 =(DefaultTableModel) tabla_servicios.getModel();
    modelo2.getDataVector().clear();
    
    String[] registros = new String[5];
    String sql = "SELECT cod_art,descripcion,existencia FROM articulo order by descripcion ASC";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("cod_art");
        registros[1] = rs.getString("descripcion");
        registros[2] = rs.getString("existencia");
        
        modelo2.addRow(registros);
    }   
       tabla_servicios.setModel(modelo2);
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
    
    void filtro(String valor) throws SQLException{
        DefaultTableModel modelo2 = (DefaultTableModel) tabla_servicios.getModel();
        modelo2.getDataVector().clear();
        
        String[] registros = new String[6];
        String sql = "SELECT cod_art,descripcion,existencia FROM articulo where CONCAT (cod_art,'',descripcion,'',existencia) LIKE '%"+ valor +"%'";
        try{
            Statement st = cn.createStatement();
            ResultSet rs =st.executeQuery(sql);
            
                while (rs.next()) {
                    registros[0] = rs.getString("cod_art");
                    registros[1] = rs.getString("descripcion");
                    registros[2] = rs.getString("existencia");
                    modelo2.addRow(registros);
                }
                tabla_servicios.setModel(modelo2);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
   public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new render_tabla_servicios_consulta());
        }
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cod_client_out = new javax.swing.JLabel();
        codigo_serv_out = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_servicios = new javax.swing.JTable();
        Buscar_serv_in = new javax.swing.JTextField();
        BTN_buscarserv = new javax.swing.JButton();
        btn_seleccionar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        cod_client_out.setText("jLabel2");

        codigo_serv_out.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tabla_servicios.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabla_servicios.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        tabla_servicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "              Codigo", "Descripcion", "           Existencia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_servicios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabla_servicios.setSelectionBackground(new java.awt.Color(198, 54, 53));
        tabla_servicios.setShowHorizontalLines(false);
        tabla_servicios.setShowVerticalLines(false);
        tabla_servicios.getTableHeader().setResizingAllowed(false);
        tabla_servicios.getTableHeader().setReorderingAllowed(false);
        tabla_servicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_serviciosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabla_serviciosMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_serviciosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_servicios);
        if (tabla_servicios.getColumnModel().getColumnCount() > 0) {
            tabla_servicios.getColumnModel().getColumn(0).setResizable(false);
            tabla_servicios.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabla_servicios.getColumnModel().getColumn(1).setResizable(false);
            tabla_servicios.getColumnModel().getColumn(1).setPreferredWidth(300);
            tabla_servicios.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 120, 601, 356));

        Buscar_serv_in.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Buscar_serv_in.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Buscar_serv_in.setToolTipText("Debe dar enter para consultar");
        Buscar_serv_in.setBorder(null);
        Buscar_serv_in.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Buscar_serv_inMouseClicked(evt);
            }
        });
        Buscar_serv_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_serv_inActionPerformed(evt);
            }
        });
        Buscar_serv_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Buscar_serv_inKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Buscar_serv_inKeyTyped(evt);
            }
        });
        getContentPane().add(Buscar_serv_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 82, 208, 30));

        BTN_buscarserv.setToolTipText("Debe dar enter para consultar");
        BTN_buscarserv.setBorder(null);
        BTN_buscarserv.setContentAreaFilled(false);
        BTN_buscarserv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_buscarserv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_buscarservActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_buscarserv, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 85, 20, 20));

        btn_seleccionar.setBackground(new java.awt.Color(76, 76, 76));
        btn_seleccionar.setBorder(null);
        btn_seleccionar.setContentAreaFilled(false);
        btn_seleccionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_seleccionar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_seleccionarMouseMoved(evt);
            }
        });
        btn_seleccionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_seleccionarMouseExited(evt);
            }
        });
        btn_seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_seleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 170, 40));

        btn_cancelar.setBackground(new java.awt.Color(76, 76, 76));
        btn_cancelar.setBorder(null);
        btn_cancelar.setContentAreaFilled(false);
        btn_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cancelar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_cancelarMouseMoved(evt);
            }
        });
        btn_cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cancelarMouseExited(evt);
            }
        });
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, 120, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/consulta.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tabla_serviciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_serviciosMouseClicked
        int fila = tabla_servicios.getSelectedRow();
        codigo_serv_out.setText(tabla_servicios.getValueAt(fila,0).toString());
        Buscar_serv_in.setText(tabla_servicios.getValueAt(fila,1).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_serviciosMouseClicked

    private void tabla_serviciosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_serviciosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_serviciosMouseEntered

    private void tabla_serviciosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_serviciosMousePressed
        int fila = tabla_servicios.getSelectedRow();
        codigo_serv_out.setText(tabla_servicios.getValueAt(fila,0).toString());
        Buscar_serv_in.setText(tabla_servicios.getValueAt(fila,1).toString());        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_serviciosMousePressed

    private void Buscar_serv_inMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buscar_serv_inMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Buscar_serv_inMouseClicked

    private void Buscar_serv_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_serv_inActionPerformed
        try {
            filtro(Buscar_serv_in.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Buscar_serv_inActionPerformed

    private void Buscar_serv_inKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Buscar_serv_inKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_Buscar_serv_inKeyReleased

    private void Buscar_serv_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Buscar_serv_inKeyTyped
        try {
            filtro(Buscar_serv_in.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_Buscar_serv_inKeyTyped

    private void BTN_buscarservActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_buscarservActionPerformed
        try {
            filtro(Buscar_serv_in.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_BTN_buscarservActionPerformed

    private void btn_seleccionarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_seleccionarMouseMoved
        //btn_seleccionar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_seleccionarMouseMoved

    private void btn_seleccionarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_seleccionarMouseExited
        //btn_seleccionar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(76, 76, 76)));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_seleccionarMouseExited

    private void btn_seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionarActionPerformed
seleccionar();
    }//GEN-LAST:event_btn_seleccionarActionPerformed

    private void btn_cancelarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelarMouseMoved
        //btn_cancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarMouseMoved

    private void btn_cancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cancelarMouseExited
        //btn_cancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(76, 76, 76)));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarMouseExited

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Consulta_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consulta_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consulta_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consulta_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consulta_Producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_buscarserv;
    public static javax.swing.JTextField Buscar_serv_in;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_seleccionar;
    public static javax.swing.JLabel cod_client_out;
    public static javax.swing.JLabel codigo_serv_out;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabla_servicios;
    // End of variables declaration//GEN-END:variables
conector cc = new conector();
    Connection cn = cc.conexion();
}
