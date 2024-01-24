package qqService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import qqCommon.MesType;
import qqCommon.Message;
import qqCommon.User;
/*
 * 该类用于完成用户登陆注册（通过传入对象，设置User的信息，并通过对象流，让客户端socket向服务端socket发送User信息以验证用户密码;
 * 						通过读取服务端发来的message，判断type来返回真假）
 */
public class UserLogService {
	private Socket socket;
	private User u;
	public boolean checkUser(String userId, String pwd) throws UnknownHostException, IOException, ClassNotFoundException {
		boolean userExist = false;
		//发送User信息流
		u = new User(userId, pwd);
		socket =  new Socket(InetAddress.getLocalHost(), 8888);
		ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(u);
		
		//接收Message信息流
		ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
		Message m = (Message)ois.readObject();
		if(m.getMesType().equals(MesType.MESSAGE_LOGIN_SUCCEED)) {
			//创建一个线程类来保持接收从服务端传来的数据
			ClientThread clientThread = new ClientThread(socket, u.getUserId());
			clientThread.start();
			ClientThreadManager.addThread(userId, clientThread);
			userExist = true;
		}
		else if(m.getMesType().equals(MesType.MESSAGE_LOGIN_FAIL)) {
			socket.close();
		}
		return userExist;
	}
	//该方法用于拉取在线用户，主要负责发送对应请求给服务端
	public void getOnlineUser(String userId) throws IOException {
		Message m2 = new Message();
		m2.setSender(userId);
		m2.setMesType(MesType.MESSAGE_GET_ONLINE_USER);
		ObjectOutputStream oos=new ObjectOutputStream(ClientThreadManager.getClientThread(userId).getSocket().getOutputStream());
		oos.writeObject(m2);
//		System.out.println("显示在线用户列表的请求已发送至服务端...");
	}
	//该方法用于结束客户端的进程（所有线程自动退出）
	public void userExit(String userId) throws IOException {
		//发送退出系统的通知给服务端，并通过systemexit0结束进程
		Message m3 =  new Message();
		m3.setSender(userId);
		m3.setMesType(MesType.MESSAGE_CLIENT_EXIT);
		ObjectOutputStream oos=new ObjectOutputStream(ClientThreadManager.getClientThread(userId).getSocket().getOutputStream());
		oos.writeObject(m3);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
