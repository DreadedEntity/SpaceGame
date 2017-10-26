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
		if (keys[68]) {
			if ((player.getX() / scaleX) - (20 * scaleX) > this.getWidth())
				player.setX(this.getWidth() - (20 * scaleX));
			else
				player.moveX(true);
		}
		if (keys[32]) { shots.add(player.shoot()); }
		
		
		//TO DO: FIX
		if (player.getX() < 0)
			player.setX(0);
		if (player.getY() < 0)
			player.setY(0);
		if ((player.getY() / scaleY) - (20 * scaleY) > this.getHeight())
			player.setY(this.getHeight() - (20 * scaleY));
		System.out.println(player.getY() + "\t" + this.getHeight());
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
		
		g.setColor(Color.MAGENTA);
		for (int i = 0; i < shots.size(); i++) {
			Shot shot = shots.get(i);
			g.fillRect((int)(shot.getX() * scaleX), (int)(shot.getY() * scaleY), Math.max(1, (int)(5 * scaleX)), Math.max(1, (int)(5 * scaleY)));
		}
		
		//draw Enemies
		for (int i = 0; i < enemies.size(); i++) {
			g.setColor(enemies.get(i).color);
//			g.fillRect(
//				(int)(enemies.get(i).getX() * scaleX),
//				(int)(enemies.get(i).getY() * scaleY),
//				(int)(enemies.get(i).rect.getWidth() * scaleX),
//				(int)(enemeis.get(i).rect.getHeight() * scaleY)
//			);
		}
		
		//draw Player
		g.setColor(player.color);
		g.fillRect(
				(int)(player.getX() * scaleX),
				(int)(player.getY() * scaleY),
				(int)(player.rect.getWidth() * scaleX),
				(int)(player.rect.getHeight() * scaleY)
		);
	}
}
