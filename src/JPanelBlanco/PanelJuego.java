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
			try {
				Thread.sleep(30);
				for (int i = 0; i < cuadrados.size(); i++) {
					// Limites horizontales
					if (cuadrados.get(i).getPosX() >= (this.getWidth() - cuadrados.get(i).getAncho())) {
						cuadrados.get(i).setVelocidadX(cuadrados.get(i).getVelocidadX() * -1);
					}
					if (cuadrados.get(i).getPosX() <= 0) {
						cuadrados.get(i).setVelocidadX(cuadrados.get(i).getVelocidadX() * -1);
					}

					// Limites vertical
					if (cuadrados.get(i).getPosY() <= 0) {
						cuadrados.get(i).setVelocidadY(cuadrados.get(i).getVelocidadY() * -1);
					}
					if (cuadrados.get(i).getPosY() >= (this.getHeight() - cuadrados.get(i).getAlto())) {
						cuadrados.get(i).setVelocidadY(cuadrados.get(i).getVelocidadY() * -1);
					}

					// Movimiento
					this.cuadrados.get(i).setPosX(this.cuadrados.get(i).getPosX() + cuadrados.get(i).getVelocidadX());
					this.cuadrados.get(i).setPosY(this.cuadrados.get(i).getPosY() + cuadrados.get(i).getVelocidadY());

					// Comprobar colision
					comprobarColision(cuadrados.get(i), i);

				}
				this.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo para comprobar colision
	 * @param sprite que queremos comprobar
	 * @param posicion que ocupa en la lista
	 */
	public void comprobarColision(Sprite sprite, int posicion) {

		for (int i = 0; i < cuadrados.size(); i++) {
			if (i != posicion) {
				// Paso 1 Sprite mas cercano a 0,0
				if ((cuadrados.get(i).getPosX() < sprite.getPosX())
						&& (cuadrados.get(i).getPosY() < sprite.getPosY())) {
					// Paso 2 
					if (((cuadrados.get(i).getPosX() + cuadrados.get(i).getAncho()) >= sprite.getPosX())
							&& ((cuadrados.get(i).getPosY() + cuadrados.get(i).getAncho()) >= sprite.getPosY())) {
						cuadrados.remove(Math.max(i,posicion));
						evaporados++;
						cuadrados.remove(Math.min(i,posicion));
						evaporados++;

					}
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
	public int aleatorio(int minimo, int maximo) {
		Random r = new Random();
		int aleatorio = r.nextInt(maximo) + minimo;
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
				cuadrados.add(new Sprite(aleatorio(15, 30), aleatorio(15, 30), e.getX(), e.getY(), aleatorio(-15, 15),
						aleatorio(-15, 15), new Color(aleatorio(0, 255), aleatorio(0, 255), aleatorio(0, 255))));
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
