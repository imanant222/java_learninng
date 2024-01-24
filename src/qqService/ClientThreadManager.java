package qqService;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import qqCommon.Message;

public class ClientThreadManager{
	static HashMap<String, ClientThread> clientThreads = new HashMap<>();
	//每登陆一个user就把对应持有客户端socket的线程放入该集合：
	public static void addThread(String userId, ClientThread ct) {
		clientThreads.put(userId, ct);
	}
	public static ClientThread getClientThread(String userId) {
		return clientThreads.get(userId);
	}
}
