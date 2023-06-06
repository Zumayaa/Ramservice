import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Autos_DAO {
    static Conexion dbConnect = null;
    static String tabla = "autos";
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
    public static String seleccionar_celda(String consulta){
        try (Connection conexion = dbConnect.getConnection()) {
            ArrayList<String> datos_seleccionados = new ArrayList<String>();
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            if (resultSet.next()){
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "-1";
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
    public  static void insertar_datos(Autos_Class auto) throws SQLException, SQLException {
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
                ps.setString(2, auto.getNombre_auto());
                ps.setString(3, auto.getMarca());
                ps.setString(4, auto.getCategoria());
                ps.setString(5, auto.getKilometraje());
                ps.setString(6, auto.getCosto());
                ps.setString(7, auto.getImg_dir());
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public static void editar_auto_por_id(int id_auto, Autos_Class autoActualizado) {
        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "UPDATE clientes SET nombre = ?, apellido = ?, correo = ?, telefono = ?, numero_de_tarjeta = ?, fecha_de_caducidad = ?, cvv = ?, password = ? WHERE id_de_cliente = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, autoActualizado.getNombre_auto());
            preparedStatement.setString(3, autoActualizado.getMarca());
            preparedStatement.setString(4, autoActualizado.getCategoria());
            preparedStatement.setString(5, autoActualizado.getKilometraje());
            preparedStatement.setString(6, autoActualizado.getCosto());
            preparedStatement.setString(7, autoActualizado.getImg_dir());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrar_auto_por_id(int id_de_auto) {
        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "DELETE FROM "+tabla+" WHERE id_de_auto = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, id_de_auto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    // funciones muy especificas
    public  static Map<Integer, String> seleccionar_autos() throws SQLException, SQLException {
        Map<Integer, String> id_autos_y_nombre_autos = new HashMap<>();
        if (dbConnect == null){
            dbConnect = new Conexion(); // commit naco quiero ver como va este asunto
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            try{
                String query = "SELECT id_de_auto, nombre_auto FROM autos";
                ps = conexion.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int idAuto = rs.getInt("id_de_auto");
                    String nombreAuto = rs.getString("nombre_auto");
                    id_autos_y_nombre_autos.put(idAuto,nombreAuto);
                }
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return id_autos_y_nombre_autos;
    }
}
