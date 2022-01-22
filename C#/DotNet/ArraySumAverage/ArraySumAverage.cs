using System;
using System.Collections.Generic;

class ArraySumAverage
{
	static void Main()
	{
		
		double[,] scores =
		{
			{ 90, 100, 0, 0 },
			{ 80, 90, 0, 0 },
			{ 100, 80, 0, 0}
		};
		//Console.WriteLine(scores.GetLength(0));
		//Console.WriteLine(scores.GetLength(1));
		for (int i = 0; i < scores.GetLength(0); i++)
		{
			scores[i, 2] = scores[i, 0] + scores[i, 1];
			scores[i, 3] = scores[i, 2] / 2.0;
		}

		Console.WriteLine("국어 영어 합계 평균");
		for (int i = 0; i < scores.GetLength(0); i++)
		{
			for (int j = 0; j < scores.GetLength(1); j++)
			{
				Console.Write($"{scores[i, j], 4} ");	// {출력할 값, 출력 길이}
			}
			Console.WriteLine();
		}
	}
}
