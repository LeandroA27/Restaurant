/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import static Clases.Clase_Variable_Publica.borrar_usu;
import Clases.funciones_usuarios;
import Clases.generador_numerico;
import Clases.render_tabla_usuario;
import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Usuarios extends javax.swing.JFrame {

int x,y;
Fuentes tipofuente;
    public Usuarios() {
        initComponents();coparacion();
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        setOpacity((float)1.0);
        setCellRender(tabla_usuario);
        tipo_letra();
        pintarColumna();
        clave_usu.setEchoChar('•'); 
         confir_clave_usu.setEchoChar('•'); 
        Nombre_usu.setNextFocusableComponent (usuario_usu);
        if(usuario_usu.isFocusable()){
         usuario_usu.setNextFocusableComponent (clave_usu);   
        }
        if(clave_usu.isFocusable()){
         clave_usu.setNextFocusableComponent (confir_clave_usu);   
        }
        
        Nombre_usu.requestFocus();
        Estatus_usu.setSelected(true);
        Activo_usu.setVisible(true);
        Inactivo_usu.setVisible(false);
        clave_usu.setEditable(false);
        confir_clave_usu.setText("");
        estado_proceso.setText("");
        modifica_usu.setEnabled(false);
        guardar_usu.setEnabled(false);
        limpiar_usu.setEnabled(false);
        Consultar_num_usu();
        limpiar_tabla();
        bloquear_usuario();
        try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes_Iconos/hoja-11.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    try {
        cargar();
    } catch (SQLException ex) {
        Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
  /////////////////////////////////////////////////////////
    void pintarColumna(){
    Clases.ColorearFilas obj = new  Clases.ColorearFilas(3);
    tabla_usuario.getColumnModel().getColumn(3).setCellRenderer(obj);

}
 ///////////////////////////////////////////////////////////
    public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new render_tabla_usuario());
        }
    }
    
 /////////////////////////////////////////////////////////
    public void tipo_letra(){
        tipofuente = new Fuentes();
        nombre_usu_usu.setFont(tipofuente.fuente(tipofuente.RIO,0,16));   
        Nombre_usu.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        clave_usu.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        usuario_usu.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        confir_clave_usu.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        Activo_usu.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Inactivo_usu.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        estado_proceso.setFont(tipofuente.fuente(tipofuente.RIO,0,15)); 
    }
 //////////////////////////////////////////////////////////   
     public void coparacion(){
    if(Clase_Variable_Publica.modificar_usu==1){
      modifica_usu.setEnabled(false);
    }else{}
    if(Clase_Variable_Publica.borrar_usu==1){
      //borrar_usu.setEnabled(false);
    }else{}

    }  
///////////////////////////////////////////////////////////////////////////////
    public void limpiar_tabla(){
    DefaultTableModel modelo3 =(DefaultTableModel) tabla_usuario.getModel();
    modelo3.getDataVector().clear();
    }
///////////////////////////////////////////////////////////////////////////////
//    public void Guardar(){
//     PreparedStatement cons = null;    
//    String obj;
//    if(Estatus_usu.isSelected()){
//        obj = "Activo";
//    }else{
//       obj = "Inactivo"; 
//    }
//    if(slc_factura.isSelected()){String sl1 = "Si";}
//    else{String sl1 = "No";}
//     
//    if(slc_descuento.isSelected()){String sl2 = "Si";}
//    else{String sl2 = "No";}
//    
//   try {
//           final String detalle ="INSERT INTO usuario(id_usuario,nombre_usu,usuario,contra_usu,estatus)"
//                    + "VALUES('"+cod_usu.getText()+"','"+Nombre_usu.getText()+"','"+usuario_usu.getText()+"','"+clave_usu.getText()+"','"+obj+"')";
//            cons = conexion.prepareStatement(detalle);
//            //ResultSet rs = cons.executeQuery();
//            
//            int n;
//            n = cons.executeUpdate();
//            if (n>0){
//                
//            }
//            JOptionPane.showMessageDialog(null, "El Usuario "+Nombre_usu.getText()+" Se ha guardado satisfactoriamente");
//            limpiar_usuario();
//}catch (SQLException e) {
//    System.out.print(e.getMessage());
//}     
//        }
//////////////////////////////////////////////////////////////////////////////////////////////////////
public void Modificar(){
        String es="";
        if(Estatus_usu.isSelected()){
            es="Activo";
            
        }else{
            es="Inactivo";
            
        }
       
        try{
            PreparedStatement psU2 = cn.prepareStatement("UPDATE usuario SET nombre_usu='"+Nombre_usu.getText()+"',contra_usu='"+usuario_usu.getText()+"',estatus='"+es+"' WHERE id_usuario='"+clave_usu.getText()+"'");
            psU2.executeUpdate();
            JOptionPane.showMessageDialog(null,"El Usuario "+Nombre_usu.getText()+" Fue Modificado");
            limpiar_usuario();
     }catch (SQLException ex){
         Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE,null, ex);
     } 
               
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
public void Borrar(){
        try{
            PreparedStatement psU2 = cn.prepareStatement("delete from usuario where id_usuario='"+codigo_usu.getText()+"'");
            psU2.executeUpdate();
            JOptionPane.showMessageDialog(null,"El Usuario "+Nombre_usu.getText()+" Fue Eliminado");
        limpiar_usuario();
            
     }catch (SQLException ex){
         Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE,null, ex);
     }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
void cargar_usuario() {
        String estado ="";
        String comparador ="";
        String[] registros = new String[4];
        String sql = "SELECT id_usuario,nombre_usu,contra_usu,estatus FROM usuario where id_usuario='"+clave_usu.getText()+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                registros[0] = rs.getString("id_usuario");
                registros[1] = rs.getString("nombre_usu");
                registros[2] = rs.getString("contra_usu");
                registros[3] = rs.getString("estatus");
                
                clave_usu.setText(registros[0]);
                Nombre_usu.setText(registros[1]);
                usuario_usu.setText(registros[2]);
                estado=registros[3];
                
                if(estado.equals("Activo")){
                    Estatus_usu.setSelected(true);
                    Activo_usu.setVisible(true);
                    Inactivo_usu.setVisible(false);
                    
                }else{
                    Estatus_usu.setSelected(false);
                    Inactivo_usu.setVisible(true);
                    Activo_usu.setVisible(false);   
                }
            }
            
                }catch(SQLException ex){
              JOptionPane.showMessageDialog(null, ex);
                    
        } 
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
void cargar() throws SQLException {
    String est="Activo";
    DefaultTableModel modelo2 =(DefaultTableModel) tabla_usuario.getModel();
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
       tabla_usuario.setModel(modelo2);
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
//////////////////////////////////////////////////////////////////////////////////////////////////////
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
                cod_usu.setText("00001");
                
            }else{
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var=Integer.parseInt(es);
                
            generador_numerico p = new generador_numerico();
            p.Generar_usu(var);
            
             cod_usu.setText(p.serie_cli());
            }
        } catch (Exception e) {
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////    
   public void limpiar_usuario(){
        clave_usu.setText("");
        Nombre_usu.setText("");
        usuario_usu.setText("");
        codigo_usu.setText("");
        confir_clave_usu.setText("");
        estado_proceso.setText("");
        Inactivo_usu.setVisible(false);
        Activo_usu.setVisible(true);
       Estatus_usu.setSelected(true);
            slc_descuento.setSelected(false);
            slc_editarp.setSelected(false);
            slc_tipocomprobante.setSelected(false);
            slc_modificarfact.setSelected(false);
            slc_exportar.setSelected(false);
            slc_modificarart.setSelected(false);
            slc_modificarusu.setSelected(false);
            slc_modificarusu.setSelected(false);
            slc_factura.setSelected(false);
            slc_inventario.setSelected(false);
            slc_articulo.setSelected(false);
            slc_despacho.setSelected(false);
            slc_usuario.setSelected(false);
            slc_usuario.setSelected(false);
            slc_delivery.setSelected(false);
            slc_modificardeliv.setSelected(false);
            slc_comprobantes.setSelected(false);
            slcBar.setSelected(false);

 Nombre_usu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.DARK_GRAY));
 usuario_usu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.DARK_GRAY));
clave_usu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.DARK_GRAY));
 
    }
////////////////////////////////////////////////////////////////////////////////////////////
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
    } 

void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar usuarios?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            this.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        codigo_usu = new javax.swing.JLabel();
        cod_usu = new javax.swing.JLabel();
        estado_proceso = new javax.swing.JLabel();
        volverAtras = new javax.swing.JButton();
        btn_minimizar = new javax.swing.JButton();
        slc_factura = new javax.swing.JCheckBox();
        slc_descuento = new javax.swing.JCheckBox();
        slc_editarp = new javax.swing.JCheckBox();
        slc_tipocomprobante = new javax.swing.JCheckBox();
        slc_modificarfact = new javax.swing.JCheckBox();
        slc_articulo = new javax.swing.JCheckBox();
        slc_modificarart = new javax.swing.JCheckBox();
        slc_inventario = new javax.swing.JCheckBox();
        slc_exportar = new javax.swing.JCheckBox();
        slc_delivery = new javax.swing.JCheckBox();
        slc_modificardeliv = new javax.swing.JCheckBox();
        slc_usuario = new javax.swing.JCheckBox();
        slc_modificarusu = new javax.swing.JCheckBox();
        slc_comprobantes = new javax.swing.JCheckBox();
        slc_despacho = new javax.swing.JCheckBox();
        slcRest = new javax.swing.JRadioButton();
        slcBar = new javax.swing.JRadioButton();
        slc_tp = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Nombre_usu = new javax.swing.JTextField();
        usuario_usu = new javax.swing.JTextField();
        clave_usu = new javax.swing.JPasswordField();
        confir_clave_usu = new javax.swing.JPasswordField();
        Activo_usu = new javax.swing.JLabel();
        Inactivo_usu = new javax.swing.JLabel();
        Estatus_usu = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_usuario = new javax.swing.JTable();
        guardar_usu = new javax.swing.JButton();
        limpiar_usu = new javax.swing.JButton();
        modifica_usu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nombre_usu_usu = new javax.swing.JLabel();

        cod_usu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cod_usu.setForeground(new java.awt.Color(255, 255, 255));

        estado_proceso.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        estado_proceso.setForeground(new java.awt.Color(165, 229, 128));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        volverAtras.setBorder(null);
        volverAtras.setContentAreaFilled(false);
        volverAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volverAtras.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                volverAtrasMouseMoved(evt);
            }
        });
        volverAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                volverAtrasMouseExited(evt);
            }
        });
        volverAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverAtrasActionPerformed(evt);
            }
        });
        getContentPane().add(volverAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 57, 30, 29));

        btn_minimizar.setToolTipText("Minimizar");
        btn_minimizar.setBorder(null);
        btn_minimizar.setContentAreaFilled(false);
        btn_minimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_minimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimizarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1212, 55, 30, 31));

        slc_factura.setBackground(new java.awt.Color(102, 102, 102));
        slc_factura.setForeground(new java.awt.Color(165, 229, 128));
        slc_factura.setContentAreaFilled(false);
        slc_factura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_facturaActionPerformed(evt);
            }
        });
        getContentPane().add(slc_factura, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 278, 90, 25));

        slc_descuento.setBackground(new java.awt.Color(102, 102, 102));
        slc_descuento.setForeground(new java.awt.Color(165, 229, 128));
        slc_descuento.setContentAreaFilled(false);
        slc_descuento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_descuentoActionPerformed(evt);
            }
        });
        getContentPane().add(slc_descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 298, 120, 25));

        slc_editarp.setBackground(new java.awt.Color(102, 102, 102));
        slc_editarp.setForeground(new java.awt.Color(165, 229, 128));
        slc_editarp.setContentAreaFilled(false);
        slc_editarp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_editarp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_editarpActionPerformed(evt);
            }
        });
        getContentPane().add(slc_editarp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 318, 130, 25));

        slc_tipocomprobante.setBackground(new java.awt.Color(102, 102, 102));
        slc_tipocomprobante.setForeground(new java.awt.Color(165, 229, 128));
        slc_tipocomprobante.setContentAreaFilled(false);
        slc_tipocomprobante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_tipocomprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_tipocomprobanteActionPerformed(evt);
            }
        });
        getContentPane().add(slc_tipocomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 339, 200, 25));

        slc_modificarfact.setBackground(new java.awt.Color(102, 102, 102));
        slc_modificarfact.setForeground(new java.awt.Color(165, 229, 128));
        slc_modificarfact.setContentAreaFilled(false);
        slc_modificarfact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_modificarfact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_modificarfactActionPerformed(evt);
            }
        });
        getContentPane().add(slc_modificarfact, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 359, 200, 25));

        slc_articulo.setBackground(new java.awt.Color(102, 102, 102));
        slc_articulo.setForeground(new java.awt.Color(165, 229, 128));
        slc_articulo.setContentAreaFilled(false);
        slc_articulo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_articuloActionPerformed(evt);
            }
        });
        getContentPane().add(slc_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(559, 278, 130, 25));

        slc_modificarart.setBackground(new java.awt.Color(102, 102, 102));
        slc_modificarart.setForeground(new java.awt.Color(165, 229, 128));
        slc_modificarart.setContentAreaFilled(false);
        slc_modificarart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_modificarart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_modificarartActionPerformed(evt);
            }
        });
        getContentPane().add(slc_modificarart, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 298, 100, 25));

        slc_inventario.setBackground(new java.awt.Color(102, 102, 102));
        slc_inventario.setForeground(new java.awt.Color(165, 229, 128));
        slc_inventario.setContentAreaFilled(false);
        slc_inventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_inventarioActionPerformed(evt);
            }
        });
        getContentPane().add(slc_inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(559, 339, 120, 25));

        slc_exportar.setBackground(new java.awt.Color(102, 102, 102));
        slc_exportar.setForeground(new java.awt.Color(165, 229, 128));
        slc_exportar.setContentAreaFilled(false);
        slc_exportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_exportarActionPerformed(evt);
            }
        });
        getContentPane().add(slc_exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 359, 100, 25));

        slc_delivery.setBackground(new java.awt.Color(102, 102, 102));
        slc_delivery.setForeground(new java.awt.Color(165, 229, 128));
        slc_delivery.setContentAreaFilled(false);
        slc_delivery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_delivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_deliveryActionPerformed(evt);
            }
        });
        getContentPane().add(slc_delivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 278, 90, 25));

        slc_modificardeliv.setBackground(new java.awt.Color(102, 102, 102));
        slc_modificardeliv.setForeground(new java.awt.Color(165, 229, 128));
        slc_modificardeliv.setContentAreaFilled(false);
        slc_modificardeliv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_modificardeliv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_modificardelivActionPerformed(evt);
            }
        });
        getContentPane().add(slc_modificardeliv, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 298, 110, 25));

        slc_usuario.setBackground(new java.awt.Color(102, 102, 102));
        slc_usuario.setForeground(new java.awt.Color(165, 229, 128));
        slc_usuario.setContentAreaFilled(false);
        slc_usuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_usuarioActionPerformed(evt);
            }
        });
        getContentPane().add(slc_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 339, 100, 25));

        slc_modificarusu.setBackground(new java.awt.Color(102, 102, 102));
        slc_modificarusu.setForeground(new java.awt.Color(165, 229, 128));
        slc_modificarusu.setContentAreaFilled(false);
        slc_modificarusu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_modificarusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_modificarusuActionPerformed(evt);
            }
        });
        getContentPane().add(slc_modificarusu, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 359, 100, 25));

        slc_comprobantes.setBackground(new java.awt.Color(102, 102, 102));
        slc_comprobantes.setForeground(new java.awt.Color(165, 229, 128));
        slc_comprobantes.setContentAreaFilled(false);
        slc_comprobantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_comprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_comprobantesActionPerformed(evt);
            }
        });
        getContentPane().add(slc_comprobantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 298, 150, 25));

        slc_despacho.setBackground(new java.awt.Color(102, 102, 102));
        slc_despacho.setForeground(new java.awt.Color(165, 229, 128));
        slc_despacho.setContentAreaFilled(false);
        slc_despacho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_despacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_despachoActionPerformed(evt);
            }
        });
        getContentPane().add(slc_despacho, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 278, 110, 25));

        buttonGroup1.add(slcRest);
        slcRest.setContentAreaFilled(false);
        getContentPane().add(slcRest, new org.netbeans.lib.awtextra.AbsoluteConstraints(992, 360, 70, 25));

        buttonGroup1.add(slcBar);
        slcBar.setContentAreaFilled(false);
        getContentPane().add(slcBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 360, 60, 25));

        slc_tp.setBackground(new java.awt.Color(102, 102, 102));
        slc_tp.setForeground(new java.awt.Color(165, 229, 128));
        slc_tp.setContentAreaFilled(false);
        slc_tp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slc_tp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slc_tpActionPerformed(evt);
            }
        });
        getContentPane().add(slc_tp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1099, 225, 190, 25));

        jPanel9.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 284, 15, 15));

        jPanel8.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 300, 15, 90));

        jPanel12.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 344, 15, 15));

        jPanel6.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 304, 15, 15));

        jPanel13.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 365, 15, 15));

        jPanel14.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 365, 15, 15));

        jPanel15.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(736, 345, 15, 15));

        jPanel17.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(926, 284, 15, 15));

        jPanel19.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1102, 230, 15, 15));

        jPanel10.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 304, 15, 15));

        jPanel24.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(736, 284, 15, 15));

        jPanel7.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 284, 15, 15));

        jPanel18.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(926, 304, 15, 15));

        jPanel20.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(926, 365, 15, 15));

        jPanel21.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(995, 365, 15, 15));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 179, 170, 20));

        Nombre_usu.setBackground(new java.awt.Color(228, 228, 228));
        Nombre_usu.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Nombre_usu.setForeground(new java.awt.Color(193, 14, 26));
        Nombre_usu.setBorder(null);
        Nombre_usu.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        Nombre_usu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Nombre_usuMouseClicked(evt);
            }
        });
        Nombre_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nombre_usuActionPerformed(evt);
            }
        });
        Nombre_usu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nombre_usuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Nombre_usuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Nombre_usuKeyTyped(evt);
            }
        });
        getContentPane().add(Nombre_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 210, 30));

        usuario_usu.setBackground(new java.awt.Color(228, 228, 228));
        usuario_usu.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        usuario_usu.setForeground(new java.awt.Color(193, 14, 26));
        usuario_usu.setBorder(null);
        usuario_usu.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        usuario_usu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuario_usuMouseClicked(evt);
            }
        });
        usuario_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuario_usuActionPerformed(evt);
            }
        });
        usuario_usu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuario_usuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuario_usuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuario_usuKeyTyped(evt);
            }
        });
        getContentPane().add(usuario_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 190, 30));

        clave_usu.setBackground(new java.awt.Color(228, 228, 228));
        clave_usu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        clave_usu.setForeground(new java.awt.Color(193, 14, 26));
        clave_usu.setBorder(null);
        clave_usu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clave_usuMouseClicked(evt);
            }
        });
        clave_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clave_usuActionPerformed(evt);
            }
        });
        clave_usu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clave_usuKeyTyped(evt);
            }
        });
        getContentPane().add(clave_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 132, 190, 30));

        confir_clave_usu.setBackground(new java.awt.Color(228, 228, 228));
        confir_clave_usu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        confir_clave_usu.setForeground(new java.awt.Color(193, 14, 26));
        confir_clave_usu.setBorder(null);
        confir_clave_usu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confir_clave_usuMouseClicked(evt);
            }
        });
        confir_clave_usu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                confir_clave_usuKeyTyped(evt);
            }
        });
        getContentPane().add(confir_clave_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 162, 190, 30));

        Activo_usu.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Activo_usu.setForeground(new java.awt.Color(0, 255, 0));
        Activo_usu.setText("Activo");
        getContentPane().add(Activo_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 128, -1, 30));

        Inactivo_usu.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Inactivo_usu.setForeground(new java.awt.Color(234, 98, 151));
        Inactivo_usu.setText("Inactivo");
        getContentPane().add(Inactivo_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 128, -1, 30));

        Estatus_usu.setBackground(new java.awt.Color(102, 102, 102));
        Estatus_usu.setForeground(new java.awt.Color(165, 229, 128));
        Estatus_usu.setContentAreaFilled(false);
        Estatus_usu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Estatus_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Estatus_usuActionPerformed(evt);
            }
        });
        getContentPane().add(Estatus_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 128, -1, 30));

        jScrollPane1.setBorder(null);
        jScrollPane1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N

        tabla_usuario.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        tabla_usuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "             Codigo", "Nombre", "Usuario", "                   Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_usuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabla_usuario.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tabla_usuario.setDragEnabled(true);
        tabla_usuario.setSelectionBackground(new java.awt.Color(128, 230, 121));
        tabla_usuario.setShowHorizontalLines(false);
        tabla_usuario.setShowVerticalLines(false);
        tabla_usuario.getTableHeader().setResizingAllowed(false);
        tabla_usuario.getTableHeader().setReorderingAllowed(false);
        tabla_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_usuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_usuario);
        if (tabla_usuario.getColumnModel().getColumnCount() > 0) {
            tabla_usuario.getColumnModel().getColumn(0).setResizable(false);
            tabla_usuario.getColumnModel().getColumn(0).setPreferredWidth(70);
            tabla_usuario.getColumnModel().getColumn(1).setResizable(false);
            tabla_usuario.getColumnModel().getColumn(1).setPreferredWidth(370);
            tabla_usuario.getColumnModel().getColumn(2).setPreferredWidth(200);
            tabla_usuario.getColumnModel().getColumn(3).setResizable(false);
            tabla_usuario.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 408, 950, 200));

        guardar_usu.setBorder(null);
        guardar_usu.setContentAreaFilled(false);
        guardar_usu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardar_usu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                guardar_usuMouseMoved(evt);
            }
        });
        guardar_usu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                guardar_usuMouseExited(evt);
            }
        });
        guardar_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_usuActionPerformed(evt);
            }
        });
        getContentPane().add(guardar_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 630, 120, 30));

        limpiar_usu.setBorder(null);
        limpiar_usu.setContentAreaFilled(false);
        limpiar_usu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        limpiar_usu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                limpiar_usuMouseMoved(evt);
            }
        });
        limpiar_usu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                limpiar_usuMouseExited(evt);
            }
        });
        limpiar_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar_usuActionPerformed(evt);
            }
        });
        getContentPane().add(limpiar_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 630, 120, 30));

        modifica_usu.setBorder(null);
        modifica_usu.setContentAreaFilled(false);
        modifica_usu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifica_usu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                modifica_usuMouseMoved(evt);
            }
        });
        modifica_usu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                modifica_usuMouseExited(evt);
            }
        });
        modifica_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifica_usuActionPerformed(evt);
            }
        });
        getContentPane().add(modifica_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 630, 120, 30));

        jLabel1.setBackground(java.awt.Color.gray);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Usuario.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, -1, 770));

        nombre_usu_usu.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        nombre_usu_usu.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(nombre_usu_usu, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 180, 170, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverAtrasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverAtrasMouseMoved
        //volverAtras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));         // TODO add your handling code here:
    }//GEN-LAST:event_volverAtrasMouseMoved

    private void volverAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverAtrasMouseExited
        //        volverAtras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(76, 76, 76)));             // TODO add your handling code here:
    }//GEN-LAST:event_volverAtrasMouseExited

    private void volverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverAtrasActionPerformed
        //Menu_Principal ob = new Menu_Principal();
        //ob.setVisible(true);
        //nombre_usu_cli.setText(nombre_usu_fac.getText());
        close();       // TODO add your handling code here:
    }//GEN-LAST:event_volverAtrasActionPerformed

    private void btn_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizarActionPerformed
        this.setState(Menu_Principal.ICONIFIED);          // TODO add your handling code here:
    }//GEN-LAST:event_btn_minimizarActionPerformed

    private void slc_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_facturaActionPerformed
        if(slc_factura.isSelected()){
            slc_descuento.setSelected(true);
            slc_editarp.setSelected(true);
            slc_tipocomprobante.setSelected(true);
            slc_modificarfact.setSelected(true);
        }else{
            slc_descuento.setSelected(false);
            slc_editarp.setSelected(false);
            slc_tipocomprobante.setSelected(false);
            slc_modificarfact.setSelected(false);
        }
    }//GEN-LAST:event_slc_facturaActionPerformed

    private void slc_descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_descuentoActionPerformed
        if(slc_descuento.isSelected()){
            slc_factura.setSelected(true);
        }
    }//GEN-LAST:event_slc_descuentoActionPerformed

    private void slc_editarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_editarpActionPerformed
        if(slc_editarp.isSelected()){
            slc_factura.setSelected(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_slc_editarpActionPerformed

    private void slc_tipocomprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_tipocomprobanteActionPerformed

        if(slc_tipocomprobante.isSelected()){
            slc_factura.setSelected(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_slc_tipocomprobanteActionPerformed

    private void slc_modificarfactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_modificarfactActionPerformed

        if(slc_modificarfact.isSelected()){
            slc_factura.setSelected(true);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_slc_modificarfactActionPerformed

    private void slc_articuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_articuloActionPerformed
        if(slc_articulo.isSelected()){
            slc_modificarart.setSelected(true);
        }else{
            slc_modificarart.setSelected(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_slc_articuloActionPerformed

    private void slc_modificarartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_modificarartActionPerformed
        if(slc_modificarart.isSelected()){
            slc_articulo.setSelected(true);
        }           // TODO add your handling code here:
    }//GEN-LAST:event_slc_modificarartActionPerformed

    private void slc_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_inventarioActionPerformed
        if(slc_inventario.isSelected()){
            slc_exportar.setSelected(true);
        }else{
            slc_exportar.setSelected(false);
        }
    }//GEN-LAST:event_slc_inventarioActionPerformed

    private void slc_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_exportarActionPerformed
        if(slc_exportar.isSelected()){
            slc_inventario.setSelected(true);
        }      // TODO add your handling code here:
    }//GEN-LAST:event_slc_exportarActionPerformed

    private void slc_deliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_deliveryActionPerformed
        if(slc_delivery.isSelected()){
            slc_modificardeliv.setSelected(true);
        }else{
            slc_modificardeliv.setSelected(false);
        }          // TODO add your handling code here:
    }//GEN-LAST:event_slc_deliveryActionPerformed

    private void slc_modificardelivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_modificardelivActionPerformed
        if(slc_modificardeliv.isSelected()){
            slc_delivery.setSelected(true);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_slc_modificardelivActionPerformed

    private void slc_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_usuarioActionPerformed
        if(slc_usuario.isSelected()){
            slc_modificarusu.setSelected(true);
        }else{
            slc_modificarusu.setSelected(false);
        }          // TODO add your handling code here:
    }//GEN-LAST:event_slc_usuarioActionPerformed

    private void slc_modificarusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_modificarusuActionPerformed
        if(slc_modificarusu.isSelected()){
            slc_usuario.setSelected(true);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_slc_modificarusuActionPerformed

    private void slc_despachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_despachoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_slc_despachoActionPerformed

    private void slc_comprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_comprobantesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_slc_comprobantesActionPerformed

    private void slc_tpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slc_tpActionPerformed
        if(slc_tp.isSelected()){
            slc_descuento.setSelected(true);
            slc_editarp.setSelected(true);
            slc_tipocomprobante.setSelected(true);
            slc_modificarfact.setSelected(true);
            slc_exportar.setSelected(true);
            slc_modificarart.setSelected(true);
            slc_modificarusu.setSelected(true);
            slc_modificarusu.setSelected(true);
            slc_factura.setSelected(true);
            slc_inventario.setSelected(true);
            slc_articulo.setSelected(true);
            slc_despacho.setSelected(true);
            slc_usuario.setSelected(true);
            slc_usuario.setSelected(true);
            slc_delivery.setSelected(true);
            slc_modificardeliv.setSelected(true);
            slc_comprobantes.setSelected(true);
            slcBar.setSelected(true);
            
        }else{
            slc_descuento.setSelected(false);
            slc_editarp.setSelected(false);
            slc_tipocomprobante.setSelected(false);
            slc_modificarfact.setSelected(false);
            slc_exportar.setSelected(false);
            slc_modificarart.setSelected(false);
            slc_modificarusu.setSelected(false);
            slc_modificarusu.setSelected(false);
            slc_factura.setSelected(false);
            slc_inventario.setSelected(false);
            slc_articulo.setSelected(false);
            slc_despacho.setSelected(false);
            slc_usuario.setSelected(false);
            slc_usuario.setSelected(false);
            slc_delivery.setSelected(false);
            slc_modificardeliv.setSelected(false);
            slc_comprobantes.setSelected(false);
            slcBar.setSelected(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_slc_tpActionPerformed

    private void Nombre_usuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Nombre_usuMouseClicked
        if(evt.getClickCount() == 1){
            if(evt.getButton()==MouseEvent.BUTTON1){
                if(Nombre_usu.getText().isEmpty() && usuario_usu.getText().isEmpty() && clave_usu.getText().isEmpty()){
                    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea crear un nuevo usuario?",
                        "Crear usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    desbloquear_usuario();
                    tabla_usuario.setEnabled(false);
                    codigo_usu.setText("");
                    Consultar_num_usu();
                    estado_proceso.setText("Creando Nuevo Usuario");
                    modifica_usu.setEnabled(false);
                    guardar_usu.setEnabled(true);
                    limpiar_usu.setEnabled(true);
                }
            }
        }
        }
        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_Nombre_usuMouseClicked

    private void Nombre_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nombre_usuActionPerformed
        usuario_usu.requestFocus();
        Nombre_usu.setNextFocusableComponent (usuario_usu);// TODO add your handling code here:
    }//GEN-LAST:event_Nombre_usuActionPerformed

    private void Nombre_usuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nombre_usuKeyPressed

        if(evt.getKeyCode()==KeyEvent.VK_UP){
            clave_usu.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            usuario_usu.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_Nombre_usuKeyPressed

    private void Nombre_usuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nombre_usuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Nombre_usuKeyReleased

    private void Nombre_usuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nombre_usuKeyTyped
        char c = evt.getKeyChar();
        if ((c<'a'|| c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') && (c!='.') &&(c!='@')
            &&(c!='#')&&(c!='!')&&(c!='$')&&(c!='%')&&(c!='&')&&(c!='?')&&(c!=',')&&(c!=':')&&(c!=';') && c!= com.sun.glass.events.KeyEvent.VK_SPACE) evt.consume();
    }//GEN-LAST:event_Nombre_usuKeyTyped

    private void usuario_usuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuario_usuMouseClicked
        if(evt.getClickCount() == 1){
            if(evt.getButton()==MouseEvent.BUTTON1){
                if(Nombre_usu.getText().isEmpty() && usuario_usu.getText().isEmpty() && clave_usu.getText().isEmpty()){
                    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea crear un nuevo usuario?",
                        "Crear usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    desbloquear_usuario();
                    tabla_usuario.setEnabled(false);
                    codigo_usu.setText("");
                    Consultar_num_usu();
                    estado_proceso.setText("Creando Nuevo Usuario");
                    modifica_usu.setEnabled(false);
                    guardar_usu.setEnabled(true);
                    limpiar_usu.setEnabled(true);
                }
            }
        }
        }        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_usuario_usuMouseClicked

    private void usuario_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuario_usuActionPerformed

        clave_usu.requestFocus();// TODO add your handling code here:
    }//GEN-LAST:event_usuario_usuActionPerformed

    private void usuario_usuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuario_usuKeyPressed

        if(evt.getKeyCode()==KeyEvent.VK_UP){
            Nombre_usu.requestFocus();
        }           // TODO add your handling code here:
    }//GEN-LAST:event_usuario_usuKeyPressed

    private void usuario_usuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuario_usuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_usuario_usuKeyReleased

    private void usuario_usuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuario_usuKeyTyped

        char c = evt.getKeyChar();
        if ((c<'a'|| c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') && (c!='.') &&(c!='@')
            &&(c!='#')&&(c!='!')&&(c!='$')&&(c!='%')&&(c!='&')&&(c!='?')&&(c!=',')&&(c!=':')&&(c!=';') && c!= com.sun.glass.events.KeyEvent.VK_SPACE) evt.consume();
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            confir_clave_usu.requestFocus();
        }// TODO add your handling code here:
    }//GEN-LAST:event_usuario_usuKeyTyped

    private void clave_usuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clave_usuMouseClicked
        if(evt.getClickCount() == 1){
            if(evt.getButton()==MouseEvent.BUTTON1){
                if(Nombre_usu.getText().isEmpty() && usuario_usu.getText().isEmpty() && clave_usu.getText().isEmpty()){
                    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea crear un nuevo usuario?",
                        "Crear usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    desbloquear_usuario();
                    tabla_usuario.setEnabled(false);
                    codigo_usu.setText("");
                    Consultar_num_usu();
                    estado_proceso.setText("Creando Nuevo Usuario");
                    modifica_usu.setEnabled(false);
                    guardar_usu.setEnabled(true);
                    limpiar_usu.setEnabled(true);
                }
            }
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_clave_usuMouseClicked

    private void clave_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clave_usuActionPerformed
        confir_clave_usu.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_clave_usuActionPerformed

    private void clave_usuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clave_usuKeyTyped
        char c = evt.getKeyChar();
        if ((c<'a'|| c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') && (c!='.') &&(c!='@')
            &&(c!='#')&&(c!='!')&&(c!='$')&&(c!='%')&&(c!='&')&&(c!='?')&&(c!=',')&&(c!=':')&&(c!=';') && c!= com.sun.glass.events.KeyEvent.VK_SPACE) evt.consume();
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            usuario_usu.requestFocus();
        }// TODO add your handling code here:
    }//GEN-LAST:event_clave_usuKeyTyped

    private void confir_clave_usuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confir_clave_usuMouseClicked
        if(evt.getClickCount() == 1){
            if(evt.getButton()==MouseEvent.BUTTON1){
                if(Nombre_usu.getText().isEmpty() && usuario_usu.getText().isEmpty() && clave_usu.getText().isEmpty()){
                    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea crear un nuevo usuario?",
                        "Crear usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    desbloquear_usuario();
                    tabla_usuario.setEnabled(false);
                    codigo_usu.setText("");
                    Consultar_num_usu();
                    estado_proceso.setText("Creando Nuevo Usuario");
                    modifica_usu.setEnabled(false);
                    guardar_usu.setEnabled(true);
                    limpiar_usu.setEnabled(true);
                }
            }
        }
        }       // TODO add your handling code here:
    }//GEN-LAST:event_confir_clave_usuMouseClicked

    private void confir_clave_usuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confir_clave_usuKeyTyped
        char c = evt.getKeyChar();
        if ((c<'a'|| c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') && (c!='.') &&(c!='@')
            &&(c!='#')&&(c!='!')&&(c!='$')&&(c!='%')&&(c!='&')&&(c!='?')&&(c!=',')&&(c!=':')&&(c!=';') && c!= com.sun.glass.events.KeyEvent.VK_SPACE) evt.consume();      // TODO add your handling code here:
    }//GEN-LAST:event_confir_clave_usuKeyTyped

    private void Estatus_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Estatus_usuActionPerformed
        String es_p = "";

        if(Estatus_usu.isSelected()){
            Activo_usu.setVisible(true);
            Inactivo_usu.setVisible(false);
        }else{
            Activo_usu.setVisible(false);
            Inactivo_usu.setVisible(true);
        }           // TODO add your handling code here:
    }//GEN-LAST:event_Estatus_usuActionPerformed

    private void tabla_usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_usuarioMouseClicked
        if(tabla_usuario.isEnabled()){
            String result="";
            String comparacion = "";

            int fila = tabla_usuario.getSelectedRow();
            if (fila>=0){
                codigo_usu.setText(tabla_usuario.getValueAt(fila,0).toString());
                Nombre_usu.setText(tabla_usuario.getValueAt(fila,1).toString());
                usuario_usu.setText(tabla_usuario.getValueAt(fila,2).toString());
                clave_usu.setText(tabla_usuario.getValueAt(fila,3).toString());
                result=(tabla_usuario.getValueAt(fila,3).toString());
                comparacion = tabla_usuario.getValueAt(fila,0).toString();
                modifica_usu.setEnabled(true);
                limpiar_usu.setEnabled(true);
                if(result.equals("Activo")){
                    Estatus_usu.setSelected(true);
                    Activo_usu.setVisible(true);
                    Inactivo_usu.setVisible(false);
                }else{
                    Estatus_usu.setSelected(false);
                    Activo_usu.setVisible(false);
                    Inactivo_usu.setVisible(true);
                }
                funciones_usuarios fu = new funciones_usuarios();
                fu.cargar_permisos();
                bloquear_usuario();

            }
        }else{}        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_usuarioMouseClicked

    private void guardar_usuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardar_usuMouseMoved
        if(guardar_usu.isEnabled()){
            // guardar_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }// TODO add your handling code here:
    }//GEN-LAST:event_guardar_usuMouseMoved

    private void guardar_usuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardar_usuMouseExited
        if(guardar_usu.isEnabled()){
            //guardar_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102,102,102)));
        }else{}// TODO add your handling code here:
    }//GEN-LAST:event_guardar_usuMouseExited

    private void guardar_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_usuActionPerformed
        if (codigo_usu.getText().isEmpty()){
            if(!Nombre_usu.getText().isEmpty() && !usuario_usu.getText().isEmpty() && !clave_usu.getText().isEmpty()){

                if(clave_usu.getText().equals(confir_clave_usu.getText())){

                    Consultar_num_usu();
                    funciones_usuarios fu = new funciones_usuarios();
                    try {
                        fu.conectar();
                    } catch (SQLException ex) {
                        Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Las claves no coinciden por favor ingresar la clave nuevamente");
                    confir_clave_usu.setText("");
                    clave_usu.setText("");
                    clave_usu.requestFocus();
                }

            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe de llenar todos los campos");
                if(Nombre_usu.getText().isEmpty()){
                    Nombre_usu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
                }
                if(usuario_usu.getText().isEmpty()){
                    usuario_usu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
                }
                if(clave_usu.getText().isEmpty()){
                    clave_usu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
                }
            }

        }

        if(!codigo_usu.getText().isEmpty()){
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente modificar el usuario?",
                "Modificar Usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(!Nombre_usu.getText().isEmpty() && !usuario_usu.getText().isEmpty() && !clave_usu.getText().isEmpty()){

                if(clave_usu.getText().equals(confir_clave_usu.getText())){
                    funciones_usuarios fnu= new funciones_usuarios();
                    try {
                        fnu.conectar_modif();
                    } catch (SQLException ex) {
                        Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //                Consultar_num_usu();
                    //                limpiar_tabla();
                    //                try {
                        //                    cargar();
                        //                } catch (SQLException ex) {
                        //                    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                        //                }
                }else{
                    JOptionPane.showMessageDialog(null, "Las claves no coiciden por favor ingresala nuevamente");
                    confir_clave_usu.setText("");
                    clave_usu.setText("");
                    clave_usu.requestFocus();
                }

            }else{
                if(Nombre_usu.getText().isEmpty()){
                    Nombre_usu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
                }
                if(usuario_usu.getText().isEmpty()){
                    usuario_usu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
                }
                if(clave_usu.getText().isEmpty()){
                    clave_usu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
                }
            }
        }

        }         // TODO add your handling code here:
    }//GEN-LAST:event_guardar_usuActionPerformed

    private void limpiar_usuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiar_usuMouseMoved
        if(limpiar_usu.isEnabled()){
            //limpiar_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }// TODO add your handling code here:
    }//GEN-LAST:event_limpiar_usuMouseMoved

    private void limpiar_usuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiar_usuMouseExited
        if(limpiar_usu.isEnabled()){
            //limpiar_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102,102,102)));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_limpiar_usuMouseExited

    private void limpiar_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar_usuActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente limpiar los campos?",
            "limpiar Usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
        limpiar_usuario();
        bloquear_usuario();
        modifica_usu.setEnabled(false);
        guardar_usu.setEnabled(false);
        limpiar_usu.setEnabled(false);
        //limpiar_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102,102,102)));
        tabla_usuario.setEnabled(true);
        Consultar_num_usu();
        //limpiar_tabla();
        //clave_usu.setEditable(false);
        //            try {
            //                cargar();
            //            } catch (SQLException ex) {
            //                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            //            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_limpiar_usuActionPerformed

    private void modifica_usuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifica_usuMouseMoved
        if(modifica_usu.isEnabled()){
            if(Clase_Variable_Publica.modificar_usu==0){
                //modifica_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
            }else{}
        }else{}        // TODO add your handling code here:
    }//GEN-LAST:event_modifica_usuMouseMoved

    private void modifica_usuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifica_usuMouseExited
        if(modifica_usu.isEnabled()){
            //modifica_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102,102,102)));
        }// TODO add your handling code here:
    }//GEN-LAST:event_modifica_usuMouseExited

    private void modifica_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifica_usuActionPerformed
        if(Clase_Variable_Publica.modificar_usu==0){
            if(!Nombre_usu.getText().isEmpty() && !usuario_usu.getText().isEmpty() && !clave_usu.getText().isEmpty()){
                if(estado_proceso.getText().equals("Creando Nuevo Usuario")){

                }else{
                    desbloquear_usuario();
                    guardar_usu.setEnabled(true);
                    limpiar_usu.setEnabled(true);
                    modifica_usu.setEnabled(false);
                    // modifica_usu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102,102,102)));
                    tabla_usuario.setEnabled(false);
                    estado_proceso.setText("Modificando Usuario");
                    Nombre_usu.requestFocus();
                }
            }else{JOptionPane.showMessageDialog(rootPane, "Primero debe de seleccionar el usuario para modificar");}
        }else{JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para modificar");}        // TODO add your handling code here:
    }//GEN-LAST:event_modifica_usuActionPerformed

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
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel Activo_usu;
    public static javax.swing.JCheckBox Estatus_usu;
    public static javax.swing.JLabel Inactivo_usu;
    public static javax.swing.JTextField Nombre_usu;
    private javax.swing.JButton btn_minimizar;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JPasswordField clave_usu;
    public static javax.swing.JLabel cod_usu;
    public static javax.swing.JLabel codigo_usu;
    public static javax.swing.JPasswordField confir_clave_usu;
    public static javax.swing.JLabel estado_proceso;
    public static javax.swing.JButton guardar_usu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JButton limpiar_usu;
    public static javax.swing.JButton modifica_usu;
    public static javax.swing.JLabel nombre_usu_usu;
    public static javax.swing.JRadioButton slcBar;
    public static javax.swing.JRadioButton slcRest;
    public static javax.swing.JCheckBox slc_articulo;
    public static javax.swing.JCheckBox slc_comprobantes;
    public static javax.swing.JCheckBox slc_delivery;
    public static javax.swing.JCheckBox slc_descuento;
    public static javax.swing.JCheckBox slc_despacho;
    public static javax.swing.JCheckBox slc_editarp;
    public static javax.swing.JCheckBox slc_exportar;
    public static javax.swing.JCheckBox slc_factura;
    public static javax.swing.JCheckBox slc_inventario;
    public static javax.swing.JCheckBox slc_modificarart;
    public static javax.swing.JCheckBox slc_modificardeliv;
    public static javax.swing.JCheckBox slc_modificarfact;
    public static javax.swing.JCheckBox slc_modificarusu;
    public static javax.swing.JCheckBox slc_tipocomprobante;
    public static javax.swing.JCheckBox slc_tp;
    public static javax.swing.JCheckBox slc_usuario;
    public static javax.swing.JTable tabla_usuario;
    public static javax.swing.JTextField usuario_usu;
    private javax.swing.JButton volverAtras;
    // End of variables declaration//GEN-END:variables
conector cc = new conector();
    Connection cn = cc.conexion();
}
