package Core;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	static boolean[] playerKeys = new boolean[155];

	public static void main(String[] args) {
		
		MainFrame gameScreen = new MainFrame();
		
		Game p = new Game();
		gameScreen.add(p);
		gameScreen.setSize(p.getPreferredSize());
		gameScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gameScreen.setVisible(true);
		
		p.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gameScreen.repaint();
			}
		});
		
		gameScreen.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent key) {
//				System.out.println(key.getKeyCode());
//				System.out.println(key.getKeyChar());
				playerKeys[key.getKeyCode()] = true;
				if (key.getKeyChar() == 'c') {
					for (int i = 0; i < Game.enemies.size(); i++) {
						Game.enemies.get(i).setSpeed(0);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent key) {
				playerKeys[key.getKeyCode()] = false;
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		while (true) {
			p.movePlayer(playerKeys);
			p.physicsTick();
			p.handleCollisions(p.collisionCheck());
			gameScreen.repaint();
			
			
			
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Game.frame++;
		}
	}	
}
