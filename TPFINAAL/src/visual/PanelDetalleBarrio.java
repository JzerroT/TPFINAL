package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Barrio;
import modelo.ComplejoHabitacional;
import modelo.TrazadoFormal;
import modelo.UrbanizacionPlanificada;

public class PanelDetalleBarrio extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelDetalleBarrio(JFrame pantalla, Barrio barrio) {
		setBackground(new Color(128, 128, 128));
		setLayout(null);

		JLabel lblTitulo = new JLabel("Detalle " + barrio.getNombre());
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTitulo.setBounds(23, 11, 440, 30);
		add(lblTitulo);

		agregarFila("tipo:", barrio.getTipo(), 55);
		agregarFila("comuna:", String.valueOf(barrio.getComuna().getNumeroComuna()), 78);
		agregarFila("hectáreas:", String.valueOf(barrio.getSuperficieHectareas()), 101);

		if (barrio instanceof TrazadoFormal) {
			TrazadoFormal tf = (TrazadoFormal) barrio;
			agregarFila("edificios PH:", String.valueOf(tf.getCantidadEdificiosPH()), 124);
			agregarFila("% asfalto:", String.valueOf(tf.getPorcentajeAsfalto()), 147);
		} else if (barrio instanceof UrbanizacionPlanificada) {
			UrbanizacionPlanificada up = (UrbanizacionPlanificada) barrio;
			String agua = "No";
			if (up.isConexionFormalAgua()) {
				agua = "Sí";
			}
			agregarFila("número de ley:", String.valueOf(up.getNumeroLeyIntegracion()), 124);
			agregarFila("agua formal:", agua, 147);
		} else if (barrio instanceof ComplejoHabitacional) {
			ComplejoHabitacional ch = (ComplejoHabitacional) barrio;
			String consorcio = "Inactivo";
			if (ch.isConsorcioActivo()) {
				consorcio = "Activo";
			}
			agregarFila("monoblocks:", String.valueOf(ch.getCantidadMonoblocks()), 124);
			agregarFila("consorcio:", consorcio, 147);
		}

		agregarFila("personas:", String.valueOf(barrio.getCantidadPersonas()), 170);
		agregarFila("densidad:", String.format("%.4f", barrio.calcularDensidad()), 193);

		String criticidad = "Sin Criticidad";
		if (barrio.tieneCriticidadHabitacional()) {
			criticidad = "Criticidad Habitacional Activa";
		}
		agregarFila("criticidad:", criticidad, 216);

		JButton botonVolver = new JButton("volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelListadoBarrios(pantalla));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		botonVolver.setBounds(10, 266, 89, 23);
		add(botonVolver);
	}

	public void agregarFila(String dato, String valor, int y) {
		JLabel lblDato = new JLabel(dato);
		lblDato.setBounds(23, y, 110, 14);
		add(lblDato);
		JLabel lblValor = new JLabel(valor);
		lblValor.setBounds(140, y, 300, 14);
		add(lblValor);
	}
}
