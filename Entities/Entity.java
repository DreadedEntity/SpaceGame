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
	
	public void kill() {
	}
	
	public RectDouble getRect() { return this.rect; }
	
	public void animate() {
		this.rect.setX(this.rect.getX() + (forceX * speed));
	}
	
	public boolean collidesWith(Entity e) {
		return this.getRect().collidesWith(e.getRect());
	}
	
	public void setSpeed(int i) {
		this.speed = 0;
	}
}
