/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.prueba;
import Clases.render_tabla_comprobantes;
import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Comprobantes extends javax.swing.JFrame {

Fuentes tipofuente;
    public Comprobantes() {
        initComponents();
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        setCellRender(tabla_comprobante);
                try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
                
               comp_inicial.requestFocus();
               String comparacion = (String) tipo_compro.getSelectedItem();
               tipo_ncf.setText(comparacion);
               tipo_letra();
               prueba pb = new prueba();   
                       try {
            pb.conecta();
            pb.cargar_fiscal();
        } catch (SQLException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }
//      try {
//        //cargar_historial();
//    } catch (SQLException ex) {
//        Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
//    }
        
    }

   

void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar comprobantes?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            this.dispose();
    }
public void Fecha(){
    try{
    Date fecha = fecha_comprobante.getDate();
    DateFormat f = new SimpleDateFormat("YYYY-MM-d");
    String fecha2 = f.format(fecha);
    //fechaa = (fecha2);
    fecha_in.setText(fecha2);
    }catch (Exception e){
        
    }
}
public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new render_tabla_comprobantes());
        }
    }
public void tipo_letra(){
       tipofuente = new Fuentes();
        nombre_usu_comp.setFont(tipofuente.fuente(tipofuente.RIO,0,16));
        B01_f.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        B01_i.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        comp_inicial.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        comp_final_cp.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        tipo_ncf.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        siguiente_nfc.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        ultimo_nfc.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        disponible_nfc.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        caducacion_nfc.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        fecha_comprobante.setFont(tipofuente.fuente(tipofuente.RIO,0,15));
        tipo_compro.setFont(tipofuente.fuente(tipofuente.RIO,0,13));
    }    
void cargar_historial() throws SQLException {
    DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");  
    DefaultTableModel modelo2 =(DefaultTableModel) tabla_comprobante.getModel();
    modelo2.getDataVector().clear();
    float precio;
    String[] registros = new String[5];
    String sql = "SELECT ncf,num_factura,nom_cliente,total,fecha FROM encabezado_factura where ncf!='"+""+"' order by fecha DESC";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        precio = Float.parseFloat(rs.getString("total"));
        registros[0] = rs.getString("ncf");
        registros[1] = rs.getString("num_factura");
        registros[2] = rs.getString("nom_cliente");
        registros[3] = formateador.format(precio);
        registros[4] = rs.getString("fecha");
        
        modelo2.addRow(registros);
    }   
       tabla_comprobante.setModel(modelo2);
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fecha_in = new javax.swing.JTextField();
        nombre_usu_comp = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Btnfacturacion = new javax.swing.JButton();
        Btnarticulo = new javax.swing.JButton();
        Btndespacho = new javax.swing.JButton();
        Btninventario = new javax.swing.JButton();
        Btnusuario = new javax.swing.JButton();
        Btndelivery = new javax.swing.JButton();
        btn_minimizar = new javax.swing.JButton();
        volverAtras = new javax.swing.JButton();
        procesar_comp = new javax.swing.JButton();
        comp_inicial = new javax.swing.JTextField();
        comp_final_cp = new javax.swing.JTextField();
        B01_f = new javax.swing.JLabel();
        B01_i = new javax.swing.JLabel();
        fecha_comprobante = new com.toedter.calendar.JDateChooser();
        tipo_compro = new javax.swing.JComboBox<>();
        tipo_ncf = new javax.swing.JTextField();
        siguiente_nfc = new javax.swing.JTextField();
        ultimo_nfc = new javax.swing.JTextField();
        caducacion_nfc = new javax.swing.JTextField();
        disponible_nfc = new javax.swing.JTextField();
        limpiar_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_comprobante = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(nombre_usu_comp, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 260, 160, 20));

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
        getContentPane().add(Btndespacho, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 240, 40));

        Btninventario.setContentAreaFilled(false);
        Btninventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btninventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtninventarioActionPerformed(evt);
            }
        });
        getContentPane().add(Btninventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 240, 40));

        Btnusuario.setContentAreaFilled(false);
        Btnusuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(Btnusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 240, 40));

        Btndelivery.setContentAreaFilled(false);
        Btndelivery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btndelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtndeliveryActionPerformed(evt);
            }
        });
        getContentPane().add(Btndelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 240, 40));

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

        procesar_comp.setContentAreaFilled(false);
        procesar_comp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        procesar_comp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesar_compActionPerformed(evt);
            }
        });
        getContentPane().add(procesar_comp, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 630, 140, 40));

        comp_inicial.setBackground(new java.awt.Color(228, 228, 228));
        comp_inicial.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        comp_inicial.setForeground(new java.awt.Color(198, 54, 53));
        comp_inicial.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        comp_inicial.setBorder(null);
        comp_inicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comp_inicialActionPerformed(evt);
            }
        });
        comp_inicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comp_inicialKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comp_inicialKeyTyped(evt);
            }
        });
        getContentPane().add(comp_inicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 185, 105, 30));

        comp_final_cp.setBackground(new java.awt.Color(228, 228, 228));
        comp_final_cp.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        comp_final_cp.setForeground(new java.awt.Color(198, 54, 53));
        comp_final_cp.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        comp_final_cp.setBorder(null);
        comp_final_cp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comp_final_cpActionPerformed(evt);
            }
        });
        comp_final_cp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comp_final_cpKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comp_final_cpKeyTyped(evt);
            }
        });
        getContentPane().add(comp_final_cp, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 185, 110, 30));

        B01_f.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        B01_f.setForeground(new java.awt.Color(198, 54, 53));
        B01_f.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        B01_f.setText("B01");
        B01_f.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B01_fMouseClicked(evt);
            }
        });
        getContentPane().add(B01_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 184, -1, 30));

        B01_i.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        B01_i.setForeground(new java.awt.Color(198, 54, 53));
        B01_i.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        B01_i.setText("B01");
        B01_i.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B01_iMouseClicked(evt);
            }
        });
        getContentPane().add(B01_i, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 184, -1, 30));

        fecha_comprobante.setBackground(new java.awt.Color(76, 76, 76));
        fecha_comprobante.setForeground(new java.awt.Color(255, 255, 255));
        fecha_comprobante.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        getContentPane().add(fecha_comprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 183, 120, 30));

        tipo_compro.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        tipo_compro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crédito Fiscal" }));
        tipo_compro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipo_comproItemStateChanged(evt);
            }
        });
        tipo_compro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipo_comproMouseClicked(evt);
            }
        });
        tipo_compro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_comproActionPerformed(evt);
            }
        });
        getContentPane().add(tipo_compro, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 129, 240, 30));

        tipo_ncf.setEditable(false);
        tipo_ncf.setBackground(new java.awt.Color(228, 228, 228));
        tipo_ncf.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        tipo_ncf.setForeground(new java.awt.Color(198, 54, 53));
        tipo_ncf.setBorder(null);
        tipo_ncf.setCaretColor(new java.awt.Color(255, 255, 255));
        tipo_ncf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_ncfActionPerformed(evt);
            }
        });
        tipo_ncf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tipo_ncfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tipo_ncfKeyTyped(evt);
            }
        });
        getContentPane().add(tipo_ncf, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 275, 220, 30));

        siguiente_nfc.setEditable(false);
        siguiente_nfc.setBackground(new java.awt.Color(228, 228, 228));
        siguiente_nfc.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        siguiente_nfc.setForeground(new java.awt.Color(198, 54, 53));
        siguiente_nfc.setBorder(null);
        siguiente_nfc.setCaretColor(new java.awt.Color(255, 255, 255));
        siguiente_nfc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguiente_nfcActionPerformed(evt);
            }
        });
        siguiente_nfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                siguiente_nfcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                siguiente_nfcKeyTyped(evt);
            }
        });
        getContentPane().add(siguiente_nfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 275, 180, 30));

        ultimo_nfc.setEditable(false);
        ultimo_nfc.setBackground(new java.awt.Color(228, 228, 228));
        ultimo_nfc.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        ultimo_nfc.setForeground(new java.awt.Color(198, 54, 53));
        ultimo_nfc.setBorder(null);
        ultimo_nfc.setCaretColor(new java.awt.Color(255, 255, 255));
        ultimo_nfc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ultimo_nfcActionPerformed(evt);
            }
        });
        ultimo_nfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ultimo_nfcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ultimo_nfcKeyTyped(evt);
            }
        });
        getContentPane().add(ultimo_nfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1102, 275, 180, 30));

        caducacion_nfc.setEditable(false);
        caducacion_nfc.setBackground(new java.awt.Color(228, 228, 228));
        caducacion_nfc.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        caducacion_nfc.setForeground(new java.awt.Color(198, 54, 53));
        caducacion_nfc.setBorder(null);
        caducacion_nfc.setCaretColor(new java.awt.Color(255, 255, 255));
        caducacion_nfc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caducacion_nfcActionPerformed(evt);
            }
        });
        caducacion_nfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                caducacion_nfcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                caducacion_nfcKeyTyped(evt);
            }
        });
        getContentPane().add(caducacion_nfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 306, 220, 30));

        disponible_nfc.setEditable(false);
        disponible_nfc.setBackground(new java.awt.Color(228, 228, 228));
        disponible_nfc.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        disponible_nfc.setForeground(new java.awt.Color(198, 54, 53));
        disponible_nfc.setBorder(null);
        disponible_nfc.setCaretColor(new java.awt.Color(255, 255, 255));
        disponible_nfc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disponible_nfcActionPerformed(evt);
            }
        });
        disponible_nfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                disponible_nfcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                disponible_nfcKeyTyped(evt);
            }
        });
        getContentPane().add(disponible_nfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 306, 190, 30));

        limpiar_btn.setBorder(null);
        limpiar_btn.setContentAreaFilled(false);
        limpiar_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        limpiar_btn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                limpiar_btnMouseMoved(evt);
            }
        });
        limpiar_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                limpiar_btnMouseExited(evt);
            }
        });
        limpiar_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar_btnActionPerformed(evt);
            }
        });
        getContentPane().add(limpiar_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 630, 100, 30));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tabla_comprobante.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        tabla_comprobante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Comprobante", "No. Factura", "Cliente", "                                   Monto", "                 Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_comprobante.setSelectionBackground(new java.awt.Color(128, 230, 121));
        tabla_comprobante.setShowHorizontalLines(false);
        tabla_comprobante.setShowVerticalLines(false);
        tabla_comprobante.getTableHeader().setResizingAllowed(false);
        tabla_comprobante.getTableHeader().setReorderingAllowed(false);
        tabla_comprobante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_comprobanteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_comprobante);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 420, 950, 192));

        jPanel2.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 40, 40));

        jLabel1.setBackground(java.awt.Color.gray);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/comprobante.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, -1, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnfacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnfacturacionActionPerformed
        if(Clase_Variable_Publica.permiso_fact==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar comprobantes?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            Facturacion fct = new Facturacion();
            fct.setVisible(true);
            Facturacion.nombre_usu_fact.setText(nombre_usu_comp.getText());
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
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar comprobantes?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            Articulo art = new Articulo();
            art.setVisible(true);
            Articulo.nombre_usu_art.setText(nombre_usu_comp.getText());
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
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar comprobantes?",
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
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar comprobante?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            Inventario inv = new Inventario();
            inv.setVisible(true);
            Inventario.nombre_usu_inv.setText(nombre_usu_comp.getText());
            this.dispose();
        }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para inventario");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtninventarioActionPerformed

    private void BtnusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnusuarioActionPerformed
        if(Clase_Variable_Publica.permiso_usu==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar comprobante?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            Usuarios usu = new Usuarios();
            usu.setVisible(true);
            Usuarios.nombre_usu_usu.setText(nombre_usu_comp.getText());
            this.dispose();
        }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para usuarios");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnusuarioActionPerformed

    private void BtndeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtndeliveryActionPerformed
        if(Clase_Variable_Publica.permiso_delv==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar comprobante?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            Delivery del = new Delivery();
            del.setVisible(true);
            Delivery.nombre_usu_delv.setText(nombre_usu_comp.getText());
            this.dispose();
        }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para delivery");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtndeliveryActionPerformed

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

    private void procesar_compActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesar_compActionPerformed
int d = comp_inicial.getText().length();
int c = comp_final_cp.getText().length();
Fecha();
if(!comp_inicial.getText().isEmpty() && !comp_final_cp.getText().isEmpty() && !fecha_in.getText().isEmpty()){
        if(d<8 || c<8){
            if(d<8){
            JOptionPane.showMessageDialog(null, "El Campo del comprobante inicial está incompleto debe ingresar 8 dígitos después del identificador");
            comp_inicial.requestFocus();
            }
            if(c<8){
            JOptionPane.showMessageDialog(null, "El Campo del comprobante final está incompleto debe ingresar 8 dígitos después del identificador");
            comp_final_cp.requestFocus();
            }
        }else{
             prueba pb = new prueba();
            pb.confirmar_fiscal();

        }
}else{
    JOptionPane.showMessageDialog(rootPane, "Debes de llenar todos los campos");
    if(comp_inicial.getText().isEmpty()){
        comp_inicial.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
        comp_inicial.requestFocus();
    }
     if(comp_final_cp.getText().isEmpty()){
        comp_final_cp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
        comp_final_cp.requestFocus();
    }
     if(fecha_in.getText().isEmpty()){
        fecha_in.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
        fecha_in.requestFocus();
    }
}
// TODO add your handling code here:
    }//GEN-LAST:event_procesar_compActionPerformed

    private void comp_inicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comp_inicialActionPerformed
        comp_final_cp.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_comp_inicialActionPerformed

    private void comp_inicialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comp_inicialKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_comp_inicialKeyReleased

    private void comp_inicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comp_inicialKeyTyped
        char c = evt.getKeyChar();
        if ((c<'0' || c>'9')) evt.consume();

        if(comp_inicial.getText().length() >= 8){
            evt.consume();
        }// TODO add your handling code here:
    }//GEN-LAST:event_comp_inicialKeyTyped

    private void comp_final_cpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comp_final_cpActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_comp_final_cpActionPerformed

    private void comp_final_cpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comp_final_cpKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_comp_final_cpKeyReleased

    private void comp_final_cpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comp_final_cpKeyTyped
        char c = evt.getKeyChar();
        if ((c<'0' || c>'9')) evt.consume();
        if(comp_final_cp.getText().length() >= 8){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_comp_final_cpKeyTyped

    private void B01_fMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B01_fMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_B01_fMouseClicked

    private void B01_iMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B01_iMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_B01_iMouseClicked

    private void tipo_comproItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipo_comproItemStateChanged
    // TODO add your handling code here:
    }//GEN-LAST:event_tipo_comproItemStateChanged

    private void tipo_comproMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipo_comproMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_comproMouseClicked

    private void tipo_comproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_comproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_comproActionPerformed

    private void tipo_ncfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_ncfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_ncfActionPerformed

    private void tipo_ncfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipo_ncfKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_ncfKeyReleased

    private void tipo_ncfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipo_ncfKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_ncfKeyTyped

    private void siguiente_nfcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguiente_nfcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siguiente_nfcActionPerformed

    private void siguiente_nfcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_siguiente_nfcKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_siguiente_nfcKeyReleased

    private void siguiente_nfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_siguiente_nfcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_siguiente_nfcKeyTyped

    private void ultimo_nfcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ultimo_nfcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ultimo_nfcActionPerformed

    private void ultimo_nfcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ultimo_nfcKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ultimo_nfcKeyReleased

    private void ultimo_nfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ultimo_nfcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ultimo_nfcKeyTyped

    private void caducacion_nfcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caducacion_nfcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caducacion_nfcActionPerformed

    private void caducacion_nfcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caducacion_nfcKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_caducacion_nfcKeyReleased

    private void caducacion_nfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caducacion_nfcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_caducacion_nfcKeyTyped

    private void disponible_nfcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disponible_nfcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disponible_nfcActionPerformed

    private void disponible_nfcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_disponible_nfcKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_disponible_nfcKeyReleased

    private void disponible_nfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_disponible_nfcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_disponible_nfcKeyTyped

    private void tabla_comprobanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_comprobanteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_comprobanteMouseClicked

    private void limpiar_btnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiar_btnMouseMoved
        //limpiar_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));        // TODO add your handling code here:
    }//GEN-LAST:event_limpiar_btnMouseMoved

    private void limpiar_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiar_btnMouseExited
        //limpiar_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(76, 76, 76)));         // TODO add your handling code here:
    }//GEN-LAST:event_limpiar_btnMouseExited

    private void limpiar_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar_btnActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea limpiar los campos?",
            "Limpiar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
        comp_inicial.setText("");
        comp_final_cp.setText("");
        fecha_in.setText("");
        tipo_ncf.setText("");
        siguiente_nfc.setText("");
        ultimo_nfc.setText("");
        disponible_nfc.setText("");
        caducacion_nfc.setText("");
        comp_inicial.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.DARK_GRAY));
        comp_final_cp.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.DARK_GRAY));
        fecha_in.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.DARK_GRAY));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_limpiar_btnActionPerformed

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
            java.util.logging.Logger.getLogger(Comprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comprobantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comprobantes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel B01_f;
    private javax.swing.JLabel B01_i;
    private javax.swing.JButton Btnarticulo;
    private javax.swing.JButton Btndelivery;
    private javax.swing.JButton Btndespacho;
    private javax.swing.JButton Btnfacturacion;
    private javax.swing.JButton Btninventario;
    private javax.swing.JButton Btnusuario;
    private javax.swing.JButton btn_minimizar;
    public static javax.swing.JTextField caducacion_nfc;
    public static javax.swing.JTextField comp_final_cp;
    public static javax.swing.JTextField comp_inicial;
    public static javax.swing.JTextField disponible_nfc;
    public static com.toedter.calendar.JDateChooser fecha_comprobante;
    public static javax.swing.JTextField fecha_in;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiar_btn;
    public static javax.swing.JLabel nombre_usu_comp;
    private javax.swing.JButton procesar_comp;
    public static javax.swing.JTextField siguiente_nfc;
    public static javax.swing.JTable tabla_comprobante;
    public static javax.swing.JComboBox<String> tipo_compro;
    public static javax.swing.JTextField tipo_ncf;
    public static javax.swing.JTextField ultimo_nfc;
    private javax.swing.JButton volverAtras;
    // End of variables declaration//GEN-END:variables
    private Connection conexion = null;
    conector cc = new conector();
    Connection cn = cc.conexion();
}
