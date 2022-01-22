
public class Note {

	public static void main(String[] args) {
		byte a1 = 10;
		int a2 = a1;	// 자동 형변환
		byte a3 = (byte)a2;	// 강제 형변환
		
		int b1 = 10000;
		byte b2 = (byte)b1;	// 데이터 손실
		
		// float, int 둘다 8바이트
		float c1 = 10.01f;
		int c2 = (int)c1;
		
		// long은 8바이트, float 4바이트
		long d1 = 10000000000000L;
		float d2 = d1;	// 실수형은 정수형보다 항상 큼
		
		byte e1 = 10;
		char e2 = (char)e1;	// byte가 더 작아도 char이 음수를 표현하지 못해서 강제 형변환
		byte e3 = (byte)e2;

		short f1 = 10;
		char f2 = (char)f1;
		short f3 = (short)f2;
		
		System.out.println(a1+" "+a2+" "+a3);
		System.out.println(b1+" "+b2);
		System.out.println(c1+" "+c2);
		System.out.println(d1+" "+d2);
		System.out.println(e1+" "+e2+" "+e3);
		System.out.println(f1+" "+f2+" "+f3);
	}

}
