package SpaceClient;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * This class is used to draw and maintain the background of stars in the game.
 * 
 * @author Tom Zachary
 * 
 */
public class Background extends Drawable {
	private Point[][] background;
/**
 * Default constructor of background.  Sets up the array of background stars.
 */
	public Background() {
		super(2, 0, 0);
		background = new Point[3][3];
		background[0][0] = new Point(-3000, -3000);
		background[0][1] = new Point(0, -3000);
		background[0][2] = new Point(3000, -3000);
		background[1][0] = new Point(-3000, 0);
		background[1][1] = new Point(0, 0);
		background[1][2] = new Point(3000, 3000);
		background[2][0] = new Point(-3000, 3000);
		background[2][1] = new Point(0, 3000);
		background[2][2] = new Point(3000, 3000);
	}
/**
 * Updates the star array for the player's location
 * @param playerX: location of player (X coordinate)
 * @param playerY: location of player (Y coordinate)
 */
	public void update(double playerX, double playerY) {
		double moveX = playerX - background[1][1].x;
		double moveY = playerY - background[1][1].y;

		boolean moveBoardX = false;
		boolean moveBoardY = false;
		if (moveX <= -1500 || moveX >= 1500) {
			moveBoardX = true;
		}

		if (moveY <= -1600 || moveY >= 1500) {
			moveBoardY = true;
		}
		if (moveBoardX) {
			if (moveX <= 0) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						background[i][j].x = background[i][j].x - 3000;
					}
				}
			} else
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						background[i][j].x = background[i][j].x + 3000;
					}
				}
		}

		if (moveBoardY) {
			if (moveY <= 0) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						background[i][j].y = background[i][j].y - 3000;
					}
				}
			} else
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						background[i][j].y = background[i][j].y + 3000;
					}
				}
		}

	}
/**
 * Inherited from Drawable.  Not used for background.
 */
	public void cycle() {

		return;
	}
	/**
	 * Returns the 2D array of points for the background images
	 * @return 2d array of Points
	 */
	public Point[][] getBackgroundPoints(){
		return background;
	}
/**
 * Paints the background array
 */
	public void paint(Graphics2D g2d, double dx, double dy) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				g2d.drawImage(image, (int) (background[i][j].x - dx),
						(int) (background[i][j].y - dy), null);
		}
	}

}
