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
public class Facturacion extends javax.swing.JFrame {

    /**
     * Creates new form Facturacion
     */
    public Facturacion() {
        initComponents();
                this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
    }

void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar facturacion?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            this.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_minimizar = new javax.swing.JButton();
        volverAtras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablafacturacion1 = new javax.swing.JTable();
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N

        tablafacturacion1.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        tablafacturacion1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "   Codigo", "Descripcion   ", "                             Precio", " Cantidad", "                               Itbis", "                              Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablafacturacion1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablafacturacion1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tablafacturacion1.setDragEnabled(true);
        tablafacturacion1.setSelectionBackground(new java.awt.Color(128, 230, 121));
        tablafacturacion1.setShowHorizontalLines(false);
        tablafacturacion1.setShowVerticalLines(false);
        tablafacturacion1.getTableHeader().setResizingAllowed(false);
        tablafacturacion1.getTableHeader().setReorderingAllowed(false);
        tablafacturacion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablafacturacion1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablafacturacion1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tablafacturacion1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 305, 949, 264));

        jLabel1.setBackground(java.awt.Color.gray);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Factura.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1365, 770));

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
        close();       // TODO add your handling code here:
    }//GEN-LAST:event_volverAtrasActionPerformed

    private void tablafacturacion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablafacturacion1MouseClicked
//        if(tablafacturacion1.isEnabled()){
//            if(evt.getClickCount() == 2){
//                if(evt.getButton()==MouseEvent.BUTTON1){
//
//                    String[] options = {"Borrar", "Modificar", "Cancelar"};
//                    //Integer[] options = {1, 3, 5, 7, 9, 11};
//                    //Double[] options = {3.141, 1.618};
//                    //Character[] options = {'a', 'b', 'c', 'd'};
//                    int x = JOptionPane.showOptionDialog(null, "Elija la opción correspondiente",
//                        "Servicio",
//                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
//
//                    if(x==0){
//                        actualizarTO();
//                        DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel(); //TableProducto es el nombre de mi tabla ;)
//                    dtm.removeRow(tablafacturacion1.getSelectedRow());
//                    sumar_total();
//                }
//                if(x==1){
//                    String cop1 = null;
//                    int fila = tablafacturacion1.getSelectedRow();
//                    if (fila>=0){
//                        cod_serv_fact1.setText(tablafacturacion1.getValueAt(fila,0).toString());
//                        cop1=tablafacturacion1.getValueAt(fila,0).toString();
//                        descrii_fact.setText(tablafacturacion1.getValueAt(fila,1).toString());
//                        //cant_contrata.setText(tablacont.getValueAt(fila,4).toString());
//
//                        for (int f = 0; f < tablaF_out.getRowCount(); f++)
//                        {
//                            {
//                                if (tablaF_out.getValueAt(f, 3).equals(cop1))
//                                {
//                                    precio_serv.setText(tablaF_out.getValueAt(f,0).toString());
//
//                                }
//                            }
//                        }
//
//                    }
//                    actualizarTO();
//                    DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel(); //TableProducto es el nombre de mi tabla ;)
//                dtm.removeRow(tablafacturacion1.getSelectedRow());
//                sumar_total();
//                cant_fact.requestFocus();
//            }
//            if(x==2){
//
//            }
//
//        }
//        }
//        }
    }//GEN-LAST:event_tablafacturacion1MouseClicked

    private void tablafacturacion1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablafacturacion1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tablafacturacion1MouseEntered

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
            java.util.logging.Logger.getLogger(Facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_minimizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tablafacturacion1;
    private javax.swing.JButton volverAtras;
    // End of variables declaration//GEN-END:variables
}
