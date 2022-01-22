import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Test06 implements Runnable{	//채팅클라이언트
	private InetAddress ia;
	private Socket soc;
	private BufferedReader br;
	private PrintWriter pw;
	private Scanner in;
	
	public Test06() {	//서버에 메세지 보내는 프로그램
		try {
			ia = InetAddress.getByName("localhost");
			soc = new Socket(ia, 12345);
			in = new Scanner(System.in);	
			pw = new PrintWriter(soc.getOutputStream(), true);
			Thread th = new Thread(this);
			th.start();
			String msg = "";
			while((msg=in.nextLine()) != null) {
				pw.println(msg);
				pw.flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {	//서버에서 온 메세지 받는 프로그램
		// TODO Auto-generated method stub
		try {
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String clientMsg = null;
			while(true) {
				clientMsg = br.readLine();
				System.out.println("서버에서 온 메세지 : " + clientMsg);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		new Test06();
	}
}
