package Core;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	static boolean[] playerKeys = new boolean[193];
	public static boolean toggle = false;
	public static boolean toggleHeld = false;

	public static void main(String[] args) {
		MainFrame gameScreen = new MainFrame();
		
		Game p = new Game();
		gameScreen.add(p);
		gameScreen.setSize(p.getPreferredSize());
		gameScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		gameScreen.setExtendedState(Frame.MAXIMIZED_BOTH);
//		gameScreen.setUndecorated(true);
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
		
		while (!playerKeys[27]) {
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
		gameScreen.getDefaultCloseOperation();
	}	
}
