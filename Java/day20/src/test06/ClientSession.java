package test06;

import java.io.*;
import java.net.*;

public class ClientSession implements Runnable {
	
	private String name;
	private Server server;
	private Socket socket;
	private ByteArrayInputStream bais;
	private ByteArrayOutputStream baos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public ClientSession(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
//		try {
			
			
			new Thread(this).start();	// �޼����� �ޱ� ����
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void run() {
		Message request = recieveMessage();	// 2) Ŭ���̾�Ʈ�κ��� ���� ��û �޼����� ����
		System.out.println("��û Ȯ��");
		System.out.println(request.getName());
		System.out.println(request.getMessage());
		name = request.getName();
		Message response = request;
//		for(ClientSession clSession : server.getClSessions()) {
//			if(clSession.getName().equals(name)) {
//				response.setMessage("deny");
//				sendMessage(response);	// 3) ��û ���� �޼����� ����
//				return;
//			}
//		}
		response.setMessage("permit");
		sendMessage(response);	// 3) ��û �㰡 �޼����� ����
		System.out.println("���� ����");
		response.setMessage("���� �����ϼ̽��ϴ�.\n");
		server.addMessage(response);
		
		while(true) {
			server.addMessage(recieveMessage());	// �޼��� ���� ������
		}
		
	}
	
	public void sendMessage(Message message) {
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(new BufferedOutputStream(baos));
			oos.writeObject(message);
			oos.flush();
			socket.getOutputStream().write(baos.toByteArray());
//			socket.getOutputStream().flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Message recieveMessage() {
		try {
			byte[] data = new byte[65508];
			socket.getInputStream().read(data);
			bais = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(new BufferedInputStream(bais));
			Message message = (Message)ois.readObject();
			return message;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public ObjectOutputStream getOos() {
		return oos;
	}
	
	public String getName() {
		return name;
	}
	
}
