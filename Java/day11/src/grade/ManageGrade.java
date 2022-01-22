package grade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import manage.Manage;

public class ManageGrade implements Manage {
	
	ArrayList<Student> students;
	Scanner scan;
	
	public ManageGrade() {
		super();
		this.students = new ArrayList<>();
		this.scan = new Scanner(System.in);
	}
	
	@Override
	public void insert() {
		System.out.print("이름: ");
		String name = scan.next();
		if(_findStudent(name) != null) {
			System.out.println("존재하는 이름");
			return;
		}
		
		System.out.print("국어점수: ");
		int kor = scan.nextInt();
		System.out.print("영어점수: ");
		int eng = scan.nextInt();
		Student inStudent = new Student(name, kor, eng);
		
		_insertStudent(inStudent);
	}
	
	public Student _findStudent(String name) {
		for(Student student : students) {
			if(name.equals(student.getName())) {
				return student;
			}
		}
		return null;	
	}
	
	public void _insertStudent(Student inStudent) {
		for(Student compStudent : students) {
			int compare = inStudent.getTotal() - compStudent.getTotal();
			if(compare > 0) {
				compStudent.decreaseRank();
			} else if(compare < 0) {
				inStudent.decreaseRank();
			}
		}
		
		// ordered by rank ascending
//		int index = 0;
//		for(Student compStudent : students) {
//			if(inStudent.getRank() >= compStudent.getRank()) {
//				index++;
//			}
//		}
//		students.add(index, inStudent);
		students.add(inStudent);
	}

	@Override
	public void view() {
		
		// 비교 메서드를 오버라이드하는 클래스를 구현
		class MySort implements Comparator<Student> {
			
			@Override
			public int compare(Student o1, Student o2) {
				return o1.compareTo(o2);
			}
		}
		
		Collections.sort(students, new MySort());
		
		for(Student student : students) {
			System.out.println(student);
		}
	}

	@Override
	public void delete() {
		System.out.print("삭제할 학생 이름: ");
		String delName = scan.next();
		Student delStudent = _findStudent(delName);
		
		if(delStudent == null) {
			System.out.println("해당 학생 없음");
			return;
		}
		_deleteStudent(delStudent);
		System.out.println("삭제 완료");
	}
	
	public void _deleteStudent(Student delStudent) {
//		for(Student compStudent : students) {
//			if(delStudent.getRank() < compStudent.getRank()) {
//				compStudent.increaseRank();
//			}
//		}
		students.remove(delStudent);
	}
	
	// 수정 방식은 기존의 내용을 지우고 새로운 내용을 삽입함
	@Override
	public void modify() {
		System.out.print("수정할 학생 이름: ");
		String modName = scan.next();
		Student delStudent = _findStudent(modName);
		if(delStudent == null) {
			System.out.println("해당 학생 없음");
			return;
		}
		
		modify: while(true) {
			System.out.print("수정할 점수(1.국어 2.영어 3.완료): ");
			switch(scan.nextInt()) {
			case 1:
				System.out.print("점수 입력: ");
				delStudent.setKor(scan.nextInt());
				System.out.println("국어점수 수정됨");
				break;
			case 2:
				System.out.print("점수 입력: ");
				delStudent.setEng(scan.nextInt());
				System.out.println("영어점수 수정됨");
				break;
			case 3:
				break modify;
			default:
				System.out.println("잘못 입력");
			}
		}
		
		Student inStudent = new Student(modName, delStudent.getKor(), delStudent.getEng());
		_deleteStudent(delStudent);
		_insertStudent(inStudent);
		System.out.println("수정 완료");
	}
	
}
