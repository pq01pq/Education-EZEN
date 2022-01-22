import java.util.Calendar;
import java.util.Scanner;

public class Test03 {
	
	static Scanner scan;
	public static final boolean DEBUG = true;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.YEAR, 2021);
//		calendar.set(Calendar.MONTH, 4 - 1);
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		// 달력을 2021년 4월 1일로 지정함
		calendar.set(2021, 4 - 1, 1);
		
		// 달력의 한 주가 시작하는 요일 지정
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		
		// 달의 1일의 요일
		int firstWeekDay = calendar.get(Calendar.DAY_OF_WEEK);
		if(DEBUG) {
			System.out.println("이번달의 첫 요일 : " + firstWeekDay);
		}
		
		// 달력의 한 주가 시작하는 요일
		int weekDay = calendar.getFirstDayOfWeek();
		if(DEBUG) {
			System.out.println("한 주의 첫 요일 : " + weekDay);
		}
		
		System.out.println("\t\t\t4 월\t\t\t");
		System.out.println("일\t월\t화\t수\t목\t금\t토");
		
		// 1일 전까지 요일을 증가시키며 공백을 찍음
		while(weekDay < firstWeekDay) {
			System.out.print("\t");
			weekDay++;
		}
		
		// 달의 마지막 날짜
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int day = 1;
		while(day <= lastDay) {
			System.out.print(day + "\t");
			day++;
			weekDay++;
			if(weekDay % 7 == calendar.getFirstDayOfWeek()) {
				System.out.println();
				weekDay = calendar.getFirstDayOfWeek();
			}
		}
	}

}
