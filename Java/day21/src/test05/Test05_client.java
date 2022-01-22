package test05;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Test05_client extends JFrame {
	private static final long serialVersionUID = 1L;

	public Test05_client() throws Exception {
		JFileChooser jfc = new JFileChooser(".");
		jfc.showOpenDialog(this);
		File file = jfc.getSelectedFile();
		String fileName = file.getName();
		
		InetAddress hostAddr = InetAddress.getByName("localhost");
		DatagramSocket guestSocket = new DatagramSocket(12346);
		DatagramPacket fileNamePacket = new DatagramPacket(
				fileName.getBytes(), fileName.getBytes().length, hostAddr, 12345);
		
		guestSocket.send(fileNamePacket);
		
		guestSocket.close();
	}

	public static void main(String[] args) throws Exception {
		new Test05_client();
	}

}
