import java.net.*;
import java.util.Scanner;
import java.io.*;

// 클라이언트 예시
public class Test04_clientEx {
	
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		InetAddress ia = null;
		Socket soc = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		
		// 자기 자신을 뜻하는 말 : localhost, 127.0.0.1, 자신의 IP(cmd - ipconfig)
		try {
			ia = InetAddress.getByName("localhost");
			soc = new Socket(ia, 12345);	// 12345 포트번호에 접속
			System.out.print("서버에 전할 말: ");
			String msg = scan.next();
			
			// 서버로 보낼 때
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())));
			pw.println(msg);
			pw.flush();	// 닫아버리면 다시 못보냄
			
			// 서버에서 받을 때
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String msg2 = br.readLine();
			System.out.println("서버에서 온 메세지: " + msg2);
			
			soc.close();
		} catch(UnknownHostException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("클라이언트 끝");
	}

}
