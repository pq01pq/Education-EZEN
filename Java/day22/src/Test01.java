
import java.net.*;

public class Test01 {

	public static void main(String[] args) throws Exception {
		byte[] by = new byte[65508];
		DatagramPacket dp = new DatagramPacket(by, by.length);
		MulticastSocket ms = new MulticastSocket(12345);
		
		InetAddress ia = InetAddress.getByName("231.0.0.4");
		
		ms.joinGroup(ia);	// 그룹으로 들어가짐
		
		ms.receive(dp);	// 그룹에 온 데이터 받기
		
		ms.leaveGroup(ia);	// 그룹에서 나가기
		
		ms.close();
		
		System.out.println("보낸이 : " + dp.getAddress().getHostAddress());
		System.out.println("내용물 : " + new String(dp.getData()).trim());
	}

}
