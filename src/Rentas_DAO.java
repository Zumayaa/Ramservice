import java.sql.*;
import java.util.ArrayList;

public class Rentas_DAO {
    static Conexion dbConnect = null;
    static String tabla = "rentas";
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
    public  static void insertar_datos(Rentas_Class renta) throws SQLException, SQLException {
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
                ps.setString(2,String.valueOf(renta.getIdentificador_cliente()));
                ps.setString(3, renta.getCliente());
                ps.setString(4,String.valueOf(renta.getIdentificador_auto()));
                ps.setString(5,renta.getAutomovil());
                ps.setString(6, renta.getFecha_de_renta());
                ps.setString(7, renta.getFecha_de_devolucion());
                ps.setString(8, String.valueOf(renta.getTiempo()));
                ps.setString(9,renta.getNumero_tarjeta());
                ps.setString(10,renta.getFecha_de_caducidad());
                ps.setString(11,renta.getCvv());
                ps.setString(12, String.valueOf(renta.getCosto()*renta.getTiempo()));
                ps.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public static void editarRentaPorId(int idRenta, Rentas_Class rentaActualizada) {
        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "UPDATE rentas SET identificador_cliente = ?, cliente = ?, identificador_auto = ?, automovil = ?, fecha_de_renta = ?, fecha_de_devolucion = ?, tiempo = ?, numero_tarjeta = ?, fecha_caducidad = ?, cvv = ?, costo = ? WHERE id_de_renta = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, rentaActualizada.getIdentificador_cliente());
            preparedStatement.setString(2, rentaActualizada.getCliente());
            preparedStatement.setInt(3, rentaActualizada.getIdentificador_auto());
            preparedStatement.setString(4, rentaActualizada.getAutomovil());
            preparedStatement.setDate(5, Date.valueOf(rentaActualizada.getFecha_de_renta()));
            preparedStatement.setDate(6, Date.valueOf(rentaActualizada.getFecha_de_devolucion()));
            preparedStatement.setInt(7, rentaActualizada.getTiempo());
            preparedStatement.setString(8, rentaActualizada.getNumero_tarjeta());
            preparedStatement.setDate(9, Date.valueOf(rentaActualizada.getFecha_de_caducidad()));
            preparedStatement.setString(10, rentaActualizada.getCvv());
            preparedStatement.setDouble(11, rentaActualizada.getCosto());
            preparedStatement.setInt(12, idRenta);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void borrar_renta_por_id(int idRenta) {
        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "DELETE FROM rentas WHERE id_de_renta = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setInt(1, idRenta);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean conflicto_entre_fechas(int id_auto, String fecha_renta_a_comparar, String fecha_devolucion_a_comparar) {
        if (dbConnect == null) {
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()) {
            String query = "SELECT fecha_de_renta, fecha_de_devolucion FROM rentas WHERE identificador_auto = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, String.valueOf(id_auto));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fecha_renta = rs.getString("fecha_de_renta");
                String fecha_devolucion = rs.getString("fecha_de_devolucion");
                boolean interferencia_entre_fechas = Fechas.compararFechas(fecha_renta, fecha_devolucion, fecha_renta_a_comparar, fecha_devolucion_a_comparar);
                if (interferencia_entre_fechas) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean conflicto_entre_fechas(int id_renta, int id_auto, String fecha_renta_a_comparar, String fecha_devolucion_a_comparar) {
        if (dbConnect == null) {
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()) {
            String query = "SELECT fecha_de_renta, fecha_de_devolucion FROM rentas WHERE identificador_auto = ? AND id_de_renta <> ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, String.valueOf(id_auto));
            ps.setString(2, String.valueOf(id_renta));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fecha_renta = rs.getString("fecha_de_renta");
                String fecha_devolucion = rs.getString("fecha_de_devolucion");
                boolean interferencia_entre_fechas = Fechas.compararFechas(fecha_renta, fecha_devolucion, fecha_renta_a_comparar, fecha_devolucion_a_comparar);
                if (interferencia_entre_fechas) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
