 package tankwar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class TankWar extends JFrame{
	static Scanner scanner=new Scanner(System.in);
	MyPanel mypanel=null;
	static String key=null;
	public TankWar() throws IOException {
		mypanel=new MyPanel(key);
		new Thread(mypanel).start();
		this.add(mypanel);
		this.setSize(1500,750);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(mypanel);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Recorder.recordToFile();
//				退出：
				System.exit(0);
			}
			
		});
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("开启新游戏输入1；继续上一局游戏输入2：");
		key =scanner.next();
		new TankWar(); 
	}

}
