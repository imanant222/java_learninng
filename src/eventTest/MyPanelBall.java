package eventTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MyPanelBall extends JPanel implements KeyListener{
	int x=10;
	int y=10;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.blue);
		g.fillOval(x, y, 20, 20);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// 当有字符输出时，该方法触发
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 当某个键按下，该方法触发
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {//DOWN对应下键
			y++;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			x--;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			x++;
		}else if(e.getKeyCode()==KeyEvent.VK_UP) {
			y--;
		}
		this.repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 当某个键释放，该方法出发
		
	}
}
