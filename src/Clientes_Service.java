import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Map;

public class Clientes_Service {
    // insertar alterar eliminar
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
        return Clientes_DAO.seleccionar_columna(consulta);
    }
    public static String obtener_celda(String consulta){
        return Rentas_DAO.seleccionar_celda(consulta);
    }
    // funciones espcicficas
    public static Map<Integer, String> seleccionar_clientes_map() throws SQLException {
        return Clientes_DAO.seleccionar_clientes();
    }
    public static String verificar_campos_de_registro(String nombre, String apellido, String correo,
                                                String telefono, String numero_de_tarjeta,String fecha_de_caducidad,
                                                String cvv, String password, String passwordConf, String editar_where){
        String mensaje = "";
        Boolean correo_existe = false;
        String correos[] = Clientes_Service.obtener_columna("SELECT correo FROM clientes "+ editar_where);
        for (int i = 0 ; i<correos.length; i++){
            if (correo.equals(correos[i])){
                correo_existe = true;
            }
        }
        if (password.equals(passwordConf) && !fecha_de_caducidad.isEmpty()
            && Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD") && !correo_existe
                && (!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty()
                && !telefono.isEmpty() && !numero_de_tarjeta.isEmpty() && !cvv.isEmpty() &&
                !password.isEmpty() && !passwordConf.isEmpty())){
                mensaje = "Permitido";
        }
        else{
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty() ||
                    numero_de_tarjeta.isEmpty() || fecha_de_caducidad.isEmpty() || cvv.isEmpty() ||
                    password.isEmpty() || passwordConf.isEmpty()){
                mensaje += "Rellene todos los campos\n";
            }
            if (!password.equals(passwordConf)){
                mensaje += "Las contraseñas no coinciden\n";
            }
            if (!Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD")){
                mensaje += "La tarjeta está caducada\n";
            }
            if (correo_existe){
                mensaje += "El correo ya se encuentra registrado\n";
            }
        }

        return mensaje;
    }
}
