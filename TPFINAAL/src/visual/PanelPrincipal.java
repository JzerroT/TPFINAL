package visual;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PanelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelPrincipal() {
		setBackground(new Color(255, 204, 204));
		setLayout(null);
		
		table = new JTable();
		table.setBounds(44, 59, 264, 201);
		add(table);
		
		JLabel lblNewLabel = new JLabel("SistemaCensos CABA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(63, 43, 258, 14);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(345, 77, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(345, 141, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(345, 208, 89, 23);
		add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBounds(44, 12, 220, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("buscar\r\n");
		btnNewButton_3.setBounds(282, 11, 89, 23);
		add(btnNewButton_3);

	}
	
	/*
	 * public PantallaPrincipal(JFrame pantalla) {
		setLayout(null);
		
		JButton btnPersonaVisual = new JButton("Consultar Personas");
		btnPersonaVisual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PersonaVisual(pantalla));
				pantalla.setVisible(true);
				pantalla.validate();
			}
		});
		btnPersonaVisual.setBounds(156, 85, 131, 23);
		add(btnPersonaVisual);
		
		JButton btn = new JButton("Agregar Persona");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new AgregarPersonaVisual(pantalla));
				pantalla.setVisible(true);
				pantalla.validate();
			}
		});
		btn.setBounds(149, 162, 149, 23);
		add(btn);
		pantalla.setBounds(100,100,500,500);
		
	}
	
	---------------------------------------------------------------------------------------------
	
	public void llenarTabla() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 61, 349, 164);
		add(scrollPane);
		modelo = new DefaultTableModel(columnas, 0);
		tablaPersona.setModel(modelo);
		scrollPane.setViewportView(tablaPersona);
		DAO d = new DAO();
		for(Persona p: d.consultar()) {
			p.getNacimiento().atStartOfDay();
			modelo.addRow(new Object[] {p.getNombre(), p.getNacimiento(), p.getTieneHijos(), p.getIdEstudio(), p.getDni()});
		}
		scrollPane.setVisible(true);
		scrollPane.validate();
	}
	
	
	 */
	

}
