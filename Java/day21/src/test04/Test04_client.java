package test04;

import java.net.*;
import java.util.*;

public class Test04_client {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		System.out.print("1.data 2.time 3.date&time: ");
		String index = scan.next();
		
		InetAddress svAddr = InetAddress.getByName("localhost");
		DatagramSocket clSocket = new DatagramSocket(12346);
		DatagramPacket clSendPacket = new DatagramPacket(
				index.getBytes(), index.getBytes().length, svAddr, 12345);
		clSocket.send(clSendPacket);
		
		byte[] dataByte = new byte[65508];
		DatagramPacket clReceivePacket = new DatagramPacket(dataByte, dataByte.length);
		clSocket.receive(clReceivePacket);
		String receiveData = new String(clReceivePacket.getData()).trim();
		
		clSocket.close();
		
		System.out.println(receiveData);
		
	}
	
}
