package eventTest;

import javax.swing.JFrame;

public class BallMove extends JFrame{
MyPanelBall mp=null;
public BallMove() {
	mp=new MyPanelBall();
	this.add(mp);
	this.setSize(300,300);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
	this.addKeyListener(mp);
}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BallMove();
	}

}
