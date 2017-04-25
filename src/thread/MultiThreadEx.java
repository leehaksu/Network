package thread;

import java.util.ArrayList;

public class MultiThreadEx {

	public static void main(String[] args) {
		ArrayList list =new ArrayList(3);
		Thread th1 = new AlphabetThread(list);
		Thread th2 = new DigitThread(list);
		Thread th3 = new DigitThread(list);
		Thread th4 = new Thread(new UpperCaseAlphabetThread());
		th1.start();
		th2.start();
		th3.start();
		th4.start();

		


	}

}
