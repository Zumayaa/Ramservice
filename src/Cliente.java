public class Cliente {
    int id_cliente;
    String nombre;
    String apellido;
    String correo;
    String telefono;
    String tarjeta_de_credito;
    String estado_de_cuenta;

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

    public String getTarjeta_de_credito() {
        return tarjeta_de_credito;
    }

    public void setTarjeta_de_credito(String tarjeta_de_credito) {
        this.tarjeta_de_credito = tarjeta_de_credito;
    }

    public String getEstado_de_cuenta() {
        return estado_de_cuenta;
    }

    public void setEstado_de_cuenta(String estado_de_cuenta) {
        this.estado_de_cuenta = estado_de_cuenta;
    }

    public Cliente(int id_cliente, String nombre, String apellido, String correo,
                   String telefono, String tarjeta_de_credito, String estado_de_cuenta){
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.tarjeta_de_credito = tarjeta_de_credito;
        this.estado_de_cuenta = estado_de_cuenta;
    }
}
