package homework599;

public class ATM {

	public static void main(String[] args) {
		Card card = new Card();
		new Thread(card).start();
		new Thread(card).start();

	}

}
