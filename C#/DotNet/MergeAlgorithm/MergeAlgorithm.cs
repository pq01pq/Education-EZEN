using System;
using System.Collections.Generic;
using System.Linq;

// 오름차순으로 정렬되어있는 정수 배열을 하나로 병합
namespace MergeAlgorithm
{
	class MergeAlgorithm
	{
		static void Main()
		{
			int[] array1 = { 1, 4, 5, 7, 10 };
			int[] array2 = { 2, 3, 6, 8, 9 };
			int m = array1.Length;
			int n = array2.Length;
			int[] merge = new int[m + n];
			int i = 0, j = 0, k = 0;

			while (i < m && j < n)
			{
				if (array1[i] < array2[j])
				{
					merge[k] = array1[i];
					i++;
				}
				else
				{
					merge[k] = array2[j];
					j++;
				}
				k++;
			}

			if (i == m)
			{
				while (j < n)
				{
					merge[k] = array2[j];
					j++;
					k++;
				}
			}
			else
			{
				while (i < m)
				{
					merge[k] = array1[i];
					i++;
					k++;
				}
			}

			foreach (int item in merge)
			{
				Console.WriteLine($"{item}\t");
			}
		}
	}
}

/*
Linq

int[] array1 = { 1, 4, 5, 7, 10 };
int[] array2 = { 2, 3, 6, 8, 9 };

int[] merge = array1.Union(array2).OrderBy(n => n).ToArray();
merge
int[10] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }

merge = (from s in array1 select s).Union(from t in array2 select t).OrderBy(m => m).ToArray();
merge
int[10] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }
 */
