package test05;

import javax.swing.*;
import java.net.*;

public class Test05_server extends JFrame {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings({ "resource", "unused" })
	public Test05_server() throws Exception {
		DatagramSocket hostSocket = new DatagramSocket(12345);
		DatagramPacket fileNamePacket = new DatagramPacket(new byte[65508], 65508);
		hostSocket.receive(fileNamePacket);
		String fileName = fileNamePacket.getData().toString().trim();
		
	}

	public static void main(String[] args) throws Exception {
		new Test05_server();
	}

}
