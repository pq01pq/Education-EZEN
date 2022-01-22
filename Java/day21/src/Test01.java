
import java.net.*;

// 서버
public class Test01 {

	public static void main(String[] args) throws Exception {
		String data = "Hello, Java!!";
		String data2 = "안녕 자바!!";
		// byte배열로 나눌 때 한글은 2바이트로 처리됨
		System.out.println(data2.length());
		System.out.println(data2.getBytes().length);
		
		InetAddress ia = InetAddress.getByName("localhost");
		DatagramPacket dp = new DatagramPacket(data.getBytes(), data.getBytes().length, ia, 12345);
		
		DatagramSocket ds = new DatagramSocket();
		ds.send(dp);
		ds.close();
		System.out.println("전송 완료");
	}

}
