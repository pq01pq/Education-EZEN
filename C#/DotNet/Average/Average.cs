using System;

class Average
{
	static void Main()
	{
		int[] data = { 90, 65, 78, 50, 95 };
		int sum = 0;
		int count = 0;

		for(int i = 0; i < data.Length; i++)
		{
			if(data[i] >= 80 && data[i] <= 95)
			{
				sum += data[i];
				count++;
			}
		}

		double average = sum / (double) count;

		Console.WriteLine($"80점 이상 95점 이하인 자료의 평균: {average}");
	}
}

/*
Linq
int[] data = { 90, 65, 78, 50, 95 };
data.Where(d => d >= 80 && d <= 95).Average()
92.5
 */