using System;

class PrimeNumberCount
{
	static void Main()
	{
		while (true)
		{
			Console.Write("수 입력: ");
			int n;
			try
			{
				n = Convert.ToInt32(Console.ReadLine());
			}
			catch (FormatException)
			{
				Console.WriteLine("숫자만 입력");
				continue;
			}

			if (n <= 0)
			{
				Console.WriteLine("종료");
				break;
			}
			else if (n == 1)
			{
				Console.WriteLine("1은 기본수임");
				continue;
			}

			int count = 0;
			for (int i = 2; i <= n; i++)
			{
				int j = 2;
				while (j <= Math.Sqrt(i))
				{
					if (i % j == 0)
					{
						break;
					}
					j++;
				}

				if(j > Math.Sqrt(i))
				{
					Console.Write($"{i}\t");
					count++;
					if(count % 5 == 0)
					{
						Console.WriteLine();
					}
				}
			}
			Console.WriteLine();
			Console.WriteLine($"2부터 {n}까지 소수의 갯수: {count}개");
		}
	}
}

