package Entities;
import Core.*;
import java.util.Random;

public class Star {
	
	private double x;
	private double y;
	private double velocity;
	private int alpha;
	private int maxAlpha;
	private Random random;
	
	public Star(Game g) {
		random = new Random();
		velocity = random.nextDouble() * 8 + 1;
		x = random.nextInt((int) (g.getWidth() / g.scaleX));
		y = random.nextInt((int) (g.getHeight() / g.scaleY));
		this.alpha = 128 + random.nextInt(128);
		this.maxAlpha = 255;
	}
	
	public Star(Game g, int i) {
		random = new Random();
		velocity = random.nextDouble() * 8 + 1;
		x = g.getWidth() / g.scaleX;
		y = random.nextInt((int) (g.getHeight() / g.scaleY));
		this.alpha = 128 + random.nextInt(128);
		this.maxAlpha = 255;
	}
	
	public void twinkle() {
		if (random.nextInt(100) > 95) {
			int temp = alpha;
			alpha = maxAlpha;
			maxAlpha = temp;
		}
	}
	
	public int getAlpha() { return alpha; }
	public double getX() { return x; }
	public double getY() { return y; }
	public double getVelocity() { return velocity; }
	
	public void runPhysics() {
		x -= velocity;
	}
}
