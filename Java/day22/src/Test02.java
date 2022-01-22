
import java.net.*;

public class Test02 {

	public static void main(String[] args) throws Exception {
		String msg = "java multicast newwork!!";
		
		InetAddress ia = InetAddress.getByName("231.0.0.4");
		DatagramPacket dp = new DatagramPacket(
				msg.getBytes(), msg.getBytes().length, ia, 12345);
		MulticastSocket ms = new MulticastSocket();
		ms.send(dp);
		
//		ms.joinGroup(ia);
//		ms.receive(dp);
//		ms.leaveGroup(ia);
		
		ms.close();
	}

}
