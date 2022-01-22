package baseball;

public class Player {
	public String name;
	private int annualIncome;
	
	public Player() {
		super();
	}
	
	public Player(String name) {
		super();
		this.name = name;
	}
	
	public int getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}

	@Override
	public String toString() {
		return "BaseballPlayer [name=" + name + ", annualIncome=" + annualIncome + "]";
	}
	
}
