package thread;

import java.util.List;

public class DigitThread extends Thread {
	private List list;
	
	

	public DigitThread(List list) {
		super();
		this.list = list;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 9; i++) {
			System.out.print(i);
			synchronized(list)
			{
			list.add(i);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
