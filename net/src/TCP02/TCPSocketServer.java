package TCP02;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSocketServer {
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
		ServerSocket serverSocket=new ServerSocket(9999);
		Socket socket=serverSocket.accept();
		FileInputStream fis=new FileInputStream("/Users/hanablack/Desktop/高山流水.PNG");
		FileInputStream fis1=new FileInputStream("/Users/hanablack/Desktop/seawind.PNG");
		byte[] data=streamToByteArray(fis);
		byte[] data1=streamToByteArray(fis1);
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		OutputStream os=socket.getOutputStream();
		String s=br.readLine();
//		System.out.println("用户希望获取："+s);
		if("高山流水".equals(s)) {
			os.write(data);
			socket.shutdownOutput();
		}
		else {
			os.write(data1);
			socket.shutdownOutput();
		}
		br.close();
		os.close();
		fis.close();
		fis1.close();
		socket.close();
		serverSocket.close();
	}

}
