/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.Funciones_art;
import Clases.generador_numerico;
import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Articulo extends javax.swing.JFrame {

Fuentes tipofuente;
    public Articulo() {
        initComponents();
        tipo_letra();
        set_valor();
        Consultar_num_usu();
        Descrip_inv.requestFocus();
         Azul_pnl.setVisible(false);
        Verde_pnl.setVisible(false);
        Amarillo_pnl.setVisible(false);
        Naranja_pnl.setVisible(false);
        Rojo_pnl.setVisible(false);
        slcRest_art.setSelected(true);
        AWTUtilities.setWindowOpaque(this, false);
        setLocationRelativeTo(null);
        try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////
public void Cargar() 
    {
        String es="";
       
        
        String[] registros = new String[16];
        String sql = "SELECT cod_art,descripcion,suplidor,marca,ubicacion,costo,ganancia,porciento,precio_sin,precio,oferta,"
                + "existencia,maximo,minimo,reorden,tipo FROM articulo where cod_art='"+Cocdigo_inv.getText()+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                registros[0] = rs.getString("cod_art");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("suplidor");
                registros[3] = rs.getString("marca");
                registros[4] = rs.getString("ubicacion");
                registros[5] = rs.getString("costo");
                registros[6] = rs.getString("ganancia");
                registros[7] = rs.getString("porciento");
                registros[8] = rs.getString("precio_sin");
                registros[9] = rs.getString("precio");
                registros[10] = rs.getString("oferta");
                registros[11] = rs.getString("existencia");
                registros[12] = rs.getString("maximo");
                registros[13] = rs.getString("minimo");
                registros[14] = rs.getString("reorden");
                registros[15] = rs.getString("tipo");
                
                cod_art_modif.setText(registros[0]);
                Descrip_inv.setText(registros[1]);
                Suplidor_inv.setText(registros[2]);
                Marca_inv.setText(registros[3]);
                Costo_inv.setText(registros[5]);
                Ganancia_inv.setText(registros[6]);
                Porciento_inv.setText(registros[7]);
                Preciosin_inv.setText(registros[8]);
                Precio_inv.setText(registros[9]);
                PrecioOferta_inv.setText(registros[10]);
                Existencia_inv.setText(registros[11]);
                Maximo_inv.setText(registros[12]);
                Minimo_inv.setText(registros[13]);
                Reorden_inv.setText(registros[14]);
                Tipo_articulo.setSelectedItem(registros[15]);
                
                 es = registros[4];
                if(es.equals("Bar")){
                slcBar_art.setSelected(true);
                }
                if(es.equals("Restaurante")){
                slcRest_art.setSelected(true);
                }
                
               
            int valor1;
            int valor2;
            int valor3;
            int valor4;
            
            valor1 = Integer.parseInt(Existencia_inv.getText());
            valor2 = Integer.parseInt(Maximo_inv.getText());
            valor3 = Integer.parseInt(Minimo_inv.getText());
            valor4 = Integer.parseInt(Reorden_inv.getText());
            
            if(valor1 <= 0){
                Azul_pnl.setVisible(false);
                Verde_pnl.setVisible(false);
                Amarillo_pnl.setVisible(false);
                Naranja_pnl.setVisible(false);
                Rojo_pnl.setVisible(true);
            }
            if(valor1 >= valor2){
                Azul_pnl.setVisible(true);
                Verde_pnl.setVisible(false);
                Amarillo_pnl.setVisible(false);
                Naranja_pnl.setVisible(false);
                Rojo_pnl.setVisible(false);
            }
                if((valor1 < valor2) && (valor1 > valor3)){
                Azul_pnl.setVisible(false);
                Verde_pnl.setVisible(true);
                Amarillo_pnl.setVisible(false);
                Naranja_pnl.setVisible(false);
                Rojo_pnl.setVisible(false);
            }
                if(valor1 == valor4){
                Azul_pnl.setVisible(false);
                Verde_pnl.setVisible(false);
                Amarillo_pnl.setVisible(true);
                Naranja_pnl.setVisible(false);
                Rojo_pnl.setVisible(false);
            }
               if((valor1 < valor4) && (valor1 > 0)){
                Azul_pnl.setVisible(false);
                Verde_pnl.setVisible(false);
                Amarillo_pnl.setVisible(false);
                Naranja_pnl.setVisible(true);
                Rojo_pnl.setVisible(false);
            } 
            
                }          
        }catch(SQLException ex){
              JOptionPane.showMessageDialog(null, ex);
                    
        } 
    
    }
///////////////////////////////////////////////////////////////////////////////////////
public void set_valor(){
    Ganancia_inv.setText("0.00");
    Porciento_inv.setText("0.00");
    PrecioOferta_inv.setText("0.00");  
    Preciosin_inv.setText("0.00");
    Precio_inv.setText("0.00");
}
//////////////////////////////////////////////////////////////////////////////////////
public void bloquear(){
    slcRest_art.setEnabled(false);
    Cocdigo_inv.setEditable(true);
    Descrip_inv.setEditable(false);
    Marca_inv.setEditable(false);
    Suplidor_inv.setEditable(false);
    Costo_inv.setEditable(false);
    Ganancia_inv.setEditable(false);
    Porciento_inv.setEditable(false);
    Preciosin_inv.setEditable(false);
    Precio_inv.setEditable(false);
    PrecioOferta_inv.setEditable(false);
    Existencia_inv.setEditable(false);
    Maximo_inv.setEditable(false);
    Minimo_inv.setEditable(false);
    Reorden_inv.setEditable(false);
    slcBar_art.setEnabled(false);
}
/////////////////////////////////////////////////////////////////////////////////////////
public void desbloquear(){
    slcRest_art.setEnabled(true);
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
     slcBar_art.setEnabled(true);
}
public void desbloquear1(){
    slcRest_art.setEnabled(true);
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
    slcBar_art.setEnabled(true);
}
///////////////////////////////////////////////////////////////////////////////////
public void confirmar(){
    PreparedStatement consul = null;
      if(Cocdigo_inv.getText().isEmpty()){
      JOptionPane.showMessageDialog(null, "Debe Ingresar el codigo del articulo");
        }else{
            String cont ="";
            String comp = "";
            String es="";
            int compar = 0;
            int cant = 0;
            comp = Cocdigo_inv.getText();
            String[] registros = new String[2];
            try {
            final String detalle = "SELECT cod_art FROM articulo where cod_art='"+Cocdigo_inv.getText()+"'";
            consul = cn.prepareStatement(detalle);
            ResultSet rs = consul.executeQuery();
            
            if(rs.next()){
                registros[0] = rs.getString("cod_art");
                es=registros[0];
            }
            if(es.isEmpty()){
               JOptionPane.showMessageDialog(null,"Este articulo no existe");
               Cocdigo_inv.setText("");
                
            }else{
               Cargar();
               bloquear();
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);

            }
           }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////
public void set_valores(){
    if( Ganancia_inv.getText().isEmpty()){Ganancia_inv.setText("0.00");}
    if( Porciento_inv.getText().isEmpty()){Porciento_inv.setText("0.00");}
    if( PrecioOferta_inv.getText().isEmpty()){PrecioOferta_inv.setText("0.00");}
    if( Preciosin_inv.getText().isEmpty()){Preciosin_inv.setText("0.00");}
    if( Precio_inv.getText().isEmpty()){Precio_inv.setText("0.00");}
    if( Marca_inv.getText().isEmpty()){Marca_inv.setText("Sin Marca");}
    if( Suplidor_inv.getText().isEmpty()){Suplidor_inv.setText("Sin Suplidor");}
} 

void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar articulos?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            this.dispose();
    }
    public void tipo_letra(){
        tipofuente = new Fuentes();
        nombre_usu_art.setFont(tipofuente.fuente(tipofuente.RIO,0,16));   
        Cocdigo_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        Descrip_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        Suplidor_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        Marca_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        Costo_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Ganancia_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Porciento_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Preciosin_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Precio_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        PrecioOferta_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Existencia_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Maximo_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Minimo_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Reorden_inv.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        Tipo_articulo.setFont(tipofuente.fuente(tipofuente.RIO,0,16));
         
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cod_art = new javax.swing.JLabel();
        cod_art_modif = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        btn_minimizar = new javax.swing.JButton();
        volverAtras = new javax.swing.JButton();
        volver = new javax.swing.JButton();
        Descrip_inv = new javax.swing.JTextField();
        Marca_inv = new javax.swing.JTextField();
        Cocdigo_inv = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Suplidor_inv = new javax.swing.JTextField();
        Costo_inv = new javax.swing.JTextField();
        Ganancia_inv = new javax.swing.JTextField();
        Porciento_inv = new javax.swing.JTextField();
        Preciosin_inv = new javax.swing.JTextField();
        Precio_inv = new javax.swing.JTextField();
        PrecioOferta_inv = new javax.swing.JTextField();
        Existencia_inv = new javax.swing.JTextField();
        Maximo_inv = new javax.swing.JTextField();
        Minimo_inv = new javax.swing.JTextField();
        Reorden_inv = new javax.swing.JTextField();
        Guardar_inv = new javax.swing.JButton();
        Modificar_inv = new javax.swing.JButton();
        Limpioar_inv = new javax.swing.JButton();
        Azul_pnl = new javax.swing.JPanel();
        Verde_pnl = new javax.swing.JPanel();
        Amarillo_pnl = new javax.swing.JPanel();
        Naranja_pnl = new javax.swing.JPanel();
        Rojo_pnl = new javax.swing.JPanel();
        slcBar_art = new javax.swing.JRadioButton();
        slcRest_art = new javax.swing.JRadioButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        nombre_usu_art = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Btnfacturacion = new javax.swing.JButton();
        Btndespacho = new javax.swing.JButton();
        Btninventario = new javax.swing.JButton();
        Btndelivery = new javax.swing.JButton();
        Btncomprobantes = new javax.swing.JButton();
        Btnusuario = new javax.swing.JButton();
        Tipo_articulo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        volver.setBorder(null);
        volver.setContentAreaFilled(false);
        volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volver.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                volverMouseMoved(evt);
            }
        });
        volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                volverMouseExited(evt);
            }
        });
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        getContentPane().add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(1281, 70, 30, 30));

        Descrip_inv.setBackground(new java.awt.Color(228, 228, 228));
        Descrip_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Descrip_inv.setForeground(new java.awt.Color(198, 54, 53));
        Descrip_inv.setBorder(null);
        Descrip_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Descrip_invActionPerformed(evt);
            }
        });
        Descrip_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Descrip_invKeyTyped(evt);
            }
        });
        getContentPane().add(Descrip_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 162, 255, 25));

        Marca_inv.setBackground(new java.awt.Color(228, 228, 228));
        Marca_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Marca_inv.setForeground(new java.awt.Color(198, 54, 53));
        Marca_inv.setBorder(null);
        Marca_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Marca_invActionPerformed(evt);
            }
        });
        Marca_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Marca_invKeyTyped(evt);
            }
        });
        getContentPane().add(Marca_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 162, 190, 25));

        Cocdigo_inv.setBackground(new java.awt.Color(228, 228, 228));
        Cocdigo_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Cocdigo_inv.setForeground(new java.awt.Color(198, 54, 53));
        Cocdigo_inv.setBorder(null);
        Cocdigo_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cocdigo_invMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Cocdigo_invMouseEntered(evt);
            }
        });
        Cocdigo_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cocdigo_invActionPerformed(evt);
            }
        });
        Cocdigo_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Cocdigo_invKeyTyped(evt);
            }
        });
        getContentPane().add(Cocdigo_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 132, 110, 25));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 30, 25));

        Suplidor_inv.setBackground(new java.awt.Color(228, 228, 228));
        Suplidor_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Suplidor_inv.setForeground(new java.awt.Color(198, 54, 53));
        Suplidor_inv.setBorder(null);
        Suplidor_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Suplidor_invActionPerformed(evt);
            }
        });
        Suplidor_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Suplidor_invKeyTyped(evt);
            }
        });
        getContentPane().add(Suplidor_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 132, 170, 25));

        Costo_inv.setBackground(new java.awt.Color(228, 228, 228));
        Costo_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Costo_inv.setForeground(new java.awt.Color(198, 54, 53));
        Costo_inv.setBorder(null);
        Costo_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Costo_invMouseClicked(evt);
            }
        });
        Costo_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Costo_invActionPerformed(evt);
            }
        });
        Costo_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Costo_invKeyTyped(evt);
            }
        });
        getContentPane().add(Costo_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 223, 180, 25));

        Ganancia_inv.setBackground(new java.awt.Color(228, 228, 228));
        Ganancia_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Ganancia_inv.setForeground(new java.awt.Color(198, 54, 53));
        Ganancia_inv.setBorder(null);
        Ganancia_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ganancia_invMouseClicked(evt);
            }
        });
        Ganancia_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ganancia_invActionPerformed(evt);
            }
        });
        Ganancia_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Ganancia_invKeyTyped(evt);
            }
        });
        getContentPane().add(Ganancia_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 263, 90, 25));

        Porciento_inv.setBackground(new java.awt.Color(228, 228, 228));
        Porciento_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Porciento_inv.setForeground(new java.awt.Color(198, 54, 53));
        Porciento_inv.setBorder(null);
        Porciento_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Porciento_invMouseClicked(evt);
            }
        });
        Porciento_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Porciento_invActionPerformed(evt);
            }
        });
        Porciento_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Porciento_invKeyTyped(evt);
            }
        });
        getContentPane().add(Porciento_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 263, 120, 25));

        Preciosin_inv.setBackground(new java.awt.Color(228, 228, 228));
        Preciosin_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Preciosin_inv.setForeground(new java.awt.Color(198, 54, 53));
        Preciosin_inv.setBorder(null);
        Preciosin_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Preciosin_invMouseClicked(evt);
            }
        });
        Preciosin_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Preciosin_invKeyTyped(evt);
            }
        });
        getContentPane().add(Preciosin_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 304, 120, 25));

        Precio_inv.setBackground(new java.awt.Color(228, 228, 228));
        Precio_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Precio_inv.setForeground(new java.awt.Color(198, 54, 53));
        Precio_inv.setBorder(null);
        Precio_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Precio_invMouseClicked(evt);
            }
        });
        Precio_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Precio_invActionPerformed(evt);
            }
        });
        Precio_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Precio_invKeyTyped(evt);
            }
        });
        getContentPane().add(Precio_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 346, 160, 25));

        PrecioOferta_inv.setBackground(new java.awt.Color(228, 228, 228));
        PrecioOferta_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        PrecioOferta_inv.setForeground(new java.awt.Color(198, 54, 53));
        PrecioOferta_inv.setBorder(null);
        PrecioOferta_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrecioOferta_invMouseClicked(evt);
            }
        });
        PrecioOferta_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecioOferta_invActionPerformed(evt);
            }
        });
        PrecioOferta_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PrecioOferta_invKeyTyped(evt);
            }
        });
        getContentPane().add(PrecioOferta_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 385, 160, 25));

        Existencia_inv.setBackground(new java.awt.Color(228, 228, 228));
        Existencia_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Existencia_inv.setForeground(new java.awt.Color(198, 54, 53));
        Existencia_inv.setBorder(null);
        Existencia_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Existencia_invMouseClicked(evt);
            }
        });
        Existencia_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Existencia_invActionPerformed(evt);
            }
        });
        Existencia_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Existencia_invKeyTyped(evt);
            }
        });
        getContentPane().add(Existencia_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 224, 160, 25));

        Maximo_inv.setBackground(new java.awt.Color(228, 228, 228));
        Maximo_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Maximo_inv.setForeground(new java.awt.Color(198, 54, 53));
        Maximo_inv.setBorder(null);
        Maximo_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Maximo_invMouseClicked(evt);
            }
        });
        Maximo_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Maximo_invActionPerformed(evt);
            }
        });
        Maximo_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Maximo_invKeyTyped(evt);
            }
        });
        getContentPane().add(Maximo_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 264, 160, 25));

        Minimo_inv.setBackground(new java.awt.Color(228, 228, 228));
        Minimo_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Minimo_inv.setForeground(new java.awt.Color(198, 54, 53));
        Minimo_inv.setBorder(null);
        Minimo_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Minimo_invMouseClicked(evt);
            }
        });
        Minimo_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Minimo_invActionPerformed(evt);
            }
        });
        Minimo_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Minimo_invKeyTyped(evt);
            }
        });
        getContentPane().add(Minimo_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(938, 305, 160, 25));

        Reorden_inv.setBackground(new java.awt.Color(228, 228, 228));
        Reorden_inv.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Reorden_inv.setForeground(new java.awt.Color(198, 54, 53));
        Reorden_inv.setBorder(null);
        Reorden_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Reorden_invMouseClicked(evt);
            }
        });
        Reorden_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reorden_invActionPerformed(evt);
            }
        });
        Reorden_inv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Reorden_invKeyTyped(evt);
            }
        });
        getContentPane().add(Reorden_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 347, 160, 25));

        Guardar_inv.setBorder(null);
        Guardar_inv.setContentAreaFilled(false);
        Guardar_inv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Guardar_inv.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                Guardar_invComponentRemoved(evt);
            }
        });
        Guardar_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar_invActionPerformed(evt);
            }
        });
        getContentPane().add(Guardar_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 630, 140, 30));

        Modificar_inv.setBorder(null);
        Modificar_inv.setContentAreaFilled(false);
        Modificar_inv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modificar_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar_invActionPerformed(evt);
            }
        });
        getContentPane().add(Modificar_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 630, 140, 30));

        Limpioar_inv.setBorder(null);
        Limpioar_inv.setContentAreaFilled(false);
        Limpioar_inv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Limpioar_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpioar_invActionPerformed(evt);
            }
        });
        getContentPane().add(Limpioar_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 630, 140, 30));

        Azul_pnl.setBackground(new java.awt.Color(75, 174, 231));

        javax.swing.GroupLayout Azul_pnlLayout = new javax.swing.GroupLayout(Azul_pnl);
        Azul_pnl.setLayout(Azul_pnlLayout);
        Azul_pnlLayout.setHorizontalGroup(
            Azul_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Azul_pnlLayout.setVerticalGroup(
            Azul_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(Azul_pnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 516, 16, 16));

        Verde_pnl.setBackground(new java.awt.Color(94, 163, 67));

        javax.swing.GroupLayout Verde_pnlLayout = new javax.swing.GroupLayout(Verde_pnl);
        Verde_pnl.setLayout(Verde_pnlLayout);
        Verde_pnlLayout.setHorizontalGroup(
            Verde_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Verde_pnlLayout.setVerticalGroup(
            Verde_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(Verde_pnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(589, 516, 16, 16));

        Amarillo_pnl.setBackground(new java.awt.Color(255, 238, 85));

        javax.swing.GroupLayout Amarillo_pnlLayout = new javax.swing.GroupLayout(Amarillo_pnl);
        Amarillo_pnl.setLayout(Amarillo_pnlLayout);
        Amarillo_pnlLayout.setHorizontalGroup(
            Amarillo_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Amarillo_pnlLayout.setVerticalGroup(
            Amarillo_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(Amarillo_pnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(764, 516, 16, 16));

        Naranja_pnl.setBackground(new java.awt.Color(227, 167, 75));

        javax.swing.GroupLayout Naranja_pnlLayout = new javax.swing.GroupLayout(Naranja_pnl);
        Naranja_pnl.setLayout(Naranja_pnlLayout);
        Naranja_pnlLayout.setHorizontalGroup(
            Naranja_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Naranja_pnlLayout.setVerticalGroup(
            Naranja_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(Naranja_pnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(938, 516, 16, 16));

        Rojo_pnl.setBackground(new java.awt.Color(198, 54, 53));

        javax.swing.GroupLayout Rojo_pnlLayout = new javax.swing.GroupLayout(Rojo_pnl);
        Rojo_pnl.setLayout(Rojo_pnlLayout);
        Rojo_pnlLayout.setHorizontalGroup(
            Rojo_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Rojo_pnlLayout.setVerticalGroup(
            Rojo_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(Rojo_pnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1147, 516, 16, 16));

        buttonGroup1.add(slcBar_art);
        slcBar_art.setContentAreaFilled(false);
        slcBar_art.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slcBar_art.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slcBar_artMouseClicked(evt);
            }
        });
        getContentPane().add(slcBar_art, new org.netbeans.lib.awtextra.AbsoluteConstraints(1048, 159, 60, 25));

        buttonGroup1.add(slcRest_art);
        slcRest_art.setContentAreaFilled(false);
        slcRest_art.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slcRest_art.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                slcRest_artMouseClicked(evt);
            }
        });
        getContentPane().add(slcRest_art, new org.netbeans.lib.awtextra.AbsoluteConstraints(1117, 159, 70, 25));

        jPanel20.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 165, 15, 15));

        jPanel21.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 165, 15, 15));

        nombre_usu_art.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        getContentPane().add(nombre_usu_art, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 255, 170, 20));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 255, 170, 20));

        Btnfacturacion.setContentAreaFilled(false);
        Btnfacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnfacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnfacturacionActionPerformed(evt);
            }
        });
        getContentPane().add(Btnfacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 240, 40));

        Btndespacho.setContentAreaFilled(false);
        Btndespacho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btndespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtndespachoActionPerformed(evt);
            }
        });
        getContentPane().add(Btndespacho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 240, 40));

        Btninventario.setContentAreaFilled(false);
        Btninventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btninventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtninventarioActionPerformed(evt);
            }
        });
        getContentPane().add(Btninventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 240, 40));

        Btndelivery.setContentAreaFilled(false);
        Btndelivery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btndelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtndeliveryActionPerformed(evt);
            }
        });
        getContentPane().add(Btndelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 240, 40));

        Btncomprobantes.setContentAreaFilled(false);
        Btncomprobantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btncomprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtncomprobantesActionPerformed(evt);
            }
        });
        getContentPane().add(Btncomprobantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 240, 40));

        Btnusuario.setContentAreaFilled(false);
        Btnusuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(Btnusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 240, 40));

        Tipo_articulo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bebidas", "Comidas" }));
        Tipo_articulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        getContentPane().add(Tipo_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 65, 150, 30));

        jLabel1.setBackground(java.awt.Color.gray);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Articulo.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1365, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizarActionPerformed
        this.setState(Menu_Principal.ICONIFIED);          // TODO add your handling code here:
    }//GEN-LAST:event_btn_minimizarActionPerformed

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

    private void volverMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_volverMouseMoved

    private void volverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_volverMouseExited

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_volverActionPerformed

    private void Descrip_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Descrip_invActionPerformed
        if(Descrip_inv.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe de ingresar la descripcion del articulo");
        }else{
            Marca_inv.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_Descrip_invActionPerformed

    private void Descrip_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Descrip_invKeyTyped
        char c = evt.getKeyChar();
        if ((c<'a'|| c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') && (c!='.') &&(c!='@')
            &&(c!='#')&&(c!='!')&&(c!='$')&&(c!='%')&&(c!='&')&&(c!='?')&&(c!=',')&&(c!=':')&&(c!=';') && c!= com.sun.glass.events.KeyEvent.VK_SPACE) evt.consume();    
        // TODO add your handling code here:
    }//GEN-LAST:event_Descrip_invKeyTyped

    private void Marca_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Marca_invActionPerformed
        Suplidor_inv.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_Marca_invActionPerformed

    private void Marca_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Marca_invKeyTyped
        char c = evt.getKeyChar();
        if ((c<'a'|| c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') && (c!='.') &&(c!='@')
            &&(c!='#')&&(c!='!')&&(c!='$')&&(c!='%')&&(c!='&')&&(c!='?')&&(c!=',')&&(c!=':')&&(c!=';') && c!= com.sun.glass.events.KeyEvent.VK_SPACE) evt.consume();        // TODO add your handling code here:
    }//GEN-LAST:event_Marca_invKeyTyped

    private void Cocdigo_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cocdigo_invMouseClicked
        if(evt.getClickCount() == 2){
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea consultar un articulo?",
                "Consultar Articulo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            Cocdigo_inv.setEditable(true);
            Cocdigo_inv.setText("");
            Cocdigo_inv.requestFocus();
        }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Cocdigo_invMouseClicked

    private void Cocdigo_invMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cocdigo_invMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Cocdigo_invMouseEntered

    private void Cocdigo_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cocdigo_invActionPerformed
        confirmar();// TODO add your handling code here:
    }//GEN-LAST:event_Cocdigo_invActionPerformed

    private void Cocdigo_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cocdigo_invKeyTyped
        char c = evt.getKeyChar();
        if (c< '0'|| c>'9') evt.consume();        // TODO add your handling code here:
    }//GEN-LAST:event_Cocdigo_invKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Consulta_Producto ca = new Consulta_Producto();
        ca.setVisible(true);
Clase_Variable_Publica.art = 2;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Suplidor_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Suplidor_invActionPerformed
        Costo_inv.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_Suplidor_invActionPerformed

    private void Suplidor_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Suplidor_invKeyTyped
        char c = evt.getKeyChar();
        if ((c<'a'|| c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') && (c!='.') &&(c!='@')
            &&(c!='#')&&(c!='!')&&(c!='$')&&(c!='%')&&(c!='&')&&(c!='?')&&(c!=',')&&(c!=':')&&(c!=';') && c!= com.sun.glass.events.KeyEvent.VK_SPACE) evt.consume();        // TODO add your handling code here:
    }//GEN-LAST:event_Suplidor_invKeyTyped

    private void Costo_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Costo_invMouseClicked
        if(Costo_inv.getText().equals("0.00")){
            Costo_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Costo_invMouseClicked

    private void Costo_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Costo_invActionPerformed
        Precio_inv.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_Costo_invActionPerformed

    private void Costo_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Costo_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(Costo_inv.getText().equals("0.00")){
            Costo_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_Costo_invKeyTyped

    private void Ganancia_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ganancia_invMouseClicked
        if(Ganancia_inv.getText().equals("0.00")){
            Ganancia_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Ganancia_invMouseClicked

    private void Ganancia_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ganancia_invActionPerformed
if(!Costo_inv.getText().isEmpty()){
        float valor1;
        float valor2;
        float mat;
        float resultado;
        float suma1;
        float suma2;
        String total;
        valor1=Float.parseFloat(Costo_inv.getText());
        valor2=Float.parseFloat(Ganancia_inv.getText());
        resultado = (Float)(valor2*100) / valor1;
        DecimalFormat df = new DecimalFormat("#.00");
        Porciento_inv.setText(df.format(resultado));

        suma1 = valor1 + valor2;
        Preciosin_inv.setText(df.format(suma1));

        suma2 = (float) (suma1 * 1.18);
        Precio_inv.setText(df.format(suma2));
        Existencia_inv.requestFocus();
}else{
    JOptionPane.showMessageDialog(null,"Primero debes de ingresar el costo del articulo");
        Ganancia_inv.setText("0.00");
    Costo_inv.requestFocus();
}
    }//GEN-LAST:event_Ganancia_invActionPerformed

    private void Ganancia_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ganancia_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(Ganancia_inv.getText().equals("0.00")){
            Ganancia_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_Ganancia_invKeyTyped

    private void Porciento_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Porciento_invMouseClicked
        if(Porciento_inv.getText().equals("0.00")){
            Porciento_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Porciento_invMouseClicked

    private void Porciento_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Porciento_invActionPerformed
if(!Costo_inv.getText().isEmpty()){
        float valor1;
        float valor2;
        float valor3;
        float mat;
        float resultado;
        float suma1;
        float suma2;
        String total;
        valor1=Float.parseFloat(Costo_inv.getText());
        valor2=Float.parseFloat(Porciento_inv.getText());

        resultado = (valor1*valor2) / 100;
        DecimalFormat df = new DecimalFormat("#.00");
        Ganancia_inv.setText(df.format(resultado));

        valor3=Float.parseFloat(Ganancia_inv.getText());
        suma1 = (valor1 + valor3);
        Preciosin_inv.setText(df.format(suma1));

        suma2 = (float) (suma1 * 1.18);
        Precio_inv.setText(df.format(suma2));
}else{
    JOptionPane.showMessageDialog(null,"Primero debes de ingresar el costo del articulo");
    Porciento_inv.setText("0.00");
    Costo_inv.requestFocus();
}
        // TODO add your handling code here:
    }//GEN-LAST:event_Porciento_invActionPerformed

    private void Porciento_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Porciento_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(Porciento_inv.getText().equals("0.00")){
            Porciento_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_Porciento_invKeyTyped

    private void Preciosin_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Preciosin_invMouseClicked
        if(Preciosin_inv.getText().equals("0.00")){
            Preciosin_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Preciosin_invMouseClicked

    private void Preciosin_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Preciosin_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(Preciosin_inv.getText().equals("0.00")){
            Preciosin_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_Preciosin_invKeyTyped

    private void Precio_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Precio_invMouseClicked
        if(Precio_inv.getText().equals("0.00")){
            Precio_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Precio_invMouseClicked

    private void Precio_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Precio_invActionPerformed
if(!Costo_inv.getText().isEmpty()){
        float valor1;
        float valor2;
        float valor3;
        float mat;
        float resultado;
        float suma1;
        float suma2;

        valor1 = Float.parseFloat(Precio_inv.getText());
        valor2 = Float.parseFloat(Costo_inv.getText());

        mat = (float) (valor1 / 1.18);

        DecimalFormat df = new DecimalFormat("#.00");
        Preciosin_inv.setText(df.format(mat));

        suma1 = mat - valor2;
        Ganancia_inv.setText(df.format(suma1));

        valor3 = Float.parseFloat(Ganancia_inv.getText());
        suma2 = (Float)(valor3*100) / valor2;
        Porciento_inv.setText(df.format(suma2));
Existencia_inv.requestFocus(); 
}else{
    JOptionPane.showMessageDialog(null,"Primero debes de ingresar el costo del articulo");
        Precio_inv.setText("0.00");
    Costo_inv.requestFocus();
}
    }//GEN-LAST:event_Precio_invActionPerformed

    private void Precio_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Precio_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(Precio_inv.getText().equals("0.00")){
            Precio_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_Precio_invKeyTyped

    private void PrecioOferta_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrecioOferta_invMouseClicked
        if(PrecioOferta_inv.getText().equals("0.00")){
            PrecioOferta_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_PrecioOferta_invMouseClicked

    private void PrecioOferta_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecioOferta_invActionPerformed

    }//GEN-LAST:event_PrecioOferta_invActionPerformed

    private void PrecioOferta_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrecioOferta_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(PrecioOferta_inv.getText().equals("0.00")){
            PrecioOferta_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_PrecioOferta_invKeyTyped

    private void Existencia_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Existencia_invMouseClicked
        if(Existencia_inv.getText().equals("0")){
            Existencia_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Existencia_invMouseClicked

    private void Existencia_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Existencia_invActionPerformed
        Maximo_inv.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_Existencia_invActionPerformed

    private void Existencia_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Existencia_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(Existencia_inv.getText().equals("0")){
            Existencia_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_Existencia_invKeyTyped

    private void Maximo_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Maximo_invMouseClicked
        if(Maximo_inv.getText().equals("0")){
            Maximo_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Maximo_invMouseClicked

    private void Maximo_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Maximo_invActionPerformed
        Minimo_inv.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_Maximo_invActionPerformed

    private void Maximo_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Maximo_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(Maximo_inv.getText().equals("0")){
            Maximo_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_Maximo_invKeyTyped

    private void Minimo_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Minimo_invMouseClicked
        if(Minimo_inv.getText().equals("0")){
            Minimo_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Minimo_invMouseClicked

    private void Minimo_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Minimo_invActionPerformed
        Reorden_inv.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_Minimo_invActionPerformed

    private void Minimo_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Minimo_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(Minimo_inv.getText().equals("0")){
            Minimo_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_Minimo_invKeyTyped

    private void Reorden_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Reorden_invMouseClicked
        if(Reorden_inv.getText().equals("0")){
            Reorden_inv.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Reorden_invMouseClicked

    private void Reorden_invKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Reorden_invKeyTyped
        char c = evt.getKeyChar();
        if ((c< '0'|| c>'9')&& (c!='.')) evt.consume();
        if(Reorden_inv.getText().equals("0")){
            Reorden_inv.setText("");
        }// TODO add your handling code here:
    }//GEN-LAST:event_Reorden_invKeyTyped

    private void Guardar_invComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_Guardar_invComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Guardar_invComponentRemoved

    private void Guardar_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar_invActionPerformed
        set_valores();
        if(cod_art_modif.getText().isEmpty()){
            if(!Descrip_inv.getText().isEmpty() && !Marca_inv.getText().isEmpty() && !Suplidor_inv.getText().isEmpty() &&
                !Costo_inv.getText().isEmpty() && !Existencia_inv.getText().isEmpty() && !Maximo_inv.getText().isEmpty() && !Minimo_inv.getText().isEmpty()
                && !Reorden_inv.getText().isEmpty()){
                Clases.Funciones_art gr = new Funciones_art();
                gr.Guardar();
            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe de llenar todos los campos");
                if(Descrip_inv.getText().isEmpty()){
                    Descrip_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Marca_inv.getText().isEmpty()){
                    Marca_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Suplidor_inv.getText().isEmpty()){
                    Suplidor_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Costo_inv.getText().isEmpty()){
                    Costo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Existencia_inv.getText().isEmpty()){
                    Existencia_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Maximo_inv.getText().isEmpty()){
                    Maximo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Minimo_inv.getText().isEmpty()){
                    Minimo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Reorden_inv.getText().isEmpty()){
                    Reorden_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
            }
        }
        else{
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente modificar el Articulo?",
                "Modificar Articulo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(!Descrip_inv.getText().isEmpty() && !Marca_inv.getText().isEmpty() && !Suplidor_inv.getText().isEmpty() &&
                !Costo_inv.getText().isEmpty() && !Existencia_inv.getText().isEmpty() && !Maximo_inv.getText().isEmpty() && !Minimo_inv.getText().isEmpty()
                && !Reorden_inv.getText().isEmpty()){
                Clases.Funciones_art gr = new Funciones_art();
                gr.Modificar();
            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe de llenar todos los campos");
                if(Descrip_inv.getText().isEmpty()){
                    Descrip_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK.RED));
                }
                if(Marca_inv.getText().isEmpty()){
                    Marca_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Suplidor_inv.getText().isEmpty()){
                    Suplidor_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Costo_inv.getText().isEmpty()){
                    Costo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Existencia_inv.getText().isEmpty()){
                    Existencia_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Maximo_inv.getText().isEmpty()){
                    Maximo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Minimo_inv.getText().isEmpty()){
                    Minimo_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(Reorden_inv.getText().isEmpty()){
                    Reorden_inv.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
            }
        }else{}
        }
    }//GEN-LAST:event_Guardar_invActionPerformed

    private void Modificar_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar_invActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea modificar este articulo?",
            "Modificar Articulo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
        desbloquear1();
        Descrip_inv.requestFocus();

        }else{}        // TODO add your handling code here:
    }//GEN-LAST:event_Modificar_invActionPerformed

    private void Limpioar_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpioar_invActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea limpiar los campos?",
            "Limpiar Campos", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
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
        desbloquear();
        Consultar_num_usu();
        Descrip_inv.requestFocus();
        }else{

        }
    }//GEN-LAST:event_Limpioar_invActionPerformed

    private void Reorden_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reorden_invActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Reorden_invActionPerformed

    private void BtnfacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnfacturacionActionPerformed
        if(Clase_Variable_Publica.permiso_fact==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                                              if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar articulos?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Facturacion fct = new Facturacion();
            fct.setVisible(true);
            Facturacion.nombre_usu_fact.setText(nombre_usu_art.getText());
            this.dispose();
                                              }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para facturacion");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnfacturacionActionPerformed

    private void BtndespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtndespachoActionPerformed
        if(Clase_Variable_Publica.permiso_desp==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar articulos?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Despacho des = new Despacho();
            des.setVisible(true);
            //Facturacion.nombre_usu_fac.setText(nombre_usu_cli.getText());
            this.dispose();
                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para despacho");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtndespachoActionPerformed

    private void BtninventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtninventarioActionPerformed
        if(Clase_Variable_Publica.permiso_inv==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar articulos?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Inventario inv = new Inventario();
            inv.setVisible(true);
            Inventario.nombre_usu_inv.setText(nombre_usu_art.getText());
            this.dispose();
                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para inventario");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtninventarioActionPerformed

    private void BtndeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtndeliveryActionPerformed
        if(Clase_Variable_Publica.permiso_delv==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar articulos?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Delivery del = new Delivery();
            del.setVisible(true);
            Delivery.nombre_usu_delv.setText(nombre_usu_art.getText());
            this.dispose();
                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para delivery");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtndeliveryActionPerformed

    private void BtncomprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtncomprobantesActionPerformed
        if(Clase_Variable_Publica.permiso_compro==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar articulos?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Comprobantes fact = new Comprobantes();
            fact.setVisible(true);
            Comprobantes.nombre_usu_comp.setText(nombre_usu_art.getText());
            this.dispose();
                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para comprobantes");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtncomprobantesActionPerformed

    private void BtnusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnusuarioActionPerformed
        if(Clase_Variable_Publica.permiso_usu==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar articulos?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Usuarios usu = new Usuarios();
            usu.setVisible(true);
            Usuarios.nombre_usu_usu.setText(nombre_usu_art.getText());
            this.dispose();
                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para usuarios");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnusuarioActionPerformed

    private void slcBar_artMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slcBar_artMouseClicked
Tipo_articulo.setEnabled(false);
Tipo_articulo.setSelectedItem("Bebidas");// TODO add your handling code here:
    }//GEN-LAST:event_slcBar_artMouseClicked

    private void slcRest_artMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slcRest_artMouseClicked
Tipo_articulo.setEnabled(true);        // TODO add your handling code here:
    }//GEN-LAST:event_slcRest_artMouseClicked

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
            java.util.logging.Logger.getLogger(Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Articulo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel Amarillo_pnl;
    public static javax.swing.JPanel Azul_pnl;
    private javax.swing.JButton Btncomprobantes;
    private javax.swing.JButton Btndelivery;
    private javax.swing.JButton Btndespacho;
    private javax.swing.JButton Btnfacturacion;
    private javax.swing.JButton Btninventario;
    private javax.swing.JButton Btnusuario;
    public static javax.swing.JTextField Cocdigo_inv;
    public static javax.swing.JLabel Cod_art;
    public static javax.swing.JTextField Costo_inv;
    public static javax.swing.JTextField Descrip_inv;
    public static javax.swing.JTextField Existencia_inv;
    public static javax.swing.JTextField Ganancia_inv;
    private javax.swing.JButton Guardar_inv;
    private javax.swing.JButton Limpioar_inv;
    public static javax.swing.JTextField Marca_inv;
    public static javax.swing.JTextField Maximo_inv;
    public static javax.swing.JTextField Minimo_inv;
    private javax.swing.JButton Modificar_inv;
    public static javax.swing.JPanel Naranja_pnl;
    public static javax.swing.JTextField Porciento_inv;
    public static javax.swing.JTextField PrecioOferta_inv;
    public static javax.swing.JTextField Precio_inv;
    public static javax.swing.JTextField Preciosin_inv;
    public static javax.swing.JTextField Reorden_inv;
    public static javax.swing.JPanel Rojo_pnl;
    public static javax.swing.JTextField Suplidor_inv;
    public static javax.swing.JComboBox<String> Tipo_articulo;
    public static javax.swing.JPanel Verde_pnl;
    private javax.swing.JButton btn_minimizar;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JLabel cod_art_modif;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    public static javax.swing.JLabel nombre_usu_art;
    public static javax.swing.JRadioButton slcBar_art;
    public static javax.swing.JRadioButton slcRest_art;
    private javax.swing.JButton volver;
    private javax.swing.JButton volverAtras;
    // End of variables declaration//GEN-END:variables
    private Connection conexion = null;
    conector cc = new conector();
    Connection cn = cc.conexion();
}
