package qqService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import qqCommon.MesType;
import qqCommon.Message;
import qqCommon.User;

/*
 * 要求：该类用于完成用户的登陆验证并开启线程保持接收message对象流（线程类另写，持有socket以接收message）
 * 方法：创建服务端，循环接收User对象流，因为不断有客户登陆系统，要确保每一次user数据都被接收到，所以用循环
 */
public class ServerCheckService {
	private ServerSocket ss = null;
	ServerNews sn;
	
	private static ConcurrentHashMap<String, ArrayList<Message>> offlineDb= new ConcurrentHashMap<>();
	public static ConcurrentHashMap<String, ArrayList<Message>> getOfflineDb() {
		return offlineDb;
	}
	public static void setOfflineDb(ConcurrentHashMap<String, ArrayList<Message>> offlineDb) {
		ServerCheckService.offlineDb = offlineDb;
	}

	private HashMap<String, String> users = new HashMap<>(); 
	
	public ServerCheckService() {
		users.put("100", "125521");
		users.put("200", "125521");
		users.put("300", "125521");
		users.put("400", "125521");
		users.put("500", "125521");
		try {
			ss = new ServerSocket(8888);
		System.out.println("服务端在8888端口保持接收user对象流并进行验证...");
		ServerNews sn = new ServerNews();
		sn.start();
		while(true) {
			//没接上就阻塞，接上就开始验证user：
			Socket socket = ss.accept();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			User u = (User)ois.readObject();
			Message m = new Message();
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			if(users.containsKey(u.getUserId())) {
				if(u.getPasswd().equals(users.get(u.getUserId()))){
					//返回登陆成功的消息
					m.setMesType(MesType.MESSAGE_LOGIN_SUCCEED);
					oos.writeObject(m);
					
//					//返回离线消息
//					if(offlineDb.containsKey(u.getUserId())) {
//						ArrayList AL = offlineDb.get(u.getUserId());
//						for(int i = 0; i<AL.size(); i++) {
//							oos.writeObject(AL.get(i));
//						}
//					}
					
					//把该用户对应的服务端线程启动并加入到线程集合
					ServerThread st = new ServerThread(socket, u.getUserId());
					st.start();
					ServerThreadManager.addThread(u.getUserId(), st);
					
				}
				else {
					m.setMesType(MesType.MESSAGE_LOGIN_FAIL);
					oos.writeObject(m);
					System.out.println("密码不正确！");
					socket.close();
				}
			}
			else {
				m.setMesType(MesType.MESSAGE_LOGIN_FAIL);
				oos.writeObject(m);
				System.out.println("用户名不存在！");
				socket.close();
			}
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	}
