package book_traditional;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BookPro pro = new BookProImpl();
		
		while(true) {
			System.out.print("1.�Է� 2.��� 3.���� 4.ã�� 5.���� : ");
			int select = System.in.read() - 48;
			System.in.skip(5);
			switch(select) {
			case 1 :	pro.input(); break;
			case 2 :	pro.view(); break;
			case 3 :	pro.delete(); break;
			case 4 :	pro.search(); break;
			case 5 :	pro.exit(); break;
			default :	System.out.println("�߸��Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���!!");
			}
		}
	}
}
