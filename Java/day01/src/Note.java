
public class Note {

	public static void main(String[] args) {
		
//		byte b1 = 130; // 에러 : 최댓값 초과
		
		byte b2 = 10, b3 = 20;
//		byte b4 = b2 + b3; // 에러 : 저장소에 기본자료형인 int로 바뀌어 저장되기 때문
		
		long l1 = 10; // int형인 10을 long으로 promotion해서 다시 저장하므로 일을 두번 함
		long l2 = 10L, l3 = 10l; // 한번에 저장함
		
//		float f1 = 10.0; // double형을 float형으로 자동으로 downcasting이 안되기 때문에 에러
		float f2 = 10.0F, f3 = 10.0f;
		
		System.out.println(b2+" "+b3+" "+l1+" "+l2+" "+l3+" "+f2+" "+f3);
	}

}
