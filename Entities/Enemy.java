package Entities;

import java.awt.Color;
import java.util.Random;

import Core.Game;

public class Enemy extends Entity {
	private Random random = new Random();
	
	public Enemy(Game game) {
		rect.setSize(20, 20);		
		rect.setLocation(game.getPreferredSize().getWidth(), random.nextInt((int) (game.getPreferredSize().getHeight() - (20 * game.scaleY))));
		rect.setColor(Color.WHITE);

		speed = -(random.nextInt(2) + random.nextDouble());
		forceX = 1;
	}
	
	public void kill() {
		Game.enemies.remove(this);
	}
	
	@Override
	public void animate() {
		super.animate();
		Random r = new Random();
		if (r.nextInt(100) > 98) {
			Game.shots.add(new Shot(this.getRect().getX() - 5, this.getRect().getY(), -1));
		}
	}
}
