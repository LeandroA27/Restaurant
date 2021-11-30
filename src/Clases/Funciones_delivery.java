/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import Formularios.Articulo;
import static Formularios.Articulo.*;
import static Formularios.Delivery.*;
import Formularios.Delivery;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restaurante.conector;

/**
 *
 * @author Thealex7
 */
public class Funciones_delivery {
    
        public void Guardar(){
        
   try {
            String sql ="INSERT INTO delivery(codigo,nombre,telefono,cedula,direccion,placa,chasis,color,marca)"
                + "VALUES('"+Cocdigo_del.getText()+"','"+nombre_del.getText()+"','"+tel_del.getText()+"','"+cedula_del.getText()+"','"+direc_del.getText()+"','"+placa_del.getText()+"','"+chasis_del.getText()+"','"+color_del.getText()+"',"
                    + "'"+marca_del.getText()+"')";
            PreparedStatement pat = cn.prepareStatement(sql);
            
            int n;
            n = pat.executeUpdate();
            if (n>0){
                
            }
            JOptionPane.showMessageDialog(null, "El delivery "+nombre_del.getText()+" Se ha guardado satisfactoriamente");
            limpiar();
            desbloquear();
            Consultar_num_usu();
            cargar_del();
            Descrip_inv.requestFocus();
}catch (SQLException e) {
    System.out.print(e.getMessage());
}     
        }
        
public void limpiar(){
    Cocdigo_del.setText("");
        nombre_del.setText("");
        tel_del.setText("");
        cedula_del.setText("");
        direc_del.setText("");
        placa_del.setText("");
        chasis_del.setText("");
        color_del.setText("");
        marca_del.setText("");
        cod_del_modif.setText("");
        nombre_del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
        tel_del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
        cedula_del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
        direc_del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
        placa_del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
        chasis_del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
        color_del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
        marca_del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
}
public void desbloquear(){
nombre_del.setEnabled(true);
    tel_del.setEditable(true);
    cedula_del.setEditable(true);
    direc_del.setEditable(true);
    placa_del.setEditable(true);
    chasis_del.setEditable(true);
    color_del.setEditable(true);
    marca_del.setEditable(true);
    buscar_del.setEditable(true);
}
public void desbloquear1(){
    nombre_del.setEnabled(true);
    tel_del.setEditable(true);
    cedula_del.setEditable(true);
    direc_del.setEditable(true);
    placa_del.setEditable(true);
    chasis_del.setEditable(true);
    color_del.setEditable(true);
    marca_del.setEditable(true);
    buscar_del.setEditable(true);
}
public void Consultar_num_usu(){
        int l;
        int cont=1;
        String num="";
        String es="";
        String SQL="SELECT MAX(codigo) AS codigo FROM delivery";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){
                es=rs.getString(1);
            }
            
            if(es==null){
                Cocdigo_del.setText("00001");
                
            }else{
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var=Integer.parseInt(es);
                
            generador_numerico p = new generador_numerico();
            p.Generar_usu(var);
            
             Cocdigo_del.setText(p.serie_inv());
            }
        } catch (Exception e) {
        }
    }
public void Modificar(){
           try{
            PreparedStatement psU2 = cn.prepareStatement("UPDATE delivery SET nombre='"+nombre_del.getText()+"',telefono='"+tel_del.getText()+"',cedula='"+cedula_del.getText()+"',direccion='"+direc_del.getText()+"',placa='"+placa_del.getText()+"',chasis='"+chasis_del.getText()+"',color='"+color_del.getText()+"',marca='"+marca_del.getText()+"' where codigo='"+cod_del_modif.getText()+"'");
            psU2.executeUpdate();
             JOptionPane.showMessageDialog(null, "El delivery "+nombre_del.getText()+" Se ha modificado satisfactoriamente");
    limpiar();
    desbloquear();
    Consultar_num_usu();  
    cargar_del();
    Descrip_inv.requestFocus();
     }catch (SQLException ex){
         Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE,null, ex);
     }
    
}
//    public void Borrar(){
//        try{
//                PreparedStatement psU2 = cn.prepareStatement("delete from inventario where codigo_inv='"+cod_art_modif.getText()+"'");
//                psU2.executeUpdate();
//                JOptionPane.showMessageDialog(null, "El producto "+Descrip_inv.getText()+" Se ha eliminado satisfactoriamente");
//                limpiar();
//                desbloquear();
//                Consultar_num_usu();  
//                Descrip_inv.requestFocus();
//                }catch (SQLException ex){
//                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE,null, ex);
//                        }
//    }
public void cargar(){
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8,sl9,sl10,sl11,sl12,sl13,sl14,sl15,sl16,sl17,sl18;
    String[] registros = new String[30];
    String sql = "SELECT direccion,placa,chasis,color,marca from delivery where codigo='"+Cocdigo_del.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("direccion");
        registros[1] = rs.getString("placa");
        registros[2] = rs.getString("chasis");
        registros[3] = rs.getString("color");
        registros[4] = rs.getString("marca");
        
        direc_del.setText(registros[0]); 
        placa_del.setText(registros[1]);   
        chasis_del.setText(registros[2]);   
        color_del.setText(registros[3]); 
        marca_del.setText(registros[4]);
    }
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
} 
void cargar_del() throws SQLException {
        String est = "Activo";
        DefaultTableModel modelo2 = (DefaultTableModel) tabla_del.getModel();
        modelo2.getDataVector().clear();

        String[] registros = new String[10];
        String sql = "SELECT codigo,nombre,cedula,telefono FROM delivery order by codigo ASC";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("codigo");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("cedula");
                registros[3] = rs.getString("telefono");

                modelo2.addRow(registros);
            }
            tabla_del.setModel(modelo2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
    conector cc = new conector();
    Connection cn = cc.conexion();
}
