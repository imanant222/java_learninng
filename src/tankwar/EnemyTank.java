package tankwar;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
	Vector<Shot> shots=new Vector<>();
	Vector<EnemyTank> enemytanks=new Vector<>();
	boolean isAlive=true;
	public EnemyTank(int x, int y) {
		super(x, y);
	}
	public void setEnemytanks(Vector<EnemyTank> enemytanks) {
		this.enemytanks=enemytanks;
	}
	public boolean isOverlap() {
		switch(this.getDirect()) {
		case 0:
			for(int i=0;i<enemytanks.size();i++) {
				EnemyTank et=enemytanks.get(i);
				if(et!=this) {
					if(et.getDirect()==0||et.getDirect()==2){
						if(this.getY()-et.getY()<85&&this.getY()-et.getY()>0&&Math.abs(this.getX()-et.getX())<85) {
							return true;
						}
					}
					else if(et.getDirect()==1||et.getDirect()==3){
						if(this.getY()-et.getY()<80&&this.getY()-et.getY()>0&&Math.abs(this.getX()-et.getX())<80) {
							return true;
						}
					}
				}
			}
			break;
		case 1:
			for(int i=0;i<enemytanks.size();i++) {
				EnemyTank et=enemytanks.get(i);
				if(et!=this) {
					if(et.getDirect()==0||et.getDirect()==2){
							if((this.getY()-et.getY()<90||et.getY()-this.getY()<80)&&et.getX()-this.getX()<90) {
								return true;
							}
						}
					else if(et.getDirect()==1||et.getDirect()==3){
							if(Math.abs(this.getY()-et.getY())<85&&et.getX()-this.getX()<85&&et.getX()-this.getX()>0) {
								return true;
							}
					}
			  }
		}
		break;
		case 2:
			for(int i=0;i<enemytanks.size();i++) {
				EnemyTank et=enemytanks.get(i);
				if(et!=this) {
					if(et.getDirect()==0||et.getDirect()==2){
						if(et.getY()-this.getY()<85&&et.getY()-this.getY()>0&&Math.abs(this.getX()-et.getX())<85) {
							return true;
						}
					}
					else if(et.getDirect()==1||et.getDirect()==3){
						if(et.getY()-this.getY()<90&&et.getY()-this.getY()>0&&Math.abs(this.getX()-et.getX())<90) {
							return true;
						}
					}
				}
			}
			break;
		case 3:
			for(int i=0;i<enemytanks.size();i++) {
				EnemyTank et=enemytanks.get(i);
				if(et!=this) {
					if(et.getDirect()==0||et.getDirect()==2) {
						if(((this.getY()-et.getY()<80&&this.getY()-et.getY()>0)||(et.getY()-this.getY()<90&&et.getY()-this.getY()>0))&&this.getX()-et.getX()<80&&this.getX()-et.getX()>0) {
							return true;
						}
					}
					else if(et.getDirect()==1||et.getDirect()==3){
						if(Math.abs(this.getY()-et.getY())<85&&this.getX()-et.getX()<85&&this.getX()-et.getX()>0) {
							return true;
						}
					}
				}
			}
			break;
	}
		return false;
	}
	
	@Override
	public void run() {
		while(isAlive==true) {
			if(shots.size()==0) {
				Shot s = null;
				switch(getDirect()){
				case 0:
					s=new Shot(getX()+19,getY()-45,0);
					break;
				case 1:
					s=new Shot(getX()+90,getY()+20,1);
					break;
				case 2:
					s=new Shot(getX()+19,getY()+90,2);
					break;
				case 3:
					s=new Shot(getX()-45,getY()+20,3);
					break;
			}
				shots.add(s);
				new Thread(s).start();
				
			}
		switch(getDirect()) {
		case 0:
			for(int i = 0;i<30;i++) {
				if(y-25>0&&isOverlap()==false) {
					MoveUp();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case 1:
			for(int i = 0;i<30;i++) {
				if(x+90<1000&&isOverlap()==false) {
					MoveRight();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case 2:
			for(int i = 0;i<30;i++) {
				if(y+75<750&&isOverlap()==false) {
					MoveDown();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case 3:
			for(int i = 0;i<30;i++) {
				if(x-25>0&&isOverlap()==false) {
				MoveLeft();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		setDirect((int)(Math.random()*4));
		
		}
	}

}
