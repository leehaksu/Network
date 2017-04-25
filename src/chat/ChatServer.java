package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	
	private static final int PORT=6060;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//wirter를 저장할 공간 생성
		List<Writer> listWriters = new ArrayList<Writer>();
		//서버 소켓 생성
		ServerSocket serversocket=null;
		Socket socket=null;
		
		try {
			
			//서버 소켓 바인드
			serversocket =new ServerSocket();
			InetAddress inet = InetAddress.getLocalHost();
			String ip = inet.getHostAddress();
			InetSocketAddress inetsocket = new InetSocketAddress(ip, PORT);
			serversocket.bind(inetsocket);
			System.out.println("[server] bind 완료 "+inetsocket.getAddress()+":"+inetsocket.getPort());
			
			while(true)
			{
				socket = serversocket.accept();//blocking
				new ChatServerThread(socket,listWriters).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
