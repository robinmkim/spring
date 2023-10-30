package kr.co.ictedu.mvc.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.catalina.ha.tcp.SendMessageData;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kr.co.ictedu.mvc.dto.ChatVO;

@Component
//ws://ȣ��Ʈ/chat ���ӽ�
@ServerEndpoint("/chat")
public class ChatHandler {
	private final static String PATH ="C:\\Users\\user\\git\\spring1023\\spring1025\\WebContent\\chat.txt";
	private static List<ChatVO> users = new LinkedList<>();
	private Gson gson = new Gson();

	// �������� ������ �޾Ƽ� ChatSession�� Ž���ϴ� �޼��带 �����Ѵ�.
	private ChatVO getSession(Session userSession) {
		System.out.println("1.userSession:" + userSession);
		Optional<ChatVO> data = users.stream().filter(x -> x.getSession() == userSession).findFirst();
		if (data.isPresent()) {
			return data.get();// ChatVO
		}
		// List<ChatVO>�� �������� ������ null�� ��ȯ��.
		return null;

	}

	// ���� ����� : ���ο� �����̸� ����� �ƴϸ� ������ ������ ���� ��Ų��.
	private ChatVO createSession(ChatVO msg, Session userSession) {
		// ������ �� ��������
		ChatVO session = getSession(userSession);
		if (session == null) {
			// �ν��Ͻ� ����
			session = new ChatVO();
			// ���� ����
			session.setSession(userSession);
			// user ����Ʈ�� �߰�
			users.add(session);
		}
		// id ����
		session.setId(msg.getId());
		// session ����
		return session;
	}
	 //@OnOpen: WebSocket�� ���ӵ� �� ȣ��Ǵ� �Լ��� ���Ǵ� ������̼�  -> accept()
	// @OnMessage : �������κ��� �޽����� ���� ȣ��Ǵ� �Լ� -> readLine()
	//  @OnClose : WebSocket�� �ݱ�� ȣ��Ǵ� �Լ�
	@OnOpen
	public void handleOpen(Session userSession) {
		System.out.println("WebSocket�� ����");
		System.out.println("id:" + userSession.getId());
	}
	@OnMessage
	public void handleMessage(String message, Session userSession) {
		//json type
		 ChatVO msg = gson.fromJson(message, ChatVO.class);
		 System.out.println("Status:"+msg.getState());
		 //status�� 0�� �Ǹ� ���� ���� , 1 �� �Ǹ� �Ϲ� �޼��� �Ǵ�. ��������: 0-> enter , 1-> chat
		 if(msg.getState() == 0) {
			 createSession(msg, userSession);
			 //File�� ������ ������ �о����
			 try {
				userSession.getBasicRemote().sendText(readFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else if(msg.getState() == 1) {
			// ���� Ȯ�� �ϱ�
			 if (getSession(userSession) != null) {
				 // �޼����� ����
				 sendMessageData(msg.getId(),msg.getValue());
				 //���Ͽ� ����
				 saveFile(msg.getId(),msg.getValue());
			 }
		 }
	}
	
	 private void saveFile(String id, String message) {
		// �޽��� ����
		String sendMessage = id + ":" + message + "\n";
		try (PrintWriter pw = new PrintWriter(new FileOutputStream(PATH),true);){
			pw.println(sendMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendMessageData(String id, String message) {
		 // �޽��� ����
		 String sendMessage = id + ":" + message + "\n";
		 
		 // ��ε� ĳ����
		 for(ChatVO user: users) {
			// �޽��� ����
		    try {
				user.getSession().getBasicRemote().sendText(sendMessage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
	}

	private String readFile() {
		File file = new File(PATH);
		if(!file.exists()) {
			return "";
		}
		try (BufferedReader rd = new BufferedReader(new FileReader(file));){
			StringBuilder sb = new StringBuilder();
			String msg = null;
			while((msg = rd.readLine()) != null) {
				sb.append(msg);
			}
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@OnClose
	 public void handleClose(Session userSession) {
		Optional<ChatVO> session = users.stream().filter(x -> x.getSession() == userSession).findFirst();
		if(session.isPresent()) {
			//List���� �ش� ������ �����.
			users.remove(session.get());
			System.out.println("userSize:"+users.size());
			if(users.size() == 0) {
				File file = new File(PATH);
				file.delete();
			}
		}
	 }
	
}