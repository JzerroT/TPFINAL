package visual;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import dao.BarrioDAO;
import dao.ComunaDAO;
import modelo.Barrio;
import modelo.ComplejoHabitacional;
import modelo.Comuna;
import modelo.TrazadoFormal;
import modelo.UrbanizacionPlanificada;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelAltaModificacionBarrio extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame pantalla;
	private Barrio barrio;
	private ArrayList<Comuna> comunas;

	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<Integer> comboBox;
	private JComboBox<String> tipoBarrioB;

	private JTextField textFieldPH;
	private JTextField textFieldAsfalto;
	private JTextField textFieldLey;
	private JCheckBox checkAgua;
	private JTextField textFieldMonoblocks;
	private JCheckBox checkConsorcio;

	public PanelAltaModificacionBarrio(JFrame pantalla, Barrio barrio) {
		this.pantalla = pantalla;
		this.barrio = barrio;
		setBackground(new Color(128, 128, 128));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Crear Barrio");
		if (barrio != null) {
			lblNewLabel.setText("Modificar Barrio");
		}
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(23, 11, 250, 25);
		add(lblNewLabel);

		JLabel nombreB = new JLabel("nombre:");
		nombreB.setBounds(23, 55, 80, 14);
		add(nombreB);

		textField = new JTextField();
		textField.setBounds(105, 52, 120, 20);
		add(textField);
		textField.setColumns(10);

		JLabel hectariaB = new JLabel("hectáreas:");
		hectariaB.setBounds(23, 87, 80, 14);
		add(hectariaB);

		textField_1 = new JTextField();
		textField_1.setBounds(105, 84, 120, 20);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel ComunaB = new JLabel("comuna:");
		ComunaB.setBounds(23, 119, 80, 14);
		add(ComunaB);

		comboBox = new JComboBox<Integer>();
		comboBox.setBounds(105, 115, 120, 22);
		add(comboBox);

		JLabel lblNewLabel_4 = new JLabel("tipo barrio:");
		lblNewLabel_4.setBounds(23, 151, 80, 14);
		add(lblNewLabel_4);

		tipoBarrioB = new JComboBox<String>();
		tipoBarrioB.addItem("Trazado formal");
		tipoBarrioB.addItem("Urbanizacion Planificada");
		tipoBarrioB.addItem("Complejo Habitacional");
		tipoBarrioB.setBounds(105, 147, 160, 22);
		add(tipoBarrioB);
		tipoBarrioB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarCampos();
			}
		});

		JLabel lblPH = new JLabel("edificios PH:");
		lblPH.setBounds(280, 55, 100, 14);
		add(lblPH);
		textFieldPH = new JTextField();
		textFieldPH.setBounds(385, 52, 80, 20);
		add(textFieldPH);

		JLabel lblAsfalto = new JLabel("% asfalto:");
		lblAsfalto.setBounds(280, 87, 100, 14);
		add(lblAsfalto);
		textFieldAsfalto = new JTextField();
		textFieldAsfalto.setBounds(385, 84, 80, 20);
		add(textFieldAsfalto);

		JLabel lblLey = new JLabel("número de ley:");
		lblLey.setBounds(280, 119, 100, 14);
		add(lblLey);
		textFieldLey = new JTextField();
		textFieldLey.setBounds(385, 116, 80, 20);
		add(textFieldLey);

		checkAgua = new JCheckBox("conexión formal de agua");
		checkAgua.setBounds(276, 145, 200, 23);
		checkAgua.setOpaque(false);
		add(checkAgua);

		JLabel lblMonoblocks = new JLabel("monoblocks:");
		lblMonoblocks.setBounds(280, 183, 100, 14);
		add(lblMonoblocks);
		textFieldMonoblocks = new JTextField();
		textFieldMonoblocks.setBounds(385, 180, 80, 20);
		add(textFieldMonoblocks);

		checkConsorcio = new JCheckBox("consorcio activo");
		checkConsorcio.setBounds(276, 209, 200, 23);
		checkConsorcio.setOpaque(false);
		add(checkConsorcio);

		JButton botonGuardar = new JButton("guardar");
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		botonGuardar.setBounds(135, 229, 89, 23);
		add(botonGuardar);

		JButton botonVolver = new JButton("volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAlListado();
			}
		});
		botonVolver.setBounds(10, 266, 89, 23);
		add(botonVolver);

		comunas = new ComunaDAO().consultarComuna();
		for (Comuna c : comunas) {
			comboBox.addItem(c.getNumeroComuna());
		}

		if (barrio != null) {
			completarCampos();
		}
		habilitarCampos();
	}

	public void completarCampos() {
		textField.setText(barrio.getNombre());
		textField.setEditable(false);
		textField_1.setText(String.valueOf(barrio.getSuperficieHectareas()));
		comboBox.setSelectedItem(barrio.getComuna().getNumeroComuna());
		tipoBarrioB.setSelectedItem(barrio.getTipo());

		if (barrio instanceof TrazadoFormal) {
			TrazadoFormal tf = (TrazadoFormal) barrio;
			textFieldPH.setText(String.valueOf(tf.getCantidadEdificiosPH()));
			textFieldAsfalto.setText(String.valueOf(tf.getPorcentajeAsfalto()));
		} else if (barrio instanceof UrbanizacionPlanificada) {
			UrbanizacionPlanificada up = (UrbanizacionPlanificada) barrio;
			textFieldLey.setText(String.valueOf(up.getNumeroLeyIntegracion()));
			checkAgua.setSelected(up.isConexionFormalAgua());
		} else if (barrio instanceof ComplejoHabitacional) {
			ComplejoHabitacional ch = (ComplejoHabitacional) barrio;
			textFieldMonoblocks.setText(String.valueOf(ch.getCantidadMonoblocks()));
			checkConsorcio.setSelected(ch.isConsorcioActivo());
		}
	}

	public void habilitarCampos() {
		String tipo = (String) tipoBarrioB.getSelectedItem();
		boolean trazado = tipo.equals("Trazado formal");
		boolean urbanizacion = tipo.equals("Urbanizacion Planificada");
		boolean complejo = tipo.equals("Complejo Habitacional");

		textFieldPH.setEditable(trazado);
		textFieldAsfalto.setEditable(trazado);
		textFieldLey.setEditable(urbanizacion);
		checkAgua.setEnabled(urbanizacion);
		textFieldMonoblocks.setEditable(complejo);
		checkConsorcio.setEnabled(complejo);
	}

	public void guardar() {
		String nombre = textField.getText().trim();
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Escribí el nombre del barrio.");
			return;
		}
		if (comboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Elegí la comuna del barrio.");
			return;
		}

		Barrio nuevo;
		try {
			double hectareas = Double.parseDouble(textField_1.getText().trim().replace(',', '.'));
			String tipo = (String) tipoBarrioB.getSelectedItem();
			if (tipo.equals("Trazado formal")) {
				nuevo = new TrazadoFormal(nombre, hectareas, Integer.parseInt(textFieldPH.getText().trim()),
						Double.parseDouble(textFieldAsfalto.getText().trim().replace(',', '.')));
			} else if (tipo.equals("Urbanizacion Planificada")) {
				nuevo = new UrbanizacionPlanificada(nombre, hectareas, Integer.parseInt(textFieldLey.getText().trim()),
						checkAgua.isSelected());
			} else {
				nuevo = new ComplejoHabitacional(nombre, hectareas, Integer.parseInt(textFieldMonoblocks.getText().trim()),
						checkConsorcio.isSelected());
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Revisá los números: hectáreas y los datos del tipo de barrio.");
			return;
		}

		int numeroComuna = (Integer) comboBox.getSelectedItem();
		for (Comuna c : comunas) {
			if (c.getNumeroComuna() == numeroComuna) {
				nuevo.setComuna(c);
			}
		}

		BarrioDAO dao = new BarrioDAO();
		int fila;
		if (barrio == null) {
			fila = dao.agregarBarrio(nuevo);
		} else {
			fila = dao.actualizarBarrioPorParametro(barrio, nuevo);
		}

		if (fila == 0) {
			JOptionPane.showMessageDialog(this, "No se pudo guardar. Revisá que el nombre no esté repetido.");
		} else {
			JOptionPane.showMessageDialog(this, "Barrio guardado.");
			volverAlListado();
		}
	}

	public void volverAlListado() {
		pantalla.setContentPane(new PanelListadoBarrios(pantalla));
		pantalla.validate();
		pantalla.repaint();
	}
}
