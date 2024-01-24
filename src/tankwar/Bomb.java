package tankwar;

public class Bomb {
int x;
int y;
boolean isAlive =true;
int life=9;
	public Bomb(int x, int y) {
	this.x = x;
	this.y = y;
}
public void lifeDown() {
	if(life>0)
		life--;
	else
		isAlive=false;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
