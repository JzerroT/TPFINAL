package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import modelo.Barrio;
import modelo.ComplejoHabitacional;
import modelo.Comuna;
import modelo.TrazadoFormal;
import modelo.UrbanizacionPlanificada;

public class BarrioDAO {
	public int agregarBarrio(Barrio barrio) {
		int fila = 0;
		Connection c = null;
		String sql = "INSERT INTO barrios(nombreBarrio, hectareas, tipoBarrio, numeroComuna, cantidadPH, "
				+ "porcentajeAsfalto, numeroLey, aguaFormal, monoblocks, consorcioActivo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			c = Conexion.conectar();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, barrio.getNombre());
			stmt.setDouble(2, barrio.getSuperficieHectareas());
			cargarDatosDelTipo(stmt, barrio, 3);
			fila = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
		return fila;
	}

	public int eliminarBarrioPorParametro(Barrio barrio) {
		int fila = 0;
		Connection c = null;
		String sql = "DELETE FROM barrios WHERE nombreBarrio = ?";
		try {
			c = Conexion.conectar();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, barrio.getNombre());
			fila = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
		return fila;
	}

	public ArrayList<Barrio> consultarBarrio() {
		ArrayList<Barrio> barrios = consultarBarrioSinPersonas();
		new PersonaDAO().cargarPersonas(barrios);
		return barrios;
	}

	public ArrayList<Barrio> consultarBarrioSinPersonas() {
		Connection c = null;
		String sql = "SELECT b.*, c.sedeComunal, c.juntaComunal FROM barrios b "
				+ "JOIN comuna c ON c.numeroComuna = b.numeroComuna ORDER BY b.nombreBarrio";
		ArrayList<Barrio> solucion = new ArrayList<>();
		try {
			c = Conexion.conectar();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String nombreBarrio = rs.getString("nombreBarrio");
				double hectareas = rs.getDouble("hectareas");
				String tipoBarrio = rs.getString("tipoBarrio");
				Barrio b = null;

				if (tipoBarrio.equals("Trazado formal")) {
					b = new TrazadoFormal(nombreBarrio, hectareas, rs.getInt("cantidadPH"),
							rs.getDouble("porcentajeAsfalto"));
				} else if (tipoBarrio.equals("Urbanizacion Planificada")) {
					b = new UrbanizacionPlanificada(nombreBarrio, hectareas, rs.getInt("numeroLey"),
							rs.getBoolean("aguaFormal"));
				} else if (tipoBarrio.equals("Complejo Habitacional")) {
					b = new ComplejoHabitacional(nombreBarrio, hectareas, rs.getInt("monoblocks"),
							rs.getBoolean("consorcioActivo"));
				}

				if (b != null) {
					b.setComuna(new Comuna(rs.getInt("numeroComuna"), rs.getString("sedeComunal"),
							rs.getString("juntaComunal")));
					solucion.add(b);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
		return solucion;
	}

	public int actualizarBarrioPorParametro(Barrio barrioAntes, Barrio barrioNuevo) {
		int fila = 0;
		String sql = "UPDATE barrios SET hectareas = ?, tipoBarrio = ?, numeroComuna = ?, cantidadPH = ?, "
				+ "porcentajeAsfalto = ?, numeroLey = ?, aguaFormal = ?, monoblocks = ?, consorcioActivo = ? "
				+ "WHERE nombreBarrio = ?";
		Connection c = null;
		try {
			c = Conexion.conectar();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setDouble(1, barrioNuevo.getSuperficieHectareas());
			cargarDatosDelTipo(stmt, barrioNuevo, 2);
			stmt.setString(10, barrioAntes.getNombre());
			fila = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
		return fila;
	}

	private void cargarDatosDelTipo(PreparedStatement stmt, Barrio barrio, int desde) throws SQLException {
		stmt.setString(desde, barrio.getTipo());
		stmt.setInt(desde + 1, barrio.getComuna().getNumeroComuna());

		stmt.setNull(desde + 2, Types.INTEGER);
		stmt.setNull(desde + 3, Types.DOUBLE);
		stmt.setNull(desde + 4, Types.INTEGER);
		stmt.setNull(desde + 5, Types.BOOLEAN);
		stmt.setNull(desde + 6, Types.INTEGER);
		stmt.setNull(desde + 7, Types.BOOLEAN);

		if (barrio instanceof TrazadoFormal) {
			TrazadoFormal tf = (TrazadoFormal) barrio;
			stmt.setInt(desde + 2, tf.getCantidadEdificiosPH());
			stmt.setDouble(desde + 3, tf.getPorcentajeAsfalto());
		} else if (barrio instanceof UrbanizacionPlanificada) {
			UrbanizacionPlanificada up = (UrbanizacionPlanificada) barrio;
			stmt.setInt(desde + 4, up.getNumeroLeyIntegracion());
			stmt.setBoolean(desde + 5, up.isConexionFormalAgua());
		} else if (barrio instanceof ComplejoHabitacional) {
			ComplejoHabitacional ch = (ComplejoHabitacional) barrio;
			stmt.setInt(desde + 6, ch.getCantidadMonoblocks());
			stmt.setBoolean(desde + 7, ch.isConsorcioActivo());
		}
	}
}
