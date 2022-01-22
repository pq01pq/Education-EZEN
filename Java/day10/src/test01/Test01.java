package test01;

public class Test01 {

	public static void main(String[] args) {
		Shape triangle = new Triangle(2.0, 2.0);
		Shape rectangle = new Rectangle(2.0, 2.0);
		
		System.out.println(triangle.area());
		System.out.println(rectangle.area());
	}

}
