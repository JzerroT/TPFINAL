package principal;

import dao.BarrioDAO;
import dao.ComunaDAO;
import dao.PersonaDAO;
import modelo.Barrio;
import modelo.Comuna;
import modelo.Persona;

public class app {
	public static void main(String[] args) {
		ComunaDAO comunaDAO = new ComunaDAO();
		BarrioDAO barrioDAO = new BarrioDAO();
		PersonaDAO personaDAO = new PersonaDAO();

		System.out.println("=== Comunas ===");
		for (Comuna c : comunaDAO.consultarComuna()) {
			String marca = "";
			if (c.esAtencionPrioritaria()) {
				marca = "  <-- ATENCION PRIORITARIA";
			}
			System.out.println("Comuna " + c.getNumeroComuna() + " - " + c.getSedeComunal() + " - barrios: " + c.cantidadDeBarrios()
					+ " (críticos: " + c.cantidadDeBarriosCriticos() + ")" + marca);
		}

		System.out.println();
		System.out.println("=== Barrios ===");
		for (Barrio b : barrioDAO.consultarBarrio()) {
			String criticidad = "Sin Criticidad";
			if (b.tieneCriticidadHabitacional()) {
				criticidad = "Criticidad Habitacional Activa";
			}
			System.out.println(b.getNombre() + " (" + b.getTipo() + ", Comuna " + b.getComuna().getNumeroComuna() + ") personas: "
					+ b.getCantidadPersonas() + " densidad: " + b.calcularDensidad() + " -> " + criticidad);
		}

		System.out.println();
		System.out.println("=== Búsqueda de personas: \"ana\" ===");
		for (Persona p : personaDAO.buscarPorNombre("ana")) {
			String barrio = "sin barrio";
			if (p.getBarrio() != null) {
				barrio = p.getBarrio().getNombre();
			}
			System.out.println(p.getDni() + " " + p.getNombreCompleto() + " - " + p.getSituacionLaboral()
					+ " - " + barrio);
		}
	}
}
