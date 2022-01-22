package test09;

import java.util.Scanner;

public class Main {

	static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		System.out.print("학생수를 입력 : ");
		int number = scan.nextInt();
		
		ComputerStudent[] student = new ComputerStudent[number];
		// 클래스 배열은 객체를 선언만 한 것이다.
		// 각 객체별로 생성을 해줘야 함
		
		for(int i = 0; i < number; i++) {
			System.out.print("이름 : ");
			String name = scan.next();
			System.out.print("국어 점수 : ");
			int kor = scan.nextInt();
			System.out.print("영어 점수 : ");
			int eng = scan.nextInt();
			System.out.print("컴퓨터 점수 : ");
			int com = scan.nextInt();
			student[i] = new ComputerStudent(name, kor, eng, com);
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
