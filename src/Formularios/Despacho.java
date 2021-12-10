/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.Timer_Despacho;
import Clases.conexion_2;
import Clases.funcion_despacho;
import static Formularios.Consulta_Producto.tabla_servicios;
import static Formularios.Facturacion.cod_serv_fact;
import static Formularios.Facturacion.tablafacturacion1;
import static Formularios.Reimprimir.Buscar_facrt_in;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.Timer;
import javax.swing.table.DefaultTableModel;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Despacho extends javax.swing.JFrame implements Runnable {
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout; 
    Thread hi;
    
    Fuentes tipofuente;
    private Connection conexion = null;
    public Despacho() {
        initComponents();
        Clase_Variable_Publica.modulo = 3;
                this.setLocationRelativeTo(null);
        this.setBackground(new Color(0,0,0,0));
        try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
                tipofuente = new Fuentes();
        cantidad_pedidos.setFont(tipofuente.fuente(tipofuente.RIO, 0, 30));
        tablaDespacho.setFont(tipofuente.fuente(tipofuente.RIO, 0, 30));
        Timer_Despacho despacho = new Timer_Despacho();
        despacho.run();
        hi = new Thread(this);
        hi.start();
      
    }
    
    public void conectar() throws SQLException {
        try {
            conecta();
            //comparacion_fecha();
          cargar_pedidos();
        } finally {
            cerrar();
        }
    }
    void cerrar(){

    }
        public void conecta() throws SQLException{
//           String direc = conexion_2.valor;
//    String contra = conexion_2.clave;
//    String usu = conexion_2.usu;
//    
//        String jdbc= direc;
        conexion = DriverManager.getConnection(conexion_2.cadena1,conexion_2.cadena2,conexion_2.cadena3);
        conexion.setAutoCommit(false);
    }
    public void cargar_pedidos(){
    String desp = "No";
    DefaultTableModel modelo3 =(DefaultTableModel) tablaDespacho.getModel();
    modelo3.getDataVector().clear();
    DefaultTableModel dtm = (DefaultTableModel) tablaDespacho.getModel();
        dtm.setRowCount(0);
    String[] registros = new String[6];
    final String sql = "SELECT cantidad,descr_serv,num_factura,tipo_despacho FROM detalle_fact where despacho='"+desp+"' order by num_factura ASC";
    
    try{
          Statement st = cn.createStatement();
          ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("cantidad");
        registros[1] = rs.getString("descr_serv");
        registros[2]= rs.getString("num_factura"); 
        registros[3]= rs.getString("tipo_despacho"); 


        modelo3.addRow(registros);
    }
    tablaDespacho.setModel(modelo3);
    }catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
           }
                

void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar despacho?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            this.dispose();
    }
public void contar_filas(){
//    for(int i=1;i<tablaDespacho.getRowCount();i++)
//{
//int fila = i+1;
//if(fila==1){
//  cantidad_pedidos.setText("0");  
//}else{
//  cantidad_pedidos.setText(""+fila);
//}
//} 

DefaultTableModel model = (DefaultTableModel) tablaDespacho.getModel();
        int filas = model.getRowCount();
        cantidad_pedidos.setText(String.valueOf(filas)); 
}
public void escucha(){
    ServerSocket servidor = null;
    Socket sc = null;
    final int PUERTO = 5000;
    DataInputStream in;
    DataOutputStream out;
    
        try {
            servidor = new ServerSocket(PUERTO);
            
            while(true){
                sc = servidor.accept();
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
                
                String mensaje = in.readUTF();
                
                //JOptionPane.showMessageDialog(null, mensaje);
                out.writeUTF("Hay una Conexion");
                sc.close();  
                Timer_Despacho despacho = new Timer_Despacho();
                despacho.run();
            }
            
        } catch (Exception e) {
        }
 
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        descri_out = new javax.swing.JLabel();
        numfact_out = new javax.swing.JLabel();
        btn_minimizar = new javax.swing.JButton();
        volverAtras = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDespacho = new javax.swing.JTable();
        enviar_cl = new javax.swing.JTextField();
        cantidad_pedidos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        descri_out.setText("jLabel2");

        numfact_out.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_minimizar.setToolTipText("Minimizar");
        btn_minimizar.setBorder(null);
        btn_minimizar.setContentAreaFilled(false);
        btn_minimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_minimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimizarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1212, 55, 30, 31));

        volverAtras.setBorder(null);
        volverAtras.setContentAreaFilled(false);
        volverAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volverAtras.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                volverAtrasMouseMoved(evt);
            }
        });
        volverAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                volverAtrasMouseExited(evt);
            }
        });
        volverAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverAtrasActionPerformed(evt);
            }
        });
        getContentPane().add(volverAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 57, 30, 29));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 600, 220, 70));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tablaDespacho.setAutoCreateRowSorter(true);
        tablaDespacho.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        tablaDespacho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cantidad", "Detalle", "factura", "Despacho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDespacho.setName(""); // NOI18N
        tablaDespacho.setPreferredSize(new java.awt.Dimension(1100, 460));
        tablaDespacho.setRowHeight(50);
        tablaDespacho.setRowMargin(0);
        tablaDespacho.setSelectionBackground(new java.awt.Color(198, 54, 53));
        tablaDespacho.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaDespacho.setShowHorizontalLines(false);
        tablaDespacho.setShowVerticalLines(false);
        tablaDespacho.getTableHeader().setResizingAllowed(false);
        tablaDespacho.getTableHeader().setReorderingAllowed(false);
        tablaDespacho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDespachoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDespacho);
        if (tablaDespacho.getColumnModel().getColumnCount() > 0) {
            tablaDespacho.getColumnModel().getColumn(0).setResizable(false);
            tablaDespacho.getColumnModel().getColumn(0).setPreferredWidth(100);
            tablaDespacho.getColumnModel().getColumn(1).setResizable(false);
            tablaDespacho.getColumnModel().getColumn(1).setPreferredWidth(800);
            tablaDespacho.getColumnModel().getColumn(2).setResizable(false);
            tablaDespacho.getColumnModel().getColumn(2).setPreferredWidth(150);
            tablaDespacho.getColumnModel().getColumn(3).setResizable(false);
            tablaDespacho.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 178, 1205, 406));

        enviar_cl.setBackground(new java.awt.Color(228, 228, 228));
        enviar_cl.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        enviar_cl.setForeground(new java.awt.Color(198, 54, 53));
        enviar_cl.setBorder(null);
        enviar_cl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enviar_clKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enviar_clKeyTyped(evt);
            }
        });
        getContentPane().add(enviar_cl, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 420, 30));

        cantidad_pedidos.setForeground(new java.awt.Color(198, 54, 53));
        getContentPane().add(cantidad_pedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 606, 150, 60));

        jLabel1.setBackground(java.awt.Color.gray);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Despacho.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, -1, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizarActionPerformed
        this.setState(Menu_Principal.ICONIFIED);          // TODO add your handling code here:
    }//GEN-LAST:event_btn_minimizarActionPerformed

    private void volverAtrasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverAtrasMouseMoved
        //volverAtras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));         // TODO add your handling code here:
    }//GEN-LAST:event_volverAtrasMouseMoved

    private void volverAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverAtrasMouseExited
        //        volverAtras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(76, 76, 76)));             // TODO add your handling code here:
    }//GEN-LAST:event_volverAtrasMouseExited

    private void volverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverAtrasActionPerformed
        //Menu_Principal ob = new Menu_Principal();
        //ob.setVisible(true);
        //nombre_usu_cli.setText(nombre_usu_fac.getText());
        close(); 
        Clase_Variable_Publica.modulo = 0;// TODO add your handling code here:
    }//GEN-LAST:event_volverAtrasActionPerformed

    private void tablaDespachoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDespachoMouseClicked
        int fila = tablaDespacho.getSelectedRow();
        descri_out.setText(tablaDespacho.getValueAt(fila,1).toString());
        numfact_out.setText(tablaDespacho.getValueAt(fila,2).toString());        // TODO add your handling code here:
    }//GEN-LAST:event_tablaDespachoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String desp = "Si";   
        try{
            PreparedStatement psU2 = cn.prepareStatement("UPDATE detalle_fact SET despacho='"+desp+"' where num_factura='"+numfact_out.getText()+"' and descr_serv='"+descri_out.getText()+"'");
            psU2.executeUpdate();
                DefaultTableModel dtm = (DefaultTableModel) tablaDespacho.getModel();
        dtm.setRowCount(0);
            cargar_pedidos();
            contar_filas();
             //JOptionPane.showMessageDialog(null, "El articulo "+descri_out.getText()+" Se ha modificado satisfactoriamente");

     }catch (SQLException ex){
               Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE,null, ex);
     }        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1ActionPerformed

    private void enviar_clKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enviar_clKeyPressed
      // TODO add your handling code here:
    }//GEN-LAST:event_enviar_clKeyPressed

    private void enviar_clKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enviar_clKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != '.') && (c != '@')
            && (c != '#') && (c != 'ñ') && (c != 'Ñ') && (c != '!') && (c != '$') && (c != '%') && (c != '&') && (c != '?') && (c != ',') && (c != ':') && (c != ';') && c != KeyEvent.VK_SPACE) {
            evt.consume();
        }// TODO add your handling code here:
    }//GEN-LAST:event_enviar_clKeyTyped

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
            java.util.logging.Logger.getLogger(Despacho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Despacho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Despacho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Despacho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Despacho().setVisible(true);
                
                
                
                
            }
        });   
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_minimizar;
    public static javax.swing.JLabel cantidad_pedidos;
    private javax.swing.JLabel descri_out;
    public static javax.swing.JTextField enviar_cl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numfact_out;
    public static javax.swing.JTable tablaDespacho;
    private javax.swing.JButton volverAtras;
    // End of variables declaration//GEN-END:variables
    @Override
    public void run() {
    Thread ct= Thread.currentThread();
    
    while(ct==hi){
    escucha();
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){}
        }    
        
      
    }
    conector cc = new conector();
    Connection cn = cc.conexion();

}
