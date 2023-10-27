package myjava.net;
// �Ǳ��� �ִ� ������OS�� ������ ������� �����Ѵ�.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServerSocket {
	
	//1. ���������� ���� 
	private ServerSocket ss;
	
	//2. Ŭ���̾�Ʈ�� ������ ���� 
	//private Socket socket;
	private ArrayList<ServerThread> cList;
	
	public MyServerSocket() {
		try {
			ss = new ServerSocket(9080);
			System.out.println("ServerSocket ����!");
			cList = new ArrayList<ServerThread>();
			execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void execute() {
		while(true) {
		try {
			Socket socket = ss.accept(); //blocking
			ServerThread ct = new ServerThread(socket, this);
			cList.add(ct);
			ct.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public static void main(String[] args) {
		MyServerSocket ref = new MyServerSocket();
	}
	public void sendMsg(String str1, String str2, String str3, String str4, String str5, ServerThread serverThread) {
	      //protocol�� ������ Ŭ���̾�Ʈ�� ����̴�.
	      //ex) draw/color/x/y
	        String type1 = str1; //talk, draw, enter���� ���������� �ۼ�
	        String type2 = str2; //nickName, color, all���� ���������� �ۼ�
	        String type3 = str3; //none,x,speaker
	        String type4 = str4; //none,y,say;
	        //��ó��
	         //����(response)
	        String str = "";
	        //talk/all/nickName/say
	        if(type1.equals("talk")){
	            str = "talk/none/none/"+"["+type3+":"+str5+"]"+type4;
	            System.out.println("Message :" + str);
	        }else if(type1.equals("draw")){
	            //ex) draw/color/x/y
	            str = type1+"/"+type2+"/"+type3+"/"+type4;
	        }else if(type1.equals("enter")){
	            //code
	        }
	        //��� �������� �ϼ��� str�� ��Ʈ���� ���ؼ� ������ �۾�
	        for(ServerThread c : cList){
	            c.getPw().println(str);
	         }
	   }
}