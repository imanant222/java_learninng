package tankwar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel implements KeyListener,Runnable{
	MyTank mytank=null;
	Vector<EnemyTank> enemytanks=new Vector<>();
	Vector<Node> nodes=new Vector<Node>();
	int enemytankSzie =3;
	Vector<Bomb> bomb=new Vector<Bomb>();
	Image image=null;
	public MyPanel(String key) throws IOException {
		File file=new File(Recorder.getFileName());
		if(!file.exists()) {
			System.out.println("无历史记录，默认开启新游戏！");
			key="1";
		}
		mytank=new MyTank(100,500); 
		mytank.setSpeed(3);
		switch(key) {
		case "1":
			Recorder.setHitEtNum(0);
			//		初始化敌人坦克
			for(int i=0;i < enemytankSzie;i++) {
				EnemyTank et=new EnemyTank(200*(i+1),10);
				et.setDirect(2);
				et.setEnemytanks(enemytanks);
				Shot shot=new Shot(et.getX()+19,et.getY()+90,et.getDirect());
				et.shots.add(shot);
				new Thread(shot).start();
				enemytanks.add(et);
				new Thread(et).start();
			}
			break;
		case "2":
//			读取历史信息
			nodes=Recorder.readFile();
			for(int i=0;i<nodes.size();i++) {
				Node node=nodes.get(i);
				EnemyTank et=new EnemyTank(node.getX(),node.getY());
				et.setDirect(node.getDirect());
				et.setEnemytanks(enemytanks);
				Shot shot=new Shot(et.getX()+19,et.getY()+90,et.getDirect());
				et.shots.add(shot);
				new Thread(shot).start();
				enemytanks.add(et);
				new Thread(et).start();
			}
			break;
		default:
			System.out.println("请重新输出正确数字：");
			break;
		}
		

		image=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb.gif"));
//		image=Toolkit.getDefaultToolkit().getImage (Panel.class.getResource ("/bomb.gif") );
		Recorder.setVector(enemytanks);
		Music m=new Music("/Users/hanablack/Desktop/tankwar/bgm.wav");
		m.start();
	}
	
//	画展示击毁敌军坦克数量的面板
	public void drawHitInfo(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("serif",Font.BOLD,20));
		g.drawString("YOU HAVE DESTROYED ENEMYTANKS:", 1050, 50);
		drawTank(1050, 100, g, 0, 1);
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getHitEtNum()+"", 1150, 120);
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
//		设置背景布的颜色、大小
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 750);
//		把绘制坦克的方法封装就更便于绘画多个坦克，只需要传入绘制坦克的数值，方法就帮你画出来！
		if(mytank!=null&&mytank.isAlive==true) {
		drawTank(mytank.getX(),mytank.getY(),g,mytank.getDirect(),0);
		}
		for(int i=0;i<enemytanks.size();i++) {
			EnemyTank et=enemytanks.get(i);
			if (et.isAlive==true)
			{drawTank(et.getX(),et.getY(),g,et.getDirect(),1);
//			System.out.println(i+":"+et.getX()+"   "+et.getY()+"   "+et.getDirect());
			}
			for(int j=0;j<et.shots.size();j++) {
				Shot shot=et.shots.get(j);
				if(shot.isAlive==true) {
					g.setColor(Color.yellow);
					g.fillOval(shot.x, shot.y, 20, 20);
				}
				else {
					et.shots.remove(shot);
				}
			}
		}
//	绘制面板
		drawHitInfo(g);
		
//		绘制炸弹
		for(int i=0;i<bomb.size();i++) {
			Bomb b=bomb.get(i);
			g.drawImage(image, b.x, b.y, 100, 100, this);
			b.isAlive=false;
			stopImage();
		}
		
//		绘制子弹
		for(int i=0;i<mytank.shots.size();i++) {
			Shot s=mytank.shots.get(i);
			if(s!=null&&s.isAlive==true) {
			g.setColor(Color.yellow);
			g.fillOval(s.x, s.y, 20, 20);
			}
			else {
				mytank.shots.remove(s);
				System.out.println("子弹remove");
			}
		}
	}
//	让爆炸效果结束
	public void stopImage() {
		Timer timer=new Timer(1000,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<bomb.size();i++) {
					Bomb b=bomb.get(i);
					if(b.isAlive==false)
					bomb.remove(b);
				}
			}
			
		});
		timer.setRepeats(false);
		timer.start();
	}
	public void drawTank(int x,int y,Graphics g,int direct,int type) {
//		type->Color
		switch(type) {
		case 0:
			g.setColor(Color.gray);
			break;
		case 1:
			g.setColor(Color.CYAN);
			break;
		}
//		direct->direction & draw
//		0->up;1->right;2->down;3->left;
		switch(direct) {
		case 0:
			g.fill3DRect(x,y,55,65,false);
			g.fill3DRect(x-15,y-10,15,85,false);
			g.fill3DRect(x+55,y-10,15,85,false);
			g.fill3DRect(x+26,y-25,5,25,false);
			g.fillOval(x,y+5,55,55);
			break;
		case 1:
			g.fill3DRect(x, y, 65, 55,false);
			g.fill3DRect(x-10, y-15, 85, 15,false); 
			g.fill3DRect(x-10, y+55, 85, 15,false); 
			g.fillOval(x+4, y, 55, 55);
			g.fill3DRect(x+65, y+26, 25, 5,false); 
			break;
		case 2:
			g.fill3DRect(x,y,55,65,false);
			g.fill3DRect(x-15,y-10,15,85,false);
			g.fill3DRect(x+55,y-10,15,85,false);
			g.fill3DRect(x+26,y+65,5,25,false);
			g.fillOval(x,y+5,55,55);
			break;
		case 3:
			g.fill3DRect(x, y, 65, 55,false);
			g.fill3DRect(x-10, y-15, 85, 15,false); 
			g.fill3DRect(x-10, y+55, 85, 15,false); 
			g.fillOval(x+4, y, 55, 55);
			g.fill3DRect(x-25, y+26, 25, 5,false); 
			break;
		} 
	}
	
	public void hitET(Shot s,EnemyTank et) {
		switch(et.getDirect()){
			case 0:
				if((s.x>(et.getX()-15)&& s.x<(et.getX()+70)&& s.y>(et.getY()-10)&& s.y<(et.getY()-25))||((s.x>(et.getX()-15)&& s.x<(et.getX()+70)&& s.y>(et.getY()-10)&& s.y<(et.getY()+75)))) {
					s.isAlive=false;
					System.out.println("我的子弹死了");
					et.isAlive=false;
					bomb.add(new Bomb(et.getX(),et.getY()));
					enemytanks.remove(et);
					Recorder.addNum();
					System.out.println("击毁d0坦克+1");
					Music m=new Music("/Users/hanablack/Desktop/tankwar/hehe.wav");
					m.start();
				}
			break;
			
			case 2:
				if((s.x>(et.getX()-15)&& s.x<(et.getX()+70)&& s.y>(et.getY()+75)&& s.y<(et.getY()+90))||((s.x>(et.getX()-15)&& s.x<(et.getX()+70)&& s.y>(et.getY()-10)&& s.y<(et.getY()+75)))) {
					s.isAlive=false;
					System.out.println("我的子弹死了");
					et.isAlive=false;
					bomb.add(new Bomb(et.getX(),et.getY()));
					enemytanks.remove(et);
					Recorder.addNum();
					System.out.println("击毁d1坦克+1");
					Music m=new Music("/Users/hanablack/Desktop/tankwar/hehe.wav");
					m.start();
				}
			break;
				
			case 1:
				if((s.x>(et.getX()+75)&& s.x<(et.getX()+90)&& s.y>(et.getY()+26)&& s.y<(et.getY()+31))||((s.x>(et.getX()-10)&& s.x<(et.getX()+75)&& s.y>(et.getY()-15)&& s.y<(et.getY()+70)))) {
					s.isAlive=false;
					System.out.println("我的子弹死了");
					et.isAlive=false;
					bomb.add(new Bomb(et.getX(),et.getY()));
					enemytanks.remove(et);
					Recorder.addNum();
					System.out.println("击毁d2坦克+1");
					Music m=new Music("/Users/hanablack/Desktop/tankwar/hehe.wav");
					m.start();
				}
			break;
			
			case 3:
				if((s.x>(et.getX()-25)&& s.x<(et.getX()-10)&& s.y>(et.getY()+26)&& s.y<(et.getY()+31))||((s.x>(et.getX()-10)&& s.x<(et.getX()+75)&& s.y>(et.getY()-15)&& s.y<(et.getY()+70)))) {
					s.isAlive=false;
					System.out.println("我的子弹死了");
					et.isAlive=false;
					bomb.add(new Bomb(et.getX(),et.getY()));
					enemytanks.remove(et);
					Recorder.addNum();
					System.out.println("击毁d3坦克+1");
					Music m=new Music("/Users/hanablack/Desktop/tankwar/hehe.wav");
					m.start();
				}
			break;
		}
	}
	
	public void hitMT(Shot s,MyTank mt) {
		switch(mt.getDirect()){
		case 0:
			if((s.x>(mt.getX()-15)&& s.x<(mt.getX()+70)&& s.y>(mt.getY()-10)&& s.y<(mt.getY()-25))||((s.x>(mt.getX()-15)&& s.x<(mt.getX()+70)&& s.y>(mt.getY()-10)&& s.y<(mt.getY()+75)))) {
				s.isAlive=false;
				System.out.println("子弹死了");
				mt.isAlive=false;
				bomb.add(new Bomb(mt.getX(),mt.getY()));
				Music m=new Music("/Users/hanablack/Desktop/tankwar/aoh.wav");
				m.start();
			}
		break;
		case 2:
			if((s.x>(mt.getX()-15)&& s.x<(mt.getX()+70)&& s.y>(mt.getY()+75)&& s.y<(mt.getY()+90))||((s.x>(mt.getX()-15)&& s.x<(mt.getX()+70)&& s.y>(mt.getY()-10)&& s.y<(mt.getY()+75)))) {
				s.isAlive=false;
				System.out.println("子弹死了");
				mt.isAlive=false;
				bomb.add(new Bomb(mt.getX(),mt.getY()));
				Music m=new Music("/Users/hanablack/Desktop/tankwar/aoh.wav");
				m.start();
			}
		break;
		case 1:
			if((s.x>(mt.getX()+75)&& s.x<(mt.getX()+90)&& s.y>(mt.getY()+26)&& s.y<(mt.getY()+31))||((s.x>(mt.getX()-10)&& s.x<(mt.getX()+75)&& s.y>(mt.getY()-15)&& s.y<(mt.getY()+70)))) {
				s.isAlive=false;
				System.out.println("子弹死了");
				mt.isAlive=false;
				bomb.add(new Bomb(mt.getX(),mt.getY()));
				Music m=new Music("/Users/hanablack/Desktop/tankwar/aoh.wav");
				m.start();
			}
		break;
		case 3:
			if((s.x>(mt.getX()-25)&& s.x<(mt.getX()-10)&& s.y>(mt.getY()+26)&& s.y<(mt.getY()+31))||((s.x>(mt.getX()-10)&& s.x<(mt.getX()+75)&& s.y>(mt.getY()-15)&& s.y<(mt.getY()+70)))) {
				s.isAlive=false;
				System.out.println("子弹死了");
				mt.isAlive=false;
				bomb.add(new Bomb(mt.getX(),mt.getY()));
				Music m=new Music("/Users/hanablack/Desktop/tankwar/aoh.wav");
				m.start();
			}
		break;
	}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) {
			if(mytank.y-25>0) {
			mytank.setDirect(0);
			mytank.MoveUp();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_D) {
			if(mytank.x+90<1000) {
			mytank.setDirect(1);
			mytank.MoveRight();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_S) {
			if(mytank.y+90<750) {
			mytank.setDirect(2);
			mytank.MoveDown();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_A) {
			if(mytank.x-25>0) {
			mytank.setDirect(3);
			mytank.MoveLeft();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_J) {
			mytank.shotET();
		}
		this.repaint();
	}
	
	@Override
	public void run() {
		while(true) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int j=0;j<mytank.shots.size();j++) {
			Shot s=mytank.shots.get(j);
		if(s!=null&&s.isAlive) {
			for(int i=0;i<enemytanks.size();i++) {
				EnemyTank et=enemytanks.get(i);
				hitET(mytank.shot,et);
			}
		}
		}
//		遍历每一个敌人坦克，遍历每个敌人坦克的子弹，每个子弹用一次hitMT方法 
		for(int i=0;i<enemytanks.size();i++) {
			EnemyTank et=enemytanks.get(i);
			for(int j=0;j<et.shots.size();j++) {
				Shot s=et.shots.get(j);
				if(mytank.isAlive ) {
				hitMT(s,mytank);
				}
			}
		}
		this.repaint();
	}

}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

