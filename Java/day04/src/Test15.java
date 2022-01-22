
public class Test15 {

	public static void main(String[] args) {
		
		int[] countNumber = new int[45];
		
		int[] lotto = new int[6];
		for(int i = 0; i < 10000; i++) {
			int count = 0;
			while(count < lotto.length) {
				lotto[count] = (int)(45 * Math.random()) + 1;
				for(int j = 0; j < count; j++) {
					if(lotto[j] == lotto[count]) {
						count--;
						break;
					}
				}
				count++;
			}
			
			for(int j = 0; j < lotto.length; j++) {
				countNumber[lotto[j] - 1]++;
			}
		}
		
		int[] frequentIndex = new int[45];
		for(int i = 0; i < frequentIndex.length; i++) {
			frequentIndex[i] = i + 1;
		}
		
		for(int i = 0; i < countNumber.length - 1; i++) {
			for(int j = i + 1; j < countNumber.length; j++) {
				if(countNumber[j] > countNumber[i]) {
					int temp = countNumber[j];
					countNumber[j] = countNumber[i];
					countNumber[i] = temp;
					
					temp = frequentIndex[j];
					frequentIndex[j] = frequentIndex[i];
					frequentIndex[i] = temp;
				}
			}
		}
		
		System.out.print("최빈값 : ");
		for(int i = 0; i < lotto.length; i++) {
			System.out.print(frequentIndex[i] + " ");
		}
		
		for(int i = 0; i < countNumber.length; i++) {
			System.out.print(frequentIndex[i] + " : ");
			System.out.println(countNumber[i]);
		}
	}

}
