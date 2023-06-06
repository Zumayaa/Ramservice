/*import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientesDAO {

    static Conexion dbConnect = null;

    public  static void insertar_datos_cliente(Cliente cliente, String nombre_tabla) throws SQLException, SQLException {
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            String columnas_de_insercion[] = RentasDAO.nombres_de_columnas(nombre_tabla);
            try{
                String query = "INSERT INTO `clientes` ("+columnas_de_insercion[0]+") VALUES ("+columnas_de_insercion[1]+");";
                ps = conexion.prepareStatement(query);
                ps.setString(1, null);
                ps.setString(2,cliente.getNombre());
                ps.setString(3, cliente.getApellido());
                ps.setString(4, cliente.getCorreo());
                ps.setString(5, cliente.getTelefono());
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public static boolean verificar_correo(String correo) throws SQLException {
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            String columnas_de_insercion[] = RentasDAO.nombres_de_columnas("clientes");
            try{
                String query = "SELECT COUNT(*) FROM rentas WHERE correo = ?";
                PreparedStatement ps = conexion.prepareStatement(query);
                ps.setString(1, correo);
                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    return true; // El correo ya está registrado
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return false;
    }
    public  static Map<Integer, String> seleccionar_clientes() throws SQLException, SQLException { // cuando puse esta madre solo chatgpt
                                                                                                   // y dios sabian lo que hacia
                                                                                                   // ahora solo chatgpt lo sabe pero perdi el prompt perdon zumaya
        Map<Integer, String> id_autos_y_nombre_autos = new HashMap<>();
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            try{
                String query = "SELECT id_de_cliente, apellido FROM clientes";
                ps = conexion.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int idCliente = rs.getInt("id_de_cliente");
                    String apellidoCliente = rs.getString("apellido");
                    System.out.println(idCliente);
                    System.out.println(apellidoCliente);
                    id_autos_y_nombre_autos.put(idCliente,apellidoCliente); // selecciona el id del cliente y su apellido correspondiente, los guarda en un hasmap y boom aparecen en el combobox
                }
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return id_autos_y_nombre_autos;
    }

    public static String[] seleccionar_columna(String columna_nombre) {
        ArrayList<String> datos_obtenidos = new ArrayList<>();

        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "SELECT " + columna_nombre + " FROM clientes";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dato = resultSet.getString(columna_nombre);
                datos_obtenidos.add(dato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] arreglo_datos = datos_obtenidos.toArray(new String[0]);

        return arreglo_datos;
    }
    public static DefaultTableModel obtener_dtm_clientes() {
        DefaultTableModel dtm = new DefaultTableModel();

        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "SELECT * FROM clientes";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Agregar columnas al modelo
            for (int i = 1; i <= columnCount; i++) {
                String nombreColumna = metaData.getColumnName(i);
                dtm.addColumn(nombreColumna);
            }

            // Agregar filas al modelo
            while (resultSet.next()) {
                Object[] fila = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    fila[i - 1] = resultSet.getObject(i);
                }
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dtm;
    }

    public static DefaultTableModel descargar_filas_tabla_dtm(String consulta) {
        DefaultTableModel dtm = new DefaultTableModel();

        try (Connection conexion = dbConnect.getConnection()) {
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String nombreColumna = metaData.getColumnName(i);
                dtm.addColumn(nombreColumna);
            }

            while (resultSet.next()) {
                Object[] fila = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    fila[i - 1] = resultSet.getObject(i);
                }
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtm;
    }
    public static Cliente cliente_informacion(int id_cliente) {
        Cliente cliente_info = new Cliente(); // Inicializar el objeto Cliente
        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "SELECT * FROM clientes WHERE id_de_cliente = ?";
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setInt(1, id_cliente);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                cliente_info.setId_cliente(id_cliente);
                cliente_info.setNombre(resultSet.getString("nombre"));
                cliente_info.setApellido(resultSet.getString("apellido"));
                cliente_info.setCorreo(resultSet.getString("correo"));
                cliente_info.setTelefono(resultSet.getString("telefono"));
                cliente_info.setTarjeta_de_credito(resultSet.getString("tarjeta_de_credito"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(cliente_info.getNombre());
        System.out.println(cliente_info.getCorreo());
        return cliente_info;
    }

}*/
