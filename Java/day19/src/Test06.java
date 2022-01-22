import java.io.*;

public class Test06 {

	public static void main(String[] args) throws IOException {
		File dir = new File("dir");
		File file = new File(dir, "bbb.txt");
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		
		String name = dis.readUTF();
		int kor = dis.readInt();
		double a = dis.readDouble();
		
		System.out.println(name + " " + kor + " " + a);
		
		dis.close();
	}

}
