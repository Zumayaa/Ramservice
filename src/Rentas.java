import java.sql.SQLException;
import java.util.ArrayList;

public class Rentas {
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

    public Rentas(int id_renta, int identificador_cliente, String cliente, int identificador_auto, String automovil, String fecha_de_renta, String fecha_de_devolucion, int tiempo, String numero_tarjeta, String fecha_de_caducidad, String cvv, double costo) {
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
    public Rentas(){

    }

    public int getId_renta() {
        return id_renta;
    }

    public void setId_renta(int id_renta) {
        this.id_renta = id_renta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    public double getCosto() {
        return costo;
    }

    public void setCosto() throws SQLException {
        this.costo = AutosDAO.getCosto(getIdentificador_auto());
    }
    public int getIdentificador_cliente() {
        return identificador_cliente;
    }

    public void setIdentificador_cliente(int identificador_cliente) {
        this.identificador_cliente = identificador_cliente;
    }

    public int getIdentificador_auto() {
        return identificador_auto;
    }

    public void setIdentificador_auto(int identificador_auto) {
        this.identificador_auto = identificador_auto;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo() {
        this.tiempo = Fechas.getDias_De_Renta(getFecha_de_renta(),getFecha_de_devolucion());
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

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public ArrayList<String> getRentasDatos(){
        ArrayList<String> datos = new ArrayList<>();
        datos.add(String.valueOf(identificador_cliente));
        datos.add(cliente);
        datos.add(String.valueOf(identificador_auto));
        datos.add(automovil);
        datos.add(fecha_de_renta);
        datos.add(fecha_de_devolucion);
        datos.add(String.valueOf(tiempo));
        datos.add(numero_tarjeta);
        datos.add(String.valueOf(costo));
        return datos;
    }
}
