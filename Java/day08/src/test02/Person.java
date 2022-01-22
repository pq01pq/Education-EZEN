package test02;

public class Person {
	// member field
	private String name;
	private String phone;
	
	// constructor
	public Person() {}
	
	public Person(String name) {
		this.name = name;
	}

	public Person(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}
	
	// getter, setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	// member method
	public void showInfo() {
		System.out.println("이름 : " + name);
		System.out.println("전화번호 : " + phone);
	}
	
}
