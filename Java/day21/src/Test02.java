
import java.net.*;

// 클라이언트
public class Test02 {

	public static void main(String[] args) throws Exception {
		byte[] by = new byte[65508];
		DatagramPacket dp = new DatagramPacket(by, by.length);
		DatagramSocket ds = new DatagramSocket(12345);
		ds.receive(dp);
		ds.close();
		
		InetAddress ia = dp.getAddress();
		String data = new String(dp.getData()).trim();
		
		System.out.println("보낸이 : " + ia.getHostAddress());
		System.out.println("보낸 내용 : " + data);
	}

}
