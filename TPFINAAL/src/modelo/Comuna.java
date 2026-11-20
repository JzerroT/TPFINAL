package modelo;

import java.util.ArrayList;

public class Comuna {
	private int numeroComuna;
	private String sedeComunal;
	private String juntaComunal;

	ArrayList<Barrio> barrios = new ArrayList<>();

	public Comuna(int numeroComuna, String sedeComunal, String juntaComunal) {
		this.numeroComuna = numeroComuna;
		this.sedeComunal = sedeComunal;
		this.juntaComunal = juntaComunal;
	}

	public void agregarBarrios(Barrio b) {
		barrios.add(b);
		b.setComuna(this);
	}

	public int cantidadDeBarrios() {
		return barrios.size();
	}

	public int cantidadDeBarriosCriticos() {
		int barriosCriticos = 0;
		for (Barrio b : barrios) {
			if (b.tieneCriticidadHabitacional()) {
				barriosCriticos++;
			}
		}
		return barriosCriticos;
	}

	public boolean esAtencionPrioritaria() {
		if (barrios.size() == 0) {
			return false;
		}
		double porcentaje = cantidadDeBarriosCriticos() * 100.0 / barrios.size();
		if (porcentaje >= 50) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Barrio> getBarrios() {
		return barrios;
	}

	public int getNumeroComuna() {
		return numeroComuna;
	}

	public void setNumeroComuna(int numeroComuna) {
		this.numeroComuna = numeroComuna;
	}

	public String getSedeComunal() {
		return sedeComunal;
	}

	public void setSedeComunal(String sedeComunal) {
		this.sedeComunal = sedeComunal;
	}

	public String getJuntaComunal() {
		return juntaComunal;
	}

	public void setJuntaComunal(String juntaComunal) {
		this.juntaComunal = juntaComunal;
	}
}
