using System;

// 소수
class PrimeNumber
{
	static void Main()
	{
		outer: while(true)
		{
			Console.Write("수 입력: ");
			int n;
			try
			{
				n = Convert.ToInt32(Console.ReadLine());
			} catch(FormatException)
			{
				Console.WriteLine("숫자만 입력");
				continue;
			}

			if (n <= 0)
			{
				Console.WriteLine("종료");
				break;
			} else if(n == 1)
			{
				Console.WriteLine("1은 기본수임");
				continue;
			}
			
			for(int i = 2; i <= Math.Sqrt(n); i++)
			{
				if(n % i == 0)
				{
					Console.WriteLine($"{n}은 소수 아님");
					goto outer;
				}
			}
			Console.WriteLine($"{n}은 소수임");
		}
	}
}
