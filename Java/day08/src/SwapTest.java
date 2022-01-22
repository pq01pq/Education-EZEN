
class Swap {
	public static void swap(Object[] a) {
		Object temp = a[0];
		a[0] = a[1];
		a[1] = temp;
	}
}

public class SwapTest {
	
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		Integer[] i = new Integer[] {a, b};
		Swap.swap(i);
		System.out.println(i[0]);
		System.out.println(i[1]);
		
		String s = "hi";
		String t = "hello";
		String[] str = new String[] {s, t};
		Swap.swap(str);
		System.out.println(str[0]);
		System.out.println(str[1]);
	}

}
