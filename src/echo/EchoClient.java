package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP= "192.168.1.60";
	private static final int SERVER_PORT = 6060;
	
	public static void main(String[] args) {
		Socket socket =null;
		Scanner scanner = new Scanner(System.in);
		
		// TODO Auto-generated method stub
		//1. Socket 생성
		 
		
		//2. 서버 연결
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			
		//3.IOstream 받아오기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);

		
		//4. 쓰기/읽기
			while(true){
				System.out.println(">>");
				String message =scanner.nextLine();
				if("exit".equals(message))
				{
					break;
				}
				//메세지 보내기
				pw.println(message);
				
				//에코 받기
				String echoMessage = br.readLine();
				
				if(echoMessage==null)
				{
					System.out.println("[client]disconnected by server");
					break;
				}
				//출력
				System.out.println("<<"+echoMessage);
				
		}
		} catch (SocketException e)
		{
			//클라이언트가 소켓을 정상적으로 닫지 못하고 좋료한 경우
			System.out.println("[server] : closed by client");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			if(socket!=null && socket.isClosed()==false)
			{
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scanner.close();
			}
		}

		}	
		

	}