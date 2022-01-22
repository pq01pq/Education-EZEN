
public class Test06 {

	public static void main(String[] args) {
		String str = "Hello, Java!!";
		int[] alphabetCount = new int[26];
		
		for(int i = 0; i < str.length(); i++) {
			char c = Character.toLowerCase(str.charAt(i));
			if(c >= 'a' && c <= 'z') {
				alphabetCount[c - 'a']++;
			}
		}
		
		for(int i = 0; i < alphabetCount.length; i++) {
			if(alphabetCount[i] > 0) {
				System.out.println((char)(i + 'A') + " : " + alphabetCount[i]);
			}
		}
		System.out.println((char)('z' + 5));
		
	}

}
