package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.BarrioDAO;
import modelo.Barrio;

public class PanelListadoBarrios extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame pantalla;
	private JTable tabla;
	private DefaultTableModel modelo;
	private ArrayList<Barrio> barrios = new ArrayList<>();
	private BarrioDAO dao = new BarrioDAO();

	public PanelListadoBarrios(JFrame pantalla) {
		this.pantalla = pantalla;
		setBackground(new Color(255, 204, 204));
		setLayout(null);

		JLabel lblTitulo = new JLabel("Barrios");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(20, 5, 200, 30);
		add(lblTitulo);

		modelo = new DefaultTableModel(new Object[] { "Nombre", "Tipo", "Comuna", "Hectáreas" }, 0);
		tabla = new JTable(modelo);
		tabla.setDefaultEditor(Object.class, null);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(120);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(140);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(55);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(65);
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 40, 330, 215);
		add(scrollPane);

		JButton botonCrear = new JButton("Crear");
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelAltaModificacionBarrio(pantalla, null));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		botonCrear.setBounds(365, 40, 105, 25);
		add(botonCrear);

		JButton botonModificar = new JButton("Modificar");
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Barrio barrio = seleccionado();
				if (barrio != null) {
					pantalla.setContentPane(new PanelAltaModificacionBarrio(pantalla, barrio));
					pantalla.validate();
					pantalla.repaint();
				}
			}
		});
		botonModificar.setBounds(365, 75, 105, 25);
		add(botonModificar);

		JButton botonDetalle = new JButton("Ver detalle");
		botonDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Barrio barrio = seleccionado();
				if (barrio != null) {
					pantalla.setContentPane(new PanelDetalleBarrio(pantalla, barrio));
					pantalla.validate();
					pantalla.repaint();
				}
			}
		});
		botonDetalle.setBounds(365, 110, 105, 25);
		add(botonDetalle);

		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		botonEliminar.setBounds(365, 145, 105, 25);
		add(botonEliminar);

		JButton botonVolver = new JButton("volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelInicio(pantalla));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		botonVolver.setBounds(20, 265, 89, 23);
		add(botonVolver);

		llenarTabla();
	}

	public void llenarTabla() {
		modelo.setRowCount(0);
		barrios = dao.consultarBarrio();
		for (Barrio b : barrios) {
			modelo.addRow(new Object[] { b.getNombre(), b.getTipo(), b.getComuna().getNumeroComuna(),
					b.getSuperficieHectareas() });
		}
	}

	public Barrio seleccionado() {
		int fila = tabla.getSelectedRow();
		if (fila == -1) {
			return null;
		}
		return barrios.get(fila);
	}

	public void eliminar() {
		Barrio b = seleccionado();
		if (b == null) {
			return;
		}
		dao.eliminarBarrioPorParametro(b);
		llenarTabla();
	}
}
