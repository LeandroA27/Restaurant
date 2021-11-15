/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import restaurante.conector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Thealex7
 */
public class conexion_2 {
    
public Connection conexion = null;


public static String cadena1;
public static String cadena2;
public static String cadena3;
//public static String valor ="jdbc:mysql://localhost:3306/eden_pr";
//public static String usu = "laxes";
//public static String clave = "laxespro";

////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////prueba nube////////////////////////////////
//public static String valor ="jdbc:mysql://sql289.main-hosting.eu/u621224220_edpr";
//public static String usu = "u621224220_alex";
//public static String clave = "Thealex1027";
//////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////eden nube////////////////////////////////
//public static String valor ="jdbc:mysql://sql289.main-hosting.eu/u621224220_eden";
//public static String usu = "u621224220_root";
//public static String clave = "Thealex1027";
//////////////////////////////////////////////////////////////////////////////////

}

//        String jdbc= "jdbc:mysql://sql289.main-hosting.eu/u621224220_edpr";
//        conexion = DriverManager.getConnection(jdbc,"u621224220_alex", "Thealex1027");
//        conexion.setAutoCommit(false);
        
//        String jdbc= "jdbc:mysql://sql289.main-hosting.eu/u621224220_eden";
//        conexion = DriverManager.getConnection(jdbc,"u621224220_root", "Thealex1027");
//        conexion.setAutoCommit(false);