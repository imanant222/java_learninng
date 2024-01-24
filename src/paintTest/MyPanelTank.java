package paintTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class MyPanelTank extends JPanel{

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.gray);
//		坦克身体（宽55像素，长65像素）
//		(x,y,55,65)
		g.fillRect(45, 40, 55, 65);
//		😀g.fill3DRect(x, y, 65, 55,false);
//		坦克轮子（宽15像素，长85像素）
//		(x-15,y-10,15,85)
		g.fillRect(30, 30, 15, 85); 
//		😀g.fill3DRect(x-10, y-15, 85, 15,false); 
//		(x+55,y-10,15,85)
		g.fillRect(100, 30, 15, 85);
//		😀g.fill3DRect(x-10, y+55, 85, 15,false); 
//		坦克炮筒（宽高55）
//		(x,y+5,55,55)
		g.fillOval(45, 45, 55, 55);
//		😀g.fillOval(x-5, y, 55, 55);
//		坦克炮条（宽2像素，长25像素）
//		(x+28,y-25,2,25)
		g.fillRect(73, 15, 2, 25);
//		😀g.fill3DRect(x+65, y+28, 25, 2,false); 
		
		
//		Image image=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bg.png"));
//		g.drawImage(image, 10, 10, 200,89,this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
