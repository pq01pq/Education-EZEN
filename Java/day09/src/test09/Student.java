package test09;

class Student {
	private String name;
	private int kor;
	private int eng;
	private int total;
	private int rank;
	
	public Student(){}
	
	public Student(String name, int kor, int eng){
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.total = kor + eng;
	}
	
	public String getName() {
		return name;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getKor() {
		return kor;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getEng() {
		return eng;
	}
	
	public void setTotal() {
		total = kor + eng;
	}
	public int getTotal() {
		return total;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void clearRank() {
		rank = 1;
	}
	public void plusRank() {
		rank++;
	}
	public void display() {
		System.out.println(name + "님의 총점은"
				+ total + "점이고, 순위는 " + rank + "등");
	}
}
