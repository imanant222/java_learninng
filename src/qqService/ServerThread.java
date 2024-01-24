package qqService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import qqCommon.MesType;
import qqCommon.Message;
/*
 *该类用于保持接收对应客户端发来的message对象流
 */
public class ServerThread extends Thread{
	Socket socket;
	String userId;//连接到该服务端的用户ID
	private ServerCheckService scs = new ServerCheckService();
	public ServerThread(Socket socket, String userId) {
		this.socket = socket;
		this.userId = userId;
	}
	public Socket getSocket() {
		return socket;
		//其实感觉很多余，不可以直接ServerThread对象.socket来得到socket吗
	}
	@Override
		public void run() {
		while(true) {
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Message m= (Message)ois.readObject();
			//接下来判断m的Type等于什么以做出对应数据输出
			if(m.getMesType().equals(MesType.MESSAGE_GET_ONLINE_USER)) {
				System.out.println(m.getSender()+"发起显示在线用户列表请求...");
				Message m2 = new Message();
				m2.setReceiver(m.getSender());
				m2.setMesType(MesType.MESSAGE_RET_ONLINE_USER);
				m2.setContent(ServerThreadManager.usersList());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(m2);
			}
			else if(m.getMesType().equals(MesType.MESSAGE_CLIENT_EXIT)) {
				//既然客户端退出了，该客户端对应的socket就该关闭了，持有该socket的线程就该从线程集合里删除了，此外，还要通过break结束循环来结束该线程。
				ServerThreadManager.removeServerThread(m.getSender());
				System.out.println("用户"+m.getSender()+"退出系统...");
				socket.close();
				break;
			}
			else if(m.getMesType().equals(MesType.MESSAGE_MES_TO_ONE)) {
				//拿到通往receiver的socket发送message
				if(ServerThreadManager.getServerThread(m.getReceiver())!=null) {
					ObjectOutputStream oos = new ObjectOutputStream(ServerThreadManager.getServerThread(m.getReceiver()).getSocket().getOutputStream());
					oos.writeObject(m);
					System.out.println("用户"+m.getSender()+"向用户"+m.getReceiver()+"发消息...");
				}
//				else {
//					ConcurrentHashMap<String, ArrayList<Message>> CHM= scs.getOfflineDb();
//					boolean exist = false;
//					Set keyset = CHM.keySet();
//					Iterator iterator = keyset.iterator();
//					while(iterator.hasNext()) {
//						Object key = iterator.next();
//						if(key.equals(m.getReceiver())) {
//							exist = true;
//						}
//					}
//					if(exist) {
//						//说明已经存在于集合中，只需要把新的m加入ArrayList
//						CHM.get(m.getReceiver()).add(m);
//					}
//					else{
//						//说明不存在，要把m先放入一个AL，再用put放Id和集合
//						ArrayList<Message> AL = new ArrayList<>();
//						AL.add(m);
//						CHM.put(m.getReceiver(), AL);
//						scs.setOfflineDb(CHM);//更新CHM
//					}
//					System.out.println("用户"+m.getSender()+"向用户"+m.getReceiver()+"发离线消息...");
//				}
			}
			else if(m.getMesType().equals(MesType.MESSAGE_MES_TO_ALL)) {
				//遍历通往所有用户的socket发送message(借用了userList的方法)
				String[] usersId = ServerThreadManager.usersList().split(" ");
				for(int i=0;i<usersId.length;i++) {
					ObjectOutputStream oos = new ObjectOutputStream(ServerThreadManager.getServerThread(usersId[i]).getSocket().getOutputStream());
					oos.writeObject(m);
					System.out.println("用户"+m.getSender()+"群发消息，消息传向用户："+usersId[i]);
				}
			}
			else if(m.getMesType().equals(MesType.MESSAGE_FILE_TO_ONE)) {
				//拿到通往receiver的socket发送message
				ObjectOutputStream oos = new ObjectOutputStream(ServerThreadManager.getServerThread(m.getReceiver()).getSocket().getOutputStream());
				oos.writeObject(m);
				System.out.println("用户"+m.getSender()+"向用户"+m.getReceiver()+"发文件...");
			}
			else {
				System.out.println("还没做相关处理...");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
}
