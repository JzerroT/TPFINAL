package principal;

import javax.swing.JFrame;

import visual.PanelInicio;

public class PantallaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;

	public PantallaPrincipal() {
		setTitle("Sistema Censo CABA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		setContentPane(new PanelInicio(this));
	}

	public static void main(String[] args) {
		PantallaPrincipal ventana = new PantallaPrincipal();
		ventana.setVisible(true);
	}
}
