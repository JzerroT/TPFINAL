package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Comuna;
import modelo.Persona;

public class conexion {

	String user = "root";
	String psw = "admin";
	String path = "jdbc:mysql://localhost:3306/gestion_barrios";


    /** Abre una conexión nueva. Hay que cerrarla con cerrar() cuando se termina de usar. */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(path, user, psw);
    }

    /** Cierra la conexión (si se llegó a abrir). Se llama en el finally. */
    public static void cerrar(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
