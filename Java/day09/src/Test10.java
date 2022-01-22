
class Point2D {
	int x; 
	int y;
	public Point2D() {
		x = 125;
		y = 227;
	}
	
	public void disp() {
		System.out.println("Point2D [x=" + x + ", y=" + y + "]");
	}
	
	
}

class Point3D extends Point2D {
	int z;
	int y;
	Point3D() {
		z = 337;
//		y = 1000;
	}
	public void disp() {
		System.out.println("Point3D [x=" + x + ", y=" + y + ", z=" + z + "]");
	}
}

public class Test10 {

	public static void main(String[] args) {
		Point2D ap = new Point3D();
		Point2D bp = ap;
		System.out.println("ap.x = " + ap.x);
		System.out.println("ap.y = " + ap.y);
		System.out.println("bp.x = " + bp.x);
		System.out.println("bp.y = " + bp.y);
		
		ap.x = 1000;
		System.out.println("ap.x = " + ap.x);
		System.out.println("bp.x = " + bp.x);
		System.out.println("--------------------");
		
		Point3D p3 = new Point3D();
		Point2D p2 = p3;	// 업캐스팅, 프로모션
		// 멤버필드의 값은 부모클래스가 가지고 있는 멤버필드만 접근 가능
		
		System.out.println("p2.x = " + p2.x);
		System.out.println("p2.y = " + p2.y);	// 부모의 y
//		System.out.println("p2.z = " + p2.z);
		System.out.println("p3.x = " + p3.x);
		System.out.println("p3.y = " + p3.y);	// 자식의 y
		System.out.println("p3.z = " + p3.z);
		System.out.println("--------------------");
		
//		Point2D pp2 = new Point2D();	// 이러면 z 필드를 못씀
		Point2D pp2 = new Point3D();	// 업캐스팅(자식 -> 부모)
		Point3D pp3 = (Point3D)pp2;	// 다운캐스팅(부모 -> 자식)
		// 자식의 클래스 타입으로 강제 형변환 해줘야함
		// 부모의 객체는 반드시 업캐스팅된 상태이어야만 함
		System.out.println("pp2.x = " + pp2.x);
		System.out.println("pp2.y = " + pp2.y);	// 부모의 y
//		System.out.println("pp2.z = " + pp2.z);
		System.out.println("pp3.x = " + pp3.x);
		System.out.println("pp3.y = " + pp3.y);	// 자식의 y
		System.out.println("pp3.z = " + pp3.z);
		System.out.println("--------------------");
		
		Point2D p22 = new Point3D();	// 업캐스팅
		p22.disp();	// 다형성 : 다양한 형태로 나오는 성질. 자식의 메서드가 실행됨
	}

}
