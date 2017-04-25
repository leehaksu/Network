package util;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.SynchronousQueue;

public class NsLookup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			while (true) {
				System.out.print(">");
				String str = sc.nextLine();
				if ("exit".equals(str)) {
					break;
				} else {
					InetAddress[] addresses = InetAddress.getAllByName(str);

					for (int i = 0; i < addresses.length; i++) {

						String[] str1 = addresses[i].toString().split("/");
						for (int j = 0; j < str1.length; j++) {
							if (j < 1) {
								System.out.print(str1[j] + ": ");
							} else {
								System.out.println(str1[j]);
							}
						}
					}
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			sc.close();
		}

	}

}
