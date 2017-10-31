package Entities;

import java.awt.Color;

import Core.Game;
import Core.RectDouble;

public class Sine extends Shot {
	private int startingFrame;
	private double startingY;
	
	public Sine(double shipX, double shipY, double forceX) {
		super();
		this.rect = new RectDouble(shipX + 20, shipY, 5, 5, Color.MAGENTA);
		this.speed = 6;
		this.forceX = forceX;
		this.startingY = shipY;
		this.startingFrame = Game.frame;
		System.out.println("Sine created");
	}
	
	public void kill() {
		Game.shots.remove(this);
	}
	
	public double getForceX() {
		return this.forceX;
	}
	
	@Override
	public void animate() {
		this.getRect().setLocation(this.getRect().getX() + (forceX * speed) / 3, startingY + (Math.sin(Math.toRadians((Game.frame - startingFrame) * 5))) * this.speed * 10);
		System.out.println(Game.frame - startingFrame);
	}
}
