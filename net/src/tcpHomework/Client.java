package tcpHomework;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Socket socket = new Socket(InetAddress.getLocalHost(),8888);
		OutputStream os = socket.getOutputStream();
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入名字：");
		String file=scanner.next();
		os.write(file.getBytes());
		socket.shutdownOutput();
		InputStream is = socket.getInputStream();
		int readLen = 0;
		byte[] buf = new byte[1024];
		FileOutputStream fos = new FileOutputStream("/Users/hanablack/Desktop/net/copy.PNG");
		while((readLen=is.read(buf))!=-1) {
			fos.write(buf, 0, readLen);
		}
		System.out.println("图片接收完毕...");
		fos.close();
		is.close();
		os.close();
		socket.close();
	}

}
