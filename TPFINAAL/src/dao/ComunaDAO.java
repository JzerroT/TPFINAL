package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Barrio;
import modelo.Comuna;

public class ComunaDAO {
	public int agregarComuna(Comuna comuna) {
		int fila = 0;
		Connection c = null;
		String sql = "INSERT INTO comuna(numeroComuna, sedeComunal, juntaComunal) VALUES (?, ?, ?)";
		try {
			c = Conexion.conectar();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, comuna.getNumeroComuna());
			stmt.setString(2, comuna.getSedeComunal());
			stmt.setString(3, comuna.getJuntaComunal());
			fila = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
		return fila;
	}

	public int eliminarPorParametro(Comuna comuna) {
		int fila = 0;
		Connection c = null;
		String sql = "DELETE FROM comuna WHERE numeroComuna = ?";
		try {
			c = Conexion.conectar();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, comuna.getNumeroComuna());
			fila = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
		return fila;
	}

	public ArrayList<Comuna> consultarComuna() {
		Connection c = null;
		String sql = "SELECT * FROM comuna ORDER BY numeroComuna";
		ArrayList<Comuna> solucion = new ArrayList<>();
		try {
			c = Conexion.conectar();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int numeroComuna = rs.getInt("numeroComuna");
				String sedeComunal = rs.getString("sedeComunal");
				String juntaComunal = rs.getString("juntaComunal");
				solucion.add(new Comuna(numeroComuna, sedeComunal, juntaComunal));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}

		BarrioDAO barrioDAO = new BarrioDAO();
		for (Barrio b : barrioDAO.consultarBarrio()) {
			for (Comuna comuna : solucion) {
				if (comuna.getNumeroComuna() == b.getComuna().getNumeroComuna()) {
					comuna.agregarBarrios(b);
				}
			}
		}
		return solucion;
	}

	public int actualizarComunaPorParametro(Comuna comunaAntes, Comuna comunaNueva) {
		int fila = 0;
		String sql = "UPDATE comuna SET sedeComunal = ?, juntaComunal = ? WHERE numeroComuna = ?";
		Connection c = null;
		try {
			c = Conexion.conectar();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, comunaNueva.getSedeComunal());
			stmt.setString(2, comunaNueva.getJuntaComunal());
			stmt.setInt(3, comunaAntes.getNumeroComuna());
			fila = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrar(c);
		}
		return fila;
	}
}
