public class Clientes_Class {
    int id_cliente;
    String nombre;
    String apellido;
    String correo;
    String telefono;
    String numero_de_tarjeta;
    String fecha_de_caducidad;
    String cvv;
    String password;

    public Clientes_Class(String nombre, String apellido, String correo, String telefono, String numero_de_tarjeta, String fecha_de_caducidad, String cvv, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.numero_de_tarjeta = numero_de_tarjeta;
        this.fecha_de_caducidad = fecha_de_caducidad;
        this.cvv = cvv;
        this.password = password;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
