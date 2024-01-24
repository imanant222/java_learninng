package process;

public class Dog implements Runnable{
	int time=0;
	@Override
	public void run() {
		while(true) {
			System.out.println("wer我是狗，啊啊～"+(++time));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(time==10) {
				break;
			}
		}
		
	}
}
