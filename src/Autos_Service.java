import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Map;

public class Autos_Service {
    public static void crearAuto(String nombre_auto, String marca, String categoria, String kilometraje, String costo, String img_dir)
            throws SQLException {
        Autos_Class autoNuevo = new Autos_Class(nombre_auto, marca, categoria, kilometraje, costo, img_dir);
        Autos_DAO.insertar_datos(autoNuevo);
    }

    public static void editarAuto(int id_de_auto, String nombre_auto, String marca, String categoria, String kilometraje, String costo, String img_dir)
            throws SQLException {
        Autos_Class autoNuevo = new Autos_Class(nombre_auto, marca, categoria, kilometraje, costo, img_dir);
        Autos_DAO.editar_auto_por_id(id_de_auto,autoNuevo);
    }

    public static void borrar_auto(int id_auto) {
        Autos_DAO.borrar_auto_por_id(id_auto);
    }
    // funciones apra rellenar tablas, combobboxes y poner textos
    public static DefaultTableModel crear_dtm_de_autos(String [] nombres_columnas, String consulta){
        Object [][] datos = Autos_DAO.seleccionar_datos(consulta);
        DefaultTableModel dtm = new DefaultTableModel(datos,nombres_columnas);
        return dtm;
    }
    public static Object [][] crear_matriz_de_autos(String consulta){
        return Autos_DAO.seleccionar_datos(consulta);
    }
    public static String[] obtener_fila(String consulta){
        return Autos_DAO.seleccionar_fila(consulta);
    }
    public static String obtener_celda(String consulta){
        return Autos_DAO.seleccionar_celda(consulta);
    }
    public static String[] obtener_columna(String consulta){
        return Autos_DAO.seleccionar_columna(consulta);
    }
    // funciones muy especificas
    public static Map<Integer, String> obtener_id_nombre_auto() throws SQLException {
        return Autos_DAO.seleccionar_autos();
    }
}
