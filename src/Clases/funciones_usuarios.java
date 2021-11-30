
package Clases;

import static Formularios.Login.usuario_l;
import Formularios.Menu_Principal;
import Formularios.Usuarios;
import static Formularios.Usuarios.*;
import static Formularios.Usuarios.Estatus_usu;
import static Formularios.Usuarios.Inactivo_usu;
import static Formularios.Usuarios.Nombre_usu;
import static Formularios.Usuarios.clave_usu;
import static Formularios.Usuarios.codigo_usu;
import static Formularios.Usuarios.slc_descuento;
import static Formularios.Usuarios.slc_editarp;
import static Formularios.Usuarios.slc_factura;
import static Formularios.Usuarios.slc_tp;
import static Formularios.Usuarios.slc_usuario;
import static Formularios.Usuarios.tabla_usuario;
import static Formularios.Usuarios.usuario_usu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restaurante.conector;


public class funciones_usuarios {
private Connection conexion = null;    
   
    public void conecta() throws SQLException{
//           String direc = conexion_2.valor;
//    String contra = conexion_2.clave;
//    String usu = conexion_2.usu;
//    
//        String jdbc= direc;
        conexion = DriverManager.getConnection(conexion_2.cadena1,conexion_2.cadena2,conexion_2.cadena3);
        conexion.setAutoCommit(false);
    }   
       public void conectar() throws SQLException {
        try {
            conecta();
            confirmar();
        } finally {
            cerrar();
        }
    }
     public void cerrar() throws SQLException{
        
    }
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void conectar_b() throws SQLException {
        try {
            conecta();
            confirmarB();
        } finally {
            cerrar();
        }
    }
     public void confirmarB(){
           PreparedStatement cons = null;
    String cont ="";
            String comp = "";
            String es="";
            int compar = 0;
            int cant = 0;
            comp = usuario_usu.getText();
            String[] registros = new String[2];
            try {
                final String detalle = "SELECT usuario FROM usuario where usuario='"+usuario_usu.getText()+"'";
            cons = conexion.prepareStatement(detalle);
            ResultSet rs = cons.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("usuario");
                es=registros[0];
            }
            if(es.isEmpty()){
               Borrar();
                
            }else{
              JOptionPane.showMessageDialog(null,"Este usuario no se puede borrar porque esta logueado");
               usuario_usu.requestFocus();
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }    
    }
     public void Borrar(){
        try{
            PreparedStatement psU2 = cn.prepareStatement("delete from usuario where id_usuario='"+codigo_usu.getText()+"'");
            psU2.executeUpdate();
            JOptionPane.showMessageDialog(null,"El usuario "+Nombre_usu.getText()+" fue eliminado");
        limpiar_usuario();
         Consultar_num_usu();
            limpiar_usuario();
            bloquear_usuario();
            estado_proceso.setText("");
            tabla_usuario.setEnabled(true);
            limpiar_tabla();
            cargar_usu();
     }catch (SQLException ex){
         Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE,null, ex);
     }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void limpiar_tabla(){
    DefaultTableModel modelo3 =(DefaultTableModel) tabla_usuario.getModel();
    modelo3.getDataVector().clear();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     void cargar_usu() throws SQLException {
    DefaultTableModel modelo2 =(DefaultTableModel) tabla_usuario.getModel();
    modelo2.getDataVector().clear();
    
    String[] registros = new String[10];
    String sql = "SELECT id_usuario,nombre_usu,usuario,contra_usu,estatus FROM usuario order by id_usuario ASC";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("id_usuario");
        registros[1] = rs.getString("nombre_usu");
        registros[2] = rs.getString("usuario");
        registros[3] = rs.getString("contra_usu");
        registros[4] = rs.getString("estatus");
        
        
        
        modelo2.addRow(registros);
    }   
       tabla_usuario.setModel(modelo2);
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     public void Consultar_num_usu(){
        int l;
        int cont=1;
        String num="";
        String es="";
        String SQL="SELECT MAX(id_usuario) AS id_usuario FROM usuario";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){
                es=rs.getString(1);
            }
            
            if(es==null){
                Usuarios.cod_usu.setText("00001");
                
            }else{
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var=Integer.parseInt(es);
                
            generador_numerico p = new generador_numerico();
            p.Generar_usu(var);
            
                Usuarios.cod_usu.setText(p.serie_cli());
            }
        } catch (Exception e) {
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         public void conectar_p() throws SQLException {
        try {
            conecta();
            cargar_permisos_menu();
        } finally {
            cerrar();
        }
    }
     public void cargar_permisos_menu(){
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7;
    String[] registros = new String[30];
    String sql = "SELECT factura_m,articulo,inventario,delivery,usuarios,despacho,comprobantes from usuario where usuario='"+usuario_l.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("factura_m");
        registros[1] = rs.getString("articulo");
        registros[2] = rs.getString("inventario");
        registros[3] = rs.getString("delivery");
        registros[4] = rs.getString("usuarios");
        registros[5] = rs.getString("despacho");
        registros[6] = rs.getString("comprobantes");
        
        sl1 = registros[0];
        sl2 = registros[1];
        sl3= registros[2];
        sl4 = registros[3];
        sl5 = registros[4];
        sl6 = registros[5]; 
        sl7 = registros[6];
        
        if(sl1.equals("Si")){ Clase_Variable_Publica.permiso_fact = 0; }
        else{Clase_Variable_Publica.permiso_fact = 1;}
      
        if(sl2.equals("Si")){ Clase_Variable_Publica.permiso_art = 0; }
        else{Clase_Variable_Publica.permiso_art = 1;}

        if(sl3.equals("Si")){ Clase_Variable_Publica.permiso_inv = 0; }
        else{Clase_Variable_Publica.permiso_inv = 1;}

        if(sl4.equals("Si")){ Clase_Variable_Publica.permiso_delv = 0; }
        else{Clase_Variable_Publica.permiso_delv = 1;}

        if(sl5.equals("Si")){ Clase_Variable_Publica.permiso_usu = 0; }
        else{Clase_Variable_Publica.permiso_usu = 1;}

        if(sl6.equals("Si")){ Clase_Variable_Publica.permiso_desp = 0; }
        else{Clase_Variable_Publica.permiso_desp = 1;}
   
        if(sl7.equals("Si")){ Clase_Variable_Publica.permiso_compro = 0; }
        else{Clase_Variable_Publica.permiso_compro = 1;}
  }   

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
}
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     public void confirmar(){
           PreparedStatement cons = null;
    String cont ="";
            String comp = "";
            String es="";
            int compar = 0;
            int cant = 0;
            comp = usuario_usu.getText();
            String[] registros = new String[2];
            try {
                final String detalle = "SELECT usuario FROM usuario where usuario='"+usuario_usu.getText()+"'";
            cons = conexion.prepareStatement(detalle);
            ResultSet rs = cons.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("usuario");
                es=registros[0];
            }
            if(es.isEmpty()){
                Guardar();
                
            }else{
              JOptionPane.showMessageDialog(null,"Este nombre de usuario ya esta  en uso");
               usuario_usu.requestFocus();
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }    
    }
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////  
     public void Guardar(){
      
    String obj;
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8,sl9,sl10,sl11,sl12,sl13,sl14,sl15,sl16,sl17,sl18;
    if(Estatus_usu.isSelected()){obj = "Activo";}
    else{obj = "Inactivo";}
    
    if(slc_factura.isSelected()){ sl1 = "Si";}
    else{ sl1 = "No";}
     
    if(slc_descuento.isSelected()){ sl2 = "Si";}
    else{ sl2 = "No";}
    
    if(slc_editarp.isSelected()){ sl3 = "Si";}
    else{ sl3 = "No";}
    
    if(slc_tipocomprobante.isSelected()){ sl4 = "Si";}
    else{ sl4 = "No";}
    
    if(slc_modificarfact.isSelected()){ sl5 = "Si";}
    else{ sl5 = "No";}
    
    if(slc_articulo.isSelected()){ sl6 = "Si";}
    else{ sl6 = "No";}
    
    if(slc_modificarart.isSelected()){ sl7 = "Si";}
    else{ sl7 = "No";}
    
    if(slc_inventario.isSelected()){ sl8 = "Si";}
     else{ sl8 = "No";} 
    
    if(slc_exportar.isSelected()){ sl9 = "Si";}
     else{ sl9 = "No";}    

    if(slc_delivery.isSelected()){ sl10 = "Si";}
     else{ sl10 = "No";}
    
     if(slc_modificardeliv.isSelected()){ sl11 = "Si";}
     else{ sl11 = "No";}
     
     if(slc_usuario.isSelected()){ sl12 = "Si";}
     else{ sl12 = "No";}
     
     if(slc_modificarusu.isSelected()){ sl13 = "Si";}
    else{ sl13 = "No";}
     
    if(slc_despacho.isSelected()){ sl14 = "Si";}
    else{ sl14 = "No";}
    
    if(slc_comprobantes.isSelected()){ sl15 = "Si";}
    else{ sl15 = "No";}
    
    if(slcBar.isSelected()){ sl16 = "Bar";}
    else{ sl16 = "Restaurante";}
        
    if(slc_generarcuadre.isSelected()){ sl17 = "Si";}
    else{ sl17 = "No";}
   
   try {
           String detalle ="INSERT INTO usuario(id_usuario,nombre_usu,usuario,contra_usu,estatus,factura_m,descuento,edit_precio,tipo_comp,modif_fact,articulo,modif_art,inventario,exp_inv,delivery,modificar_del,usuarios,modificar_usu,despacho,comprobantes,ubicacion,cuadre_caja) "
           + "VALUES ('"+Usuarios.cod_usu.getText()+"','"+Nombre_usu.getText()+"','"+usuario_usu.getText()+"','"+clave_usu.getText()+"','"+obj+"',"
           + "'"+sl1+"','"+sl2+"','"+sl3+"','"+sl4+"','"+sl5+"','"+sl6+"','"+sl7+"','"+sl8+"','"+sl9+"','"+sl10+"','"+sl11+"','"+sl12+"','"+sl13+"',"
           + "'"+sl14+"','"+sl15+"','"+sl16+"','"+sl17+"')";
           PreparedStatement pat = cn.prepareStatement(detalle);
            
            int n;
            n = pat.executeUpdate();
            if (n>0){
                
            }
            
            JOptionPane.showMessageDialog(null, "El Usuario "+Nombre_usu.getText()+" Se ha guardado satisfactoriamente");
            limpiar_usuario();
            bloquear_usuario();
            estado_proceso.setText("");
            tabla_usuario.setEnabled(true);
                DefaultTableModel modelo3 =(DefaultTableModel) tabla_usuario.getModel();
                modelo3.getDataVector().clear();
            cargar();
        modifica_usu.setEnabled(false);
        guardar_usu.setEnabled(false);
        limpiar_usu.setEnabled(false);
        //guardar_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102,102,102)));
}catch (SQLException e) {
    System.out.print(e.getMessage());
}     
        }
     public void limpiar_usuario(){
        clave_usu.setText("");
        Nombre_usu.setText("");
        usuario_usu.setText("");
        confir_clave_usu.setText("");
        Inactivo_usu.setVisible(false);
        Activo_usu.setVisible(true);
       Estatus_usu.setSelected(true);
       slc_descuento.setEnabled(false);
            slc_editarp.setEnabled(false);
            slc_tipocomprobante.setEnabled(false);
            slc_modificarfact.setEnabled(false);
            slc_exportar.setEnabled(false);
            slc_modificarart.setEnabled(false);
            slc_modificarusu.setEnabled(false);
            slc_modificarusu.setEnabled(false);
            slc_factura.setEnabled(false);
            slc_inventario.setEnabled(false);
            slc_articulo.setEnabled(false);
            slc_despacho.setEnabled(false);
            slc_usuario.setEnabled(false);
            slc_usuario.setEnabled(false);
            slc_delivery.setEnabled(false);
            slc_modificardeliv.setEnabled(false);
            slc_comprobantes.setEnabled(false);
            slcBar.setEnabled(false);
            slcRest.setEnabled(false);
    }
 void cargar() throws SQLException {
    DefaultTableModel modelo2 =(DefaultTableModel) Usuarios.tabla_usuario.getModel();
    modelo2.getDataVector().clear();
    
    String[] registros = new String[10];
    String sql = "SELECT id_usuario,nombre_usu,usuario,estatus FROM usuario order by id_usuario ASC";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("id_usuario");
        registros[1] = rs.getString("nombre_usu");
        registros[2] = rs.getString("usuario");
       registros[3] = rs.getString("estatus");
        
        
        
        modelo2.addRow(registros);
    }   
       Usuarios.tabla_usuario.setModel(modelo2);
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}   
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void cargar_permisos(){
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8,sl9,sl10,sl11,sl12,sl13,sl14,sl15,sl16,sl17,sl18;
    String[] registros = new String[30];
    String sql = "SELECT contra_usu,factura_m,descuento,edit_precio,tipo_comp,modif_fact,articulo,modif_art,inventario,exp_inv,delivery,modificar_del,usuarios,modificar_usu,despacho,comprobantes,ubicacion,cuadre_caja from usuario where id_usuario='"+codigo_usu.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("contra_usu");
        registros[1] = rs.getString("factura_m");
        registros[2] = rs.getString("descuento");
        registros[3] = rs.getString("edit_precio");
        registros[4] = rs.getString("tipo_comp");
        registros[5] = rs.getString("modif_fact");
        registros[6] = rs.getString("articulo");
        registros[7] = rs.getString("modif_art");
        registros[8] = rs.getString("inventario");
        registros[9] = rs.getString("exp_inv");
        registros[10] = rs.getString("delivery");
        registros[11] = rs.getString("modificar_del");
        registros[12] = rs.getString("usuarios");
        registros[13] = rs.getString("modificar_usu");
        registros[14] = rs.getString("despacho");
        registros[15] = rs.getString("comprobantes");
        registros[16] = rs.getString("ubicacion");
         registros[17] = rs.getString("cuadre_caja");
        
        sl2= registros[1]; 
        sl3 = registros[2]; sl4= registros[3];
        sl5 = registros[4]; sl6= registros[5];
        sl7 = registros[6]; sl8= registros[7];
        sl9 = registros[8]; sl10= registros[9];
        sl11 = registros[10]; sl12 = registros[11];
        sl13 = registros[12]; sl14  = registros[13];
        sl15 = registros[14]; sl16 = registros[15];
        sl17 = registros[16]; sl18 = registros[17]; 
        clave_usu.setText(registros[0]);
        confir_clave_usu.setText(registros[0]);
     
        if(sl2.equals("Si")){ slc_factura.setSelected(true); }
    else{ slc_factura.setSelected(false);}
     
    if(sl3.equals("Si")){slc_descuento.setSelected(true);}
    else{slc_descuento.setSelected(false);}
    
    if(sl4.equals("Si")){slc_editarp.setSelected(true);}
    else{slc_editarp.setSelected(false);}
    
    if(sl5.equals("Si")){slc_tipocomprobante.setSelected(true);}
    else{slc_tipocomprobante.setSelected(false);}
    
    if(sl6.equals("Si")){slc_modificarfact.setSelected(true);}
    else{slc_modificarfact.setSelected(false);}
    
    if(sl7.equals("Si")){slc_articulo.setSelected(true);}
    else{slc_articulo.setSelected(false);}
    
    if(sl8.equals("Si")){slc_modificarart.setSelected(true);}
    else{slc_modificarart.setSelected(false);}
    
    if(sl9.equals("Si")){slc_inventario.setSelected(true);}
     else{slc_inventario.setSelected(false);} 
    
    if(sl10.equals("Si")){slc_exportar.setSelected(true);}
     else{slc_exportar.setSelected(false);}    

    if(sl11.equals("Si")){slc_delivery.setSelected(true);}
     else{slc_delivery.setSelected(false);}
    
     if(sl12.equals("Si")){slc_modificardeliv.setSelected(true);}
     else{slc_modificardeliv.setSelected(false);}
     
     if(sl13.equals("Si")){slc_usuario.setSelected(true);}
    else{slc_usuario.setSelected(false);}
     
    if(sl14.equals("Si")){slc_modificarusu.setSelected(true);}
    else{slc_modificarusu.setSelected(false);}
    
    if(sl15.equals("Si")){slc_despacho.setSelected(true);}
    else{slc_despacho.setSelected(false);}
    
    if(sl16.equals("Si")){slc_comprobantes.setSelected(true);}
    else{slc_comprobantes.setSelected(false);}
    
    if(sl17.equals("Bar")){slcBar.setSelected(true);}
    else{slcRest.setSelected(true);}
        
      if(sl18.equals("Si")){slc_generarcuadre.setSelected(true);}
    else{slc_generarcuadre.setSelected(false);}
        }

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
} 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

       public void conectar_modif() throws SQLException {
        try {
            conecta();
           Modificar();
        } finally {
            cerrar();
        }
    }
public void confirmarM(){
           PreparedStatement cons = null;
    String cont ="";
            String comp = "";
            String es="";
            int compar = 0;
            int cant = 0;
            comp = usuario_usu.getText();
            String[] registros = new String[2];
            try {
                final String detalle = "SELECT usuario FROM usuario where usuario='"+usuario_usu.getText()+"'";
            cons = conexion.prepareStatement(detalle);
            ResultSet rs = cons.executeQuery();
            if(rs.next()){
                registros[0] = rs.getString("usuario");
                es=registros[0];
            }
            if(es.isEmpty()){
                Modificar();
                
            }else{
              JOptionPane.showMessageDialog(null,"Este nombre de usuario ya esta en uso");
               usuario_usu.requestFocus();
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }    
    }       
public void Modificar() throws SQLException{
    String obj;
    String es="";
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8,sl9,sl10,sl11,sl12,sl13,sl14,sl15,sl16,sl17;
    if(Estatus_usu.isSelected()){obj = "Activo";}
    else{obj = "Inactivo";}
    
     if(slc_factura.isSelected()){ sl1 = "Si";}
    else{ sl1 = "No";}
     
    if(slc_descuento.isSelected()){ sl2 = "Si";}
    else{ sl2 = "No";}
    
    if(slc_editarp.isSelected()){ sl3 = "Si";}
    else{ sl3 = "No";}
    
    if(slc_tipocomprobante.isSelected()){ sl4 = "Si";}
    else{ sl4 = "No";}
    
    if(slc_modificarfact.isSelected()){ sl5 = "Si";}
    else{ sl5 = "No";}
    
    if(slc_articulo.isSelected()){ sl6 = "Si";}
    else{ sl6 = "No";}
    
    if(slc_modificarart.isSelected()){ sl7 = "Si";}
    else{ sl7 = "No";}
    
    if(slc_inventario.isSelected()){ sl8 = "Si";}
     else{ sl8 = "No";} 
    
    if(slc_exportar.isSelected()){ sl9 = "Si";}
     else{ sl9 = "No";}    

    if(slc_delivery.isSelected()){ sl10 = "Si";}
     else{ sl10 = "No";}
    
     if(slc_modificardeliv.isSelected()){ sl11 = "Si";}
     else{ sl11 = "No";}
     
     if(slc_usuario.isSelected()){ sl12 = "Si";}
     else{ sl12 = "No";}
     
     if(slc_modificarusu.isSelected()){ sl13 = "Si";}
    else{ sl13 = "No";}
     
    if(slc_despacho.isSelected()){ sl14 = "Si";}
    else{ sl14 = "No";}
    
    if(slc_comprobantes.isSelected()){ sl15 = "Si";}
    else{ sl15 = "No";}
    
    if(slcBar.isSelected()){ sl16 = "Bar";}
    else{ sl16 = "Restaurante";}
    
    if(slc_generarcuadre.isSelected()){ sl17 = "Si";}
    else{ sl17 = "No";}
    
    PreparedStatement psU2 = cn.prepareStatement("UPDATE usuario SET nombre_usu='"+Nombre_usu.getText()+"',usuario='"+usuario_usu.getText()+"',contra_usu='"+clave_usu.getText()+"',estatus='"+obj+"',factura_m='"+sl1+"',descuento='"+sl2+"'"
           + ",edit_precio='"+sl3+"',tipo_comp='"+sl4+"',modif_fact='"+sl5+"',articulo='"+sl6+"',modif_art='"+sl7+"',inventario='"+sl8+"',exp_inv='"+sl9+"',delivery='"+sl10+"',modificar_del='"+sl11+"',usuarios='"+sl12+"'"
           + ",modificar_usu='"+sl13+"',despacho='"+sl14+"',comprobantes='"+sl15+"',ubicacion='"+sl16+"',cuadre_caja='"+sl17+"' where id_usuario='"+codigo_usu.getText()+"'");
            psU2.executeUpdate();
            JOptionPane.showMessageDialog(null,"El Usuario "+Nombre_usu.getText()+" Fue Modificado");
          limpiar_usuario();
            bloquear_usuario();
            tabla_usuario.setEnabled(true);
           estado_proceso.setText("");
           cargar();
        modifica_usu.setEnabled(false);
        guardar_usu.setEnabled(false);
        limpiar_usu.setEnabled(false);
        //guardar_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102,102,102)));
}
public void bloquear_usuario(){
clave_usu.setEditable(false);
confir_clave_usu.setEditable(false);
Nombre_usu.setEditable(false);
usuario_usu.setEditable(false);
Estatus_usu.setEnabled(false);
slc_descuento.setEnabled(false);
            slc_editarp.setEnabled(false);
            slc_tipocomprobante.setEnabled(false);
            slc_modificarfact.setEnabled(false);
            slc_exportar.setEnabled(false);
            slc_modificarart.setEnabled(false);
            slc_modificarusu.setEnabled(false);
            slc_modificarusu.setEnabled(false);
            slc_factura.setEnabled(false);
            slc_inventario.setEnabled(false);
            slc_articulo.setEnabled(false);
            slc_despacho.setEnabled(false);
            slc_usuario.setEnabled(false);
            slc_usuario.setEnabled(false);
            slc_delivery.setEnabled(false);
            slc_modificardeliv.setEnabled(false);
            slc_comprobantes.setEnabled(false);
            slcBar.setEnabled(false);
            slcRest.setEnabled(false);
            slc_generarcuadre.setEnabled(false);
    }  
////////////////////////////////////////////////////////////////////////////////////////
public void desbloquear_usuario(){
clave_usu.setEditable(true);
confir_clave_usu.setEditable(true);
Nombre_usu.setEditable(true);
usuario_usu.setEditable(true);
Estatus_usu.setEnabled(true);
slc_descuento.setEnabled(true);
            slc_editarp.setEnabled(true);
            slc_tipocomprobante.setEnabled(true);
            slc_modificarfact.setEnabled(true);
            slc_exportar.setEnabled(true);
            slc_modificarart.setEnabled(true);
            slc_modificarusu.setEnabled(true);
            slc_modificarusu.setEnabled(true);
            slc_factura.setEnabled(true);
            slc_inventario.setEnabled(true);
            slc_articulo.setEnabled(true);
            slc_despacho.setEnabled(true);
            slc_usuario.setEnabled(true);
            slc_usuario.setEnabled(true);
            slc_delivery.setEnabled(true);
            slc_modificardeliv.setEnabled(true);
            slc_comprobantes.setEnabled(true);
            slcBar.setEnabled(true);
            slcRest.setEnabled(true);
            slc_generarcuadre.setEnabled(true);
    } 
      conector cc = new conector();
    Connection cn = cc.conexion();  
}
