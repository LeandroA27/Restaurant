/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.consultas;
import Clases.render_tabla_reimprimir_factura;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
public class Reimprimir extends javax.swing.JFrame {

    Fuentes tipofuente;
    public Reimprimir() {
        initComponents();
        setCellRender(tabla_factura_cs);
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
        Buscar_facrt_in.setFont(tipofuente.fuente(tipofuente.RIO, 0, 18));
        try {
            cargar();
        } catch (SQLException ex) {
            Logger.getLogger(Reimprimir.class.getName()).log(Level.SEVERE, null, ex);
        }
        Clase_Variable_Publica.fact = 1;
        Buscar_facrt_in.requestFocus();
    }

void cargar() throws SQLException {
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        float Valor;
        DefaultTableModel modelo2 = (DefaultTableModel) tabla_factura_cs.getModel();
        modelo2.getDataVector().clear();

        String[] registros = new String[5];
        String sql = "SELECT num_factura,nom_cliente,total,date_format(fecha,'%d-%m-%Y') as fecha FROM encabezado_factura where ubicacion ='"+Clase_Variable_Publica.ubicacion+"' order by num_factura DESC";

        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Valor = Float.parseFloat(rs.getString("total"));
                registros[0] = rs.getString("num_factura");
                registros[1] = rs.getString("nom_cliente");
                registros[2] = formateador.format(Valor);
                registros[3] = rs.getString("fecha");

                modelo2.addRow(registros);
            }
            tabla_factura_cs.setModel(modelo2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    void filtro(String valor) throws SQLException {
        DefaultTableModel modelo2 = (DefaultTableModel) tabla_factura_cs.getModel();
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        modelo2.getDataVector().clear();
        float Valor;
        String[] registros = new String[6];
        String sql = "SELECT num_factura,nom_cliente,total,date_format(fecha,'%d-%m-%Y') as fecha FROM encabezado_factura where CONCAT (num_factura,'',nom_cliente,'',total,'',date_format(fecha,'%d-%m-%Y')) LIKE '%" + valor + "%'";
        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Valor = Float.parseFloat(rs.getString("total"));
                registros[0] = rs.getString("num_factura");
                registros[1] = rs.getString("nom_cliente");
                registros[2] = formateador.format(Valor);
                registros[3] = rs.getString("Tipo_Fact");
                registros[4] = rs.getString("fecha");
                modelo2.addRow(registros);
            }
            tabla_factura_cs.setModel(modelo2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new render_tabla_reimprimir_factura());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        num_fact_b = new javax.swing.JLabel();
        btn_seleccionar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        Buscar_facrt_in = new javax.swing.JTextField();
        BTN_buscarserv = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_factura_cs = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        num_fact_b.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        Buscar_facrt_in.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Buscar_facrt_in.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Buscar_facrt_in.setToolTipText("Debe dar enter para consultar");
        Buscar_facrt_in.setBorder(null);
        Buscar_facrt_in.setCaretColor(new java.awt.Color(255, 255, 255));
        Buscar_facrt_in.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Buscar_facrt_inMouseClicked(evt);
            }
        });
        Buscar_facrt_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_facrt_inActionPerformed(evt);
            }
        });
        Buscar_facrt_in.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Buscar_facrt_inKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Buscar_facrt_inKeyTyped(evt);
            }
        });
        getContentPane().add(Buscar_facrt_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 82, 208, 30));

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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tabla_factura_cs.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        tabla_factura_cs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "      Num. Factura", "Cliente", "                   Monto", "          Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_factura_cs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabla_factura_cs.setSelectionBackground(new java.awt.Color(198, 54, 53));
        tabla_factura_cs.setShowHorizontalLines(false);
        tabla_factura_cs.setShowVerticalLines(false);
        tabla_factura_cs.getTableHeader().setResizingAllowed(false);
        tabla_factura_cs.getTableHeader().setReorderingAllowed(false);
        tabla_factura_cs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_factura_csMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_factura_csMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_factura_cs);
        if (tabla_factura_cs.getColumnModel().getColumnCount() > 0) {
            tabla_factura_cs.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla_factura_cs.getColumnModel().getColumn(1).setPreferredWidth(300);
            tabla_factura_cs.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabla_factura_cs.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 120, 601, 356));

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

    private void btn_seleccionarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_seleccionarMouseMoved
        //btn_seleccionar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_seleccionarMouseMoved

    private void btn_seleccionarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_seleccionarMouseExited
        //btn_seleccionar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(76, 76, 76)));        // TODO add your handling code here:
    }//GEN-LAST:event_btn_seleccionarMouseExited

    private void btn_seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionarActionPerformed
        consultas cns = new consultas();
        try {
            cns.con_rimprimir_fact();
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Reimprimir.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void Buscar_facrt_inMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buscar_facrt_inMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Buscar_facrt_inMouseClicked

    private void Buscar_facrt_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_facrt_inActionPerformed
        try {
            filtro(Buscar_facrt_in.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Buscar_facrt_inActionPerformed

    private void Buscar_facrt_inKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Buscar_facrt_inKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_Buscar_facrt_inKeyReleased

    private void Buscar_facrt_inKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Buscar_facrt_inKeyTyped
        try {
            filtro(Buscar_facrt_in.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_Buscar_facrt_inKeyTyped

    private void BTN_buscarservActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_buscarservActionPerformed
        try {
            filtro(Buscar_facrt_in.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_BTN_buscarservActionPerformed

    private void tabla_factura_csMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_factura_csMouseClicked
        int fila = tabla_factura_cs.getSelectedRow();
        num_fact_b.setText(tabla_factura_cs.getValueAt(fila, 0).toString());
        Buscar_facrt_in.setText(tabla_factura_cs.getValueAt(fila, 1).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_factura_csMouseClicked

    private void tabla_factura_csMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_factura_csMousePressed
        int fila = tabla_factura_cs.getSelectedRow();
        num_fact_b.setText(tabla_factura_cs.getValueAt(fila, 0).toString());
        Buscar_facrt_in.setText(tabla_factura_cs.getValueAt(fila, 1).toString());
        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_factura_csMousePressed

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
            java.util.logging.Logger.getLogger(Reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reimprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reimprimir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_buscarserv;
    public static javax.swing.JTextField Buscar_facrt_in;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_seleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel num_fact_b;
    private javax.swing.JTable tabla_factura_cs;
    // End of variables declaration//GEN-END:variables
conector cc = new conector();
    Connection cn = cc.conexion();
}
