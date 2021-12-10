/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.Conexion_factura_Modificar;
import Clases.Conexion_factura_guardar;
import Clases.Factura_1;
import Clases.conexion_2;
import Clases.funciones_factura;
import Clases.generador_numerico;
import Clases.peticion;
import Clases.render_tabla_prueba;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import restaurante.conector;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Facturacion extends javax.swing.JFrame implements Runnable {

    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Thread hi;
    
    Fuentes tipofuente;
     private final String Logo ="/Imagenes/Logo_calidad.png";
    private Connection conexion = null;
    private String codigoS = "";
    private String ub = Clase_Variable_Publica.ubicacion; 
    private String Monto_cuadre,cant_cuadre;
    public Facturacion() {
        initComponents();
        Clase_Variable_Publica.modulo = 1;
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0,0,0,0));
        cod_cli_fact.setText("00001");
        nom_cli_fact.setText("Cliente casual");
        precio_serv.setEditable(false);
        para_llevar.setSelected(true);
        cod_serv_fact.requestFocus();
        setCellRender(tablafacturacion1);
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
            setIconImage(icon);
            setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        fecha_fact1.setText(Clase_Variable_Publica.fechaDom);
        fecha_fact2.setText(Clase_Variable_Publica.fechaus);
        fecha_fact3.setText(Clase_Variable_Publica.fechaDom);
        descuento_rp.setText("- RD$ 0.00");
        descuento_front.setText("- RD$ 0.00");
        desc_serv.setEditable(false);
        tipo_letra();
        limpiar_tabla();
        sumar_total();
        
        
        if(ub.equals("Bar")){
            Consultar_num_bar();
        }else{
        Consultar_num_cot();    
        }
        
        Clase_Variable_Publica.cli = 1;
        Clase_Variable_Publica.art = 1;
        Clase_Variable_Publica.rnc = 1;
        Clase_Variable_Publica.cot = 3;
        Clase_Variable_Publica.fact = 0;
        tipo_comp.setSelectedItem("Consumidor Final");
        cant_compro();
        modificar_fact.setEnabled(false);
        desc_serv.setText("0.00");
        procesar_fact.setEnabled(false);
        limpiar_fact.setEnabled(false);
        modificar_fact.setEnabled(false);
        this.jLabel1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    Facturacion(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Facturacion(String string, String string0, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Facturacion(String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void tipo_letra() {
        tipofuente = new Fuentes();
        nombre_usu_fact.setFont(tipofuente.fuente(tipofuente.RIO, 0, 16));
        cod_cli_fact.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        nom_cli_fact.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        cod_serv_fact.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        descrii_fact.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        cant_fact.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        rnc_fact.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        precio_serv.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        desc_serv.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        fecha_fact1.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        num_fact_1.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        ncf_disp.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        tipo_comp.setFont(tipofuente.fuente(tipofuente.RIO, 0, 11));
        sub_total.setFont(tipofuente.fuente(tipofuente.RIO, 0, 12));
        itbis_fact.setFont(tipofuente.fuente(tipofuente.RIO, 0, 12));
        descuento_front.setFont(tipofuente.fuente(tipofuente.RIO, 0, 12));
        total_fact.setFont(tipofuente.fuente(tipofuente.RIO, 0, 20));
        ncf_f.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
        ncf_fgb.setFont(tipofuente.fuente(tipofuente.RIO, 0, 17));
    }
//////////////////////////////////////////////////////////////////////////////////////////////

    public void coparacion() {
        if (Clase_Variable_Publica.descuento_ft == 1) {
            desc_serv.setEnabled(false);
        } else {
        }
        if (Clase_Variable_Publica.editar_precio_ft == 1) {
            precio_serv.setEditable(false);
        } else {
        }
        if (Clase_Variable_Publica.comprobante == 1) {
            tipo_comp.setEnabled(false);
        } else {
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////

    public void cargar() {
        String cf = "Si";
        String var = "";
        String[] registros = new String[5];
        String sql = "(SELECT MIN(numero) AS numero, ncf FROM secuencia_ncf WHERE Disponible='" + cf + "')";

        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("ncf");

                ncf_f.setText(registros[0]);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////
    public void cant_compro() {
        String cont = "";
        String comp = "Si";
        String es = "";
        int compar = 0;
        int cant = 0;
        int clic = Clase_Variable_Publica.cot;
        String[] registros = new String[2];
        String sql = "SELECT count(ncf) as NCF from secuencia_ncf where Disponible='" + comp + "'";
        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                registros[0] = rs.getString("NCF");
                es = registros[0];
            }
            if (es.equals("0")) {
                JOptionPane.showMessageDialog(rootPane, "No hay números de comprobantes disponibles para las facturas de crédito fiscal, ¡Favor Registrar las Secuencias!");
                //tipo_comp.setEnabled(false);
                //tipo_comp.removeAll();
                //tipo_comp.setSelectedItem("Consumidor Final");
            }
            if (es.equals("5")) {
                JOptionPane.showMessageDialog(rootPane, "Solo hay 5 números de comprobantes disponibles para las facturas de crédito fiscal");
                cargar();
            }
            if (es.equals("3")) {
                JOptionPane.showMessageDialog(rootPane, "Solo hay 3 números de comprobantes disponibles para las facturas de crédito fiscal");
                cargar();
            }
            if (es.equals("2")) {
                JOptionPane.showMessageDialog(rootPane, "Solo hay 2 números de comprobantes disponibles para las facturas de crédito fiscal");
                cargar();
            }
            if (es.equals("1")) {
                JOptionPane.showMessageDialog(rootPane, "Solo hay 1 número de comprobante disponible para las facturas de crédito fiscal");
                cargar();
            } else {
                cargar();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////
    public void proceso_articulo() {
        if (cod_serv_fact.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar el código del servicio");
        } else {
            String cont = "";
            String comp = "";

            String es = "";
            int compar = 0;
            int cant = 0;
            comp = cod_serv_fact.getText();
            String[] registros = new String[2];
            String sql = "SELECT cod_art FROM articulo where cod_art='" + cod_serv_fact.getText() + "'";
            try {
                java.sql.Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    registros[0] = rs.getString("cod_art");
                    es = registros[0];
                }
                if (es.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Este articulo no existe");
                } else {
                    try {
                        cargar_pro();
                    } catch (SQLException ex) {
                        Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cant_fact.requestFocus();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////

    public void limpiar_tabla() {
        DefaultTableModel modelo3 = (DefaultTableModel) tablafacturacion1.getModel();
        modelo3.getDataVector().clear();
    }
/////////////////////8/////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////
    void cargar_pro() throws SQLException {
        String[] registros = new String[12];
        String sql = "SELECT cod_art,descripcion,precio FROM articulo where cod_art='" + cod_serv_fact.getText() + "'";
        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("cod_art");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("precio");

                cod_serv_fact.setText(registros[0]);
                descrii_fact.setText(registros[1]);
                precio_serv.setText(registros[2]);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
    void llenar_tabla() {
        DefaultTableModel modelo3 = (DefaultTableModel) tablafacturacion1.getModel();
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        float precio;
        String[] registros = new String[9];
        registros[0] = cod_serv_fact.getText();
        registros[1] = descrii_fact.getText();
        precio = Float.parseFloat(precio_serv.getText());
        registros[2] = formateador.format(precio);
        float pre = precio;
        float cantidad = Float.parseFloat(cant_fact.getText());
        float resultado = 0;
        float calculo = 0;
        float itbis1 = 0;
        calculo = (float) (pre * 0.18) * cantidad;
        itbis1 = calculo + pre;
        resultado = (pre * cantidad);

        float pre2 = precio;
        float itbis = 0;
        double monto = 0.18;
        itbis = (float) (pre2 * monto);
        registros[4] = formateador.format(calculo);
        registros[5] = formateador.format(resultado);
        registros[3] = cant_fact.getText();
        modelo3.addRow(registros);
        tablafacturacion1.setModel(modelo3);
        llenarTout1();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    void llenarTout1() {
        DefaultTableModel modelo3 = (DefaultTableModel) tablaF_out.getModel();
        float precio;
        String[] registros = new String[6];
        registros[0] = precio_serv.getText();
        float pre = Float.parseFloat(registros[0]);
        float cantidad = Float.parseFloat(cant_fact.getText());
        float resultado = 0;
        float calculo = 0;
        float itbis1 = 0;
        calculo = (float) (pre * 0.18) * cantidad;;
        itbis1 = calculo + pre;
        resultado = (pre * cantidad);

        float pre2 = Float.parseFloat(registros[0]);
        float itbis = 0;
        double monto = 0.18;
        itbis = (float) (pre2 * monto);
        registros[1] = String.valueOf(calculo);
        registros[2] = String.valueOf(resultado);
        registros[3] = cod_serv_fact.getText();
        modelo3.addRow(registros);
        tablaF_out.setModel(modelo3);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    void llenar_tabla1() {
        DefaultTableModel modelo3 = (DefaultTableModel) tablafacturacion1.getModel();
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        float precio;
        String[] registros = new String[6];
        registros[0] = cod_serv_fact.getText();
        registros[1] = descrii_fact.getText();
        precio = Float.parseFloat(precio_serv.getText());
        registros[2] = formateador.format(precio);
        float pre = precio;
        float cantidad = Float.parseFloat(cant_fact.getText());
        float resultado = 0;
        float calculo = 0;
        calculo = (float) (pre * 0.18) * cantidad;;
        resultado = pre * cantidad;

        float pre2 = precio;
        float itbis = 0;
        double monto = 0.18;
        itbis = (float) (pre2 * monto);
        registros[4] = formateador.format(0.0);
        registros[5] = formateador.format(resultado);
        registros[3] = cant_fact.getText();
        modelo3.addRow(registros);
        tablafacturacion1.setModel(modelo3);
        llenarTout2();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    void llenarTout2() {
        DefaultTableModel modelo3 = (DefaultTableModel) tablaF_out.getModel();
        float precio;
        String[] registros = new String[6];
        registros[0] = precio_serv.getText();
        float pre = Float.parseFloat(registros[0]);
        float cantidad = Float.parseFloat(cant_fact.getText());
        float resultado = 0;
        float calculo = 0;
        float itbis1 = 0;
        calculo = (float) (pre * 0.18) * cantidad;;
        itbis1 = calculo + pre;
        resultado = pre * cantidad;

        float pre2 = Float.parseFloat(registros[0]);
        float itbis = 0;
        double monto = 0.18;
        itbis = (float) (pre2 * monto);
        registros[1] = String.valueOf("0.0");
        registros[2] = String.valueOf(resultado);
        registros[3] = cod_serv_fact.getText();
        modelo3.addRow(registros);
        tablaF_out.setModel(modelo3);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void sumar_total() {
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        DefaultTableModel modelo3 = (DefaultTableModel) tablafacturacion1.getModel();
        String Vtotal = "";
        String valor = "";
        String itbis = "";
        float sub_total = 0.0f, sub_total3 = 0.0f;
        String jitbis = "";
        float itbis_total = 0.0f;
        float itbis_total3 = 0.0f;
        float valor_total = 0.0f;
        float gran_total = 0.0f;
        String total_venta = "";
        float subtotal_precio = 0.0f;

        float cantidad1 = 0.0f;
        String cantidad2 = "";

        for (int i = 0; i < tablaF_out.getRowCount(); i++) {
            Vtotal = (tablaF_out.getValueAt(i, 0).toString());
            sub_total = Float.parseFloat(Vtotal);

            itbis = (tablaF_out.getValueAt(i, 1).toString());
            itbis_total = Float.parseFloat(itbis);

            valor = (tablaF_out.getValueAt(i, 2).toString());
            valor_total = Float.parseFloat(valor);

            cantidad2 = (tablafacturacion1.getValueAt(i, 3).toString());
            cantidad1 = Float.parseFloat(cantidad2);

            subtotal_precio = subtotal_precio + (valor_total - itbis_total);

            itbis_total3 = itbis_total3 + itbis_total;

            gran_total = subtotal_precio + itbis_total3;
        }
        Facturacion.sub_total.setText(formateador.format(subtotal_precio));
        itbis_fact.setText(formateador.format(itbis_total3));
        total_devueltas.setText(String.valueOf(gran_total));
        total_fact.setText(formateador.format(gran_total));
        sub_total_ft.setText(String.valueOf(subtotal_precio));
        itbis_ft.setText(String.valueOf(itbis_total3));
        desc_serv.setText("0.00");
    }
    //////////////////////////////////////////////////////////

    public void sumar_total1() {
        DecimalFormat formateador = new DecimalFormat("RD#,##0.00");
        DefaultTableModel modelo3 = (DefaultTableModel) tablafacturacion1.getModel();
        String jtotal = "";
        String jtotal2 = "";
        String jtotal1 = "";
        double sub_total2 = 0, sub_total3 = 0;
        String jitbis = "";
        double itbis_total2 = (float) 0.0;
        double itbis_total3 = 0;
        double gran_total = 0;
        double gran_total2 = 0;
        String total_venta = "";
        double total_precio1 = 0;

        double cantidad1 = (float) 0.0;
        String cantidad2 = "";

        for (int i = 0; i < tablafacturacion1.getRowCount(); i++) {
            jtotal = (tablafacturacion1.getValueAt(i, 5).toString());
            sub_total2 = Double.parseDouble(jtotal);

            jtotal1 = (tablafacturacion1.getValueAt(i, 3).toString());
            itbis_total2 = Double.parseDouble(jtotal1);

            jtotal2 = (tablafacturacion1.getValueAt(i, 2).toString());
            gran_total = Double.parseDouble(jtotal2);

            total_precio1 = total_precio1 + sub_total2;

            itbis_total3 = itbis_total3 + itbis_total2;

            gran_total2 = gran_total2 + gran_total;
        }
        sub_total.setText(formateador.format(gran_total2));
        itbis_fact.setText(formateador.format(itbis_total3));
        total_devueltas.setText(String.valueOf(total_precio1));
        total_fact.setText(formateador.format(total_precio1));
        sub_total_ft.setText(String.valueOf(gran_total2));
        itbis_ft.setText(String.valueOf(itbis_total3));
    }

    public void descuento() {
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        DecimalFormat formateador1 = new DecimalFormat("- RD$ #,##0.00");
        DefaultTableModel modelo3 = (DefaultTableModel) tablafacturacion1.getModel();
        String jtotal = desc_serv.getText();
        String jtotal2 = total_devueltas.getText();
        String jtotal1 = "";
        double subtotal = Double.parseDouble(jtotal2);
        double descuento = Double.parseDouble(jtotal);
        double total = 0;
        double gran_total2 = 0;
        double itbis = 0;

        gran_total2 = subtotal - descuento;

        itbis = gran_total2 * 0.18;

        total = gran_total2 + itbis;

        //sub_total.setText(formateador.format(gran_total2));
        itbis_fact.setText(formateador.format(itbis));
        total_fact.setText(formateador.format(total));
        descuento_rp.setText(formateador1.format(descuento));
        descuento_front.setText(formateador1.format(descuento));
        total_devueltas.setText(String.valueOf(total));
        //sub_total_ft.setText(String.valueOf(gran_total2));
        itbis_ft.setText(String.valueOf(itbis));

    }
    ////////////////////////////////////////////////////////////////////////

    public void descuento_sinitbs() {
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
        DecimalFormat formateador1 = new DecimalFormat("- RD$ #,##0.00");
        DefaultTableModel modelo3 = (DefaultTableModel) tablafacturacion1.getModel();
        String jtotal = desc_serv.getText();
        String jtotal2 = total_devueltas.getText();
        String jtotal1 = "";
        double subtotal = Double.parseDouble(jtotal2);
        double descuento = Double.parseDouble(jtotal);
        double total = 0;
        double gran_total2 = 0;
        double itbis = 0;

        gran_total2 = subtotal - descuento;

        //sub_total.setText(formateador.format(gran_total2));
        //itbis_fact.setText(formateador.format(itbis));
        total_fact.setText(formateador.format(gran_total2));
        descuento_rp.setText(formateador1.format(descuento));
        descuento_front.setText(formateador1.format(descuento));
        total_devueltas.setText(String.valueOf(gran_total2));

        //sub_total_ft.setText(String.valueOf(gran_total2));
        //itbis_ft.setText(String.valueOf(itbis));
    }
    /////////////////////////////////////////////////////////

    void limpiar() {
        cod_serv_fact.setText("");
        descrii_fact.setText("");
        cant_fact.setText("");
        precio_serv.setText("");
    }

    void limpiar1() {
        cod_cli_fact.setText("");
        nom_cli_fact.setText("");
        cod_serv_fact.setText("");
        descrii_fact.setText("");
        cant_fact.setText("");
        rnc_fact.setText("");
        precio_serv.setText("");
        ncf_anterior.setText("");
        tiponcf_anterior.setText("");
        descuento_rp.setText("- RD$ 0.00");
        descuento_front.setText("- RD$ 0.00");
        desc_serv.setText("0.00");
        cod_cli_fact.setText("00001");
        nom_cli_fact.setText("Cliente casual");
        desc_serv.setEditable(false);
        cuenta_abierta.setSelected(false);
            enviar_delivery.setSelected(false);
        Consultar_num_cot();
        DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel();
        cod_serv_fact.requestFocus();
        dtm.setRowCount(0);
        Clase_Variable_Publica.fact = 0;
        modificar_fact.setEnabled(false);
        precio_serv.setEditable(false);
        String comparacion = (String) tipo_comp.getSelectedItem();

        if (comparacion.equals("Crédito Fiscal")) {
            ncf_disp.setText(ncf_f.getText());
        }
        if (comparacion.equals("Consumidor Final")) {
            ncf_disp.setText("");
        }
    }
    //////////////////////////////////////////////////////////////////////

    public void Consultar_num_cot() {
        int l;
        int cont = 1;
        String num = "";
        String es = "";
        String SQL = "SELECT MAX(numero)AS numero FROM contador_facturas";

        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                es = rs.getString(1);
            }

            if (es == null) {
                Facturacion.num_fact_1.setText("R00001");

            } else {
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var = Integer.parseInt(es);

                generador_numerico p = new generador_numerico();
                p.Generar_rest(var);

                //JOptionPane.showMessageDialog(null, Clase_Variable_Publica.contador);
                Facturacion.num_fact_1.setText(p.serie_rest());

            }
        } catch (Exception e) {
        }
    }
    public void Consultar_num_bar(){
        int l;
        int cont = 1;
        String num = "";
        String es = "";
        String SQL = "SELECT MAX(contador)AS contador FROM contador_factura_bar";

        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                es = rs.getString(1);
            }

            if (es == null) {
                num_fact_1.setText("B00001");

            } else {
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var = Integer.parseInt(es);

                generador_numerico p = new generador_numerico();
                p.Generar_Bar(var);

                //JOptionPane.showMessageDialog(null, Clase_Variable_Publica.contador);
                num_fact_1.setText(p.serie_bar());

            }
        } catch (Exception e) {
        }
    }  
    /////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void desbloquear() {
        cod_cli_fact.setEditable(true);
        nom_cli_fact.setEditable(true);
        cod_serv_fact.setEditable(true);
        descrii_fact.setEditable(true);
        cant_fact.setEditable(true);
        rnc_fact.setEditable(true);
        precio_serv.setEditable(true);
        desc_serv.setEditable(true);
        Facturacion.tipo_comp.setEnabled(true);
        rnc_fact.setEditable(true);
        Facturacion.buscar_rnc.setEnabled(true);
        tablafacturacion1.setEnabled(true);
        Facturacion.buscar_serv.setEnabled(true);
        para_llevar.setEnabled(true);
        para_comerAqui.setEnabled(true);
        reimprimir_fact.setEnabled(true);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void Confimacion_factura_fiscal() throws SQLException {
        String cont = "";
        String comp = "Si";
        String es = "";
        int compar = 0;
        int cant = 0;
        int clic = Clase_Variable_Publica.cot;
        String[] registros = new String[2];
        String sql = "SELECT count(ncf) as NCF from secuencia_ncf where Disponible='" + comp + "'";
        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                registros[0] = rs.getString("NCF");
                es = registros[0];
            }
            if (es.equals("0")) {
                JOptionPane.showMessageDialog(null, "No hay números de comprobantes disponibles para las facturas de crédito fiscal, ¡Favor Registrar las Secuencias!");
            } else {
                float conpara1 = Float.parseFloat(total_devueltas.getText());
                if (conpara1 > 0.0 && !cod_cli_fact.getText().isEmpty() && !nom_cli_fact.getText().isEmpty()) {
                    if (Clase_Variable_Publica.fact == 0) {

                        float conpara = Float.parseFloat(total_devueltas.getText());
                        if (conpara > 0.0 && !cod_cli_fact.getText().isEmpty() && !nom_cli_fact.getText().isEmpty()) {

                            int x = JOptionPane.showConfirmDialog(rootPane, "¿Desea agregar una nota a la factura?", "Nota Factura", JOptionPane.YES_NO_CANCEL_OPTION);

                            if (x == JOptionPane.NO_OPTION) {
                                Conexion_factura_guardar cfg = new Conexion_factura_guardar();
                                try {
                                    cfg.conectar();
                                } catch (SQLException ex) {
                                    Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if (x == JOptionPane.YES_OPTION) {
                                String texto = JOptionPane.showInputDialog(null, "Mensaje Factura");
                                if (!texto.equals(JOptionPane.CANCEL_OPTION) && !texto.equals(JOptionPane.CLOSED_OPTION)) {
                                    nota_fact.setText(texto);
                                    Conexion_factura_guardar cfg = new Conexion_factura_guardar();
                                    try {
                                        cfg.conectar();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            if (x == JOptionPane.CANCEL_OPTION) {

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos para proceder a la factura");
                        }
                    }
                    if (Clase_Variable_Publica.fact == 1) {
                        if (Clase_Variable_Publica.modificar_ft == 0) {
                            float conpara = Float.parseFloat(total_devueltas.getText());
                            if (conpara > 0.0 && !cod_cli_fact.getText().isEmpty() && !nom_cli_fact.getText().isEmpty()) {

                                int x = JOptionPane.showConfirmDialog(rootPane, "¿Desea modificar la nota anterior de la factura?", "Nota Factura", JOptionPane.YES_NO_CANCEL_OPTION);

                                if (x == JOptionPane.NO_OPTION) {
                                    Conexion_factura_Modificar cfm = new Conexion_factura_Modificar();
                                    try {
                                        cfm.conectar();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                                if (x == JOptionPane.YES_OPTION) {
                                    String texto = JOptionPane.showInputDialog(null, "Mensaje Factura");
                                    if (!texto.equals(JOptionPane.CANCEL_OPTION) && !texto.equals(JOptionPane.CLOSED_OPTION)) {
                                        nota_fact.setText(texto);
                                        Conexion_factura_Modificar cfm = new Conexion_factura_Modificar();
                                        try {
                                            cfm.conectar();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                                if (x == JOptionPane.CANCEL_OPTION) {
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos para proceder a la factura");
                            }
                        } else {
                            Conexion_factura_Modificar cfm = new Conexion_factura_Modificar();
                            try {
                                cfm.conectar();
                            } catch (SQLException ex) {
                                Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos para proceder a la factura");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////
    public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new render_tabla_prueba());
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////

    public void actualizarTO() {
        String cop1 = null;
        String cop2;
        DefaultTableModel dtm = (DefaultTableModel) tablaF_out.getModel();
        int fila = tablafacturacion1.getSelectedRow();
        if (fila >= 0) {
            cop1 = tablafacturacion1.getValueAt(fila, 0).toString();
        }

        for (int f = 0; f < tablaF_out.getRowCount(); f++) {
            {
                if (tablaF_out.getValueAt(f, 3).equals(cop1)) {
                    dtm.removeRow(f);

                }
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void datorepetido() {
        String cop1 = null;
        int obcion = 0;
        int fila = tablafacturacion1.getRowCount();
        DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel();

        if (fila > 0) {
            cop1 = cod_serv_fact.getText();
            for (int f = 0; f < tablafacturacion1.getRowCount(); f++) {
                if (tablafacturacion1.getValueAt(f, 0).equals(cop1)) {
                    obcion = Integer.parseInt(cop1);
                    JOptionPane.showMessageDialog(null, obcion);
                }
            }

            if (fila != obcion) {
                llenarServicio();
            } else {
                JOptionPane.showMessageDialog(null, "Este articulo ya está en la tabla, si desea agregar otra cantidad "
                        + "favor darle doble clic en la fila y elegir la opción deseada");
                cod_serv_fact.setText("");
                descrii_fact.setText("");
                cant_fact.setText("");
                cod_serv_fact.requestFocus();
            }

        } else {
            llenarServicio();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void llenarServicio() {
        String cap = "";
        String cont = "";
        int compar = 0;
        int cant = 0;
        if (!cod_cli_fact.getText().isEmpty()) {
            if (cant_fact.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe digitar la cantidad");
                return;
            } else {
                cap = cant_fact.getText();
                cant = Integer.parseInt(cap);

                llenar_tabla();
                sumar_total();
                limpiar();
                cod_serv_fact.requestFocus();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe introducir un cliente");
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    public int buscaProductoEnModelo() {
        int fila = tablafacturacion1.getRowCount();
        String codigo = cod_serv_fact.getText();
        String valor = "";
        if (!codigo.equals(null) && fila > 0) {

            for (int renglon = 0; renglon < fila; renglon++) {

                //JOptionPane.showMessageDialog(null, renglon);
                String codigoEnRenglon = tablafacturacion1.getValueAt(renglon, 0).toString();
                if (codigo.equals(codigoEnRenglon)) {
                    DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00");
                    float total, precio1, calculo, itbis1, resultado;
                    int cantidad1, cantidad2, suma_cantidad;
                    String codigo1 = cod_serv_fact.getText();
                    String cantidad = tablafacturacion1.getValueAt(renglon, 3).toString();
                    String precio = precio_serv.getText();
                    String cant = cant_fact.getText();

                    cantidad1 = Integer.parseInt(cantidad);
                    cantidad2 = Integer.parseInt(cant);
                    suma_cantidad = cantidad1 + cantidad2;

                    precio1 = Float.parseFloat(precio);

                    calculo = (float) (precio1 * 0.18) * suma_cantidad;
                    itbis1 = calculo + precio1;
                    resultado = (precio1 * suma_cantidad);

                    DefaultTableModel dtm = (DefaultTableModel) tablaF_out.getModel();
                    tablafacturacion1.setValueAt(formateador.format(precio1), renglon, 2);
                    tablafacturacion1.setValueAt(formateador.format(calculo), renglon, 4);
                    tablafacturacion1.setValueAt(suma_cantidad, renglon, 3);
                    tablafacturacion1.setValueAt(formateador.format(resultado), renglon, 5);
                    cod_serv_fact.setText("");
                    descrii_fact.setText("");
                    cant_fact.setText("");
                    precio_serv.setText("");
                    cod_serv_fact.requestFocus();

                    for (int f = 0; f < tablaF_out.getRowCount(); f++) {
                        {
                            if (tablaF_out.getValueAt(f, 3).equals(codigoEnRenglon)) {

                                tablaF_out.setValueAt(precio1, f, 0);
                                tablaF_out.setValueAt(calculo, f, 1);
                                tablaF_out.setValueAt(resultado, f, 2);
                                tablaF_out.setValueAt(codigo1, f, 3);

                                return renglon;
                            }
                        }
                    }

                    //JOptionPane.showMessageDialog(null, "el servicio ya esta en la tabla para cambiar" + precio + cantidad);
                    valor = codigoEnRenglon;
                    return renglon;
                }
            }
            if (!codigo.equals(valor)) {

                llenar_tabla();
                sumar_total();
                limpiar();
                cod_serv_fact.requestFocus();

            }
        }
        if (fila == 0) {
            llenar_tabla();
            sumar_total();
            limpiar();
            cod_serv_fact.requestFocus();
        }
        return -1;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    public void conecta() throws SQLException {
//           String direc = conexion_2.valor;
//    String contra = conexion_2.clave;
//    String usu = conexion_2.usu;
//    
//        String jdbc= direc;
        conexion = DriverManager.getConnection(conexion_2.cadena1, conexion_2.cadena2, conexion_2.cadena3);
        conexion.setAutoCommit(false);
    }

    void close() {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar facturacion?",
                "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }
    ////////////////////////////////////////////////////////////
    public void Cuadre(){
     cargar_montos();
     String est1 ="2",estado ="1",ub= Clase_Variable_Publica.ubicacion;;
     
      try{
            PreparedStatement psU2 = cn.prepareStatement("UPDATE encabezado_factura SET estado='"+est1+"' where estado='"+estado+"' and ubicacion ='"+ub+"'");
            psU2.executeUpdate();
            
            
            Imprimir_cuadre();
                  }catch (SQLException ex){
         Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE,null, ex);
     }
        
    }
    void cargar_montos(){
        String estado ="1", ubicacion= Clase_Variable_Publica.ubicacion;
        String[] registros = new String[5];
            final String codigo = "SELECT SUM(total) as total,COUNT(*) as contador FROM encabezado_factura where estado='"+estado+"' and ubicacion ='"+ubicacion+"'";
            try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(codigo);
            
            if(rs.next()){
                registros[0] = rs.getString("total");
                registros[1] = rs.getString("contador");
                Monto_cuadre =registros[0];
                cant_cuadre = registros[1];
            }
              }catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    private void PrintReportToPrinter(JasperPrint jp) throws JRException {
    // TODO Auto-generated method stub
      PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
    // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
    printRequestAttributeSet.add(new Copies(1));

    PrinterName printerName = new PrinterName("POS80", null); //gets printer 

    PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
    printServiceAttributeSet.add(printerName);

     JRPrintServiceExporter exporter = new JRPrintServiceExporter();

    exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING.JASPER_PRINT, jp);
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
    exporter.exportReport();
}
    ////////////////////////////////////////////////////////////////////////////////////////////
    void Imprimir_cuadre(){
       ArrayList lista1 = new ArrayList();
        //List <Factura>lista = new ArrayList<>();
        lista1.add("");
        String ub = Clase_Variable_Publica.ubicacion; 
        
        for (int i = 0; i<tablafacturacion1.getRowCount(); i++){
             
            
            //Factura_1 mortiza = new Factura_1 (tablafacturacion1.getValueAt(i,3).toString()+"",tablafacturacion1.getValueAt(i,2).toString()+"",tablafacturacion1.getValueAt(i,1).toString()+"",tablafacturacion1.getValueAt(i,5).toString()+"");
        //lista1.add(mortiza);
        
    }
        JasperReport jr=null;
        DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00"); 
        Float cobrar;
        cobrar = Float.parseFloat(Monto_cuadre);
        try{
            
            jr = (JasperReport) JRLoader.loadObjectFromFile("cuadre.jasper");
           
                HashMap parametro = new HashMap();
                //parametro.put("Nombre_ususario", nombre_usu_cot.getText());
                parametro.put("total", formateador.format(cobrar));
                parametro.put("cantidad", cant_cuadre);
                parametro.put("usuario", nombre_usu_fact.getText());
                parametro.put("ubicacion", ub);
                parametro.put("Logo", this.getClass().getResourceAsStream(Logo));

                        
        
        JasperPrint jp = JasperFillManager.fillReport(jr, parametro,new JRBeanCollectionDataSource(lista1));
       //JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
       PrintReportToPrinter(jp);
//        JasperViewer jv = new JasperViewer(jp,false);
//        jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        jv.setVisible(true);
        
        }catch(JRException ex){
            ex.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "ERROR\n" + ex.getMessage());
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    void sumarstock(String codi,String can)
    {
       int des = Integer.parseInt(can);
       String cap="";
       int desfinal;
       String[] registros = new String[3];
       String consul="SELECT existencia FROM articulo WHERE cod_art='"+codi+"'";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next())
            {
                registros[0] = rs.getString("existencia");
                
                
                cap = registros[0];
            }
            
            
        } catch (Exception e) {
        }
        desfinal=Integer.parseInt(cap)+des;
        String modi="UPDATE articulo SET existencia='"+desfinal+"' WHERE cod_art = '"+codi+"'";
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    } 
    //////////////////////////////////////////////////////////////////
    void descontarstock(String codi,String can)
    {
       int des = Integer.parseInt(can);
       String cap="";
       int desfinal;
       String[] registros = new String[3];
       String consul="SELECT existencia FROM articulo WHERE cod_art='"+codi+"'";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next())
            {
                registros[0] = rs.getString("existencia");
                
                
                cap = registros[0];
            }
            
            
        } catch (Exception e) {
        }
        desfinal=Integer.parseInt(cap)-des;
        String modi="UPDATE articulo SET existencia='"+desfinal+"' WHERE cod_art = '"+codi+"'";
        try {
            PreparedStatement pst = cn.prepareStatement(modi);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    //////////////////////////////////////////
     public void conectar_guardar() throws SQLException {
        try {
            conecta();
            //comparacion_fecha();
          transaccion();
        } finally {
            cerrar();
        }
    }
      private void transaccion() throws SQLException{
        String comp = "";
         String estado = "1";
         String ubicacion = Clase_Variable_Publica.ubicacion;
         String despacho = "";

            if(tipo_comp.getSelectedItem().equals("Crédito Fiscal")){
               comp = ncf_disp.getText();
            }
        PreparedStatement detalle_fact = null, encabezado_factura= null, contador_facturas= null, Comprobante= null;
        try {
            String cod;
            String es="";
        for (int i = 0; i<tablafacturacion1.getRowCount(); i++){
            cod = (tablafacturacion1.getValueAt(i,0).toString());  
            String[] registros = new String[5];
            final String codigo = "SELECT tipo FROM articulo where cod_art='"+cod+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(codigo);
            
            if(rs.next()){
                registros[0] = rs.getString("tipo");
                es=registros[0];
            }
            if(es.equals("Comidas")){
                despacho = "No";
            }else if(es.equals("Bebidas")){
             despacho = "Si";   
            }
                  String tipo_despacho;
            if(para_llevar.isSelected()){
                tipo_despacho="Para llevar";
            }else{
               tipo_despacho="Comer aqui"; 
            }
        final String detalle ="INSERT INTO detalle_fact(num_factura,cod_servicio,descr_serv,cantidad,precio,itbis,total,despacho,tipo_despacho)VALUES('"+num_fact_1.getText()+"','"+tablafacturacion1.getValueAt(i,0)+"','"+tablafacturacion1.getValueAt(i,1)+"','"+tablafacturacion1.getValueAt(i,3)+"','"+tablaF_out.getValueAt(i,0)+"','"+tablaF_out.getValueAt(i,1)+"','"+tablaF_out.getValueAt(i,2)+"','"+despacho+"','"+tipo_despacho+"')";
        
            detalle_fact =conexion.prepareStatement(detalle);
            detalle_fact.executeUpdate();
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////
        String tiempo = null;
        String abono, total_pago,efectivo="0.00";
                String delivery,abierta;
        if(enviar_delivery.isSelected()){
            delivery="Si";
        }else{
           delivery="No"; 
        }
        
        if(cuenta_abierta.isSelected()){
            abierta="Si";
        }else{
           abierta="No"; 
        }
        final String Encabezado ="INSERT INTO encabezado_factura(num_factura,ncf,tipo_comprobante,Cod_cliente,nom_cliente,rnc_cl,sub_total,descuento,itbis,total,abono,devuelta,fecha,estado,ubicacion,delivery,abierta)"
                + "VALUES('"+num_fact_1.getText()+"','"+comp+"','"+Facturacion.tipo_comp.getSelectedItem()+"','"+cod_cli_fact.getText()+"','"+nom_cli_fact.getText()+"','"+rnc_fact.getText()+"',"
                + "'"+sub_total_ft.getText()+"','"+Facturacion.desc_serv.getText()+"','"+itbis_ft.getText()+"','"+total_devueltas.getText()+"','"+efectivo+"','"+efectivo+"','"+Facturacion.fecha_fact2.getText()+"','"+estado+"','"+ubicacion+"','"+delivery+"','"+abierta+"')";
            encabezado_factura =conexion.prepareStatement(Encabezado);
            encabezado_factura.executeUpdate();
        ///////////////////////////////////////////////////////////////////////////////////////////////////    
        if(ubicacion.equals("Bar")){
         final String Contador ="INSERT INTO contador_factura_bar(secuencia)VALUES('"+num_fact_1.getText()+"')";
         contador_facturas =conexion.prepareStatement(Contador);
            contador_facturas.executeUpdate();  
        }else{
           final String Contador ="INSERT INTO contador_facturas(secuencia)VALUES('"+num_fact_1.getText()+"')";
         contador_facturas =conexion.prepareStatement(Contador);
            contador_facturas.executeUpdate();       
                }
        //////////////////////////////////////////////////////////////////////////////////////////////////
        String ncf = "";
        if(tipo_comp.getSelectedItem().equals("Crédito Fiscal")){
        String dp="No";
        PreparedStatement psU2 = cn.prepareStatement("UPDATE secuencia_ncf SET Disponible='"+dp+"'where ncf='"+comp+"'");
            psU2.executeUpdate();   
        }
            
            conexion.commit();
            
            
for(int i=0;i<tablafacturacion1.getRowCount();i++)
{
String capcod="",capcan="";
capcod=tablafacturacion1.getValueAt(i, 0).toString();
capcan=tablafacturacion1.getValueAt(i, 3).toString();          
descontarstock(capcod, capcan);

}            
   limpiar1();
   desbloquear();
   peticion pt = new peticion();
   pt.run();
            //JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
        } catch (SQLException e) {
            conexion.rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar los datos");
        }finally{
            if(detalle_fact !=null){
                detalle_fact.close(); 
            }
            if(encabezado_factura !=null){
                encabezado_factura.close(); 
            }
            if(contador_facturas !=null){
                contador_facturas.close(); 
            }
        }
    
    }
      ///////////////////////////////////////////////////////////
      public void cerrar() throws SQLException{
        
    }
  /////////////////////////////////////////////////////////////
        public void conectar_modificar() throws SQLException {
        try {
            conecta();
            transaccion_m();
        } finally {
            cerrar();
        }
    }
    //////////////////////////////////////////////////////////////
      private void transaccion_m() throws SQLException{
         String cod;
         String es="";
         String despacho = "";
for(int i=0;i<tablafacturacion1.getRowCount();i++)
{
String capcod="",capcan="";
capcod=tablafacturacion1.getValueAt(i, 0).toString();
capcan=tablafacturacion1.getValueAt(i, 3).toString();          
descontarstock(capcod, capcan);


}      

        PreparedStatement detalle_fact = null, encabezado_factura= null, contador_facturas= null, Borrar= null;
        try {
        for (int i = 0; i<tablafacturacion1.getRowCount(); i++){
        final String borrar ="Delete From detalle_fact where num_factura='"+Facturacion.num_fact_1.getText()+"'";   
        Borrar =conexion.prepareStatement(borrar);
        Borrar.executeUpdate();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////
              for (int i = 0; i<tablafacturacion1.getRowCount(); i++){
             cod = (tablafacturacion1.getValueAt(i,0).toString());  
            String[] registros = new String[5];
            final String codigo = "SELECT tipo FROM articulo where cod_art='"+cod+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(codigo);
            
            if(rs.next()){
                registros[0] = rs.getString("tipo");
                es=registros[0];
            }
             if(es.equals("Comidas")){
                despacho = "No";
            }else if(es.equals("Bebidas")){
             despacho = "Si";   
            }
            String tipo_despacho;
            if(para_llevar.isSelected()){
                tipo_despacho="Para llevar";
            }else{
               tipo_despacho="Comer aqui"; 
            }     
            
        final String detalle ="INSERT INTO detalle_fact(num_factura,cod_servicio,descr_serv,cantidad,precio,itbis,total,despacho,tipo_despacho)VALUES('"+num_fact_1.getText()+"','"+tablafacturacion1.getValueAt(i,0)+"','"+tablafacturacion1.getValueAt(i,1)+"','"+tablafacturacion1.getValueAt(i,3)+"','"+tablaF_out.getValueAt(i,0)+"','"+tablaF_out.getValueAt(i,1)+"','"+tablaF_out.getValueAt(i,2)+"','"+despacho+"','"+tipo_despacho+"')";
        
            detalle_fact =conexion.prepareStatement(detalle);
            detalle_fact.executeUpdate();
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////
        String tiempo = null;
        String comp = "";
        String comp1 = "";
        String estado = "No",dinero="0.00";
              String delivery,abierta;
        if(enviar_delivery.isSelected()){
            delivery="Si";
        }else{
           delivery="No"; 
        }
        
        if(cuenta_abierta.isSelected()){
            abierta="Si";
        }else{
           abierta="No"; 
        }
         if(tipo_comp.getSelectedItem().equals("Crédito Fiscal")){
               comp = Facturacion.ncf_disp.getText();
         }

        final String Encabezado ="UPDATE encabezado_factura SET ncf='"+comp+"',tipo_comprobante='"+Facturacion.tipo_comp.getSelectedItem()+"',Cod_cliente='"+cod_cli_fact.getText()+"',nom_cliente='"+nom_cli_fact.getText()+"',rnc_cl='"+rnc_fact.getText()+"',sub_total='"+sub_total_ft.getText()+"',descuento='"+Facturacion.desc_serv.getText()+"',itbis='"+itbis_ft.getText()+"',total='"+total_devueltas.getText()+"',abono='"+dinero+"',devuelta='"+dinero+"',fecha='"+Facturacion.fecha_fact2.getText()+"',delivery='"+delivery+"',abierta='"+abierta+"' where num_factura='"+Facturacion.num_fact_1.getText()+"'";
            encabezado_factura =conexion.prepareStatement(Encabezado);
            encabezado_factura.executeUpdate();
            
        ///////////////////////////////////////////////////////////////////////////////////////////////////    
        String ncf = "";
        if(tipo_comp.getSelectedItem().equals("Crédito Fiscal")){
        String dp="No";
        PreparedStatement psU2 = cn.prepareStatement("UPDATE secuencia_ncf SET Disponible='"+dp+"'where ncf='"+comp+"'");
            psU2.executeUpdate();   
        }
       //////////////////////////////////////////////////////////////////////////////////////////////////////////////
       String ncf2 = "";
       String ncf3 = "";
       ncf2 = Facturacion.ncf_disp.getText();
       ncf3 = Facturacion.ncf_anterior.getText();

       if(!ncf2.equals(ncf3)){
       JOptionPane.showMessageDialog(null, "es diferente");
       comp1 = Facturacion.ncf_anterior.getText();
        if(Facturacion.tiponcf_anterior.getText().equals("Crédito Fiscal")){
        String dp1="Si";
        PreparedStatement psU3 = cn.prepareStatement("UPDATE secuencia_ncf SET Disponible='"+dp1+"'where ncf='"+comp1+"'");
            psU3.executeUpdate();   
        }
       }
            conexion.commit();
            
 limpiar1();
 desbloquear();
  peticion pt = new peticion();
   pt.run();
            //JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
        } catch (SQLException e) {
            conexion.rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar los datos");
        }finally{
            if(Borrar !=null){
                Borrar.close(); 
            }
            if(detalle_fact !=null){
                detalle_fact.close(); 
            }
            if(encabezado_factura !=null){
                encabezado_factura.close(); 
            }
            
        }
    
    }   
      
   public void solicitud(){
      String HOST = "10.0.0.11";
             int PUERTO = 5000;
             DataInputStream in;
             DataOutputStream out; 
             
             try {
            Socket sc = new Socket(HOST,PUERTO);
            
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            
            //out.writeUTF("Ejecuta el aasunto");
            String mensaje = in.readUTF();
            
            //JOptionPane.showMessageDialog(null, mensaje);
            
            sc.close();
            
        } catch (Exception e) {
        } 
   }   
      
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ncf_fgb = new javax.swing.JLabel();
        ncf_f = new javax.swing.JLabel();
        ncf_fep = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        nota_fact = new javax.swing.JTextField();
        numero_id_ft = new javax.swing.JTextField();
        tipo_fact1 = new javax.swing.JLabel();
        total_devueltas = new javax.swing.JLabel();
        sub_total_ft = new javax.swing.JLabel();
        itbis_ft = new javax.swing.JLabel();
        fecha_fact3 = new javax.swing.JLabel();
        descuento_rp = new javax.swing.JLabel();
        ncf_reult = new javax.swing.JLabel();
        ncf_anterior = new javax.swing.JLabel();
        tiponcf_anterior = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        buscar_rnc = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fecha_fact2 = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaF_out = new javax.swing.JTable();
        btn_minimizar = new javax.swing.JButton();
        volverAtras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablafacturacion1 = new javax.swing.JTable();
        nombre_usu_fact = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Btnarticulo = new javax.swing.JButton();
        Btndespacho = new javax.swing.JButton();
        Btninventario = new javax.swing.JButton();
        Btndelivery = new javax.swing.JButton();
        Btncomprobantes = new javax.swing.JButton();
        Btnusuario = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        num_fact_1 = new javax.swing.JLabel();
        ncf_disp = new javax.swing.JLabel();
        cod_cli_fact = new javax.swing.JTextField();
        nom_cli_fact = new javax.swing.JTextField();
        cod_serv_fact = new javax.swing.JTextField();
        descrii_fact = new javax.swing.JTextField();
        buscar_serv = new javax.swing.JButton();
        cant_fact = new javax.swing.JTextField();
        rnc_fact = new javax.swing.JTextField();
        fecha_fact1 = new javax.swing.JLabel();
        tipo_comp = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        desc_serv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        precio_serv = new javax.swing.JTextField();
        sub_total = new javax.swing.JLabel();
        descuento_front = new javax.swing.JLabel();
        itbis_fact = new javax.swing.JLabel();
        total_fact = new javax.swing.JLabel();
        procesar_fact = new javax.swing.JButton();
        modificar_fact = new javax.swing.JButton();
        limpiar_fact = new javax.swing.JButton();
        reimprimir_fact = new javax.swing.JButton();
        cuadre_caja = new javax.swing.JButton();
        enviar_delivery = new javax.swing.JCheckBox();
        cuenta_abierta = new javax.swing.JCheckBox();
        para_comerAqui = new javax.swing.JRadioButton();
        para_llevar = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        ncf_fgb.setForeground(new java.awt.Color(255, 255, 255));

        ncf_f.setForeground(new java.awt.Color(255, 255, 255));

        ncf_fep.setForeground(new java.awt.Color(255, 255, 255));

        numero_id_ft.setEditable(false);
        numero_id_ft.setBackground(new java.awt.Color(76, 76, 76));
        numero_id_ft.setForeground(new java.awt.Color(255, 255, 255));
        numero_id_ft.setText("2");
        numero_id_ft.setBorder(null);
        numero_id_ft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_id_ftActionPerformed(evt);
            }
        });

        tipo_fact1.setText("jLabel6");

        fecha_fact3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        fecha_fact3.setForeground(new java.awt.Color(255, 255, 255));

        ncf_reult.setForeground(new java.awt.Color(255, 255, 255));

        ncf_anterior.setForeground(new java.awt.Color(255, 255, 255));

        tiponcf_anterior.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(77, 77, 77));

        buscar_rnc.setBorder(null);
        buscar_rnc.setContentAreaFilled(false);
        buscar_rnc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscar_rnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_rncActionPerformed(evt);
            }
        });

        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        fecha_fact2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        tablaF_out.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "precio", "itbis", "toal", "codigo"
            }
        ));
        jScrollPane2.setViewportView(tablaF_out);

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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N

        tablafacturacion1.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        tablafacturacion1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "   Codigo", "Descripcion   ", "                             Precio", " Cantidad", "                               Itbis", "                              Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablafacturacion1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablafacturacion1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tablafacturacion1.setDragEnabled(true);
        tablafacturacion1.setSelectionBackground(new java.awt.Color(198, 54, 53));
        tablafacturacion1.setShowHorizontalLines(false);
        tablafacturacion1.setShowVerticalLines(false);
        tablafacturacion1.getTableHeader().setResizingAllowed(false);
        tablafacturacion1.getTableHeader().setReorderingAllowed(false);
        tablafacturacion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablafacturacion1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablafacturacion1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tablafacturacion1);
        if (tablafacturacion1.getColumnModel().getColumnCount() > 0) {
            tablafacturacion1.getColumnModel().getColumn(0).setPreferredWidth(70);
            tablafacturacion1.getColumnModel().getColumn(1).setPreferredWidth(480);
            tablafacturacion1.getColumnModel().getColumn(2).setPreferredWidth(140);
            tablafacturacion1.getColumnModel().getColumn(3).setPreferredWidth(70);
            tablafacturacion1.getColumnModel().getColumn(4).setPreferredWidth(140);
            tablafacturacion1.getColumnModel().getColumn(5).setPreferredWidth(140);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 305, 949, 264));
        getContentPane().add(nombre_usu_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 255, 160, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 255, 170, 20));

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

        jPanel3.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 118, 30, 40));

        num_fact_1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        getContentPane().add(num_fact_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 67, 90, 20));
        getContentPane().add(ncf_disp, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 67, 120, 20));

        cod_cli_fact.setBackground(new java.awt.Color(228, 228, 228));
        cod_cli_fact.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        cod_cli_fact.setForeground(new java.awt.Color(198, 54, 53));
        cod_cli_fact.setBorder(null);
        cod_cli_fact.setDisabledTextColor(new java.awt.Color(58, 58, 58));
        cod_cli_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cod_cli_factActionPerformed(evt);
            }
        });
        cod_cli_fact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cod_cli_factKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cod_cli_factKeyTyped(evt);
            }
        });
        getContentPane().add(cod_cli_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 129, 90, 30));

        nom_cli_fact.setBackground(new java.awt.Color(228, 228, 228));
        nom_cli_fact.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        nom_cli_fact.setForeground(new java.awt.Color(198, 54, 53));
        nom_cli_fact.setBorder(null);
        nom_cli_fact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nom_cli_factKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nom_cli_factKeyTyped(evt);
            }
        });
        getContentPane().add(nom_cli_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 129, 235, 30));

        cod_serv_fact.setBackground(new java.awt.Color(228, 228, 228));
        cod_serv_fact.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        cod_serv_fact.setForeground(new java.awt.Color(198, 54, 53));
        cod_serv_fact.setBorder(null);
        cod_serv_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cod_serv_factActionPerformed(evt);
            }
        });
        cod_serv_fact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cod_serv_factKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cod_serv_factKeyTyped(evt);
            }
        });
        getContentPane().add(cod_serv_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 160, 87, 30));

        descrii_fact.setBackground(new java.awt.Color(228, 228, 228));
        descrii_fact.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        descrii_fact.setForeground(new java.awt.Color(198, 54, 53));
        descrii_fact.setBorder(null);
        descrii_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descrii_factActionPerformed(evt);
            }
        });
        descrii_fact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descrii_factKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descrii_factKeyTyped(evt);
            }
        });
        getContentPane().add(descrii_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 160, 260, 30));

        buscar_serv.setBorder(null);
        buscar_serv.setContentAreaFilled(false);
        buscar_serv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscar_serv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_servActionPerformed(evt);
            }
        });
        getContentPane().add(buscar_serv, new org.netbeans.lib.awtextra.AbsoluteConstraints(929, 163, 20, 20));

        cant_fact.setBackground(new java.awt.Color(228, 228, 228));
        cant_fact.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        cant_fact.setForeground(new java.awt.Color(198, 54, 53));
        cant_fact.setBorder(null);
        cant_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cant_factActionPerformed(evt);
            }
        });
        cant_fact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cant_factKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cant_factKeyTyped(evt);
            }
        });
        getContentPane().add(cant_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(1076, 160, 120, 30));

        rnc_fact.setBackground(new java.awt.Color(228, 228, 228));
        rnc_fact.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        rnc_fact.setForeground(new java.awt.Color(198, 54, 53));
        rnc_fact.setBorder(null);
        rnc_fact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rnc_factKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rnc_factKeyTyped(evt);
            }
        });
        getContentPane().add(rnc_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 225, 150, 30));

        fecha_fact1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        fecha_fact1.setForeground(new java.awt.Color(198, 54, 53));
        getContentPane().add(fecha_fact1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 255, 130, 30));

        tipo_comp.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        tipo_comp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Consumidor Final", "Crédito Fiscal" }));
        tipo_comp.setBorder(null);
        tipo_comp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tipo_comp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipo_compItemStateChanged(evt);
            }
        });
        tipo_comp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipo_compMouseClicked(evt);
            }
        });
        tipo_comp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_compActionPerformed(evt);
            }
        });
        getContentPane().add(tipo_comp, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 223, 230, 30));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(198, 54, 53));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("RD$");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 221, -1, 30));

        desc_serv.setBackground(new java.awt.Color(228, 228, 228));
        desc_serv.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        desc_serv.setForeground(new java.awt.Color(198, 54, 53));
        desc_serv.setToolTipText("El descuento se aplica al monto total de la factura");
        desc_serv.setBorder(null);
        desc_serv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desc_servMouseClicked(evt);
            }
        });
        desc_serv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desc_servActionPerformed(evt);
            }
        });
        desc_serv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                desc_servKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                desc_servKeyTyped(evt);
            }
        });
        getContentPane().add(desc_serv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 224, 110, 30));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(198, 54, 53));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("RD$");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(943, 252, -1, 30));

        precio_serv.setBackground(new java.awt.Color(228, 228, 228));
        precio_serv.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        precio_serv.setForeground(new java.awt.Color(198, 54, 53));
        precio_serv.setBorder(null);
        precio_serv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                precio_servMouseClicked(evt);
            }
        });
        precio_serv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precio_servActionPerformed(evt);
            }
        });
        precio_serv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                precio_servKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precio_servKeyTyped(evt);
            }
        });
        getContentPane().add(precio_serv, new org.netbeans.lib.awtextra.AbsoluteConstraints(982, 255, 147, 30));

        sub_total.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        sub_total.setForeground(new java.awt.Color(198, 54, 53));
        sub_total.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sub_total.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(sub_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 582, 130, 20));

        descuento_front.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        descuento_front.setForeground(new java.awt.Color(198, 54, 53));
        descuento_front.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descuento_front.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(descuento_front, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 602, 150, 20));

        itbis_fact.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        itbis_fact.setForeground(new java.awt.Color(198, 54, 53));
        itbis_fact.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        itbis_fact.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(itbis_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 582, 120, 20));

        total_fact.setFont(new java.awt.Font("Dialog", 1, 27)); // NOI18N
        total_fact.setForeground(new java.awt.Color(198, 54, 53));
        total_fact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_fact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(total_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 630, 290, 30));

        procesar_fact.setBorder(null);
        procesar_fact.setContentAreaFilled(false);
        procesar_fact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        procesar_fact.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                procesar_factMouseMoved(evt);
            }
        });
        procesar_fact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                procesar_factMouseExited(evt);
            }
        });
        procesar_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesar_factActionPerformed(evt);
            }
        });
        getContentPane().add(procesar_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 595, 130, 30));

        modificar_fact.setBorder(null);
        modificar_fact.setContentAreaFilled(false);
        modificar_fact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modificar_fact.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                modificar_factMouseMoved(evt);
            }
        });
        modificar_fact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                modificar_factMouseExited(evt);
            }
        });
        modificar_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificar_factActionPerformed(evt);
            }
        });
        getContentPane().add(modificar_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 630, 130, 30));

        limpiar_fact.setBorder(null);
        limpiar_fact.setContentAreaFilled(false);
        limpiar_fact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        limpiar_fact.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                limpiar_factMouseMoved(evt);
            }
        });
        limpiar_fact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                limpiar_factMouseExited(evt);
            }
        });
        limpiar_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar_factActionPerformed(evt);
            }
        });
        getContentPane().add(limpiar_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 595, 130, 30));

        reimprimir_fact.setBorder(null);
        reimprimir_fact.setContentAreaFilled(false);
        reimprimir_fact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reimprimir_fact.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                reimprimir_factMouseMoved(evt);
            }
        });
        reimprimir_fact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reimprimir_factMouseExited(evt);
            }
        });
        reimprimir_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reimprimir_factActionPerformed(evt);
            }
        });
        getContentPane().add(reimprimir_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 630, 140, 30));

        cuadre_caja.setContentAreaFilled(false);
        cuadre_caja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cuadre_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadre_cajaActionPerformed(evt);
            }
        });
        getContentPane().add(cuadre_caja, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 590, 180, 40));

        enviar_delivery.setContentAreaFilled(false);
        enviar_delivery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enviar_delivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviar_deliveryActionPerformed(evt);
            }
        });
        getContentPane().add(enviar_delivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(1147, 132, 100, -1));

        cuenta_abierta.setContentAreaFilled(false);
        cuenta_abierta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cuenta_abierta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuenta_abiertaMouseClicked(evt);
            }
        });
        cuenta_abierta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuenta_abiertaActionPerformed(evt);
            }
        });
        getContentPane().add(cuenta_abierta, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 132, 150, -1));

        buttonGroup1.add(para_comerAqui);
        para_comerAqui.setContentAreaFilled(false);
        para_comerAqui.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(para_comerAqui, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 257, 120, -1));

        buttonGroup1.add(para_llevar);
        para_llevar.setContentAreaFilled(false);
        para_llevar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(para_llevar, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 257, 80, -1));

        jPanel7.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 260, 20, 30));

        jPanel6.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, 20, 30));

        jPanel4.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1148, 130, 20, 30));

        jPanel5.setBackground(new java.awt.Color(228, 228, 228));
        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, 20, 30));

        jLabel1.setBackground(java.awt.Color.gray);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Factura.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1365, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents
public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY-MM-dd");
        return formatofecha.format(fecha);
    }

    public static String fechaactual1() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-YYYY");
        return formatofecha.format(fecha);

    }
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
        close(); 
        Clase_Variable_Publica.modulo = 0;// TODO add your handling code here:
    }//GEN-LAST:event_volverAtrasActionPerformed

    private void tablafacturacion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablafacturacion1MouseClicked
        if(tablafacturacion1.isEnabled()){
            if(evt.getClickCount() == 2){
                if(evt.getButton()==MouseEvent.BUTTON1){

                    String[] options = {"Borrar", "Modificar", "Cancelar"};
                    //Integer[] options = {1, 3, 5, 7, 9, 11};
                    //Double[] options = {3.141, 1.618};
                    //Character[] options = {'a', 'b', 'c', 'd'};
                    int x = JOptionPane.showOptionDialog(null, "Elija la opción correspondiente",
                        "Servicio",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    if(x==0){
                        actualizarTO();
                        DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel(); //TableProducto es el nombre de mi tabla ;)
                    dtm.removeRow(tablafacturacion1.getSelectedRow());
                    sumar_total();
                }
                if(x==1){
                    String cop1 = null;
                    int fila = tablafacturacion1.getSelectedRow();
                    if (fila>=0){
                        cod_serv_fact.setText(tablafacturacion1.getValueAt(fila,0).toString());
                        cop1=tablafacturacion1.getValueAt(fila,0).toString();
                        descrii_fact.setText(tablafacturacion1.getValueAt(fila,1).toString());
                        //cant_contrata.setText(tablacont.getValueAt(fila,4).toString());

                        for (int f = 0; f < tablaF_out.getRowCount(); f++)
                        {
                            {
                                if (tablaF_out.getValueAt(f, 3).equals(cop1))
                                {
                                    precio_serv.setText(tablaF_out.getValueAt(f,0).toString());

                                }
                            }
                        }

                    }
                    actualizarTO();
                    DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel(); //TableProducto es el nombre de mi tabla ;)
                dtm.removeRow(tablafacturacion1.getSelectedRow());
                sumar_total();
                cant_fact.requestFocus();
            }
            if(x==2){

            }

        }
        }
        }
    }//GEN-LAST:event_tablafacturacion1MouseClicked

    private void tablafacturacion1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablafacturacion1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tablafacturacion1MouseEntered

    private void BtnarticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnarticuloActionPerformed
        if (Clase_Variable_Publica.permiso_art == 0) {
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
            //        fm.conectar();
            //    } catch (SQLException ex) {
            //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
            //    }
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar facturacion?",
                    "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Articulo art = new Articulo();
                art.setVisible(true);
                Articulo.nombre_usu_art.setText(nombre_usu_fact.getText());
                this.dispose();
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para articulo");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnarticuloActionPerformed

    private void BtndespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtndespachoActionPerformed
        if (Clase_Variable_Publica.permiso_desp == 0) {
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
            //        fm.conectar();
            //    } catch (SQLException ex) {
            //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
            //    }
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar facturacion?",
                    "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Despacho des = new Despacho();
                des.setVisible(true);
                //Facturacion.nombre_usu_fac.setText(nombre_usu_cli.getText());
                this.dispose();

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para despacho");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtndespachoActionPerformed

    private void BtninventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtninventarioActionPerformed
        if (Clase_Variable_Publica.permiso_inv == 0) {
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
            //        fm.conectar();
            //    } catch (SQLException ex) {
            //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
            //    }
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar facturacion?",
                    "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Inventario inv = new Inventario();
                inv.setVisible(true);
                Inventario.nombre_usu_inv.setText(nombre_usu_fact.getText());
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para inventario");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtninventarioActionPerformed

    private void BtndeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtndeliveryActionPerformed
        if (Clase_Variable_Publica.permiso_delv == 0) {
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
            //        fm.conectar();
            //    } catch (SQLException ex) {
            //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
            //    }
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar facturacion?",
                    "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Delivery del = new Delivery();
                del.setVisible(true);
                Delivery.nombre_usu_delv.setText(nombre_usu_fact.getText());
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para delivery");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtndeliveryActionPerformed

    private void BtncomprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtncomprobantesActionPerformed
        if (Clase_Variable_Publica.permiso_compro == 0) {
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
            //        fm.conectar();
            //    } catch (SQLException ex) {
            //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
            //    }
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar facturacion?",
                    "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Comprobantes fact = new Comprobantes();
                fact.setVisible(true);
                Comprobantes.nombre_usu_comp.setText(nombre_usu_fact.getText());
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para comprobantes");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtncomprobantesActionPerformed

    private void BtnusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnusuarioActionPerformed
        if (Clase_Variable_Publica.permiso_usu == 0) {
            //    funciones_menuprincipal fm = new funciones_menuprincipal();
            //    try {
            //        fm.conectar();
            //    } catch (SQLException ex) {
            //        Logger.getLogger(Menu_Principal.class.getName()).log(Level.SEVERE, null, ex);
            //    }
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar facturacion?",
                    "Cerrar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Usuarios usu = new Usuarios();
                usu.setVisible(true);
                Usuarios.nombre_usu_usu.setText(nombre_usu_fact.getText());
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para usuarios");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnusuarioActionPerformed

    private void cod_cli_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cod_cli_factActionPerformed
//        if(cod_cli_fact.getText().isEmpty()){
//            JOptionPane.showMessageDialog(rootPane, "Debe ingresar el código del cliente");
//        }else{
//            funciones_factura fcff = new funciones_factura();
//            try {
//                fcff.conectar_cargar_cliente();
//                /*String cont ="";
//                String comp = "";
//                String es="";
//                int compar = 0;
//                int cant = 0;
//                comp = cod_cli_fact.getText();
//                String[] registros = new String[2];
//                String sql = "SELECT estado_cl FROM cliente where codigo_cl='"+cod_cli_fact.getText()+"'";
//                try {
//                    java.sql.Statement st = cn.createStatement();
//                    ResultSet rs = st.executeQuery(sql);
//                    if(rs.next()){
//                        registros[0] = rs.getString("estado_cl");
//                        es=registros[0];
//                    }
//                    if(es.equals("Inactivo")){
//                        JOptionPane.showMessageDialog(rootPane,"Este Cliente esta Inactivo");
//
//                    }else{
//                        cargar_cliente();
//                    }
//                }catch(SQLException ex){
//                    JOptionPane.showMessageDialog(null, ex);
//
//                }*/
//            } catch (SQLException ex) {
//                Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }// TODO add your handling code here:
    }//GEN-LAST:event_cod_cli_factActionPerformed

    private void cod_cli_factKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cod_cli_factKeyPressed
        if (!cod_cli_fact.getText().isEmpty()) {
            procesar_fact.setEnabled(true);
            limpiar_fact.setEnabled(true);
            reimprimir_fact.setEnabled(false);
            //cotizacion_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
            //contrata_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
            //reimprimir_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        }// TODO add your handling code here:
    }//GEN-LAST:event_cod_cli_factKeyPressed

    private void cod_cli_factKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cod_cli_factKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
        if (cod_cli_fact.getText().length() >= 6) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cod_cli_factKeyTyped

    private void nom_cli_factKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom_cli_factKeyPressed
        if (!nom_cli_fact.getText().isEmpty()) {
            procesar_fact.setEnabled(true);
            limpiar_fact.setEnabled(true);
            reimprimir_fact.setEnabled(false);
                    }        // TODO add your handling code here:
    }//GEN-LAST:event_nom_cli_factKeyPressed

    private void nom_cli_factKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom_cli_factKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != '.') && (c != '@')
                && (c != '#') && (c != 'ñ') && (c != 'Ñ') && (c != '!') && (c != '$') && (c != '%') && (c != '&') && (c != '?') && (c != ',') && (c != ':') && (c != ';') && c != KeyEvent.VK_SPACE) {
            evt.consume();      
        }// TODO add your handling code here:
    }//GEN-LAST:event_nom_cli_factKeyTyped

    private void cod_serv_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cod_serv_factActionPerformed
        if (!cod_cli_fact.getText().isEmpty()) {
            funciones_factura fcf = new funciones_factura();
            try {
                fcf.conectar_cargar_serv();
                /*String cont ="";
                String comp = "";
                String es="";
                int compar = 0;
                int cant = 0;
                comp = cod_serv_fact1.getText();
                String[] registros = new String[2];
                String sql = "SELECT estado_serv FROM servicios where codigo_serv='"+cod_serv_fact1.getText()+"'";
                try {
                    java.sql.Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    if(rs.next()){
                        registros[0] = rs.getString("estado_serv");
                        es=registros[0];
                    }
                    if(es.equals("Inactivo")){
                        JOptionPane.showMessageDialog(rootPane,"Este Servicio esta Inactivo");

                    }else{
                        proceso_articulo();
                        desc_serv.setText("0.0");
                    }
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);

                }*/
            } catch (SQLException ex) {
                Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe primero seleccionar un cliente");
            cod_cli_fact.requestFocus();
            cod_serv_fact.setText("");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cod_serv_factActionPerformed

    private void cod_serv_factKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cod_serv_factKeyPressed
        if (!cod_serv_fact.getText().isEmpty()) {
            procesar_fact.setEnabled(true);
            limpiar_fact.setEnabled(true);
            //cotizacion_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
            //contrata_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
            //reimprimir_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cod_serv_factKeyPressed

    private void cod_serv_factKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cod_serv_factKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
        if (cod_serv_fact.getText().length() >= 7) {
            evt.consume();
        }    // TODO add your handling code here:
    }//GEN-LAST:event_cod_serv_factKeyTyped

    private void descrii_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descrii_factActionPerformed
        Consulta_Producto csv = new Consulta_Producto();
        csv.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_descrii_factActionPerformed

    private void descrii_factKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descrii_factKeyPressed
        if (!descrii_fact.getText().isEmpty()) {
            procesar_fact.setEnabled(true);
            limpiar_fact.setEnabled(true);

       }        // TODO add your handling code here:
    }//GEN-LAST:event_descrii_factKeyPressed

    private void descrii_factKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descrii_factKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c != '.') && (c != '@')
                && (c != '#') && (c != 'ñ')&& (c != 'Ñ') && (c != '!') && (c != '$') && (c != '%') && (c != '&') && (c != '?') && (c != ',') && (c != ':') && (c != ';') && c != KeyEvent.VK_SPACE) {
            evt.consume();  
        }// TODO add your handling code here:
    }//GEN-LAST:event_descrii_factKeyTyped

    private void buscar_servActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_servActionPerformed
        if (cod_serv_fact.isEditable()) {
            if (!cod_cli_fact.getText().isEmpty()) {
                Clase_Variable_Publica.art = 1;
                Consulta_Producto csv = new Consulta_Producto();
                csv.setVisible(true);
                Consulta_Producto.cod_client_out.setText(cod_cli_fact.getText());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Primero debe seleccionar un cliente");
            }
        } else {

        }        // TODO add your handling code here:
    }//GEN-LAST:event_buscar_servActionPerformed

    private void cant_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cant_factActionPerformed
        if (!cod_cli_fact.getText().isEmpty()) {
            if (cant_fact.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe digitar la cantidad");
                cant_fact.requestFocus();
                return;
            } else {
                int cant = 0;
                String[] registros = new String[5];
    String sql = "SELECT existencia FROM articulo where cod_art = '"+cod_serv_fact.getText()+"'";

try{
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("existencia");
        cant = Integer.parseInt(registros[0]);
          }   
    if(cant <=0){
       JOptionPane.showMessageDialog(null, "Este articulo no tiene existencia");
    }else{
      
                buscaProductoEnModelo();
                sumar_total();
                precio_serv.setEditable(false);     
    }

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }           
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe introducir un cliente");
            cod_cli_fact.requestFocus();
        }
    }//GEN-LAST:event_cant_factActionPerformed

    private void cant_factKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cant_factKeyPressed
        if (!cant_fact.getText().isEmpty()) {
            procesar_fact.setEnabled(true);
            limpiar_fact.setEnabled(true);
            //cotizacion_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
            //contrata_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
            //reimprimir_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cant_factKeyPressed

    private void cant_factKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cant_factKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
        if (cant_fact.getText().length() >= 7) {
            evt.consume();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_cant_factKeyTyped

    private void rnc_factKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rnc_factKeyPressed
        if (!rnc_fact.getText().isEmpty()) {
            procesar_fact.setEnabled(true);
            limpiar_fact.setEnabled(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_rnc_factKeyPressed

    private void rnc_factKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rnc_factKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
        if (rnc_fact.getText().length() >= 11) {
            evt.consume();
        }           // TODO add your handling code here:
    }//GEN-LAST:event_rnc_factKeyTyped

    private void tipo_compItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipo_compItemStateChanged
        String comparacion = (String) tipo_comp.getSelectedItem();
        if (comparacion.equals("Crédito Fiscal")) {
            ncf_disp.setText(ncf_f.getText());
        }
        if (comparacion.equals("Consumidor Final")) {
            ncf_disp.setText("");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_compItemStateChanged

    private void tipo_compMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipo_compMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_compMouseClicked

    private void tipo_compActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_compActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_compActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        if (Clase_Variable_Publica.descuento_ft == 1) {
            JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para realizar descuento");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void desc_servMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desc_servMouseClicked
        if (!desc_serv.isEditable()) {
            if (Clase_Variable_Publica.descuento_ft == 1) {
                JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para realizar descuento");
            } else {
                if (JOptionPane.showConfirmDialog(rootPane, "El descuento debe aplicarse al finalizar su factura ¿Desea continuar?",
                        "Descuento", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    desc_serv.setEditable(true);
                    desc_serv.requestFocus();
                }
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_desc_servMouseClicked

    private void desc_servActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desc_servActionPerformed
        if (cod_serv_fact.getText().isEmpty() && descrii_fact.getText().isEmpty()) {
            int fila = tablafacturacion1.getRowCount();
            if (fila > 0) {
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea aplicar descuento?",
                        "Descuento", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    if (!desc_serv.getText().isEmpty()) {
                        descuento_sinitbs();
                    } else {
                        sumar_total();
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Aplique el descuento al finalizar su factura");
                desc_serv.setText("0.00");
                cod_serv_fact.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aplique el descuento después de procesar el servicio");
            desc_serv.setText("0.00");
            cant_fact.requestFocus();
        }
    }//GEN-LAST:event_desc_servActionPerformed

    private void desc_servKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desc_servKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_desc_servKeyPressed

    private void desc_servKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desc_servKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && (c != '.')) {
            evt.consume();   
        }// TODO add your handling code here:
    }//GEN-LAST:event_desc_servKeyTyped

    private void precio_servMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_precio_servMouseClicked
        if (!precio_serv.getText().isEmpty()) {
            if (!precio_serv.isEditable()) {
                if (Clase_Variable_Publica.editar_precio_ft == 0) {
                    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea modificar el precio?",
                            "Modificar Precio", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        precio_serv.setEditable(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para editar el precio");
                }
            }
        } else {
        }// TODO add your handling code here:
    }//GEN-LAST:event_precio_servMouseClicked

    private void precio_servActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precio_servActionPerformed

        if (!cod_cli_fact.getText().isEmpty()) {
            if (cant_fact.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe digitar la cantidad");
                cant_fact.requestFocus();
                return;
            } else {
                buscaProductoEnModelo();
                sumar_total();
                precio_serv.setEditable(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe introducir un cliente");
            cod_cli_fact.requestFocus();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_precio_servActionPerformed

    private void precio_servKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precio_servKeyPressed
        if (!precio_serv.getText().isEmpty()) {
            procesar_fact.setEnabled(true);
            limpiar_fact.setEnabled(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_precio_servKeyPressed

    private void precio_servKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precio_servKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && (c != '.')) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_precio_servKeyTyped

    private void numero_id_ftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_id_ftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numero_id_ftActionPerformed

    private void buscar_rncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_rncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar_rncActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void procesar_factMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_procesar_factMouseMoved
        if (procesar_fact.isEnabled()) {
            //procesar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }// TODO add your handling code here:
    }//GEN-LAST:event_procesar_factMouseMoved

    private void procesar_factMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_procesar_factMouseExited
        if (procesar_fact.isEnabled()) {
            //procesar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        }// TODO add your handling code here:
    }//GEN-LAST:event_procesar_factMouseExited

    private void procesar_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesar_factActionPerformed
        float conpara = Float.parseFloat(total_devueltas.getText());
            if (conpara > 0.0 && !cod_cli_fact.getText().isEmpty() && !nom_cli_fact.getText().isEmpty()) {
                if(!cuenta_abierta.isSelected()){
                if(Clase_Variable_Publica.modificar_actrivo == 1){
//                    for(int i=0;i<tablafacturacion1.getRowCount();i++)
//                    {
//                    String capcod="",capcan="";
//                    capcod=tablafacturacion1.getValueAt(i, 0).toString();
//                    capcan=tablafacturacion1.getValueAt(i, 3).toString();          
//                    sumarstock(capcod, capcan);
//
//                    }
                cobro cb = new cobro();
                cb.setVisible(true);
                if(enviar_delivery.isSelected()){
                float total,delivery,total_c;
                DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00"); 
                total = Float.parseFloat(total_devueltas.getText());
                delivery = Float.parseFloat("25");
                total_c = total + delivery;
                cobro.Monto_pagar.setText(formateador.format(total_c));
                cobro.lbl_pagar.setText(String.valueOf(total_c));
                cobro.pago_delivery.setText("25");
                }else{
                cobro.Monto_pagar.setText(total_fact.getText());
                cobro.lbl_pagar.setText(total_devueltas.getText());    
                cobro.pago_delivery.setText("0.00");    
                }
                
                }else{
                cobro cb = new cobro();
                cb.setVisible(true);
                if(enviar_delivery.isSelected()){
                float total,delivery,total_c;
                DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00"); 
                total = Float.parseFloat(total_devueltas.getText());
                delivery = Float.parseFloat("25");
                total_c = total + delivery;
                cobro.Monto_pagar.setText(formateador.format(total_c));
                cobro.lbl_pagar.setText(String.valueOf(total_c));
                cobro.pago_delivery.setText("25");
                }else{
                cobro.Monto_pagar.setText(total_fact.getText());
                cobro.lbl_pagar.setText(total_devueltas.getText());    
                cobro.pago_delivery.setText("0.00");    
                }
                }

                }else{
                   if(Clase_Variable_Publica.modificar_actrivo == 1){
                       String[] options = {"Facturar", "Guardar", "Cancelar"};
                    //Integer[] options = {1, 3, 5, 7, 9, 11};
                    //Double[] options = {3.141, 1.618};
                    //Character[] options = {'a', 'b', 'c', 'd'};
                    int x = JOptionPane.showOptionDialog(null, "Elija la opción correspondiente",
                        "Cuenta Abierta",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);    
                         if(x==0){
                     for(int i=0;i<tablafacturacion1.getRowCount();i++)
                    {
                    String capcod="",capcan="";
                    capcod=tablafacturacion1.getValueAt(i, 0).toString();
                    capcan=tablafacturacion1.getValueAt(i, 3).toString();          
                    sumarstock(capcod, capcan);

                    }
                     cobro cb = new cobro();
                cb.setVisible(true);
              if(enviar_delivery.isSelected()){
                float total,delivery,total_c;
                DecimalFormat formateador = new DecimalFormat("RD$ #,##0.00"); 
                total = Float.parseFloat(total_devueltas.getText());
                delivery = Float.parseFloat("25");
                total_c = total + delivery;
                cobro.Monto_pagar.setText(formateador.format(total_c));
                cobro.lbl_pagar.setText(String.valueOf(total_c));
                cobro.pago_delivery.setText("25");
                }else{
                cobro.Monto_pagar.setText(total_fact.getText());
                cobro.lbl_pagar.setText(total_devueltas.getText());    
                cobro.pago_delivery.setText("0.00");    
                }
                }
                if(x==1){
                     for(int i=0;i<tablafacturacion1.getRowCount();i++)
                    {
                    String capcod="",capcan="";
                    capcod=tablafacturacion1.getValueAt(i, 0).toString();
                    capcan=tablafacturacion1.getValueAt(i, 3).toString();          
                    sumarstock(capcod, capcan);

                    }
                     try {
                           conectar_modificar();
                       } catch (SQLException ex) {
                           Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                       }
        
                } 
               if(x==2){}
                }else if(tablafacturacion1.isEnabled()){
                       try {
                           conectar_guardar();
                       } catch (SQLException ex) {
                           Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   
                   }else{
                   JOptionPane.showMessageDialog(null, "Debes de darle a modificar primero"); 
                } 
                   
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos para proceder a la factura");
            }
        
 
 

    

         
    }//GEN-LAST:event_procesar_factActionPerformed

    private void modificar_factMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificar_factMouseMoved
        if (modificar_fact.isEnabled()) {
            if (Clase_Variable_Publica.modificar_ft == 0) {
                //modificar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
            } else {
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_modificar_factMouseMoved

    private void modificar_factMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificar_factMouseExited
        if (modificar_fact.isEnabled()) {
            //modificar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_modificar_factMouseExited

    private void modificar_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificar_factActionPerformed
        if (modificar_fact.isEnabled()) {
            if (Clase_Variable_Publica.modificar_ft == 0) {
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea modificar la factura?",
                        "Modificar Factura", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    desbloquear();
                    Clase_Variable_Publica.modificar_actrivo = 1;
                    modificar_fact.setEnabled(false);
                    procesar_fact.setEnabled(true);
                    limpiar_fact.setEnabled(true);
                    //modificar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
                        for(int i=0;i<tablafacturacion1.getRowCount();i++)
                    {
                    String capcod="",capcan="";
                    capcod=tablafacturacion1.getValueAt(i, 0).toString();
                    capcan=tablafacturacion1.getValueAt(i, 3).toString();          
                    sumarstock(capcod, capcan);

                    } 

                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para modificar esta factura");
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_modificar_factActionPerformed

    private void limpiar_factMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiar_factMouseMoved
        if (limpiar_fact.isEnabled()) {
            //limpiar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }// TODO add your handling code here:
    }//GEN-LAST:event_limpiar_factMouseMoved

    private void limpiar_factMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiar_factMouseExited
        if (limpiar_fact.isEnabled()) {
            // limpiar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        }// TODO add your handling code here:
    }//GEN-LAST:event_limpiar_factMouseExited

    private void limpiar_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar_factActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente limpiar la factura?",
                "Limpiar Factura", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if(Clase_Variable_Publica.modificar_actrivo == 0){
            DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel();
            dtm.setRowCount(0);
            DefaultTableModel dtm1 = (DefaultTableModel) tablaF_out.getModel();
            dtm1.setRowCount(0);
            //cod_cli_fact.requestFocus();
            Clase_Variable_Publica.fact = 0;
            limpiar1();
            desbloquear();
            sumar_total();
            fecha_fact1.setText(Clase_Variable_Publica.fechaDom);
            fecha_fact2.setText(Clase_Variable_Publica.fechaus);
            tipo_comp.setSelectedItem("Consumidor Final");
            tipo_fact1.setText("Contado");
            modificar_fact.setEnabled(false);
            procesar_fact.setEnabled(false);
            limpiar_fact.setEnabled(false);
            reimprimir_fact.setEnabled(true);
            cuenta_abierta.setSelected(false);
            enviar_delivery.setSelected(false);
            //limpiar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
            funciones_factura fnf = new funciones_factura();
            fnf.cant_compro();
            fnf.cant_compro1();
            fnf.cant_compro2();
            try {
                fnf.conectar_cargar_rnc();
            } catch (SQLException ex) {
                Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            }if(Clase_Variable_Publica.modificar_actrivo == 1){
               for(int i=0;i<tablafacturacion1.getRowCount();i++)
                {
                String capcod="",capcan="";
                capcod=tablafacturacion1.getValueAt(i, 0).toString();
                capcan=tablafacturacion1.getValueAt(i, 3).toString();          
                descontarstock(capcod, capcan);

                }
                DefaultTableModel dtm = (DefaultTableModel) tablafacturacion1.getModel();
            dtm.setRowCount(0);
            DefaultTableModel dtm1 = (DefaultTableModel) tablaF_out.getModel();
            dtm1.setRowCount(0);
            //cod_cli_fact.requestFocus();
            Clase_Variable_Publica.fact = 0;
            limpiar1();
            desbloquear();
            sumar_total();
            fecha_fact1.setText(Clase_Variable_Publica.fechaDom);
            fecha_fact2.setText(Clase_Variable_Publica.fechaus);
            tipo_comp.setSelectedItem("Consumidor Final");
            tipo_fact1.setText("Contado");
            modificar_fact.setEnabled(false);
            procesar_fact.setEnabled(false);
            limpiar_fact.setEnabled(false);
            reimprimir_fact.setEnabled(true);
            //limpiar_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
            funciones_factura fnf = new funciones_factura();
            fnf.cant_compro();
            fnf.cant_compro1();
            fnf.cant_compro2();
            try {
                fnf.conectar_cargar_rnc();
            } catch (SQLException ex) {
                Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        }
        
        
    }//GEN-LAST:event_limpiar_factActionPerformed

    private void reimprimir_factMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reimprimir_factMouseMoved
        if (reimprimir_fact.isEnabled()) {
            //reimprimir_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
        }          // TODO add your handling code here:
    }//GEN-LAST:event_reimprimir_factMouseMoved

    private void reimprimir_factMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reimprimir_factMouseExited
        if (reimprimir_fact.isEnabled()) {
            //reimprimir_fact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        }// TODO add your handling code here:
    }//GEN-LAST:event_reimprimir_factMouseExited

    private void reimprimir_factActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reimprimir_factActionPerformed
        Reimprimir rpf = new Reimprimir();
        rpf.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_reimprimir_factActionPerformed

    private void cuadre_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadre_cajaActionPerformed
if (Clase_Variable_Publica.cuadre_ft == 0) {
    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea generar el cuadre?",
                    "Cuadre", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    Cuadre();
    }
}else {
                JOptionPane.showMessageDialog(rootPane, "Este usuario no tiene permiso para el cuadre de factura");
            }

    }//GEN-LAST:event_cuadre_cajaActionPerformed

    private void cuenta_abiertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuenta_abiertaMouseClicked
      // TODO add your handling code here:
    }//GEN-LAST:event_cuenta_abiertaMouseClicked

    private void cuenta_abiertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuenta_abiertaActionPerformed
    if(cuenta_abierta.isSelected()){
      para_comerAqui.setSelected(true);
      para_comerAqui.setEnabled(false);
      para_llevar.setEnabled(false);
      enviar_delivery.setSelected(false);
    }
else{
      para_comerAqui.setEnabled(true);
      para_llevar.setEnabled(true);  
    }// TODO add your handling code here:
    }//GEN-LAST:event_cuenta_abiertaActionPerformed

    private void enviar_deliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviar_deliveryActionPerformed
    if(enviar_delivery.isSelected()){
      para_llevar.setSelected(true);   
      para_comerAqui.setEnabled(false);
      para_llevar.setEnabled(false);
      cuenta_abierta.setSelected(false);
    }else{
      para_comerAqui.setEnabled(true);
      para_llevar.setEnabled(true);  
    }        // TODO add your handling code here:
    }//GEN-LAST:event_enviar_deliveryActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturacion().setVisible(true);
            }
        }); 
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnarticulo;
    private javax.swing.JButton Btncomprobantes;
    private javax.swing.JButton Btndelivery;
    private javax.swing.JButton Btndespacho;
    private javax.swing.JButton Btninventario;
    private javax.swing.JButton Btnusuario;
    private javax.swing.JButton btn_minimizar;
    public static javax.swing.JButton buscar_rnc;
    public static javax.swing.JButton buscar_serv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public static javax.swing.JTextField cant_fact;
    public static javax.swing.JTextField cod_cli_fact;
    public static javax.swing.JTextField cod_serv_fact;
    private javax.swing.JButton cuadre_caja;
    public static javax.swing.JCheckBox cuenta_abierta;
    public static javax.swing.JTextField desc_serv;
    public static javax.swing.JTextField descrii_fact;
    public static javax.swing.JLabel descuento_front;
    public static javax.swing.JLabel descuento_rp;
    public static javax.swing.JCheckBox enviar_delivery;
    public static javax.swing.JLabel fecha_fact1;
    public static javax.swing.JLabel fecha_fact2;
    public static javax.swing.JLabel fecha_fact3;
    public static javax.swing.JLabel itbis_fact;
    public static javax.swing.JLabel itbis_ft;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JButton limpiar_fact;
    public static javax.swing.JButton modificar_fact;
    public static javax.swing.JLabel ncf_anterior;
    public static javax.swing.JLabel ncf_disp;
    public static javax.swing.JLabel ncf_f;
    public static javax.swing.JLabel ncf_fep;
    public static javax.swing.JLabel ncf_fgb;
    public static javax.swing.JLabel ncf_reult;
    public static javax.swing.JTextField nom_cli_fact;
    public static javax.swing.JLabel nombre_usu_fact;
    public static javax.swing.JTextField nota_fact;
    public static javax.swing.JLabel num_fact_1;
    public static javax.swing.JTextField numero_id_ft;
    public static javax.swing.JRadioButton para_comerAqui;
    public static javax.swing.JRadioButton para_llevar;
    public static javax.swing.JTextField precio_serv;
    public static javax.swing.JButton procesar_fact;
    public static javax.swing.JButton reimprimir_fact;
    public static javax.swing.JTextField rnc_fact;
    public static javax.swing.JLabel sub_total;
    public static javax.swing.JLabel sub_total_ft;
    public static javax.swing.JTable tablaF_out;
    public static javax.swing.JTable tablafacturacion1;
    public static javax.swing.JComboBox<String> tipo_comp;
    public static javax.swing.JLabel tipo_fact1;
    public static javax.swing.JLabel tiponcf_anterior;
    public static javax.swing.JLabel total_devueltas;
    public static javax.swing.JLabel total_fact;
    private javax.swing.JButton volverAtras;
    // End of variables declaration//GEN-END:variables
    @Override 
    public void run() {
    Thread ct= Thread.currentThread();
    
    while(ct==hi){
    solicitud();
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){}
        }    
        
      
    }
      conector cc = new conector();
    Connection cn = cc.conexion();
   
     
}
