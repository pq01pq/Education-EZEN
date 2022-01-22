import java.io.*;

public class Test05 {

	public static void main(String[] args) throws IOException {
		File dir = new File("dir");
		File file = new File(dir, "bbb.txt");
		
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);
		
		dos.writeUTF("홍길동");
		dos.writeInt(100);
		dos.writeDouble(10.23);
//		dos.flush();	// 입력이 끝났다는 신호임
		dos.close();	// 먼저 flush()를 해줌
	}

}
