package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	
	private static final int SERVER_PORT = 5050;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket sc=null;
		try {
			//1. 서버 소켓 생성
			 sc = new ServerSocket();
			//2. 바인딩(binding)
			 InetAddress ia = InetAddress.getLocalHost();
			 String localhostAddress = ia.getHostAddress();
			 InetSocketAddress inetSocketAddress = new InetSocketAddress(localhostAddress,SERVER_PORT);
			 sc.bind(inetSocketAddress);
			 System.out.println("[Server] binding "+ localhostAddress+":"+SERVER_PORT);
			 
			 //3. accept(연결 요청을 기다림)
			 Socket socket=sc.accept(); // blocking됨
			 
			 //4. 연결 성공
			 InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			 int remoteHostPort =remoteAddress.getPort();
			 String remoteHostAddress= remoteAddress.getAddress().getHostAddress();
			 System.out.println("[Server] connected from " + remoteHostAddress +" : "+ remoteHostPort);
			 try{
				 // 5. ios stream 받아오기
			  InputStream is = socket.getInputStream();
			  OutputStream os = socket.getOutputStream();
			  // 6. 데이터 읽기
			  byte [] buffer = new byte[256];
			  int readByteCount = is.read(buffer);//blocking
			  if(readByteCount<=-1)
			  {
				  //클라이언트가 소켓을 닫은 경우
				  System.out.println("[Sever] disconnected by client");
				  return;
			  }
			  String data = new String(buffer,0,readByteCount,"utf-8");
			  System.out.println("[server] received :"+data);
			  
			  //7. 데이터 쓰기
			  os.write(data.getBytes("utf-8"));
			 }catch(IOException e)
			 {
				 e.printStackTrace();
			 }finally
			 {
				 if(socket !=null && socket.isClosed()==false)
				 {
					 socket.close(); 
				 }
				
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try{
			//자월 정리
			if(sc !=null && sc.isClosed()==false)
			{
				sc.close();
				
			}
			}catch(IOException e)
			{
				                                                                                                                                                                                                                             
			}
			
		}
		
		
		

	}

}
