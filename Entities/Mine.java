package Entities;

import java.awt.Color;
import java.util.Random;

import Core.Game;
import Core.RectDouble;

public class Mine extends Shot {
	private double angle;
	private double decay = 0;
	private int startingFrame;
	
	
	public Mine(double shipX, double shipY, double forceX) {
		super();
		this.rect = new RectDouble(shipX + 20, shipY, 5, 5, Color.YELLOW);
		this.forceX = forceX;
		startingFrame = Game.frame;
		System.out.println(Game.frame);
		
		Random r = new Random();
		this.angle = r.nextInt(121) - 60;
		this.speed = r.nextInt(3) + 3;
		while (this.decay == 0)
			this.decay = (r.nextDouble() / 10) + 0.1;
	}
	
	@Override
	public void animate() {
		if (this.speed > 0.1) {
			RectDouble rect = this.getRect();
			double newX = rect.getX() + this.speed;
			double newY = rect.getY() + (Math.sin(Math.toRadians(this.angle)) * speed);
			this.speed -= this.decay;
			
			rect.setLocation(newX, newY);
		} else {
			if (Game.frame < startingFrame + 500) {
				this.getRect().setColor(Color.YELLOW);
				if (this.speed <= 0) {
					this.speed = 0.1;
				}
				this.getRect().setLocation(this.getRect().getX() + this.speed, this.getRect().getY());
				if ((Game.frame - startingFrame) % 50 < 10)
					this.getRect().setColor(Color.RED);
			} else {
				this.kill();
				System.out.println("Ran on frame " + Game.frame);
			}
		}
	}
}
