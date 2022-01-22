package member;

public class Member {
	private String name;
	private String tel;
	
	public Member(String name, String tel) {
		this.name = name;
		this.tel = tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public String getTel() {
		return tel;
	}
	public void disp() {
		System.out.println("이름 : " + name + " , 전화번호 : " + tel);
	}
}





