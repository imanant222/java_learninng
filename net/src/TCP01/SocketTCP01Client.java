package TCP01;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTCP01Client {
	//一个能够通过传进输入流对象，从而返回字节数组的方法
	public static byte[] streamToByteArray(InputStream is) throws Exception{
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		byte[]b=new byte[1024];
		int len;
		//边读（传进来的输入流is）边写（输出流bos）：
		while((len=is.read(b))!=-1) {
			bos.write(b, 0, len);
		}
		//将读到的都转成字节数组
		byte[] array=bos.toByteArray();
		bos.close();
		return array;
	}
	
	public static void main(String[] args) throws Exception {
		// 1.创建Socket让本机和服务端端口连接
		Socket socket=new Socket(InetAddress.getLocalHost(),8888);
		
		// 2.通过字节流读取磁盘上的图片并转换成字节数组
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream("/Users/hanablack/Desktop/copy.MP4"));
		byte[] buff=streamToByteArray(bis);/*buff里已经装好图片了*/
		
		// 3.通过socket获取输出流将byte传给服务端
		OutputStream os=socket.getOutputStream();
		os.write(buff);
		
		// 4.设置结束输出的标记
		socket.shutdownOutput();

		//5.通过字符流接收服务端发来的数据
		InputStream is=socket.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		System.out.print(br.readLine());
		
		//6.关闭socket和流
		socket.close();
		bis.close();
		os.close();
		br.close();
	}

}
