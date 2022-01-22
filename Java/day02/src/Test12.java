import java.io.IOException;

public class Test12 {

	public static void main(String[] args) throws IOException {
		System.out.print("한글자 입력 : ");
//		int a = System.in.read();
		// 아스키코드값 : 'A' == 65, 'a' == 97, '0' == 48
		char a = (char)System.in.read();	System.in.read();System.in.read();
		System.out.println(a);
	}

}
