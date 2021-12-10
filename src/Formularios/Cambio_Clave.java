/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.conexion_2;
import Clases.funciones_login;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Cambio_Clave extends javax.swing.JFrame {

     private Connection conexion = null;
   Fuentes tipofuente;
   
    public Cambio_Clave() {
        initComponents();
                try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0,0,0,0));
                tipofuente = new Fuentes();
        usuario_c.setFont(tipofuente.fuente(tipofuente.RIO,0,14));
        clave_anterior.setFont(tipofuente.fuente(tipofuente.RIO,0,14));
        clave_nueva.setFont(tipofuente.fuente(tipofuente.RIO,0,14));
        confirmar_clave.setFont(tipofuente.fuente(tipofuente.RIO,0,14));
        clave_anterior.setEchoChar((char)0);  
        clave_nueva.setEchoChar((char)0);  
        confirmar_clave.setEchoChar((char)0);  
        clave_anterior.moveCaretPosition(0);
        clave_anterior.requestFocus();
        Cambio_Clave.clave_nueva.setEnabled(false);
        Cambio_Clave.confirmar_clave.setEnabled(false);
        
        if(clave_anterior.isFocusable()){
            clave_nueva.moveCaretPosition(0); 
         clave_anterior.setNextFocusableComponent (clave_anterior);
        }
        
         if(clave_nueva.isFocusable()){
         if(clave_nueva.getText().equals("Contraseña Nueva")){   
         confirmar_clave.moveCaretPosition(0); 
         }
         clave_nueva.setNextFocusableComponent (confirmar_clave);
        }
         if(confirmar_clave.isFocusable()){
         if(confirmar_clave.getText().equals("Confirmar Contraseña")){ 
         usuario_c.moveCaretPosition(0); 
         }
         confirmar_clave.setNextFocusableComponent (usuario_c);
        }  
        
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
    /////////////////////////////////////////////////////////////////////////////// funcion para cargar ncf
      public void conectar() throws SQLException {
        try {
            conecta();
               String usu = usuario_c.getText();
               String pas =new String(clave_anterior.getPassword());
            acceder(usu,pas);
        } finally {
            cerrar();
        }
    }   
  public void cerrar() throws SQLException{
        
    } 
  //-----------------------------------------------------------------------------------------------------------//
  void acceder (String usuario, String pass){
    String clave = null,usu = null;
    String sql = "SELECT nombre_usu,estatus FROM usuario where nombre_usu='"+usuario+"' && contra_usu ='"+clave_anterior.getText()+"'";

try{
    java.sql.Statement st = cn.createStatement();
    ResultSet conex = st.executeQuery(sql);
    
    while (conex.next()) {
        usu = conex.getString("estatus");     
    }
    
    if(!usu.equals("Activo") && !usu.equals("Inactivo")){
       JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta"); 
       clave_anterior.setText("");
    }
    
    if(usu.equals("Activo")){
     JOptionPane.showMessageDialog(null, "esta funcionando la cuestion");
    }
      
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

    }
 //-------------------------------------------------------------------------------------------------------------//
  public void modificar_Clave() throws SQLException{
   String contra = new String(clave_nueva.getPassword());   
      PreparedStatement psU2 = cn.prepareStatement("UPDATE usuario SET contra_usu='"+contra+"' where nombre_usu='"+usuario_c.getText()+"'");
      psU2.executeUpdate();
      JOptionPane.showMessageDialog(null,"La contraseña fue modificada");
       clave_anterior.setText("Contraseña Actual");
       //clave_anterior.setForeground(new Color(153,153,153));
      clave_nueva.setText("Contraseña Nueva");
      //clave_nueva.setForeground(new Color(153,153,153));
      confirmar_clave.setText("Confirmar Contraseña");
      //confirmar_clave.setForeground(new Color(153,153,153));
      clave_anterior.setEchoChar((char)0);  
        clave_nueva.setEchoChar((char)0);  
        confirmar_clave.setEchoChar((char)0);  
        clave_anterior.moveCaretPosition(0);
        clave_anterior.requestFocus();
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        BTN_cancelar = new javax.swing.JButton();
        BTN_procesar = new javax.swing.JButton();
        vistaCConfirmar = new javax.swing.JToggleButton();
        vistaCNueva = new javax.swing.JToggleButton();
        vistaCAnterior = new javax.swing.JToggleButton();
        usuario_c = new javax.swing.JTextField();
        clave_anterior = new javax.swing.JPasswordField();
        clave_nueva = new javax.swing.JPasswordField();
        confirmar_clave = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        BTN_cancelar.setBorder(null);
        BTN_cancelar.setContentAreaFilled(false);
        BTN_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_cancelarActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 120, 40));

        BTN_procesar.setBorder(null);
        BTN_procesar.setContentAreaFilled(false);
        BTN_procesar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_procesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_procesarActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 120, 40));

        vistaCConfirmar.setBorder(null);
        vistaCConfirmar.setContentAreaFilled(false);
        vistaCConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vistaCConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vistaCConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(vistaCConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 30, 25));

        vistaCNueva.setBorder(null);
        vistaCNueva.setContentAreaFilled(false);
        vistaCNueva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vistaCNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vistaCNuevaActionPerformed(evt);
            }
        });
        getContentPane().add(vistaCNueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 30, 25));

        vistaCAnterior.setBorder(null);
        vistaCAnterior.setContentAreaFilled(false);
        vistaCAnterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vistaCAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vistaCAnteriorActionPerformed(evt);
            }
        });
        getContentPane().add(vistaCAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 30, 25));

        usuario_c.setEditable(false);
        usuario_c.setBackground(new java.awt.Color(255, 255, 255));
        usuario_c.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        usuario_c.setText("Ingrese el  Usuario");
        usuario_c.setBorder(null);
        usuario_c.setCaretColor(new java.awt.Color(255, 255, 255));
        usuario_c.setSelectedTextColor(new java.awt.Color(153, 153, 153));
        usuario_c.setSelectionColor(new java.awt.Color(76, 76, 76));
        usuario_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuario_cActionPerformed(evt);
            }
        });
        usuario_c.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuario_cKeyPressed(evt);
            }
        });
        getContentPane().add(usuario_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 97, 190, 25));

        clave_anterior.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        clave_anterior.setText("Contraseña Actual");
        clave_anterior.setBorder(null);
        clave_anterior.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        clave_anterior.setSelectionColor(new java.awt.Color(255, 255, 255));
        clave_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clave_anteriorActionPerformed(evt);
            }
        });
        clave_anterior.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                clave_anteriorKeyPressed(evt);
            }
        });
        getContentPane().add(clave_anterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 150, 210, 25));

        clave_nueva.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        clave_nueva.setText("Contraseña Nueva");
        clave_nueva.setBorder(null);
        clave_nueva.setSelectedTextColor(new java.awt.Color(153, 153, 153));
        clave_nueva.setSelectionColor(new java.awt.Color(76, 76, 76));
        clave_nueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clave_nuevaActionPerformed(evt);
            }
        });
        clave_nueva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                clave_nuevaKeyPressed(evt);
            }
        });
        getContentPane().add(clave_nueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 210, 230, 25));

        confirmar_clave.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        confirmar_clave.setText("Confirmar Contraseña");
        confirmar_clave.setBorder(null);
        confirmar_clave.setSelectedTextColor(new java.awt.Color(153, 153, 153));
        confirmar_clave.setSelectionColor(new java.awt.Color(76, 76, 76));
        confirmar_clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmar_claveActionPerformed(evt);
            }
        });
        confirmar_clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmar_claveKeyPressed(evt);
            }
        });
        getContentPane().add(confirmar_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 256, 230, 25));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cambio_clave.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_cancelarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_cancelarActionPerformed

    private void BTN_procesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_procesarActionPerformed
        if(!clave_anterior.getText().equals("Contraseña Actual")){
            String contra1 = new String(clave_nueva.getPassword());
            String contra2 = new String(confirmar_clave.getPassword());
            if(contra1.equals(contra2)){
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cambiar la contraseña?",
                    "Cambiar Contraseña", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    try {
                        modificar_Clave();
                        this.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(Cambio_Clave.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }else{
            JOptionPane.showMessageDialog(null, "La contraseña no coincide favor confirmar de nuevo ");
            confirmar_clave.setText("");
            confirmar_clave.requestFocus();
        }
        }else{
            JOptionPane.showMessageDialog(null, "Debe de ingresar la contraseña actual antes de proceder");
            clave_anterior.moveCaretPosition(0);
            clave_anterior.requestFocus();
        }
    }//GEN-LAST:event_BTN_procesarActionPerformed

    private void vistaCConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vistaCConfirmarActionPerformed
        String clave = new String(confirmar_clave.getPassword());
        if(!clave.equals("Confirmar Contraseña")){
            if(vistaCConfirmar.isSelected()){
                confirmar_clave.setEchoChar((char)0);
                confirmar_clave.requestFocus();
            }else{
                confirmar_clave.setEchoChar('•');
                confirmar_clave.requestFocus();
            }
        }else{
        }         // TODO add your handling code here:
    }//GEN-LAST:event_vistaCConfirmarActionPerformed

    private void vistaCNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vistaCNuevaActionPerformed
        String clave = new String(clave_nueva.getPassword());
        if(!clave.equals("Contraseña Nueva")){
            if(vistaCNueva.isSelected()){
                clave_nueva.setEchoChar((char)0);
                clave_nueva.requestFocus();
            }else{
                clave_nueva.setEchoChar('•');
                clave_nueva.requestFocus();
            }
        }else{
        }        // TODO add your handling code here:
    }//GEN-LAST:event_vistaCNuevaActionPerformed

    private void vistaCAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vistaCAnteriorActionPerformed
        String clave = new String(clave_anterior.getPassword());
        if(!clave.equals("Contraseña Actual")){
            if(vistaCAnterior.isSelected()){
                clave_anterior.setEchoChar((char)0);
                clave_anterior.requestFocus();
            }else{
                clave_anterior.setEchoChar('•');
                clave_anterior.requestFocus();
            }
        }else{
        }
    }//GEN-LAST:event_vistaCAnteriorActionPerformed

    private void usuario_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuario_cActionPerformed
        if(!usuario_c.getText().isEmpty()){
            clave_anterior.requestFocus();
            clave_anterior.moveCaretPosition(0);
        }else{
            JOptionPane.showMessageDialog(null, "Debe de llenar el campo");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_usuario_cActionPerformed

    private void usuario_cKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuario_cKeyPressed

        if(usuario_c.getText().equals("Ingrese el  Usuario")){
            usuario_c.setText("");
            usuario_c.setForeground(Color.BLACK);
        }// TODO add your handling code here:
    }//GEN-LAST:event_usuario_cKeyPressed

    private void clave_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clave_anteriorActionPerformed
        funciones_login fl = new funciones_login();
        try {
            fl.cambio();
        } catch (SQLException ex) {
            Logger.getLogger(Cambio_Clave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_clave_anteriorActionPerformed

    private void clave_anteriorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clave_anteriorKeyPressed
        if(clave_anterior.getText().equals("Contraseña Actual")){
            clave_anterior.setText("");
            //clave_anterior.setForeground(Color.WHITE);
            clave_anterior.setEchoChar('•');
        }         // TODO add your handling code here:
    }//GEN-LAST:event_clave_anteriorKeyPressed

    private void clave_nuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clave_nuevaActionPerformed
        confirmar_clave.requestFocus();
        confirmar_clave.moveCaretPosition(0);         // TODO add your handling code here:
    }//GEN-LAST:event_clave_nuevaActionPerformed

    private void clave_nuevaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clave_nuevaKeyPressed
        if(clave_nueva.getText().equals("Contraseña Nueva")){
            clave_nueva.setText("");
            //clave_nueva.setForeground(Color.WHITE);
            clave_nueva.setEchoChar('•');
        }         // TODO add your handling code here:
    }//GEN-LAST:event_clave_nuevaKeyPressed

    private void confirmar_claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmar_claveActionPerformed

        if(!clave_anterior.getText().equals("Contraseña Actual")){
            String contra1 = new String(clave_nueva.getPassword());
            String contra2 = new String(confirmar_clave.getPassword());
            if(contra1.equals(contra2)){
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cambiar la contraseña?",
                    "Cambiar Contraseña", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    try {
                        modificar_Clave();
                        this.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(Cambio_Clave.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }else{
            JOptionPane.showMessageDialog(null, "La contraseña no coincide favor confirmar de nuevo ");
            confirmar_clave.setText("");
            confirmar_clave.requestFocus();
        }
        }else{
            JOptionPane.showMessageDialog(null, "Debe de ingresar la contraseña actual antes de proceder");
            clave_anterior.moveCaretPosition(0);
            clave_anterior.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_confirmar_claveActionPerformed

    private void confirmar_claveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmar_claveKeyPressed
        if(confirmar_clave.getText().equals("Confirmar Contraseña")){
            confirmar_clave.setText("");
            //confirmar_clave.setForeground(Color.WHITE);
            confirmar_clave.setEchoChar('•');
        }        // TODO add your handling code here:
    }//GEN-LAST:event_confirmar_claveKeyPressed

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
            java.util.logging.Logger.getLogger(Cambio_Clave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cambio_Clave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cambio_Clave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cambio_Clave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cambio_Clave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_cancelar;
    private javax.swing.JButton BTN_procesar;
    public static javax.swing.JPasswordField clave_anterior;
    public static javax.swing.JPasswordField clave_nueva;
    public static javax.swing.JPasswordField confirmar_clave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JTextField usuario_c;
    private javax.swing.JToggleButton vistaCAnterior;
    private javax.swing.JToggleButton vistaCConfirmar;
    private javax.swing.JToggleButton vistaCNueva;
    // End of variables declaration//GEN-END:variables
    conector cc = new conector();
 Connection cn = cc.conexion();
}
