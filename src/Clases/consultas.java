/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Formularios.Facturacion;
import static Formularios.Facturacion.*;
import Formularios.Reimprimir;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import restaurante.conector;

/**
 *
 * @author Thealex7
 */
public class consultas {
//    String direc = conexion_2.valor;
//    String contra = conexion_2.clave;
//    String usu = conexion_2.usu;
    
    private Connection conexion = null;
    public void conecta() throws SQLException{

        //String jdbc= direc;
        conexion = DriverManager.getConnection(conexion_2.cadena1,conexion_2.cadena2,conexion_2.cadena3);
        conexion.setAutoCommit(false);
    }
    public void cerrar() throws SQLException{
        
    }

   ///////////////////////////////////////////////CONSULTA SERVICIOS///////////////////////////////////////////////////////////////////////////////////////////// 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
///////////////////////////////////////////////////////////REIMPRIMIR FACTURA//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public void con_rimprimir_fact() throws SQLException {
        try {
            conecta();
            tr_fact();
            //v.dispose();
        } finally {
            cerrar();
        }
    }
    public void tr_fact(){
        PreparedStatement cons = null;
        String cont ="";
        String comp = "";
        String es="",ubi = Clase_Variable_Publica.ubicacion;
        int compar = 0;
        int cant = 0;
        
        if(ubi.equals("Bar")){
        String[] registros = new String[2];
        final String detalle  = "SELECT secuencia FROM contador_factura_bar where secuencia='"+Reimprimir.num_fact_b.getText()+"'";
        try {
            cons = conexion.prepareStatement(detalle);
         ResultSet rs = cons.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("secuencia");
                es=registros[0];
            }
            if(es.isEmpty()){
                JOptionPane.showMessageDialog(null,"Esta factura no ha sido creada");

            }else{

                    cargar_cot_ft();
                }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);

        }
        }else{
          String[] registros = new String[2];
        final String detalle  = "SELECT secuencia FROM contador_facturas where secuencia='"+Reimprimir.num_fact_b.getText()+"'";
        try {
            cons = conexion.prepareStatement(detalle);
         ResultSet rs = cons.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("secuencia");
                es=registros[0];
            }
            if(es.isEmpty()){
                JOptionPane.showMessageDialog(null,"Esta factura no ha sido creada");

            }else{

                    cargar_cot_ft();
                }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);

        }  
        }
    }
    public void cargar_cot_ft(){
    PreparedStatement cons = null;
        limpiar_ft();
       String es="",delivery="",abierta="";
       String tiempo = null;
       double sub =0;
       double itb =0;
       double total =0;
       double desc = 0;
   String[] registros = new String[30];
        final String detalle  = "SELECT num_factura,ncf,tipo_comprobante,Cod_cliente,nom_cliente,rnc_cl,descuento,fecha,date_format(fecha, '%d / %m / %Y') as fecha1,"
                + "abono,devuelta,sub_total,itbis,total,estado,delivery,abierta FROM encabezado_factura where num_factura='"+Reimprimir.num_fact_b.getText()+"'";
        try {
            DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
            DecimalFormat formateador1 = new DecimalFormat("- RD$ #,##0.00");
            cons = conexion.prepareStatement(detalle);
         ResultSet rs = cons.executeQuery();
            while (rs.next()){
                
                registros[0] = rs.getString("num_factura");
                registros[1] = rs.getString("ncf");
                registros[2] = rs.getString("tipo_comprobante");
                registros[3] = rs.getString("Cod_cliente");
                registros[4] = rs.getString("nom_cliente");
                registros[5] = rs.getString("rnc_cl");
                registros[6] = rs.getString("descuento");
                registros[7] = rs.getString("fecha");
                registros[8] = rs.getString("fecha1");
                registros[9] = rs.getString("abono");
                registros[10] = rs.getString("devuelta");
                registros[11] = rs.getString("sub_total");
                registros[12] = rs.getString("itbis");
                registros[13] = rs.getString("total");
                registros[14] = rs.getString("estado");
                registros[15] = rs.getString("delivery");
                registros[16] = rs.getString("abierta");
                
                 sub = Double.parseDouble(registros[11]);
                itb = Double.parseDouble(registros[12]);
                total = Double.parseDouble(registros[13]);
                desc = Double.parseDouble(registros[6]);

                Facturacion.num_fact_1.setText(registros[0]);
                Facturacion.ncf_disp.setText(registros[1]);
                es = registros[2];
                if(es.equals("Consumidor Final")){
                    tipo_comp.setSelectedItem("Consumidor Final");
                }else{
                tipo_comp.setSelectedItem("Crédito Fiscal");    
                }
                 delivery = registros[15];
                if(delivery.equals("Si")){
                    enviar_delivery.setSelected(true);
                }else{
                enviar_delivery.setSelected(false);    
                }
                
                 abierta = registros[16];
                if(abierta.equals("Si")){
                cuenta_abierta.setSelected(true);
                }else{
                cuenta_abierta.setSelected(false);    
                }
                
                Facturacion.cod_cli_fact.setText(registros[3]);
                Facturacion.nom_cli_fact.setText(registros[4]);
                Facturacion.rnc_fact.setText(registros[5]);
                Facturacion.desc_serv.setText(registros[6]);
                Facturacion.fecha_fact2.setText(registros[7]);
                Facturacion.fecha_fact1.setText(registros[8]);
                Facturacion.sub_total_ft.setText(registros[11]);
                Facturacion.itbis_ft.setText(registros[12]);
                Facturacion.total_devueltas.setText(registros[13]);

                }
                    
               sub_total.setText(formateador.format(sub));
        itbis_fact.setText(formateador.format(itb));
        total_fact.setText(formateador.format(total));
            Facturacion.descuento_rp.setText(formateador1.format(desc));
            Facturacion.descuento_front.setText(formateador1.format(desc));
            cargar_fact();
            //sumar_total_ft();
            Clase_Variable_Publica.fact=1;
            Clase_Variable_Publica.modificar_actrivo=0;
            
            bloquear();
procesar_fact.setEnabled(true);
limpiar_fact.setEnabled(true);
reimprimir_fact.setEnabled(false);
//cotizacion_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
//contrata_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
//reimprimir_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
//      String comparacion = (String) tipo_comp.getSelectedItem();
//        
//if(comparacion.equals("Régimen Especial")){
//ncf_disp.setText(ncf_reult.getText());      
//}
//if(comparacion.equals("Crédito Fiscal")){
//ncf_disp.setText(ncf_reult.getText());      
//}
//if(comparacion.equals("Gubernamental")){
//ncf_disp.setText(ncf_reult.getText());      
//}
//if(comparacion.equals("Consumidor Final")){
//ncf_disp.setText("");    
//}                            
                    
        }catch(SQLException ex){
              JOptionPane.showMessageDialog(null, ex);
                    
        }
   }
    void limpiar_ft(){
cod_cli_fact.setText("");
     nom_cli_fact.setText("");
    cod_serv_fact.setText("");
    descrii_fact.setText("");
    cant_fact.setText("");
    rnc_fact.setText("");
    precio_serv.setText("");
    desc_serv.setText("0.0");
    DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel(); 
dtm.setRowCount(0);

   }
    public void bloquear(){
        cod_cli_fact.setEditable(false);
     nom_cli_fact.setEditable(false);
    cod_serv_fact.setEditable(false);
    descrii_fact.setEditable(false);
    cant_fact.setEditable(false);
    rnc_fact.setEditable(false);
    precio_serv.setEditable(false);
    Facturacion.desc_serv.setEditable(false);
        Facturacion.tipo_comp.setEnabled(false);
    
        rnc_fact.setEditable(false);
        Facturacion.buscar_rnc.setEnabled(false);
        tablafacturacion1.setEnabled(false);
        Facturacion.buscar_serv.setEnabled(false);
//        Facturacion.modificar_fact.setEnabled(true);
    }
    public void cargar_fact(){
        PreparedStatement cons = null;
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00"); 
        DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel();
        dtm.setRowCount(0);
        DefaultTableModel dtm1 = (DefaultTableModel) tablaF_out.getModel();
        dtm1.setRowCount(0);
       DefaultTableModel modelo3 =(DefaultTableModel) tablafacturacion1.getModel();
       DefaultTableModel modelo2 =(DefaultTableModel) tablaF_out.getModel();
   float precio,itbis,total = 0, itebisT = 0;
   int cantidad;
   String tipo_despacho="";
    String[] registros = new String[30];
    String[] registros1 = new String[30];
    final String detalle = "SELECT cod_servicio,descr_serv,precio,cantidad,itbis,total,tipo_despacho FROM detalle_fact where num_factura='"+Reimprimir.num_fact_b.getText()+"'";
    
    try{
        cons = conexion.prepareStatement(detalle);
         ResultSet rs = cons.executeQuery();
    
    while (rs.next()) {
        
        precio = Float.parseFloat(rs.getString("precio"));
        itbis = Float.parseFloat(rs.getString("itbis"));
        total = Float.parseFloat(rs.getString("total"));
        cantidad = Integer.parseInt(rs.getString("cantidad"));

        registros[0] = rs.getString("cod_servicio");
        registros[1] = rs.getString("descr_serv");
        registros[2] =formateador.format(precio); 
        registros[3]= rs.getString("cantidad"); 
        registros[4] = formateador.format(itbis); 
        registros[5] = formateador.format(total); 
        registros[6]= rs.getString("tipo_despacho"); 
        
        registros1[0]=  rs.getString("precio");
        registros1[1]=  String.valueOf(itbis);
        registros1[2]=  String.valueOf(total);
        registros1[3] = rs.getString("cod_servicio");
        
        tipo_despacho = registros[6];
        
        modelo3.addRow(registros);
        modelo2.addRow(registros1);  
    }
    
            if(tipo_despacho.equals("Para llevar")){
         para_llevar.setSelected(true);
         para_comerAqui.setSelected(false);
        }else if (tipo_despacho.equals("Comer aqui")){
         para_comerAqui.setSelected(true);
         para_llevar.setSelected(false);
        }

 
    tablafacturacion1.setModel(modelo3);
    tablaF_out.setModel(modelo2);
modificar_fact.setEnabled(true);
procesar_fact.setEnabled(true);
limpiar_fact.setEnabled(true);
reimprimir_fact.setEnabled(true);
para_llevar.setEnabled(false);
para_comerAqui.setEnabled(false);
//reimprimir_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
    }catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
    
   
}
 void sumarstock(String codi,String can)
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
        desfinal=Integer.parseInt(cap)+des;
        String modi="UPDATE articulo SET existencia='"+desfinal+"' WHERE cod_art = '"+codi+"'";
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }   
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////CONSULTA DE COTIZACION//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////CONSULTA MODULO DE CLIENTE//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
conector cc = new conector();
    Connection cn = cc.conexion();
}
