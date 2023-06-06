/*import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class RentasDAO {

    static Conexion dbConnect = null;
    static String tabla = "rentas";
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

    public static DefaultTableModel obtener_dtm_rentas() {
        DefaultTableModel dtm = new DefaultTableModel();

        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "SELECT * FROM rentas";
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

    public static String[] seleccionar_columna(String columna_nombre) {
        ArrayList<String> datos_obtenidos = new ArrayList<>();

        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "SELECT " + columna_nombre + " FROM rentas";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

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

    public static DefaultTableModel obtener_dtm_historial_de_auto(String id_cliente) {
        DefaultTableModel dtm = new DefaultTableModel();

        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "SELECT * FROM rentas WHERE id_cliente = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setString(1, id_cliente);
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

    public static DefaultTableModel obtener_dtm_custom(String columna_filtro,int id_auto) { // este es un metodo q se podria hacer generico pa todo pero ya hice 18 clases dao q la chupen las buenas practicas :smiling_imp:
        DefaultTableModel dtm = new DefaultTableModel();

        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "SELECT * FROM rentas WHERE ? = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setString(1, String.valueOf(id_auto));
            preparedStatement.setString(2, columna_filtro);
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
    public static DefaultTableModel obtener_dtm_historial_auto(int id_auto) { // este es un metodo q se podria hacer generico pa todo pero ya hice 18 clases dao q la chupen las buenas practicas :smiling_imp:
        DefaultTableModel dtm = new DefaultTableModel();

        try (Connection conexion = dbConnect.getConnection()) {
            String consulta = "SELECT * FROM rentas WHERE identificador_auto = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setString(1, String.valueOf(id_auto));
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
    public static void editarRentaPorId(int idRenta, Rentas rentaActualizada) {
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
    public static int contarColumnasTabla(String nombreTabla) {
        int cantidadColumnas = 0;

        try (Connection conexion = dbConnect.getConnection()) {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, nombreTabla, null);

            while (resultSet.next()) {
                cantidadColumnas++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cantidadColumnas;
    }
    public static String[] nombres_de_columnas_en_arreglo(String nombreTabla){
        String nombres_columnas[] = new String[contarColumnasTabla(nombreTabla)];

        try (Connection conexion = dbConnect.getConnection()) {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, nombreTabla, null);
            int i = 0;
            while (resultSet.next()) {
                nombres_columnas[i] = resultSet.getString("COLUMN_NAME");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombres_columnas;
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
    public static Object[] descargar_columnas_tabla_arreglo(String consulta, String nombre_tabla, int numero_columna) {

        try (Connection conexion = dbConnect.getConnection()) {
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int rowCount = contarFilasTabla(nombre_tabla);
            Object[] fila = new Object[rowCount];
            int i = 0;
            while (resultSet.next()) {
                fila[i++] = resultSet.getObject(numero_columna);
            }
            return fila;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}*/
