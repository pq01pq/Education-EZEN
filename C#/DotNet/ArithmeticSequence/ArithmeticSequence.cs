using System;

/// <summary>
/// 등차수열
/// 1부터 20까지의 정수 중 홀수의 합
/// </summary>
class ArithmeticSequence
{
	static void Main()
	{
		int sum = 0;
		for(int i = 1; i <= 20; i++)	// 주어진 범위
		{
			if(i % 2 == 1)	// 주어진 조건
			{
				sum += i;
				Console.Write("{0, 3}", i);	// 0번째 자리부터 3칸 출력
			}
		}
		Console.WriteLine($"\n1부터 20까지 홀수의 합: {sum}");
	}
}
