using System;

// 완전수 : 자신을 제외한 약수의 합이 자신과 같은 수
// 6 = 1 + 2 + 3
class PerfectNumber
{
	static void Main()
	{
		while (true)
		{
			int sum;
			int max;    // 가장 큰 약수
			int rem;    // 나머지값 임시 보관
			int count = 0;

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

			for(int i = 2; i <= n; i++)
			{
				sum = 0;
				max = i / 2; // 모든 짝수를 2로 나누면 가장 큰 약수를 구할 수 있음
				for (int j = 1; j <= max; j++)
				{
					rem = i % j;
					if(rem == 0)
					{
						sum += j;
					}
				}

				if(i == sum)
				{
					Console.WriteLine("완전수 : {0}", i);
					count++;
				}
			}

			Console.WriteLine("완전수 갯수 : {0}", count);
		}
	}
}
/*
수 입력: 10000
완전수 : 6
완전수 : 28
완전수 : 496
완전수 : 8128
완전수 갯수 : 4
 */