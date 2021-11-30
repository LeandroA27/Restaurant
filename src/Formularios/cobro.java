/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.Conexion_factura_Modificar;
import Clases.Conexion_factura_guardar;
import Clases.Factura_1;
import Clases.generador_numerico;
import static Formularios.Cambio_Clave.clave_anterior;
import static Formularios.Cambio_Clave.clave_nueva;
import static Formularios.Cambio_Clave.confirmar_clave;
import static Formularios.Cambio_Clave.usuario_c;
import static Formularios.Facturacion.cant_fact;
import static Formularios.Facturacion.cod_cli_fact;
import static Formularios.Facturacion.cod_serv_fact;
import static Formularios.Facturacion.desc_serv;
import static Formularios.Facturacion.descrii_fact;
import static Formularios.Facturacion.descuento_rp;
import static Formularios.Facturacion.fecha_fact1;
import static Formularios.Facturacion.fecha_fact2;
import static Formularios.Facturacion.itbis_fact;
import static Formularios.Facturacion.itbis_ft;
import static Formularios.Facturacion.limpiar_fact;
import static Formularios.Facturacion.modificar_fact;
import static Formularios.Facturacion.ncf_anterior;
import static Formularios.Facturacion.ncf_disp;
import static Formularios.Facturacion.nom_cli_fact;
import static Formularios.Facturacion.num_fact_1;
import static Formularios.Facturacion.precio_serv;
import static Formularios.Facturacion.procesar_fact;
import static Formularios.Facturacion.reimprimir_fact;
import static Formularios.Facturacion.rnc_fact;
import static Formularios.Facturacion.sub_total;
import static Formularios.Facturacion.sub_total_ft;
import static Formularios.Facturacion.tablaF_out;
import static Formularios.Facturacion.tablafacturacion1;
import static Formularios.Facturacion.tipo_comp;
import static Formularios.Facturacion.tipo_fact1;
import static Formularios.Facturacion.tiponcf_anterior;
import static Formularios.Facturacion.total_devueltas;
import static Formularios.Facturacion.total_fact;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class cobro extends javax.swing.JFrame {
Fuentes tipofuente;
private String ubic = Clase_Variable_Publica.ubicacion; 
 private final String Logo ="/Imagenes/Logo_calidad.png";
    public cobro() {
        initComponents();
         try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        tipofuente = new Fuentes();
        Monto_pagar.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        Forma_pago.setFont(tipofuente.fuente(tipofuente.RIO,0,15));
        RD_label.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        Monto_pagado.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        devuelta.setFont(tipofuente.fuente(tipofuente.RIO,0,30));
        Monto_pagado.requestFocus();
    }
///////////////////////////////////////////////////////////////////////
    private void PrintReportToPrinter(JasperPrint jp) throws JRException {
    // TODO Auto-generated method stub
      PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
    // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
    printRequestAttributeSet.add(new Copies(1));

    PrinterName printerName = new PrinterName("POS80", null); //gets printer 

    PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
    printServiceAttributeSet.add(printerName);

     JRPrintServiceExporter exporter = new JRPrintServiceExporter();

    exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING.JASPER_PRINT, jp);
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
    exporter.exportReport();
}
    ////////////////////////////////////////////////////////////////////
void reimprimir1(){
       String itbis;
       float n1, n2;
        ArrayList lista1 = new ArrayList();
        //List <Factura>lista = new ArrayList<>();
        //lista.add("");
        
        
        for (int i = 0; i<tablafacturacion1.getRowCount(); i++){
             
            
            Factura_1 mortiza = new Factura_1 (tablafacturacion1.getValueAt(i,3).toString()+"",tablafacturacion1.getValueAt(i,2).toString()+"",tablafacturacion1.getValueAt(i,1).toString()+"",tablafacturacion1.getValueAt(i,5).toString()+"");
        lista1.add(mortiza);
        
    }
        JasperReport jr=null;
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00"); 
        Float cobrar,delivery;
        cobrar = Float.parseFloat(cobro.Monto_pagado.getText());
        delivery = Float.parseFloat(cobro.pago_delivery.getText());
        try{
            
            jr = (JasperReport) JRLoader.loadObjectFromFile("factura.jasper");
           
                HashMap parametro = new HashMap();
                //parametro.put("Nombre_ususario", nombre_usu_cot.getText());
                parametro.put("num_factura", num_fact_1.getText());
                parametro.put("fecha_ft", fecha_fact1.getText());
                parametro.put("ncf", ncf_disp.getText());
                parametro.put("Nombre_cli",nom_cli_fact.getText());
                parametro.put("Rnc_cli", rnc_fact.getText());
                parametro.put("sub_total", sub_total.getText());
                parametro.put("itbis_total", itbis_fact.getText());
                parametro.put("total_t", total_fact.getText());
                parametro.put("recibido", formateador.format(cobrar));
                parametro.put("devuelta", cobro.devuelta.getText());
                parametro.put("tipo_factura", tipo_comp.getSelectedItem());
                parametro.put("delivery", formateador.format(delivery));
                //parametro.put("descuento", cotzacion_m.precio_desc1.getText());
                parametro.put("Logo", this.getClass().getResourceAsStream(Logo));

                        
        
        JasperPrint jp = JasperFillManager.fillReport(jr, parametro,new JRBeanCollectionDataSource(lista1));
       //JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
//       PrintReportToPrinter(jp);
       limpiar1();
cod_serv_fact.requestFocus();
Clase_Variable_Publica.modificar_actrivo=0;
sumar_total();
desbloquear();
                    if(ubic.equals("Bar")){
                    Consultar_num_bar();
                    }else if (ubic.equals("Restaurante")){
                    Consultar_num_cot();    
                    }

        JasperViewer jv = new JasperViewer(jp,false);
        jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jv.setVisible(true);
        
        }catch(JRException ex){
            ex.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "ERROR\n" + ex.getMessage());
        }
    }
  void limpiar(){
    cod_serv_fact.setText("");
    descrii_fact.setText("");
    cant_fact.setText("");
      precio_serv.setText("");
    }
      void limpiar1(){
          Facturacion.nota_fact.setText("");
     descuento_rp.setText("- RD$ 0.00");
     cod_cli_fact.setText("00001");
     nom_cli_fact.setText("Cliente Casual");
    cod_serv_fact.setText("");
    descrii_fact.setText("");
    cant_fact.setText("");
    rnc_fact.setText("");
    precio_serv.setText("");
     precio_serv.setText("");
    ncf_anterior.setText("");
    tiponcf_anterior.setText("");
    desc_serv.setText("0.00");
    desc_serv.setEditable(false);
 fecha_fact1.setText(Clase_Variable_Publica.fechaDom);
 fecha_fact2.setText(Clase_Variable_Publica.fechaus);
 tipo_comp.setSelectedItem("Consumidor Final");
        tipo_fact1.setText("Contado");
 DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel(); 
dtm.setRowCount(0);
DefaultTableModel dtm1 = (DefaultTableModel) tablaF_out.getModel(); 
dtm1.setRowCount(0);
modificar_fact.setEnabled(false);
procesar_fact.setEnabled(false);
limpiar_fact.setEnabled(false);
reimprimir_fact.setEnabled(true);
//procesar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));   
    
    Clase_Variable_Publica.fact=0;
    }
    ///////////////////////////////////////////////////////
      public void desbloquear(){
        cod_cli_fact.setEditable(true);
     nom_cli_fact.setEditable(true);
    cod_serv_fact.setEditable(true);
    descrii_fact.setEditable(true);
    cant_fact.setEditable(true);
    rnc_fact.setEditable(true);
    precio_serv.setEditable(true);
        Facturacion.tipo_comp.setEnabled(true);
        rnc_fact.setEditable(true);
        Facturacion.buscar_rnc.setEnabled(true);
        tablafacturacion1.setEnabled(true);
        Facturacion.buscar_serv.setEnabled(true);
    }
    //////////////////////////////////////////////////////
      public void sumar_total(){
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        DefaultTableModel modelo3 = (DefaultTableModel) tablafacturacion1.getModel();
        String jtotal = "";
        String jtotal2 = "";
        String jtotal1 = "";
        double sub_total2 = 0, sub_total3 = 0;
        String jitbis = "";
        double itbis_total2 = (float) 0.0;
        double itbis_total3 = 0;
        double gran_total= 0;
        double gran_total2= 0;
        String total_venta ="";
        double total_precio1 = 0;
        
        double cantidad1 = 0.0;
        String cantidad2 = "";
        
        for (int i = 0; i< tablaF_out.getRowCount(); i++) {
                jtotal =(tablaF_out.getValueAt(i, 2).toString());
 
                sub_total2 = Double.parseDouble(jtotal);

                jtotal1 =(tablaF_out.getValueAt(i, 1).toString());
                itbis_total2 = Double.parseDouble(jtotal1);
                
                jtotal2 =(tablaF_out.getValueAt(i, 0).toString());
                gran_total = Double.parseDouble(jtotal2);
                
                cantidad2 =(tablafacturacion1.getValueAt(i, 4).toString());
                cantidad1 = Double.parseDouble(cantidad2);
                
                total_precio1 = total_precio1 + sub_total2;
                
                itbis_total3 =itbis_total3+(itbis_total2*cantidad1);
                
                gran_total2 = total_precio1 - itbis_total3;
        }
        sub_total.setText(formateador.format(gran_total2));
        itbis_fact.setText(formateador.format(itbis_total3));
        total_devueltas.setText(String.valueOf(total_precio1));
        total_fact.setText(formateador.format(total_precio1));
        sub_total_ft.setText(String.valueOf(gran_total2));
        itbis_ft.setText(String.valueOf(itbis_total3));
        desc_serv.setText("0.00");
         
    }
    /////////////////////////////////////
       public void Consultar_num_cot(){
        int l;
        int cont = 1;
        String num = "";
        String es = "";
        String SQL = "SELECT MAX(numero)AS numero FROM contador_facturas";

        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                es = rs.getString(1);
            }

            if (es == null) {
                Facturacion.num_fact_1.setText("R00001");

            } else {
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var = Integer.parseInt(es);

                generador_numerico p = new generador_numerico();
                p.Generar_rest(var);

                //JOptionPane.showMessageDialog(null, Clase_Variable_Publica.contador);
                Facturacion.num_fact_1.setText(p.serie_rest());

            }
        } catch (Exception e) {
        }
    }
    public void Consultar_num_bar(){
        int l;
        int cont = 1;
        String num = "";
        String es = "";
        String SQL = "SELECT MAX(numero)AS numero FROM contador_facturas_bar";

        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                es = rs.getString(1);
            }

            if (es == null) {
                Facturacion.num_fact_1.setText("B00001");

            } else {
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var = Integer.parseInt(es);

                generador_numerico p = new generador_numerico();
                p.Generar_Bar(var);

                //JOptionPane.showMessageDialog(null, Clase_Variable_Publica.contador);
                Facturacion.num_fact_1.setText(p.serie_bar());

            }
        } catch (Exception e) {
        }
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_pagar = new javax.swing.JLabel();
        devueltalbl = new javax.swing.JLabel();
        pago_delivery = new javax.swing.JLabel();
        Forma_pago = new javax.swing.JComboBox<>();
        devuelta = new javax.swing.JLabel();
        cancelar_cobro = new javax.swing.JButton();
        procesar_cobro = new javax.swing.JButton();
        RD_label = new javax.swing.JLabel();
        Monto_pagado = new javax.swing.JTextField();
        Monto_pagar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        devueltalbl.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Forma_pago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));
        Forma_pago.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Forma_pago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Forma_pagoItemStateChanged(evt);
            }
        });
        getContentPane().add(Forma_pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 130, 30));

        devuelta.setForeground(new java.awt.Color(198, 54, 53));
        getContentPane().add(devuelta, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 250, 260, 40));

        cancelar_cobro.setContentAreaFilled(false);
        cancelar_cobro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelar_cobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar_cobroActionPerformed(evt);
            }
        });
        getContentPane().add(cancelar_cobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 140, 40));

        procesar_cobro.setContentAreaFilled(false);
        procesar_cobro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        procesar_cobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesar_cobroActionPerformed(evt);
            }
        });
        getContentPane().add(procesar_cobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 140, 40));

        RD_label.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        RD_label.setForeground(new java.awt.Color(198, 54, 53));
        RD_label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RD_label.setText("RD$");
        RD_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RD_labelMouseClicked(evt);
            }
        });
        getContentPane().add(RD_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 180, -1, 40));

        Monto_pagado.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        Monto_pagado.setForeground(new java.awt.Color(198, 54, 53));
        Monto_pagado.setBorder(null);
        Monto_pagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Monto_pagadoActionPerformed(evt);
            }
        });
        Monto_pagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Monto_pagadoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Monto_pagadoKeyTyped(evt);
            }
        });
        getContentPane().add(Monto_pagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 181, 150, 40));

        Monto_pagar.setForeground(new java.awt.Color(198, 54, 53));
        getContentPane().add(Monto_pagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 110, 170, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 160, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 160, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 160, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cobro.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void procesar_cobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesar_cobroActionPerformed
if(!devuelta.getText().isEmpty()){
        if (Clase_Variable_Publica.fact == 0) {
            

                Conexion_factura_guardar cfg = new Conexion_factura_guardar();
                try {
                    cfg.conectar();
                    this.dispose();
                    } catch (SQLException ex) {
                    Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
        if (Clase_Variable_Publica.fact == 1) {
            
            if(Clase_Variable_Publica.modificar_actrivo == 1){
                        Conexion_factura_Modificar cfm = new Conexion_factura_Modificar();
                        try {
                            cfm.conectar();
                            this.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }else{
                
                reimprimir1();

               this.dispose();
            }
        
}
}else{
    JOptionPane.showMessageDialog(null, "Debes de darle a enter para que se genere el cobro");
    Monto_pagado.requestFocus();
}
       // TODO add your handling code here:
    }//GEN-LAST:event_procesar_cobroActionPerformed

    private void cancelar_cobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelar_cobroActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_cancelar_cobroActionPerformed

    private void RD_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RD_labelMouseClicked
        if (Clase_Variable_Publica.descuento_ft == 1) {
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para realizar descuento");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_RD_labelMouseClicked

    private void Monto_pagadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Monto_pagadoKeyPressed
       // TODO add your handling code here:
    }//GEN-LAST:event_Monto_pagadoKeyPressed

    private void Monto_pagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Monto_pagadoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
        if (Monto_pagado.getText().length() >= 11) {
            evt.consume();
        }           // TODO add your handling code here:
    }//GEN-LAST:event_Monto_pagadoKeyTyped

    private void Monto_pagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Monto_pagadoActionPerformed
float monto,dinero,total_p,delivery;
String devuelta_f;
monto = Float.parseFloat(lbl_pagar.getText());
dinero = Float.parseFloat(Monto_pagado.getText());

if (monto > dinero){
    JOptionPane.showMessageDialog(null, "El monto ingresado es menor al monto a pagar");
    Monto_pagado.setText("");
    Monto_pagado.requestFocus();
}else{
   DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00"); 
   total_p =  dinero - monto; 
   devuelta.setText(formateador.format(total_p));
   devueltalbl.setText(String.valueOf(total_p));
   procesar_cobro.requestFocus();
}



    }//GEN-LAST:event_Monto_pagadoActionPerformed

    private void Forma_pagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Forma_pagoItemStateChanged
// TODO add your handling code here:
    }//GEN-LAST:event_Forma_pagoItemStateChanged

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
            java.util.logging.Logger.getLogger(cobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cobro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cobro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Forma_pago;
    public static javax.swing.JTextField Monto_pagado;
    public static javax.swing.JLabel Monto_pagar;
    private javax.swing.JLabel RD_label;
    private javax.swing.JButton cancelar_cobro;
    public static javax.swing.JLabel devuelta;
    public static javax.swing.JLabel devueltalbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JLabel lbl_pagar;
    public static javax.swing.JLabel pago_delivery;
    private javax.swing.JButton procesar_cobro;
    // End of variables declaration//GEN-END:variables
    conector cc = new conector();
    Connection cn = cc.conexion();
}
