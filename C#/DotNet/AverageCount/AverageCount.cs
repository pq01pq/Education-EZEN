using System;

// 펑균 이상 학생 수
class AverageCount
{
	static void Main()
	{
		int[] data = { 90, 65, 78, 50, 95 };
		int sum = 0;
		double average = 0.0;
		int count = 0;

		for (int i = 0; i < data.Length; i++)
		{
			sum += data[i];
		}
		average = (double)sum / data.Length;
		for (int i = 0; i < data.Length; i++)
		{
			if((double)data[i] > average)
			{
				count++;
			}
		}
		Console.WriteLine($"평균 {average}점 이상 학생 수: {count}명");
	}
}
