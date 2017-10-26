package Entities;
public class Shot extends Entity {
	private double x;
	private double y;
	private double speed;
	
	public Shot(double shipX, double shipY) {
		x = shipX;
		y = shipY;
		speed = 15;
	}
	
	public void animate() {
		x += speed;
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
}
