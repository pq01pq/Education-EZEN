using System;

/// <summary>
/// 합계 알고리즘
/// </summary>
class SumAlgorithm
{
	static void Main()
	{
		// 1. 입력 : n명의 국어 점수
		int[] scores = { 100, 75, 50, 37, 90, 95 };

		// 2. 처리
		int sum = 0;
		for(int i = 0; i < scores.Length; i++)
		{
			if(scores[i] >= 80)
			{
				sum += scores[i];
			}
		}

		// 3 출력
		Console.WriteLine($"{scores.Length}명의 점수 중 80점 이상의 총점: {sum}점");

		// C# interactive 콘솔에서 입력
		// (new int[] { 100, 75, 50, 37, 90, 95 }).Where(s => s >= 80).Sum() // 표현식(람다식 사용)
		// s => s >= 80 (s goes to (s >= 80)) : s >= 80인 s 들에 대하여

		// 디버거(디버깅 s/w)를 사용하여 디버깅하기 : F9 -> F5 -> F11 -> F5
	}
}