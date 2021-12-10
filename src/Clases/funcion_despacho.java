/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Formularios.Despacho;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restaurante.conector;

/**
 *
 * @author Leandro Aquino
 */
public class funcion_despacho {
    private Connection conexion = null;
    public void conectar() throws SQLException {
        try {
            conecta();
            //comparacion_fecha();
          transaccion();
        } finally {
            cerrar();
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
     private void transaccion() throws SQLException{
        PreparedStatement despacho = null; 
    String desp = "No";
    DefaultTableModel modelo3 =(DefaultTableModel) Despacho.tablaDespacho.getModel();
    modelo3.getDataVector().clear();
    DefaultTableModel dtm = (DefaultTableModel) Despacho.tablaDespacho.getModel();
        dtm.setRowCount(0);
    String[] registros = new String[6];
    final String detalle ="SELECT cantidad,descr_serv,num_factura,tipo_despacho FROM detalle_fact where despacho='"+desp+"' order by num_factura ASC";
//            despacho =conexion.prepareStatement(detalle);
//            despacho.executeUpdate();    
    try{
        despacho = conexion.prepareStatement(detalle);
        ResultSet rs = despacho.executeQuery();
    
    while (rs.next()) {
        registros[0] = rs.getString("cantidad");
        registros[1] = rs.getString("descr_serv");
        registros[2]= rs.getString("num_factura"); 
        registros[3]= rs.getString("tipo_despacho"); 


        modelo3.addRow(registros);
    }
    Despacho.tablaDespacho.setModel(modelo3);
        conexion.commit();
    }catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
   }
    //finally{
//            if(despacho !=null){
//                despacho.close(); 
//            }
//        }

    
    
           }
                

void cerrar(){

    }
     conector cc = new conector();
    Connection cn = cc.conexion(); 
}
