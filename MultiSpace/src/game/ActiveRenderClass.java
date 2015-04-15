package game;

import java.awt.*;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*

 * This is an example of a simple windowed render loop

 */

public class ActiveRenderClass {

	JFrame app;
	JPanel panel;

	BufferedImage bi;

	GraphicsEnvironment ge;
	GraphicsDevice gd;
	GraphicsConfiguration gc;

	Graphics graphics;
	Graphics2D g2d;

	RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

	Color background = Color.BLACK;

	int fps = 0;
	int frames = 0;
	long nanoSecondToSecondCounter = 0;
	long elapsedTime = 0;
	long currentTime = 0;
	long lastCurrentTime = 0;
	int runTimeSeconds = 0;
	long nano = 1000000000;

	Square[] drawables;

	public ActiveRenderClass() {

		drawables = new Square[2];

		for (int i = 0; i < drawables.length; i++) {
			drawables[i] = new Square(false);
		}
		drawables[0].setPrint(true);

		app = new JFrame();
		app.setIgnoreRepaint(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setTitle("Panel");
		panel = new JPanel();
		panel.setIgnoreRepaint(true);
		panel.setPreferredSize(new Dimension(650, 650));
		app.add(panel);

		app.pack();
		app.setVisible(true);

		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd = ge.getDefaultScreenDevice();
		gc = gd.getDefaultConfiguration();

		bi = gc.createCompatibleImage(650, 650);

		gameLoop();

	}

	public void gameLoop() {

		currentTime = System.nanoTime();
		lastCurrentTime = currentTime;

		while (true) {

			try {

				lastCurrentTime = currentTime;
				currentTime = System.nanoTime();
				elapsedTime = currentTime - lastCurrentTime;

				for (Square s : drawables) {
					s.update(elapsedTime);
				}

				drawBackground();

				for (Square s : drawables) {
					s.draw(g2d);
				}

				drawFrameRate();

				drawToScreen();

				Thread.yield();

			} finally {
				dispose();
				System.out.println(app.getContentPane().getSize());
				// app.setSize(650, 650);
			}

		}
	}

	public void dispose() {
		// release resources

		if (graphics != null)

			graphics.dispose();

		if (g2d != null)

			g2d.dispose();
	}

	public void drawToScreen() {
		// Blit image and flip...

		graphics = panel.getGraphics();
		graphics.drawImage(bi, 0, 0, null);

	}

	public void drawBackground() {

		g2d = bi.createGraphics();
		g2d.setRenderingHints(rh);

		g2d.setColor(background);
		g2d.fillRect(0, 0, 650, 650);
	}

	public void drawFrameRate() {
		nanoSecondToSecondCounter += currentTime - lastCurrentTime;

		if (nanoSecondToSecondCounter > nano) {
			runTimeSeconds++;
			nanoSecondToSecondCounter -= nano;

			fps = frames;

			frames = 0;

		}

		++frames;

		g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		g2d.setColor(Color.GREEN);
		g2d.drawString(String.format("FPS: %-4sFrames: %-4sSec: %s", fps,
				frames, runTimeSeconds), 20, 20);
	}

	public static void main(String[] args) {
		new ActiveRenderClass();
	}

}
