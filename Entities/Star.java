package Entities;
import Core.*;
import java.util.Random;

public class Star {
	
	private double x;
	private double y;
	private double velocity;
	
	public Star(Game g) {
		Random r = new Random();
		velocity = r.nextDouble() * 8 + 1;
		x = r.nextInt((int) (g.getWidth() / g.scaleX));
		y = r.nextInt((int) (g.getHeight() / g.scaleY));
	}
	
	public Star(Game g, int i) {
		Random r = new Random();
		velocity = r.nextDouble() * 8 + 1;
		x = g.getWidth() / g.scaleX;
		y = r.nextInt((int) (g.getHeight() / g.scaleY));
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public double getVelocity() { return velocity; };
	
	public void runPhysics() {
		x -= velocity;
	}
}
