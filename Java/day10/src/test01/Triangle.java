package test01;

public class Triangle extends Shape {
	private double base;
	private double height;
	
	public Triangle(double base, double height) {
		this.base = base;
		this.height = height;
		super.setArea(base * height / 2.0);
	}
	
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
	
	public double area() {
		System.out.println("삼각형의 넓이를 구해러 리턴");
		return super.getArea();
	}
}
