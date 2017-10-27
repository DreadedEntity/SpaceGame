package Entities;

import java.awt.Color;

import Core.RectDouble;

public class Shot extends Entity {
	
	public Shot(double shipX, double shipY, double forceX) {
		this.rect = new RectDouble(shipX, shipY, 5, 5, Color.MAGENTA);
		this.speed = 6;
		this.forceX = forceX;
	}
}
