package tankwar;

public class Shot implements Runnable{
int x;//子弹x坐标
int y;//子弹y坐标
int direct=0;
int speed=6;
boolean isAlive=true;
	public Shot(int x, int y, int direct) {
	super();
	this.x = x;
	this.y = y;
	this.direct = direct;
}
	@Override
	public void run() {
		while(true) {
			if(!(x>0&&x<1000&&y>0&&y<750&&isAlive==true)) {
				isAlive=false;
				System.out.println("子弹线程销毁");
				break;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(direct) {
			case 0:
				y-=speed;
				break;
			case 1:
				x+=speed;
				break;
			case 2:
				y+=speed;
//				System.out.println(x+";"+y);
				break;
			case 3:
				x-=speed;
				break;
			}
		}
	}
	
}
