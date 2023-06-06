import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class Renta_Service {
    // modificar las tablas
    public static void crearRenta(int identificador_cliente,String cliente,
                                  int identifiacdor_auto, String automovil,
                                  String fecha_de_renta, String fecha_de_devolcion,
                                  String numero_tarjeta, String fecha_de_caducidad, String cvv)
            throws SQLException {
        Rentas_Class rentaNueva = new Rentas_Class();
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
        Rentas_DAO.insertar_datos(rentaNueva);
    }
    public static void editarRenta(int id_renta, int identificador_cliente,String cliente,
                                   int identifiacdor_auto, String automovil,
                                   String fecha_de_renta, String fecha_de_devolcion,
                                   String numero_tarjeta, String fecha_de_caducidad, String cvv)
            throws SQLException {
        Rentas_Class rentaNueva = new Rentas_Class();
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
        Rentas_DAO.editarRentaPorId(id_renta,rentaNueva);
    }


    public static void borrar_renta(int id_renta) {
        Rentas_DAO.borrar_renta_por_id(id_renta);
    }
    // funciones para hacer validaciones
    public static String comprobar_fechas(int id_auto, String fecha_de_renta, String fecha_de_devolucion, String fecha_de_caducidad){
        String mensaje = "";
        if (!Rentas_DAO.conflicto_entre_fechas(id_auto, fecha_de_renta, fecha_de_devolucion)
                && Fechas.verificarLegalidadDeFechas(fecha_de_renta,fecha_de_devolucion, "RENTAR")
                && Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD")){
            mensaje = "Permitido";
        }
        else {
            if (Rentas_DAO.conflicto_entre_fechas(id_auto, fecha_de_renta, fecha_de_devolucion)){
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
        if(!Rentas_DAO.conflicto_entre_fechas(id_de_renta_a_editar,id_auto, fecha_de_renta, fecha_de_devolucion)
                && Fechas.verificarLegalidadDeFechas(fecha_de_renta,fecha_de_devolucion, "RENTAR")
                && Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD")){
            mensaje = "Permitido";
        }
        else {
            if (Rentas_DAO.conflicto_entre_fechas(id_de_renta_a_editar,id_auto, fecha_de_renta, fecha_de_devolucion)){
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
    // funciones apra rellenar tablas, combobboxes y poner textos
    public static DefaultTableModel crear_dtm_de_rentas(String [] nombres_columnas, String consulta){
        Object [][] datos = Rentas_DAO.seleccionar_datos(consulta);
        DefaultTableModel dtm = new DefaultTableModel(datos,nombres_columnas);
        return dtm;
    }

    public static String[] crear_arreglo_de_rentas(String [] nombres_columnas, String consulta){
        String seccion_de_datos_seleccionada [] = null;
        return seccion_de_datos_seleccionada;
    }
    public static Object [][] crear_matriz_de_rentas(String consulta){
        return Rentas_DAO.seleccionar_datos(consulta);
    }
    public static String[] obtener_fila(String consulta){
        return Rentas_DAO.seleccionar_fila(consulta);
    }
    public static String[] obtener_columna(String consulta){
        return Rentas_DAO.seleccionar_columna(consulta);
    }
}
