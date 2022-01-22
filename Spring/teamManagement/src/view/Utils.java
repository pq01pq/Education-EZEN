package view;

import org.apache.commons.lang.StringUtils;

public class Utils {
	public static boolean isEmpty(String str, String field) {
		if(StringUtils.isEmpty(str)) {
			System.out.println(field + "에 값을 입력해주세요");
			return true;
		}
		return false;
	}
	
	public static boolean isNumeric(String str, String field) {
		if(StringUtils.isNumeric(str)) {
			return true;
		}
		System.out.println(field + "에는 숫자만 입력해주세요");
		return false;
	}
	
	public static boolean isSmallLength(String name, String field, int length) {
		if(name == null) {
			return false;
		}
		if(length < name.length()) {
			System.out.println(field + "는 " + length + "문자 이하로 입력해주세요");
			return false;
		}
		return true;
	}
}
