package menu;

import game.GamePlay;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



import SpaceClient.Board;
import SpaceClient.Board.GameState;

/**
 * This class is used when the client is in the modify menu portion of the game.
 * 
 * @author Tom Zachary
 * 
 */
public class ModifyMenu {
	private GameButton shipOne;
	private GameButton shipTwo;
	private GameButton shipThree;
	private GameButton shipFour;
	private GameButton back;
	private GameButton joinGame;
	private GameTextField name;
	private GameTextField server;
	private Point onePoint;
	private Point twoPoint;
	private Point threePoint;
	private Point fourPoint;
	private Point backPoint;
	private Point joinPoint;
	private Color innerColor;
	private Color outterColor;
	private Point namePoint;
	private Point serverPoint;
	private Point clickPoint = new Point(0, 0);

	private enum TextState {
		NAME, SERVER, NONE
	};

	private enum ShipState {
		ONE, TWO, THREE, FOUR
	};

	private ShipState shipSelected_ = ShipState.ONE;
	private TextState textSelected = TextState.NONE;
	boolean rejectedName_ = false;
/**
 * Default constructor of ModifyMenu
 */
	public ModifyMenu() {
		innerColor = Color.BLUE;
		outterColor = Color.white;
		int differenceInY = Board.height / 8 + 4;
		int differenceInX = Board.width / 8 + 4;
		int startY = Board.height / 2 - Board.height / 20 - differenceInY;
		int startX = Board.width / 2 - Board.width / 8 - differenceInX;

		onePoint = new Point(startX, startY);
		twoPoint = new Point(startX, startY + (1 * differenceInY));
		threePoint = new Point(startX, startY + (2 * differenceInY));
		fourPoint = new Point(startX, startY + (3 * differenceInY));
		backPoint = new Point(startX, startY + (4 * differenceInY));
		joinPoint = new Point(startX, startY + (5 * differenceInY));
		namePoint = new Point(startX, startY - differenceInY);
		serverPoint = new Point(startX, startY - (2 * differenceInY));

		name = new GameTextField(namePoint, outterColor, "Name");
		server = new GameTextField(serverPoint, outterColor, "Server IP");
		shipOne = new GameButton(onePoint, innerColor, outterColor,
				"Ship One");
		shipTwo = new GameButton(twoPoint, innerColor, outterColor,
				"Ship Two");
		shipThree = new GameButton(threePoint, innerColor, outterColor,
				"Ship Three");
		shipFour = new GameButton(fourPoint, innerColor, outterColor,
				"Ship Four");
		back = new GameButton(backPoint, innerColor, outterColor, "Back");
		joinGame = new GameButton(joinPoint, Color.red, outterColor,
				"Join Game");
	}
/**
 * Draws the menu
 * @param g2d graphics of the system
 */
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Board.width, Board.height);
		g2d.setColor(Color.red);
		if (rejectedName_ == true) {
			g2d.drawString("Name Rejected.  Please choose another name.",
					Board.width / 3, Board.height / 3);
		}

		shipOne.draw(g2d);
		shipTwo.draw(g2d);
		shipThree.draw(g2d);
		shipFour.draw(g2d);
		back.draw(g2d);
		joinGame.draw(g2d);
		name.draw(g2d);
		server.draw(g2d);
	}
/**
 * Updates the menu.  Updates the location/width of buttons/fields
 */
	public void update() {
		int differenceInY = Board.height / 10 + 4;
		int differenceInX = Board.width / 4 + 4;
		int startY = Board.height / 2 - Board.height / 20;
		int startX = Board.width / 2 - Board.width / 8
				- (int) (differenceInX * .5);

		shipOne.update(new Point(startX, startY));
		shipTwo.update(new Point(startX + (1 * differenceInX), startY
				+ (0 * differenceInY)));
		shipThree.update(new Point(startX, startY + (1 * differenceInY)));
		shipFour.update(new Point(startX + (1 * differenceInX), startY
				+ (1 * differenceInY)));
		back.update(new Point(startX, startY + (2 * differenceInY)));
		joinGame.update(new Point(startX + ((int) (1 * differenceInX)), startY
				+ (2 * differenceInY)));
		name.update(new Point(startX, startY - (int) (differenceInY * .5)));
		server.update(new Point(startX, startY - (int) (1 * differenceInY)));
		switch (shipSelected_) {
		case ONE:
			shipOne.changeInsideColor(Color.DARK_GRAY);
			break;
		case TWO:
			shipTwo.changeInsideColor(Color.DARK_GRAY);

			break;
		case THREE:

			shipThree.changeInsideColor(Color.DARK_GRAY);
			break;
		case FOUR:
			shipFour.changeInsideColor(Color.DARK_GRAY);
			break;
		}
		switch (textSelected) {
		case NONE:
			name.changeInsideColor(Color.lightGray);
			server.changeInsideColor(Color.LIGHT_GRAY);
			break;
		case NAME:
			name.changeInsideColor(Color.YELLOW);
			server.changeInsideColor(Color.LIGHT_GRAY);
			break;
		case SERVER:
			name.changeInsideColor(Color.lightGray);
			server.changeInsideColor(Color.yellow);
			break;
		}

	}
/**
 * Handles mouse clicks.
 * @param e MouseEvent
 */
	public void mouseClicked(MouseEvent e) {
		textSelected = TextState.NONE;
		clickPoint = e.getPoint();
		if (back.contain(clickPoint)) {
			Board.gameStatus = GameState.STARTMENU;
		}
		if (joinGame.contain(clickPoint)) {
			int ship = -1;
			switch (shipSelected_) {
			case ONE:
				ship = 8;
				break;
			case TWO:
				ship = 0;
				break;
			case THREE:
				ship = 9;
				break;
			case FOUR:
				ship = 10;
				break;
			}

			Board.gameStatus = GameState.GAME;
			GamePlay.getPlayer().setImage(ship);
			GamePlay.getPlayer().setHitBox(ship);
			GamePlay.getPlayer().setName(name.getText());
			if (this.rejectedName_ == false)
				Board.gamePlay.startHandler(server.getText());
			else
				GamePlay.getPlayer().setName(name.getText());

		}
		if (name.contain(clickPoint)) {
			name.clicked();
			textSelected = TextState.NAME;
		}
		if (server.contain(clickPoint)) {
			server.clicked();
			textSelected = TextState.SERVER;

		}
		if (shipOne.contain(clickPoint)) {
			GamePlay.getPlayer().setImage(8);
			shipSelected_ = ShipState.ONE;
		}
		if (shipTwo.contain(clickPoint)) {
			GamePlay.getPlayer().setImage(0);
			shipSelected_ = ShipState.TWO;
		}
		if (shipThree.contain(clickPoint)) {
			GamePlay.getPlayer().setImage(9);
			shipSelected_ = ShipState.THREE;
		}
		if (shipFour.contain(clickPoint)) {
			GamePlay.getPlayer().setImage(10);
			shipSelected_ = ShipState.FOUR;
		}

	}
/**
 * Handles mouse movement
 * @param e MouseEvent
 */
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();

		if (name.contain(p) && textSelected != TextState.NAME) {
			name.changeInsideColor(Color.cyan);
		} else if (textSelected != TextState.NAME)
			name.changeInsideColor(Color.LIGHT_GRAY);
		if (server.contain(p) && textSelected != TextState.SERVER) {
			server.changeInsideColor(Color.cyan);
		} else if (textSelected != TextState.SERVER)
			server.changeInsideColor(Color.LIGHT_GRAY);

		if (joinGame.contain(p)) {
			joinGame.changeInsideColor(Color.black);
		} else {
			joinGame.changeInsideColor(Color.RED);
		}
		if (shipOne.contain(p) && shipSelected_ != ShipState.ONE) {
			shipOne.changeInsideColor(Color.BLACK);
		} else {
			if (shipSelected_ != ShipState.ONE)
				shipOne.changeInsideColor(Color.blue);
		}
		if (shipTwo.contain(p) && shipSelected_ != ShipState.TWO) {
			shipTwo.changeInsideColor(Color.black);
		} else {
			if (shipSelected_ != ShipState.TWO)
				shipTwo.changeInsideColor(Color.blue);
		}
		if (shipThree.contain(p) && shipSelected_ != ShipState.THREE) {
			shipThree.changeInsideColor(Color.black);
		} else {
			if (shipSelected_ != ShipState.THREE)
				shipThree.changeInsideColor(Color.blue);
		}
		if (shipFour.contain(p) && shipSelected_ != ShipState.FOUR) {
			shipFour.changeInsideColor(Color.black);
		} else {
			if (shipSelected_ != ShipState.FOUR)
				shipFour.changeInsideColor(Color.blue);
		}

		if (back.contain(p)) {
			back.changeInsideColor(Color.black);
		} else {

			back.changeInsideColor(Color.blue);
		}

	}
/**
 * Keyboard listener to send the strokes to their appropriate objects
 * @param e KeyEvent
 */
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			switch (textSelected) {
			case NAME:
				textSelected = TextState.SERVER;
			case NONE:
				textSelected = TextState.SERVER;
				break;
			case SERVER:
				textSelected = TextState.NAME;
				break;
			}
		} else if (textSelected == TextState.NAME) {
			name.keyPressed(e);
		} else if (textSelected == TextState.SERVER) {
			server.keyPressed(e);
		}

	}
/**
 * Sets rejectedName to true and sets the status back to modifyMenu
 */
	public void nameReject() {
		rejectedName_ = true;
		Board.gameStatus = GameState.MODIFYMENU;
	}
	/**
	 * returns the rejected Boolean. For testing Purposes
	 * @return rejectedName
	 */
	public boolean getReject(){
		return rejectedName_;
	}
	/**
	 * Returns array of gameButtons. For testing Purposes only
	 * @return array of GameButtons used
	 */
	public GameButton[] returnButtons(){
		GameButton[] arrayButton = {shipOne,shipTwo,shipThree, shipFour, back, joinGame};
		return arrayButton;
	}
	/**
	 * Returns array of gameTextFields.  For testing Purposes Only
	 * @return array of GameTextFields used
	 */
	public GameTextField[] returnTextFields(){
		GameTextField[] textFields = {name, server};
		return textFields;
	}
	
}