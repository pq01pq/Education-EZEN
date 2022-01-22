
import java.net.*;

public class Test03 {
	public static void main(String[] args) throws Exception {
		InetAddress ia = InetAddress.getByName("192.168.0.255");
		String msg = "안녕, 브로드캐스트 통신이야!!";
		DatagramPacket dp = new DatagramPacket(msg.getBytes(), 
				msg.getBytes().length, ia, 12345);
		DatagramSocket ds = new DatagramSocket();
		ds.send(dp);
		ds.close();
	}
}
