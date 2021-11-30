/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Formularios.Facturacion;
import static Formularios.Facturacion.*;
import Formularios.cobro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import restaurante.conector;



/**
 *
 * @author Thealex7
 */
public class Conexion_factura_Modificar {
    private Connection conexion = null;
     private final String Logo ="/Imagenes/Logo_calidad.png";
    private final String Titulo_cmp ="/Reportes/Factura_de_credito.png";
    private final String Titulo_sin ="/Reportes/Factura_.png";
    String capcod="",capcan="",anterior=""; 
     private String ubica = Clase_Variable_Publica.ubicacion; 
    
    public void conectar() throws SQLException {
        try {
            conecta();
            transaccion();
        } finally {
            cerrar();
        }
    }
    ////////////////////////////////////
    public void conecta() throws SQLException{
//           String direc = conexion_2.valor;
//    String contra = conexion_2.clave;
//    String usu = conexion_2.usu;
//    
//        String jdbc= direc;
        conexion = DriverManager.getConnection(conexion_2.cadena1,conexion_2.cadena2,conexion_2.cadena3);
        conexion.setAutoCommit(false);
    }
    public static String fechaactual(){
    Date fecha= new Date();
    SimpleDateFormat formatofecha= new SimpleDateFormat("YYYY-MM-dd");
    return formatofecha.format(fecha); 
}

public static String fechaactual1(){
    Date fecha= new Date();
    SimpleDateFormat formatofecha= new SimpleDateFormat("dd-MM-YYYY");
    return formatofecha.format(fecha);
    
  
}
    ////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
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
       ///////////////////////////////////////////////////////////////////////////////////////////
      
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

        JasperViewer jv = new JasperViewer(jp,false);
        jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jv.setVisible(true);
        
        }catch(JRException ex){
            ex.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "ERROR\n" + ex.getMessage());
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
    /////////////////////////////////////
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
    cuenta_abierta.setSelected(false);
    enviar_delivery.setSelected(false);
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
        para_llevar.setEnabled(true);
        para_comerAqui.setEnabled(true);
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
        String Vtotal = "";
        String valor = "";
        String itbis = "";
        float sub_total = 0.0f, sub_total3 = 0.0f;
        String jitbis = "";
        float itbis_total = 0.0f;
        float itbis_total3 = 0.0f;
        float valor_total = 0.0f;
        float gran_total = 0.0f;
        String total_venta = "";
        float subtotal_precio = 0.0f;

        float cantidad1 = 0.0f;
        String cantidad2 = "";

        for (int i = 0; i < tablaF_out.getRowCount(); i++) {
            Vtotal = (tablaF_out.getValueAt(i, 0).toString());
            sub_total = Float.parseFloat(Vtotal);

            itbis = (tablaF_out.getValueAt(i, 1).toString());
            itbis_total = Float.parseFloat(itbis);

            valor = (tablaF_out.getValueAt(i, 2).toString());
            valor_total = Float.parseFloat(valor);

            cantidad2 = (tablafacturacion1.getValueAt(i, 3).toString());
            cantidad1 = Float.parseFloat(cantidad2);

            subtotal_precio = subtotal_precio + (valor_total - itbis_total);

            itbis_total3 = itbis_total3 + itbis_total;

            gran_total = subtotal_precio + itbis_total3;
        }
        Facturacion.sub_total.setText(formateador.format(subtotal_precio));
        itbis_fact.setText(formateador.format(itbis_total3));
        total_devueltas.setText(String.valueOf(gran_total));
        total_fact.setText(formateador.format(gran_total));
        sub_total_ft.setText(String.valueOf(subtotal_precio));
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
    ////////////////////////////////////////////////
    private void transaccion() throws SQLException{
         String cod;
         String es="";
         String despacho = "";
for(int i=0;i<tablafacturacion1.getRowCount();i++)
{
String capcod="",capcan="";
capcod=tablafacturacion1.getValueAt(i, 0).toString();
capcan=tablafacturacion1.getValueAt(i, 3).toString();          
descontarstock(capcod, capcan);


}      

        PreparedStatement detalle_fact = null, encabezado_factura= null, contador_facturas= null, Borrar= null;
        try {
        for (int i = 0; i<tablafacturacion1.getRowCount(); i++){
        final String borrar ="Delete From detalle_fact where num_factura='"+Facturacion.num_fact_1.getText()+"'";   
        Borrar =conexion.prepareStatement(borrar);
        Borrar.executeUpdate();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////
              for (int i = 0; i<tablafacturacion1.getRowCount(); i++){
             cod = (tablafacturacion1.getValueAt(i,0).toString());  
            String[] registros = new String[5];
            final String codigo = "SELECT tipo FROM articulo where cod_art='"+cod+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(codigo);
            
            if(rs.next()){
                registros[0] = rs.getString("tipo");
                es=registros[0];
            }
             if(es.equals("Comidas")){
                despacho = "No";
            }else if(es.equals("Bebidas")){
             despacho = "Si";   
            }
              String tipo_despacho;
            if(para_llevar.isSelected()){
                tipo_despacho="Para llevar";
            }else{
               tipo_despacho="Comer aqui"; 
            }    
            
        final String detalle ="INSERT INTO detalle_fact(num_factura,cod_servicio,descr_serv,cantidad,precio,itbis,total,despacho,tipo_despacho)VALUES('"+num_fact_1.getText()+"','"+tablafacturacion1.getValueAt(i,0)+"','"+tablafacturacion1.getValueAt(i,1)+"','"+tablafacturacion1.getValueAt(i,3)+"','"+tablaF_out.getValueAt(i,0)+"','"+tablaF_out.getValueAt(i,1)+"','"+tablaF_out.getValueAt(i,2)+"','"+despacho+"','"+tipo_despacho+"')";
        
            detalle_fact =conexion.prepareStatement(detalle);
            detalle_fact.executeUpdate();
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////
        String tiempo = null;
        String comp = "";
        String comp1 = "";
        String estado = "No";
        String delivery,abierta;
        if(enviar_delivery.isSelected()){
            delivery="Si";
        }else{
           delivery="No"; 
        }
        
        if(cuenta_abierta.isSelected()){
            abierta="Si";
        }else{
           abierta="No"; 
        }
         if(tipo_comp.getSelectedItem().equals("Crédito Fiscal")){
               comp = Facturacion.ncf_disp.getText();
         }

        final String Encabezado ="UPDATE encabezado_factura SET ncf='"+comp+"',tipo_comprobante='"+Facturacion.tipo_comp.getSelectedItem()+"',Cod_cliente='"+cod_cli_fact.getText()+"',nom_cliente='"+nom_cli_fact.getText()+"',rnc_cl='"+rnc_fact.getText()+"',sub_total='"+sub_total_ft.getText()+"',descuento='"+Facturacion.desc_serv.getText()+"',itbis='"+itbis_ft.getText()+"',total='"+total_devueltas.getText()+"',abono='"+cobro.Monto_pagado.getText()+"',devuelta='"+cobro.devueltalbl.getText()+"',fecha='"+Facturacion.fecha_fact2.getText()+"',delivery='"+delivery+"',abierta='"+abierta+"' where num_factura='"+Facturacion.num_fact_1.getText()+"'";
            encabezado_factura =conexion.prepareStatement(Encabezado);
            encabezado_factura.executeUpdate();
            
        ///////////////////////////////////////////////////////////////////////////////////////////////////    
        String ncf = "";
        if(tipo_comp.getSelectedItem().equals("Crédito Fiscal")){
        String dp="No";
        PreparedStatement psU2 = cn.prepareStatement("UPDATE secuencia_ncf SET Disponible='"+dp+"'where ncf='"+comp+"'");
            psU2.executeUpdate();   
        }
       //////////////////////////////////////////////////////////////////////////////////////////////////////////////
       String ncf2 = "";
       String ncf3 = "";
       ncf2 = Facturacion.ncf_disp.getText();
       ncf3 = Facturacion.ncf_anterior.getText();

       if(!ncf2.equals(ncf3)){
       JOptionPane.showMessageDialog(null, "es diferente");
       comp1 = Facturacion.ncf_anterior.getText();
        if(Facturacion.tiponcf_anterior.getText().equals("Crédito Fiscal")){
        String dp1="Si";
        PreparedStatement psU3 = cn.prepareStatement("UPDATE secuencia_ncf SET Disponible='"+dp1+"'where ncf='"+comp1+"'");
            psU3.executeUpdate();   
        }
       }
            conexion.commit();
            
 
reimprimir1();
if(ubica.equals("Bar")){
Consultar_num_bar();
}else if(ubica.equals("Restaurante")){
Consultar_num_cot();    
}  
   

            //JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
        } catch (SQLException e) {
            conexion.rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar los datos");
        }finally{
            if(Borrar !=null){
                Borrar.close(); 
            }
            if(detalle_fact !=null){
                detalle_fact.close(); 
            }
            if(encabezado_factura !=null){
                encabezado_factura.close(); 
            }
            
        }
    
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
     void aumentarstock(String codi,String can, String ant)
    {
       int des = Integer.parseInt(can);
       int cal = Integer.parseInt(ant);
       int resultado = des - cal;
       String cap="";
       int desfinal;
       String[] registros = new String[3];
       String consul="SELECT existencia FROM articulo WHERE cod_art='"+codi+"'";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next())
            {
                registros[0] = rs.getString("existencia");
                
                
                cap = registros[0];
            }
            
            
        } catch (Exception e) {
        }
        int actual = Integer.parseInt(cap);
        if(actual < cal){
        desfinal=Integer.parseInt(cap)+ resultado;
        String modi="UPDATE articulo SET existencia='"+desfinal+"' WHERE cod_art = '"+codi+"'";
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }else if(cap.equals(cal)){
    
    }else if(actual > cal){
   
    descontarstock(capcod, capcan);
        
    }
    }
     
  void descontarstock(String codi,String can)
    {
       int des = Integer.parseInt(can);
       String cap="";
       int desfinal;
       String[] registros = new String[3];
       String consul="SELECT existencia FROM articulo WHERE cod_art='"+codi+"'";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next())
            {
                registros[0] = rs.getString("existencia");
                
                
                cap = registros[0];
            }
            
            
        } catch (Exception e) {
        }
        desfinal=Integer.parseInt(cap)-des;
        String modi="UPDATE articulo SET existencia='"+desfinal+"' WHERE cod_art = '"+codi+"'";
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void cant_compro(){
    String cont ="";
            String comp = "Si";
            String es="";
            int compar = 0;
            int cant = 0;
            int clic = Clase_Variable_Publica.cot;
            String[] registros = new String[2];
            String sql = "SELECT count(ncf) as NCF from secuencia_ncf where Disponible='"+comp+"'";
            try {
                java.sql.Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registros[0] = rs.getString("NCF");
                es=registros[0];
            }
            if(es.equals("0")){
               JOptionPane.showMessageDialog(null,"No hay números de comprobantes para la factura de crédito fiscal, ¡Favor Registrar Secuencia!");
                tipo_comp.setEnabled(false);
            }if(es.equals("5")){
                JOptionPane.showMessageDialog(null,"Solo hay 5 números de comprobantes disponibles para la factura de crédito fiscal");
               cargar();
            }
            if(es.equals("3")){
                JOptionPane.showMessageDialog(null,"Solo hay 3 números de somprobantes disponibles para la factura de crédito fiscal");
               cargar();
            }
            if(es.equals("2")){
                JOptionPane.showMessageDialog(null,"Solo hay 2 números de comprobantes disponibles para la factura de crédito fiscal");
               cargar();
            }
            if(es.equals("1")){
                JOptionPane.showMessageDialog(null,"Solo hay 1 número de comprobante disponible para la Factura de crédito fiscal");
               cargar();
            }else{
               cargar(); 
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void cant_compro2(){
    String cont ="";
            String comp = "Si";
            String es="";
            int compar = 0;
            int cant = 0;
            int clic = Clase_Variable_Publica.cot;
            String[] registros = new String[2];
            String sql = "SELECT count(ncf) as NCF from secuencia_ncf_gubern where Disponible='"+comp+"'";
            try {
                java.sql.Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registros[0] = rs.getString("NCF");
                es=registros[0];
            }
            if(es.equals("0")){
               JOptionPane.showMessageDialog(null,"No hay números de comprobantes para la factura de comprobante gubernamental, ¡Favor Registrar Secuencia!");
                tipo_comp.setEnabled(false);
                tipo_comp.setSelectedItem("Consumidor Final");
            }if(es.equals("5")){
                JOptionPane.showMessageDialog(null,"Solo hay 5 números de comprobantes disponibles para la factura de comprobante gubernamental");
               cargar1();
            }
            if(es.equals("3")){
                JOptionPane.showMessageDialog(null,"Solo hay 3 números de comprobantes disponibles para la factura de comprobante gubernamental");
               cargar1();
            }
            if(es.equals("2")){
                JOptionPane.showMessageDialog(null,"Solo hay 2 números de comprobantes disponibles para la factura de comprobante gubernamental");
               cargar1();
            }
            if(es.equals("1")){
                JOptionPane.showMessageDialog(null,"Solo hay 1 número de comprobantes disponibles para la factura de comprobante gubernamental");
               cargar1();
            }else{
               cargar1(); 
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
}
    public void cant_compro3(){
    String cont ="";
            String comp = "Si";
            String es="";
            int compar = 0;
            int cant = 0;
            int clic = Clase_Variable_Publica.cot;
            String[] registros = new String[2];
            String sql = "SELECT count(ncf) as NCF from secuencia_ncf_esecial where Disponible='"+comp+"'";
            try {
                java.sql.Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registros[0] = rs.getString("NCF");
                es=registros[0];
            }
            if(es.equals("0")){
               JOptionPane.showMessageDialog(null,"No hay números de comprobantes disponibles para las facturas de Regimen especial, ¡Favor Registrar las Secuencias!");
//                tipo_comp.setEnabled(false);
            }if(es.equals("5")){
                JOptionPane.showMessageDialog(null,"Solo hay 5 números de comprobantes disponibles para las facturas de Regimen especial");
               cargar2();
            }
            if(es.equals("3")){
                JOptionPane.showMessageDialog(null,"Solo hay 3 números de comprobantes disponibles para las facturas de Regimen especial");
               cargar2();
            }
            if(es.equals("2")){
                JOptionPane.showMessageDialog(null,"Solo hay 2 números de comprobantes disponibles para las facturas de Regimen especial");
               cargar2();
            }
            if(es.equals("1")){
                JOptionPane.showMessageDialog(null,"Solo hay 1 número de Comprobante disponible para las facturas de Regimen especial");
               cargar2();
            }else{
               cargar2(); 
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
    }
    //////////////////////////////////////////////////////////////////////////////
    public void cargar2() {
String cf = "Si";    
    String[] registros = new String[5];
    String sql = "(SELECT MIN(ncf) AS Ncf FROM secuencia_ncf_esecial WHERE Disponible='"+cf+"')";

try{
    java.sql.Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("Ncf");

        ncf_fep.setText(registros[0]);

    }   
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
    //////////////////////////////////////////////////////////////////////////////
    public void cargar1() {
String cf = "Si";    
String var ="";
    String[] registros = new String[5];
    String sql = "(SELECT MIN(numero) AS numero, ncf FROM secuencia_ncf_gubern WHERE Disponible='"+cf+"')";

try{
    java.sql.Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("ncf");

        ncf_fgb.setText(registros[0]);

    }   
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
     ///////////////////////////////////////////////////////////////////////  
    public void cargar() {
String cf = "Si";    
    String[] registros = new String[5];
    String sql = "(SELECT MIN(ncf) AS Ncf FROM secuencia_ncf WHERE Disponible='"+cf+"')";

try{
    java.sql.Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("Ncf");

        ncf_f.setText(registros[0]);

    }   
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
    /////////////////////////////////
    public void cerrar() throws SQLException{
        
    }
        
     conector cc = new conector();
    Connection cn = cc.conexion(); 
}
