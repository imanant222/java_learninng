package qqService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Scanner;

import qqCommon.MesType;
import qqCommon.Message;

public class ServerNews extends Thread{
	public String typeIn(int len) {
	Scanner scanner = new Scanner(System.in);
	String s = scanner.next();
	if(s.length()<=len) {
		return s;
	}
	else
		return new String("输入出错！");
}
	@Override
	public void run() {
		//向所有用户推送消息
		while(true) {
			System.out.println("请输入服务器要推送的消息：");
			String news = typeIn(200);
			Message m = new Message();
			m.setMesType(MesType.MESSAGE_NEWS_TO_ALL);
			m.setSender("可爱的服务器");
			m.setContent(news);
			m.setSendTime(new Date().toString());
			System.out.println(m.getSendTime()+"\t服务器推送消息："+m.getContent());
			String[] usersId = ServerThreadManager.usersList().split(" ");
			for(int i=0; i<usersId.length; i++) {
				try {
					ObjectOutputStream oos = new ObjectOutputStream(ServerThreadManager.getServerThread(usersId[i]).getSocket().getOutputStream());
					oos.writeObject(m);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
