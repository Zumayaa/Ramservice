import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class Tarjetas_Service {
    public static void crear_tarjeta(String nombre, String apellido, String id_cliente,
                                     String numero_de_tarjeta,String fecha_de_caducidad,
                                     String cvv) throws SQLException {
        Tarjeta_Class tarjetaNueva = new Tarjeta_Class
                (nombre, apellido,
                numero_de_tarjeta, fecha_de_caducidad, cvv);

        Tarjetas_DAO.insertar_datos(tarjetaNueva, id_cliente);
    }

    public static void editar_tarjeta(String nombre, String apellido, String id_cliente,
                                      String numero_de_tarjeta,String fecha_de_caducidad,
                                      String cvv) throws SQLException {

        Tarjeta_Class tarjetaNueva = new Tarjeta_Class
                (nombre, apellido,
                        numero_de_tarjeta, fecha_de_caducidad, cvv);

        Tarjetas_DAO.editar_tarjeta_por_id(tarjetaNueva, Integer.parseInt(id_cliente));
    }
    public static void eliminar_tarjeta(int id_tarjeta_a_eliminar){
        Tarjetas_DAO.borrar_tarjeta_por_id(id_tarjeta_a_eliminar);
    }
    public static boolean existencia_de_tarjeta_con_usuario(int id_de_cliente) throws SQLException {
        return Tarjetas_DAO.existencia_tarjeta(id_de_cliente);
    }
    // funciones generales
    public static DefaultTableModel crear_dtm_de_tarjetas(String [] nombres_columnas, String consulta){
        Object [][] datos = Clientes_DAO.seleccionar_datos(consulta);
        DefaultTableModel dtm = new DefaultTableModel(datos,nombres_columnas);
        return dtm;
    }
    public static Object [][] crear_matriz_de_tarjetas(String consulta){
        return Rentas_DAO.seleccionar_datos(consulta);
    }
    public static String[] obtener_fila(String consulta){
        return Tarjetas_DAO.seleccionar_fila(consulta);
    }
    public static String[] obtener_columna(String consulta){
        return Tarjetas_DAO.seleccionar_columna(consulta);
    }
    public static String obtener_celda(String consulta){
        return Tarjetas_DAO.seleccionar_celda(consulta);
    }
}
