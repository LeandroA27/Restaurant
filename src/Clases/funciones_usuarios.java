
package Clases;

import static Formularios.Login.usuario_l;
import Formularios.Clientes;
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
         Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE,null, ex);
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
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8,sl9,sl10,sl11,sl12,sl13;
    String[] registros = new String[30];
    String sql = "SELECT factura_m,cotizacion,contrata,cliente,servicios,usuarios,comprobantes,cxc,creativos,graficas,gastos,modif_gastos,modificar_creativo from usuario where usuario='"+usuario_l.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("factura_m");
        registros[1] = rs.getString("cotizacion");
        registros[2] = rs.getString("contrata");
        registros[3] = rs.getString("cliente");
        registros[4] = rs.getString("servicios");
        registros[5] = rs.getString("usuarios");
        registros[6] = rs.getString("comprobantes");
        registros[7] = rs.getString("cxc");
        registros[8] = rs.getString("creativos");
        registros[9] = rs.getString("graficas");
        registros[10] = rs.getString("gastos");
        registros[11] = rs.getString("modif_gastos");
        registros[12] = rs.getString("modificar_creativo");
        
        sl1 = registros[0];
        sl2 = registros[1];
        sl3= registros[2];
        sl4 = registros[3];
        sl5 = registros[4];
        sl6 = registros[5]; 
        sl7 = registros[6];
        sl8 = registros[7];
        sl9 = registros[8];
        sl10 = registros[9];
        sl11 = registros[10];
        sl12 = registros[11];
        sl13 = registros[12];
        
        if(sl1.equals("Si")){ Clase_Variable_Publica.permiso_fact = 0; }
        else{Clase_Variable_Publica.permiso_fact = 1;}
      
        if(sl2.equals("Si")){ Clase_Variable_Publica.permiso_cot = 0; }
        else{Clase_Variable_Publica.permiso_cot = 1;}

        if(sl3.equals("Si")){ Clase_Variable_Publica.permiso_contr = 0; }
        else{Clase_Variable_Publica.permiso_contr = 1;}

        if(sl4.equals("Si")){ Clase_Variable_Publica.permiso_client = 0; }
        else{Clase_Variable_Publica.permiso_client = 1;}

        if(sl5.equals("Si")){ Clase_Variable_Publica.permiso_serv = 0; }
        else{Clase_Variable_Publica.permiso_serv = 1;}

        if(sl6.equals("Si")){ Clase_Variable_Publica.permiso_usu = 0; }
        else{Clase_Variable_Publica.permiso_usu = 1;}
   
        if(sl7.equals("Si")){ Clase_Variable_Publica.permiso_compro = 0; }
        else{Clase_Variable_Publica.permiso_compro = 1;}
  
        if(sl8.equals("Si")){ Clase_Variable_Publica.permiso_cxc = 0; }
        else{Clase_Variable_Publica.permiso_cxc = 1;}
        
        if(sl9.equals("Si")){ Clase_Variable_Publica.creativos = 0; }
        else{Clase_Variable_Publica.creativos = 1;}
        
        if(sl10.equals("Si")){ Clase_Variable_Publica.permiso_graficos = 0; }
        else{Clase_Variable_Publica.permiso_graficos = 1;}
        
        if(sl11.equals("Si")){ Clase_Variable_Publica.permiso_gastos = 0; }
        else{Clase_Variable_Publica.permiso_gastos = 1;}
        
        if(sl12.equals("Si")){ Clase_Variable_Publica.permiso_MGastos = 0; }
        else{Clase_Variable_Publica.permiso_MGastos = 1;}
        
        if(sl13.equals("Si")){ Clase_Variable_Publica.permiso_MCreativo = 0; }
        else{Clase_Variable_Publica.permiso_MCreativo = 1;}
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
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8,sl9,sl10,sl11,sl12,sl13,sl14,sl15,sl16,sl17,sl18,sl19,sl20,sl21,sl22,sl23,sl24,sl25,sl26,sl27,sl28;
    if(Estatus_usu.isSelected()){obj = "Activo";}
    else{obj = "Inactivo";}
    
    if(slc_factura.isSelected()){ sl1 = "Si";}
    else{ sl1 = "No";}
     
    if(slc_descuento.isSelected()){ sl2 = "Si";}
    else{ sl2 = "No";}
    
    if(slc_editarp.isSelected()){ sl3 = "Si";}
    else{ sl3 = "No";}
    
    if(slc_tipofact.isSelected()){ sl4 = "Si";}
    else{ sl4 = "No";}
    
    if(slc_tipocompr.isSelected()){ sl5 = "Si";}
    else{ sl5 = "No";}
    
    if(slc_modifact.isSelected()){ sl6 = "Si";}
    else{ sl6 = "No";}
    
    if(slc_cotizacion.isSelected()){ sl7 = "Si";}
    else{ sl7 = "No";}
    
    if(slc_modificarc.isSelected()){ sl8 = "Si";}
     else{ sl8 = "No";} 
    
    if(slc_borrarc.isSelected()){ sl9 = "Si";}
     else{ sl9 = "No";}    

    if(slc_contrata.isSelected()){ sl10 = "Si";}
     else{ sl10 = "No";}
    
     if(slc_modificarcon.isSelected()){ sl11 = "Si";}
     else{ sl11 = "No";}
     
     if(slc_borrarcon.isSelected()){ sl12 = "Si";}
    else{ sl12 = "No";}
     
    if(slc_cliente.isSelected()){ sl13 = "Si";}
    else{ sl13 = "No";}
    
    if(slc_modificarclien.isSelected()){ sl14 = "Si";}
    else{ sl14 = "No";}
    
    if(slc_borrarclien1.isSelected()){ sl15 = "Si";}
    else{ sl15 = "No";}
    
    if(slc_servicios.isSelected()){ sl16 = "Si";}
    else{ sl16 = "No";}
    
    if(slc_modificarserv.isSelected()){ sl17 = "Si";}
    else{ sl17 = "No";}
    
    if(slc_borrarserv.isSelected()){ sl18 = "Si";}
    else{ sl18 = "No";}
    
    if(slc_usuario.isSelected()){ sl19 = "Si";}
     else{ sl19 = "No";} 
    
    if(slc_modificarusu.isSelected()){ sl20 = "Si";}
     else{ sl20 = "No";}    

    if(slc_borrarusu.isSelected()){ sl21 = "Si";}
     else{ sl21 = "No";}
    
     if(slc_comprobante.isSelected()){ sl22 = "Si";}
     else{ sl22 = "No";}
     
     if(slc_cxc.isSelected()){ sl23 = "Si";}
     else{ sl23 = "No";}
     
     if(slc_creativos.isSelected()){sl24 = "Si";}
     else{sl24="No";}
     
      if(slc_Gastos.isSelected()){ sl25 = "Si";}
     else{ sl25 = "No";}
    
     if(slc_modificarGasto.isSelected()){ sl26 = "Si";}
     else{ sl26 = "No";}
     
     if(slc_modificarcreativo.isSelected()){ sl27 = "Si";}
     else{ sl27 = "No";}
     
     if(slc_Graficas.isSelected()){sl28 = "Si";}
     else{sl28="No";}
   try {
           String detalle ="INSERT INTO usuario(id_usuario,nombre_usu,usuario,contra_usu,estatus,factura_m,descuento,edit_precio,tipo_fact,tipo_comp,"
           + "modif_fact,cotizacion,modif_cot,borrar_cot,contrata,modif_cont,borrar_cont,cliente,modificar_cli,borrar_cli,servicios,modificar_ser,"
           + "borar_serv,usuarios,modificar_usu,borrar_usu,comprobantes,cxc,creativos,gastos,modif_gastos,modificar_creativo,graficas) "
           + "VALUES ('"+Usuarios.cod_usu.getText()+"','"+Nombre_usu.getText()+"','"+usuario_usu.getText()+"','"+clave_usu.getText()+"','"+obj+"',"
           + "'"+sl1+"','"+sl2+"','"+sl3+"','"+sl4+"','"+sl5+"','"+sl6+"','"+sl7+"','"+sl8+"','"+sl9+"','"+sl10+"','"+sl11+"','"+sl12+"','"+sl13+"',"
           + "'"+sl14+"','"+sl15+"','"+sl16+"','"+sl17+"','"+sl18+"','"+sl19+"','"+sl20+"','"+sl21+"','"+sl22+"','"+sl23+"','"+sl24+"','"+sl25+"','"+sl26+"','"+sl27+"','"+sl28+"')";
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
       slc_descuento.setSelected(false);
slc_editarp.setSelected(false);
slc_tipofact.setSelected(false);
slc_tipocompr.setSelected(false);
slc_modifact.setSelected(false);
slc_modificarc.setSelected(false);
slc_borrarc.setSelected(false);
slc_modificarcon.setSelected(false);
slc_borrarcon.setSelected(false);
slc_modificarclien.setSelected(false);
slc_borrarclien1.setSelected(false);
slc_modificarserv.setSelected(false);
slc_borrarserv.setSelected(false);
slc_modificarusu.setSelected(false);
slc_borrarusu.setSelected(false);
slc_comprobante.setSelected(false);
slc_cxc.setSelected(false);
slc_factura.setSelected(false);
slc_cotizacion.setSelected(false);
slc_contrata.setSelected(false);
slc_cliente.setSelected(false);
slc_servicios.setSelected(false);
slc_usuario.setSelected(false);
slc_tp.setSelected(false);
slc_creativos.setSelected(false);
slc_Gastos.setSelected(false);
slc_modificarGasto.setSelected(false);
slc_modificarcreativo.setSelected(false);
slc_Graficas.setSelected(false);
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
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8,sl9,sl10,sl11,sl12,sl13,sl14,sl15,sl16,sl17,sl18,sl19,sl20,sl21,sl22,sl23,sl24,sl25,sl26,sl27,sl28;
    String[] registros = new String[30];
    String sql = "SELECT contra_usu,factura_m,descuento,edit_precio,tipo_fact,tipo_comp,modif_fact,cotizacion,modif_cot,borrar_cot,contrata,modif_cont,borrar_cont,cliente,modificar_cli,borrar_cli,servicios,modificar_ser,borar_serv,usuarios,modificar_usu,borrar_usu,comprobantes,cxc,creativos,gastos,modif_gastos,modificar_creativo,graficas from usuario where id_usuario='"+codigo_usu.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("factura_m");
        registros[1] = rs.getString("descuento");
        registros[2] = rs.getString("edit_precio");
        registros[3] = rs.getString("tipo_fact");
        registros[4] = rs.getString("tipo_comp");
        registros[5] = rs.getString("modif_fact");
        registros[6] = rs.getString("cotizacion");
        registros[7] = rs.getString("modif_cot");
        registros[8] = rs.getString("borrar_cot");
        registros[9] = rs.getString("contrata");
        registros[10] = rs.getString("modif_cont");
        registros[11] = rs.getString("borrar_cont");
        registros[12] = rs.getString("cliente");
        registros[13] = rs.getString("modificar_cli");
        registros[14] = rs.getString("borrar_cli");
        registros[15] = rs.getString("servicios");
        registros[16] = rs.getString("modificar_ser");
        registros[17] = rs.getString("borar_serv");
        registros[18] = rs.getString("usuarios");
        registros[19] = rs.getString("modificar_usu");
        registros[20] = rs.getString("borrar_usu");
        registros[21] = rs.getString("comprobantes");
        registros[22] = rs.getString("cxc");
        registros[23] = rs.getString("creativos");
        registros[24] = rs.getString("contra_usu");
        registros[25] = rs.getString("gastos");
        registros[26] = rs.getString("modif_gastos");
        registros[27] = rs.getString("modificar_creativo");
        registros[28] = rs.getString("graficas");
        
        sl1 = registros[0]; sl2= registros[1]; 
        sl3 = registros[2]; sl4= registros[3];
        sl5 = registros[4]; sl6= registros[5];
        sl7 = registros[6]; sl8= registros[7];
        sl9 = registros[8]; sl10= registros[9];
        sl11 = registros[10]; sl12 = registros[11];
        sl13 = registros[12]; sl14  = registros[13];
        sl15 = registros[14]; sl16 = registros[15];
        sl17 = registros[16]; sl18 = registros[17];
        sl19 = registros[18]; sl20 = registros[19];
        sl21 = registros[20]; sl22 = registros[21];
        sl23 = registros[22]; sl24 = registros[23];
        sl25 = registros[25]; sl26 = registros[26];
        sl27 = registros[27]; sl28 = registros[28];
        clave_usu.setText(registros[24]);
        confir_clave_usu.setText(registros[24]);
     
        if(sl1.equals("Si")){ slc_factura.setSelected(true); }
    else{ slc_factura.setSelected(false);}
     
    if(sl2.equals("Si")){slc_descuento.setSelected(true);}
    else{slc_descuento.setSelected(false);}
    
    if(sl3.equals("Si")){slc_editarp.setSelected(true);}
    else{slc_editarp.setSelected(false);}
    
    if(sl4.equals("Si")){slc_tipofact.setSelected(true);}
    else{slc_tipofact.setSelected(false);}
    
    if(sl5.equals("Si")){slc_tipocompr.setSelected(true);}
    else{slc_tipocompr.setSelected(false);}
    
    if(sl6.equals("Si")){slc_modifact.setSelected(true);}
    else{slc_modifact.setSelected(false);}
    
    if(sl7.equals("Si")){slc_cotizacion.setSelected(true);}
    else{slc_cotizacion.setSelected(false);}
    
    if(sl8.equals("Si")){slc_modificarc.setSelected(true);}
     else{slc_modificarc.setSelected(false);} 
    
    if(sl9.equals("Si")){slc_borrarc.setSelected(true);}
     else{slc_borrarc.setSelected(false);}    

    if(sl10.equals("Si")){slc_contrata.setSelected(true);}
     else{slc_contrata.setSelected(false);}
    
     if(sl11.equals("Si")){slc_modificarcon.setSelected(true);}
     else{slc_modificarcon.setSelected(false);}
     
     if(sl12.equals("Si")){slc_borrarcon.setSelected(true);}
    else{slc_borrarcon.setSelected(false);}
     
    if(sl13.equals("Si")){slc_cliente.setSelected(true);}
    else{slc_cliente.setSelected(false);}
    
    if(sl14.equals("Si")){slc_modificarclien.setSelected(true);}
    else{slc_modificarclien.setSelected(false);}
    
    if(sl15.equals("Si")){slc_borrarclien1.setSelected(true);}
    else{slc_borrarclien1.setSelected(false);}
    
    if(sl16.equals("Si")){slc_servicios.setSelected(true);}
    else{slc_servicios.setSelected(false);}
    
    if(sl16.equals("Si")){slc_modificarserv.setSelected(true);}
    else{slc_modificarserv.setSelected(false);}
    
    if(sl18.equals("Si")){slc_borrarserv.setSelected(true);}
    else{slc_borrarserv.setSelected(false);}
    
    if(sl19.equals("Si")){slc_usuario.setSelected(true);}
     else{slc_usuario.setSelected(false);} 
    
    if(sl20.equals("Si")){slc_modificarusu.setSelected(true);}
     else{slc_modificarusu.setSelected(false);}    

    if(sl21.equals("Si")){slc_borrarusu.setSelected(true);}
     else{slc_borrarusu.setSelected(false);}
    
     if(sl22.equals("Si")){slc_comprobante.setSelected(true);}
     else{slc_comprobante.setSelected(false);}
     
     if(sl23.equals("Si")){slc_cxc.setSelected(true);}
     else{slc_cxc.setSelected(false);}
     
    if(sl24.equals("Si")){slc_creativos.setSelected(true);}
     else{slc_creativos.setSelected(false);}
    
    if(sl25.equals("Si")){slc_Gastos.setSelected(true);}
     else{slc_Gastos.setSelected(false);}
    
    if(sl26.equals("Si")){slc_modificarGasto.setSelected(true);}
     else{slc_modificarGasto.setSelected(false);}
    
    if(sl27.equals("Si")){slc_modificarcreativo.setSelected(true);}
     else{slc_modificarcreativo.setSelected(false);}
    
    if(sl28.equals("Si")){slc_Graficas.setSelected(true);}
     else{slc_Graficas.setSelected(false);}
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
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8,sl9,sl10,sl11,sl12,sl13,sl14,sl15,sl16,sl17,sl18,sl19,sl20,sl21,sl22,sl23,sl24,sl25,sl26,sl27,sl28;
    if(Estatus_usu.isSelected()){obj = "Activo";}
    else{obj = "Inactivo";}
    
    if(slc_factura.isSelected()){ sl1 = "Si";}
    else{ sl1 = "No";}
     
    if(slc_descuento.isSelected()){ sl2 = "Si";}
    else{ sl2 = "No";}
    
    if(slc_editarp.isSelected()){ sl3 = "Si";}
    else{ sl3 = "No";}
    
    if(slc_tipofact.isSelected()){ sl4 = "Si";}
    else{ sl4 = "No";}
    
    if(slc_tipocompr.isSelected()){ sl5 = "Si";}
    else{ sl5 = "No";}
    
    if(slc_modifact.isSelected()){ sl6 = "Si";}
    else{ sl6 = "No";}
    
    if(slc_cotizacion.isSelected()){ sl7 = "Si";}
    else{ sl7 = "No";}
    
    if(slc_modificarc.isSelected()){ sl8 = "Si";}
     else{ sl8 = "No";} 
    
    if(slc_borrarc.isSelected()){ sl9 = "Si";}
     else{ sl9 = "No";}    

    if(slc_contrata.isSelected()){ sl10 = "Si";}
     else{ sl10 = "No";}
    
     if(slc_modificarcon.isSelected()){ sl11 = "Si";}
     else{ sl11 = "No";}
     
     if(slc_borrarcon.isSelected()){ sl12 = "Si";}
    else{ sl12 = "No";}
     
    if(slc_cliente.isSelected()){ sl13 = "Si";}
    else{ sl13 = "No";}
    
    if(slc_modificarclien.isSelected()){ sl14 = "Si";}
    else{ sl14 = "No";}
    
    if(slc_borrarclien1.isSelected()){ sl15 = "Si";}
    else{ sl15 = "No";}
    
    if(slc_servicios.isSelected()){ sl16 = "Si";}
    else{ sl16 = "No";}
    
    if(slc_modificarserv.isSelected()){ sl17 = "Si";}
    else{ sl17 = "No";}
    
    if(slc_borrarserv.isSelected()){ sl18 = "Si";}
    else{ sl18 = "No";}
    
    if(slc_usuario.isSelected()){ sl19 = "Si";}
     else{ sl19 = "No";} 
    
    if(slc_modificarusu.isSelected()){ sl20 = "Si";}
     else{ sl20 = "No";}    

    if(slc_borrarusu.isSelected()){ sl21 = "Si";}
     else{ sl21 = "No";}
    
     if(slc_comprobante.isSelected()){ sl22 = "Si";}
     else{ sl22 = "No";}
     
     if(slc_cxc.isSelected()){ sl23 = "Si";}
     else{ sl23 = "No";}
     
      if(slc_creativos.isSelected()){ sl24 = "Si";}
     else{ sl24 = "No";}
      
       if(slc_Gastos.isSelected()){ sl25 = "Si";}
     else{ sl25 = "No";}
       
        if(slc_modificarGasto.isSelected()){ sl26 = "Si";}
     else{ sl26 = "No";}
        
         if(slc_modificarcreativo.isSelected()){ sl27 = "Si";}
     else{ sl27 = "No";}
         
         if(slc_Graficas.isSelected()){ sl28 = "Si";}
     else{ sl28 = "No";}
      
  if(Estatus_usu.isSelected()){
            es="Activo";
            
        }else{
            es="Inactivo";
            
        }
    PreparedStatement psU2 = cn.prepareStatement("UPDATE usuario SET nombre_usu='"+Nombre_usu.getText()+"',usuario='"+usuario_usu.getText()+"',contra_usu='"+clave_usu.getText()+"',estatus='"+es+"',factura_m='"+sl1+"',descuento='"+sl2+"'"
           + ",edit_precio='"+sl3+"',tipo_fact='"+sl4+"',tipo_comp='"+sl5+"',modif_fact='"+sl6+"',cotizacion='"+sl7+"',modif_cot='"+sl8+"',borrar_cot='"+sl9+"',contrata='"+sl10+"',modif_cont='"+sl11+"',borrar_cont='"+sl12+"'"
           + ",cliente='"+sl13+"',modificar_cli='"+sl14+"',borrar_cli='"+sl15+"',servicios='"+sl16+"',modificar_ser='"+sl17+"',borar_serv='"+sl18+"',usuarios='"+sl19+"',modificar_usu='"+sl20+"',borrar_usu='"+sl21+"',comprobantes='"+sl22+"',"
           + "cxc='"+sl23+"', creativos ='"+sl24+"', gastos ='"+sl25+"', modif_gastos ='"+sl26+"', modificar_creativo ='"+sl27+"', graficas ='"+sl28+"' where id_usuario='"+codigo_usu.getText()+"'");
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
slc_tipofact.setEnabled(false);
slc_tipocompr.setEnabled(false);
slc_modifact.setEnabled(false);
slc_modificarc.setEnabled(false);
slc_borrarc.setEnabled(false);
slc_modificarcon.setEnabled(false);
slc_borrarcon.setEnabled(false);
slc_modificarclien.setEnabled(false);
slc_borrarclien1.setEnabled(false);
slc_modificarserv.setEnabled(false);
slc_borrarserv.setEnabled(false);
slc_modificarusu.setEnabled(false);
slc_borrarusu.setEnabled(false);
slc_comprobante.setEnabled(false);
slc_cxc.setEnabled(false);
slc_factura.setEnabled(false);
slc_cotizacion.setEnabled(false);
slc_contrata.setEnabled(false);
slc_cliente.setEnabled(false);
slc_servicios.setEnabled(false);
slc_usuario.setEnabled(false);
slc_tp.setEnabled(false);
slc_creativos.setEnabled(false);
slc_Gastos.setEnabled(false);
slc_modificarGasto.setEnabled(false);
slc_modificarcreativo.setEnabled(false);
slc_Graficas.setEnabled(false);
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
slc_tipofact.setEnabled(true);
slc_tipocompr.setEnabled(true);
slc_modifact.setEnabled(true);
slc_modificarc.setEnabled(true);
slc_borrarc.setEnabled(true);
slc_modificarcon.setEnabled(true);
slc_borrarcon.setEnabled(true);
slc_modificarclien.setEnabled(true);
slc_borrarclien1.setEnabled(true);
slc_modificarserv.setEnabled(true);
slc_borrarserv.setEnabled(true);
slc_modificarusu.setEnabled(true);
slc_borrarusu.setEnabled(true);
slc_comprobante.setEnabled(true);
slc_cxc.setEnabled(true);
slc_factura.setEnabled(true);
slc_cotizacion.setEnabled(true);
slc_contrata.setEnabled(true);
slc_cliente.setEnabled(true);
slc_servicios.setEnabled(true);
slc_usuario.setEnabled(true);
slc_tp.setEnabled(true);
slc_creativos.setEnabled(true);
slc_Gastos.setEnabled(true);
slc_modificarGasto.setEnabled(true);
slc_modificarcreativo.setEnabled(true);
slc_Graficas.setEnabled(true);
    } 
      conector cc = new conector();
    Connection cn = cc.conexion();  
}
