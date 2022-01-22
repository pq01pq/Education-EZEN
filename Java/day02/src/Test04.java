import java.util.Scanner;

public class Test04 {

	public static void main(String[] args) {
		boolean b = true;
		System.out.println(!b);
		
		int a = 10;
		System.out.println(++a);
		// ++a -> a = a + 1
		
		byte c = 10;
//		c = (byte)(c + 1);	// 캐스팅을 하면서 여러번 연산하여 프로그램이 느려지게 됨
		++c;	// 자료형에 영향을 안받음
		System.out.println(c);
		System.out.println();
		
		int d = 10;
		int e = 13;
		System.out.println(d==e && ++d>e);	// ++d>e는 실행되지 않음
		System.out.println(d==e && ++d>e);
		System.out.println(d==e && ++d>e);
		System.out.println(d==e && ++d>e);
		System.out.println(d==e && ++d>e);
		System.out.println();
		System.out.println(d==e & ++d>e);
		System.out.println(d==e & ++d>e);
		System.out.println(d==e & ++d>e);
		System.out.println(d==e & ++d>e);
		System.out.println(d==e & ++d>e);
		
		int f = 10;
		System.out.println(f == 10 ? "a의 값은 10이다." : "a의 값은 10이 아니다.");
		
		Scanner scan = new Scanner(System.in);
		System.out.print("1.남성 2.여성 : ");
		int gender = scan.nextInt();
		String s = gender == 1 ? "남성" : "여성";
		System.out.printf("당신은 %s입니다.", s);
		
		scan.close();
		
		byte g = 10;
//		g = (byte)(g + 5);
		g += 5;
		System.out.println(g);
		
		byte h = 10;
		byte i = h++;
		System.out.println("h = " + h + ", i = " + i);
		
		String x = "바보";
		x += "멍청이";
		System.out.println(x);
	}

}
