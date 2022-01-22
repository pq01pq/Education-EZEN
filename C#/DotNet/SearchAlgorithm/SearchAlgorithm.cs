using System;
using System.Collections.Generic;
using System.Linq;

namespace SearchAlgorithm
{
	class SearchAlgorithm
	{
		static void Main()
		{
			int[] data = { 1, 3, 5, 7, 9 };	// 이미 정렬된 데이터

			outer: while (true)
			{
				int key;
				try
				{
					Console.Write("수 입력 >");
					key = int.Parse(Console.ReadLine());
				}
				catch (FormatException e)
				{
					Console.WriteLine(e.Message);
					continue;
				}

				int low = 0;
				int high = data.Length - 1;
				while (low <= high)
				{
					int mid = (low + high) / 2;
					if (data[mid] < key)
					{
						low = mid + 1;
					}
					else if(data[mid] > key)
					{
						high = mid - 1;
					}
					else
					{
						Console.WriteLine($"found (index={mid})");
						goto outer;
					}
				}
				Console.WriteLine("not found");
			}
			
		}
	}
}

/*
Linq

int[] data = { 1, 3, 5, 7, 9 };
var result = data.ToList().BinarySearch(9);
result
4
var result = data.ToList().BinarySearch(11);
result
-6
*/
