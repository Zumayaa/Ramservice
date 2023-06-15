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
                    "identificador_auto INT NOT NULL REFERENCES vehiculos(id_vehiculo),"+
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
                        "CREATE TABLE IF NOT EXISTS marcas (" +
                                "id_marcas INT NOT NULL AUTO_INCREMENT," +
                                "img_marcas VARCHAR(255) NOT NULL, " +
                                "nombre_marca VARCHAR(255) NOT NULL, " +
                                "descripcion VARCHAR(255) NOT NULL, " +
                                "PRIMARY KEY (id_marcas));";
                ps = conexion.prepareStatement(query);
                ps.executeUpdate();


                query =
                        "CREATE TABLE IF NOT EXISTS categorias (" +
                                "id_categorias INT NOT NULL AUTO_INCREMENT," +
                                "img_categorias VARCHAR(255) NOT NULL, " +
                                "nombre_categorias VARCHAR(255) NOT NULL, " +
                                "descripcion VARCHAR(255) NOT NULL, " +
                                "PRIMARY KEY (id_categorias));";
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

                query = "INSERT IGNORE INTO clientes " +
                        "(id_de_cliente,nombre, apellido, correo, telefono) VALUES " +
                        "('1','Pancho', 'Alejandro', 'alu.uabcs@gmail.com', '6121174106'),"+
                        "('2','Ramses', 'Accesible', 'ramses@ramses.com', '6121185436'),"+
                        "('3','Zumaya', 'Zumaya', 'bioshock_4@2023.com', '6124564106'),"+
                        "('4','Oreo', 'Stan', 'poop_tart@gmail.com', '6123344566');";

                ps = conexion.prepareStatement(query);
                ps.executeUpdate();


                query =
                        "CREATE TABLE IF NOT EXISTS vehiculos (" +
                                "id_vehiculo INT NOT NULL AUTO_INCREMENT," +
                                "img_vehiculo VARCHAR(255) NOT NULL, " +
                                "nombre_vehiculo VARCHAR(255) NOT NULL, " +
                                "categoria_vehiculo VARCHAR(255) NOT NULL, " +
                                "transmision_vehiculo VARCHAR(255) NOT NULL, " +
                                "año_vehiculo VARCHAR(255) NOT NULL, " +
                                "combustible_vehiculo VARCHAR(255) NOT NULL, " +
                                "precio_vehiculo VARCHAR(255) NOT NULL, " +
                                "marca_vehiculo VARCHAR(255) NOT NULL, " +
                                "PRIMARY KEY (id_vehiculo));";
                ps = conexion.prepareStatement(query);
                ps.executeUpdate();

                query = "INSERT IGNORE INTO vehiculos " +
                        "(id_vehiculo,img_vehiculo, nombre_vehiculo, categoria_vehiculo, transmision_vehiculo,año_vehiculo,combustible_vehiculo,precio_vehiculo,marca_vehiculo) VALUES " +
                        "('1','src/img/13.png', 'Rey X', 'Sedán de lujo', 'Estándar','2021','Gasolinaa','$201','BMW'),"+
                        "('2','src/img/image9.png', 'TQM Rey X', 'Carrito', 'Estándar','2021','Gasolinaa','$290','BMW')," +
                        "('3','src/img/image10.png', 'Tacoma', 'Camioneta', 'Estándar','2021','Gasolinaa','$256','BMW');";

                ps = conexion.prepareStatement(query);
                ps.executeUpdate();

                query = "INSERT IGNORE INTO marcas " +
                        "(id_marcas, img_marcas, nombre_marca, descripcion) VALUES " +
                        "('1','src/img/13.png', 'BMW' , 'BMW es una marca de automóviles de prestigio que se destaca por su rendimiento y estilo. Sus vehículos combinan la deportividad con el lujo y la elegancia');";

                ps = conexion.prepareStatement(query);
                ps.executeUpdate();

                query = "INSERT IGNORE INTO categorias " +
                        "(id_categorias, img_categorias, nombre_categorias, descripcion) VALUES " +
                        "('1','src/img/12.png', 'Sedán de lujo' , 'Sedanes de lujo');";

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