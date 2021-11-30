/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.funciones_login;
import Clases.funciones_usuarios;
import com.sun.awt.AWTUtilities;
import java.awt.Cursor;
import static java.awt.Frame.WAIT_CURSOR;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Login extends javax.swing.JFrame {

    int x,y;
    Fuentes tipofuente;
    int visible = 0;
    public Login() {
        initComponents();
                Clave.setEchoChar('•'); 
        //setOpacity((float)0.9);
        AWTUtilities.setWindowOpaque(this, false);
        setLocationRelativeTo(this);
         try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        if(!usuario_l.getText().isEmpty() && !Clave.getText().isEmpty()){
        negro_l.setCursor(new Cursor(WAIT_CURSOR));  
        }
                 tipofuente = new Fuentes();

        usuario_l.setFont(tipofuente.fuente(tipofuente.RIO, 0, 20));
        Clave.setFont(tipofuente.fuente(tipofuente.RIO, 0, 20));
    }
    ///////////////////////////////////////////////////
    void acceder (String usuario, String pass){
      String cap = "";
      String nombre = "";
      String sql = "SELECT * FROM usuario WHERE usuario='"+usuario+"' && contra_usu='"+pass+"'";
      try{
          Statement st = cn.createStatement();
          ResultSet rs = st.executeQuery(sql);
          while(rs.next()){
              cap = rs.getString("estatus");
              nombre = rs.getString("nombre_usu");
          }
          
          
          if (cap.equals("Activo")){
              this.setVisible(false);
              //JOptionPane.showMessageDialog(null,"Bienvenido");
              
              //back obj = new back();
              //obj.setVisible(true);
              this.negro_l.setCursor(new Cursor(Cursor.WAIT_CURSOR));
              Menu_Principal ingreso = new Menu_Principal();
              ingreso.setVisible(true);
              funciones_usuarios fu = new funciones_usuarios();
            try {
                fu.conectar_p();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
              Menu_Principal.nombre_usu_cli.setText(nombre);
              
              //ingreso.pack();
              
              
                      
              
          }
          if(cap.equals("Inactivo"))
          {   
              JOptionPane.showMessageDialog(null,"Este usuario está inactivo favor intente de nuevo");
              Clave.setText("");
              usuario_l.setText("");
              usuario_l.requestFocus();
          }
          
        if ((!cap.equals("Activo")) && (!cap.equals("Inactivo"))){
            JOptionPane.showMessageDialog(this,"El usuario y la contraseña no coinciden");
            Clave.setText("");
        }  
          
      }catch(SQLException ex){
          
      }  
    }
/////////////////////////////////////////////////////
    void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        boton_vista = new javax.swing.JToggleButton();
        Clave = new javax.swing.JPasswordField();
        usuario_l = new javax.swing.JTextField();
        negro_l = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        aceptar.setBorder(null);
        aceptar.setContentAreaFilled(false);
        aceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aceptar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                aceptarMouseMoved(evt);
            }
        });
        aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aceptarMouseExited(evt);
            }
        });
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });
        getContentPane().add(aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 395, 130, 35));

        cancelar.setBorder(null);
        cancelar.setContentAreaFilled(false);
        cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cancelarMouseMoved(evt);
            }
        });
        cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelarMouseExited(evt);
            }
        });
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        getContentPane().add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 395, 140, 35));

        boton_vista.setBorder(null);
        boton_vista.setContentAreaFilled(false);
        boton_vista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton_vista.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boton_vistaItemStateChanged(evt);
            }
        });
        boton_vista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_vistaActionPerformed(evt);
            }
        });
        getContentPane().add(boton_vista, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 300, 35, 25));

        Clave.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Clave.setAlignmentX(0.0F);
        Clave.setAlignmentY(0.0F);
        Clave.setAutoscrolls(false);
        Clave.setBorder(null);
        Clave.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        Clave.setDropMode(javax.swing.DropMode.INSERT);
        Clave.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        Clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClaveActionPerformed(evt);
            }
        });
        Clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ClaveKeyPressed(evt);
            }
        });
        getContentPane().add(Clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 303, 200, 25));

        usuario_l.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        usuario_l.setBorder(null);
        usuario_l.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        usuario_l.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        usuario_l.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuario_lMouseClicked(evt);
            }
        });
        usuario_l.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuario_lActionPerformed(evt);
            }
        });
        usuario_l.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuario_lKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuario_lKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuario_lKeyTyped(evt);
            }
        });
        getContentPane().add(usuario_l, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 219, 230, 25));

        negro_l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login.png"))); // NOI18N
        getContentPane().add(negro_l, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_aceptarMouseMoved

    private void aceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_aceptarMouseExited

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        if(Clave.getPassword().equals("")){
            JOptionPane.showMessageDialog(null, "Debe ingresar la contraseña");
            return;
        }
        else{
            this.negro_l.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            this.usuario_l.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            this.Clave.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            this.aceptar.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            this.cancelar.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            funciones_login fl = new funciones_login();
            try {
                fl.conectar();
                if(Clase_Variable_Publica.valor==1){
                    this.dispose();
                }else{}
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 

    }//GEN-LAST:event_aceptarActionPerformed

    private void cancelarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarMouseMoved

    private void cancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarMouseExited

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        close();        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed

    private void boton_vistaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boton_vistaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_vistaItemStateChanged

    private void boton_vistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_vistaActionPerformed
        if(boton_vista.isSelected()){
            Clave.setEchoChar((char)0);
            Clave.requestFocus();
        }else{
            Clave.setEchoChar('•');
            Clave.requestFocus();
        }
    }//GEN-LAST:event_boton_vistaActionPerformed

    private void ClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClaveActionPerformed

        if(Clave.getPassword().equals("")){
            JOptionPane.showMessageDialog(null, "Debe ingresar la contraseña");
            return;
        }
        else{
            this.negro_l.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            this.usuario_l.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            this.Clave.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            this.aceptar.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            this.cancelar.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            funciones_login fl = new funciones_login();
            try {
                fl.conectar();
                if(Clase_Variable_Publica.valor==1){
                    this.dispose();
                }else{}
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_ClaveActionPerformed

    private void ClaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClaveKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClaveKeyPressed

    private void usuario_lMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuario_lMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_usuario_lMouseClicked

    private void usuario_lActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuario_lActionPerformed
        Clave.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_usuario_lActionPerformed

    private void usuario_lKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuario_lKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Clave.requestFocus();
        }                 // TODO add your handling code here:
    }//GEN-LAST:event_usuario_lKeyPressed

    private void usuario_lKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuario_lKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_usuario_lKeyReleased

    private void usuario_lKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuario_lKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_usuario_lKeyTyped

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPasswordField Clave;
    public static javax.swing.JButton aceptar;
    private javax.swing.JToggleButton boton_vista;
    public static javax.swing.JButton cancelar;
    public static javax.swing.JLabel negro_l;
    public static javax.swing.JTextField usuario_l;
    // End of variables declaration//GEN-END:variables
conector cc = new conector();
    Connection cn = cc.conexion();

}
