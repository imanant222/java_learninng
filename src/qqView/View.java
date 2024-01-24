package qqView;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import qqService.FileService;
import qqService.MesService;
import qqService.UserLogService;

public class View {
	boolean loop=true;
	UserLogService us = new UserLogService();
	MesService ms = new MesService();
	FileService fs = new FileService();
	
	public String typeIn(int len) {
	Scanner scanner = new Scanner(System.in);
	String s = scanner.next();
	if(s.length()<=len) {
		return s;
	}
	else
		return new String("输入出错！");
}
	
	public void mainMenu() throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
		while(loop) {
			System.out.println("===============欢迎登陆===============");
			System.out.println("\t\t1 登陆系统");
			System.out.println("\t\t9 退出系统");
			System.out.print("\t\t请输入您的选择：");
			String key = typeIn(1);
			switch(key) {
			case "1":
				System.out.print("请输入用户号：");
				String userId = typeIn(50);
				System.out.print("请输入密 码：");
				String pwd = typeIn(50);
				if(us.checkUser(userId, pwd)) {
					System.out.println("***************欢迎用户"+userId+"登陆系统o(≧v≦)o***************");
					while(loop) {
					Thread.sleep(500);
					System.out.println("===============网络通信系统二级菜单（用户"+userId+"）===============");
					System.out.println("\t\t1 显示在线用户列表息 ʕ •ᴥ•ʔ");
					System.out.println("\t\t2 私发消息 (=´∀｀)人(´∀｀=)");
					System.out.println("\t\t3 群发消息 ♪(*^^)o∀*∀o(^^*)♪");
					System.out.println("\t\t4 发送文件 ♪(´ε｀ )");
					System.out.println("\t\t9 退出系统 ε=ε=ε=ε=ε=ε=┌(;￣◇￣)┘");//退出系统操作的话直接systemexit0就能直接结束进程，进程结束了所有的线程都自动退出。
					System.out.print("\t\t请输入您的选择：");
					String key1 = typeIn(1);
					switch(key1) {
						case "1":
							us.getOnlineUser(userId);
							break;
						case "2":
							System.out.print("请输入消息发送对象：");
							String receiverId = typeIn(50);
							System.out.print("请输入消息内容：");
							String content = typeIn(200);
							ms.sendToOne(userId, receiverId, content);
							break;
						case "3":
							System.out.println("请输入消息内容：");
							String content2 = typeIn(200);
							ms.sendToAll(userId, content2);
							break;
						case "4":
							System.out.print("请输入文件发送对象：");
							String receiverId2 = typeIn(50);
							System.out.print("请输入源文件地址：");
							String src = typeIn(200);
							System.out.print("请输入对方文件存放地址：");
							String dest = typeIn(200);
							fs.sendFile(userId, receiverId2, src, dest);
							break;
						case "9":
							System.out.println("客户端退出系统...");
							loop=false;
							us.userExit(userId);
							break;
				}
				}
			}
			else {
				System.out.println("登陆失败！");
			}
				break;
			case "9":
				loop=false;
				System.out.println("退出系统成功～");
				break;
			}
		}
}
	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
		new View().mainMenu();
	}

}
