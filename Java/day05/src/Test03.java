import java.util.Arrays;

public class Test03 {

	public static void main(String[] args) {
		int[] number = new int[10];
		
		// 선택 정렬
		for(int i = 0; i < number.length; i++) {
			number[i] = (int)(Math.random() * 200) - 100;
		}
		
		
		for(int i = 0; i < number.length - 1; i++) {
			for(int j = i + 1; j < number.length; j++) {
				if(number[i] > number[j]) {
					int temp = number[i];
					number[i] = number[j];
					number[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(number));
		
		// 버블 정렬
		for(int i = 0; i < number.length; i++) {
			number[i] = (int)(Math.random() * 201) - 100;
		}
		
		for(int i = number.length - 1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(number[j] > number[j + 1]) {
					int temp = number[j];
					number[j] = number[j + 1];
					number[j + 1] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(number));
		
		// 삽입 정렬
		for(int i = 0; i < number.length; i++) {
			number[i] = (int)(Math.random() * 201) - 100;
		}
		
		for(int i = 1; i < number.length; i++) {
			int temp = number[i];
			int j = i;
			while(j > 0) {
				if(temp < number[j - 1]) {
					number[j] = number[j - 1];
					j--;
				} else {
					number[j] = temp;
					break;
				}
			}
			if(j == 0) {
				number[j] = temp;
			}
		}
		System.out.println(Arrays.toString(number));
		
		for(int i = 0; i < number.length; i++) {
			number[i] = (int)(Math.random() * 201) - 100;
		}
		Arrays.sort(number);	// 자동정렬(qsort)
		Arrays.toString(number);
	}

}
