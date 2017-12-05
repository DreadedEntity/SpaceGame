package Core;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	public Menu() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getX() > 280 && e.getX() < 680)
					System.out.println("TESTESTETSETSET");
			}
		});
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(960, 540);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.YELLOW);
		g.drawString("Click to advance", 1, 10);
		
		for (int i = 0; i < 4; i++) {
			g.fillRect(280, 340 + (40*i), 400, 30);
		}
		g.setColor(Color.BLACK);
		g.drawString("New Game", 280, 350);
		g.drawString("Continue", 280, 390);
		g.drawString("Options", 280, 430);
		g.drawString("Quit", 280, 470);
	}
}
