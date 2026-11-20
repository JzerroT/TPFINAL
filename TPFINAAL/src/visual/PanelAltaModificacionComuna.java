package visual;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.ComunaDAO;
import modelo.Comuna;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAltaModificacionComuna extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame pantalla;
	private Comuna comuna;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public PanelAltaModificacionComuna(JFrame pantalla, Comuna comuna) {
		this.pantalla = pantalla;
		this.comuna = comuna;
		setBackground(new Color(128, 128, 128));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Crear Comuna");
		if (comuna != null) {
			lblNewLabel.setText("Modificar Comuna");
		}
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(25, 11, 250, 46);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(144, 68, 86, 20);
		add(textField);
		textField.setColumns(10);

		JLabel numeroComuna = new JLabel("numero comuna:");
		numeroComuna.setFont(new Font("Tahoma", Font.PLAIN, 13));
		numeroComuna.setBounds(35, 68, 105, 14);
		add(numeroComuna);

		textField_1 = new JTextField();
		textField_1.setBounds(144, 102, 200, 20);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("sede Comunal:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(34, 104, 105, 14);
		add(lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setBounds(144, 149, 200, 20);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("junta Comunal:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(35, 152, 105, 14);
		add(lblNewLabel_2);

		JButton btnNewButton = new JButton("guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnNewButton.setBounds(141, 219, 89, 23);
		add(btnNewButton);

		JButton botonVolver = new JButton("volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAlListado();
			}
		});
		botonVolver.setBounds(32, 263, 89, 23);
		add(botonVolver);

		if (comuna != null) {
			textField.setText(String.valueOf(comuna.getNumeroComuna()));
			textField.setEditable(false);
			textField_1.setText(comuna.getSedeComunal());
			textField_2.setText(comuna.getJuntaComunal());
		}
	}

	public void guardar() {
		int numero;
		try {
			numero = Integer.parseInt(textField.getText().trim());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El número de comuna tiene que ser un número.");
			return;
		}
		if (numero < 1 || numero > 15) {
			JOptionPane.showMessageDialog(this, "El número de comuna tiene que estar entre 1 y 15.");
			return;
		}
		if (textField_1.getText().trim().isEmpty() || textField_2.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Completá la sede y la junta comunal.");
			return;
		}

		Comuna nueva = new Comuna(numero, textField_1.getText().trim(), textField_2.getText().trim());
		ComunaDAO dao = new ComunaDAO();
		int fila;
		if (comuna == null) {
			fila = dao.agregarComuna(nueva);
		} else {
			fila = dao.actualizarComunaPorParametro(comuna, nueva);
		}

		if (fila == 0) {
			JOptionPane.showMessageDialog(this, "No se pudo guardar. Revisá que el número de comuna no esté repetido.");
		} else {
			JOptionPane.showMessageDialog(this, "Comuna guardada.");
			volverAlListado();
		}
	}

	public void volverAlListado() {
		pantalla.setContentPane(new PanelListadoComunas(pantalla));
		pantalla.validate();
		pantalla.repaint();
	}
}
