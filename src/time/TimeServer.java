package time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeServer {
	private static int PORT=6060;
	
	public static void main(String [] args)
	{	
		DatagramSocket datagramsocket =null;
		
		
		try {
			//1. Datagram Socket 생성
			datagramsocket = new DatagramSocket(PORT);
			
			//2. 수신 패킷을 생성
			DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
			
			//3. 수신대기
			datagramsocket.receive(receivePacket);
			
			//4. 데이터 수신
			String message = new String(receivePacket.getData(),0,receivePacket.getLength(),"utf-8");
			
			
			//5. 에코잉
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a" );
			String data = format.format( new Date() );
			System.out.println("[server] : "+"["+data+"]"+ message);
			
			String temp_sendData = "["+data+"] "+ message;
			
			byte [] sendData = temp_sendData.getBytes("utf-8");
			
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,receivePacket.getSocketAddress());
			
			datagramsocket.send(sendPacket);

			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally 
		{
			if(datagramsocket!=null && datagramsocket.isClosed()==false)
			{
				datagramsocket.close();
			}
		}
		
	}

}
