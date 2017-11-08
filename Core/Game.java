package Core;
import Entities.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	public static ArrayList<Star> stars = new ArrayList<Star>(Collections.synchronizedCollection(new ArrayList<Star>()));
	public static ArrayList<Shot> shots = new ArrayList<Shot>(Collections.synchronizedCollection(new ArrayList<Shot>()));
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>(Collections.synchronizedCollection(new ArrayList<Enemy>()));
//	public static ArrayList<Sine> sines = new ArrayList<Sine>(Collections.synchronizedCollection(new ArrayList<Sine>()));
	public static Player player = new Player();
	
	public static int frame = 0;
	
	public double scaleX = 1;
	public double scaleY = 1;
	
	public Game() {
		this.setSize(getPreferredSize());
		for (int i = 0; i < 200; i++) {
			stars.add(new Star(this));
		}
		
		for (int i = 0; i < 5; i++)
			enemies.add(new Enemy(this));
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
			Shot shot = shots.get(i);
			if (shot.getRect().getX() > this.getPreferredSize().getWidth()) {
				shots.remove(i);
				shots.trimToSize();
			}
			else
				shot.animate();
		}
		
//		for (int i = 0; i < shots.size(); i++) {
//			Shot shot = shots.get(i);
//			if (shot.getRect().getX() > this.getPreferredSize().getWidth()) {
//				shots.remove(i);
//				shots.trimToSize();
//			}
//			else
//				shot.animate();
//		}
		
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getRect().getX() < 0) {
				enemies.remove(i);
				enemies.add(new Enemy(this));
				enemies.trimToSize();
			}
			
			enemies.get(i).animate();
		}
	}
	public ArrayList<Entity> collisionCheck() {
		ArrayList<Entity> collisions = new ArrayList<Entity>();
		
		for (int i = 0; i < Game.enemies.size(); i++) {
			if (Game.enemies.get(i).collidesWith(Game.player))
				collisions.add(Game.enemies.get(i));
			
		}
		if (Game.shots.size() > 0)
			for (int i = 0; i < Game.enemies.size(); i++) {
				if (Game.enemies.get(i).collidesWith(Game.player))
					collisions.add(Game.enemies.get(i));
				for (int j = 0; j < Game.shots.size(); j++) {
					if (Game.enemies.get(i).collidesWith(Game.shots.get(j))) {
						if (Game.shots.get(j).getForceX() > 0) {
							collisions.add(Game.enemies.get(i));
							collisions.add(Game.shots.get(j));
							Game.enemies.add(new Enemy(this));
							Game.enemies.add(new Enemy(this));
						}
					}
				}
			}
		return collisions;
	}
	
	public void handleCollisions(ArrayList<Entity> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).kill();
		}
	}
	
	public void movePlayer(boolean[] keys) {
		if (keys[87]) {	player.moveY(false); }
		if (keys[65]) { player.moveX(false); }
		if (keys[83]) { player.moveY(true); }
		if (keys[68]) {	player.moveX(true);	}
		if (keys[32]) {
			Shot shot = player.shoot();
			if (shot != null)
				shots.add(shot);
		}
		if (keys[86]) {
			Sine shot = player.shoot1();
			if (shot != null)
				shots.add((Shot)shot);
		}
		if (keys[88]) {
			Mine shot = player.shoot2();
			if (shot != null)
				shots.add((Shot)shot);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(960, 540);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		scaleX = this.getSize().getWidth() / this.getPreferredSize().getWidth();
		scaleY = this.getSize().getHeight() / this.getPreferredSize().getHeight();
		
		//draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawBackground(g);
		
		//draw Shots
		for (int i = 0; i < shots.size(); i++) {
			drawEntity(shots.get(i), g);
		}
		
		//draw Enemies
		for (int i = 0; i < enemies.size(); i++) {
			drawEntity(enemies.get(i), g);
		}
		
		//draw Player
		drawEntity(player, g);
		
		g.setColor(Color.YELLOW);
		g.drawString("Frame: " + Game.frame, 1, 10);
//		double fps = (MainFrame.fps + 10) - System.currentTimeMillis();
//		g.drawString("FPS: " + (60 * ((100/6) / fps)), 1, 22);
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
		
//		g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
	}
	private void drawBackground(Graphics g) {
		for (int i = 0; i < stars.size(); i++) {
			Color c = new Color(255,255,255, stars.get(i).getAlpha());
			g.setColor(c);
			
			Star star = stars.get(i);
			g.fillRect(
				(int)(star.getX() * scaleX),
				(int)(star.getY() * scaleY),
				Math.max(1, (int)(1 * scaleX)),
				Math.max(1, (int)(1 * scaleY))
			);
			
			star.twinkle();
		}
	}
}
