import java.sql.*;
import java.util.ArrayList;

public class SQL {
    static Conexion dbConnect = null;
    static String tabla = "";
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
    public  static void insertar_datos(String datos_a_insertar[], String tabla_nombre) throws SQLException, SQLException {
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            String columnas_de_insercion[] = nombres_de_columnas(tabla_nombre);
            try{
                String query = "INSERT INTO `"+tabla_nombre+"` ("+columnas_de_insercion[0]+") VALUES ("+columnas_de_insercion[1]+");";
                ps = conexion.prepareStatement(query);
                ps.setString(1, null);
                int pos = 0;
                for (int i = 2; i<datos_a_insertar.length; i++){
                    ps.setString(i,datos_a_insertar[pos++]);
                }
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public static void editar_tabla(String datos_a_actualizar[], String query) {
        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = query;
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setString(1,null);
            for (int i = 2; i<= datos_a_actualizar.length; i++){
                ps.setString(i,datos_a_actualizar[i-2]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void eliminar_registro(String tabla_nombre, String discriminador ,int id) {
        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "DELETE FROM "+tabla_nombre+" WHERE "+discriminador+" = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, id);
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
}
