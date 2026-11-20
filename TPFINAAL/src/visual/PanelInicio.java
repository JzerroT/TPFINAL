package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInicio extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelInicio(JFrame pantalla) {
		Color rosa = new Color(255, 204, 204);
		setBackground(rosa);
		setLayout(new BorderLayout(0, 30));
		setBorder(BorderFactory.createEmptyBorder(90, 15, 15, 15));

		JLabel titulo = new JLabel("CensoCABA");
		titulo.setFont(new Font("Dialog", Font.BOLD, 18));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo, BorderLayout.NORTH);

		JButton comunas = new JButton("Comunas");
		comunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelListadoComunas(pantalla));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		JButton barrios = new JButton("Barrios");
		barrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setContentPane(new PanelListadoBarrios(pantalla));
				pantalla.validate();
				pantalla.repaint();
			}
		});
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
		botones.setBackground(rosa);
		botones.add(comunas);
		botones.add(barrios);
		add(botones, BorderLayout.CENTER);
	}
}
