using System;
using System.Linq;

/// <summary>
/// 계수 알고리즘
/// 1부터 100까지의 정수 중 13의 배수의 갯수
/// </summary>
class CountAlgorithm
{
	static void Main()
	{
		int[] numArray = Enumerable.Range(1, 1000).ToArray();
		int count = 0;
		for (int i = 0; i < numArray.Length; i++)
		{
			if (numArray[i] % 13 == 0)
			{
				count++;
			}
		}
		Console.WriteLine($"1부터 1000까지 13의 배수: {count}개");
		
		// 표현식
		// Enumerable.Range(1, 1000).ToArray().Where(n => n % 13 == 0).Count()
		// Enumerable.Range(1, 1000).ToArray().Count(n => n % 13 == 0)
	}
}
