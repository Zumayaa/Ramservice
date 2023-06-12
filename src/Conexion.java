import java.sql.*;

public class Conexion {
    static Conexion dbConnect = null;
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String NOMBRE_BASE_DE_DATOS = "base_ramservice";
    private static final String NOMBRE_USUARIO = "root";
    private static final String PASSWORD = "";

    public static void crearBaseDeDatosSiNoExiste() {
        try (Connection connection = DriverManager.getConnection(URL, NOMBRE_USUARIO, PASSWORD)) {
                try (Statement statement = connection.createStatement()) {
                    String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS  " + NOMBRE_BASE_DE_DATOS;
                    statement.executeUpdate(createDatabaseQuery);
                    System.out.println("La base de datos se ha creado correctamente.");
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  static void crearTablasSiNoExisten()throws SQLException {
        if (dbConnect == null){
            dbConnect = new Conexion();
        }
        try (Connection conexion = dbConnect.getConnection()){
            PreparedStatement ps = null;
            try{
                String query =
                    "CREATE TABLE IF NOT EXISTS rentas("+
                    "id_de_renta INT NOT NULL AUTO_INCREMENT,"+
                    "identificador_cliente INT NOT NULL REFERENCES clientes(id_de_cliente),"+
                    "cliente VARCHAR(255) NOT NULL,"+
                    "identificador_auto INT NOT NULL REFERENCES autos(id_de_auto),"+
                    "automovil VARCHAR(255) NOT NULL,"+
                    "fecha_de_renta DATE NOT NULL,"+
                    "fecha_de_devolucion DATE NOT NULL,"+
                    "tiempo INT NOT NULL,"+
                    "numero_tarjeta VARCHAR(255) NOT NULL,"+
                    "fecha_caducidad DATE NOT NULL,"+
                    "cvv VARCHAR(3) NOT NULL,"+
                    "costo DOUBLE NOT NULL,"+
                    "PRIMARY KEY (id_de_renta));";
                ps = conexion.prepareStatement(query);
                ps.executeUpdate();

                query =
                        "CREATE TABLE IF NOT EXISTS clientes (" +
                        "id_de_cliente INT NOT NULL AUTO_INCREMENT," +
                        "nombre VARCHAR(255) NOT NULL, " +
                        "apellido VARCHAR(255) NOT NULL, " +
                        "correo VARCHAR(255) NOT NULL, " +
                        "telefono VARCHAR(255) NOT NULL, " +
                        "PRIMARY KEY (id_de_cliente)," +
                        "UNIQUE KEY uk_correo (correo));";
                ps = conexion.prepareStatement(query);
                ps.executeUpdate();
                query =
                    "CREATE TABLE IF NOT EXISTS autos (" +
                    "id_de_auto INT NOT NULL AUTO_INCREMENT," +
                    "nombre_auto VARCHAR(255) NOT NULL, " +
                    "marca VARCHAR(255) NOT NULL, " +
                    "categoria VARCHAR(255) NOT NULL, " +
                    "kilometraje VARCHAR(255), " +
                    "costo FLOAT NOT NULL, " +
                    "imagen_dir VARCHAR(255), "+
                    "PRIMARY KEY (id_de_auto));";
                ps = conexion.prepareStatement(query);
                ps.executeUpdate();

                query =
                        "CREATE TABLE IF NOT EXISTS tarjetas_de_clientes (" +
                                "id_de_tarjeta INT NOT NULL AUTO_INCREMENT," +
                                "identificador_cliente VARCHAR(255) NOT NULL REFERENCES clientes(id_de_cliente), " +
                                "nombres_cliente VARCHAR(255) NOT NULL REFERENCES clientes(nombre), " +
                                "apellidos_cliente VARCHAR(255) NOT NULL REFERENCES clientes(apellido), " +
                                "numero_de_tarjeta VARCHAR(255) NOT NULL, " +
                                "fecha_de_caducidad DATE NOT NULL, " +
                                "cvv VARCHAR(3) NOT NULL, " +
                                "PRIMARY KEY (id_de_tarjeta)," +
                                "UNIQUE KEY uk_id_cliente (identificador_cliente));";;
                ps = conexion.prepareStatement(query);
                ps.executeUpdate();

                query = "INSERT IGNORE INTO autos " +
                        "(nombre_auto, marca, categoria, kilometraje, costo, imagen_dir) VALUES ('Auto1', 'Marca1', 'Categoria1', '10000', 10000.00, 'src/img/auto1'), ('Auto2', 'Marca2', 'Categoria2', '20000', 20000.00, 'src/img/auto1'), ('Auto3', 'Marca3', 'Categoria3', '30000', 30000.00, 'src/img/auto1');";
                ps = conexion.prepareStatement(query);
                ps.executeUpdate();

            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public static Connection getConnection(){
        Connection conection = null;
        try {
            conection = DriverManager.getConnection(URL+NOMBRE_BASE_DE_DATOS, NOMBRE_USUARIO, PASSWORD);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conection;
    }
}