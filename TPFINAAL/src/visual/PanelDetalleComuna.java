package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import modelo.Barrio;
import modelo.Comuna;

public class PanelDetalleComuna extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelDetalleComuna(JFrame pantalla, Comuna comuna) {
		setBackground(new Color(128, 128, 128));
		setLayout(null);

		JLabel lblTitulo = new JLabel("Detalle Comuna " + comuna.getNumeroComuna());
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTitulo.setBounds(23, 11, 300, 30);
		add(lblTitulo);

		JLabel lblSede = new JLabel("sede comunal:");
		lblSede.setBounds(23, 55, 110, 14);
		add(lblSede);
		JLabel valorSede = new JLabel(comuna.getSedeComunal());
		valorSede.setBounds(140, 55, 200, 14);
		add(valorSede);

		JLabel lblJunta = new JLabel("junta comunal:");
		lblJunta.setBounds(23, 80, 110, 14);
		add(lblJunta);
		JLabel valorJunta = new JLabel(comuna.getJuntaComunal());
		valorJunta.setBounds(140, 80, 200, 14);
		add(valorJunta);

		JLabel lblBarrios = new JLabel("barrios:");
		lblBarrios.setBounds(23, 105, 110, 14);
		add(lblBarrios);
		DefaultListModel<String> nombres = new DefaultListModel<String>();
		for (Barrio b : comuna.getBarrios()) {
			if (b.tieneCriticidadHabitacional()) {
				nombres.addElement(b.getNombre() + "  (crítico)");
			} else {
				nombres.addElement(b.getNombre());
			}
		}
		JList<String> listaBarrios = new JList<String>(nombres);
		JScrollPane scrollPane = new JScrollPane(listaBarrios);
		scrollPane.setBounds(140, 105, 220, 90);
		add(scrollPane);

		JLabel lblCriticos = new JLabel("barrios con criticidad:");
		lblCriticos.setBounds(23, 205, 130, 14);
		add(lblCriticos);
		JLabel valorCriticos = new JLabel(comuna.cantidadDeBarriosCriticos() + " de " + comuna.cantidadDeBarrios());
		valorCriticos.setBounds(160, 205, 100, 14);
		add(valorCriticos);

		JLabel lblPrioritaria = new JLabel("atención prioritaria:");
		lblPrioritaria.setBounds(23, 228, 130, 14);
		add(lblPrioritaria);
		String prioritaria = "No";
		if (comuna.esAtencionPrioritaria()) {
			prioritaria = "Sí";
		}
		JLabel valorPrioritaria = new JLabel(prioritaria);
		valorPrioritaria.setBounds(160, 228, 100, 14);
		add(valorPrioritaria);

		JButton botonVolver = new JButton("volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelListadoComunas(pantalla));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		botonVolver.setBounds(10, 266, 89, 23);
		add(botonVolver);
	}
}
