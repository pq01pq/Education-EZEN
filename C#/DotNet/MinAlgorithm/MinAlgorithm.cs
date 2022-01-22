using System;
using System.Linq;
using static System.Console;

class MinAlgorithm
{
	static void Main()
	{
		int[] numbers = { 0b_0010, 0b_0101, 0b_0011, 0b_0111, 0b_0001 };
		int min= Int32.MaxValue;

		for (int i = 0; i < numbers.Length; i++)
		{
			if (numbers[i] < min && numbers[i] % 2 == 0)
			{
				min = numbers[i];
			}
		}

		WriteLine($"최솟값(식): {numbers.Where(n => n % 2 == 0).Min()}");
		WriteLine($"최솟값(문): {min}");
	}
}
