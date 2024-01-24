package homework598;


public class Thread01 implements Runnable{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while(new Thread02().getLoop()) {
			System.out.println((int)(Math.random()*100+1));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
