package time;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class TimeClient {
	
	private static final String SERVER_IP="192.168.1.60";
	private static final int SERVER_PORT = 6060;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatagramSocket sendSocket =null;
		Scanner scanner = new Scanner(System.in);		
		
		try {
			//1. Datagram socket 생성
			 sendSocket = new DatagramSocket();
			 
			 //2. 키보드에서 입력받는다.
			 System.out.print(">>");
			 String message = scanner.nextLine();
			 if("exit".equals(message))
			 {
				 return;
			 }
			 
			 //3.전송 패킷 생성 
			 byte [] sendData = message.getBytes("utf-8");
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,new InetSocketAddress(SERVER_IP,SERVER_PORT));
			
			//4.패킷 전송
			sendSocket.send(sendPacket);
			
			//5. 패킷 수신
			DatagramPacket receivePacket = new DatagramPacket(new byte[1024],1024);
			sendSocket.receive(receivePacket);//blocking
			
			//5. 화면 출력
			
			message = new String(receivePacket.getData(),0,receivePacket.getLength(),"utf-8");
			System.out.println(message);
			
			/*String [] messages = message.split("/");
			for(int i=0;i<messages.length;i++)
			{
				System.out.print(message);
			}*/
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally	
		{
			if(sendSocket!=null && sendSocket.isClosed()==false)
			{
				sendSocket.close();
			}
			scanner.close();
	
		}

	}

}
