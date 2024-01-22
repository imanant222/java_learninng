package TCP02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPSocketClient {

	public static void main(String[] args) throws IOException {
		Socket socket=new Socket(InetAddress.getLocalHost(),9999);
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入名字：");
		String s=scanner.next();
		bw.write("s");
		socket.shutdownOutput();
		InputStream is=socket.getInputStream();
		byte[] buf=new byte[1024];
		int readLen=0;
		FileOutputStream fos=new FileOutputStream(new File ("/Users/hanablack/Desktop/net/src/copy.PNG"));
		while((readLen=is.read())!=-1) {
			fos.write(is.readNBytes(buf, 0, readLen));
		}
		System.out.println("图片保存完毕");
		bw.close();
		fos.close();
		socket.close();
	}

}
