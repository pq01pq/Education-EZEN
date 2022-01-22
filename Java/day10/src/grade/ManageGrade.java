package grade;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class ManageGrade implements Manage {
	
	HashSet<Student> students;
	Scanner scan;
	Iterator<Student> it;
	
	public ManageGrade() {
		super();
		this.students = new HashSet<>();
		this.scan = new Scanner(System.in);
	}
	
	@Override
	public void insertStudent() {
		System.out.print("이름: ");
		String name = scan.next();
		it = students.iterator();
		while(it.hasNext()) {
			if(name.equals(it.next().getName())) {
				System.out.println("중복된 이름");
				return;
			}
		}
		
		System.out.print("국어점수: ");
		int kor = scan.nextInt();
		System.out.print("영어점수: ");
		int eng = scan.nextInt();
		
		Student inputStudent = new Student(name, kor, eng);
		_insert(inputStudent);
	}
	
	public void _insert(Student inputStudent) {
		it = students.iterator();
		while(it.hasNext()) {
			Student compStudent = it.next();
			int compare = _compare(inputStudent.getTotal(), compStudent.getTotal());
			if(compare > 0) {
				compStudent.decreaseRank();
			} else if(compare < 0){
				inputStudent.decreaseRank();
			}
		}
		
		students.add(inputStudent);
	}

	@Override
	public void viewStudent() {
		Object[] studentArray = students.toArray();
		Student[] viewArray = new Student[students.size()];
		
		for(int i = 0; i < studentArray.length; i++) {
			viewArray[i] = (Student)studentArray[i];
		}
		
		for(int i = 0; i < viewArray.length - 1; i++) {
			for(int j = i + 1; j < viewArray.length; j++) {
				if(viewArray[i].getRank() > viewArray[j].getRank()) {
					Student temp = viewArray[i];
					viewArray[i] = viewArray[j];
					viewArray[j] = temp;
				}
			}
		}
		
		for(int i = 0; i < viewArray.length; i++) {
			System.out.println(viewArray[i]);
		}
	}

	@Override
	public void deleteStudent() {
		System.out.print("삭제할 학생 이름: ");
		String delName = scan.next();
		it = students.iterator();
		while(it.hasNext()) {
			Student delStudent = it.next();
			if(delName.equals(delStudent.getName())) {
				_delete(delStudent);
				System.out.println("삭제 완료");
				return;
			}
		}
		
		System.out.println("해당 학생 없음");
	}
	
	public void _delete(Student delStudent) {
		it = students.iterator();
		while(it.hasNext()) {
			Student compStudent = it.next();
			if(delStudent.getRank() < compStudent.getRank()) {
				compStudent.increaseRank();
			}
		}
		students.remove(delStudent);
	}
	
	// 수정 방식은 기존의 내용을 지우고 새로운 내용을 삽입함
	@Override
	public void modifyStudent() {
		System.out.print("수정할 학생 이름: ");
		String modName = scan.next();
		it = students.iterator();
		while(it.hasNext()) {
			Student delStudent = it.next();
			if(modName.equals(delStudent.getName())) {
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
				Student inStudent = new Student(modName,
						delStudent.getKor(), delStudent.getEng());
				_delete(delStudent);
				_insert(inStudent);
				System.out.println("수정 완료");
				return;
			}	// end of if
		}	// end of while
		
		System.out.println("해당 학생 없음");
	}
	
	public int _compare(int total1, int total2) {
		if(total1 > total2) {
			return 1;
		} else if(total1 < total2) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
