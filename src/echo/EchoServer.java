package echo;


import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private static final int SERVER_PORT = 6060;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket sc = null;
		try {
			// 1. 서버 소켓 생성
			sc = new ServerSocket();
			// 2. 바인딩(binding)
			InetAddress ia = InetAddress.getLocalHost();
			String localhostAddress = ia.getHostAddress();
			InetSocketAddress inetSocketAddress = new InetSocketAddress(localhostAddress, SERVER_PORT);
			sc.bind(inetSocketAddress);
			System.out.println("[Server"+ Thread.currentThread().getId()+"] binding " + localhostAddress + ":" + SERVER_PORT);

			while(true)
				{
			// 3. accept(연결 요청을 기다림)
			Socket socket = sc.accept(); // blocking됨
			Thread thread = new EchoServerReceivedThread(socket);
			thread.start();
				}
		} catch (Exception e) {

		}

	}
}
