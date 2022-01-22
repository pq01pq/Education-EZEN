import java.io.*;

class A09 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	int a = 10;
	int b = 20;
	transient int c = 30;
}

public class Test09 {

	public static void main(String[] args) throws IOException {
		File dir = new File("dir");
		File file = new File(dir, "ddd.txt");
		
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		A09 ap = new A09();
		// NotSerializableException : 직렬화하지 않은 예외
		// 데이터는 메모리상에 01101110... 이런식으로 병렬로 되어있음
		// 파일로 출력할 때는 직렬로 순서대로 보내야함
		oos.writeObject(ap);
		oos.close();
	}

}
