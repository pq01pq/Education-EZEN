import java.io.IOException;
import java.net.ServerSocket;

/**
서버 포트번호 찾기 : 12345 사용
 */
public class Test01 {

	public static void main(String[] args) {
		ServerSocket ss = null;
		for(int i = 0; i < 30000; i++) {
			try {
				ss = new ServerSocket(i);
				ss.close();
			} catch(IOException e) {
				System.out.println(i + "번은 현재 사용중임");
			}
		}
	}

}
