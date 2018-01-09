package JPanelBlanco;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Sprite {

	private BufferedImage buffer;
	private Color color;
	private int ancho;
	private int alto;
	private int posX;
	private int posY;
	private int velocidadX;
	private int velocidadY;

	// Contructores
	public Sprite() {
	}

	public Sprite(int ancho, int alto, int posX, int posY, int velocidadX, int velocidadY, Color color) {
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.color = color;

		actualizarSprite();
	}

	public void actualizarSprite() {
		buffer = new BufferedImage(this.ancho, this.alto, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		g.setColor(this.color);
		g.fillRect(0, 0, this.ancho, this.alto);
		g.dispose();
	}

	public void moverSprite(int anchoPantalla, int altoPantalla) {
		if (posX + ancho >= anchoPantalla) {

		}
		if (posY + alto >= altoPantalla) {

		}

	}

	// Get and Set
	public BufferedImage getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferedImage buffer) {
		this.buffer = buffer;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidad) {
		this.velocidadX = velocidad;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

}
