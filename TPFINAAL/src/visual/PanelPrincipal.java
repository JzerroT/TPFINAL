package visual;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPrincipal extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;

	public PanelPrincipal(JFrame pantalla) {
		setBackground(new Color(255, 204, 204));
		setLayout(null);

		table = new JTable();
		table.setBounds(44, 59, 264, 201);
		add(table);

		JLabel lblNewLabel = new JLabel("SistemaCensos CABA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(63, 43, 258, 14);
		add(lblNewLabel);

		JButton btnComuna = new JButton("Comuna");
		btnComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelListadoComunas(pantalla));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		btnComuna.setBounds(345, 77, 89, 23);
		add(btnComuna);

		JButton btnBarrio = new JButton("Barrio");
		btnBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelListadoBarrios(pantalla));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		btnBarrio.setBounds(345, 141, 89, 23);
		add(btnBarrio);

		JButton btnPersona = new JButton("Persona");
		btnPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelAltaModificacionPersona(pantalla));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		btnPersona.setBounds(345, 208, 89, 23);
		add(btnPersona);

		textField = new JTextField();
		textField.setBounds(44, 12, 220, 20);
		add(textField);
		textField.setColumns(10);

		JButton btnNewButton_3 = new JButton("buscar\r\n");
		btnNewButton_3.setBounds(282, 11, 89, 23);
		add(btnNewButton_3);

	}

}
