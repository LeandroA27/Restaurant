
package Clases;


public class Factura_1 { 
  private String cantidad;
  private String descripcion;
  private String total;
  private String precio;
  
 

    public Factura_1(String cantidad, String precio, String descripcion, String total) {
     
this.cantidad = cantidad;
this.descripcion = descripcion;
this.total = total;
this.precio = precio;
    }
 
       public String getcantidad() {
        return cantidad;
    }
    
    public void setcantidad(String precio) {
        this.cantidad = precio;
    }
      public String getprecio() {
        return precio;
    }
    
    public void setprecio(String precio) {
        this.precio = precio;
    }
    public String getdescripcion() {
        return descripcion;
    }
    
    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String gettotal() {
        return total;
    }
    
    public void settotal(String total) {
        this.total = total;
    }


}

