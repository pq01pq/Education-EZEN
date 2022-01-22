
import java.net.*;

public class Test02 {

	public static void main(String[] args) {
		InetAddress ia = null;
		try {
			ia = InetAddress.getByName("www.ezenac.co.kr");
			System.out.println("호스트 이름 : " + ia.getHostName());
			System.out.println("호스트 주소 : " + ia.getHostAddress());
		} catch(UnknownHostException e) {
			System.out.println("주소가 올바르지 않음");
		}
	}

}
