package test09;

public class ComputerStudent extends Student  {
	private int com;
	private int total;
	
	public ComputerStudent() {
		super();
	}
	public ComputerStudent(String name, int kor, int eng, int com) {
		super(name, kor, eng);
		this.com = com;
		total = super.getTotal() + this.com;
	}

	public int getCom() {
		return com;
	}
	public void setCom(int com) {
		this.com = com;
	}
	public void setTotalPlusCom() {
		this.total = super.getKor() + super.getEng() + com;
	}
	public int getTotalPlusCom() {
		return this.total;
	}
	@Override
	public void display() {
		System.out.println(getName() + "님의 총점은"
				+ total + "점이고, 순위는 " + getRank() + "등");
	}
	
}
