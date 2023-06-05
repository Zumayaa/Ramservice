import java.sql.*;
import java.util.ArrayList;

public class TablasDAO {

    static Conexion dbConnect = null;
    public  static void insertar_datos_rentas(Rentas renta, String nombre_tabla) throws SQLException, SQLException {
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            String columnas_de_insercion[] = nombres_de_columnas(nombre_tabla);
            try{
                String query = "INSERT INTO `rentas` ("+columnas_de_insercion[0]+") VALUES ("+columnas_de_insercion[1]+");";
                ps = conexion.prepareStatement(query);
                for (int i = 0; i<renta.getRentasDatos().size(); i++){
                    System.out.println(i+1 + renta.getRentasDatos().get(i));
                    ps.setString(i+1, renta.getRentasDatos().get(i));
                }
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
        }

    }
    public static String[] nombres_de_columnas(String nombreTabla){
        String nombres_columnas[] = new String[2];

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_ramservice", "root", "")) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, nombreTabla, null);
            ArrayList<String> nombresColumnasAL = new ArrayList<>();
            ArrayList<String> signosAL = new ArrayList<>();
            while (resultSet.next()) {
                String nombreColumna = resultSet.getString("COLUMN_NAME");
                if (!nombreColumna.contains("id_de_")){
                    nombresColumnasAL.add("`"+nombreColumna+"`");
                    signosAL.add("?");
                }
            }
            String[] arregloColumnas = nombresColumnasAL.toArray(new String[0]);
            String[] arregloSignos = signosAL.toArray(new String[0]);
            nombres_columnas[0] = String.join(",", arregloColumnas);
            nombres_columnas[1] = String.join(",", arregloSignos);
            System.out.println(nombres_columnas[0]);
            System.out.println(nombres_columnas[1]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombres_columnas;
    }
}
