import java.util.ArrayList;

public class Test05 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		int a = 10;
//		int b = 20;
//		int c = a + b;
//		System.out.println(c);
		
		Integer a1 = new Integer(10);
		Integer b1= new Integer("20");
		Integer c1 = a1 + b1;
		System.out.println(c1);
		
		String sPrice = "1000000";
		int price = Integer.parseInt(sPrice);
		System.out.println("가격 : " + (int)(price * 1.1));
		
		Integer a2 = new Integer(10);
		Integer b2 = 20;
		System.out.println(a2 + b2);
		
		int a3 = 10;
		Integer b3 = new Integer(a3);	// boxing : 기본형을 클래스타입으로 바꾸는 과정
		Integer c3 = a3;	// auto-boxing
		
		Integer a4 = new Integer(10);
		int b4 = a4.intValue();	// unboxing : 클래스타입에서 기본형을 꺼내는 과정
		int c4 = a4;	// auto-unboxing
		System.out.println(c4);
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(100);
		list.add(70);
		list.add(60);
	}

}
