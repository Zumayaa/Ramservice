import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class Clientes_Service {
    public static void crear_cliente(String nombre, String apellido, String correo,
                              String telefono, String numero_de_tarjeta,String fecha_de_caducidad,
                              String cvv, String password) throws SQLException {
        Clientes_Class clienteNuevo = new Clientes_Class(
                nombre, apellido, correo,
                telefono,numero_de_tarjeta,
                fecha_de_caducidad, cvv, password);
        Clientes_DAO.insertar_datos(clienteNuevo);
    }

    public static void editar_cliente(String nombre, String apellido, String correo,
                                     String telefono, String numero_de_tarjeta,String fecha_de_caducidad,
                                     String cvv, String password, int id_cuenta) throws SQLException {
        Clientes_Class clienteNuevo = new Clientes_Class(
                nombre, apellido, correo,
                telefono,numero_de_tarjeta,
                fecha_de_caducidad, cvv, password);
        Clientes_DAO.editar_cliente_por_id(id_cuenta,clienteNuevo);
    }
    public static void eliminar_cliente(int id_cliente_a_eliminar){
        Clientes_DAO.borrar_cliente_por_id(id_cliente_a_eliminar);
    }

    // funciones generales
    public static DefaultTableModel crear_dtm_de_clientes(String [] nombres_columnas, String consulta){
        Object [][] datos = Clientes_DAO.seleccionar_datos(consulta);
        DefaultTableModel dtm = new DefaultTableModel(datos,nombres_columnas);
        return dtm;
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
