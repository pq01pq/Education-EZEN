
public class Test02 {

	public static void main(String[] args) {
		// 1~100 합
		int sum = 0;
		int i = 1;
		while(true) {
			sum += i;
			i++;
			if(i > 100) {
				break;
			}
		}
		System.out.println(sum);
	}

}
