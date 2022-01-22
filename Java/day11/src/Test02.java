import java.util.ArrayList;
import java.util.Iterator;

public class Test02 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();	// 10개
		ArrayList<String> list2 = new ArrayList<>(1000);
		
		list.add("유재석");
		list.add("하하");
		list.add("이광수");
		list.add("송지효");
		System.out.println(list);
		System.out.println("list 크기: " + list.size());
		System.out.println("list2 크기: " + list2.size());
		
		list.add(1, "김종국");
		System.out.println(list);
		
		for(int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			String name = (String)obj;
			System.out.println(name);
		}
		
		System.out.println("-----------------------------------------");
		
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			String name = (String)obj;
			System.out.println(name);
		}
		
//		list.remove("송지효");
		list.remove(4);
		System.out.println(list);
		
		String[] name = new String[list.size()];
		list.toArray(name);
		for(int i = 0; i < name.length; i++) {
			System.out.println(name[i]);
		}
	}

}
