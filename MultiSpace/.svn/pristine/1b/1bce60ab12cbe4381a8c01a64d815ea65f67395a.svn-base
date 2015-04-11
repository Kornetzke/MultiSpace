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
	private GameButton shipOne_;
	private GameButton shipTwo_;
	private GameButton shipThree_;
	private GameButton shipFour_;
	private GameButton back_;
	private GameButton joinGame_;
	private GameTextField name_;
	private GameTextField server_;
	private Point onePoint_;
	private Point twoPoint_;
	private Point threePoint_;
	private Point fourPoint_;
	private Point backPoint_;
	private Point joinPoint_;
	private Color innerColor_;
	private Color outterColor_;
	private Point namePoint_;
	private Point serverPoint_;
	private Point clickPoint_ = new Point(0, 0);

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
		innerColor_ = Color.BLUE;
		outterColor_ = Color.white;
		int differenceInY = Board.height / 8 + 4;
		int differenceInX = Board.width / 8 + 4;
		int startY = Board.height / 2 - Board.height / 20 - differenceInY;
		int startX = Board.width / 2 - Board.width / 8 - differenceInX;

		onePoint_ = new Point(startX, startY);
		twoPoint_ = new Point(startX, startY + (1 * differenceInY));
		threePoint_ = new Point(startX, startY + (2 * differenceInY));
		fourPoint_ = new Point(startX, startY + (3 * differenceInY));
		backPoint_ = new Point(startX, startY + (4 * differenceInY));
		joinPoint_ = new Point(startX, startY + (5 * differenceInY));
		namePoint_ = new Point(startX, startY - differenceInY);
		serverPoint_ = new Point(startX, startY - (2 * differenceInY));

		name_ = new GameTextField(namePoint_, outterColor_, "Name");
		server_ = new GameTextField(serverPoint_, outterColor_, "Server IP");
		shipOne_ = new GameButton(onePoint_, innerColor_, outterColor_,
				"Ship One");
		shipTwo_ = new GameButton(twoPoint_, innerColor_, outterColor_,
				"Ship Two");
		shipThree_ = new GameButton(threePoint_, innerColor_, outterColor_,
				"Ship Three");
		shipFour_ = new GameButton(fourPoint_, innerColor_, outterColor_,
				"Ship Four");
		back_ = new GameButton(backPoint_, innerColor_, outterColor_, "Back");
		joinGame_ = new GameButton(joinPoint_, Color.red, outterColor_,
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

		shipOne_.draw(g2d);
		shipTwo_.draw(g2d);
		shipThree_.draw(g2d);
		shipFour_.draw(g2d);
		back_.draw(g2d);
		joinGame_.draw(g2d);
		name_.draw(g2d);
		server_.draw(g2d);
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

		shipOne_.update(new Point(startX, startY));
		shipTwo_.update(new Point(startX + (1 * differenceInX), startY
				+ (0 * differenceInY)));
		shipThree_.update(new Point(startX, startY + (1 * differenceInY)));
		shipFour_.update(new Point(startX + (1 * differenceInX), startY
				+ (1 * differenceInY)));
		back_.update(new Point(startX, startY + (2 * differenceInY)));
		joinGame_.update(new Point(startX + ((int) (1 * differenceInX)), startY
				+ (2 * differenceInY)));
		name_.update(new Point(startX, startY - (int) (differenceInY * .5)));
		server_.update(new Point(startX, startY - (int) (1 * differenceInY)));
		switch (shipSelected_) {
		case ONE:
			shipOne_.changeInsideColor(Color.DARK_GRAY);
			break;
		case TWO:
			shipTwo_.changeInsideColor(Color.DARK_GRAY);

			break;
		case THREE:

			shipThree_.changeInsideColor(Color.DARK_GRAY);
			break;
		case FOUR:
			shipFour_.changeInsideColor(Color.DARK_GRAY);
			break;
		}
		switch (textSelected) {
		case NONE:
			name_.changeInsideColor(Color.lightGray);
			server_.changeInsideColor(Color.LIGHT_GRAY);
			break;
		case NAME:
			name_.changeInsideColor(Color.YELLOW);
			server_.changeInsideColor(Color.LIGHT_GRAY);
			break;
		case SERVER:
			name_.changeInsideColor(Color.lightGray);
			server_.changeInsideColor(Color.yellow);
			break;
		}

	}
/**
 * Handles mouse clicks.
 * @param e MouseEvent
 */
	public void mouseClicked(MouseEvent e) {
		textSelected = TextState.NONE;
		clickPoint_ = e.getPoint();
		if (back_.contain(clickPoint_)) {
			Board.gameStatus_ = GameState.STARTMENU;
		}
		if (joinGame_.contain(clickPoint_)) {
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

			Board.gameStatus_ = GameState.GAME;
			GamePlay.getPlayer().setImage(ship);
			GamePlay.getPlayer().setHitBox(ship);
			GamePlay.getPlayer().setName(name_.getText());
			if (this.rejectedName_ == false)
				Board.gamePlay_.startHandler(server_.getText());
			else
				GamePlay.getPlayer().setName(name_.getText());

		}
		if (name_.contain(clickPoint_)) {
			name_.clicked();
			textSelected = TextState.NAME;
		}
		if (server_.contain(clickPoint_)) {
			server_.clicked();
			textSelected = TextState.SERVER;

		}
		if (shipOne_.contain(clickPoint_)) {
			GamePlay.getPlayer().setImage(8);
			shipSelected_ = ShipState.ONE;
		}
		if (shipTwo_.contain(clickPoint_)) {
			GamePlay.getPlayer().setImage(0);
			shipSelected_ = ShipState.TWO;
		}
		if (shipThree_.contain(clickPoint_)) {
			GamePlay.getPlayer().setImage(9);
			shipSelected_ = ShipState.THREE;
		}
		if (shipFour_.contain(clickPoint_)) {
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

		if (name_.contain(p) && textSelected != TextState.NAME) {
			name_.changeInsideColor(Color.cyan);
		} else if (textSelected != TextState.NAME)
			name_.changeInsideColor(Color.LIGHT_GRAY);
		if (server_.contain(p) && textSelected != TextState.SERVER) {
			server_.changeInsideColor(Color.cyan);
		} else if (textSelected != TextState.SERVER)
			server_.changeInsideColor(Color.LIGHT_GRAY);

		if (joinGame_.contain(p)) {
			joinGame_.changeInsideColor(Color.black);
		} else {
			joinGame_.changeInsideColor(Color.RED);
		}
		if (shipOne_.contain(p) && shipSelected_ != ShipState.ONE) {
			shipOne_.changeInsideColor(Color.BLACK);
		} else {
			if (shipSelected_ != ShipState.ONE)
				shipOne_.changeInsideColor(Color.blue);
		}
		if (shipTwo_.contain(p) && shipSelected_ != ShipState.TWO) {
			shipTwo_.changeInsideColor(Color.black);
		} else {
			if (shipSelected_ != ShipState.TWO)
				shipTwo_.changeInsideColor(Color.blue);
		}
		if (shipThree_.contain(p) && shipSelected_ != ShipState.THREE) {
			shipThree_.changeInsideColor(Color.black);
		} else {
			if (shipSelected_ != ShipState.THREE)
				shipThree_.changeInsideColor(Color.blue);
		}
		if (shipFour_.contain(p) && shipSelected_ != ShipState.FOUR) {
			shipFour_.changeInsideColor(Color.black);
		} else {
			if (shipSelected_ != ShipState.FOUR)
				shipFour_.changeInsideColor(Color.blue);
		}

		if (back_.contain(p)) {
			back_.changeInsideColor(Color.black);
		} else {

			back_.changeInsideColor(Color.blue);
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
			name_.keyPressed(e);
		} else if (textSelected == TextState.SERVER) {
			server_.keyPressed(e);
		}

	}
/**
 * Sets rejectedName to true and sets the status back to modifyMenu
 */
	public void nameReject() {
		rejectedName_ = true;
		Board.gameStatus_ = GameState.MODIFYMENU;
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
		GameButton[] arrayButton = {shipOne_,shipTwo_,shipThree_, shipFour_, back_, joinGame_};
		return arrayButton;
	}
	/**
	 * Returns array of gameTextFields.  For testing Purposes Only
	 * @return array of GameTextFields used
	 */
	public GameTextField[] returnTextFields(){
		GameTextField[] textFields = {name_, server_};
		return textFields;
	}
	
}