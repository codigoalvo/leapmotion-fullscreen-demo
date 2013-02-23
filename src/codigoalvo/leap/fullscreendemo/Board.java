package codigoalvo.leap.fullscreendemo;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.Timer;
import com.leapmotion.leap.Controller;

public class Board extends Frame implements ActionListener {

	private Vector<Circulo> circVector;
	private Timer timer;
	private LeapListener leapListener;
	private Controller leapController;

	public Board() {
		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {}

			public void keyReleased(KeyEvent arg0) {}

			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		setBackground(Color.BLACK);
		this.setUndecorated(true);
		setCircVec(new Vector<Circulo>());
		timer = new Timer(100, this);
		timer.start();

		leapListener = new LeapListener(getCircVec());
		leapController = new Controller();

		leapController.addListener(leapListener);
	}

	public void paint(Graphics g) {
		super.paint(g);
		desenha2(g);
		g.dispose();
	}

	protected void desenha2(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.GREEN);

		synchronized (circVector) {
			for (Circulo circulo : getCircVec()) {
				g2d.drawOval(circulo.getX(), circulo.getY(), circulo.getRaio(), circulo.getRaio());
			}
		}
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		repaint();
	}

	private void clearInvisible() {
		synchronized (circVector) {
			if (circVector.isEmpty())
				return;

			List<Circulo> removeList = new ArrayList<>();
			for (Circulo circle : circVector) {
				if (!circle.isVisible())
					removeList.add(circle);
			}
			if (!removeList.isEmpty())
				circVector.removeAll(removeList);
		}
	}

	public Vector<Circulo> getCircVec() {
		clearInvisible();
		return circVector;
	}

	public void setCircVec(Vector<Circulo> circList) {
		this.circVector = circList;
	}

}
