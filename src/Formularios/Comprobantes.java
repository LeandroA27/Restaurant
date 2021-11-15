/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import com.sun.awt.AWTUtilities;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro Aquino
 */
public class Comprobantes extends javax.swing.JFrame {

    /**
     * Creates new form Comprobantes
     */
    public Comprobantes() {
        initComponents();
                this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
    }

void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar comprobantes?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            this.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_minimizar = new javax.swing.JButton();
        salir_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_comprobante = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        salir_btn.setBorder(null);
        salir_btn.setContentAreaFilled(false);
        salir_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salir_btn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                salir_btnMouseMoved(evt);
            }
        });
        salir_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salir_btnMouseExited(evt);
            }
        });
        salir_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salir_btnActionPerformed(evt);
            }
        });
        getContentPane().add(salir_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 57, 30, 29));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tabla_comprobante.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        tabla_comprobante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Comprobante", "No. Factura", "Cliente", "                                   Monto", "                 Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_comprobante.setSelectionBackground(new java.awt.Color(128, 230, 121));
        tabla_comprobante.setShowHorizontalLines(false);
        tabla_comprobante.setShowVerticalLines(false);
        tabla_comprobante.getTableHeader().setResizingAllowed(false);
        tabla_comprobante.getTableHeader().setReorderingAllowed(false);
        tabla_comprobante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_comprobanteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_comprobante);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 418, 950, 192));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/comprobante.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizarActionPerformed
        this.setState(Menu_Principal.ICONIFIED);          // TODO add your handling code here:
    }//GEN-LAST:event_btn_minimizarActionPerformed

    private void salir_btnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salir_btnMouseMoved
        //salir_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));         // TODO add your handling code here:
    }//GEN-LAST:event_salir_btnMouseMoved

    private void salir_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salir_btnMouseExited
        //salir_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(76, 76, 76)));          // TODO add your handling code here:
    }//GEN-LAST:event_salir_btnMouseExited

    private void salir_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir_btnActionPerformed
        close();       // TODO add your handling code here:
    }//GEN-LAST:event_salir_btnActionPerformed

    private void tabla_comprobanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_comprobanteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_comprobanteMouseClicked

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
            java.util.logging.Logger.getLogger(Comprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comprobantes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_minimizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton salir_btn;
    public static javax.swing.JTable tabla_comprobante;
    // End of variables declaration//GEN-END:variables
}
