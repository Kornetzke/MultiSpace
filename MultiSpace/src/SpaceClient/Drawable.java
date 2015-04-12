package SpaceClient;



import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;


import javax.swing.ImageIcon;

/**
 * A super class for all drawable objects. This includes the basic datamembers
 * and methods that all drawable objects have.
 * 
 * @author Kyle Kornetzke
 * 
 */
public abstract class Drawable {
	/**
	 * ii is the ImageIcon for the Drawable object and used when getting the
	 * Image of the object
	 */
	protected ImageIcon imageIcon;

	/**
	 * image is the Image for the Drawable object and is used when painting the
	 * object
	 */
	protected Image image;

	/**
	 * width of the Drawable object
	 */
	protected int width;

	/**
	 * height of the Drawable object
	 */
	protected int height;

	/**
	 * this point is the middle of the drawable object
	 */
	public Point middle;

	/**
	 * this is the direction of the Drawable object. A value of zero is the top
	 * of the screen. A value of 90 is the left side of the screen.
	 */
	protected Direction direction;

	/**
	 * the x coordinate of the ship on the map
	 */
	protected double x;
	/**
	 * the y coordinate of the ship on the map
	 */
	protected double y;
	protected int imageNumber;
	/**
	 * if visible is true the Drawable object will be painted. If false the
	 * Drawable object will not be painted.
	 */
	protected boolean visible;

	/**
	 * This Polygon is used for detecting if other objects are "hitting" the
	 * drawable
	 */
	protected HitBox hitBox;

	protected Point[] startingHitBoxPoints;

	/**
	 * basic constructor for the Drawable object.
	 * 
	 * @param iiNumber
	 *            - The location of the ImageIcon that will be used in iiArray
	 * @param x
	 *            - x coordinate of the Drawable object
	 * @param y
	 *            - y coordinate of teh Drawable object
	 */
	public Drawable(int iiNumber, double x, double y) {
		imageNumber = iiNumber;
		this.imageIcon = Board.imageIconArray[imageNumber];
		this.image = imageIcon.getImage();
		height = imageIcon.getIconHeight();
		width = imageIcon.getIconWidth();
		this.x = x;
		this.y = y;
		this.direction = new Direction(0);
		this.middle = new Point();
		updateMiddle();

		this.startingHitBoxPoints = new Point[4];
		startingHitBoxPoints[0] = new Point((int) x, (int) y);
		startingHitBoxPoints[1] = new Point((int) (x + width), (int) y);
		startingHitBoxPoints[2] = new Point((int) (x + width),
				(int) (y + height));
		startingHitBoxPoints[3] = new Point((int) x, (int) (y + height));

		this.hitBox = new HitBox(startingHitBoxPoints, middle);

	}

	/**
	 * The paint method paints the object and any Drawable object that this
	 * object contains.
	 * <p>
	 * The Image will be rotated based on the direction value.
	 * <p>
	 * The dx and the dy value will translate the object to be drawn in respect
	 * to the object of focus. The dx will tran
	 * 
	 * @param g2d
	 * @param dx
	 * @param dy
	 */
	public void paint(Graphics2D g2d, double dx, double dy) {
		g2d.rotate(Math.toRadians(-getDirection()), middle.x - dx, middle.y
				- dy);
		g2d.drawImage(image, (int) (x - dx), (int) (y - dy), null);
		g2d.rotate(Math.toRadians(getDirection()), middle.x - dx, middle.y - dy);

		// Draws the hitBox of the ship for debugging
		//hitBox.getPolygon().translate((int) -dx, (int) -dy);
		//g2d.draw(hitBox.getPolygon());

	}

	public int getImageNumber() {
		return imageNumber;
	}

	public void setDirection(int value) {
		direction.setDirection(value);

	}

	public int getDirection() {
		return direction.getDirection();
	}

	public double getX() {
		return x;
	}

	public int getmiddleX() {
		return middle.x;
	}

	public int getmiddleY() {
		return middle.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public HitBox getHitBox() {
		return hitBox;
	}

	public void updateMiddle() {
		middle.setLocation(x + width / 2, y + height / 2);
	}

	public void updateHitBox() {
		hitBox.update(middle, direction.getDirection());

	}

	public boolean intersects(Point[] polyPoints) {
		return hitBox.contains(polyPoints);
	}

	public Point[] getHitPoints() {
		return hitBox.getPolyPoints();
	}

	public void setImage(int newImageNumber) {
		imageNumber = newImageNumber;
		this.imageIcon = Board.imageIconArray[imageNumber];
		this.image = imageIcon.getImage();
		this.width = imageIcon.getIconWidth();
		updateMiddle();

	}

	public void setPlacement(double x, double y, int direction) {
		this.x = x;
		this.y = y;
		setDirection(direction);

	}

	public abstract void cycle();

}
