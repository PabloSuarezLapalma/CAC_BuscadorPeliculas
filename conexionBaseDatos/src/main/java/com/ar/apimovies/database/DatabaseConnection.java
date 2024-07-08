package com.ar.apimovies.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";

    // En caso de ser necesario cambiar la URL de conexión, así como el usuario y contraseña
    private static final String URL = "jdbc:mysql://localhost:3306/moviescac";
	
    private static final String USER = "root";
    private static final String PASS = "root";

    static {
        try {
            Class.forName(CONTROLADOR);
            //System.out.println("Driver cargado correctamente");
        }
        catch (ClassNotFoundException error) {
            System.out.println("Error al cargar Driver JDBC");
            error.printStackTrace();
        }
    }

    public Connection conectar() {

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            //System.out.println("Conexión establecida");
        }
        catch (SQLException e) {
            System.out.println("Error al establecer la conexión");
            e.printStackTrace();
        }

        return conexion;
    }
}
