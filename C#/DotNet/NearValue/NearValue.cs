using System;
using System.Linq;

class NearAlgorithm
{
	static void Main()
	{
		// 로컬 함수
		/*
		int Abs(int num)
		{
			if(num < 0)
			{
				return -num;
			}
			return num;
		}
		*/
		int Abs(int num) => (num < 0) ? -num : num;

		int[] numbers = { 10, 20, 30, 27, 17 };

		int pivot = 25;
		int near = default;
		//Console.WriteLine(near);
		int minDiff = int.MaxValue;
		for (int i = 0; i < numbers.Length; i++)
		{
			int diff = Abs(numbers[i] - pivot);
			if(diff < minDiff)
			{
				minDiff = diff;
				near = numbers[i];
			}
		}
		
		Console.WriteLine($"{pivot}과 근삿값(문): {near}(차이:{minDiff})");
		var minDiff2 = numbers.Min(m => Abs(m - pivot));    // 차이의 최솟값
		var near2 = numbers.First(n => Abs(n - pivot) == minDiff2);
		Console.WriteLine($"{pivot}과 근삿값(식): {near2}(차이:{minDiff2})");
	}
}
