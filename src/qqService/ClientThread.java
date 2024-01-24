package qqService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import qqCommon.MesType;
import qqCommon.Message;

public class ClientThread extends Thread{
	Socket socket;
	String userId;
	public ClientThread(Socket socket, String userId) {
		this.socket = socket;
		this.userId = userId;
	}
	public Socket getSocket() {
		return socket;
	}
	@Override
	public void run() {
		while(true) {
			try {
				//保持读取从服务端发来的数据
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message m = (Message)ois.readObject();
				
				//根据消息类型作出相应处理
				if(m.getMesType().equals(MesType.MESSAGE_RET_ONLINE_USER)) {
					String[] onlineUsers = m.getContent().split(" ");
					System.out.println("*･゜ﾟ･*:.｡..｡.:*･'当前在线用户列表当前在线用户列表'･*:.｡. .｡.:*･゜ﾟ･*");
					for(int i=0;i<onlineUsers.length;i++) {
						System.out.println("用户："+onlineUsers[i]);
					}
				}
				else if(m.getMesType().equals(MesType.MESSAGE_MES_TO_ONE)) {
					System.out.println("\n"+m.getSendTime()+"\t"+m.getSender()+"对我说:"+m.getContent());
				}
				else if(m.getMesType().equals(MesType.MESSAGE_MES_TO_ALL)) {
					if(!(m.getSender().equals(userId))) {
						System.out.println("\n"+m.getSendTime()+"\t"+m.getSender()+"对大家说："+m.getContent());
					}
				}
				else if(m.getMesType().equals(MesType.MESSAGE_FILE_TO_ONE)) {
					System.out.println("\n"+m.getSendTime()+"\t"+m.getSender()+"向我发送文件到:"+m.getDest());
					FileOutputStream fos = new FileOutputStream(m.getDest());
					fos.write(m.getFileBytes());
					fos.close();
					System.out.println("文件保存成功...");
				}
				else if(m.getMesType().equals(MesType.MESSAGE_NEWS_TO_ALL)) {
					System.out.println("\n"+m.getSendTime()+"\t"+m.getSender()+"推送📢:"+m.getContent());
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
