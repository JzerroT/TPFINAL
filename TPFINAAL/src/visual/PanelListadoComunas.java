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

import dao.ComunaDAO;
import modelo.Comuna;

public class PanelListadoComunas extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame pantalla;
	private JTable tabla;
	private DefaultTableModel modelo;
	private ArrayList<Comuna> comunas = new ArrayList<>();
	private ComunaDAO dao = new ComunaDAO();

	public PanelListadoComunas(JFrame pantalla) {
		this.pantalla = pantalla;
		setBackground(new Color(255, 204, 204));
		setLayout(null);

		JLabel lblTitulo = new JLabel("Comunas");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(20, 5, 200, 30);
		add(lblTitulo);

		modelo = new DefaultTableModel(new Object[] { "Número", "Sede comunal", "Junta comunal", "Barrios" }, 0);
		tabla = new JTable(modelo);
		tabla.setDefaultEditor(Object.class, null);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(55);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 40, 330, 215);
		add(scrollPane);

		JButton botonCrear = new JButton("Crear");
		botonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelAltaModificacionComuna(pantalla, null));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		botonCrear.setBounds(365, 40, 105, 25);
		add(botonCrear);

		JButton botonModificar = new JButton("Modificar");
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comuna comuna = seleccionada();
				if (comuna != null) {
					pantalla.setContentPane(new PanelAltaModificacionComuna(pantalla, comuna));
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
				Comuna comuna = seleccionada();
				if (comuna != null) {
					pantalla.setContentPane(new PanelDetalleComuna(pantalla, comuna));
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
		comunas = dao.consultarComuna();
		for (Comuna c : comunas) {
			modelo.addRow(new Object[] { c.getNumeroComuna(), c.getSedeComunal(), c.getJuntaComunal(),
					c.cantidadDeBarrios() });
		}
	}

	public Comuna seleccionada() {
		int fila = tabla.getSelectedRow();
		if (fila == -1) {
			return null;
		}
		return comunas.get(fila);
	}

	public void eliminar() {
		Comuna c = seleccionada();
		if (c == null) {
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(this, "¿Eliminar la comuna " + c.getNumeroComuna() + "?",
				"Eliminar", JOptionPane.YES_NO_OPTION);
		if (respuesta != JOptionPane.YES_OPTION) {
			return;
		}
		if (dao.eliminarPorParametro(c) == 0) {
			JOptionPane.showMessageDialog(this, "No se pudo eliminar la comuna. Si tiene barrios, "
					+ "primero hay que borrarlos o pasarlos a otra comuna.");
		}
		llenarTabla();
	}
}
