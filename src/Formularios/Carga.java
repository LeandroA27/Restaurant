/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.Clase_Variable_Publica;
import Clases.archivo_txt;
import Clases.cargar_barra_eden;
import Clases.conexion_2;
import Clases.generador_numerico;
//import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import tipografias.Fuentes;

/**
 *
 * @author Leandro Aquino
 */
public class Carga extends javax.swing.JFrame {

   cargar_barra_eden hilo;
   Fuentes tipofuente;
   private Connection conexion = null;
    public Carga() {
        initComponents();
                pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
        
        this.setBackground(new Color(0,0,0,0));
        setLocationRelativeTo(null);
         try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }

                         tipofuente = new Fuentes();

        pan1.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan2.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan3.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan4.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan5.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan6.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan7.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan8.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan9.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan10.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan11.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan12.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
        pan13.setFont(tipofuente.fuente(tipofuente.RIO, 0, 8));
         progreso.setFont(tipofuente.fuente(tipofuente.RIO, 0, 6));
        
        leertxt();
        try {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes_Iconos/hoja-11.png"));
        setIconImage(icon);
 setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        hilo = new cargar_barra_eden(progreso);
        hilo.start();
        hilo=null;
      pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
    }

    void iniciar() throws SQLException
    {
      if(progreso.getValue()==5)
{
       pan1.setVisible(true);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
       
       }
   if(progreso.getValue()==10)
{
    conectar();
    pan1.setVisible(false);
        pan2.setVisible(true);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
        
}
        if(progreso.getValue()==15)
{
      pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(true);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
        
        
}

      if(progreso.getValue()==20)
{
        Consultar_num_cot();
       pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(true);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
        
}
      if(progreso.getValue()==25)
{
       pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(true);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
}
      if(progreso.getValue()==30)
{
    
       pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(true);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
        
}
      if(progreso.getValue()==35)
{
        pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(true);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
        
}
      if(progreso.getValue()==40)
{
        pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(true);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
        
}
      if(progreso.getValue()==45)
{
        
       pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(true);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
}
      if(progreso.getValue()==50)
{
        pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(true);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(false);
}
      if(progreso.getValue()==55)
{
        pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(true);
        pan12.setVisible(false);
        pan13.setVisible(false);
}
      if(progreso.getValue()==60)
{
        pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(true);
        pan13.setVisible(false);
}
      if(progreso.getValue()==70)
{
        pan1.setVisible(false);
        pan2.setVisible(false);
        pan3.setVisible(false);
        pan4.setVisible(false);
        pan5.setVisible(false);
        pan6.setVisible(false);
        pan7.setVisible(false);  
        pan8.setVisible(false);
        pan9.setVisible(false);
        pan10.setVisible(false);
        pan11.setVisible(false);
        pan12.setVisible(false);
        pan13.setVisible(true);
}
        if(progreso.getValue()==100)
    {
      
        Login ob = new Login();
        dispose();
        ob.setVisible(true);
        Login.usuario_l.requestFocus();
    }
    }
    public void conecta() throws SQLException{
//           String direc = conexion_2.valor;
//    String contra = conexion_2.clave;
//    String usu = conexion_2.usu;
//    
//        String jdbc= direc;
        conexion = DriverManager.getConnection(conexion_2.cadena1,conexion_2.cadena2,conexion_2.cadena3);
        conexion.setAutoCommit(false);
    } 
//------------------------------------------------------------------------//
public void conectar() throws SQLException {
        try {
            conecta();
            cargar_dia();
        } finally {
            cerrar();
        }
    }   
  public void cerrar() throws SQLException{
        
    }
  //-----------------------------------------------------------------------------------------------//
    public void leertxt(){
        archivo_txt a = new archivo_txt();
        String sl = a.leerTxt("conector.txt");
        conexion_2.cadena1=sl;
        String s2 = a.leerTxt1("conector.txt");
        conexion_2.cadena2=s2;
        String s3 = a.leerTxt2("conector.txt");
        conexion_2.cadena3=s3;

        //System.out.println(sl);
    }
 //----------------------------------------------------------------------------------------------//
    void cargar_dia(){
      String es="",es1="";
    String[] registros = new String[2];
    String sql1 = "set time_zone = '-04:00'"; 
    String sql2 = "SELECT date_format(CURDATE(),'%d / %m / %Y') as fechaDom, CURDATE() as fechaUS"; 
    //String sql = "SELECT (ELT(WEEKDAY(CURRENT_DATE()) + 1, 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo')) AS DIA_SEMANA"; 
     try {
                java.sql.Statement st = conexion.createStatement();
                ResultSet rs1 = st.executeQuery(sql1);
                ResultSet rs2 = st.executeQuery(sql2);
                //ResultSet rs = st.executeQuery(sql);
//            if(rs.next()){
//                registros[0] = rs.getString("DIA_SEMANA");
//                es=registros[0];
//            }
             if(rs2.next()){
                registros[0] = rs2.getString("fechaDom");
                registros[1] = rs2.getString("fechaUS");
                Clase_Variable_Publica.fechaDom=registros[0];
                Clase_Variable_Publica.fechaus=registros[1];
            }
//            menu_fecha1.setText(es);
            
            //menu_fecha.setText(formato1.format(sistFecha1));
            
            }catch(SQLException ex){
     JOptionPane.showMessageDialog(null, ex);
    
}
  }
//---------------------------------------------------------------------------------------------------------------------------//
public void Consultar_num_cot() throws SQLException{
        int l;
        int cont=1;
        String num="";
        String es="";
        String SQL="SELECT MAX(numero)AS numero FROM contador_facturas";
        conecta();
        try {
            java.sql.Statement st = conexion.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){
                es=rs.getString(1);
            }
            
            if(es==null){
                Clase_Variable_Publica.numeroFactura="00001";
                //Facturacion.num_fact_1.setText("00001");
                
            }else{
                /*char r1 = es.charAt(0);
                char r2 = es.charAt(1);
                char r3 = es.charAt(2);
                char r4 = es.charAt(3);
                
                String juntar = ""+r1+r2+r3+r4;*/
                int var=Integer.parseInt(es);
                
            generador_numerico p = new generador_numerico();
            p.Generar_cliente(var);
            
            //JOptionPane.showMessageDialog(null, Clase_Variable_Publica.contador);
             Clase_Variable_Publica.numeroFactura=p.serie_cli();
                
            }
        } catch (Exception e) {
        }
    }   
//--------------------------------------------------------------------------------------------------------------------------------//
public void cargar_per_ft() throws SQLException{
    String sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8;
    String[] registros = new String[30];
    String sql = "SELECT descuento,edit_precio,tipo_comp,modif_fact from usuario where nombre_usu='"+Menu_Principal.nombre_usu_cli.getText()+"'";
conecta();
try{
    java.sql.Statement st = conexion.createStatement();
    ResultSet rs = st.executeQuery(sql);
    
    while (rs.next()) {
        registros[0] = rs.getString("descuento");
        registros[1] = rs.getString("edit_precio");
        registros[2] = rs.getString("tipo_comp");
        registros[3] = rs.getString("modif_fact");

        sl1 = registros[0];
        sl2 = registros[1];
        sl3= registros[2];
        sl4 = registros[3];

        
        if(sl1.equals("Si")){ Clase_Variable_Publica.descuento_ft = 0; }
        else{Clase_Variable_Publica.descuento_ft = 1;}
      
        if(sl2.equals("Si")){ Clase_Variable_Publica.editar_precio_ft = 0; }
        else{Clase_Variable_Publica.editar_precio_ft = 1;}

        if(sl3.equals("Si")){ Clase_Variable_Publica.comprobante = 0; }
        else{Clase_Variable_Publica.comprobante = 1;}

        if(sl4.equals("Si")){ Clase_Variable_Publica.modificar_ft = 0; }
        else{Clase_Variable_Publica.modificar_ft = 1;}


  }   

}catch (SQLException ex){
    JOptionPane.showMessageDialog(null, ex);
    }
} 

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan7 = new javax.swing.JLabel();
        pan10 = new javax.swing.JLabel();
        pan11 = new javax.swing.JLabel();
        pan5 = new javax.swing.JLabel();
        pan13 = new javax.swing.JLabel();
        pan6 = new javax.swing.JLabel();
        pan2 = new javax.swing.JLabel();
        pan12 = new javax.swing.JLabel();
        progreso = new javax.swing.JProgressBar();
        pan4 = new javax.swing.JLabel();
        pan9 = new javax.swing.JLabel();
        pan8 = new javax.swing.JLabel();
        pan1 = new javax.swing.JLabel();
        pan3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pan7.setBackground(new java.awt.Color(0, 0, 0));
        pan7.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan7.setText("Cargando Modulo de Reportes");
        getContentPane().add(pan7, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan10.setBackground(new java.awt.Color(0, 0, 0));
        pan10.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan10.setText("Cargando Reportes");
        getContentPane().add(pan10, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan11.setBackground(new java.awt.Color(0, 0, 0));
        pan11.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan11.setText("Iniciando Sistema");
        getContentPane().add(pan11, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan5.setBackground(new java.awt.Color(0, 0, 0));
        pan5.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan5.setText("Cargando Modulo de Servicios");
        getContentPane().add(pan5, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan13.setBackground(new java.awt.Color(0, 0, 0));
        pan13.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan13.setText("Conectando a la Base de Datos");
        getContentPane().add(pan13, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan6.setBackground(new java.awt.Color(0, 0, 0));
        pan6.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan6.setText("Cargando Modulo de Usuarios");
        getContentPane().add(pan6, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan2.setBackground(new java.awt.Color(0, 0, 0));
        pan2.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan2.setText("Cargando Modulo de Factura");
        getContentPane().add(pan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan12.setBackground(new java.awt.Color(0, 0, 0));
        pan12.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan12.setText("Cargando Modulo de Login");
        getContentPane().add(pan12, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        progreso.setBackground(new java.awt.Color(255, 255, 255));
        progreso.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        progreso.setForeground(new java.awt.Color(193, 14, 26));
        progreso.setBorderPainted(false);
        progreso.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        progreso.setOpaque(true);
        progreso.setRequestFocusEnabled(false);
        progreso.setString("");
        progreso.setStringPainted(true);
        progreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                progresoStateChanged(evt);
            }
        });
        getContentPane().add(progreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 230, 131, 10));

        pan4.setBackground(new java.awt.Color(0, 0, 0));
        pan4.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan4.setText("Cargando Modulo de Cliente");
        getContentPane().add(pan4, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan9.setBackground(new java.awt.Color(0, 0, 0));
        pan9.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan9.setText("Cargando Modulo de Consulta");
        getContentPane().add(pan9, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan8.setBackground(new java.awt.Color(0, 0, 0));
        pan8.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan8.setText("Cargar Modulo de Gastos");
        getContentPane().add(pan8, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan1.setBackground(new java.awt.Color(0, 0, 0));
        pan1.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan1.setText("Iniciando Sistema");
        getContentPane().add(pan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        pan3.setBackground(new java.awt.Color(0, 0, 0));
        pan3.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        pan3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pan3.setText("Cargando Modulo de Cotizacion");
        getContentPane().add(pan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 245, 160, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Carga.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void progresoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_progresoStateChanged
        try {
            iniciar();
            UIManager.put( "nimbusOrange", new Color(193, 14, 26) );
        } catch (SQLException ex) {
            Logger.getLogger(Carga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_progresoStateChanged

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
            java.util.logging.Logger.getLogger(Carga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Carga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Carga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Carga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Carga().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel pan1;
    private javax.swing.JLabel pan10;
    private javax.swing.JLabel pan11;
    private javax.swing.JLabel pan12;
    private javax.swing.JLabel pan13;
    private javax.swing.JLabel pan2;
    private javax.swing.JLabel pan3;
    private javax.swing.JLabel pan4;
    private javax.swing.JLabel pan5;
    private javax.swing.JLabel pan6;
    private javax.swing.JLabel pan7;
    private javax.swing.JLabel pan8;
    private javax.swing.JLabel pan9;
    private javax.swing.JProgressBar progreso;
    // End of variables declaration//GEN-END:variables
}
