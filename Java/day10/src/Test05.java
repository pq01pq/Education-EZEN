

public class Test05 {

	public static void main(String[] args) {
		
		String a = "aaa";	// 일반 자료형처럼 사용할 수 있게 만들어놓음
		String b = "aaa";
		String c = new String("aaa");
		String d = new String("aaa");
		
		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(b == c);
		System.out.println(c == d);
		
		System.out.println("----------------");
		
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		System.out.println(b.equals(c));
		System.out.println(c.equals(d));
		
		System.out.println("----------------");
		
		String str = new String("Hello, Java!");
		for(int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
		}
		// 인덱스는 0부터 시작
		// String 클래스의 길이 : length()
		// String 클래스 값을 접근 : charAt(index) -> 결과 char형
		String e = "10000";
		int ie = Integer.parseInt(e);
		System.out.println(ie);
		String f = "10.23232";
		double df = Double.parseDouble(f);
		System.out.println(df);
		
		System.out.println("----------------");
		
		String email = "aaa@naver.com";
		if (email.endsWith("naver.com")){
			System.out.println("네이버를 쓰는구만!");
		}
		if(email.startsWith("aaa")) {
			System.out.println("aaa님 안녕하세요");
		}
		
		System.out.println("----------------");
		
		String aaa = "Hello, Java!!";
		String bbb = "Hello, java!!";
		if(aaa.equalsIgnoreCase(bbb)) {	// 대소문자 구분 안하고 비교
			System.out.println("같은 값");
		}
		
		System.out.println("----------------");
		
		String msg = "Hello, Java!!";
		byte[] by = msg.getBytes();
		for(int i = 0; i < by.length; i++) {
			System.out.println(by[i]);
		}
		
		String msg2 = new String(by);
		System.out.println(msg2);
		
		System.out.println(msg.indexOf("J"));
		
		System.out.println("----------------");
		
		String s = "kor eng math";
		String[] sub = s.split(" ");
		for(int i = 0; i < sub.length; i++) {
			System.out.println(sub[i]);
		}
		
		System.out.println("----------------");
		
		String s2 = "Hello, Java!!";
		System.out.println(s2.substring(7));
		System.out.println(s2.substring(7, 11));
		
		System.out.println("----------------");
		
		String addr = "	 	 	 	 서울 강북구 	 	 	 	";
		System.out.println(addr);
		System.out.println(addr.trim());	// 앞뒤에 쓸데없는 공백 제거
		
		System.out.println("----------------");
		
		String s3 = "Hello, ava!!";
		StringBuffer sb = new StringBuffer(str);	// 동기화 가능
		sb.insert(7, "J");
		sb.delete(7, 8);	// 왜있누
		String s4 = sb.toString();
		System.out.println(s4);
		
		StringBuilder s5 = new StringBuilder(s3);
		s5.insert(7, "J");
		System.out.println(s5);
		
	}

}
