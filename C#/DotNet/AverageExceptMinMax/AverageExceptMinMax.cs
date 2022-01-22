using System;

class AverageExceptMinMax
{
	static void Main()
	{
		int[] scores = { 10, 20, 30, 40, 50 };
		int min = int.MaxValue;
		int max = int.MinValue;
		int sum = 0;
		for (int i = 0; i < scores.Length; i++)
		{
			if(scores[i] < min)
			{
				min = scores[i];
			}
			else if(scores[i] > max)
			{
				max = scores[i];
			}
			sum += scores[i];
		}
		double average = (double) (sum - min - max) / (scores.Length - 2);

		Console.WriteLine($"합계: {sum}, 최댓값: {max}, 최솟값: {min}");
		Console.WriteLine($"최댓값과 최솟값을 제외한 평균값: {average:F2}");
	}
}
