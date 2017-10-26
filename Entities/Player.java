package Entities;
import java.awt.Color;

import Core.RectDouble;

public class Player extends Entity {
	
	public Player() {
		speed = 10;
		forceX = 1;
		forceY = 1;
		rect = new RectDouble(10, 200, 20, 20, Color.RED);
	}
	
	public Shot shoot() {
		Shot shot = new Shot((int)rect.getX(),(int)(rect.getY() + 10));
		return shot;
	}
	
	public void moveX(boolean b) {
		if (b)
			rect.setX(rect.getX() + (forceX * speed));
		else
			rect.setX(rect.getX() - (forceX * speed));
		System.out.println(rect.getX() + (forceX * speed));
		
	}
	public void moveY(boolean b) {
		if (b)
			rect.setY(rect.getY() + forceY * speed);
		else
			rect.setY(rect.getY() - forceY * speed);
	}
	
	public double getHealth() { return health; }
	public double getSpeed() { return speed; }
	public double getForceX() { return forceX; }
	public double getForceY() { return forceY; }
}
