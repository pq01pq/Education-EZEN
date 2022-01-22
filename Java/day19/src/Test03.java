import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test03 {

	public static void main(String[] args) throws IOException {
		File dir = new File("dir");
		File file = new File(dir, "aaa.txt");
		
		FileOutputStream fos = new FileOutputStream(file, true);
								// true : 이어씀, false : 덮어씀
//		fos.write('A');
//		fos.write(66);
		
		String str = "Hello, Java!!";
		byte[] b = str.getBytes();
		fos.write(b);
		
		fos.close();
	}

}
