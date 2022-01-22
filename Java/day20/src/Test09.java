import java.net.*;
import java.io.*;
import java.util.*;
public class Test09{
	private ServerSocket ss;
	private Socket soc;
	private PrintWriter pw;
	private BufferedReader br;
	private Hashtable<String, Socket>ht;
	//클라이언트 저장
	
	public Test09() throws Exception {
		ss = new ServerSocket(12345);
		ht = new Hashtable<>();
		while(true){
			soc = ss.accept();
			ChatClient cc = new ChatClient(soc);
			cc.start();
		}
	}
	
	public void sendMessage(Socket soc, String name)
										throws Exception{
		if (ht.size() != 0){
			Enumeration<String> enu = ht.keys();
			while(enu.hasMoreElements()){
				String n = enu.nextElement();
				Socket s = ht.get(n);
				pw = new PrintWriter(s.getOutputStream(), true);
				pw.print("["+name+"]님 입장" + "\n");
				pw.flush();
			}
		}
		ht.put(name, soc);
	}
	
	public void sendMessage(String name, String msg) throws Exception{
		Enumeration<String> enu = ht.keys();
		while(enu.hasMoreElements()){
			String n = enu.nextElement();
			//if (n.equals(name)) continue;
			Socket s = ht.get(n);
			pw = new PrintWriter(new BufferedWriter
							(new OutputStreamWriter(s.getOutputStream())));
			pw.print(name +" : " + msg + "\n");
			pw.flush();
		}
	}
	
	class ChatClient extends Thread{
		Socket soc;
		ChatClient(Socket soc){
			this.soc = soc;
		}
		public void run(){
			while(true){
				try{
					br = new BufferedReader
						(new InputStreamReader(soc.getInputStream()));
					String str = br.readLine();
					if (str == null) break;
					if (str.charAt(0) == '@'){
						sendMessage(soc, str.substring(1));
					}else {
						Scanner sc = new Scanner(str).useDelimiter("\\s*:\\s*");
						String name = sc.next();
						String msg = sc.next();
						sendMessage(name, msg);
						sc.close();
					}
				}catch(Exception e){}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Test09();
	}
}