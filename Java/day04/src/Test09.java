import java.util.Arrays;

public class Test09 {

	public static void main(String[] args) {
		// 배열 선언
		int arr1[] = new int[5];
		System.out.println(Arrays.toString(arr1));
		int[] arr2 = new int[5];
		System.out.println(Arrays.toString(arr2));
		int arr3[];
		arr3 = new int[5];
		System.out.println(Arrays.toString(arr3));
		int[] arr4;
		arr4 = new int[5];
		System.out.println(Arrays.toString(arr4));
		// 배열 선언과 동시에 초기값 주기
		int arr5[] = new int[] {1, 2, 3, 4, 5};
		System.out.println(Arrays.toString(arr5));
		int arr6[];
		arr6 = new int[] {1, 2, 3, 4, 5};
		System.out.println(Arrays.toString(arr6));
		int[] arr7 = {1, 2, 3, 4, 5};	// 권장 안함
		System.out.println(Arrays.toString(arr7));
//		int arr8[];
//		arr8 = {1, 2, 3, 4, 5};	// 오류. 선언과 동시에 해야함
		
		// 배열의 값 꺼내기
		int[] arr = new int[5];
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;
		arr[3] = 40;
		arr[4] = 50;
		
		int[] array = new int[] {1, 2, 3, 4, 5};
//		System.out.println(array[5]);	// 인덱스값 참조 오류
		System.out.println(Arrays.toString(array));
		
		int array1[] = new int[5];
		System.out.println(array1[0]);
		// 배열을 선언하면 알아서 초기값이 이루어진다.
		// boolean -> false, 정수 -> 0, 실수 -> 0.0이 자동으로 입력
	}

}
