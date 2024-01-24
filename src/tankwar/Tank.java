package tankwar;

public class Tank {
	boolean isAlive=true;
//	坦克起始点
	protected int x;
	protected int y;
//	坦克朝向：0上，1右，2下，3左
	private int direct;
//	坦克移动速度
	private int speed=4;
	public int MoveUp() {
		return y-=speed;
	}
	public int MoveRight() {
		return x+=speed;
	}
	public int MoveDown() {
		return y+=speed;
	}
	public int MoveLeft() {
		return x-=speed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Tank(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
