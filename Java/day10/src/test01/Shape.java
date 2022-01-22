package test01;

/**
 추상화 클래스는 추상화 메서드가 한개 이상 있는 클래스
 추상화 메서드가 있으면 그 클래스는 추상화 클래스가 됨
 추상화 메서드는 내용을 정의하지 않고, 선언만 함
 내용은 추상화 클래스를 상속받은 자식클래스가 함
 추상화 클래스는 독립적으로 객체 만들기 불가
*/
public abstract class Shape {
	private double area;
	
//	public Shape() {}
	// 생성자 굳이 만들 필요 없음

	public double getArea() {
		return area;
	}
	
	public void setArea(double area) {
		this.area = area;
	}
	
	// 추상화 메서드를 만들면 그 클래스는 반드시 추상화 클래스여야 함
	public abstract double area(); /*{
		System.out.println("도형의 넓이를 구해러 리턴");
		return area;
	}*/
}
