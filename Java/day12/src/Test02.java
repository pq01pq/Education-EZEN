import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test02 {

	@SuppressWarnings({ "unused", "deprecation" })
	public static void main(String[] args) {
		Date date = new Date();
		// 1900년 1월 1일 0초를 기준으로 날짜를 계산
		System.out.println(date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// y-년도, M-월, d-일, H-0~23시, m-분, s-초, S-밀리초
		System.out.println(sdf.format(date));
		
		Date date2 = new Date(2021, 4, 21);
		// deprecated : 앞으로 없어질 문법일 수 있다
		
		int year = date.getYear();
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal);
		
		int year2 = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		System.out.printf("%d-%d-%d\n", year2, month, day);
	}

}
