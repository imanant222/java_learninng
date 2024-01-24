package tankwar;

import java.util.Vector;

public class MyTank extends Tank{
	Shot shot=null;
	Vector<Shot> shots=new Vector<Shot>();
	public MyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void shotET() {
		if(shots.size()>0) {
			
		}
		else {
		System.out.println("新子弹线程");
		switch(getDirect()){
			case 0:
				shot=new Shot(getX()+19,getY()-45,0);
				break;
			case 1:
				shot=new Shot(getX()+90,getY()+20,1);
				break;
			case 2:
				shot=new Shot(getX()+19,getY()+90,2);
				break;
			case 3:
				shot=new Shot(getX()-45,getY()+20,3);
				break;
		}
		shots.add(shot);
		new Thread(shot).start();
		}
	}

}
