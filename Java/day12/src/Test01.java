import java.util.ArrayList;
import java.util.Vector;

class A01 {}

public class Test01 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList</*String*/>();
		// 객체 선언 부분에서는 명시해주고, 객체 생성 부분에선 생략 가능
		list.add("강호동");
		list.add("김희철");
		list.add("서장훈");
		list.add("이수근");
		
//		list.add(new A01());	// 오류
		
		for(String name : list) {
			// list의 0번째 위치부터 끝까지 하나씩 name으로 넘겨준다
//			String name = (String)obj;
			System.out.println(name);
		}
		
		// Vector도 List형
		Vector<String> v = new Vector<>();
		v.add("aaa");
		v.add("bbb");
		for(String ab : v) {
			System.out.println(ab);
		}
		
	}

}
