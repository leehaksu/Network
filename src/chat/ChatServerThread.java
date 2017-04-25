package chat;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatServerThread extends Thread {

	private String nickname;
	private Socket socket;
	List<Writer> listWriters=null;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		int remoteHostPort = remoteAddress.getPort();
		String remoteHostAddress = remoteAddress.getAddress().getHostAddress();
		String connect ="[Server] connected from " + remoteHostAddress + " : " + remoteHostPort;
		System.out.println(connect);
		Log(connect);

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			while (true) {
				String message = br.readLine();
			if (message == null) {
					System.out.println("[server]= Client"+"연결이 끊겼습니다.");
				}
				
				String[] token = message.split(":");
				
				if ("NICKNAME".equals(token[0])) {
					dojoin(token[1], pw);
				} else if ("MESSAGE".equals(token[0])) {
					doMessage(token[1], pw);
				} else if ("QUIT".equals(token[0])) {
					QUIT(pw);
					break;
				} else {
					System.out.println("에러 : 알 수 없는 에러(" + token[0] + ")");
				}

			}
			

		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void QUIT(Writer writer) {
		// TODO Auto-generated method stub
		String data = nickname + "님이 퇴장 하였습니다.";
		broadcast(data);
		removeWriter(writer);
		Log(data);

	}

	private void doMessage(String data, Writer writer) {
		// TODO Auto-generated method stub
		broadcast(nickname+">>"+data);
		Log(data);

	}

	private void dojoin(String nickname, Writer writer) {
		// TODO Auto-generated method stub
		this.nickname = nickname;
		String data = nickname + "님이 들어오셨습니다.";
		addWriter(writer);
		broadcast(data);
		PrintWriter printWriter = (PrintWriter) writer;
		printWriter.println("join:ok");
		printWriter.flush();
		Log(data);
	}

	private void addWriter(Writer writer) {
		// TODO Auto-generated method stub
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}

	private void broadcast(String data) {

		synchronized (listWriters) {

		for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}

	}

	private void removeWriter(Writer writer) {
		// TODO Auto-generated method stub
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
	}
	private void Log(String data)
	{
		FileOutputStream fo =null;
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a" );
		String Log = "["+format.format( new Date() )+"]"+ data;
		try {
			System.out.println(Log);
			fo = new FileOutputStream("Log.txt",true);
			fo.write((Log+"\n").getBytes());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
