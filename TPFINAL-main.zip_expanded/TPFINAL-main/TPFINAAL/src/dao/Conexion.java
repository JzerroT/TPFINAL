package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	static String user = "root";
	static String psw = "admin";
	static String path = "jdbc:mysql://localhost:3306/gestion_barrios";

	public static Connection conectar() throws SQLException {
		return DriverManager.getConnection(path, user, psw);
	}

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
