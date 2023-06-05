import java.sql.*;
import java.util.ArrayList;

public class Clientes_DAO {
    static Conexion dbConnect = null;
    static String tabla = "rentas";
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
