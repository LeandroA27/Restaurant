/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.generador_numerico;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Menu_Principal extends javax.swing.JFrame implements Runnable {
int x,y; 
Fuentes tipofuente;
ArrayList array = new ArrayList();
DefaultListModel ListaCliente = new DefaultListModel();
String hora, minutos, segundos, ampm;
Calendar calendario;
Thread hi;
String dia;
String nota,year;
String cliente_list = "";
Float ganancia, gastos,ingreso;
int giro =180;
    private Connection conector = null;
    /**
     * Creates new form Menu_Principal
     */
    public Menu_Principal() {
        initComponents();
                
        pregunta_clave.setVisible(false);
        btn_no.setVisible(false);
        btn_si.setVisible(false);
         pregunta_cambiarC.setVisible(false);
        btn_no1.setVisible(false);
        btn_si1.setVisible(false);
        this.setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
    try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    
    Date sistFecha1=new Date();
       int h=sistFecha1.getDay();
       diano.setText(String.valueOf(h));
    if (diano.getText().equals("0")){
       dia="Dom";
       }
    if (diano.getText().equals("1")){
       dia="Lun";
       }
    if (diano.getText().equals("2")){
       dia="Mar";
       }
    if (diano.getText().equals("3")){
       dia="Mi??";
       }
    if (diano.getText().equals("4")){
       dia="Jue";
       }
    if (diano.getText().equals("5")){
       dia="Vie";
       }
    if (diano.getText().equals("6")){
       dia="S??b";
       }
       //menu_fecha1.setText(dia);
        SimpleDateFormat formato1=new SimpleDateFormat("dd MMM.");
        menu_fecha.setText(dia +" "+formato1.format(sistFecha1));
        
        hi = new Thread(this);
        hi.start();
        
        

        
        tipofuente = new Fuentes();
        nombre_usu_cli.setFont(tipofuente.fuente(tipofuente.RIO, 0, 16));
        menu_hora.setFont(tipofuente.fuente(tipofuente.RIO, 0, 16));

        //menu_fecha1.setFont(tipofuente.fuente(tipofuente.RIO,0,16));
        menu_fecha.setFont(tipofuente.fuente(tipofuente.RIO,0,16));
    }
    void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "??Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
///////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu_fecha1 = new javax.swing.JLabel();
        diano = new javax.swing.JLabel();
        btn_minimizar = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        btn_cambiarcontra = new javax.swing.JButton();
        btn_cambiarusu = new javax.swing.JButton();
        btn_si = new javax.swing.JButton();
        btn_no = new javax.swing.JButton();
        btn_si1 = new javax.swing.JButton();
        btn_no1 = new javax.swing.JButton();
        pregunta_clave = new javax.swing.JLabel();
        pregunta_cambiarC = new javax.swing.JLabel();
        menu_hora = new javax.swing.JLabel();
        menu_fecha = new javax.swing.JLabel();
        pnlMain3 = new javax.swing.JPanel();
        pnlMain5 = new javax.swing.JPanel();
        Btnusuario = new javax.swing.JButton();
        Btncomprobantes = new javax.swing.JButton();
        Btndelivery = new javax.swing.JButton();
        Btninventario = new javax.swing.JButton();
        Btndespacho = new javax.swing.JButton();
        Btnarticulo = new javax.swing.JButton();
        Btnfacturacion = new javax.swing.JButton();
        nombre_usu_cli = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        negro_m = new javax.swing.JLabel();

        menu_fecha1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        menu_fecha1.setForeground(new java.awt.Color(255, 255, 255));
        menu_fecha1.setText("Miercoles,");
        menu_fecha1.setToolTipText("Fecha Actual");
        menu_fecha1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        menu_fecha1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
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

        btn_cerrar.setToolTipText("Cerrar");
        btn_cerrar.setBorder(null);
        btn_cerrar.setContentAreaFilled(false);
        btn_cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 55, 30, 31));

        btn_cambiarcontra.setBackground(new java.awt.Color(77, 77, 77));
        btn_cambiarcontra.setToolTipText("Cambiar Contrase??a");
        btn_cambiarcontra.setBorder(null);
        btn_cambiarcontra.setContentAreaFilled(false);
        btn_cambiarcontra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cambiarcontra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_cambiarcontraMouseMoved(evt);
            }
        });
        btn_cambiarcontra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cambiarcontraMouseExited(evt);
            }
        });
        btn_cambiarcontra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarcontraActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cambiarcontra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1165, 55, 30, 31));

        btn_cambiarusu.setBackground(new java.awt.Color(77, 77, 77));
        btn_cambiarusu.setToolTipText("Cambiar de Usuario");
        btn_cambiarusu.setBorder(null);
        btn_cambiarusu.setContentAreaFilled(false);
        btn_cambiarusu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cambiarusu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_cambiarusuMouseMoved(evt);
            }
        });
        btn_cambiarusu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cambiarusuMouseExited(evt);
            }
        });
        btn_cambiarusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarusuActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cambiarusu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 235, 210, 40));

        btn_si.setBorder(null);
        btn_si.setContentAreaFilled(false);
        btn_si.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siActionPerformed(evt);
            }
        });
        getContentPane().add(btn_si, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 300, 20, 20));

        btn_no.setBorder(null);
        btn_no.setContentAreaFilled(false);
        btn_no.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_noActionPerformed(evt);
            }
        });
        getContentPane().add(btn_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 300, 20, 20));

        btn_si1.setBorder(null);
        btn_si1.setContentAreaFilled(false);
        btn_si1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_si1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_si1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_si1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1006, 70, 20, 20));

        btn_no1.setBorder(null);
        btn_no1.setContentAreaFilled(false);
        btn_no1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_no1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_no1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_no1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1086, 70, 20, 20));

        pregunta_clave.setBackground(new java.awt.Color(255, 255, 255));
        pregunta_clave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pregunta_User.png"))); // NOI18N
        getContentPane().add(pregunta_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 270, 200, 60));

        pregunta_cambiarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pregunta_clave.png"))); // NOI18N
        getContentPane().add(pregunta_cambiarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(967, 40, 190, 60));

        menu_hora.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        menu_hora.setForeground(new java.awt.Color(193, 14, 26));
        menu_hora.setToolTipText("Hora Actual");
        menu_hora.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        menu_hora.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(menu_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(857, 57, 110, 30));

        menu_fecha.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        menu_fecha.setForeground(new java.awt.Color(193, 14, 26));
        menu_fecha.setToolTipText("Fecha Actual");
        menu_fecha.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        menu_fecha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(menu_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 57, 110, 30));

        pnlMain3.setBackground(new java.awt.Color(236, 236, 236));
        getContentPane().add(pnlMain3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 450, 60));

        pnlMain5.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(pnlMain5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 275, 190, 50));

        Btnusuario.setContentAreaFilled(false);
        Btnusuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(Btnusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 240, 40));

        Btncomprobantes.setContentAreaFilled(false);
        Btncomprobantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btncomprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtncomprobantesActionPerformed(evt);
            }
        });
        getContentPane().add(Btncomprobantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 240, 40));

        Btndelivery.setContentAreaFilled(false);
        Btndelivery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btndelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtndeliveryActionPerformed(evt);
            }
        });
        getContentPane().add(Btndelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 240, 40));

        Btninventario.setContentAreaFilled(false);
        Btninventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btninventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtninventarioActionPerformed(evt);
            }
        });
        getContentPane().add(Btninventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 240, 40));

        Btndespacho.setContentAreaFilled(false);
        Btndespacho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btndespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtndespachoActionPerformed(evt);
            }
        });
        getContentPane().add(Btndespacho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 240, 40));

        Btnarticulo.setContentAreaFilled(false);
        Btnarticulo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnarticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnarticuloActionPerformed(evt);
            }
        });
        getContentPane().add(Btnarticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 240, 40));

        Btnfacturacion.setContentAreaFilled(false);
        Btnfacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnfacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnfacturacionActionPerformed(evt);
            }
        });
        getContentPane().add(Btnfacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 240, 40));
        getContentPane().add(nombre_usu_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 255, 160, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 255, 170, 20));

        negro_m.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu.png"))); // NOI18N
        negro_m.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                negro_mMouseClicked(evt);
            }
        });
        getContentPane().add(negro_m, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizarActionPerformed
        this.setState(Menu_Principal.ICONIFIED);          // TODO add your handling code here:
    }//GEN-LAST:event_btn_minimizarActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        close();         // TODO add your handling code here:
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_cambiarcontraMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cambiarcontraMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cambiarcontraMouseMoved

    private void btn_cambiarcontraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cambiarcontraMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cambiarcontraMouseExited

    private void btn_cambiarcontraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarcontraActionPerformed
        //if (JOptionPane.showConfirmDialog(rootPane, "??Desea cambiar la contrase??a del usuario?",
            //                "Cambiar contrase??a", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
        //
        //String texto = JOptionPane.showInputDialog(null, "Nueva Contrase??a");
        //    nota = texto;
        //    if(!nota.isEmpty()){
            //    try {
                //        actualizar_contra();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
            //}else{
            // JOptionPane.showMessageDialog(null, "Debe de ingresar la contrase??a para poder cambiarla");
            //
            //}
        //}else{}
        if(pregunta_cambiarC.isVisible()){
            pregunta_cambiarC.setVisible(false);
            btn_no1.setVisible(false);
            btn_si1.setVisible(false);
        }else{
            pregunta_cambiarC.setVisible(true);
            btn_no1.setVisible(true);
            btn_si1.setVisible(true);
        }
    }//GEN-LAST:event_btn_cambiarcontraActionPerformed

    private void btn_cambiarusuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cambiarusuMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cambiarusuMouseMoved

    private void btn_cambiarusuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cambiarusuMouseExited

    }//GEN-LAST:event_btn_cambiarusuMouseExited

    private void btn_cambiarusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarusuActionPerformed
        if(pregunta_clave.isVisible()){
            pregunta_clave.setVisible(false);
            btn_no.setVisible(false);
            btn_si.setVisible(false);
        }else{
            pregunta_clave.setVisible(true);
            btn_no.setVisible(true);
            btn_si.setVisible(true);
        }
    }//GEN-LAST:event_btn_cambiarusuActionPerformed

    private void btn_si1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_si1ActionPerformed
        pregunta_cambiarC.setVisible(false);
        btn_no1.setVisible(false);
        btn_si1.setVisible(false);
        Cambio_Clave cd = new Cambio_Clave();
        cd.setVisible(true);
       Cambio_Clave.usuario_c.setText(nombre_usu_cli.getText());

    }//GEN-LAST:event_btn_si1ActionPerformed

    private void btn_no1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_no1ActionPerformed
        pregunta_cambiarC.setVisible(false);
        btn_no1.setVisible(false);
        btn_si1.setVisible(false);         // TODO add your handling code here:
    }//GEN-LAST:event_btn_no1ActionPerformed

    private void btn_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siActionPerformed
        pregunta_clave.setVisible(false);
        btn_no.setVisible(false);
        btn_si.setVisible(false);
        this.dispose();
        //System.exit(0);
        Login lg = new Login();
        lg.setVisible(true);
        //Clase_Variable_Publica.valor=0;

        //Cambio_de_clave cd = new Cambio_de_clave();
        //cd.setVisible(true);
        //usuario_c.setForeground(Color.WHITE);
        //Cambio_de_clave.usuario_c.setText(nombre_usu_cli.getText());
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_siActionPerformed

    private void btn_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_noActionPerformed
        pregunta_clave.setVisible(false);
        btn_no.setVisible(false);
        btn_si.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_noActionPerformed

    private void negro_mMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_negro_mMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_negro_mMouseClicked

    private void BtnfacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnfacturacionActionPerformed
if(Clase_Variable_Publica.permiso_fact==0){
    if(Clase_Variable_Publica.modulo == 1){
        JOptionPane.showMessageDialog(rootPane, "El formulario de facturacion ya esta abierto");
    }else{
      Facturacion fct = new Facturacion();
fct.setVisible(true);
Facturacion.nombre_usu_fact.setText(nombre_usu_cli.getText());  
    }

}else{
JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para facturacion");
}
// TODO add your handling code here:
    }//GEN-LAST:event_BtnfacturacionActionPerformed

    private void BtncomprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtncomprobantesActionPerformed
if(Clase_Variable_Publica.permiso_compro==0){
if(Clase_Variable_Publica.modulo == 6){
        JOptionPane.showMessageDialog(rootPane, "El formulario de comprobantes ya esta abierto");
    }else{
Comprobantes fact = new Comprobantes();
fact.setVisible(true);
Comprobantes.nombre_usu_comp.setText(nombre_usu_cli.getText());
//this.dispose();
}
}else{
JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para comprobantes");
}
                // TODO add your handling code here:
    }//GEN-LAST:event_BtncomprobantesActionPerformed

    private void BtnarticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnarticuloActionPerformed
if(Clase_Variable_Publica.permiso_art==0){
    if(Clase_Variable_Publica.modulo == 2){
      JOptionPane.showMessageDialog(rootPane, "El modulo de articulo ya esta abierto");  
    }else{
            Articulo art = new Articulo();
art.setVisible(true);
Articulo.nombre_usu_art.setText(nombre_usu_cli.getText());
//this.dispose();    
    }
}else{
JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para articulo");
}
// TODO add your handling code here:
    }//GEN-LAST:event_BtnarticuloActionPerformed

    private void BtninventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtninventarioActionPerformed
if(Clase_Variable_Publica.permiso_inv==0){
if(Clase_Variable_Publica.modulo == 4){
      JOptionPane.showMessageDialog(rootPane, "El modulo de inventario ya esta abierto");  
    }else{
Inventario inv = new Inventario();
inv.setVisible(true);
Inventario.nombre_usu_inv.setText(nombre_usu_cli.getText());
//this.dispose();
}
}else{
JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para inventario");
}
        // TODO add your handling code here:
    }//GEN-LAST:event_BtninventarioActionPerformed

    private void BtndespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtndespachoActionPerformed
if(Clase_Variable_Publica.permiso_desp==0){
  if(Clase_Variable_Publica.modulo == 3){
      JOptionPane.showMessageDialog(rootPane, "El modulo de despacho ya esta abierto");  
    }else{
        Despacho des = new Despacho();
        des.setVisible(true);
//Facturacion.nombre_usu_fac.setText(nombre_usu_cli.getText());
//this.dispose();
  }
}else{
JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para despacho");
}
// TODO add your handling code here:
    }//GEN-LAST:event_BtndespachoActionPerformed

    private void BtnusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnusuarioActionPerformed
if(Clase_Variable_Publica.permiso_usu==0){
 if(Clase_Variable_Publica.modulo == 7){
      JOptionPane.showMessageDialog(rootPane, "El modulo de Usuarios ya esta abierto");  
    }else{
        Usuarios usu = new Usuarios();
usu.setVisible(true);
Usuarios.nombre_usu_usu.setText(nombre_usu_cli.getText());
//this.dispose();
 }
}else{
JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para usuarios");
}
// TODO add your handling code here:
    }//GEN-LAST:event_BtnusuarioActionPerformed

    private void BtndeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtndeliveryActionPerformed
if(Clase_Variable_Publica.permiso_delv==0){
if(Clase_Variable_Publica.modulo == 5){
      JOptionPane.showMessageDialog(rootPane, "El modulo de delivery ya esta abierto");  
    }else{
        Delivery del = new Delivery();
del.setVisible(true);
    Delivery.nombre_usu_delv.setText(nombre_usu_cli.getText());
//this.dispose();
}
}else{
JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para delivery");
}
// TODO add your handling code here:
    }//GEN-LAST:event_BtndeliveryActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnarticulo;
    private javax.swing.JButton Btncomprobantes;
    private javax.swing.JButton Btndelivery;
    private javax.swing.JButton Btndespacho;
    private javax.swing.JButton Btnfacturacion;
    private javax.swing.JButton Btninventario;
    private javax.swing.JButton Btnusuario;
    private javax.swing.JButton btn_cambiarcontra;
    private javax.swing.JButton btn_cambiarusu;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_minimizar;
    private javax.swing.JButton btn_no;
    private javax.swing.JButton btn_no1;
    private javax.swing.JButton btn_si;
    private javax.swing.JButton btn_si1;
    private javax.swing.JLabel diano;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel menu_fecha;
    public static javax.swing.JLabel menu_fecha1;
    private javax.swing.JLabel menu_hora;
    private javax.swing.JLabel negro_m;
    public static javax.swing.JLabel nombre_usu_cli;
    private javax.swing.JPanel pnlMain3;
    private javax.swing.JPanel pnlMain5;
    private javax.swing.JLabel pregunta_cambiarC;
    private javax.swing.JLabel pregunta_clave;
    // End of variables declaration//GEN-END:variables
public void run() {
    Thread ct= Thread.currentThread();
    
    while(ct==hi){
    calcula();
    menu_hora.setText(hora+":"+minutos+" "+ampm);
    //:"+segundos+"
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){}
        }
    }
    private void calcula(){
       Calendar calendario = new GregorianCalendar();
       Date fechaHoraActal = new Date();

       calendario.setTime(fechaHoraActal);
       ampm= calendario.get(Calendar.AM_PM)==Calendar.AM?"A.M.":"P.M.";
    if(ampm.equals("P.M.")){
    int h= calendario.get(Calendar.HOUR_OF_DAY)-12;
    hora=h>9?""+h:"0"+h;
    }else{
    hora=calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
    }
        minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos=calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
            
    }
        conector cc = new conector();
    Connection cn = cc.conexion();
}
