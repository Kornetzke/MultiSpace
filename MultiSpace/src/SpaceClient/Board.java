package SpaceClient;

import game.GamePlay;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.Timer;

import menu.StartMenu;
import menu.ModifyMenu;

/**
 * This is the main panel for the game. This contains the main sequence of
 * methods used for the program.
 * 
 * @author Tom Zachary and Kyle Kornetzke
 * 
 */
public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static enum GameState {
		GAME, MAINMENU, STARTMENU, MODIFYMENU, SETTINGS, ESCMENU, DISCONNECTED
	};

	public static GamePlay gamePlay;
	private StartMenu startMenu;
	public static ModifyMenu modifyMenu;

	public static GameState gameStatus;

	public static int height, width; // height and width of the board.

	private Timer gameTimer; // timer for the repaint function.

	public static ImageIcon[] imageIconArray = {

	new ImageIcon(Board.class.getResource("Fighter150W.png")),
			new ImageIcon(Board.class.getResource("LazerFix.png")),
			new ImageIcon(Board.class.getResource("Space.png")),
			new ImageIcon(Board.class.getResource("DaP.png")),
			new ImageIcon(Board.class.getResource("planet1.png")),
			new ImageIcon(Board.class.getResource("planet2.png")),
			new ImageIcon(Board.class.getResource("planet3.png")),
			new ImageIcon(Board.class.getResource("planet4.png")),
			new ImageIcon(Board.class.getResource("TitanFighter150W.png")),
			new ImageIcon(Board.class.getResource("RaveShip150W.png")),
			new ImageIcon(Board.class.getResource("PinkPsychotic120W.png"))

	};

	private final int UPDATE_DELAY = 15;

	/**
	 * Default constructor of Board. Instantiates all of the items in board.
	 */
	public Board() {
		this.setFocusTraversalKeysEnabled(false);
		gameStatus = GameState.STARTMENU;
		startMenu = new StartMenu();
		modifyMenu = new ModifyMenu();
		gamePlay = new GamePlay();
		Board.height = this.getBounds().height;
		Board.width = this.getBounds().width;
		setFocusable(true);
		setDoubleBuffered(true);

		addKeyListener(new KAdapter());
		addMouseListener(new MAdapter());
		addMouseMotionListener(new TAdapter());

		gameTimer = new Timer(UPDATE_DELAY, this);
		gameTimer.start();

	}

	private class KAdapter extends KeyAdapter {
		/**
		 * KeyPressed Method. Sends to the specific menu or to GamePlay
		 */
		public void keyPressed(KeyEvent e) {

			switch (gameStatus) {
			case GAME:
				gamePlay.keyPressed(e);
				break;
			case ESCMENU:
				break;
			case MAINMENU:
				break;
			case MODIFYMENU:
				modifyMenu.keyPressed(e);
				break;
			case SETTINGS:
				break;
			case STARTMENU:
				break;
			default:
				break;
			}

		}

		/**
		 * KeyReleased. Sends to the specific menu or to GamePlay
		 */
		public void keyReleased(KeyEvent e) {
			switch (gameStatus) {
			case GAME:
				gamePlay.keyReleased(e);
			case ESCMENU:
				break;
			case MAINMENU:
				break;
			case MODIFYMENU:
				break;
			case SETTINGS:
				break;
			case STARTMENU:
				break;
			default:
				break;
			}
		}
	}

	private class MAdapter extends MouseAdapter {
		/**
		 * MouseClicked. Sends to the specific menu.
		 */
		public void mouseClicked(MouseEvent e) {
			if (gameStatus == GameState.STARTMENU) {
				startMenu.mouseClicked(e);
			}
			if (gameStatus == GameState.MODIFYMENU) {
				modifyMenu.mouseClicked(e);
			}
		}

		/**
		 * not used. Inherited
		 */
		public void mouseEntered(MouseEvent e) {
		}

		/**
		 * not used. Inherited
		 */
		public void mouseExited(MouseEvent e) {
		}

		/**
		 * MousePressed. Sends to GamePlay if the gamestate is Game
		 */
		public void mousePressed(MouseEvent e) {
			if (gameStatus == GameState.GAME) {
				gamePlay.mousePressed(e);
			}

		}

		/**
		 * MouseReleased. Sends to GamePlay if the gamestate is Game
		 */

		public void mouseReleased(MouseEvent e) {
			if (gameStatus == GameState.GAME) {
				gamePlay.mouseReleased(e);
			}
		}

	}

	private class TAdapter extends MouseMotionAdapter {
		/**
		 * Used to track mouse movement.  Sends to the state the game is in.
		 */
		public void mouseMoved(MouseEvent e) {
			switch (gameStatus) {
			case GAME:
				gamePlay.mouseMoved(e);
				break;
			case STARTMENU:
				startMenu.mouseMoved(e);
				break;
			case MODIFYMENU:
				modifyMenu.mouseMoved(e);
			case ESCMENU:
				break;
			case MAINMENU:
				break;
			case SETTINGS:
				break;
			default:
				break;
			}
		}
/**
 * MouseDragged.  Sends to gamePlay if the game state is in game.
 */
		public void mouseDragged(MouseEvent e) {
			if (gameStatus == GameState.GAME) {
				gamePlay.mouseDragged(e);
			}

		}
	}
/**
 * Activated by the timer.  This is the main loop for the game.
 */
	public void actionPerformed(ActionEvent arg0) {

		Board.height = this.getHeight();
		Board.width = this.getWidth();
		switch (gameStatus) {
		case GAME:

			gamePlay.update();

			break;
		case STARTMENU:

			startMenu.update();
			break;
		case MODIFYMENU:
			modifyMenu.update();
		case ESCMENU:
			break;
		case MAINMENU:
			break;
		case SETTINGS:
			break;
		default:
			break;

		} // end switch
		repaint();

	}
/**
 * Paints the panel onto the frame.  Calls the draw of the appropriate menu/gameplay.
 */
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
	    RenderingHints rh = new RenderingHints(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	   g2d.setRenderingHints(rh);

		switch (gameStatus) {
		case GAME:
			gamePlay.draw(g2d);
			break;
		case MAINMENU:
			break;
		case STARTMENU:
			startMenu.draw(g2d);
			break;
		case MODIFYMENU:
			modifyMenu.draw(g2d);
		case ESCMENU:
			break;
		case SETTINGS:
			break;
		default:
			break;
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}

}
