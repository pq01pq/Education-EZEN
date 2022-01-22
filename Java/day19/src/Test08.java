import java.io.*;

public class Test08 {

	public static void main(String[] args) throws IOException {
		File dir = new File("dir");
		File file = new File(dir, "ccc.txt");
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		while(true) {
			String msg = br.readLine();
			if(msg == null) {	// EOF값이 null
				break;
			}
			System.out.println(msg);
		}
		
		br.close();
	}

}
