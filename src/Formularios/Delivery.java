/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.Funciones_delivery;
import Clases.generador_numerico;
import static Formularios.Usuarios.tabla_usuario;
import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Delivery extends javax.swing.JFrame {

  Fuentes tipofuente;
    public Delivery() {
        initComponents();
        tipo_letra();
        Consultar_num_del();
        limpiar_tabla();
        AWTUtilities.setWindowOpaque(this, false);
        setLocationRelativeTo(null);
        try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
      try {
          cargar();
      } catch (SQLException ex) {
          Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar delivery?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            this.dispose();
    }
public void tipo_letra(){
        tipofuente = new Fuentes();
        nombre_usu_delv.setFont(tipofuente.fuente(tipofuente.RIO,0,16));   
        Cocdigo_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        nombre_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        tel_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        cedula_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20));   
        direc_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        placa_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        chasis_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        color_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        marca_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
        buscar_del.setFont(tipofuente.fuente(tipofuente.RIO,0,20)); 
       
    }
public void bloquear(){
    nombre_del.setEnabled(false);
    tel_del.setEditable(false);
    cedula_del.setEditable(false);
    direc_del.setEditable(false);
    placa_del.setEditable(false);
    chasis_del.setEditable(false);
    color_del.setEditable(false);
    marca_del.setEditable(false);
    buscar_del.setEditable(false);
}
/////////////////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////
public void Consultar_num_del(){
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
public void limpiar_tabla() {
        DefaultTableModel modelo3 = (DefaultTableModel) tabla_del.getModel();
        modelo3.getDataVector().clear();
    }
////////////////////////////////////////////////////
void cargar() throws SQLException {
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
//////////////////////////////////////////////
void filtro(String valor) throws SQLException{
        DefaultTableModel modelo2 = (DefaultTableModel) tabla_del.getModel();
        modelo2.getDataVector().clear();
        
        String[] registros = new String[6];
        String sql = "SELECT codigo,nombre,cedula,telefono FROM delivery where CONCAT (codigo,'',nombre,'',cedula,'',telefono) LIKE '%"+ valor +"%'";
        try{
            Statement st = cn.createStatement();
            ResultSet rs =st.executeQuery(sql);
            
                while (rs.next()) {
                    registros[0] = rs.getString("codigo");
                    registros[1] = rs.getString("nombre");
                    registros[2] = rs.getString("cedula");
                    registros[3] = rs.getString("telefono");
                    modelo2.addRow(registros);
                }
                tabla_del.setModel(modelo2);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cod_del_modif = new javax.swing.JLabel();
        btn_minimizar = new javax.swing.JButton();
        volverAtras = new javax.swing.JButton();
        nombre_usu_delv = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Btnfacturacion = new javax.swing.JButton();
        Btnarticulo = new javax.swing.JButton();
        Btndespacho = new javax.swing.JButton();
        Btninventario = new javax.swing.JButton();
        Btncomprobantes = new javax.swing.JButton();
        Btnusuario = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Cocdigo_del = new javax.swing.JTextField();
        buscar_del = new javax.swing.JTextField();
        marca_del = new javax.swing.JTextField();
        color_del = new javax.swing.JTextField();
        chasis_del = new javax.swing.JTextField();
        placa_del = new javax.swing.JTextField();
        direc_del = new javax.swing.JTextField();
        nombre_del = new javax.swing.JTextField();
        tel_del = new javax.swing.JFormattedTextField();
        cedula_del = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_del = new javax.swing.JTable();
        Guardar_inv = new javax.swing.JButton();
        Limpiar_del = new javax.swing.JButton();
        Modificar_del = new javax.swing.JButton();
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
        getContentPane().add(nombre_usu_delv, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 260, 160, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 170, 20));

        Btnfacturacion.setContentAreaFilled(false);
        Btnfacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnfacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnfacturacionActionPerformed(evt);
            }
        });
        getContentPane().add(Btnfacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 240, 40));

        Btnarticulo.setContentAreaFilled(false);
        Btnarticulo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnarticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnarticuloActionPerformed(evt);
            }
        });
        getContentPane().add(Btnarticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 240, 40));

        Btndespacho.setContentAreaFilled(false);
        Btndespacho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btndespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtndespachoActionPerformed(evt);
            }
        });
        getContentPane().add(Btndespacho, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 240, 40));

        Btninventario.setContentAreaFilled(false);
        Btninventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btninventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtninventarioActionPerformed(evt);
            }
        });
        getContentPane().add(Btninventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 240, 40));

        Btncomprobantes.setContentAreaFilled(false);
        Btncomprobantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btncomprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtncomprobantesActionPerformed(evt);
            }
        });
        getContentPane().add(Btncomprobantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 240, 40));

        Btnusuario.setContentAreaFilled(false);
        Btnusuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(Btnusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 240, 40));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 280, 20, 25));

        Cocdigo_del.setBackground(new java.awt.Color(228, 228, 228));
        Cocdigo_del.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        Cocdigo_del.setForeground(new java.awt.Color(198, 54, 53));
        Cocdigo_del.setBorder(null);
        Cocdigo_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cocdigo_delMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Cocdigo_delMouseEntered(evt);
            }
        });
        Cocdigo_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cocdigo_delActionPerformed(evt);
            }
        });
        Cocdigo_del.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Cocdigo_delKeyTyped(evt);
            }
        });
        getContentPane().add(Cocdigo_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 65, 110, 25));

        buscar_del.setBackground(new java.awt.Color(228, 228, 228));
        buscar_del.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        buscar_del.setForeground(new java.awt.Color(198, 54, 53));
        buscar_del.setBorder(null);
        buscar_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscar_delMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buscar_delMouseEntered(evt);
            }
        });
        buscar_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_delActionPerformed(evt);
            }
        });
        buscar_del.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscar_delKeyTyped(evt);
            }
        });
        getContentPane().add(buscar_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 280, 180, 25));

        marca_del.setBackground(new java.awt.Color(228, 228, 228));
        marca_del.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        marca_del.setForeground(new java.awt.Color(198, 54, 53));
        marca_del.setBorder(null);
        marca_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                marca_delMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                marca_delMouseEntered(evt);
            }
        });
        marca_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marca_delActionPerformed(evt);
            }
        });
        marca_del.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                marca_delKeyTyped(evt);
            }
        });
        getContentPane().add(marca_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(1143, 227, 140, 25));

        color_del.setBackground(new java.awt.Color(228, 228, 228));
        color_del.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        color_del.setForeground(new java.awt.Color(198, 54, 53));
        color_del.setBorder(null);
        color_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                color_delMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                color_delMouseEntered(evt);
            }
        });
        color_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                color_delActionPerformed(evt);
            }
        });
        color_del.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                color_delKeyTyped(evt);
            }
        });
        getContentPane().add(color_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(957, 227, 100, 25));

        chasis_del.setBackground(new java.awt.Color(228, 228, 228));
        chasis_del.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        chasis_del.setForeground(new java.awt.Color(198, 54, 53));
        chasis_del.setBorder(null);
        chasis_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chasis_delMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chasis_delMouseEntered(evt);
            }
        });
        chasis_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chasis_delActionPerformed(evt);
            }
        });
        chasis_del.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                chasis_delKeyTyped(evt);
            }
        });
        getContentPane().add(chasis_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 227, 250, 25));

        placa_del.setBackground(new java.awt.Color(228, 228, 228));
        placa_del.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        placa_del.setForeground(new java.awt.Color(198, 54, 53));
        placa_del.setBorder(null);
        placa_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                placa_delMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                placa_delMouseEntered(evt);
            }
        });
        placa_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placa_delActionPerformed(evt);
            }
        });
        placa_del.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                placa_delKeyTyped(evt);
            }
        });
        getContentPane().add(placa_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 227, 130, 25));

        direc_del.setBackground(new java.awt.Color(228, 228, 228));
        direc_del.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        direc_del.setForeground(new java.awt.Color(198, 54, 53));
        direc_del.setBorder(null);
        direc_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                direc_delMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                direc_delMouseEntered(evt);
            }
        });
        direc_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direc_delActionPerformed(evt);
            }
        });
        direc_del.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direc_delKeyTyped(evt);
            }
        });
        getContentPane().add(direc_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 161, 520, 25));

        nombre_del.setBackground(new java.awt.Color(228, 228, 228));
        nombre_del.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        nombre_del.setForeground(new java.awt.Color(198, 54, 53));
        nombre_del.setBorder(null);
        nombre_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombre_delMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nombre_delMouseEntered(evt);
            }
        });
        nombre_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_delActionPerformed(evt);
            }
        });
        nombre_del.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_delKeyTyped(evt);
            }
        });
        getContentPane().add(nombre_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 130, 390, 25));

        tel_del.setBackground(new java.awt.Color(228, 228, 228));
        tel_del.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tel_del.setForeground(new java.awt.Color(198, 54, 53));
        try {
            tel_del.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###) ###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(tel_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 125, 180, 30));

        cedula_del.setBackground(new java.awt.Color(228, 228, 228));
        cedula_del.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cedula_del.setForeground(new java.awt.Color(198, 54, 53));
        try {
            cedula_del.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-#######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(cedula_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 160, 190, 30));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tabla_del.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Cedula", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_del.setSelectionBackground(new java.awt.Color(198, 54, 53));
        tabla_del.setShowHorizontalLines(false);
        tabla_del.setShowVerticalLines(false);
        tabla_del.getTableHeader().setResizingAllowed(false);
        tabla_del.getTableHeader().setReorderingAllowed(false);
        tabla_del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_delMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_del);
        if (tabla_del.getColumnModel().getColumnCount() > 0) {
            tabla_del.getColumnModel().getColumn(0).setResizable(false);
            tabla_del.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_del.getColumnModel().getColumn(1).setResizable(false);
            tabla_del.getColumnModel().getColumn(1).setPreferredWidth(300);
            tabla_del.getColumnModel().getColumn(2).setResizable(false);
            tabla_del.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 318, 950, 295));

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

        Limpiar_del.setBorder(null);
        Limpiar_del.setContentAreaFilled(false);
        Limpiar_del.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Limpiar_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpiar_delActionPerformed(evt);
            }
        });
        getContentPane().add(Limpiar_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 630, 140, 30));

        Modificar_del.setBorder(null);
        Modificar_del.setContentAreaFilled(false);
        Modificar_del.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modificar_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar_delActionPerformed(evt);
            }
        });
        getContentPane().add(Modificar_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 630, 140, 30));

        jLabel1.setBackground(java.awt.Color.gray);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Delivery.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

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

    private void BtnfacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnfacturacionActionPerformed
        if(Clase_Variable_Publica.permiso_fact==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar delivery?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Facturacion fct = new Facturacion();
            fct.setVisible(true);
            Facturacion.nombre_usu_fact.setText(nombre_usu_delv.getText());
            this.dispose();
                                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para facturacion");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnfacturacionActionPerformed

    private void BtnarticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnarticuloActionPerformed
        if(Clase_Variable_Publica.permiso_art==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar delivery?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Articulo art = new Articulo();
            art.setVisible(true);
            Articulo.nombre_usu_art.setText(nombre_usu_delv.getText());
            this.dispose();
                                                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para articulo");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnarticuloActionPerformed

    private void BtndespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtndespachoActionPerformed
        if(Clase_Variable_Publica.permiso_desp==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar delivery?",
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
                                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar delivery?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Inventario inv = new Inventario();
            inv.setVisible(true);
            Inventario.nombre_usu_inv.setText(nombre_usu_delv.getText());
            this.dispose();
                                                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para inventario");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtninventarioActionPerformed

    private void BtncomprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtncomprobantesActionPerformed
        if(Clase_Variable_Publica.permiso_compro==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar delivery?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Comprobantes fact = new Comprobantes();
            fact.setVisible(true);
            Comprobantes.nombre_usu_comp.setText(nombre_usu_delv.getText());
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
                                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar delivery?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Usuarios usu = new Usuarios();
            usu.setVisible(true);
            Usuarios.nombre_usu_usu.setText(nombre_usu_delv.getText());
            this.dispose();
                                                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para usuarios");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnusuarioActionPerformed

    private void nombre_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombre_delMouseClicked
        if(evt.getClickCount() == 2){
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea consultar un articulo?",
                "Consultar Articulo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            nombre_del.setEditable(true);
            nombre_del.setText("");
            nombre_del.requestFocus();
        }
        }// TODO add your handling code here:
    }//GEN-LAST:event_nombre_delMouseClicked

    private void nombre_delMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombre_delMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_delMouseEntered

    private void nombre_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_delActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_nombre_delActionPerformed

    private void nombre_delKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_delKeyTyped
        char c = evt.getKeyChar();
        if ((c<'a'|| c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') && (c!='.') &&(c!='@')
            &&(c!='#')&&(c!='!')&&(c!='$')&&(c!='%')&&(c!='&')&&(c!='?')&&(c!=',')&&(c!=':')&&(c!=';') && c!= com.sun.glass.events.KeyEvent.VK_SPACE) evt.consume();    
        // TODO add your handling code here:      // TODO add your handling code here:
    }//GEN-LAST:event_nombre_delKeyTyped

    private void direc_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_direc_delMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_direc_delMouseClicked

    private void direc_delMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_direc_delMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_direc_delMouseEntered

    private void direc_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direc_delActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direc_delActionPerformed

    private void direc_delKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direc_delKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_direc_delKeyTyped

    private void placa_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_placa_delMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_placa_delMouseClicked

    private void placa_delMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_placa_delMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_placa_delMouseEntered

    private void placa_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placa_delActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placa_delActionPerformed

    private void placa_delKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_placa_delKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_placa_delKeyTyped

    private void chasis_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chasis_delMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chasis_delMouseClicked

    private void chasis_delMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chasis_delMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_chasis_delMouseEntered

    private void chasis_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chasis_delActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chasis_delActionPerformed

    private void chasis_delKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chasis_delKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_chasis_delKeyTyped

    private void color_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_delMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_color_delMouseClicked

    private void color_delMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_delMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_color_delMouseEntered

    private void color_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_color_delActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_color_delActionPerformed

    private void color_delKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_color_delKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_color_delKeyTyped

    private void marca_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_marca_delMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_marca_delMouseClicked

    private void marca_delMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_marca_delMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_marca_delMouseEntered

    private void marca_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marca_delActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marca_delActionPerformed

    private void marca_delKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_marca_delKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_marca_delKeyTyped

    private void buscar_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscar_delMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar_delMouseClicked

    private void buscar_delMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscar_delMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar_delMouseEntered

    private void buscar_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_delActionPerformed
        try {
            filtro(buscar_del.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }           // TODO add your handling code here:
    }//GEN-LAST:event_buscar_delActionPerformed

    private void buscar_delKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar_delKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar_delKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            filtro(buscar_del.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Guardar_invComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_Guardar_invComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Guardar_invComponentRemoved

    private void Guardar_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar_invActionPerformed
        if(cod_del_modif.getText().isEmpty()){
            if(!nombre_del.getText().isEmpty() && !tel_del.getText().equals("(   )    -    ") && !cedula_del.equals("   -       - ") &&
                !direc_del.getText().isEmpty() && !placa_del.getText().isEmpty() && !chasis_del.getText().isEmpty() && !color_del.getText().isEmpty()
                && !marca_del.getText().isEmpty()){
                Funciones_delivery gr = new Funciones_delivery();
                gr.Guardar();

            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe de llenar todos los campos");
                if(nombre_del.getText().isEmpty()){
                    nombre_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(tel_del.getText().isEmpty()){
                    tel_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(cedula_del.getText().isEmpty()){
                    cedula_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(direc_del.getText().isEmpty()){
                    direc_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(placa_del.getText().isEmpty()){
                    placa_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(chasis_del.getText().isEmpty()){
                    chasis_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(color_del.getText().isEmpty()){
                    color_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(marca_del.getText().isEmpty()){
                    marca_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
            }
        }
        else{
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente modificar el Articulo?",
                "Modificar Articulo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(!nombre_del.getText().isEmpty() && !tel_del.getText().equals("(   )    -    ") && !cedula_del.equals("   -       - ") &&
                !direc_del.getText().isEmpty() && !placa_del.getText().isEmpty() && !chasis_del.getText().isEmpty() && !color_del.getText().isEmpty()
                && !marca_del.getText().isEmpty()){
                Funciones_delivery gr = new Funciones_delivery();
               gr.Modificar();
            }else{
                if(nombre_del.getText().isEmpty()){
                    nombre_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(tel_del.getText().isEmpty()){
                    tel_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(cedula_del.getText().isEmpty()){
                    cedula_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(direc_del.getText().isEmpty()){
                    direc_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(placa_del.getText().isEmpty()){
                    placa_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(chasis_del.getText().isEmpty()){
                    chasis_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(color_del.getText().isEmpty()){
                    color_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
                if(marca_del.getText().isEmpty()){
                    marca_del.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }
            }
        }else{}
        }
    }//GEN-LAST:event_Guardar_invActionPerformed

    private void Limpiar_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpiar_delActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea limpiar los campos?",
            "Limpiar Campos", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
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
        desbloquear();
        Consultar_num_del();
        nombre_del.requestFocus();
        }else{

        }
    }//GEN-LAST:event_Limpiar_delActionPerformed

    private void Modificar_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar_delActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea modificar este articulo?",
            "Modificar Articulo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
        desbloquear();
        nombre_del.requestFocus();

        }else{}        // TODO add your handling code here:
    }//GEN-LAST:event_Modificar_delActionPerformed

    private void Cocdigo_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cocdigo_delMouseClicked
        if(evt.getClickCount() == 2){
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea consultar un articulo?",
                "Consultar Articulo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            Cocdigo_del.setEditable(true);
            Cocdigo_del.setText("");
            Cocdigo_del.requestFocus();
        }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Cocdigo_delMouseClicked

    private void Cocdigo_delMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cocdigo_delMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Cocdigo_delMouseEntered

    private void Cocdigo_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cocdigo_delActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_Cocdigo_delActionPerformed

    private void Cocdigo_delKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cocdigo_delKeyTyped
        char c = evt.getKeyChar();
        if (c< '0'|| c>'9') evt.consume();        // TODO add your handling code here:
    }//GEN-LAST:event_Cocdigo_delKeyTyped

    private void tabla_delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_delMouseClicked
 int fila = tabla_del.getSelectedRow();
            if (fila >= 0) {
                Cocdigo_del.setText(tabla_del.getValueAt(fila, 0).toString());
                cod_del_modif.setText(tabla_del.getValueAt(fila, 0).toString());
                nombre_del.setText(tabla_del.getValueAt(fila, 1).toString());
                cedula_del.setText(tabla_del.getValueAt(fila, 2).toString());
                tel_del.setText(tabla_del.getValueAt(fila, 3).toString());
                //clave_usu.setText(tabla_usuario.getValueAt(fila,3).toString());
                Modificar_del.setEnabled(true);
                Limpiar_del.setEnabled(true);
                Funciones_delivery fu = new Funciones_delivery();
                fu.cargar();
                bloquear();

            }
        else {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_delMouseClicked

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
            java.util.logging.Logger.getLogger(Delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Delivery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnarticulo;
    private javax.swing.JButton Btncomprobantes;
    private javax.swing.JButton Btndespacho;
    private javax.swing.JButton Btnfacturacion;
    private javax.swing.JButton Btninventario;
    private javax.swing.JButton Btnusuario;
    public static javax.swing.JTextField Cocdigo_del;
    private javax.swing.JButton Guardar_inv;
    private javax.swing.JButton Limpiar_del;
    private javax.swing.JButton Modificar_del;
    private javax.swing.JButton btn_minimizar;
    public static javax.swing.JTextField buscar_del;
    public static javax.swing.JFormattedTextField cedula_del;
    public static javax.swing.JTextField chasis_del;
    public static javax.swing.JLabel cod_del_modif;
    public static javax.swing.JTextField color_del;
    public static javax.swing.JTextField direc_del;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField marca_del;
    public static javax.swing.JTextField nombre_del;
    public static javax.swing.JLabel nombre_usu_delv;
    public static javax.swing.JTextField placa_del;
    public static javax.swing.JTable tabla_del;
    public static javax.swing.JFormattedTextField tel_del;
    private javax.swing.JButton volverAtras;
    // End of variables declaration//GEN-END:variables
    conector cc = new conector();
    Connection cn = cc.conexion();
}
