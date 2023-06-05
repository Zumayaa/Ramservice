public class Rentas_Class {
    int id_renta;
    int identificador_cliente;
    String cliente;
    int identificador_auto;
    String automovil;
    String fecha_de_renta;
    String fecha_de_devolucion;
    int tiempo;
    String numero_tarjeta;
    String fecha_de_caducidad;
    String cvv;
    double costo;

    public Rentas_Class(int id_renta, int identificador_cliente, String cliente, int identificador_auto, String automovil, String fecha_de_renta, String fecha_de_devolucion, int tiempo, String numero_tarjeta, String fecha_de_caducidad, String cvv, double costo) {
        this.id_renta = id_renta;
        this.identificador_cliente = identificador_cliente;
        this.cliente = cliente;
        this.identificador_auto = identificador_auto;
        this.automovil = automovil;
        this.fecha_de_renta = fecha_de_renta;
        this.fecha_de_devolucion = fecha_de_devolucion;
        this.tiempo = tiempo;
        this.numero_tarjeta = numero_tarjeta;
        this.fecha_de_caducidad = fecha_de_caducidad;
        this.cvv = cvv;
        this.costo = costo;
    }

    public int getId_renta() {
        return id_renta;
    }

    public void setId_renta(int id_renta) {
        this.id_renta = id_renta;
    }

    public int getIdentificador_cliente() {
        return identificador_cliente;
    }

    public void setIdentificador_cliente(int identificador_cliente) {
        this.identificador_cliente = identificador_cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getIdentificador_auto() {
        return identificador_auto;
    }

    public void setIdentificador_auto(int identificador_auto) {
        this.identificador_auto = identificador_auto;
    }

    public String getAutomovil() {
        return automovil;
    }

    public void setAutomovil(String automovil) {
        this.automovil = automovil;
    }

    public String getFecha_de_renta() {
        return fecha_de_renta;
    }

    public void setFecha_de_renta(String fecha_de_renta) {
        this.fecha_de_renta = fecha_de_renta;
    }

    public String getFecha_de_devolucion() {
        return fecha_de_devolucion;
    }

    public void setFecha_de_devolucion(String fecha_de_devolucion) {
        this.fecha_de_devolucion = fecha_de_devolucion;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
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

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
