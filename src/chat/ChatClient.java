package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "192.168.1.60";
	private static final int SERVER_PORT = 6060;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. 소켓 생성
		Socket socket = null;
		Scanner scanner = new Scanner(System.in);

		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
			
			System.out.print("사용할 닉네임을 적어주세요 : ");
			String message = "NICKNAME"+":"+scanner.nextLine();
			pw.println(message);
			pw.flush();
			while(true)
			{
					new ChatClientThread(br).start();
			     	String message2 = scanner.nextLine();
			      if("quit".equals(message) == true ) {
			    	  message2 = "QUIT"+":"+message2;
			      } else {
			    	  message2 = "MESSAGE"+":"+message2;
			      }
			      pw.println(message2);
		    	  pw.flush();
		    	 
		}
			
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
					socket.close();
					scanner.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
