package Entities;

import Core.RectDouble;

public class Entity {
	protected RectDouble rect;
	protected double health;
	protected double speed;
	protected double forceX;
	protected double forceY;
	
	public Entity() {
		rect = new RectDouble();
		health = speed = forceX = forceY = 0;
	}
	
	public RectDouble getRect() { return this.rect; }
}
