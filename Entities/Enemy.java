package Entities;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import Core.Game;

public class Enemy extends Entity {
	private double x;
	private double y;
	private double speed;
	public Color color;
	public Rectangle rect;
	
	public Enemy(Game game) {
		rect.setSize(20, 20);
		
		Random r = new Random();
		x = game.getWidth();
		y = r.nextInt((int) (game.getHeight() - (20 * game.scaleY)));
		
		speed = 5;
	}
	
	public void tickEnemy() {
		
	}
}
