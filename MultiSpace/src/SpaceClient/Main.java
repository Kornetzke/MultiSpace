package SpaceClient;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This is the main class for the program. It creates a JFrame with a Board
 * object in it.
 * 
 * @author Thomas and Kyle
 * 
 * 
 * 
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
/**
 * Main Constructor.  Builds the Frame
 * @param width: width of the frame
 * @param height: height of the frame
 */
	public Main(int width, int height, boolean undecorated) {

		add(new Board());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		this.setAlwaysOnTop(false);
		setLocationRelativeTo(null);
		setTitle("Space Ship Now on the Git");
		this.setMinimumSize(new Dimension(width,height));
		setFocusTraversalKeysEnabled(false);
		this.setUndecorated(undecorated);
		setVisible(true);
	}
/**
 * Driver of the game
 * @param args
 */
	public static void main(String[] args) {

		new Main(500, 500,false);
	}
}
