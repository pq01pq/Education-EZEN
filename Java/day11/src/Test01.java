import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Test01 {

	public static void main(String[] args) {
		Hashtable<String, String> ht = new Hashtable<>();	// 11개의 저장공간, 0.75f 부하량
		Hashtable<String, String> ht2 = new Hashtable<>(1000);
		Hashtable<String, String> ht3 = new Hashtable<>(1000, 0.9f);
		
		// put(key, value) : key는 유일, value는 중복 가능
		// key가 중복되게 넣으면 value값이 수정됨
		ht.put("유재석", "개그맨");
		ht.put("손흥민", "축구선수");
		ht.put("류현진", "야구선수");
		ht.put("김동현", "격투기선수");
		System.out.println(ht);	// toString()
		
		System.out.println("ht 데이터의 수 : " + ht.size());
		System.out.println("ht2 데이터의 수 : " + ht2.size());
		System.out.println("ht3 데이터의 수 : " + ht3.size());
		
		System.out.printf("%s님의 직업은 %s이다.\n", "김동현", ht.get("김동현"));
		
		System.out.println("-------------------------------");
		
		Enumeration<String> enu = ht.keys();
		while(enu.hasMoreElements()) {	// hasNext()
			Object obj = enu.nextElement();	// next()
			String key = (String)obj;
			Object obj2 = ht.get(key);
			String value = (String)obj2;
			System.out.println(key + "님의 직업은 " + value + "이다.");
		}
		
		System.out.println("-------------------------------");
		
		Set<String> set = ht.keySet();	// key값을 set타입으로 보냄
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			String key = (String)obj;
			Object obj2 = ht.get(key);
			String value = (String)obj2;
			System.out.println(key + "님의 직업은 " + value + "이다.");
		}
		
		System.out.println("-------------------------------");
		
		Enumeration<String> enu2 = ht.elements();
		while(enu2.hasMoreElements()) {
			Object obj = enu2.nextElement();
			String value = (String)obj;
			System.out.println(value);
		}
		
		System.out.println("-------------------------------");
		
		if(ht.containsKey("손흥민")) {	// key값이 있냐
			System.out.println("손흥민은 저희 회원");
		}
		
		// value값이 존재하는지 물어보는 메서드는 contains(), containsValue()
		
		System.out.println("-------------------------------");
		
		ht.remove("김동현");
		System.out.println("ht 데이터의 수 : " + ht.size());
		
		ht.clear();
		System.out.println("clear후 데이터 수 : " + ht.size());
		if(ht.isEmpty()) {
			System.out.println("데이터 없음");
		}
	}

}
