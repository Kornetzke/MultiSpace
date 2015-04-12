package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import SpaceClient.Board;
import SpaceClient.Board.GameState;

/**
 * This class is used to maintain the game when in the start menu state.
 * 
 * @author Tom Zachary
 * 
 */
public class StartMenu {
	private GameButton joinMatch;
	private GameButton exit;
	private Point joinPoint;

	private Point exitPoint_;

	private Color outterColor_;
	private Color innerColor_;
	private final String TITLE_ONE = "EPICALLY LONG,";
	private final String TITLE_TWO = "OVERLY DRAMATIC,";
	private final String TITLE_THREE = "VERY SPACEY,";
	private final String TITLE_FOUR = "GAME TITLE";
/**
 * Default Constructor for StartMenu. does normal instantiating items
 */
	public StartMenu() {
		outterColor_ = Color.red;
		innerColor_ = Color.white;
		int startY = Board.height / 2 - Board.height / 20;
		int startX = Board.width / 2 - Board.width / 8;
		int differenceInY = Board.width / 8 + 4;
		joinPoint = new Point(startX, startY);

		exitPoint_ = new Point(startX, startY + (3 * differenceInY));

		joinMatch = new GameButton(joinPoint, outterColor_, innerColor_,
				"Join Match");

		exit = new GameButton(exitPoint_, outterColor_, innerColor_,
				"Exit Game");
	}
/**
 * updates the points of the buttons
 */
	public void update() {
		int startY = Board.height / 2 - Board.height / 20;
		int startX = Board.width / 2 - Board.width / 8;
		int differenceInY = Board.height / 10 + 2;
		joinMatch.update(new Point(startX, startY + (2 * differenceInY)));
		joinPoint = new Point(startX, startY + (2 * differenceInY));
		exit.update(new Point(startX, startY + (3 * differenceInY)));
		exitPoint_ = new Point(startX, startY + (3 * differenceInY)); 

	}
/**
 * Draws the StartMenu
 * @param g2d Graphics of the system
 */
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Board.width, Board.height);

		// Draw Title
		g2d.setColor(Color.white);
		g2d.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, Board.height / 20));
		g2d.drawString(TITLE_ONE, Board.width / 2
				- (int) (g2d.getFont().getSize() * 3.5), Board.height / 10);
		g2d.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, Board.height / 30));
		g2d.drawString(TITLE_TWO, Board.width / 2
				- (int) (g2d.getFont().getSize() * 4.5), Board.height / 10
				+ (g2d.getFont().getSize() * 1));
		g2d.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, Board.height / 40));
		g2d.drawString(TITLE_THREE, Board.width / 2
				- (g2d.getFont().getSize() * 3), Board.height / 10
				+ (g2d.getFont().getSize() * 3));
		g2d.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, Board.height / 50));
		g2d.drawString(TITLE_FOUR, Board.width / 2
				- (g2d.getFont().getSize() * 2), Board.height / 10
				+ (g2d.getFont().getSize() * 5));

		joinMatch.draw(g2d);

		exit.draw(g2d);
	}
/**
 * Mouse clicked calls this method.  It does the appropriate commands for each button.
 * @param e MouseEvent
 */
	public void mouseClicked(MouseEvent e) {

		Point click = e.getPoint();
		if (joinMatch.contain(click)) {
			Board.gameStatus = GameState.MODIFYMENU;

		}

		if (exit.contain(click)) {
			System.exit(0);
		}
	}
/**
 * When the mouse is moved, this gets the location of the mouse and changes the color of the buttons accordingly
 * @param e MouseEvent
 */
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();

		if (joinMatch.contain(p)) {
			joinMatch.changeInsideColor(Color.black);
		} else {
			joinMatch.changeInsideColor(Color.red);
		}
		if (exit.contain(p)) {
			exit.changeInsideColor(Color.black);
		} else {
			exit.changeInsideColor(Color.red);
		}

	}
	/**
	 * Gets the locations of JoinPoint. For testing purposes only
	 * @return location of join button
	 */
	public Point getJoinPoint(){
		return joinPoint;
	}
	/**
	 * Gets the location of exit button.  For testing purposes only
	 * @return location of exit button
	 */
	public Point getExitPoint(){
		return exitPoint_;
	}

}
