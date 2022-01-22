package test08;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class ManagePerson implements Manage {
	
	private HashSet<Person> people;
	Scanner scan;
	Iterator<Person> it;
	
	public ManagePerson() {
		people = new HashSet<>();
		scan = new Scanner(System.in);
	}
	
	public int function() {
		System.out.print("1.입력 2.출력 3.삭제 4.수정 5.종료 : ");
		return scan.nextInt();
	}
	
	public void input() {
		Person person = new Person();
		System.out.println("입력");
		System.out.print("사용자 이름 : ");
		person.setName(scan.next());
		System.out.print("전화번호 : ");
		person.setPhone(scan.next());
		people.add(person);
	}
	

	public void view() {
		if(people.size() > 0) {
			it = people.iterator();
			while(it.hasNext()) {
				Person person = it.next();
				System.out.println("이름 : " + person.getName());
				System.out.println("전화번호 : " + person.getPhone());			
			}
		} else {
			System.out.println("사용자 없음");
		}
	}
	
	public void delete() {
		System.out.println("삭제");
		System.out.print("사용자명 : ");
		String name = scan.next();
		
		it = people.iterator();
		while(it.hasNext()) {
			Person delPerson = it.next();
			if(name.equals(delPerson.getName())) {
				people.remove(delPerson);
				return;
			}
		}
		
		System.out.println("없는 이름");
	}
	
	public void modify() {
		System.out.println("수정");
		System.out.print("사용자명 : ");
		String name = scan.next();
		
		it = people.iterator();
		while(it.hasNext()) {
			Person modPerson = it.next();
			if(name.equals(modPerson.getName())) {
				System.out.print("수정할 번호 : ");
				modPerson.setPhone(scan.next());
				return;
			}
		}

		System.out.println("없는 이름");
	}
	
}
