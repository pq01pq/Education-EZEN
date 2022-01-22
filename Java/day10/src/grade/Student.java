package grade;

public class Student {
	// field
	private String name;
	private int kor;
	private int eng;
	private int total;
	private int rank = 1;
	
	//constructor
	public Student() {}
	
	public Student(String name) {
		this.name = name;
	}
	
	public Student(String name, int kor, int eng) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
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

	@Override
	public String toString() {
		return "[name=" + name + ", kor=" + kor + ", eng=" + eng
				+ ", total=" + total + ", rank=" + rank + "]";
	}

}
