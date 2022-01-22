import java.net.*;
import java.io.*;

// 서버 예시
public class Test03_serverEx {

	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket soc = null;
		
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			ss = new ServerSocket(12345);
			System.out.println("서버대기중...");
			soc = ss.accept();
			// 대상 포트번호에서 신호가 들어올때까지 대기하다가
			// 신호가 오면 소켓으로 들어옴
			
			// 클라이언트에서 받을 때
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String msg = br.readLine();
			System.out.println("클라이언트에서 온 메세지: " + msg);
			
			// 클라이언트에게 보낼 때
			pw = new PrintWriter(soc.getOutputStream());	// 약식
			pw.println(msg);
			
			System.out.println(soc.toString());
			// Socket[addr=/127.0.0.1,port=4533,localport=12345]
			//				[접속한 사람의 포트, 나의 포트]
			
			//br.close();
			pw.close();
			soc.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
