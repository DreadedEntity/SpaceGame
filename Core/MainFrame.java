package Core;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial") 
public class MainFrame extends JFrame {
	
	public static boolean[] playerKeys = new boolean[193];
	public static boolean toggle = false;
	public static boolean toggleHeld = false;
	public static long fps = 0;
	public static MainFrame gameScreen = new MainFrame();
	public static JPanel game = new Game();
	public static JPanel menu = new Menu();
	

	public static void main(String[] args) {
		gameScreen.add(menu);
		gameScreen.setSize(menu.getPreferredSize());
		gameScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		gameScreen.setExtendedState(Frame.MAXIMIZED_BOTH);
//		gameScreen.setUndecorated(true);
		gameScreen.setVisible(true);
		
		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gameScreen.add(game);
				gameScreen.remove(menu);
				System.out.println("menu mouse ran");
				gameScreen.repaint();
			}
		});
		game.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gameScreen.add(menu);
				gameScreen.remove(game);
				System.out.println("game mouse ran");
				gameScreen.repaint();
			}
		});
		
		gameScreen.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent key) {
				System.out.println(key.getKeyCode());
//				System.out.println(key.getKeyChar());
				int code = key.getKeyCode();
				char ch = key.getKeyChar();
				
				playerKeys[code] = true;
				if (ch == 'c') {
					for (int i = 0; i < Game.enemies.size(); i++) {
						Game.enemies.get(i).setSpeed(0);
					}
				}
				if (code == 49 && !toggleHeld) {
					toggle = !toggle;
					toggleHeld = true;
					if (toggle) {
						gameScreen.setExtendedState(Frame.MAXIMIZED_BOTH);
						gameScreen.setUndecorated(true);
					} else {
						gameScreen.setExtendedState(Frame.NORMAL);
						
					}
					System.out.println(toggle);
				}
			}

			@Override
			public void keyReleased(KeyEvent key) {
				int code = key.getKeyCode();
				
				playerKeys[key.getKeyCode()] = false;
				
				if (code == 49) {
					toggleHeld = false;
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		double fps60 = 16.66667;
		double frameWait = 10;
		int end = 0;
		
		while (!playerKeys[27]) {
			/*
			fps = System.nanoTime();
			
			p.movePlayer(playerKeys);
			p.physicsTick();
			p.handleCollisions(p.collisionCheck());
			gameScreen.repaint();
			
			frameWait = (System.nanoTime() - fps) / 10000;
			System.out.print(frameWait - fps);
			try {
				TimeUnit.MILLISECONDS.sleep((long) frameWait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (60 * (fps60 / ((System.nanoTime() - fps) / 1000000)) < 30)
				if (++end == 5)
					break;
			Game.frame++;
			//System.out.println("\t" + (System.nanoTime() - fps));
			 */
		}
		gameScreen.repaint();
		
		System.out.println("Thanks for playing");
		
		Game.enemies.trimToSize();
		Game.shots.trimToSize();
		Game.stars.trimToSize();
		
		System.out.println("Total Objects: " + Game.enemies.size() + Game.shots.size() + Game.stars.size() + 1);
	}	
}
