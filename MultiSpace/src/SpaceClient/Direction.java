package SpaceClient;

/**
 * This class holds the direction algorithms used when drawing and manipulating
 * objects in the game.
 * 
 * @author Kyle Kornetzke
 * 
 */
public class Direction {

	private int value_;
/**
 * Default Constructor
 * @param value: direction
 */
	public Direction(int value) {
		this.value_ = value % 360;
	}
/**
 * Retrieves the direction
 * @return direction
 */
	public int getDirection() {
		return value_;
	}
/**
 * Sets the direction to newValue if it is between 0-359.  Otherwise 0
 * @param newvalue: new Direction
 */
	public void setDirection(int newvalue) {
		if (newvalue < 0 || newvalue > 359) {
			value_ = 0;
		} else
			this.value_ = newvalue;
	}
/**
 * Increments the Direction by increment
 * @param increment
 */
	public void incrementDirection(double increment) {
		value_ += increment;

		if (value_ >= 360)
			value_ %= 360;
		else if (value_ < 0)
			value_ += 360;
	}
/**
 * Retrieves the rotation degrees to the target direction
 * @param targetDirection
 * @return the direction needed
 */
	public int getRotationDegrees(int targetDirection) {

		int change = 180 - value_;	

		int temp = 180;
		targetDirection += change;

		if (targetDirection > 359)
			targetDirection %= 360;
		else if (targetDirection < 0)
			targetDirection += 360;

		return targetDirection - temp;

	}

}
