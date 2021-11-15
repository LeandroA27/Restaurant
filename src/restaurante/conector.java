 
package restaurante;

import Clases.conexion_2;
import java.sql.*;
import javax.swing.*;

public class conector {
   //////////////////////////////////////////////////////////////////////////
   ////////////////////////CONEXION PRUEBA NUBE//////////////////////////////
    //   String direccion = "jdbc:mysql://sql289.main-hosting.eu/u621224220_edpr";
    //   String usu = "u621224220_alex";
    //   String clave = "Thealex1027";
   /////////////////////////////////////////////////////////////////////////
   
   //////////////////////////////////////////////////////////////////////////
   ////////////////////////CONEXION EDEN NUBE///////////////////////////////////
   //String direccion = "jdbc:mysql://sql289.main-hosting.eu/u621224220_eden";
   //String usu = "u621224220_root";
   //String clave = "Thealex1027";
   /////////////////////////////////////////////////////////////////////////
   
   //////////////////////////////////////////////////////////////////////////
   ////////////////////////CONEXION LOCAL///////////////////////////////////
   String direccion = "jdbc:mysql://localhost:3306/eden_pr";
   String usu = "laxes";
   String clave = "laxespro";
   /////////////////////////////////////////////////////////////////////////
   String direccion2=conexion_2.cadena1;
   String usu2=conexion_2.cadena2;
   String clave2=conexion_2.cadena3;
   
   
    Connection conect = null;

    public Connection conexion() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            //org.gjt.mm.mysql.Driver
            //com.mysql.jdbc.Driver
            //org.mariadb.jdbc.Driver
            //
            conect = DriverManager.getConnection (direccion2,usu2,clave2);
            

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL SERVIDOR");
        }
        return conect;
    }
}

//"jdbc:mariadb://localhost:3308/eden_pr", "root", "123456789"
  //jdbc:postgresql://localhost:8080/postgres
//("jdbc:mysql://localhost:3306/eden_1.0", "root", "Thealex1027");
//("jdbc:mysql://sql289.main-hosting.eu/u621224220_edpr", "u621224220_alex", "Thealex1027");
//("jdbc:mysql://sql289.main-hosting.eu/u621224220_eden", "u621224220_root", "Thealex1027");
// ("jdbc:mysql://sql3.freemysqlhosting.net/sql3302690","sql3302690", "F5lq31Qwrp"); 
