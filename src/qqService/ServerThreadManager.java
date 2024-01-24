package qqService;

import java.util.HashMap;
import java.util.Set;

public class ServerThreadManager {
	private static HashMap<String, ServerThread> serverThreads = new HashMap<>();
	//每登陆一个user就把对应持有客户端socket的线程放入该集合：
	public static void addThread(String userId, ServerThread st) {
		serverThreads.put(userId, st);
	}
	public static ServerThread getServerThread(String userId) {
		return serverThreads.get(userId);
	}
	public static String usersList() {
		String keys = "";
		Set keyset = serverThreads.keySet();
		for(Object key:keyset) {
			keys += key + " ";
		}
		return keys;
	}
	public static void removeServerThread(String userId) {
		serverThreads.remove(userId);
	}
}
