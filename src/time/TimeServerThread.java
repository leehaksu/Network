package time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TimeServerThread extends Thread {

	private static final int PORT = 6060;

	@Override
	public void run() {
		// TODO Auto-generated method stub

		DatagramSocket datagramsocket = null;
		try {
			// 1. DatagramSocket 생성
			datagramsocket = new DatagramSocket(PORT);

			// 2. 수신 받을 패킷 생성
			DatagramPacket dataPacket = new DatagramPacket(new byte[1024], 1024);

			// 3. 패킷을 수신
			datagramsocket.receive(dataPacket);

			// 4. 수신받은 패킷 화면 출력
			String message = new String(dataPacket.getData(), 0, dataPacket.getLength(), "utf-8");
			System.out.println(message);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (datagramsocket != null && datagramsocket.isClosed() == false) {
				datagramsocket.close();
			}

		}

	}

}
