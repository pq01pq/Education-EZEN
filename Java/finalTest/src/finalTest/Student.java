package finalTest;

public class Student implements Comparable<Student>{
	private String name;
	private int kor;
	private int eng;
	private int total;
	private double average;
	
	public Student(String name) {
		super();
		this.name = name;
	}
	
	public Student(String name, int kor, int eng) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		total = kor + eng;
		average = total / 2.0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}

	@Override
	public int compareTo(Student other) {
		return other.getTotal() - this.total;
	}
	
	@Override
	public String toString() {
		return "{이름:" + name + ", 총점:" + total + ", 평점:" + average + "}";
	}
}
