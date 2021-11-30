/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Formularios.Cambio_Clave;
import Formularios.Login;
import static Formularios.Login.Clave;
import static Formularios.Login.aceptar;
import static Formularios.Login.cancelar;
import static Formularios.Login.usuario_l;
import Formularios.Menu_Principal;
import static Formularios.Menu_Principal.menu_fecha1;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import restaurante.conector;

/**
 *
 * @author Thealex7
 */
public class funciones_login {
   private Connection conexion = null;
   String usu = usuario_l.getText();
   String pas = new String(Login.Clave.getPassword());
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
      public void conectar() throws SQLException {
        try {
            conecta();
            acceder(usu,pas);
        } finally {
            cerrar();
        }
    }   
  public void cerrar() throws SQLException{
        
    }     
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  void cargar_dia(){
      String es="",es1="";
    String[] registros = new String[2];
    String sql1 = "set time_zone = '-04:00'"; 
    String sql2 = "SELECT date_format(CURDATE(),'%d-%m-%Y') as fechaDom, CURDATE() as fechaUS"; 
    //String sql = "SELECT (ELT(WEEKDAY(CURRENT_DATE()) + 1, 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo')) AS DIA_SEMANA"; 
     try {
                java.sql.Statement st = conexion.createStatement();
                ResultSet rs1 = st.executeQuery(sql1);
                ResultSet rs2 = st.executeQuery(sql2);
                //ResultSet rs = st.executeQuery(sql);
//            if(rs.next()){
//                registros[0] = rs.getString("DIA_SEMANA");
//                es=registros[0];
//            }
             if(rs2.next()){
                registros[0] = rs2.getString("fechaDom");
                registros[1] = rs2.getString("fechaUS");
                Clase_Variable_Publica.fechaDom=registros[0];
                Clase_Variable_Publica.fechaus=registros[1];
            }
//            menu_fecha1.setText(es);
            
            //menu_fecha.setText(formato1.format(sistFecha1));
            
            }catch(SQLException ex){
     JOptionPane.showMessageDialog(null, ex);
    
}
  }
///////////////////////////////////////////////////////////////////////////////////////////////////////////    
    void acceder (String usuario, String pass){
      String cap = "";
      String nombre = "";
      String sql = "SELECT estatus,nombre_usu FROM usuario WHERE usuario='"+usuario+"' && contra_usu='"+pass+"'";
      try{
          Statement st = cn.createStatement();
          ResultSet rs = st.executeQuery(sql);
          while(rs.next()){
              cap = rs.getString("estatus");
              nombre = rs.getString("nombre_usu");
          }
          
          
          if (cap.equals("Activo")){
              //JOptionPane.showMessageDialog(null,"Bienvenido");
              
              //back obj = new back();
              //obj.setVisible(true);
              funciones_usuarios fu = new funciones_usuarios();
              Clase_Variable_Publica.valor=1;
              cargar_per_ft();
              cargar_per_art();
              cargar_per_inv();
              conecta();
              cargar_per_usu();
              cargar_per_deliv();
            try {
                fu.conectar_p();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
               Menu_Principal ingreso = new Menu_Principal();
              ingreso.setVisible(true);
              Menu_Principal.nombre_usu_cli.setText(nombre);
              //ingreso.pack(); 
          }
          if(cap.equals("Inactivo"))
          {   
              JOptionPane.showMessageDialog(null,"Este usuario esta inactivo favor intente de nuevo");
              Login.Clave.setText("");
              Login.negro_l.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         usuario_l.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
         Clave.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
         aceptar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
         cancelar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
              usuario_l.setText("");
              usuario_l.requestFocus();
          }
          
        if ((!cap.equals("Activo")) && (!cap.equals("Inactivo"))){
            JOptionPane.showMessageDialog(null,"El usuario y la contraseña no coinciden");
            Login.negro_l.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    usuario_l.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
         Clave.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
         aceptar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
         cancelar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
            Login.Clave.setText("");
        }  
          
      }catch(SQLException ex){
          
      }  
    }
   //------------------------------------------------------------------------------------------------------//
    public void cargar_per_cli(){
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8;
    String[] registros = new String[30];
    String sql = "SELECT modificar_cli,borrar_cli from usuario where usuario='"+usuario_l.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("modificar_cli");
        registros[1] = rs.getString("borrar_cli");
       

        sl1 = registros[0];
        sl2 = registros[1];

        
//        if(sl1.equals("Si")){ Clase_Variable_Publica.modificar_cli = 0; }
//        else{Clase_Variable_Publica.modificar_cli = 1;}
//      
//        if(sl2.equals("Si")){ Clase_Variable_Publica.borrar_cli = 0; }
//        else{Clase_Variable_Publica.borrar_cli = 1;}

  }   

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
}
   //------------------------------------------------------------------------------------------------------//
    public void cargar_per_deliv(){
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8;
    String[] registros = new String[30];
    String sql = "SELECT modificar_del from usuario where usuario='"+usuario_l.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("modificar_del");
       

        sl1 = registros[0];


        
        if(sl1.equals("Si")){ Clase_Variable_Publica.modificar_deliv = 0; }
        else{Clase_Variable_Publica.modificar_deliv = 1;}


  }   

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
}
  //-------------------------------------------------------------------------------------------------------//
    public void cargar_per_usu(){
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8;
    String[] registros = new String[30];
    String sql = "SELECT modificar_usu from usuario where usuario='"+usuario_l.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("modificar_usu");
       

        sl1 = registros[0];

        
        if(sl1.equals("Si")){ Clase_Variable_Publica.modificar_usu = 0; }
        else{Clase_Variable_Publica.modificar_usu = 1;}

  }   

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
}
    //-------------------------------------------------------------------------------------------------------//
    public void cargar_per_inv(){
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8;
    String[] registros = new String[30];
    String sql = "SELECT exp_inv from usuario where usuario='"+usuario_l.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("exp_inv");

        sl1 = registros[0];


        
        if(sl1.equals("Si")){ Clase_Variable_Publica.exportar_inv = 0; }
        else{Clase_Variable_Publica.exportar_inv = 1;}

        
  }   

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
}
 //------------------------------------------------------------------------------------------------------//
    public void cargar_per_art(){
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8;
    String[] registros = new String[30];
    String sql = "SELECT modif_art,ubicacion from usuario where usuario='"+usuario_l.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("modif_art");
        registros[1] = rs.getString("ubicacion");

        sl1 = registros[0];
        sl2 = registros[1];
        
        if(sl1.equals("Si")){ Clase_Variable_Publica.modificar_art = 0; }
        else{Clase_Variable_Publica.modificar_art = 1;}
        Clase_Variable_Publica.ubicacion = sl2 ;
  }   

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
}
 //---------------------------------------------------------------------------------------------------------//   
    public void cargar_per_ft() throws SQLException{
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8;
    String[] registros = new String[30];
    String sql = "SELECT descuento,edit_precio,tipo_comp,modif_fact,cuadre_caja from usuario where usuario='"+usuario_l.getText()+"'";
conecta();
try{
    java.sql.Statement st = conexion.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("descuento");
        registros[1] = rs.getString("edit_precio");
        registros[2] = rs.getString("tipo_comp");
        registros[3] = rs.getString("modif_fact");
        registros[4] = rs.getString("cuadre_caja");

        sl1 = registros[0];
        sl2 = registros[1];
        sl3= registros[2];
        sl4 = registros[3];
        sl5 = registros[4];

        
        if(sl1.equals("Si")){ Clase_Variable_Publica.descuento_ft = 0; }
        else{Clase_Variable_Publica.descuento_ft = 1;}
      
        if(sl2.equals("Si")){ Clase_Variable_Publica.editar_precio_ft = 0; }
        else{Clase_Variable_Publica.editar_precio_ft = 1;}

        if(sl3.equals("Si")){ Clase_Variable_Publica.comprobante = 0; }
        else{Clase_Variable_Publica.comprobante = 1;}

        if(sl4.equals("Si")){ Clase_Variable_Publica.modificar_ft = 0; }
        else{Clase_Variable_Publica.modificar_ft = 1;}
        
        if(sl5.equals("Si")){ Clase_Variable_Publica.cuadre_ft = 0; }
        else{Clase_Variable_Publica.cuadre_ft = 1;}


  }   

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
}
 //---------------------------------------------------------------------------------------------------------//
          public void cambio() throws SQLException {
        try {
            conecta();
            String cambio_usu = Cambio_Clave.usuario_c.getText();
            String cambio_pass = new String (Cambio_Clave.clave_anterior.getPassword());
            acceder_cambio(cambio_usu,cambio_pass);
        } finally {
            cerrar();
        }
    }
//----------------------------------------------------------------------------------------------------------//
    
void acceder_cambio (String usuarioC, String passC){
    
      String cap = "";
      String nombre = "";
      String sql = "SELECT estatus,nombre_usu FROM usuario WHERE nombre_usu='"+usuarioC+"' && contra_usu='"+passC+"'";
      try{
          Statement st = cn.createStatement();
          ResultSet rs = st.executeQuery(sql);
          while(rs.next()){
              cap = rs.getString("estatus");
              nombre = rs.getString("nombre_usu");
          }
          
          
          if (cap.equals("Activo")){ 
              Cambio_Clave.clave_nueva.setEnabled(true);
              Cambio_Clave.confirmar_clave.setEnabled(true);
              Cambio_Clave.clave_nueva.requestFocus();
          }
   
          if(cap.equals("Inactivo"))
          {   
              
          }
          
        if ((!cap.equals("Activo")) && (!cap.equals("Inactivo"))){
        JOptionPane.showMessageDialog(null, "La contraseña esta incorrecta"); 
        Cambio_Clave.clave_anterior.setText(""); 
        }  
          
      }catch(SQLException ex){
          
      }  
    }    
    conector cc = new conector();
    Connection cn = cc.conexion();
}
