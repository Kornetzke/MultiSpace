package SpaceClient;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

/**
 * This class manages all hit boxes for each object (ships, planets, missiles).
 * 
 * @author Kyle Kornetzke
 * 
 */
public class HitBox {

	private Point[] hitBoxPoints_;
	private int numberOfPoints_;

	private Polygon hitBoxPoly_;
	private Point middle_;
/**
 * Main Constructor of Hitbox
 * @param points: array of points of the hitbox
 * @param middle: middle point of the hitbox
 */
	public HitBox(Point[] points, Point middle) {
		hitBoxPoly_ = new Polygon();
		hitBoxPoints_ = new Point[points.length];
		numberOfPoints_ = points.length;
		middle_ = new Point(middle);

		for (int i = 0; i < points.length; i++) {
			hitBoxPoints_[i] = new Point(points[i]);
			hitBoxPoly_.addPoint(points[i].x, points[i].y);
		}

	}
/**
 * Sets the hitbox to the specified points and middle points
 * @param points
 * @param middle
 */
	public void setHitbox(Point[] points, Point middle) {

		hitBoxPoints_ = new Point[points.length];
		numberOfPoints_ = points.length;
		middle_.setLocation(middle);
		hitBoxPoly_ = new Polygon();

		for (int i = 0; i < points.length; i++) {
			hitBoxPoints_[i] = new Point(points[i]);
			hitBoxPoly_.addPoint(points[i].x, points[i].y);
		}

	}
/**
 * updates the hitbox to the new middle and spun to the direction of dir
 * @param newmiddle
 * @param dir
 */
	public void update(Point newmiddle, int dir) {

		Point[] changedhitBoxPoints_ = new Point[hitBoxPoints_.length];

		for (int i = 0; i < hitBoxPoints_.length; i++) {
			changedhitBoxPoints_[i] = new Point(hitBoxPoints_[i]);
		}

		hitBoxPoly_ = new Polygon();

		AffineTransform.getRotateInstance(Math.toRadians(-dir), middle_.x,
				middle_.y).transform(hitBoxPoints_, 0, changedhitBoxPoints_, 0,
				numberOfPoints_);

		for (int i = 0; i < changedhitBoxPoints_.length; i++) {
			hitBoxPoly_.addPoint(changedhitBoxPoints_[i].x,
					changedhitBoxPoints_[i].y);
		}

		hitBoxPoly_.translate(newmiddle.x - middle_.x, newmiddle.y - middle_.y);

	}
/**
 * Returns the array of points for the hitbox's polygon
 * @return polygon's points
 */
	public Point[] getPolyPoints() {
		Point[] temp = new Point[numberOfPoints_];

		int[] xPoints = hitBoxPoly_.xpoints;
		int[] yPoints = hitBoxPoly_.ypoints;

		for (int i = 0; i < temp.length; i++) {
			temp[i] = new Point(xPoints[i], yPoints[i]);
		}
		return temp;

	}
/**
 * Returns the Polygon of the hitbox
 * @return polygon of the hitbox
 */
	public Polygon getPolygon() {
		return hitBoxPoly_;
	}
/**
 * Checks to see if any of the points in the array of 'points' are in the polygon of hitbox
 * @param points
 * @return true if intersection occurs, false otherwise.
 */
	public boolean contains(Point[] points) {

		for (int i = 0; i < points.length; i++) {
			if (hitBoxPoly_.contains(points[i]))
				return true;
		}

		return false;
	}

}
