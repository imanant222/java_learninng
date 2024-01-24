package process;

public class Tickets implements Runnable{
	int ticketsNum=100;
	boolean loop=true;
	
	public synchronized void sell() {
		if (ticketsNum<=0) {
			loop=false;
			System.out.println("票已售完...");	
			return;
		}
		
		System.out.println("窗口"+Thread.currentThread().getName()+"卖出一张，剩余票数"+(--ticketsNum)+"张...");
	}
	@Override
	public void run() {
		while(loop) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sell();	
		}
	}
	
}
