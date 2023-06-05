import javax.swing.table.DefaultTableModel;
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
    public static String comprobar_fechas(int id_auto, String fecha_de_renta, String fecha_de_devolucion, String fecha_de_caducidad){
        String mensaje = "";
        if (!RentasDAO.conflicto_entre_fechas(id_auto, fecha_de_renta, fecha_de_devolucion)
            && Fechas.verificarLegalidadDeFechas(fecha_de_renta,fecha_de_devolucion, "RENTAR")
            && Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD")){
                    mensaje = "Permitido";
            }
            else {
                if (RentasDAO.conflicto_entre_fechas(id_auto, fecha_de_renta, fecha_de_devolucion)){
                    mensaje += "Fecha no disponible\n";
                    }
                if (!Fechas.verificarLegalidadDeFechas(fecha_de_renta,fecha_de_devolucion, "RENTAR")){
                    mensaje += "Fecha ilegal\n";
                }
                if (!Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD")){
                    mensaje += "Fecha de tarjeta caducada\n";
                }

            }
        return mensaje;
    }
    public static String comprobar_fechas_editar(int id_de_renta_a_editar,int id_auto, String fecha_de_renta, String fecha_de_devolucion, String fecha_de_caducidad){
        String mensaje = "";
        if(!RentasDAO.conflicto_entre_fechas(id_de_renta_a_editar,id_auto, fecha_de_renta, fecha_de_devolucion)
                && Fechas.verificarLegalidadDeFechas(fecha_de_renta,fecha_de_devolucion, "RENTAR")
                && Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD")){
            mensaje = "Permitido";
        }
        else {
            if (RentasDAO.conflicto_entre_fechas(id_de_renta_a_editar,id_auto, fecha_de_renta, fecha_de_devolucion)){
                mensaje += "Fecha no disponible\n";
            }
            if (!Fechas.verificarLegalidadDeFechas(fecha_de_renta,fecha_de_devolucion, "RENTAR")){
                mensaje += "Fecha ilegal\n";
            }
            if (!Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD")){
                mensaje += "Fecha de tarjeta caducada\n";
            }
        }

        return mensaje;
    }

    public static void borrar_renta(int id_renta) {
        RentasDAO.borrar_renta_por_id(id_renta);
    }
    public static DefaultTableModel descargar_tabla_dtm(String query){
        return RentasDAO.descargar_filas_tabla_dtm(query);
    }
    public static Object[] descargar_tabla_arreglo(String query, String nombre_tabla, int numero_columna){
        return RentasDAO.descargar_columnas_tabla_arreglo(query, nombre_tabla, numero_columna);
    }
}
