package echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoServerReceivedThread extends Thread {
	private Socket socket;
	
	
	public EchoServerReceivedThread(Socket socket) {
		this.socket = socket;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		int remoteHostPort = remoteAddress.getPort();
		String remoteHostAddress = remoteAddress.getAddress().getHostAddress();
		System.out.println("[Server] connected from " + remoteHostAddress + " : " + remoteHostPort);
		// 5. ios stream 받아오기
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
			
			while(true)
			{
				String message = br.readLine();//blocking
				if(message==null)
				{
					//클라ㅣ언트가 소켓을 닫음
					consoleLog("disconnected by client");
					break;
				}
					consoleLog(message);	
				//데이터 쓰기
				pw.println(message);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}
	
	
	private void consoleLog(String message)
	{
		System.out.println("[Server: "+getId()+"]"+ message);
	}

}
