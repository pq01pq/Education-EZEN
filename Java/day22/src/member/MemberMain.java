package member;

import java.io.*;

public class MemberMain {
	public static void main(String[] args) throws IOException {
		MemberPro pro = new MemberProImpl();
		
		while(true) {
			System.out.print("1.입력 2.출력 3.삭제 4.수정 5.종료 : ");
			int select = System.in.read() - 48;
			System.in.skip(5);
			switch(select) {
			case 1 :	pro.input(); break;
			case 2 :	pro.view(); break;
			case 3 :	pro.delete(); break;
			case 4 :	pro.update(); break;
			case 5 :	pro.exit(); break;
			default :	System.out.println("잘못입력하셨습니다. 다시 입력해 주세요!!");
			}
		}
	}
}
