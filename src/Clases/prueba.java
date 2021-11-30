/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Formularios.Comprobantes;
import static Formularios.Comprobantes.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
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
public class prueba {

    private Connection conexion = null;
    private int dato;
    private int cont = 1;
    private String num = "";

    public void confirmar_fiscal() {
        String cont = "";
        String comp = "";
        String est = "";
        int compar = 0;
        int cant = 0;
        int clic = Clase_Variable_Publica.cot;
        int valor_m = 0;
        String pes = Comprobantes.comp_final_cp.getText();
        String es = "";
        String cadena = comp_inicial.getText();
        es = cadena;
        char r1 = es.charAt(0);
        char r2 = es.charAt(1);
        char r3 = es.charAt(2);
        char r4 = es.charAt(3);
        char r5 = es.charAt(4);
        char r6 = es.charAt(5);
        char r7 = es.charAt(6);
        char r8 = es.charAt(7);
        String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8;
        int var = Integer.parseInt(juntar);
        int dato = var;
        int dato1;
        String num1 = null;
        if ((dato > 0) && (dato <= 9)) {
            num1 = "B010000000" + dato;
        }
        if ((dato > 9) && (dato <= 99)) {
            num1 = "B01000000" + dato;
        }
        if ((dato > 99) && (dato <= 999)) {
            num1 = "B0100000" + dato;
        }
        if ((dato > 999) && (dato <= 9999)) {
            num1 = "B010000" + dato;
        }
        if ((dato > 9999) && (dato <= 99999)) {
            num1 = "B01000" + dato;
        }
        num = num1;
        String[] registros = new String[2];
        String sql = "SELECT ncf FROM secuencia_ncf where ncf='" + num + "'";
        try {
            java.sql.Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                registros[0] = rs.getString("ncf");
                est = registros[0];
            }
            if (!est.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El comprobante inicial ya esta registrado");

            } else {
                prueba1();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void conecta() throws SQLException {
//           String direc = conexion_2.valor;
//    String contra = conexion_2.clave;
//    String usu = conexion_2.usu;
//    
//        String jdbc= direc;
        conexion = DriverManager.getConnection(conexion_2.cadena1, conexion_2.cadena2, conexion_2.cadena3);
        conexion.setAutoCommit(false);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

    public void prueba1() {
        Fecha();
        int valor_m = 0;
        String pes = comp_final_cp.getText();
        String es = "";
        String cadena = comp_inicial.getText();
        es = cadena;
        char r1 = es.charAt(0);
        char r2 = es.charAt(1);
        char r3 = es.charAt(2);
        char r4 = es.charAt(3);
        char r5 = es.charAt(4);
        char r6 = es.charAt(5);
        char r7 = es.charAt(6);
        char r8 = es.charAt(7);
        String juntar = "" + r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8;
        int var = Integer.parseInt(juntar);

        char l1 = pes.charAt(0);
        char l2 = pes.charAt(1);
        char l3 = pes.charAt(2);
        char l4 = pes.charAt(3);
        char l5 = pes.charAt(4);
        char l6 = pes.charAt(5);
        char l7 = pes.charAt(6);
        char l8 = pes.charAt(7);
        String juntar1 = "" + l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8;
        valor_m = Integer.parseInt(juntar1);
        if (var > valor_m) {
            JOptionPane.showMessageDialog(null, "El primer comprobante no debe ser menor al ultimo comprobante");
            comp_inicial.requestFocus();
        } else {

            for (int i = var; i <= valor_m; i++) {
                System.out.println(i);
                String ob = "Si";
                int dato = i;
                int dato1;
                String num1 = null;
                if ((dato > 0) && (dato <= 9)) {
                    num1 = "B010000000" + dato;
                }
                if ((dato > 9) && (dato <= 99)) {
                    num1 = "B01000000" + dato;
                }
                if ((dato > 99) && (dato <= 999)) {
                    num1 = "B0100000" + dato;
                }
                if ((dato > 999) && (dato <= 9999)) {
                    num1 = "B010000" + dato;
                }
                if ((dato > 9999) && (dato <= 99999)) {
                    num1 = "B01000" + dato;
                }
                try {
                    String sql = "INSERT INTO secuencia_ncf(ncf,Disponible,fecha_vigencia)VALUES('" + num1 + "','" + ob + "',STR_TO_DATE('" + fecha_in.getText() + "','%d-%m-%Y'))";
                    PreparedStatement pat = cn.prepareStatement(sql);
                    //STR_TO_DATE('"+registro_comprobantes.fecha_in.getText()+"','%d-%m-%Y')
                    int n;
                    n = pat.executeUpdate();
                    if (n > 0) {

                    }

                } catch (SQLException e) {
                    System.out.print(e.getMessage());
                }
            }
            JOptionPane.showMessageDialog(null, "Comprobantes registrados correctamente");
            fecha_in.setText("");
            comp_inicial.setText("");
            comp_final_cp.setText("");
            tipo_ncf.setText("");
            siguiente_nfc.setText("");
            ultimo_nfc.setText("");
            disponible_nfc.setText("");
            caducacion_nfc.setText("");
            comp_inicial.requestFocus();
            String comparacion = (String) tipo_compro.getSelectedItem();
            tipo_ncf.setText(comparacion);
            cargar_fiscal();

        }
    }

    public void Fecha() {
        try {
            Date fecha = fecha_comprobante.getDate();
            DateFormat f = new SimpleDateFormat("dd-MM-YYYY");
            String fecha2 = f.format(fecha);
            //fechaa = (fecha2);
            fecha_in.setText(fecha2);
        } catch (Exception e) {

        }
    }
    public void cargar_fecha(){
            SimpleDateFormat formatodeltexto = new SimpleDateFormat("yyyyy/MM/dd");
        String fecha =fecha_in.getText();
         Date dato = null;
    try {
        dato = (Date) formatodeltexto.parse(fecha);
        fecha_comprobante.setDate(dato);
    } catch (ParseException ex) {
        Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
    }
} 
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void cargar_fiscal() {
        String est = "Si";

        String[] registros = new String[11];
        String sql = "SELECT count(ncf) as conteo,MIN(ncf) as minimo,MAX(ncf) as maximo,date_format(fecha_vigencia,'%d/%m/%Y') as fecha FROM secuencia_ncf where Disponible='" + est + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("conteo");
                registros[1] = rs.getString("minimo");
                registros[2] = rs.getString("maximo");
                registros[3] = rs.getString("fecha");

                siguiente_nfc.setText(registros[1]);
                ultimo_nfc.setText(registros[2]);
                disponible_nfc.setText(registros[0]);
                caducacion_nfc.setText(registros[3]);
                

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
     
    conector cc = new conector();
    Connection cn = cc.conexion();
}
