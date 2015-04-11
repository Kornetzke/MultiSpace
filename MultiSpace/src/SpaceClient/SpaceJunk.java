package SpaceClient;

/**
 * This class are all non player controlled objects that are drawn (minus
 * missiles).
 * 
 * @author Kyle Kornetzke
 * 
 */
public class SpaceJunk extends Drawable {
/**
 * Default Constructor of SpaceJunk.  Just does the super constructor of Drawable
 * @param facePaint: number of the image in image array
 * @param x: x coordinate
 * @param y: y coordinate
 */
	public SpaceJunk(int facePaint, double x, double y) {
		super(facePaint, x, y);

	}
/**
 * Not used by this class.  Was inherited from drawable
 */
	public void cycle() {

	}

}
