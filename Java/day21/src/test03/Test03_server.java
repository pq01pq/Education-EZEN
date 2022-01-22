package test03;

import java.io.*;
import java.net.*;

public class Test03_server {

	public static void main(String[] args) {
		
		byte[] by = new byte[65508];
		
		try {
			DatagramPacket dp = new DatagramPacket(by, by.length);
			DatagramSocket ds = new DatagramSocket(12345);
			ds.receive(dp);
			InetAddress ia = dp.getAddress();
			String data = new String(dp.getData()).trim();
			DatagramPacket dp2 = new DatagramPacket(data.getBytes(), data.getBytes().length, ia, 12346);
			ds.send(dp2);
			ds.close();
			System.out.println("보낸이 : " + ia.getHostAddress());
			System.out.println("보낸 내용 : " + data);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
