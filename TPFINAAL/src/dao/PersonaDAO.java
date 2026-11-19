package dao;

import java.sql.Connection;
import modelo.Persona;
import modelo.Barrio;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPersona {
	String user = "root";
	String psw = "admin";
	String path = "jdbc:mysql://localhost:3306/gestion_barrios";

	public Connection conectar() {
		Connection c = null;
		try {
			c = DriverManager.getConnection(path, user, psw);
			System.out.println("Se conectó");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return c;
	}
	
	//modificar en clases(los nombres de los datos)
	 public void cargarResidentes(Barrio barrio) {
	        Connection conexion = null;
	        try {
	            conexion = Conexion.conectar();
	            PreparedStatement ps = conexion.prepareStatement(
	                    "SELECT dni, nombre_completo, fecha_nacimiento, situacion_laboral "
	                    + "FROM persona WHERE nombre_barrio = ?");
	            ps.setString(1, barrio.getNombre());
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Persona persona = new Persona(rs.getString("dni"), rs.getString("nombre_completo"),
	                        rs.getDate("fecha_nacimiento").toLocalDate(),
	                        SituacionLaboral.valueOf(rs.getString("situacion_laboral")));
	                persona.setBarrio(barrio);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            Conexion.cerrar(conexion);
	        }
	    }
	 
	 //modificar tambien
	 public int actualizar(Persona p) {
			Connection conexion = null;
			conexion = conectar();
			//sql = "UPDATE `bd_53`.`persona` SET `id`= 69 WHERE `id` = '"+p.getId()+"';";
			sql = "UPDATE `bd_53`.`persona` SET `nombre`= 'jk' WHERE `id` = '"+p.getIdPersona()+"';";
			//sql = "UPDATE `bd_53`.`persona` SET `apellido`= 'Torres' WHERE `id` = '"+p.getId()+"';";
			try {
				Statement stmt = conexion.createStatement();
				int fila = stmt.executeUpdate(sql);
				System.out.println(fila);
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return fila;
		}
	 
	 //modificar
	 public int eliminar(Persona p) {
			sql = "DELETE FROM `persona` WHERE id = '(?)';";		
			Connection conexion = null;
			conexion = conectar();
			try {
				PreparedStatement stmt = conexion.prepareStatement(sql);
				stmt.setFloat(1, p.getIdPersona());
				//int fila = stmt.executeUpdate();
				//System.out.println(fila);
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return fila;
		}
	 
	//modificar los datos
	 
	 public int agregarPersonaPorParametro(Persona p) {
			Connection conexion = null;
			conexion = conectar();
			sql = "INSERT INTO `tpfinalpoo`.`personas`(`idpersona`,`nombre`,`nacimiento`,`hijoas`,`idestudio`,`dni`)VALUES(?,?,?,?,?,?);";
			try {
				Date sqlDate = Date.valueOf(p.getNacimiento());
				PreparedStatement stmt = conexion.prepareStatement(sql);
				stmt.setString(2, p.getNombre());
				stmt.setDate(3, sqlDate);
				stmt.setFloat(1, p.getIdPersona());
				stmt.setBoolean(4, p.getTieneHijos());
				stmt.setInt(5, p.getIdPersona());
				stmt.setInt(6, p.getDni());
				fila = stmt.executeUpdate(); //Se tiene que ejecutar sin parametro(sql) porque ya se ejecuto el insert anteriormente 
				System.out.println(fila);
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return fila;
		}
	 
	 
}
