package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import SpaceClient.Board;

/**
 * This reusable class is for the game buttons found on the client menus. It
 * contains various abilities within.
 * 
 * @author Tom Zachary
 * 
 */
public class GameButton {
	private Rectangle edge;
	private Rectangle inner;
	private Color outside;
	private Color inside;
	private final int BORDER = 6;
	private String text;
/**
 * The only constructor for GameButton
 * @param location Point of upper left edge
 * @param insideColor Inside color of the button
 * @param outsideColor Outside Color of the button
 * @param text Text that is on the button
 */
	public GameButton(Point location, Color insideColor, Color outsideColor,
			String text) {
		edge = new Rectangle(location.x, location.y, (Board.width / 4),
				(Board.height / 10));
		inner = new Rectangle(location.x + (BORDER / 2), location.y
				+ (BORDER / 2), (Board.width / 4 - BORDER),
				(Board.height / 10 - BORDER));
		inside = insideColor;
		outside = outsideColor;
		this.text = text;
	}
/**
 * Changes the inside color of the button
 * @param color new inside Color
 */
	public void changeInsideColor(Color color) {
		inside = color;
	}
/**
 * Changes the outside color of the button
 * @param color new outside Color
 */
	public void changeOutsideColor(Color color) {
		outside = color;
	}
/**
 * Paints the Button
 * @param g2d Graphics handed by the system
 */
	public void draw(Graphics g2d) {
		g2d.setColor(outside);
		g2d.fillRect(edge.x, edge.y, edge.width, edge.height);
		g2d.setColor(inside);
		g2d.fillRect(inner.x, inner.y, inner.width, inner.height);
		g2d.setColor(Color.white);
		if (Board.height > Board.width)
			g2d.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,
					(Board.width / 35)));
		else
			g2d.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,
					(Board.height / 35)));
		g2d.drawString(text,
				inner.x + (inner.width / 2) - (g2d.getFont().getSize()) * 2,
				inner.y + (inner.height / 2));
	}
/**
 * Updates the size and bounds of the button
 * @param point new Point of the button
 */
	public void update(Point point) {
		edge.setBounds(new Rectangle(point.x, point.y, (Board.width / 4),
				(Board.height / 10)));
		inner.setBounds(new Rectangle(point.x + (BORDER / 2), point.y
				+ (BORDER / 2), (Board.width / 4 - BORDER), (Board.height / 10)
				- BORDER));

	}
	/**
	 * Returns the inside rectangle
	 * @return inside Rectangle
	 */
public Rectangle returnInner(){
	return inner;
}
/**
 * checks to see if the point is in the inside rectangle
 * @param click point of click
 * @return true if click was inside the rectangle, else it returns false
 */
	public boolean contain(Point click) {
		if (inner.contains(click)) {
			return true;
		} else
			return false;
	}
/**
 * Returns the inside color
 * @return inside color
 */
	public Color getInsideColor() {
		return inside;
	}
	/**
	 * Returns the outside color
	 * @return outside color
	 */
	public Color getOutsideColor() {
		return this.outside;
	}
	/**
	 * Sets the bounds of the button
	 * @param dimension new bounds of the button
	 */
	public void setBounds(Dimension dimension){
		edge = new Rectangle(edge.x, edge.y, (Board.width / 4),
				(Board.height / 10));
		inner = new Rectangle(inner.x, inner.y, dimension.width-BORDER,
				dimension.height-BORDER);
	}
	/**
	 * For testing purposes
	 */
	public String toString(){
		return this.edge.x+" "+this.edge.y;
	}
}
