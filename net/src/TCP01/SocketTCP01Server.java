package TCP01;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCP01Server {

	public static void main(String[] args) throws IOException {
		// 1.创建端口
		ServerSocket serverSocket=new ServerSocket(8888);
		
		// 2.等待接收
		Socket socket=serverSocket.accept();
		
		//	3.若接收到客户端的图片，用socket得到输入流读取图片，并用输出流保存在src下
		BufferedInputStream bis=new BufferedInputStream(socket.getInputStream());
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("/Users/hanablack/Desktop/net/src/copy1.MP4"));
		byte b[]=new byte[1024];
		int readLen=0;
		while((readLen=bis.read(b))!=-1) {
			bos.write(b, 0, readLen);
		}
		// 4.通过字符流回复客户端
		OutputStream os=socket.getOutputStream();
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
		bw.write("图片保存完毕...");
		
		// **设置结束输出的标记(必须用readLine读取才行)
		bw.newLine();
		// **必须手动刷新才能把数据写入通道
		bw.flush();
		
		//5.关闭serversocket,socket和流
		serverSocket.close();
		socket.close();
		bw.close();
		bis.close();
		bos.close();
	}			
}
