import java.io.*;

public class Test07 {

	public static void main(String[] args) throws IOException {
		File dir = new File("dir");
		File file = new File(dir, "ccc.txt");
		
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		pw.print("안뇽");
		pw.println("자바 프로그램");
		pw.println("시간이다");
		pw.println(20);
		bw.close();
	}

}
