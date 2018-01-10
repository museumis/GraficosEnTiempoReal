package JPanelBlanco;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelJuego extends JPanel implements Runnable {

	private ArrayList<Sprite> cuadrados;
	private JLabel contador;
	private int evaporados = 0;

	// Constructor
	public PanelJuego() {
		// Iniciar listado de sprites
		cuadrados = new ArrayList<>();
		// Iniciar y anadir label del contador
		contador = new JLabel();
		this.add(contador);
		// Anadir listenes
		listenedClick();

		new Thread(this).start();

	}

	/**
	 * Metodo sobreescrito para pintar el componente
	 */
	@Override
	protected void paintComponent(Graphics g) {
		rellenarFondo(g);
		pintarSprite(g);
		pintarContador();
	}

	/**
	 * Rellenar fondo
	 * 
	 * @param grafico
	 */
	public void rellenarFondo(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Pinta el contador de destruccion de sprites
	 */
	public void pintarContador() {
		contador.setBounds(this.getWidth() - 100, getHeight() - 100, 100, 100);
		contador.setText("Evaporados " + evaporados);
	}

	/**
	 * Pinta el grafico con los datos del sprite
	 * 
	 * @param g
	 */
	public void pintarSprite(Graphics g) {
		for (int i = 0; i < cuadrados.size(); i++) {
			g.drawImage(cuadrados.get(i).getBuffer(), cuadrados.get(i).getPosX(), cuadrados.get(i).getPosY(),
					cuadrados.get(i).getAncho(), cuadrados.get(i).getAlto(), null);
		}
	}

	/**
	 * Run del panel
	 */
	@Override
	public void run() {
		while (true) {
			this.repaint();
			try {
				Thread.sleep(30);
				for (int i = 0; i < cuadrados.size(); i++) {
					// Movimiento
					cuadrados.get(i).moverSprite(getWidth(), getHeight());

					// Comprobar colision
					comprobarColision(cuadrados.get(i), i);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo para comprobar colision
	 * 
	 * @param sprite
	 *            que queremos comprobar
	 * @param posicion
	 *            que ocupa en la lista
	 */
	public void comprobarColision(Sprite sprite, int posicion) {

		for (int i = 0; i < cuadrados.size(); i++) {
			for (int j = i+1; j < cuadrados.size(); j++) {
				if (cuadrados.get(i).colisionaCon(cuadrados.get(j))) {
					cuadrados.remove(j);
					evaporados++;
					cuadrados.remove(i);
					evaporados++;
				}

			}
		}

	}

	/**
	 * Obtiene un aleatorio
	 * 
	 * @param minimo
	 * @param maximo
	 * @return aleatorio entre minimo y maximo
	 */
	public int aleatorio(int minimo, int cantidad) {
		Random r = new Random();
		int aleatorio = r.nextInt(cantidad) + minimo;
		// System.out.println("Aleatorio generado " + aleatorio);
		return aleatorio;

	}

	public void listenedClick() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				cuadrados.add(new Sprite(aleatorio(15, 30), aleatorio(15, 30), e.getX(), e.getY(), aleatorio(-15, 31),
						aleatorio(-15, 31), new Color(aleatorio(0, 255), aleatorio(0, 255), aleatorio(0, 255))));
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

}
