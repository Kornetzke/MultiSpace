package SpaceClient;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
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

	private Point[] hitBoxPoints;
	private int numberOfPoints;

	private Polygon hitBoxPoly;
	private Point middle;
/**
 * Main Constructor of Hitbox
 * @param points: array of points of the hitbox
 * @param middle: middle point of the hitbox
 */
	public HitBox(Point[] points, Point middle) {
		hitBoxPoly = new Polygon();
		hitBoxPoints = new Point[points.length];
		numberOfPoints = points.length;
		this.middle = new Point(middle);

		for (int i = 0; i < points.length; i++) {
			hitBoxPoints[i] = new Point(points[i]);
			hitBoxPoly.addPoint(points[i].x, points[i].y);
		}

	}
/**
 * Sets the hitbox to the specified points and middle points
 * @param points
 * @param middle
 */
	public void setHitbox(Point[] points, Point middle) {

		hitBoxPoints = new Point[points.length];
		numberOfPoints = points.length;
		this.middle.setLocation(middle);
		hitBoxPoly = new Polygon();

		for (int i = 0; i < points.length; i++) {
			hitBoxPoints[i] = new Point(points[i]);
			hitBoxPoly.addPoint(points[i].x, points[i].y);
		}

	}
/**
 * updates the hitbox to the new middle and spun to the direction of dir
 * @param newmiddle
 * @param dir
 */
	public void update(Point newmiddle, int dir) {

		Point[] changedhitBoxPoints_ = new Point[hitBoxPoints.length];

		for (int i = 0; i < hitBoxPoints.length; i++) {
			changedhitBoxPoints_[i] = new Point(hitBoxPoints[i]);
		}
		
		
		hitBoxPoly.reset();

		AffineTransform.getRotateInstance(Math.toRadians(-dir), middle.x,
				middle.y).transform(hitBoxPoints, 0, changedhitBoxPoints_, 0,
				numberOfPoints);

		for (int i = 0; i < changedhitBoxPoints_.length; i++) {
			hitBoxPoly.addPoint(changedhitBoxPoints_[i].x,
					changedhitBoxPoints_[i].y);
		}

		hitBoxPoly.translate(newmiddle.x - middle.x, newmiddle.y - middle.y);

	}
/**
 * Returns the array of points for the hitbox's polygon
 * @return polygon's points
 */
	public Point[] getPolyPoints() {
		Point[] temp = new Point[numberOfPoints];

		int[] xPoints = hitBoxPoly.xpoints;
		int[] yPoints = hitBoxPoly.ypoints;

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
		return hitBoxPoly;
	}
/**
 * Checks to see if any of the points in the array of 'points' are in the polygon of hitbox
 * @param points
 * @return true if intersection occurs, false otherwise.
 */
	public boolean contains(Point[] points) {

		for (int i = 0; i < points.length; i++) {
			if (hitBoxPoly.contains(points[i]))
				return true;
		}

		return false;
	}
	
	public String toString(){
		String returnString = "";
		
		for(int i = 0; i < hitBoxPoly.npoints;i++){
			returnString +="["+hitBoxPoly.xpoints[i]+ ":"+hitBoxPoly.ypoints[i]+"]";
		}
		return returnString;
	}
	
	public void paint(Graphics2D g2d, double displacementX, double displacementY){
		
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.WHITE);
		g2d.translate(-displacementX, -displacementY);
		g2d.drawPolygon(hitBoxPoly);
		g2d.translate(displacementX, displacementY);
		
	}

}
