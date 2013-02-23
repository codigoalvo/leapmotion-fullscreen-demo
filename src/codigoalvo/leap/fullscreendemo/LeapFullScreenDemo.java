package codigoalvo.leap.fullscreendemo;

import java.awt.*;
import javax.swing.JFrame;

public class LeapFullScreenDemo extends JFrame {

	public static final int F_WIDTH = 1360;
	public static final int F_HEIGHT = 768;

	private static int counter = 0;

	private static final int MAX = 50;

	private static DisplayMode MODES[] = new DisplayMode[] {new DisplayMode(1360, 768, 32, 0), new DisplayMode(1280, 720, 16, 0), new DisplayMode(800, 600, 32, 0)};

	private static DisplayMode getBestDisplayMode(GraphicsDevice device) {
		for (int x = 0, xn = MODES.length; x < xn; x++) {
			DisplayMode[] modes = device.getDisplayModes();
			for (int i = 0, in = modes.length; i < in; i++) {
				if (modes[i].getWidth() == MODES[x].getWidth() && modes[i].getHeight() == MODES[x].getHeight() && modes[i].getBitDepth() == MODES[x].getBitDepth()) {
					LeapListener.SCREEN_MAX_X = MODES[x].getWidth();
					LeapListener.SCREEN_MAX_Y = MODES[x].getHeight();
					return MODES[x];
				}
			}
		}
		return null;
	}

	public LeapFullScreenDemo() {

		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
//		DisplayMode originalDisplayMode = graphicsDevice.getDisplayMode();
		Board mainScreen = new Board();
		graphicsDevice.setFullScreenWindow(mainScreen);
		if (graphicsDevice.isDisplayChangeSupported()) {
			graphicsDevice.setDisplayMode(getBestDisplayMode(graphicsDevice));
		}
		mainScreen.createBufferStrategy(2); // 2 buffers
	}

	public static void main(String[] args) {
		new LeapFullScreenDemo();
	}

}
