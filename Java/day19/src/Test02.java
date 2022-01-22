import java.io.File;
import java.io.IOException;

public class Test02 {

	public static void main(String[] args) throws IOException {
		File dir = new File("C:\\JAVA\\EZEN\\workspace\\day19\\dir");
		File file = new File(dir, "aaa.txt");
		
		if(file.createNewFile()) {	// 없으면 만들고 true 반환, 있으면 false 반환
			System.out.println(file.getName() + " 파일을 만들었음");
		} else {
			System.out.println(file.getName() + " 파일은 이미 존재함");
		}
		
//		if(file.delete()) {	// 파일이 있으면 지우고 true 반환, 없으면 false 반환
//			System.out.println("aaa 파일을 삭제했음");
//		} else {
//			System.out.println("aaa 파일은 없음");
//		}
		
//		File temp = File.createTempFile("temp", ".dat", dir);
//		temp.deleteOnExit();	// 프로그램 종료시 해당파일 지움
//		try {
//			Thread.sleep(5000);
//		} catch(InterruptedException e) {}
		
		for(int i = 0; i < dir.list().length; i++) {
			System.out.println(dir.list()[i]);
		}
		
		File file2 = new File("src\\Test01.java");
		System.out.println(file2.length());
	}

}
