package grade;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {
	private static final long serialVersionUID = 1L;
	
	// field
	private String name;
	private int kor;
	private int eng;
	private int total;
	private int rank;
	private String command;
	
	// constructor
	public Student() {}
	
	public Student(String name) {
		this.name = name;
	}
	
	public Student(String name, String command) {
		this.name = name;
		this.command = command;
	}
	
	public Student(String name, int kor, int eng) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		rank = 1;
		this.setTotal();
	}
	
	public Student(String name, int kor, int eng, String command) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.command = command;
		rank = 1;
		this.setTotal();
	}
	
	// getter setter
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
		setTotal();
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
		setTotal();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal() {
		total = kor + eng;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	// method
	public void increaseRank() {
		rank--;
	}
	
	public void decreaseRank() {
		rank++;
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	@Override
	public String toString() {
		return "이름: " + name + ", 국어:" + kor + ", 영어:" + eng
				+ ", 총점: " + total + ", 등수: " + rank;
	}

	@Override
	public int compareTo(Student compStudent) {
		return this.name.compareTo(compStudent.getName());
	}

}
