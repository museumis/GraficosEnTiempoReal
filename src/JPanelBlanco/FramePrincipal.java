package JPanelBlanco;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FramePrincipal {

	private JFrame frame;
	private PanelJuego juego;

	public FramePrincipal() {
		frame = new JFrame("");
		frame.setBounds(200, 200, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void iniciar() {
		iniciarComponentes();
		iniciarListened();
		frame.setVisible(true);
	}
	public void iniciarComponentes() {
		frame.setLayout(new GridLayout());
		
		juego = new PanelJuego();
		
		frame.add(juego);
		
	}
	public void iniciarListened() {}

}
