package finalTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		
		System.out.println("학생관리 프로그램");
		System.out.println("1.입력 2.출력 0.종료");
		while(true) {
			System.out.print("> ");
			try {
				int index = scan.nextInt();
				switch(index) {
				case 1:{
					System.out.print("이름:");
					String name = scan.next();
					System.out.println("국어: ");
					int kor = scan.nextInt();
					System.out.println("영어: ");
					int eng = scan.nextInt();
					
					Student student = new Student(name, kor, eng);
					students.add(student);
					Collections.sort(students);
					break;
				}
				case 2:{
					int korTotal = 0;
					int engTotal = 0;
					int total = 0;
					for(int i = 0; i < students.size(); i++) {
						Student student = students.get(i);
						System.out.println((i + 1) + "등:" + student);
						korTotal += student.getKor();
						engTotal += student.getEng();
						total += student.getTotal();
					}
					
					System.out.println("----- 국어 ------");
					System.out.println("총점: " + korTotal);
					System.out.println("평균: " + (korTotal / students.size()));
					System.out.println("-----------------");
					
					System.out.println("----- 영어 ------");
					System.out.println("총점: " + engTotal);
					System.out.println("평균: " + (engTotal / students.size()));
					System.out.println("-----------------");
					
					System.out.println("----- 전체 ------");
					System.out.println("총점: " + total);
					System.out.println("평균: " + (total / students.size()));
					System.out.println("-----------------");
					break;
				}
				case 0:
					System.exit(0);
				default:
					System.out.println("인덱스 제대로 입력");
				}
			} catch (Exception e) {
				System.out.println("값 잘못 입력됨");
			}
		}
	}

}
