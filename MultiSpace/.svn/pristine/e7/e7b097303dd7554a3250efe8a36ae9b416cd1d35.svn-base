package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import SpaceClient.Board;

public class GameTextField {

	private Rectangle edge_;
	private Rectangle inner_;
	private Color outside_;
	private final int BORDER = 6;
	private Color inside_;
	private String text_;
	private String origText_;
	private boolean beenClicked = false;
/**
 * Only constructor for GameTextField
 * @param location point of upper left of textbox
 * @param outsideColor the color of the outside box of the button
 * @param text text to display in the box
 */
	public GameTextField(Point location, Color outsideColor, String text) {
		edge_ = new Rectangle(location.x, location.y, (Board.width / 3),
				(Board.height / 20));
		inner_ = new Rectangle(location.x + (BORDER / 2), location.y
				+ (BORDER / 2), (Board.width / 3 - BORDER),
				(Board.height / 20 - BORDER));
		outside_ = outsideColor;
		inside_ = Color.lightGray;
		text_ = text;
		origText_ = text;
	}
/**
 * Returns the outside Color
 * @param color outside color
 */
	public void changeOutsideColor(Color color) {
		outside_ = color;
	}
/**
 * Draws the textField
 * @param g2d graphics of the system
 */
	public void draw(Graphics g2d) {

		g2d.setColor(outside_);
		g2d.fillRect(edge_.x, edge_.y, edge_.width, edge_.height);
		g2d.setColor(inside_);
		g2d.fillRect(inner_.x, inner_.y, inner_.width, inner_.height);
		g2d.setColor(Color.black);
		if (Board.height < Board.width)
			g2d.setFont(new Font(Font.SANS_SERIF, Font.ITALIC,
					Board.height / 35));
		else
			g2d.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, Board.width / 35));
		g2d.drawString(text_,
				inner_.x + (inner_.width / 2) - (g2d.getFont().getSize()) * 2,
				inner_.y + (inner_.height / 2) * 2);

		g2d.setColor(Color.white);
		g2d.drawString(origText_, edge_.x - g2d.getFont().getSize() * 5,
				edge_.y + g2d.getFont().getSize());
	}
/**
 * Sets the current text to the new String.  For testing purposes
 * @param newString the new String
 */
	public void setString(String newString) {
		text_ = newString;
	}
/**
 * Updates the location of the button.  Also modifies the text to null if it is tabbed to on the first time.
 * @param point new location of the button
 */
	public void update(Point point) {
		edge_.setBounds(new Rectangle(point.x, point.y, (Board.width / 3),
				(Board.height / 20)));
		inner_.setBounds(new Rectangle(point.x + (BORDER / 2), point.y
				+ (BORDER / 2), (Board.width / 3 - BORDER), (Board.height / 20)
				- BORDER));
		if (inside_ == Color.yellow && beenClicked == false) {
			beenClicked = true;
			text_ = "";
		}

	}
/**
 * if clicked on, changes the text to null
 */
	public void clicked() {
		if (text_.compareToIgnoreCase(origText_) == 0) {
			text_ = "";
		}
	}
/**
 * Checks to see if the point is inside the button
 * @param click location of mouse click
 * @return true of click was inside the button, otherwise false
 */
	public boolean contain(Point click) {
		if (inner_.contains(click)) {
			return true;
		} else
			return false;
	}
/**
 * Changes the inside color
 * @param color new inside color
 */
	public void changeInsideColor(Color color) {
		inside_ = color;
	}
/**
 * Deals with the keystrokes inputed by the player
 * @param e Keyevent action
 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (text_.length() == 0) {
				return;
			}
			String temp = text_.substring(0, text_.length() - 1);
			text_ = temp;
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			return;
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			return;
		} else {
			if (text_.length() < 25) {
				char typed = e.getKeyChar();
				text_ = text_.concat("" + typed);
			}
		}
	}
	/**
	 * returns the inside color
	 * @return inside color
	 */
	public Color getInsideColor() {
		return inside_;
	}
	/**
	 * Returns the outside color
	 * @return outside color
	 */
	public Color getOutsideColor() {
		return this.outside_;
	}
	/**
	 * Returns the text of the field
	 * @return text of the field
	 */
	public String getText() {
		return text_;
	}
/**
 * For testing purposes.  Returns location.
 */
	public String toString(){
		return this.edge_.x+" "+this.edge_.y;
	}
}
