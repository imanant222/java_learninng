package homework599;

public class Card implements Runnable{
	private int money=10000;
	private boolean loop=true;
	@Override
	public void run() {
		while(loop) {
			synchronized(this) {
			if(money<1000) {
				System.out.println("余额不足...");
				loop=false;
				return;
			}
			System.out.println(Thread.currentThread().getName()+"取出金额："+"1000元；剩余金额："+(money-=1000)+"元...");
			}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}			
				

}
