using System;
using System.Linq;

class MaxAlgorithm
{
	static void Main()
	{
		int[] numbers = { -2, -5, -3, -7, -1 };
		int max = int.MinValue;

		for (int i = 0; i < numbers.Length; i++)
		{
			if(numbers[i] > max)
			{
				max = numbers[i];
			}
		}

		Console.WriteLine($"최댓값(식): {numbers.Max()}");
		Console.WriteLine($"최댓값(문): {max}");
	}
}
