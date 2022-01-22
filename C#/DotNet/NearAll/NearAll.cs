using System;
using System.Collections.Generic;

class NearAll
{
	static void Main(string[] args)
	{
		int[] data = { 10, 20, 23, 27, 17 };
		int pivot = 25;
		List<int> nears = new List<int>();
		int minDiff = int.MaxValue;

		for (int i = 0; i < data.Length; i++)
		{
			int diff = Math.Abs(Math.Abs(data[i] - pivot));
			if(diff < minDiff)
			{
				minDiff = diff;
			}
		}

		Console.WriteLine($"차이의 최솟값: {minDiff}");

		for (int i = 0;  i < data.Length;  i++)
		{
			if(Math.Abs(data[i] - pivot) == minDiff)
			{
				nears.Add(data[i]);
			}
		}

		foreach (int near in nears)
		{
			Console.WriteLine(near);
		}
	}
}
