package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ChatClientThread extends Thread {
	private BufferedReader br;

	public ChatClientThread(BufferedReader br) {
		this.br = br;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		
				while(true)
				{
					String message = br.readLine();
					if("join:ok".equals(message))
					{
						
					}else
					{
						System.out.println(message);
					}
				}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
