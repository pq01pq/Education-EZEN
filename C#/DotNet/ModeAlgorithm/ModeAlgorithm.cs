using System;
using System.Collections.Generic;
using System.Linq;

/// <summary>
/// 최빈값 알고리즘 : 점수 인덱스(0~n)의 갯수의 최댓값
/// </summary>
namespace ModeAlgorithm
{
	class ModeAlgorithm
	{
		static void Main()
		{
			int[] data = { 1, 4, 7, 9, 3, 4, 7, 0, 9, 8, 7, 2, 3, 5, 4, 8, 7, 6, 5, 3, 9, 2, 3, 1, 4, 7, 0, 9, 5, 4, 2, 7, 7 };
			/*
			중복 제거
			data.ToList().Distinct()
			DistinctIterator { 1, 4, 7, 9, 3, 0, 8, 2, 5, 6 }
			 */

			var set = data.ToHashSet();
			var dict = new Dictionary<int, int>();

			foreach (var item in set)
			{
				dict[item] = 0;
			}
			/*foreach(var item in dict)
			{
				Console.WriteLine(item);
			}*/

			for (int i = 0; i < data.Length; i++)
			{
				dict[data[i]]++;
			}


			var max = dict.First();
			foreach (var item in dict)
			{
				Console.WriteLine(item);
				if(item.Value > max.Value)
				{
					max = item;
				}
			}
			Console.WriteLine($"max: {max.Key}이 {max.Value}번 나옴");
		}
	}
}

/*
Linq

var q = data.GroupBy(v => v).OrderByDescending(g => g.Count()).First();
int frequent = q.Key;
int modeCount = q.Count();
 */
