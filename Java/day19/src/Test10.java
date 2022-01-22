import java.io.*;

public class Test10 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File dir = new File("dir");
		File file = new File(dir, "ddd.txt");
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);
		
		
		A09 ap = (A09)ois.readObject();
		System.out.println("ap.a = " + ap.a);
		System.out.println("ap.b = " + ap.b);
		System.out.println("ap.c = " + ap.c);
		
		ois.close();
	}

}
