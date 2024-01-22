package tcpHomework;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
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
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket socket =	serverSocket.accept();
		InputStream is = socket.getInputStream();
		byte[] buf=new byte[1024];
		int readLen = 0;
		String recName = "";
		while((readLen=is.read(buf))!=-1) {
			recName += new String(buf, 0, readLen);
		}
		String fileName = " ";
		if (recName.equals("高山流水")){
			fileName="高山流水.PNG";
		}
		else {
			fileName = "无名.PNG";
		}
		System.out.println("文件名称："+fileName);
		FileInputStream fis=new FileInputStream("/Users/hanablack/Desktop/"+fileName);
		OutputStream os = socket.getOutputStream();
		os.write(streamToByteArray(fis));
		socket.shutdownOutput();
		System.out.println("服务端输出完毕...");
		is.close();
		fis.close();
		os.close();
		socket.close();
		serverSocket.close();
	}

}
