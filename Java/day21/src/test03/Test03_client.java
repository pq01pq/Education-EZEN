package test03;

import java.io.*;
import java.net.*;

public class Test03_client {
	
	public static void main(String[] args) {
		
		String data = "졸려 자고싶어";
		
		try {
			InetAddress ia = InetAddress.getByName("localhost");
			DatagramPacket dp = new DatagramPacket(data.getBytes(), data.getBytes().length, ia, 12345);
			DatagramSocket ds = new DatagramSocket(12346);
			ds.send(dp);
			DatagramPacket dp2 = new DatagramPacket(new byte[65508], 65508);
			ds.receive(dp2);
			ds.close();
			InetAddress ia2 = dp2.getAddress();
			String data2 = new String(dp2.getData()).trim();
			System.out.println("보낸이 : " + ia2.getHostAddress());
			System.out.println("보낸 내용 : " + data2);
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
