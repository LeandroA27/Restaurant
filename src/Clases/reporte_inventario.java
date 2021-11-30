package Clases;

public class reporte_inventario {

    private String codigo;
    private String descripcion;
    private String suplidor;
    private String ubicacion;
    private String costo;
    private String precio;
    private String existencia;

    public reporte_inventario(String codigo, String descripcion, String suplidor, String ubicacion, String costo, String precio, String existencia) {

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.suplidor = suplidor;
        this.ubicacion = ubicacion;
        this.costo = costo;
        this.precio = precio;
        this.existencia = existencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(String suplidor) {
        this.suplidor = suplidor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
    }


    

}
