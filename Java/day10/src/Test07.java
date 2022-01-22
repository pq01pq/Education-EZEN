import java.util.HashSet;
import java.util.Iterator;

class A07 {}

public class Test07 {

	public static void main(String[] args) {
		HashSet<String> set1 = new HashSet<>();	// 16개, 0.75f
		HashSet<String> set2 = new HashSet<>(1000);	// 1000개, 0.75f
		HashSet<String> set3 = new HashSet<>(1000, 0.9f); 	// 1000개, 0.9f
		
		// 집합이라 중복없이 데이터를 넣음
		set1.add("유재석");	// HashSet에 데이터 넣기 : add()
		set1.add("김종국");
		set1.add("하하");
		set1.add("송지효");
		set1.add("이광수");
		System.out.println(set1);	// toString()
		
		System.out.println("저장된 데이터 수 : "
							+ set1.size());	// set1 크기 : size()
//		set1.clear();
//		System.out.println("clear 후 데이터 수 : "
//				+ set1.size());	// set1 크기 : size()
		if(set1.contains("하하")) {	// 해당 객체가 있는지 찾기
			System.out.println("하하는 저희 멤버입니다");
		}
		
		if(set1.isEmpty()) {	// 데이터가 있는지 없는지 알려줌
			System.out.println("set1는 비어있음");
		}
		if(set2.isEmpty()) {	// 데이터가 있는지 없는지 알려줌
			System.out.println("set2는 비어있음");
		}
		if(set3.isEmpty()) {	// 데이터가 있는지 없는지 알려줌
			System.out.println("set3는 비어있음");
		}
		
		set1.remove("이광수");	// 데이터 삭제, 해당 객체가 있으면 삭제후 true, 없으면 false
		System.out.println("이광수 삭제 후 멤버수 : " + set1.size());
		
		String[] name = new String[set1.size()];
		set1.toArray(name);
		for(int i = 0; i < name.length; i++) {
			System.out.println(name[i]);
		}
		
		// Set에 있는 데이터를 꺼낼때는 Iterator라는 반복자를 이용함
		Iterator<String> it = set1.iterator();
		while(it.hasNext()) {	// it에 데이터가 있냐
			Object obj = it.next();	// it가 가리키는 곳의 데이터를 꺼내 obj에 넣음
			String str = (String)obj;
			System.out.println(str);
		}
	}

}
