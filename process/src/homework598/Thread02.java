package homework598;
import java.util.Scanner;

public class Thread02 extends Thread{
	private static boolean loop=true;
	private Scanner scanner=new Scanner(System.in);
	
	@Override
	public void run() {
		super.run();
		while(true) {
			System.out.println("your order(Q):");
			if(scanner.next().toUpperCase().charAt(0)=='Q') {
				loop=false;
				System.out.println("done!");
				break;
			}
		}
	}

	public boolean getLoop() {
		return loop;
	}

}
