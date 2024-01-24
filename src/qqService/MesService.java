package qqService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import qqCommon.MesType;
import qqCommon.Message;

public class MesService {
	
	public void sendToOne(String sender, String receiver, String content) throws IOException {
			Message m = new Message();
			m.setMesType(MesType.MESSAGE_MES_TO_ONE);
			m.setSender(sender);
			m.setReceiver(receiver);
			m.setContent(content);
			m.setSendTime(new Date().toString());
			ObjectOutputStream oos = new ObjectOutputStream(ClientThreadManager.getClientThread(sender).getSocket().getOutputStream());
			oos.writeObject(m);
			System.out.println(m.getSendTime()+"\t我对"+receiver+"说:"+content);
			//感觉这样更像聊天窗口——>"@"+receiver+" "+content
	}
	public void sendToAll(String sender, String content) throws IOException {
		Message m = new Message();
		m.setMesType(MesType.MESSAGE_MES_TO_ALL);
		m.setSender(sender);
		m.setContent(content);
		m.setSendTime(new Date().toString());
		ObjectOutputStream oos = new ObjectOutputStream(ClientThreadManager.getClientThread(sender).getSocket().getOutputStream());
		oos.writeObject(m);
		System.out.println(m.getSendTime()+"\t我对大家说:"+content);
	}
}
