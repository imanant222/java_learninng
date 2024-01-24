package paintTest;

import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Draw extends JFrame {
private MyPanelTank mp=null;
public Draw() {
	mp=new MyPanelTank();
	this.add(mp);
	this.setSize(400, 300);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	public static void main(String[] args) {
		new Draw();

	}

}
