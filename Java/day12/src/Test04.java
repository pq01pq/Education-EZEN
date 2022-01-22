import java.text.DecimalFormat;

public class Test04 {

	public static void main(String[] args) {
		int price = 1000000;
		System.out.println("가격 : " + price);
		DecimalFormat df = new DecimalFormat("###,###");
		System.out.println("가격 : " + df.format(price));
	}

}
