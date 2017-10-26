package Core;
import Entities.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	
//	private ArrayList<Star> stars = new ArrayList<Star>();
	private static ArrayList<Star> stars = new ArrayList<Star>(Collections.synchronizedCollection(new ArrayList<Star>()));
	private static ArrayList<Shot> shots = new ArrayList<Shot>(Collections.synchronizedCollection(new ArrayList<Shot>()));
	private static ArrayList<Enemy> enemies = new ArrayList<Enemy>(Collections.synchronizedCollection(new ArrayList<Enemy>()));
	Player player = new Player();
	
	public double scaleX = 1;
	public double scaleY = 1;
	
	public Game() {
		this.setSize(getPreferredSize());
		for (int i = 0; i < 200; i++) {
			stars.add(new Star(this));
		}
	}
	
	public void physicsTick() {
		for (int i = 0; i < stars.size(); i++) {
			stars.get(i).runPhysics();
			if (stars.get(i).getX() < 0) {
				stars.remove(i);
				stars.add(new Star(this, 0));
				stars.trimToSize();
			}			
		}
		for (int i = 0; i < shots.size(); i++) {
			shots.get(i).animate();
		}
	}
	
	public void movePlayer(boolean[] keys) {
		if (keys[87]) {	player.moveY(false); }
		if (keys[65]) { player.moveX(false); }
		if (keys[83]) { player.moveY(true); }
		if (keys[68]) {	player.moveX(true);	}
		if (keys[32]) { shots.add(player.shoot()); }
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(960, 540);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		scaleX = this.getSize().getWidth() / this.getPreferredSize().getWidth();
		scaleY = this.getSize().getHeight() / this.getPreferredSize().getHeight();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.WHITE);
		for (int i = 0; i < stars.size(); i++) {
			Star star = stars.get(i);
			g.fillRect((int)(star.getX() * scaleX), (int)(star.getY() * scaleY), Math.max(1, (int)(1 * scaleX)), Math.max(1, (int)(1 * scaleY)));
		}
		
		//drawShots
		for (int i = 0; i < shots.size(); i++) {
			drawEntity(shots.get(i), g);
		}
		
		//draw Enemies
		for (int i = 0; i < enemies.size(); i++) {
			drawEntity(shots.get(i), g);
		}
		
		//draw Player
		drawEntity(player, g);
	}
	
	private void drawEntity(Entity e, Graphics g) {
		RectDouble rect = e.getRect();
		g.setColor(rect.getColor());
		g.fillRect(
				(int)(rect.getX() * scaleX),
				(int)(rect.getY() * scaleY),
				(int)(rect.getWidth() * scaleX),
				(int)(rect.getHeight() * scaleY)
		);
	}
}
