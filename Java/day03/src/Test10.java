
public class Test10 {

	public static void main(String[] args) {
		System.out.println("1.");
		for(int i = 1; i <= 5; i++) {
			for(int j = i; j <= i + 4; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		
		System.out.println();
		
		System.out.println("2.");
		for(int i = 5; i >= 1; i--) {
			for(int j = i; j <= i + 4; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		
		System.out.println();
		
		System.out.println("3.");
		for(int i = 1; i <= 5; i++) {
			for(int j = 1; j <= i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		
		System.out.println();
		
		System.out.println("4.");
		int flag = 0;
		for(int i = 1; i <= 9; i++) {
			if(i <= 5) {
				flag++;
			} else {
				flag--;
			}
			
			for(int j = 1; j <= flag; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		
		System.out.println();
		
		System.out.println("5.");
		flag = 6;
		for(int i = 1; i <= 9; i++) {
			if(i <= 5) {
				flag--;
			} else {
				flag++;
			}
			
			for(int j = 1; j <= flag; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		
		System.out.println();
		
		System.out.println("6.");
		for(int i = 1; i <= 5; i++) {
			for(int blank = 1; blank <= 5 - i; blank++) {
				System.out.print(" ");
			}
			for(int j = 1; j <= i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		
		System.out.println();
		
		System.out.println("7.");
		flag = 0;
		for(int i = 1; i <= 9; i++) {
			if(i <= 5) {
				flag++;
			} else {
				flag--;
			}
			
			for(int blank = 1; blank <= 5 - flag; blank++) {
				System.out.print(" ");
			}
			for(int j = 1; j <= flag; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		
		System.out.println();
		
		System.out.println("8.");
		flag = 0;
		for(int i = 1; i <= 9; i++) {
			if(i <= 5) {
				flag++;
			} else {
				flag--;
			}
			
			for(int blank = 1; blank <= 5 - flag; blank++) {
				System.out.print(" ");
			}
			for(int j = 1; j <= 2 * flag - 1; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}

}
