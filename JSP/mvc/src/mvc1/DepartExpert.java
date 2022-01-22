package mvc1;

import java.util.ArrayList;
import java.util.List;

public class DepartExpert {
	
	public List<String> getAdvice(String depart){
		List<String> list = new ArrayList<String>();
		switch(depart) {
		case "c":
			list.add("1971년 벨 연구소에 의해 개발된 시스템 프로그래밍 언어");
			list.add("프로그램 기초 및 절차지향 프로그램 공부할 때 많이 사용");
			list.add("현재는 C++언어와 같이 사용되고 있다.");
			break;
		case "java":
			list.add("객체지향언어이다.");
			list.add("문법이 간단하다.");
			list.add("보안에 강하다.");
			list.add("아키텍처가 중립적이다.");
			list.add("이식성이 높다.");
			break;
		case "python":
			list.add("귀도 반 로섬이 만든 언어");
			list.add("c언어가 어려워서 쉬운 언어를 만든 언어");
			list.add("이름은 영국 코미디 프로 앞글자를 따서 만든 언어");
			break;
		case "go":
			
			break;
		}
		
		return list;
	}
}
