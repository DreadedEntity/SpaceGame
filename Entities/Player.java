package Entities;
import java.awt.Color;

import Core.Game;
import Core.RectDouble;

public class Player extends Entity {
	int frameFiredLast = 0;
	
	public Player() {
		speed = 5;
		forceX = 1;
		forceY = 1;
		rect = new RectDouble(10, 200, 20, 20, Color.RED);
	}
	
	public Shot shoot() {
		Shot shot = null;
		if (Game.frame > frameFiredLast + 10) {
			shot = new Shot(rect.getX(),rect.getY() + 10, 1);
			frameFiredLast = Game.frame;
		}
		return shot;
	}
	public Sine shoot1() {
		Sine shot = null;
		if (Game.frame > frameFiredLast + 10) {
			shot = new Sine(rect.getX(),rect.getY() + 10, 1);
			frameFiredLast = Game.frame;
		}
		return shot;
	}
	public Mine shoot2() {
		Mine shot = null;
		if (Game.frame > frameFiredLast + 10) {
			shot = new Mine(rect.getX(),rect.getY() + 10, 1);
			frameFiredLast = Game.frame;
		}
		return shot;
	}
	
	public void moveX(boolean b) {
		if (b)
			rect.setX(rect.getX() + (forceX * speed));
		else
			rect.setX(rect.getX() - (forceX * speed));
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
