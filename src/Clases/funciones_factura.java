/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Formularios.Facturacion;
import static Formularios.Facturacion.cant_fact;
import static Formularios.Facturacion.desc_serv;
import static Formularios.Facturacion.descrii_fact;
import static Formularios.Facturacion.ncf_f;
import static Formularios.Facturacion.precio_serv;
import static Formularios.Facturacion.tipo_comp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static Formularios.Facturacion.cod_serv_fact;

/**
 *
 * @author Thealex7
 */
public class funciones_factura {
    private Connection conexion = null;
    ///////////////////////////////////////////CONEXIONES////////////////////////////////////////////  
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
      public void conectar_cargar_rnc() throws SQLException {
        try {
            conecta();
            Consultar_num_cot();
        } finally {
            cerrar();
        }
    }
     
    public void Consultar_num_cot(){
    PreparedStatement contador = null;
        int l;
        int cont=1;
        String num="";
        String es="";     
        try {
            final String detalle = "SELECT MAX(numero)AS numero FROM contador_facturas";
            contador = conexion.prepareStatement(detalle);
            ResultSet rs = contador.executeQuery();
            if(rs.next()){
                es=rs.getString(1);
            }
            
            if(es==null){
                Facturacion.num_fact_1.setText("00001");
                Clase_Variable_Publica.numeroFactura="00001";
                
            }else{
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var=Integer.parseInt(es);
                
            generador_numerico p = new generador_numerico();
            p.Generar_cliente(var);
            
                Facturacion.num_fact_1.setText(p.serie_cli());
                Clase_Variable_Publica.numeroFactura=p.serie_cli();
                
            }
        } catch (Exception e) {
        }
        
    }
    public void cerrar() throws SQLException{
        
    }
  ////////////////////////////////////////////////////////////////////////////////////////cargar cliente
      public void conectar_cargar_cliente() throws SQLException {
        try {
            conecta();
            confirmar();
        } finally {
            cerrar();
        }
      }
      public void confirmar(){
           PreparedStatement cons = null;
    String cont ="";
            String comp = "";
            String es="";
            int compar = 0;
            int cant = 0;
            comp = Facturacion.cod_cli_fact.getText();
            String[] registros = new String[2];
            try {
                final String detalle = "SELECT estado_cl FROM cliente where codigo_cl='"+Facturacion.cod_cli_fact.getText()+"'";
            cons = conexion.prepareStatement(detalle);
            ResultSet rs = cons.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("estado_cl");
                es=registros[0];
            }
            if(es.equals("Inactivo")){
                JOptionPane.showMessageDialog(null,"Este cliente esta inactivo");
                
            }else{
               cargar_cliente();
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }    
    }
       public void cargar_cliente(){
           PreparedStatement consul = null;
           if(Facturacion.cod_cli_fact.getText().isEmpty()){
      JOptionPane.showMessageDialog(null, "Debe ingresar el codigo del cliente");
        }else{
            String cont ="";
            String comp = "";
            String es="";
            int compar = 0;
            int cant = 0;
            comp = Facturacion.cod_cli_fact.getText();
            String[] registros = new String[2];
            try {
            final String detalle = "SELECT codigo_cl FROM cliente where codigo_cl='"+Facturacion.cod_cli_fact.getText()+"'";
            consul = conexion.prepareStatement(detalle);
            ResultSet rs = consul.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("codigo_cl");
                es=registros[0];
            }
            if(es.isEmpty()){
               JOptionPane.showMessageDialog(null,"Este cliente no existe");
                
            }else{
               cargar_cli();
                   Facturacion.cod_serv_fact.requestFocus();
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
           }
       }
       
       void cargar_cli() throws SQLException {
         PreparedStatement cargar = null;  
        String[] registros = new String[12];
        try {
           final String detalle = "SELECT codigo_cl, nombre_cl, rnc_cl FROM cliente where codigo_cl='"+Facturacion.cod_cli_fact.getText()+"'";
            cargar = conexion.prepareStatement(detalle);
            ResultSet rs = cargar.executeQuery();
            
            while (rs.next()){
                registros[0] = rs.getString("codigo_cl");
                registros[1] = rs.getString("nombre_cl");
                registros[2] = rs.getString("rnc_cl");
               
                
                Facturacion.cod_cli_fact.setText( registros[0]);
                Facturacion.nom_cli_fact.setText(registros[1]);
                Facturacion.rnc_fact.setText(registros[2]);
                
                }         
                    
        }catch(SQLException ex){
              JOptionPane.showMessageDialog(null, ex);
                    
        } 
    }
    ///////////////////////////////////////////////////////////////////////////////////consultar rnc
    public void cant_compro(){
       PreparedStatement cargar = null;  
    String cont ="";
            String comp = "Si";
            String es="";
            int compar = 0;
            int cant = 0;
            int clic = Clase_Variable_Publica.cot;
            String[] registros = new String[2];
            try {
               final String detalle = "SELECT count(ncf) as NCF from secuencia_ncf where Disponible='"+comp+"'";
            cargar = conexion.prepareStatement(detalle);
            ResultSet rs = cargar.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("NCF");
                es=registros[0];
            }
            if(es.equals("0")){
               JOptionPane.showMessageDialog(null,"No hay números de comprobantes para la factura de crédito fiscal, ¡Favor Registrar Secuencia!");
                tipo_comp.setEnabled(false);
                tipo_comp.setSelectedItem("Consumidor Final");
            }if(es.equals("5")){
                JOptionPane.showMessageDialog(null,"Solo hay 5 números de comprobantes disponibles para la factura de crédito fiscal");
               cargar();
            }
            if(es.equals("3")){
                JOptionPane.showMessageDialog(null,"Solo hay 3 números de comprobantes disponibles para la factura de crédito fiscal");
               cargar();
            }
            if(es.equals("2")){
                JOptionPane.showMessageDialog(null,"Solo hay 2 números de comprobantes disponibles para la factura de crédito fiscal");
               cargar();
            }
            if(es.equals("1")){
                JOptionPane.showMessageDialog(null,"Solo hay 1 número de comprobante disponible para la factura de crédito fiscal");
               cargar();
            }else{
               cargar(); 
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
}
    
public void cargar() {
 PreparedStatement cargar = null;      
String cf = "Si";    
String var ="";
    String[] registros = new String[5];
try{
    final String detalle = "(SELECT MIN(numero) AS numero, ncf FROM secuencia_ncf WHERE Disponible='"+cf+"')";
    cargar = conexion.prepareStatement(detalle);
    ResultSet rs = cargar.executeQuery();
    
    while (rs.next()) {
        registros[0] = rs.getString("ncf");

        ncf_f.setText(registros[0]);

    }   
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
//////////////////////////////////////////////////////////////////////////////////////////////////
public void cant_compro1(){
       PreparedStatement cargar = null;  
    String cont ="";
            String comp = "Si";
            String es="";
            int compar = 0;
            int cant = 0;
            int clic = Clase_Variable_Publica.cot;
            String[] registros = new String[2];
            try {
               final String detalle = "SELECT count(ncf) as NCF from secuencia_ncf_gubern where Disponible='"+comp+"'";
            cargar = conexion.prepareStatement(detalle);
            ResultSet rs = cargar.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("NCF");
                es=registros[0];
            }
            if(es.equals("0")){
               JOptionPane.showMessageDialog(null,"No hay números de comprobantes para la factura Gubernamental, ¡Favor Registrar Secuencia!");
                tipo_comp.setEnabled(false);
                tipo_comp.setSelectedItem("Consumidor Final");
            }if(es.equals("5")){
                JOptionPane.showMessageDialog(null,"Solo hay 5 números de comprobantes disponibles para la factura Gubernamental");
               cargar1();
            }
            if(es.equals("3")){
                JOptionPane.showMessageDialog(null,"Solo hay 3 números de comprobantes disponibles para la factura Gubernamental");
               cargar1();
            }
            if(es.equals("2")){
                JOptionPane.showMessageDialog(null,"Solo hay 2 números de comprobantes disponibles para la factura Gubernamental");
               cargar1();
            }
            if(es.equals("1")){
                JOptionPane.showMessageDialog(null,"Solo hay 1 número de comprobante disponible para la factura Gubernamental");
               cargar1();
            }else{
               cargar1(); 
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
}
    
public void cargar1() {
 PreparedStatement cargar = null;      
String cf = "Si";    
String var ="";
    String[] registros = new String[5];
try{
    final String detalle = "(SELECT MIN(numero) AS numero, ncf FROM secuencia_ncf_gubern WHERE Disponible='"+cf+"')";
    cargar = conexion.prepareStatement(detalle);
    ResultSet rs = cargar.executeQuery();
    
    while (rs.next()) {
        registros[0] = rs.getString("ncf");

        ncf_f.setText(registros[0]);

    }   
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
////////////////////////////////////////////////////////////////////////////////////////////////////
public void cant_compro2(){
       PreparedStatement cargar = null;  
    String cont ="";
            String comp = "Si";
            String es="";
            int compar = 0;
            int cant = 0;
            int clic = Clase_Variable_Publica.cot;
            String[] registros = new String[2];
            try {
               final String detalle = "SELECT count(ncf) as NCF from secuencia_ncf_esecial where Disponible='"+comp+"'";
            cargar = conexion.prepareStatement(detalle);
            ResultSet rs = cargar.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("NCF");
                es=registros[0];
            }
            if(es.equals("0")){
               JOptionPane.showMessageDialog(null,"No hay números de comprobantes para la factura de Regimen Especial, ¡Favor Registrar Secuencia!");
                tipo_comp.setEnabled(false);
                tipo_comp.setSelectedItem("Consumidor Final");
            }if(es.equals("5")){
                JOptionPane.showMessageDialog(null,"Solo hay 5 números de comprobantes disponibles para la factura de Regimen Especial");
               cargar2();
            }
            if(es.equals("3")){
                JOptionPane.showMessageDialog(null,"Solo hay 3 números de comprobantes disponibles para la factura de Regimen Especial");
               cargar2();
            }
            if(es.equals("2")){
                JOptionPane.showMessageDialog(null,"Solo hay 2 números de comprobantes disponibles para la factura de Regimen Especial");
               cargar2();
            }
            if(es.equals("1")){
                JOptionPane.showMessageDialog(null,"Solo hay 1 número de comprobante disponible para la factura de Regimen Especial");
               cargar2();
            }else{
               cargar2(); 
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
}
    
public void cargar2() {
 PreparedStatement cargar = null;      
String cf = "Si";    
String var ="";
    String[] registros = new String[5];
try{
    final String detalle = "(SELECT MIN(numero) AS numero, ncf FROM secuencia_ncf_esecial WHERE Disponible='"+cf+"')";
    cargar = conexion.prepareStatement(detalle);
    ResultSet rs = cargar.executeQuery();
    
    while (rs.next()) {
        registros[0] = rs.getString("ncf");

        ncf_f.setText(registros[0]);

    }   
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
   ///////////////////////////////////////////////////////////////////////////////////CARGAR ARTICULO
    public void conectar_cargar_serv() throws SQLException {
        try {
            conecta();
             proceso_articulo();
        } finally {
            cerrar();
        }
      }
       public void servicio(){
       PreparedStatement cargar = null;  
      String cont ="";
            String comp = "";
            String es="";
            int compar = 0;
            int cant = 0;
            comp = cod_serv_fact.getText();
            String[] registros = new String[2];
            try {
             final String detalle = "SELECT estado_serv,existencia FROM servicios where codigo_serv='"+cod_serv_fact.getText()+"'";
            cargar = conexion.prepareStatement(detalle);
            ResultSet rs = cargar.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("estado_serv");
                registros[1] = rs.getString("existencia");
                es=registros[0];
                cant = Integer.parseInt(registros[1]);
            }
            JOptionPane.showMessageDialog(null,cant);
            if(es.equals("Inactivo")){
               JOptionPane.showMessageDialog(null,"Este articulo esta inactivo");
                
            }else{
                if(cant > 0){
                  JOptionPane.showMessageDialog(null,"Este articulo no tiene existencia");  
                }else{
                proceso_articulo();
                 desc_serv.setText("0.00");
                }
                 }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
   }
    public void proceso_articulo(){
        PreparedStatement cargar = null; 
        if(cod_serv_fact.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo del articulo");
        }else{
            String cont ="";
            String comp = "";
            String es="";
            String ub="";
            int compar = 0;
            int cant = 0;
            comp = cod_serv_fact.getText();
            String[] registros = new String[2];
            try {
                final String detalle = "SELECT cod_art,existencia FROM articulo where cod_art='"+cod_serv_fact.getText()+"'";
            cargar = conexion.prepareStatement(detalle);
            ResultSet rs = cargar.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("cod_art");
                registros[1] = rs.getString("existencia");
                es=registros[0];
                ub=registros[1];
                cant = Integer.parseInt(ub);
            }
            if(es.isEmpty()){
               JOptionPane.showMessageDialog(null,"Este articulo no existe");   
            }else{
                if(cant <=0){
                    JOptionPane.showMessageDialog(null,"Este articulo no tiene existencia");  
                }else{
                        cargar_pro();
                        cant_fact.requestFocus();
                }

            }
        }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
        }
    }
    void cargar_pro() throws SQLException {
        PreparedStatement cargar = null; 
        String[] registros = new String[12];
        try {
           final String detalle = "SELECT cod_art,descripcion,precio FROM articulo where cod_art='"+cod_serv_fact.getText()+"'";
            cargar = conexion.prepareStatement(detalle);
            ResultSet rs = cargar.executeQuery();
            
            while (rs.next()){
                registros[0] = rs.getString("cod_art");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("precio");
               
                
                cod_serv_fact.setText( registros[0]);
                descrii_fact.setText(registros[1]);
                precio_serv.setText(registros[2]);
                
                }
                    
        }catch(SQLException ex){
              JOptionPane.showMessageDialog(null, ex);
                    
        } 
    }
       
       
}
