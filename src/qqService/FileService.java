package qqService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import qqCommon.MesType;
import qqCommon.Message;

public class FileService {
	
	public void sendFile(String sender, String receiver, String src, String dest ) throws IOException {
		//获取源文件，指定接收客户端对象，发送数据给服务端
		Message m = new Message();
		byte[] fileBytes = new byte[(int)new File(src).length()];
		FileInputStream fis = new FileInputStream(src);
		try {
			fis.read(fileBytes);
			m.setFileBytes(fileBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		m.setMesType(MesType.MESSAGE_FILE_TO_ONE);
		m.setSender(sender);
		m.setReceiver(receiver);
		m.setSrc(src);
		m.setDest(dest);
		m.setSendTime(new Date().toString());
		ObjectOutputStream oos = new ObjectOutputStream(ClientThreadManager.getClientThread(sender).getSocket().getOutputStream());
		oos.writeObject(m);
		System.out.println(m.getSendTime()+"\t我向"+receiver+"发送文件:"+src+"到"+dest);
	}	
	
}
