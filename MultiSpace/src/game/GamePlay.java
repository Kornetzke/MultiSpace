package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import menu.GameButton;
import SpaceClient.Background;
import SpaceClient.Board;
import SpaceClient.Drawable;
import SpaceClient.NetworkCommunicationHandler;
import SpaceClient.SpaceShip;

/**
 * This class is used to maintain the game while in the gameplay state.
 * 
 * @author Tom Zachary and Kyle Kornetzke
 * 
 */
public class GamePlay {
	public static NetworkCommunicationHandler handler;
	private Background backgroundImage;
	private static SpaceShip player; // Object that the player will control.
	private GameButton deadButton;
	private static List<Drawable> spaceDrawableList = new ArrayList<Drawable>();

	private static Hashtable<String, SpaceShip> playerHashTable = new Hashtable<String, SpaceShip>();

	private double pantDisplacementX, pantDisplacementY; // Displacement for the x
														// and
														// y value for drawing
														// other
														// drawable objects, to
														// center the vision of
														// the
														// board on the player
														// screen with focus on

	/**
	 * The default constructor for game play will set up the player ship,
	 * background, and the death button.
	 * 
	 * @precondition no precondition
	 * 
	 * 
	 */
	public GamePlay() {
		deadButton = new GameButton(new Point(Board.width / 2,
				Board.height / 2 + 100), Color.red, Color.WHITE, "RESPAWN");

		backgroundImage = new Background();

		player = new SpaceShip(0, (int) (Math.random() * 1000),
				(int) (Math.random() * 1000));

		player.setName("Kyle" + (int) (Math.random() * 1000));

	}

	public GamePlay(String test) {
		handler = new NetworkCommunicationHandler();
	}

	/**
	 * keyPressed method will convert the keyEvent event into a player status.
	 * <p>
	 * example If the 'a' key is pressed the player.setLeft(true) will be called
	 * 
	 * @param e
	 *            - keyEvent object
	 * @precondition player is not null
	 * 
	 */
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 65) {
			player.setLeft(true);
		}
		if (e.getKeyCode() == 68) {
			player.setRight(true);
		}
		if (e.getKeyCode() == 87) {
			player.setForward(true);
		}
		if (e.getKeyCode() == 83) {
			player.setBreak(true);
		}
		if (e.getKeyCode() == 32) {
			player.setFire(true);
		}

	}

	/**
	 * keyReleased method will convert the keyEvent event into a player status.
	 * <p>
	 * example If the 'a' key is released the player.setLeft(false) will be
	 * called
	 * 
	 * @param e
	 *            - keyEvent object
	 * @precondition player is not null
	 * 
	 */
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == 65) {
			player.setLeft(false);
		}
		if (e.getKeyCode() == 68) {
			player.setRight(false);
		}
		if (e.getKeyCode() == 87) {
			player.setForward(false);
		}
		if (e.getKeyCode() == 83) {
			player.setBreak(false);
		}
		if (e.getKeyCode() == 32) {
			player.setFire(false);
		}
	}

	/**
	 * the mousePressed method will listen for the mouse to be pressed. When
	 * called the method will check to see if the player alive or dead.
	 * <p>
	 * If dead and the mouse is within the respawn button the player.respawn()
	 * is called.
	 * <p>
	 * If the player is alive the player.setFire(true) will be called
	 * 
	 * @param e
	 *            - MouseEvent
	 * @precondition player is not null
	 */
	public void mousePressed(MouseEvent e) {
		if (player.isDead() && deadButton.contain(e.getPoint())) {
			player.respawn();
		}
		player.setFire(true);
	}

	/**
	 * The mouseReleased method, when called, will call player.setFire(false).
	 * This will stop the player ship from firing
	 * 
	 * @param e
	 *            - MouseEvent
	 * @precondition Player is not null
	 */
	public void mouseReleased(MouseEvent e) {
		player.setFire(false);
	}

	/**
	 * When called the mouseMoved will check if the player is alive or dead.
	 * <p>
	 * If the player is dead and the MouseEvent moves to inside the deadButton,
	 * the button will change
	 * <p>
	 * If the player is alive it will change the Player.setMouseLocation based
	 * on the MouseEvent Point
	 * 
	 * @param e
	 *            - MouseEvent
	 */
	public void mouseMoved(MouseEvent e) {
		if (player.isDead()) {
			if (deadButton.contain(e.getPoint())) {
				deadButton.changeInsideColor(Color.black);
			} else
				deadButton.changeInsideColor(Color.red);
		}
		player.setMouseLocation(e.getPoint());

	}

	/**
	 * When the mouseDragged method is called it will change the
	 * player.mouseLocation to the new MouseEvent point
	 * 
	 * @param e
	 *            - MouseEvent
	 */
	public void mouseDragged(MouseEvent e) {
		player.setMouseLocation(e.getPoint());

	}

	/**
	 * The update method moves all the objects that change placement during the
	 * game. This method moves playerHashTable's missiles and then moves their
	 * hitboxes.
	 * <p>
	 * If the player is dead the deadButton.update() is called
	 * <p>
	 * If the player is alive the player.cycle() is called, then the hitCheck()
	 * is called
	 * <p>
	 * lastly the backgroundImage is updated
	 * 
	 * @precondition PlayerHashTable is not null
	 *               <p>
	 *               Player is not null
	 *               <p>
	 *               backgroundImage is not null
	 *               <p>
	 *               deadButton is not null
	 */
	public void update() {

		synchronized (playerHashTable) {
			for (SpaceShip x : playerHashTable.values()) {
				x.MoveMissile();
				x.updateHitBox();

			}
		}

		if (player.isDead()) {
			deadButton.update(new Point(Board.width / 2,
					Board.height / 2 + 100));

		} else {
			player.cycle();
			HitCheck();
		}

		backgroundImage.update(player.middle.x, player.middle.y);

	}

	/**
	 * The HitCheck method checks if the player got hit by any of the missiles
	 * of the other players.
	 * 
	 * @preconditions playerHashTable is not null
	 * @preconditions player is not null
	 */
	public void HitCheck() {
		synchronized (playerHashTable) {
			for (SpaceShip x : playerHashTable.values()) {
				x.checkIfWeaponHit(player);
			}
		}

	}

	/**
	 * The draw method checks if the handler is ready before drawing anything
	 * else. The handler would be getting all of the planets or ships from the
	 * server when not ready.
	 * <p>
	 * If the handler is ready, the draw method will find what kind of
	 * displacement is needed to keep the player's ship in the center of the
	 * screen. All objects will be drawn with this displacement to balance the
	 * move. This DOES NOT change the real position of the objects, just how
	 * they are drawn on the screen.
	 * <p>
	 * The method draws in the order of, background, planets, other players,
	 * player, ui
	 * <p>
	 * If the player is dead the screen will add a filter and not draw the
	 * player's ship. The respawn button will be visible.
	 * 
	 * @param g2d
	 *            - Graphics2D
	 * @precondition handler, background, playerHashTable, space, player are not
	 *               null
	 */

	public void draw(Graphics2D g2d) {

		if (handler.isGameReady() == false) {

			g2d.setColor(Color.black);
			g2d.fillRect(0, 0, Board.width, Board.height);
			return;
		}

		pantDisplacementX = player.getmiddleX() - (Board.width / 2);
		pantDisplacementY = player.getmiddleY() - (Board.height / 2);

		backgroundImage.paint(g2d, pantDisplacementX, pantDisplacementY);

		for (Drawable x : spaceDrawableList) {
			if (Math.abs(player.getX() - x.getX()) <= 3000
					&& Math.abs(player.getY() - x.getY()) <= 3000)
				x.paint(g2d, pantDisplacementX, pantDisplacementY);

		}

		synchronized (playerHashTable) {
			for (SpaceShip x : playerHashTable.values()) {
				x.paint(g2d, pantDisplacementX, pantDisplacementY);
				
				drawRadar(g2d, x.getmiddleX(), x.getmiddleY());
			}
		}
		player.paint(g2d, pantDisplacementX, pantDisplacementY);
		
		//player.getHitBox().paint(g2d, pantDisplacementX, pantDisplacementY);
		


		drawUI(g2d);

		if (player.isDead()) {
			g2d.setColor(new Color(0f, 0f, 0f, .5f));
			g2d.fillRect(0, 0, Board.width, Board.height);
			g2d.setColor(Color.white);
			g2d.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,
					Board.height / 20));
			g2d.drawString("YOU ARE DEAD", Board.width / 2 - 4
					* g2d.getFont().getSize(), Board.height / 2
					- g2d.getFont().getSize());
			g2d.drawString("Deaths: " + player.getDeathCount(), Board.width
					/ 2 - 4 * g2d.getFont().getSize(), Board.height / 2);
			deadButton.draw(g2d);
		}

	}

	/**
	 * The drawUI method will draw the information of the ship on the top left
	 * side of the screen for the player to view.
	 * 
	 * @param g2d
	 * @precondition player is not null
	 */
	public void drawUI(Graphics2D g2d) {
		int UIwidth = Board.width / 5;
		int UIheight = Board.height / 5;

		int UImaxWidth = 275;
		int UImaxHeight = 150;

		if (UIwidth > UImaxWidth)
			UIwidth = UImaxWidth;
		if (UIheight > UImaxHeight)
			UIheight = UImaxHeight;

		g2d.setColor(Color.RED);
		g2d.fillRoundRect(0, 0, UIwidth, UIheight / 3, 10, 10);
		g2d.fillRoundRect(0, UIheight / 3 + 1, UIwidth, UIheight / 3, 10, 10);
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRoundRect(0, (UIheight * 2) / 3 + 1, UIwidth, UIheight / 3, 10,
				10);

		g2d.setColor(Color.GREEN);
		g2d.drawRoundRect(0, 0, UIwidth, UIheight / 3, 10, 10);
		g2d.fillRoundRect(
				0,
				0,
				(int) (UIwidth * (player.getHealth() / player.getMaxHealth())),
				UIheight / 3, 10, 10);

		g2d.setColor(Color.BLUE);
		g2d.drawRoundRect(0, UIheight / 3 + 1, UIwidth, UIheight / 3, 10, 10);
		g2d.fillRoundRect(
				0,
				UIheight / 3 + 1,
				(int) (UIwidth * (player.getShield() / player.getMaxShield())),
				UIheight / 3, 10, 10);

		g2d.setColor(Color.MAGENTA);
		g2d.drawRoundRect(0, (UIheight * 2) / 3 + 1, UIwidth, UIheight / 3, 10,
				10);
		g2d.fillRoundRect(
				0,
				(UIheight * 2) / 3 + 1,
				(int) (UIwidth * (player.getEnergy() / player.getMaxEnergy())),
				UIheight / 3, 10, 10);

	}
/**
 * This method will draw a radar indicator based on opponent players middle x and y position. The 
 *  
 * @param g2d
 * @param oppenentMiddleX
 * @param opponentMiddleY
 */
	public void drawRadar(Graphics2D g2d, double oppenentMiddleX,	double opponentMiddleY ) {

		int boardMiddleX = Board.width/2;
		int boardMiddleY = Board.height/2;
		double lineThickness = 1.5;
		double magnitude = 2;
		int arcAngle = 40;
		double angle;
		double distance;
		

		g2d.rotate(
				-(Math.atan2((double)player.getmiddleX() - oppenentMiddleX,
						(double)player.getmiddleY() - opponentMiddleY)),
						boardMiddleX,boardMiddleY);
		
		
		distance = Math.sqrt(Math.pow(
				Math.abs(player.getmiddleX() - oppenentMiddleX), 2)
				+ Math.pow(Math.abs(player.getmiddleY() - opponentMiddleY), 2));

		magnitude = 900 / distance;

		magnitude = (magnitude > 1) ? 1 : magnitude;
		magnitude = (magnitude < 0.025) ? 0.025 : magnitude;


		angle = arcAngle * magnitude;

		g2d.setStroke(new BasicStroke((float)lineThickness));
		
		g2d.setColor(Color.YELLOW);
		
		g2d.drawArc(boardMiddleX - 150, boardMiddleY - 150, 300, 300, (int)(90 - (angle / 2)), (int)angle);
		g2d.drawLine((int)(boardMiddleX-lineThickness/2), boardMiddleY-148, (int)(boardMiddleX-lineThickness/2), (int)(boardMiddleY-150+angle+300));


		g2d.rotate(
				(Math.atan2(player.getmiddleX() - oppenentMiddleX,
						player.getmiddleY() - opponentMiddleY)),
						boardMiddleX, boardMiddleY);


	}

	/**
	 * retrieves the spaceship hash table
	 * 
	 * @return Spaceship Hash Table
	 */
	public static Hashtable<String, SpaceShip> getPlayerHashTable() {
		return playerHashTable;
	}

	/**
	 * Retrieves the player's ship
	 * 
	 * @return player's ship
	 */
	public static SpaceShip getPlayer() {
		return player;
	}

	/**
	 * Retrieves all space items (not ships)
	 * 
	 * @return list of space items (planets)
	 */
	public static List<Drawable> getSpaceList() {
		return spaceDrawableList;
	}

	/**
	 * Starts the handler for contact to server
	 * 
	 * @param ip
	 *            Address
	 */
	public void startHandler(String string) {

		handler = new NetworkCommunicationHandler(string);
		handler.start();
	}

	/**
	 * Sets the space list of objects
	 * 
	 * @param list
	 *            of Space Objects
	 */
	public static void setSpaceList(List<Drawable> list) {
		spaceDrawableList = list;

	}

	/**
	 * Sets the player ship
	 * 
	 * @param spaceShip
	 */
	public static void setPlayer(SpaceShip ship) {
		player = ship;

	}

	/**
	 * Sets the hashtable of players
	 * 
	 * @param hashtable
	 *            of players
	 */
	public static void setPlayerHashTable(Hashtable<String, SpaceShip> hash) {
		playerHashTable = hash;

	}

}
