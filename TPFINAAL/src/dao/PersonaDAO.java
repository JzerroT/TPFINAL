package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import enums.SituacionLaboral;
import modelo.Barrio;
import modelo.Persona;

public class PersonaDAO {
	public void agregarPersona(Persona p) {
		Connection c = null;
		String sql = "INSERT INTO persona(dni, nombre, apellido, fechaNacimiento, situacionLaboral, nombreBarrio) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			c = Conexion.conectar();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, p.getDni());
			stmt.setString(2, p.getNombre());
			stmt.setString(3, p.getApellido());
			stmt.setDate(4, Date.valueOf(p.getFechaNacimiento()));
			stmt.setString(5, p.getSituacionLaboral().name());
			if (p.getBarrio() != null) {
				stmt.setString(6, p.getBarrio().getNombre());
			} else {
				stmt.setNull(6, Types.VARCHAR);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
	}

	public void eliminarPersona(Persona p) {
		Connection c = null;
		String sql = "DELETE FROM persona WHERE dni = ?";
		try {
			c = Conexion.conectar();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, p.getDni());
			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Se borró la persona");
			} else {
				System.out.println("No se encontró la persona");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
	}

	public void actualizarPersona(Persona p) {
		Connection c = null;
		String sql = "UPDATE persona SET nombre = ?, apellido = ?, fechaNacimiento = ?, situacionLaboral = ?, "
				+ "nombreBarrio = ? WHERE dni = ?";
		try {
			c = Conexion.conectar();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getApellido());
			stmt.setDate(3, Date.valueOf(p.getFechaNacimiento()));
			stmt.setString(4, p.getSituacionLaboral().name());
			if (p.getBarrio() != null) {
				stmt.setString(5, p.getBarrio().getNombre());
			} else {
				stmt.setNull(5, Types.VARCHAR);
			}
			stmt.setString(6, p.getDni());
			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Se modificó la persona");
			} else {
				System.out.println("No se encontró la persona");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
	}

	public ArrayList<Persona> consultarPersonas() {
		ArrayList<Barrio> barrios = new BarrioDAO().consultarBarrioSinPersonas();
		return cargarPersonas(barrios);
	}

	public ArrayList<Persona> buscarPorNombre(String texto) {
		ArrayList<Persona> resultado = new ArrayList<>();
		for (Persona p : consultarPersonas()) {
			if (p.coincideCon(texto)) {
				resultado.add(p);
			}
		}
		return resultado;
	}

	public ArrayList<Persona> cargarPersonas(ArrayList<Barrio> barrios) {
		Connection c = null;
		String sql = "SELECT * FROM persona ORDER BY apellido, nombre";
		ArrayList<Persona> solucion = new ArrayList<>();
		try {
			c = Conexion.conectar();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String nombreBarrio = rs.getString("nombreBarrio");
				Barrio barrio = null;
				for (Barrio b : barrios) {
					if (b.getNombre().equalsIgnoreCase(nombreBarrio)) {
						barrio = b;
					}
				}
				Persona p = new Persona(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getDate("fechaNacimiento").toLocalDate(),
						SituacionLaboral.valueOf(rs.getString("situacionLaboral")), barrio);
				solucion.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
		return solucion;
	}
}
