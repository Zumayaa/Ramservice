import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ClientesDAO {

    static Conexion dbConnect = null;
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
    public static DefaultTableModel obtener_dtm_clientes() {
        DefaultTableModel dtm = new DefaultTableModel();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_ramservice", "root", "")) {
            String consulta = "SELECT * FROM clientes";
            Statement statement = connection.createStatement();
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

}
