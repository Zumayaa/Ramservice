import java.sql.SQLException;

public class FuncionesSQL {
    // aqui estan conexiones a la base d datos pochas, la verdad no he probado las q alteran las tablas porq m da
    // culo q fallen pero pues quizas lo hago mañana pero si no actualizo esto signific q no lo hice asi q jijijiji
    // smiling_imp
    // las funciones pues hacen lo q dice el nombre, la de actualizar es diferente a las otras dos porque fue la unica
    // manera q se me ocurri ode medio hacerla cool, meter todo con arreglos pues no es la mejor cosas del mundo pero
    // es mejor q estar metiendo 293939 de datos en el constructror, si quieren cambiarlo pues namas seria poner cada
    // dato que se quiera poner pero esto le quita la flexibilidad a esta chingadera q es lo q se busca supong olee
    static void insertar_a_tabla(String datos_a_ingresar[], String nombre_tabla) throws SQLException {
        SQL.insertar_datos(datos_a_ingresar,nombre_tabla);
    }
    static void actualizar_tabla(String datos_a_ingresar[], String nombre_tabla ,String columnas_a_modificar, String discriminador, int id){
        SQL.editar_tabla(datos_a_ingresar, nombre_tabla, columnas_a_modificar, discriminador, id);
    }// se ocupa especificar que columna se va a modificar asi "nombre_col1 = ?, nombre_col2 = ?"
    // el discriminador es la columna por la cual se va a identificar que fila se debe de actualizar generalmente son los id
    static void actualizar_tabla_completa(String datos_a_ingresar[], String nombre_tabla, String discriminador, int id){
        SQL.editar_toda_tabla(datos_a_ingresar, nombre_tabla, discriminador, id);
    } // no se ocupa especificar que columnas se van a actualizar
    // el discriminador es la columna por la cual se va a identificar que fila se debe de actualizar generalmente son los id
    static void borrar_registro(String tabla_nombre, String discriminador ,int id){
        SQL.eliminar_registro(tabla_nombre,discriminador,id);
    }
    static String[] obtener_fila(String consulta){
        return SQL.seleccionar_fila(consulta);
    }
    static String[] obtener_columna(String consulta){
        return SQL.seleccionar_columna(consulta);
    }
    static String obtener_celda(String consulta){
        return SQL.seleccionar_celda(consulta);
    }
}
