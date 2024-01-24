package homework598;

public class Go {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread02().start();
		new Thread(new Thread01()).start();
	}

}
