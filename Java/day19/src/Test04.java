import java.io.*;

public class Test04 {

	public static void main(String[] args) throws IOException{
		File dir = new File("dir");
		File file = new File(dir, "aaa.txt");
		
		FileInputStream fis = new FileInputStream(file);
		
		while(true) {
			int res = fis.read();
			if(res < 0) {	// EOF == -1
				break;
			} else {
				System.out.println((char)res);
			}
		}
		
		fis.close();
		
	}

}
