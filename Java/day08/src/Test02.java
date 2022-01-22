import java.util.Scanner;

class Student {
	private String name;
	private int kor;
	private int eng;
	private int total;
	private int rank;
	
	Student(){}
	
	Student(String name, int kor, int eng){
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

public class Test02 {
	
	static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		System.out.print("학생수를 입력 : ");
		int number = scan.nextInt();
		
		Student[] student = new Student[number];
		// 클래스 배열은 객체를 선언만 한 것이다.
		// 각 객체별로 생성을 해줘야 함
		
		for(int i = 0; i < number; i++) {
			System.out.print("이름 : ");
			String name = scan.next();
			System.out.print("국어 점수 : ");
			int kor = scan.nextInt();
			System.out.print("영어 점수 : ");
			int eng = scan.nextInt();
			student[i] = new Student(name, kor, eng);
		}
		
		for(int i = 0; i < number; i++) {
			student[i].clearRank();
			for(int j = 0; j < number; j++) {
				if(student[i].getTotal() < student[j].getTotal()) {
					student[i].plusRank();
				}
			}
		}
		
		for(int i = 0; i < number; i++) {
			student[i].display();
		}
	}

}
