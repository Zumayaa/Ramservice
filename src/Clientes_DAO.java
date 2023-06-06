import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Clientes_DAO {
    static Conexion dbConnect = null;
    static String tabla = "clientes";
    // metodos genericos, seleccionar datos
    public static Object[][] seleccionar_datos(String consulta){
        try (Connection conexion = dbConnect.getConnection()) {
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            int k = 0;
            int rowsCount = contarFilasTabla(tabla);

            Object[][] datos = new Object[rowsCount][columnCount];
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    datos[k][i - 1] = resultSet.getObject(i);
                }
                k++;
            }
            return datos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Object[0][];
    }

    public static String[] seleccionar_columna(String consulta){
        try (Connection conexion = dbConnect.getConnection()) {
            ArrayList<String> datos_seleccionados = new ArrayList<String>();
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()){
                datos_seleccionados.add(resultSet.getString(1));
            }
            String[] arreglo_columna = datos_seleccionados.toArray(new String[0]);
            for (int i = 0; i<arreglo_columna.length; i++){
                System.out.println(arreglo_columna.length);
            }
            return arreglo_columna;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }


    public static String[] seleccionar_fila(String consulta){
        try (Connection conexion = dbConnect.getConnection()) {
            ArrayList<String> datos_seleccionados = new ArrayList<String>();
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (resultSet.next()){
                for (int i = 1; i <= columnCount; i++) {
                    String valor = resultSet.getString(i);
                    datos_seleccionados.add(valor);
                }
            }
            String[] arreglo_fila = datos_seleccionados.toArray(new String[0]);
            return arreglo_fila;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public static int contarFilasTabla(String nombreTabla) {
        int cantidadFilas = 0;

        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "SELECT COUNT(*) as count FROM " + nombreTabla;
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            if (resultSet.next()) {
                cantidadFilas = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cantidadFilas;
    }
    // insertar eliminar etc
    public  static void insertar_datos(Clientes_Class cliente) throws SQLException, SQLException {
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            String columnas_de_insercion[] = nombres_de_columnas(tabla);
            try{
                String query = "INSERT INTO `"+tabla+"` ("+columnas_de_insercion[0]+") VALUES ("+columnas_de_insercion[1]+");";
                ps = conexion.prepareStatement(query);
                ps.setString(1, null);
                ps.setString(2, cliente.getNombre());
                ps.setString(3, cliente.getApellido());
                ps.setString(4, cliente.getCorreo());
                ps.setString(5, cliente.getTelefono());
                ps.setString(6, cliente.getNumero_de_tarjeta());
                ps.setString(7, cliente.getFecha_de_caducidad());
                ps.setString(8, cliente.getCvv());
                ps.setString(9, cliente.getPassword());
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public static void editar_cliente_por_id(int id_cliente, Clientes_Class clienteActualizado) {
        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "UPDATE clientes SET nombre = ?, apellido = ?, correo = ?, telefono = ?, numero_de_tarjeta = ?, fecha_de_caducidad = ?, cvv = ?, password = ? WHERE id_de_cliente = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setString(1, clienteActualizado.getNombre());
            preparedStatement.setString(2, clienteActualizado.getApellido());
            preparedStatement.setString(3, clienteActualizado.getCorreo());
            preparedStatement.setString(4, clienteActualizado.getTelefono());
            preparedStatement.setString(5, clienteActualizado.getNumero_de_tarjeta());
            preparedStatement.setString(6, clienteActualizado.getFecha_de_caducidad());
            preparedStatement.setString(7, clienteActualizado.getCvv());
            preparedStatement.setString(8, clienteActualizado.getPassword());
            preparedStatement.setInt(9, id_cliente);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrar_cliente_por_id(int id_de_cliente) {
        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "DELETE FROM "+tabla+" WHERE id_de_cliente = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, id_de_cliente);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// funciones muy especificas
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
    public static String[] nombres_de_columnas(String nombreTabla){
        String nombres_columnas[] = new String[2];

        try (Connection conexion = dbConnect.getConnection()) {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, nombreTabla, null);
            ArrayList<String> nombresColumnasAL = new ArrayList<>();
            ArrayList<String> signosAL = new ArrayList<>();
            while (resultSet.next()) {
                String nombreColumna = resultSet.getString("COLUMN_NAME");
                nombresColumnasAL.add("`"+nombreColumna+"`");
                signosAL.add("?");
            }
            String[] arregloColumnas = nombresColumnasAL.toArray(new String[0]);
            String[] arregloSignos = signosAL.toArray(new String[0]);
            nombres_columnas[0] = String.join(",", arregloColumnas);
            nombres_columnas[1] = String.join(",", arregloSignos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombres_columnas;
    }
}
