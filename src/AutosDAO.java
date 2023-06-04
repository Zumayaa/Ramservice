import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AutosDAO {
    static Conexion dbConnect = null;
/**/

    public  static Map<Integer, String> seleccionar_autos() throws SQLException, SQLException {
        Map<Integer, String> id_autos_y_nombre_autos = new HashMap<>();
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            try{
                String query = "SELECT id_auto, nombre_auto FROM autos";
                ps = conexion.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int idAuto = rs.getInt("id_auto");
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
    public static String[] seleccionar_columna(String columna_nombre) {
        ArrayList<String> datos_obtenidos = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_ramservice", "root", "")) {
            String consulta = "SELECT " + columna_nombre + " FROM autos";
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dato = resultSet.getString(columna_nombre);
                datos_obtenidos.add(dato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convertir la lista a un arreglo de Strings
        String[] arreglo_datos = datos_obtenidos.toArray(new String[0]);

        return arreglo_datos;
    }


    public  static double getCosto(int id_auto) throws SQLException, SQLException {
        double costo = 0;
        Map<Integer, String> id_autos_y_nombre_autos = new HashMap<>();
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            try{
                String query = "SELECT costo FROM autos WHERE id_auto = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, String.valueOf(id_auto));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String costo_auto = rs.getString("costo");
                    costo = Double.parseDouble(costo_auto);
                    System.out.println("El costo es: " + costo);
                } else {
                    System.out.println("No se encontró el auto con el ID especificado");
                }

                ps.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return costo;
    }
    public static DefaultTableModel obtener_dtm_autos(String consulta) { // este es un metodo q se podria hacer generico pa todo pero ya hice 18 clases dao q la chupen las buenas practicas :smiling_imp:
        DefaultTableModel dtm = new DefaultTableModel();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_ramservice", "root", "")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

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
}

