package myjava.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ServerThread extends Thread {
	private Socket socket; // ����� ������ ������ ��������
	private MyServerSocket server; // ��ε� ĳ����
	private PrintWriter pw;
	private BufferedReader in;
	private String reip;
	private Map<String, String> map;

	public ServerThread(Socket socket, MyServerSocket server) {
		this.socket = socket;
		this.server = server;
		reip = socket.getInetAddress().getHostAddress();
		map = new HashMap<String, String>();
		map.put("�ȳ�", "�ݰ���.");
		map.put("�ȳ��ϼ���", "�ݰ�����");
		map.put("���ݸ�", "�� �̾���!");
		map.put("������", "�� �˰ڽ��ϴ�.");
		map.put("������", "������ �弼��!");
		try {
			pw = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String msg = in.readLine();
				System.out.println("Client Msg:" + msg);
				transMsg(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void transMsg(String msg) {
		// �ɽ���
		// String serMsg = map.get(msg);
		// ex) chat/����
		// draw/color/x/y
		StringTokenizer stn = new StringTokenizer(msg, "/");
		String str1 = stn.nextToken();
		String str2 = stn.nextToken();
		String str3 = stn.nextToken();
		String str4 = stn.nextToken();
		String str5 = reip;
		server.sendMsg(str1, str2, str3, str4,str5, this);
	}

	public PrintWriter getPw() {
		return pw;
	}

	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}
	
	
}