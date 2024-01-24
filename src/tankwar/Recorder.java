package tankwar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.io.FileReader;
public class Recorder {
	private static int hitEtNum =0;
	private static BufferedWriter bw=null;
	private static BufferedReader br=null;
	private static String fileName="/Users/hanablack/Desktop/tankwar/recorder.txt";
	private static Vector<EnemyTank> enemytanks=new Vector<EnemyTank>();
	private static Vector<Node> nodes=new Vector<Node>();
	public static void setVector(Vector<EnemyTank> enemytanks) {
		Recorder.enemytanks=enemytanks;
	}
	public static int getHitEtNum() {
		return hitEtNum;
	}

	public static void setHitEtNum(int hitEtNum) {
		Recorder.hitEtNum = hitEtNum;
	}

	public static void addNum() {
		hitEtNum=hitEtNum+1;
	}
	public static void recordToFile() {
		try {
			bw=new BufferedWriter(new FileWriter(fileName));
			bw.write(hitEtNum+"");
			System.out.println("战绩写入成功");
			bw.newLine();
			for(int i=0;i<enemytanks.size();i++) {
				EnemyTank et=enemytanks.get(i);
				if(et.isAlive)
				bw.write(et.getX()+" "+et.getY()+" "+et.getDirect());
				bw.newLine();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (bw!=null) {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Vector<Node> readFile() throws IOException{
		File file=new File("/Users/hanablack/Desktop/tankwar/recorder.txt");
		br=new BufferedReader(new FileReader(fileName));
		hitEtNum=Integer.parseInt(br.readLine());
		String line =null;
		while((line=br.readLine())!=null) {
			String[] xyd=line.split(" ");
			Node node=new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
			nodes.add(node);
		}
		br.close();
		return nodes;
	}
	public static String getFileName() {
		return fileName;
	}
	
}
