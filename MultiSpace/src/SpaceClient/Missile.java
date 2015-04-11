package SpaceClient;

/**
 * This class is the parent class of all weapon classes.
 * 
 * @author Kyle Kornetzke
 * 
 */
public class Missile extends Drawable {

	private static double energyCost_ = 10;
	private String id_;
	private double damage = 7;
	private double dx, dy;
	private double acc;
	private double speed;
	private double maxSpeed;
	private double range;
	private double traveled;
/**
 * Main Constructor of Missile
 * @param x: x coordinate
 * @param y: y coordinate
 * @param direction : direction of the player
 * @param dx: change in x 
 * @param dy: change in y 
 * @param missileID: Name of the missile
 */
	public Missile(double x, double y, int direction, double dx, double dy,
			String missileID) {
		super(1, 0, 0);

		this.id_ = missileID;
		this.x = x - width / 2;
		this.y = y - height / 2;
		this.acc = 1;
		this.speed = 0;
		this.maxSpeed = 30;
		this.range = 5000;
		this.traveled = 0;
		this.direction.setDirection(direction);
		this.dx = dx;
		this.dy = dy;

	}
/**
 * Moves the missile using sine and cosine to find the new x and y
 */
	public void move() {
		double dir = Math.toRadians((direction.getDirection()));
		dx -= Math.sin(dir) * acc;
		dy -= Math.cos(dir) * acc;

		speed = Math.sqrt(Math.pow(dy, 2) + Math.pow(dx, 2));

		if (speed > maxSpeed) {
			dy = maxSpeed / speed * dy;
			dx = maxSpeed / speed * dx;
		}

		x += dx;
		y += dy;

		traveled += Math.sqrt(Math.pow(Math.abs(dx), 2)
				+ Math.pow(Math.abs(dy), 2));

		updateMiddle();
		hitBox.update(middle, direction.getDirection());

	}
/**
 * Sees if the missile is inRange
 * @return true if traveld is less than range, else false
 */
	public boolean inRange() {
		return traveled <= range;
	}
/**
 * Not used for this class.  Inherited by Drawable.
 */
	public void cycle() {

	}
/**
 * Retrieves the damage of the missile
 * @return damage
 */
	public double getDamage() {
		return damage;
	}
/**
 * Sets the damage of the missile
 * @param newDamage: new damage amount
 */
	public void setDamage(double newDamage) {
		damage = newDamage;
	}

	/**
	 * Gets the energy cost
	 * @return the energyCost
	 */
	public static double getEnergyCost() {
		return energyCost_;
	}

	/**
	 * Sets the energy cost of a missile
	 * @param energyCost: the energyCost to set
	 */
	public static void setEnergyCost(double energyCost) {
		Missile.energyCost_ = energyCost;
	}

	/**
	 * Gets the ID of the missile
	 * @return the iD
	 */
	public String getID() {
		return id_;
	}

	/**
	 * sets the missile ID
	 * @param iD: the iD to set
	 */
	public void setID(String iD) {
		id_ = iD;
	}

}
