package process;

public class Thread01{

	public static void main(String[] args) {
		Tickets tickets=new Tickets();
		new Thread(tickets).start();
		new Thread(tickets).start();
		new Thread(tickets).start();
	}

}
