package codigoalvo.leap.fullscreendemo;

import java.util.*;

public class Circulo {

	@Override
	public String toString() {
		return super.toString() + " * x:" + x + " y:" + y + " r:" + raio + " v:" + (isVisible ? 1 : 0);
	}

	private Timer timer;
	private int x;
	private int y;
	private int raio = 10;
	private boolean isVisible = true;

	public Circulo() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (raio < 40) {
					raio += 5;
				} else {
					isVisible = false;
					timer.cancel();
				}
			}
		}, 100, 100);
	}

	public Circulo(int x, int y) {
		this();
		setX(x);
		setY(y);
	}

	public Circulo(int x, int y, int raio) {
		this(x, y);
		setRaio(raio);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRaio() {
		return raio;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRaio(int raio) {
		this.raio = raio;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
}
