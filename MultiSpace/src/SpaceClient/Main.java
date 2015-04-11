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
	public Main(int width, int height) {

		add(new Board());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		this.setAlwaysOnTop(false);
		setLocationRelativeTo(null);
		setTitle("Space Ship Now on the SVN");
		this.setMinimumSize(new Dimension(500, 500));
		setFocusTraversalKeysEnabled(false);
		//this.setUndecorated(true);
		setVisible(true);
	}
/**
 * Driver of the game
 * @param args
 */
	public static void main(String[] args) {
		
	     // switch on translucency acceleration in Windows
	    System.setProperty("sun.java2d.translaccel", "true"); 
	    // System.setProperty("sun.java2d.ddforcevram", "true"); 

	     // switch on hardware acceleration if using OpenGL with pbuffers
	      System.setProperty("sun.java2d.opengl", "true"); 

		new Main(500, 500);
	}
}