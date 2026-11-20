package modelo;

import java.time.LocalDate;

import enums.SituacionLaboral;

public class Persona {
	private String dni;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private SituacionLaboral situacionLaboral;
	private Barrio barrio;

	public Persona(String dni, String nombre, String apellido, LocalDate fechaNacimiento,
			SituacionLaboral situacionLaboral, Barrio barrio) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.situacionLaboral = situacionLaboral;
		asociarBarrio(barrio);
	}

	public void asociarBarrio(Barrio nuevo) {
		if (this.barrio != null) {
			this.barrio.quitarPersona(this);
		}
		this.barrio = nuevo;
		if (nuevo != null) {
			nuevo.agregarPersona(this);
		}
	}

	public boolean coincideCon(String texto) {
		return getNombreCompleto().toLowerCase().contains(texto.toLowerCase().trim());
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombreCompleto() {
		return nombre + " " + apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public SituacionLaboral getSituacionLaboral() {
		return situacionLaboral;
	}

	public void setSituacionLaboral(SituacionLaboral situacionLaboral) {
		this.situacionLaboral = situacionLaboral;
	}

	public Barrio getBarrio() {
		return barrio;
	}
}
