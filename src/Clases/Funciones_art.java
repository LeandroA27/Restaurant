/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import Formularios.Articulo;
import static Formularios.Articulo.*;
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
import restaurante.conector;

/**
 *
 * @author Thealex7
 */
public class Funciones_art {
    
        public void Guardar(){
    String obj = null;
    if(slcBar_art.isSelected()){
        obj = "Bar";
    }else{
       obj = "Restaurante"; 
    }
        
   try {
            String sql ="INSERT INTO articulo(cod_art,descripcion,suplidor,marca,ubicacion,costo,ganancia,porciento,precio_sin,precio,oferta,"
                + "existencia,maximo,minimo,reorden,tipo)"
                + "VALUES('"+Cod_art.getText()+"','"+Descrip_inv.getText()+"','"+Suplidor_inv.getText()+"','"+Marca_inv.getText()+"','"+obj+"','"+Costo_inv.getText()+"','"+Ganancia_inv.getText()+"','"+Porciento_inv.getText()+"','"+Preciosin_inv.getText()+"',"
                    + "'"+Precio_inv.getText()+"','"+PrecioOferta_inv.getText()+"','"+Existencia_inv.getText()+"','"+Maximo_inv.getText()+"','"+Minimo_inv.getText()+"','"+Reorden_inv.getText()+"','"+Tipo_articulo.getSelectedItem()+"')";
            PreparedStatement pat = cn.prepareStatement(sql);
            
            int n;
            n = pat.executeUpdate();
            if (n>0){
                
            }
            JOptionPane.showMessageDialog(null, "El articulo "+Descrip_inv.getText()+" Se ha guardado satisfactoriamente");
            limpiar();
            desbloquear();
            Consultar_num_usu();
            Descrip_inv.requestFocus();
}catch (SQLException e) {
    System.out.print(e.getMessage());
}     
        }
        
public void limpiar(){
    Cocdigo_inv.setText("");
    Descrip_inv.setText("");
    Marca_inv.setText("");
    Suplidor_inv.setText("");
    Costo_inv.setText("");
    Ganancia_inv.setText("");
    Porciento_inv.setText("");
    Preciosin_inv.setText("");
    Precio_inv.setText("");
    PrecioOferta_inv.setText("");
    Existencia_inv.setText("");
    Maximo_inv.setText("");
    Minimo_inv.setText("");
    Reorden_inv.setText("");
    cod_art_modif.setText("");
    Ganancia_inv.setText("0.00");
    Porciento_inv.setText("0.00");
    PrecioOferta_inv.setText("0.00");
    Descrip_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Marca_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Suplidor_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Costo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Ganancia_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Porciento_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Preciosin_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Precio_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    PrecioOferta_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Existencia_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Maximo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Minimo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Reorden_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
    Maximo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED)); 
    Azul_pnl.setVisible(false);
    Verde_pnl.setVisible(false);
    Amarillo_pnl.setVisible(false);
    Naranja_pnl.setVisible(false);
    Rojo_pnl.setVisible(false);
}
public void desbloquear(){
    slcBar_art.setEnabled(true);
    Cocdigo_inv.setEditable(true);
    Descrip_inv.setEditable(true);
    Marca_inv.setEditable(true);
    Suplidor_inv.setEditable(true);
    Costo_inv.setEditable(true);
    Ganancia_inv.setEditable(true);
    Porciento_inv.setEditable(true);
    Preciosin_inv.setEditable(true);
    Precio_inv.setEditable(true);
    PrecioOferta_inv.setEditable(true);
    Existencia_inv.setEditable(true);
    Maximo_inv.setEditable(true);
    Minimo_inv.setEditable(true);
    Reorden_inv.setEditable(true);
    slcRest_art.setEnabled(true);
}
public void desbloquear1(){
    slcBar_art.setEnabled(true);
    Cocdigo_inv.setEditable(false);
    Descrip_inv.setEditable(true);
    Marca_inv.setEditable(true);
    Suplidor_inv.setEditable(true);
    Costo_inv.setEditable(true);
    Ganancia_inv.setEditable(true);
    Porciento_inv.setEditable(true);
    Preciosin_inv.setEditable(true);
    Precio_inv.setEditable(true);
    PrecioOferta_inv.setEditable(true);
    Existencia_inv.setEditable(true);
    Maximo_inv.setEditable(true);
    Minimo_inv.setEditable(true);
    Reorden_inv.setEditable(true);
    slcRest_art.setEnabled(true);
}
public void Consultar_num_usu(){
        int l;
        int cont=1;
        String num="";
        String es="";
        String SQL="SELECT MAX(cod_art) AS cod_art FROM articulo";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){
                es=rs.getString(1);
            }
            
            if(es==null){
                Cod_art.setText("00001");
                Cocdigo_inv.setText("00001");
                
            }else{
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var=Integer.parseInt(es);
                
            generador_numerico p = new generador_numerico();
            p.Generar_usu(var);
            
             Cod_art.setText(p.serie_inv());
             Cocdigo_inv.setText(p.serie_inv());
            }
        } catch (Exception e) {
        }
    }
public void Modificar(){
    String est; 
    if(slcBar_art.isSelected()){
      est = "Bar"; 
    }else{
       est = "Restaurante";  
            }
           try{
            PreparedStatement psU2 = cn.prepareStatement("UPDATE articulo SET descripcion='"+Descrip_inv.getText()+"',suplidor='"+Suplidor_inv.getText()+"',marca='"+Marca_inv.getText()+"',ubicacion='"+est+"',costo='"+Costo_inv.getText()+"',ganancia='"+Ganancia_inv.getText()+"',porciento='"+Porciento_inv.getText()+"',precio_sin='"+Preciosin_inv.getText()+"',precio='"+Precio_inv.getText()+"',oferta='"+PrecioOferta_inv.getText()+"',existencia='"+Existencia_inv.getText()+"',maximo='"+Maximo_inv.getText()+"',minimo='"+Minimo_inv.getText()+"',reorden='"+Reorden_inv.getText()+"',tipo='"+Tipo_articulo.getSelectedItem()+"' where cod_art='"+cod_art_modif.getText()+"'");
            psU2.executeUpdate();
             JOptionPane.showMessageDialog(null, "El articulo "+Descrip_inv.getText()+" Se ha modificado satisfactoriamente");
    limpiar();
    desbloquear();
    Consultar_num_usu();  
    Descrip_inv.requestFocus();
     }catch (SQLException ex){
         Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE,null, ex);
     }
    
}
    public void Borrar(){
        try{
                PreparedStatement psU2 = cn.prepareStatement("delete from inventario where codigo_inv='"+cod_art_modif.getText()+"'");
                psU2.executeUpdate();
                JOptionPane.showMessageDialog(null, "El producto "+Descrip_inv.getText()+" Se ha eliminado satisfactoriamente");
                limpiar();
                desbloquear();
                Consultar_num_usu();  
                Descrip_inv.requestFocus();
                }catch (SQLException ex){
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE,null, ex);
                        }
    }
    conector cc = new conector();
    Connection cn = cc.conexion();
}
