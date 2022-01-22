using System;
using System.Collections.Generic;
using System.Linq;

// 선택정렬
namespace SortAlgorithm
{
	class SortAlgorithm
	{
		static void Swap(int[] array, int i, int j)
		{
			int temp = array[j];
			array[j] = array[i];
			array[i] = temp;
		}

		static void Main()
		{
			int[] data = { 3, 2, 1, 4, 5 };
			int N = data.Length;

			for (int i = 0; i < N - 1; i++)
			{
				for (int j = i + 1; j < N; j++)
				{
					if(data[j] < data[i])
					{
						Swap(data, i, j);
					}
				}
			}

			for (int i = 0; i < N; i++)
			{
				Console.Write($"{data[i]}\t");
			}
		}
	}
}

/* Linq
int[] data = { 3, 2, 1, 4, 5 };
Array.Sort(data);
data
int[5] { 1, 2, 3, 4, 5 }

int[] data = { 3, 2, 1, 4, 5 };
var sort = data.OrderBy(n => n).ToArray();
sort
int[5] { 1, 2, 3, 4, 5 }
 */