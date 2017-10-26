package Entities;
import java.awt.Color;

import Core.RectDouble;

public class Player extends Entity {
	private double x;
	private double y;
	private double health;
	private double speed;
	private double forceX;
	private double forceY;
	public Color color;
	public RectDouble rect;
	
	public Player() {
		x = 10;
		y = 200;
		health = 100;
		speed = 10;
		forceX = 1;
		forceY = 1;
		//rect.setSize((double)20, (double)20);
	}
	
	public Shot shoot() {
		Shot shot = new Shot((int)x,(int)(y + 10));
		return shot;
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	
	public void setX(double i) { x = i; }
	public void setY(double i) { y = i; }
	
	public void moveX(boolean b) {
		if (b)
			x += forceX * speed;
		else
			x -= forceX * speed;
		
	}
	public void moveY(boolean b) {
		if (b)
			y += forceY * speed;
		else
			y -= forceY * speed;
	}
	
	public double getHealth() { return health; }
	public double getSpeed() { return speed; }
	public double getForceX() { return forceX; }
	public double getForceY() { return forceY; }
}
