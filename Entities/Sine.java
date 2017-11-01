package Entities;

import java.awt.Color;

import Core.RectDouble;

public class Sine extends Shot {
	private double startingY;
	private double startingX;
	
	public Sine(double shipX, double shipY, double forceX) {
		super();
		this.rect = new RectDouble(shipX + 20, shipY, 5, 5, Color.MAGENTA);
		this.speed = 6;
		this.forceX = forceX;
		this.startingY = shipY;
		this.startingX = shipX + 20;
	}
	
	@Override
	public void animate() {
//		this.getRect().setLocation(this.getRect().getX() + (forceX * speed) / 3, startingY + (Math.sin(Math.toRadians((Game.frame - startingFrame) * 5))) * this.speed * 10);
		this.getRect().setLocation(this.getRect().getX() + (forceX * speed) / 3, startingY + (Math.sin(Math.toRadians((this.getRect().getX() - this.startingX) * 3))) * this.speed * 10);
	}
}
