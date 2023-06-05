import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Map;

public class ClienteService {
    public static String crear_cliente(
            String nombre, String apellido, String correo,
            String telefono, String tarjeta_de_credito
    ) throws SQLException {
            if (ClientesDAO.verificar_correo(correo)){
                Cliente cliente_nuevo = new Cliente(1, nombre, apellido, correo, telefono, tarjeta_de_credito);
                ClientesDAO.insertar_datos_cliente(cliente_nuevo, "clientes");
                return "Registro exitoso";
            }
            else{
                return "El correo ingresado ya ha sido registrado";
            }
        }
    public static String[] descargar_id_clientes(String nombre_columna) throws SQLException {
        return ClientesDAO.seleccionar_columna(nombre_columna);
    }
    public static DefaultTableModel descargar_tabla_dtm(String query){
        return ClientesDAO.descargar_filas_tabla_dtm(query);
    }
    public static String[] informacion_cliente(int id_cliente){
        Cliente clienteDatos = ClientesDAO.cliente_informacion(id_cliente);
        String info[] = new String[3];
        info[0] = clienteDatos.getNombre();
        info[1] = clienteDatos.getCorreo();
        info[2] = clienteDatos.getTelefono();
        return info;
    };
}
