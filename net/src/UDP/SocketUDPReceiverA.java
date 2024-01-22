package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SocketUDPReceiverA {

	public static void main(String[] args) throws Exception {
		/*接收数据*/
		// 1.创建socket并指定接收数据的端口
		DatagramSocket socket=new DatagramSocket(9999);
		// 2.创建指定大小的字节数组来接收数据（UDP传数据时，数据包一次最多装64k）
		byte[] buf=new byte[1024];
		// 3.创建数据包等待传过来的数据进行接收，并通过socket接收数据
		DatagramPacket packet=new DatagramPacket(buf,buf.length);
		socket.receive(packet);
		// 4.通过packet的方法将数据得到并转换成字符串
		String s=new String(packet.getData(),0,packet.getLength());
		System.out.println(s);
		
		/*发送数据*/
		byte[] buf1="好的，明天见...".getBytes();
		DatagramPacket packet1=new DatagramPacket(buf1,buf1.length,InetAddress.getByName("127.0.0.1"),9998);
		socket.send(packet1);
		
		/*关闭socket*/
		socket.close();
	}

}
