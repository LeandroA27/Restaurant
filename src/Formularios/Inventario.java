/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.clsExportarExcel;
import Clases.conexion_2;
import Clases.render_tabla_inv;
import Clases.reporte_inventario;
import static Formularios.Consulta_Producto.tabla_servicios;
import static Formularios.Menu_Principal.nombre_usu_cli;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Inventario extends javax.swing.JFrame {

Fuentes tipofuente;
    public Inventario() {
        initComponents();
        Clase_Variable_Publica.modulo = 4;
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0,0,0,0));
        setCellRender(tb_inventario);
        
                tipofuente = new Fuentes();
        nombre_usu_inv.setFont(tipofuente.fuente(tipofuente.RIO, 0, 16));
        total_e.setFont(tipofuente.fuente(tipofuente.RIO, 0, 20));

        //menu_fecha1.setFont(tipofuente.fuente(tipofuente.RIO,0,16));
        total_A.setFont(tipofuente.fuente(tipofuente.RIO,0,20));
        articulo_text.setFont(tipofuente.fuente(tipofuente.RIO,0,18));
        marca_text.setFont(tipofuente.fuente(tipofuente.RIO,0,18));
        cantidad_text.setFont(tipofuente.fuente(tipofuente.RIO,0,18));
        Tipo_inventrio.setFont(tipofuente.fuente(tipofuente.RIO, 0, 16));
        
         try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            cargar();
            Total_existencia();
            Monto_inventario();
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar inventario?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            this.dispose();
    }
public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new render_tabla_inv());
        }
    }
void cargar() throws SQLException {
    DefaultTableModel modelo2 =(DefaultTableModel) tb_inventario.getModel();
    modelo2.getDataVector().clear();
    DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        float Costo,total_pago;
    String[] registros = new String[10];
    String sql = "SELECT cod_art,descripcion,suplidor,marca,ubicacion,costo,precio,existencia,tipo FROM articulo order by descripcion ASC";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        Costo = Float.parseFloat(rs.getString("costo"));
        total_pago = Float.parseFloat(rs.getString("precio"));
        registros[0] = rs.getString("cod_art");
        registros[1] = rs.getString("descripcion");
        registros[2] = rs.getString("suplidor");
        registros[3] = rs.getString("marca");
        registros[4] = rs.getString("ubicacion");
        registros[5] = formateador.format(Costo);
        registros[6] = formateador.format(total_pago);
        registros[7] = rs.getString("existencia");
        registros[8] = rs.getString("tipo");
                                                
        
        modelo2.addRow(registros);
    }   
       tb_inventario.setModel(modelo2);
}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }

}
public void Total_existencia() throws SQLException{
      PreparedStatement encabezado_= null;
      String[] registros = new String[5];
    String sql = "SELECT SUM(existencia) as total FROM articulo WHERE existencia >0";
try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("total");
        total_e.setText(registros[0]);
        
    }
  }catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
  }
public void Monto_inventario() throws SQLException{
      PreparedStatement encabezado_= null;
      String[] registros = new String[5];
      DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");  
    String sql = "SELECT SUM(costo) as total FROM articulo WHERE existencia >0";
    double total;
try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        total = Double.parseDouble(rs.getString("total"));
        registros[1] = formateador.format(total);
        
        total_A.setText(registros[1]);
        
    }
  }catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
  }
void transaccion() {
        String seleccion, cliente;
         try {
            filtro_1(articulo_text.getText(), marca_text.getText(), cantidad_text.getText(),Tipo_inventrio.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
void filtro_1(String valor, String valor2, String valor3,String valor4) throws SQLException {
        String tipo = "Credito";
        if (valor4.equals("Todos")){
            valor4="";
        }
        DefaultTableModel modelo2 = (DefaultTableModel) tb_inventario.getModel();
        modelo2.getDataVector().clear();
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        String[] registros = new String[12];
        float Costo,total_pago,total_p = 0;
        int existencia,total_ex = 0;
        
        final String sql = "SELECT cod_art,descripcion,suplidor,marca,ubicacion,costo,precio,existencia,tipo FROM articulo "
                + "where CONCAT (descripcion) LIKE '%" + valor + "%' && (marca) LIKE '%" + valor2 + "%' && existencia LIKE '%" + valor3 + "%'&& tipo LIKE '%" + valor4 + "%' ORDER BY cod_art ASC";
        try {
           Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
         Costo = Float.parseFloat(rs.getString("costo"));
        total_pago = Float.parseFloat(rs.getString("precio"));
        existencia = Integer.parseInt(rs.getString("existencia"));
        registros[0] = rs.getString("cod_art");
        registros[1] = rs.getString("descripcion");
        registros[2] = rs.getString("suplidor");
        registros[3] = rs.getString("marca");
        registros[4] = rs.getString("ubicacion");
        registros[5] = formateador.format(Costo);
        registros[6] = formateador.format(total_pago);
        registros[7] = rs.getString("existencia");
        registros[8] = rs.getString("tipo");

                modelo2.addRow(registros);
                total_p= total_p + Costo;
                total_ex= total_ex + existencia;
                
            }
            tb_inventario.setModel(modelo2);
            total_A.setText(formateador.format(total_p));
            total_e.setText(String.valueOf(total_ex));
            //tabla_int_CXC.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
void imprimir1(){
       String itbis;
       float n1, n2;
        ArrayList lista = new ArrayList();
        //List <Factura>lista = new ArrayList<>();
        //lista.add("");
        
        for (int i = 0; i<tb_inventario.getRowCount(); i++){
             
            
        reporte_inventario mortizar = new reporte_inventario (tb_inventario.getValueAt(i,0).toString()+"",tb_inventario.getValueAt(i,1).toString()+"",tb_inventario.getValueAt(i,2).toString()+"",tb_inventario.getValueAt(i,4).toString()+"",tb_inventario.getValueAt(i,5).toString()+"",tb_inventario.getValueAt(i,6).toString()+"",tb_inventario.getValueAt(i,7).toString()+"");
        lista.add(mortizar);
        
    }
        JasperReport jr=null;
        try{
            
            jr = (JasperReport) JRLoader.loadObjectFromFile("reporte_cxcjrxml.jasper");
        
                HashMap parametro = new HashMap();
                parametro.put("usuario", nombre_usu_inv.getText());
                parametro.put("total_e", total_e.getText());
                parametro.put("monto_i", total_A.getText());
      
                //parametro.put("Logo", this.getClass().getResourceAsStream(Logo));
                //parametro.put("Titulo", this.getClass().getResourceAsStream(Titulo));
                
        JasperPrint jp = JasperFillManager.fillReport(jr, parametro,new JRBeanCollectionDataSource(lista));
       //JasperPrint jp = JasperFillManager.fillReport(jr, null, new JRBeanCollectionDataSource(lista));
        JasperViewer jv = new JasperViewer(jp,false);
        jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jv.setVisible(true);
        
        }catch(JRException ex){
            ex.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "ERROR\n" + ex.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        volverAtras = new javax.swing.JButton();
        btn_minimizar = new javax.swing.JButton();
        nombre_usu_inv = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Btnfacturacion = new javax.swing.JButton();
        Btnarticulo = new javax.swing.JButton();
        Btndespacho = new javax.swing.JButton();
        Btndelivery = new javax.swing.JButton();
        Btncomprobantes = new javax.swing.JButton();
        Btnusuario = new javax.swing.JButton();
        total_A = new javax.swing.JLabel();
        total_e = new javax.swing.JLabel();
        articulo_text = new javax.swing.JTextField();
        marca_text = new javax.swing.JTextField();
        cantidad_text = new javax.swing.JTextField();
        consultar_cxc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_inventario = new javax.swing.JTable();
        Imprimir_inv = new javax.swing.JButton();
        exportar_cxc = new javax.swing.JButton();
        Tipo_inventrio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        getContentPane().add(nombre_usu_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 258, 160, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 258, 170, 20));

        Btnfacturacion.setContentAreaFilled(false);
        Btnfacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnfacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnfacturacionActionPerformed(evt);
            }
        });
        getContentPane().add(Btnfacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 240, 40));

        Btnarticulo.setContentAreaFilled(false);
        Btnarticulo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnarticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnarticuloActionPerformed(evt);
            }
        });
        getContentPane().add(Btnarticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 240, 40));

        Btndespacho.setContentAreaFilled(false);
        Btndespacho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btndespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtndespachoActionPerformed(evt);
            }
        });
        getContentPane().add(Btndespacho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 240, 40));

        Btndelivery.setContentAreaFilled(false);
        Btndelivery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btndelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtndeliveryActionPerformed(evt);
            }
        });
        getContentPane().add(Btndelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 240, 40));

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

        total_A.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        total_A.setForeground(new java.awt.Color(198, 54, 53));
        total_A.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(total_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 635, 200, 30));

        total_e.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        total_e.setForeground(new java.awt.Color(198, 54, 53));
        total_e.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(total_e, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 599, 190, 30));

        articulo_text.setBackground(new java.awt.Color(228, 228, 228));
        articulo_text.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        articulo_text.setForeground(new java.awt.Color(198, 54, 53));
        articulo_text.setBorder(null);
        articulo_text.setCaretColor(new java.awt.Color(255, 255, 255));
        articulo_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                articulo_textActionPerformed(evt);
            }
        });
        getContentPane().add(articulo_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 162, 190, 30));

        marca_text.setBackground(new java.awt.Color(228, 228, 228));
        marca_text.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        marca_text.setForeground(new java.awt.Color(198, 54, 53));
        marca_text.setBorder(null);
        marca_text.setCaretColor(new java.awt.Color(255, 255, 255));
        marca_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marca_textActionPerformed(evt);
            }
        });
        getContentPane().add(marca_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 162, 190, 30));

        cantidad_text.setBackground(new java.awt.Color(228, 228, 228));
        cantidad_text.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        cantidad_text.setForeground(new java.awt.Color(198, 54, 53));
        cantidad_text.setBorder(null);
        cantidad_text.setCaretColor(new java.awt.Color(255, 255, 255));
        cantidad_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidad_textActionPerformed(evt);
            }
        });
        getContentPane().add(cantidad_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 162, 100, 30));

        consultar_cxc.setBorder(null);
        consultar_cxc.setContentAreaFilled(false);
        consultar_cxc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        consultar_cxc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar_cxcActionPerformed(evt);
            }
        });
        getContentPane().add(consultar_cxc, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 165, 20, 20));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N

        tb_inventario.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        tb_inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "  Codigo", "Descripcion", "         Suplidor", "           Marca", "      Ubicacion", "                     Costo", "                     Precio", "    Existencia", "   Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_inventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tb_inventario.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tb_inventario.setDragEnabled(true);
        tb_inventario.setSelectionBackground(new java.awt.Color(198, 54, 53));
        tb_inventario.setShowHorizontalLines(false);
        tb_inventario.setShowVerticalLines(false);
        tb_inventario.getTableHeader().setResizingAllowed(false);
        tb_inventario.getTableHeader().setReorderingAllowed(false);
        tb_inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_inventarioMouseClicked(evt);
            }
        });
        tb_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_inventarioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_inventario);
        if (tb_inventario.getColumnModel().getColumnCount() > 0) {
            tb_inventario.getColumnModel().getColumn(0).setResizable(false);
            tb_inventario.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_inventario.getColumnModel().getColumn(1).setResizable(false);
            tb_inventario.getColumnModel().getColumn(1).setPreferredWidth(200);
            tb_inventario.getColumnModel().getColumn(2).setResizable(false);
            tb_inventario.getColumnModel().getColumn(2).setPreferredWidth(100);
            tb_inventario.getColumnModel().getColumn(3).setResizable(false);
            tb_inventario.getColumnModel().getColumn(3).setPreferredWidth(100);
            tb_inventario.getColumnModel().getColumn(4).setResizable(false);
            tb_inventario.getColumnModel().getColumn(4).setPreferredWidth(80);
            tb_inventario.getColumnModel().getColumn(5).setResizable(false);
            tb_inventario.getColumnModel().getColumn(5).setPreferredWidth(100);
            tb_inventario.getColumnModel().getColumn(6).setResizable(false);
            tb_inventario.getColumnModel().getColumn(6).setPreferredWidth(100);
            tb_inventario.getColumnModel().getColumn(7).setResizable(false);
            tb_inventario.getColumnModel().getColumn(7).setPreferredWidth(80);
            tb_inventario.getColumnModel().getColumn(8).setResizable(false);
            tb_inventario.getColumnModel().getColumn(8).setPreferredWidth(50);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 206, 950, 378));

        Imprimir_inv.setBorder(null);
        Imprimir_inv.setContentAreaFilled(false);
        Imprimir_inv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Imprimir_inv.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Imprimir_invMouseMoved(evt);
            }
        });
        Imprimir_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Imprimir_invMouseExited(evt);
            }
        });
        Imprimir_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Imprimir_invActionPerformed(evt);
            }
        });
        getContentPane().add(Imprimir_inv, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 630, 130, 40));

        exportar_cxc.setBorder(null);
        exportar_cxc.setContentAreaFilled(false);
        exportar_cxc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportar_cxc.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                exportar_cxcMouseMoved(evt);
            }
        });
        exportar_cxc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exportar_cxcMouseExited(evt);
            }
        });
        exportar_cxc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportar_cxcActionPerformed(evt);
            }
        });
        getContentPane().add(exportar_cxc, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 630, 130, 40));

        Tipo_inventrio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Bebidas", "Comidas" }));
        Tipo_inventrio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        getContentPane().add(Tipo_inventrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 67, 150, 30));

        jLabel1.setBackground(java.awt.Color.gray);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Inventario.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, -1, 770));

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
        close();
        Clase_Variable_Publica.modulo = 0;// TODO add your handling code here:
    }//GEN-LAST:event_volverAtrasActionPerformed

    private void btn_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizarActionPerformed
        this.setState(Menu_Principal.ICONIFIED);          // TODO add your handling code here:
    }//GEN-LAST:event_btn_minimizarActionPerformed

    private void BtnfacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnfacturacionActionPerformed
        if(Clase_Variable_Publica.permiso_fact==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar inventario?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Facturacion fct = new Facturacion();
            fct.setVisible(true);
            Facturacion.nombre_usu_fact.setText(nombre_usu_inv.getText());
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
                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar inventario?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Articulo art = new Articulo();
            art.setVisible(true);
            Articulo.nombre_usu_art.setText(nombre_usu_inv.getText());
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
                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar inventario?",
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

    private void BtndeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtndeliveryActionPerformed
        if(Clase_Variable_Publica.permiso_delv==0){
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
                //        fm.conectar();
                //    } catch (SQLException ex) {
                //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar inventario?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Delivery del = new Delivery();
            del.setVisible(true);
            Delivery.nombre_usu_delv.setText(nombre_usu_inv.getText());
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
                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar inventario?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Comprobantes fact = new Comprobantes();
            fact.setVisible(true);
            Comprobantes.nombre_usu_comp.setText(nombre_usu_inv.getText());
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
                                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar inventario?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){ 
            Usuarios usu = new Usuarios();
            usu.setVisible(true);
            Usuarios.nombre_usu_usu.setText(nombre_usu_inv.getText());
            this.dispose();
                                }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para usuarios");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnusuarioActionPerformed

    private void tb_inventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_inventarioMouseClicked

        //    try{
            //        String numero_fact,fecha,total,espacio;
            //        if(fsel == -1){
                //            JOptionPane.showMessageDialog(null,"Debe seleccionar una factura","Advertencia",JOptionPane.WARNING_MESSAGE);
                //        }
            //        else{
                //            m = (DefaultTableModel) tb_cxc.getModel();
                //            numero_fact =tb_cxc.getValueAt(fsel,0).toString();
                //            fecha =tb_cxc.getValueAt(fsel,1).toString();
                //            total=tb_cxc.getValueAt(fsel,2).toString();
                //
                //            DefaultTableModel dtm = (DefaultTableModel) tb_cxc.getModel(); //TableProducto es el nombre de mi tabla ;)
            //            dtm.removeRow(tb_cxc.getSelectedRow());
            //
            //
            //            m = (DefaultTableModel) pagar.getModel();
            //            String filaelemento[] = {numero_fact,fecha,total};
            //            m.addRow(filaelemento);
            //           calcular_tabla1();
            //           calcular_tabla3();
            //            sumar_total1();
            //            sumar_total2();
            //               }
        //    }catch(Exception e){
        //
        //    }
    }//GEN-LAST:event_tb_inventarioMouseClicked

    private void tb_inventarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_inventarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_inventarioKeyPressed

    private void exportar_cxcMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportar_cxcMouseMoved
        //exportar_cxc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));         // TODO add your handling code here:
    }//GEN-LAST:event_exportar_cxcMouseMoved

    private void exportar_cxcMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportar_cxcMouseExited
        //exportar_cxc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));         // TODO add your handling code here:
    }//GEN-LAST:event_exportar_cxcMouseExited

    private void exportar_cxcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportar_cxcActionPerformed
        try {
            clsExportarExcel obj = new clsExportarExcel();
            obj.exportarExcel(tb_inventario);
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportar_cxcActionPerformed

    private void cantidad_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidad_textActionPerformed
transaccion();
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidad_textActionPerformed

    private void articulo_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_articulo_textActionPerformed
transaccion();         // TODO add your handling code here:
    }//GEN-LAST:event_articulo_textActionPerformed

    private void marca_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marca_textActionPerformed
transaccion();        // TODO add your handling code here:
    }//GEN-LAST:event_marca_textActionPerformed

    private void consultar_cxcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultar_cxcActionPerformed
transaccion();


    }//GEN-LAST:event_consultar_cxcActionPerformed

    private void Imprimir_invMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Imprimir_invMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Imprimir_invMouseMoved

    private void Imprimir_invMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Imprimir_invMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Imprimir_invMouseExited

    private void Imprimir_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Imprimir_invActionPerformed
imprimir1();        // TODO add your handling code here:
    }//GEN-LAST:event_Imprimir_invActionPerformed

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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnarticulo;
    private javax.swing.JButton Btncomprobantes;
    private javax.swing.JButton Btndelivery;
    private javax.swing.JButton Btndespacho;
    private javax.swing.JButton Btnfacturacion;
    private javax.swing.JButton Btnusuario;
    private javax.swing.JButton Imprimir_inv;
    private javax.swing.JComboBox<String> Tipo_inventrio;
    public static javax.swing.JTextField articulo_text;
    private javax.swing.JButton btn_minimizar;
    public static javax.swing.JTextField cantidad_text;
    private javax.swing.JButton consultar_cxc;
    private javax.swing.JButton exportar_cxc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField marca_text;
    public static javax.swing.JLabel nombre_usu_inv;
    public static javax.swing.JTable tb_inventario;
    public static javax.swing.JLabel total_A;
    public static javax.swing.JLabel total_e;
    private javax.swing.JButton volverAtras;
    // End of variables declaration//GEN-END:variables
conector cc = new conector();
    Connection cn = cc.conexion();
}
