package modelo;

import java.util.ArrayList;

public abstract class Barrio {
	private String nombreBarrio;
	private double Hectareas;
	private Comuna comuna;
	private ArrayList<Persona> personas = new ArrayList<>();

	public Barrio(String nombreBarrio, double Hectareas) {
		this.nombreBarrio = nombreBarrio;
		this.Hectareas = Hectareas;
	}

	public abstract double calcularDensidad(int cantidadPersonas);

	public abstract boolean tieneCriticidadHabitacional();

	public abstract String getTipo();

	public double calcularDensidad() {
		return calcularDensidad(personas.size());
	}

	public void agregarPersona(Persona p) {
		if (!personas.contains(p)) {
			personas.add(p);
		}
	}

	public void quitarPersona(Persona p) {
		personas.remove(p);
	}

	public int getCantidadPersonas() {
		return personas.size();
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public String getNombre() {
		return nombreBarrio;
	}

	public void setNombre(String nombre) {
		this.nombreBarrio = nombre;
	}

	public double getSuperficieHectareas() {
		return Hectareas;
	}

	public void setSuperficieHectareas(double superficieHectareas) {
		this.Hectareas = superficieHectareas;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}
}
