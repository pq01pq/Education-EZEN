package test02;

import java.util.Scanner;

public class ManagePerson {
	
	private Person[] person;
	private int count;
	Scanner scan;
	
	public ManagePerson() {
		person = new Person[5];
		count = 0;
		scan = Ex03.scan;
	}
	
	public void inputPerson() {
		// 가변배열 조작
		if(count >= person.length) {
			Person[] temp = new Person[person.length + 5];
			for(int i = 0; i < person.length; i++) {
				temp[i] = person[i];
			}
			person = temp;
//			temp = null;
		}
		
		person[count] = new Person();
		System.out.println("입력");
		System.out.print("사용자 이름 : ");
		person[count].setName(scan.next());
		System.out.print("전화번호 : ");
		person[count].setPhone(scan.next());
		count++;
		if(Ex03.DEBUG) {
			System.out.println("배열 크기 : " + person.length);
			System.out.println("인원수 : " + count);
		}
	}
	

	public void viewPerson() {
		if(count > 0) {
			for(int i = 0; i < count; i++) {
				person[i].showInfo();
			}
		} else {
			System.out.println("사용자 목록 없음");
		}
	}
	
	public void delPerson() {
		System.out.println("삭제");
		System.out.print("사용자명 : ");
		int delIndex = _findIndex(scan.next());
		if(delIndex < count) {
			int moveIndex;
			for(moveIndex = delIndex; moveIndex < count - 1; moveIndex++) {
				person[moveIndex] = person[moveIndex + 1];
			}
			person[moveIndex] = null;
			count--;
			System.out.println("삭제 완료");
		} else {
			System.out.println("없는 이름");
		}
	}
	
	public void modPerson() {
		System.out.println("수정");
		System.out.print("사용자명 : ");
		int modIndex = _findIndex(scan.next());
		if(modIndex < count) {
			System.out.print("수정할 번호 : ");
			person[modIndex].setPhone(scan.next());
			System.out.println("수정 완료");
		} else {
			System.out.println("없는 이름");
		}
	}
	
	public int _findIndex(String name) {
		int index;
		for(index = 0; index < count; index++) {
			if(name.equals(person[index].getName())) {
				break;
			}
		}
		return index;
	}
}
