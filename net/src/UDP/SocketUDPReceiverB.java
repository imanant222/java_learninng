package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketUDPReceiverB {

	public static void main(String[] args) throws Exception {
//		System.out.println(InetAddress.getLocalHost());//Natties-MBP.local/127.0.0.1
		/*发送数据*/
		// 1.创建socket以及接收数据的端口
		DatagramSocket socket=new DatagramSocket(9998);
		// 2.将要发送的字符串装成字节数组并放进packet数据包，并且指定发送端IP和接收端端口
		byte [] buf="明天吃火锅...".getBytes();
		DatagramPacket packet=new DatagramPacket(buf,buf.length,InetAddress.getByName("127.0.0.1"),9999);
		// 3.通过socket发送包
		socket.send(packet);
		
//		/*接收数据*/
		byte[] buf1=new byte[1024];
		DatagramPacket packet1=new DatagramPacket(buf,buf.length);
		socket.receive(packet1);
		String s=new String(packet.getData(),0,packet.getLength());
		System.out.println(s);
		
		/*关闭socket*/
		socket.close();
	}

}
