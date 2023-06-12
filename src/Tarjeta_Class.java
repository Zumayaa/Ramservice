public class Tarjeta_Class {
    String nombres_cliente;
    String apellidos_cliente;
    String numero_de_tarjeta;
    String fecha_de_caducidad;
    String cvv;

    public Tarjeta_Class(String nombres_cliente, String apellidos_cliente, String numero_de_tarjeta, String fecha_de_caducidad, String cvv) {
        this.nombres_cliente = nombres_cliente;
        this.apellidos_cliente = apellidos_cliente;
        this.numero_de_tarjeta = numero_de_tarjeta;
        this.fecha_de_caducidad = fecha_de_caducidad;
        this.cvv = cvv;
    }
    public String getNombres_cliente() {
        return nombres_cliente;
    }

    public void setNombres_cliente(String nombres_cliente) {
        this.nombres_cliente = nombres_cliente;
    }

    public String getApellidos_cliente() {
        return apellidos_cliente;
    }

    public void setApellidos_cliente(String apellidos_cliente) {
        this.apellidos_cliente = apellidos_cliente;
    }

    public String getNumero_de_tarjeta() {
        return numero_de_tarjeta;
    }

    public void setNumero_de_tarjeta(String numero_de_tarjeta) {
        this.numero_de_tarjeta = numero_de_tarjeta;
    }

    public String getFecha_de_caducidad() {
        return fecha_de_caducidad;
    }

    public void setFecha_de_caducidad(String fecha_de_caducidad) {
        this.fecha_de_caducidad = fecha_de_caducidad;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}