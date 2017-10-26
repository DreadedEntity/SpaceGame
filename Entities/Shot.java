package Entities;

import java.awt.Color;

import Core.RectDouble;

public class Shot extends Entity {
	
	public Shot(double shipX, double shipY) {
		rect = new RectDouble(shipX, shipY, 5, 5, Color.MAGENTA);
		speed = 15;
	}
	
	public void animate() {
		rect.setX(rect.getX() + speed); 
	}
}
