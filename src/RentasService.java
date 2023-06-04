import java.sql.SQLException;

public class RentasService {
    /*
    int identificador_cliente;
    String cliente;
    int identificador_auto;
    String automovil;
    String fecha_de_renta;
    String fecha_de_devolucion;
    int tiempo;
    String numero_tarjeta;
    double costo;*/
    public static void crearRenta(int identificador_cliente,String cliente,
                                  int identifiacdor_auto, String automovil,
                                  String fecha_de_renta, String fecha_de_devolcion,
                                  String numero_tarjeta, String fecha_de_caducidad, String cvv)
                           throws SQLException {
        Rentas rentaNueva = new Rentas();
        rentaNueva.setIdentificador_cliente(identificador_cliente);
        rentaNueva.setCliente(cliente);
        rentaNueva.setIdentificador_auto(identifiacdor_auto);
        rentaNueva.setAutomovil(automovil);
        rentaNueva.setFecha_de_renta(fecha_de_renta);
        rentaNueva.setFecha_de_devolucion(fecha_de_devolcion);
        rentaNueva.setTiempo();
        rentaNueva.setNumero_tarjeta(numero_tarjeta);
        rentaNueva.setCosto();
        rentaNueva.setFecha_de_caducidad(fecha_de_caducidad);
        rentaNueva.setCvv(cvv);
        RentasDAO.insertar_datos_rentas(rentaNueva, "rentas");
    }
    public static void editarRenta(int id_renta, int identificador_cliente,String cliente,
                                  int identifiacdor_auto, String automovil,
                                  String fecha_de_renta, String fecha_de_devolcion,
                                  String numero_tarjeta, String fecha_de_caducidad, String cvv)
            throws SQLException {
        Rentas rentaNueva = new Rentas();
        rentaNueva.setIdentificador_cliente(identificador_cliente);
        rentaNueva.setCliente(cliente);
        rentaNueva.setIdentificador_auto(identifiacdor_auto);
        rentaNueva.setAutomovil(automovil);
        rentaNueva.setFecha_de_renta(fecha_de_renta);
        rentaNueva.setFecha_de_devolucion(fecha_de_devolcion);
        rentaNueva.setTiempo();
        rentaNueva.setNumero_tarjeta(numero_tarjeta);
        rentaNueva.setCosto();
        rentaNueva.setFecha_de_caducidad(fecha_de_caducidad);
        rentaNueva.setCvv(cvv);
        RentasDAO.editarRentaPorId(id_renta,rentaNueva);
    }
}
