
class Bank{
	private String name;
	private double money;
	static float interest;
	// static 멤버는 객체의 생성과 상관없이 메모리에 올라감
	
	static {
		interest = 0.02f;
	}
	
	public Bank(String name, double money, float interest) {
		this.name = name;
		this.money = money;
		Bank.interest = interest;
	}
	
	public void setInterest(float interest) {
		Bank.interest = interest;
	}
	public void disp() {
		System.out.println(name + "님의 현재 잔액은 " + money + "원 이고, ");
		System.out.println("현재 적용되는 이율은 " + interest*100 + "% 입니다.");
	}
}

public class Test04 {

	public static void main(String[] args) {
		System.out.println("현재 저희 은행 이율은 " + Bank.interest*100 + "% 입니다.");
		/*
		Bank aaa = new Bank("aaa", 1000.0, 0.02f);
		aaa.disp();
		System.out.println("*********************************");
		Bank bbb = new Bank("bbb", 1000.0, 0.015f);
		//aaa.setInterest(0.015f);
		aaa.disp();
		bbb.disp();
		System.out.println("*********************************");
		Bank ccc = new Bank("ccc", 1000.0, 0.01f);
		//aaa.setInterest(0.01f);
		//bbb.setInterest(0.01f);
		aaa.disp();
		bbb.disp();
		ccc.disp();
		*/
	}

}
