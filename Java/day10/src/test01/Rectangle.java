package test01;

public class Rectangle extends Shape {
	private double base;
	private double height;
	
	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Rectangle(double base, double height) {
		this.base = base;
		this.height = height;
		super.setArea(base * height);
	}
	
	public double area() {
		System.out.println("사각형의 넓이를 구해러 리턴");
		return super.getArea();
	}
}
