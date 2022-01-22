package test04;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test04_server {

	public static void main(String[] args) throws Exception {
		DatagramSocket svSocket = new DatagramSocket(12345);
		
		byte[] dataByte = new byte[65508];
		DatagramPacket svReceivePacket = new DatagramPacket(dataByte, dataByte.length);
		svSocket.receive(svReceivePacket);
		
		String svReceiveData = new String(svReceivePacket.getData()).trim();
		
		Date date = new Date();
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm:ss");
		
		String svSendData = "";
		switch(svReceiveData) {
		case "1":
			svSendData = dateSdf.format(date);
			break;
		case "2":
			svSendData = timeSdf.format(date);
			break;
		case "3":
			svSendData = dateSdf.format(date) + " " + timeSdf.format(date);
			break;
			
		default :
		}
		InetAddress clAddr = svReceivePacket.getAddress();
		DatagramPacket svSendPacket = new DatagramPacket(
				svSendData.getBytes(), svSendData.getBytes().length, clAddr, 12346);
		svSocket.send(svSendPacket);
		
		svSocket.close();
		
	}

}
