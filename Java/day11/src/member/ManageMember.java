package member;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

import manage.Manage;

public class ManageMember implements Manage {
	
	private Hashtable<String, String> members;
	Scanner scan;
	Enumeration<String> en;
	
	public ManageMember() {
		members = new Hashtable<>();
		scan = new Scanner(System.in);
	}
	
	@Override
	public void insert() {
		System.out.println("입력");
		System.out.print("사용자 이름 : ");
		String inName = scan.next();
		if(_findMember(inName)) {
			System.out.println("존재하는 이름");
			return;
		}
		
		System.out.print("전화번호 : ");
		String inPhone = scan.next();
		_insertMemeber(inName, inPhone);
		System.out.println("입력 완료");
	}
	
	public boolean _findMember(String name) {
		if(members.containsKey(name)) {
			return true;
		}
		return false;
	}
	
	public void _insertMemeber(String inName, String inPhone) {
		members.put(inName, inPhone);
	}
	

	public void view() {
		en = members.keys();
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			String phone = members.get(name);
			System.out.println("이름: " + name + " 번호: " + phone);
		}
	}
	
	@Override
	public void delete() {
		System.out.println("삭제");
		System.out.print("사용자명 : ");
		String delName = scan.next();
		if(!_findMember(delName)) {
			System.out.println("없는 이름");
			return;
		}
		
		_deleteMember(delName);
		System.out.println("삭제 완료");
	}
	
	public void _deleteMember(String delName) {
		members.remove(delName);
	}
	
	@Override
	public void modify() {
		System.out.println("수정");
		System.out.print("사용자명 : ");
		String modName = scan.next();
		if(!_findMember(modName)) {
			System.out.println("없는 이름");
			return;
		}
		
		System.out.print("수정할 번호: ");
		String modPhone = scan.next();
		_deleteMember(modName);
		_insertMemeber(modName, modPhone);
		System.out.println("수정 완료");
	}
	
}
