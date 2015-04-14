package game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*

 * This is an example of a simple windowed render loop

 */

public class ActiveRenderClass {

	JFrame app;
	Frame app2;
	JPanel panel;
	Canvas canvas;

	boolean useCanvas = true;

	BufferStrategy buffer;
	BufferedImage bi;

	GraphicsEnvironment ge;
	GraphicsDevice gd;
	GraphicsConfiguration gc;

	Graphics graphics;
	Graphics2D g2d;

	RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

	Color background = Color.BLACK;

	public ActiveRenderClass() {
			

		if (useCanvas) {
			app2 = new Frame();
			app2.setIgnoreRepaint(true);
			canvas = new Canvas();
			canvas.setIgnoreRepaint(true);
			canvas.setSize(650, 650);
			app2.add(canvas);
			app2.setTitle("Canvas");
			app2.pack();
			app2.setVisible(true);
			
			app2.addWindowListener(new WindowAdapter() {
		        public void windowClosing(WindowEvent we) {
		        	System.out.println("WindowEvent: "+we);
		            app2.dispose();
		            System.exit(0);
		         }
		     }
		);
			
			canvas.createBufferStrategy(2);
			buffer = canvas.getBufferStrategy();
		} else {
			
			app = new JFrame();
			app.setIgnoreRepaint(true);
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.setTitle("Panel");
			app.setSize(650,650);
			app.setMinimumSize(new Dimension(650,650));
			panel = new JPanel();
			panel.setIgnoreRepaint(true);
			panel.setSize(650, 650);
			app.add(panel);
			
			app.pack();
			app.setVisible(true);
			
		}

		

		


		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd = ge.getDefaultScreenDevice();
		gc = gd.getDefaultConfiguration();

		bi = gc.createCompatibleImage(650, 650);

		drawFrames();

	}

	public void drawFrames() {

		int fps = 0;

		int frames = 0;

		long totalTime = 0;
		long elapsedTime = 0;

		long curTime = System.nanoTime();

		long lastTime = curTime;

		int speed = 40;
		int seconds = 0;
		long nano = 1000000000;

		double displacement = 0;
		Point2D position = new Point2D.Double(0.0, 0.0);

		while (true) {

			try {

				// count Frames per second...

				lastTime = curTime;

				curTime = System.nanoTime();
				elapsedTime = curTime - lastTime;

				totalTime += curTime - lastTime;

				if (totalTime > nano) {
					seconds++;
					totalTime -= nano;

					fps = frames;

					frames = 0;

				}

				++frames;

				// clear back buffer...

				g2d = bi.createGraphics();

				g2d.setRenderingHints(rh);

				g2d.setColor(background);

				g2d.fillRect(0, 0, 650, 650);

				// draw some rectangles...

				g2d.setColor(Color.WHITE);

				displacement = speed * (elapsedTime / (nano + 0.0));

				System.out.println("ElaspsedTime :" + elapsedTime);
				System.out.println("Displacement :" + displacement);

				if (seconds >= 2) {

					position.setLocation(position.getX() + displacement,
							position.getY() + displacement);

				}

				System.out.println("Position:" + position.getX() + ":"
						+ position.getY());

				g2d.fillRect((int) position.getX(), (int) position.getY(), 150,
						150);

				// display frames per second...

				g2d.setFont(new Font("Courier New", Font.PLAIN, 12));

				g2d.setColor(Color.GREEN);

				g2d.drawString(String.format("FPS: %s	Frames: %s	Sec: %s", fps,
						frames, seconds), 20, 20);

				// Blit image and flip...

				if(useCanvas){
				graphics = buffer.getDrawGraphics();
				
				}else{
				
				graphics = panel.getGraphics();
				}
				graphics.drawImage(bi, 0, 0, null);

				
				if (useCanvas && !buffer.contentsLost()) {
					buffer.show();
				}else{
					
				}

				// Let the OS have a little time...

				Thread.yield();

			} finally {

				// release resources

				if (graphics != null)

					graphics.dispose();

				if (g2d != null)

					g2d.dispose();

			}

		}
	}

	public static void main(String[] args) {
		new ActiveRenderClass();
	}

}
