using System;
using System.Collections.Generic;
using System.Linq;

namespace RankAlgorithm
{
	class RankAlgorithm
	{
		static void Main()
		{
			int[] scores = { 90, 87, 100, 95, 80 };
			int[] rankings = Enumerable.Repeat(1, 5).ToArray();

			for (int i = 0; i < scores.Length; i++)
			{
				rankings[i] = 1;
				for (int j = 0; j < scores.Length; j++)
				{
					if(scores[i] < scores[j])
					{
						rankings[i]++;
					}
				}
			}

			for (int i = 0; i < rankings.Length; i++)
			{
				Console.WriteLine($"{scores[i], 3}점: {rankings[i]}등");
			}
		}
	}
}

/*
 * 나보다 값이 큰 것들의 갯수를 세서 +1 해주면 등수가 됨
int[] scores = { 90, 87, 100, 95, 80 };
var rankings = scores.Select(s => scores.Where(ss => ss > s).Count() + 1).ToArray();
rankings
int[5] { 3, 4, 1, 2, 5 }

 * 익명 타입 선언
var rankings = scores.Select(s => new { Score = s, Rank = scores.Where(ss => ss > s).Count() + 1});
rankings
Enumerable.WhereSelectArrayIterator<int, <>f__AnonymousType0#4<int, int>> { \{ Score = 90, Rank = 3 }, \{ Score = 87, Rank = 4 }, \{ Score = 100, Rank = 1 }, \{ Score = 95, Rank = 2 }, \{ Score = 80, Rank = 5 } }
*/
