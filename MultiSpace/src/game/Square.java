package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Square {
	
	double x,y;
	int width,height,speedX,speedY;
	Color color;
	boolean print;
	
	
	public Square(boolean print){
		Random rand = new Random();

		this.x = rand.nextInt(600);
		this.y = rand.nextInt(600);
		this.width = rand.nextInt(25)+25;
		this.height = rand.nextInt(25)+25;
		
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		this.color = new Color(r, g, b);
		
		this.speedX = speedY = rand.nextInt(50)+25;
		this.speedX = speedY = 35;
		
		this.print = print;
	}
	
	public void update(long elapsedTime){
		if(x+width > 650 || x < 0)
			speedX *= -1;
		
		if(y+height > 650 || y < 0)
			speedY *= -1;
		
		
		x += speedX*elapsedTime/Math.pow(10, 9);
		y += speedY*elapsedTime/Math.pow(10, 9);
	}
	
	public void draw(Graphics2D g2d){
		
		g2d.setColor(color);
		g2d.fillRect((int)Math.round(x), (int)Math.round(y), width, height);
	}
	
	public void setPrint(boolean print){
		this.print = print;
	}

}
