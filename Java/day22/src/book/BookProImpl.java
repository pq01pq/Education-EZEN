package book;

import java.util.*;
import java.text.*;

public class BookProImpl implements BookPro {
	
	BookDAO dao;
	Scanner in;
	public BookProImpl() {
		dao = new BookDAO();
		in = new Scanner(System.in);
	}
	
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		System.out.print("�������� �Է� : ");
		String bookname = in.next();
		System.out.print("�����̸� �Է� : ");
		String writer = in.next();
		System.out.print("���ǻ縦 �Է� : ");
		String publisher = in.next();
		System.out.print("�ǸŰ��� �Է� : ");
		int price = in.nextInt();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int res = dao.insertBook(bookname, writer, publisher, price, sdf.format(date));
		if (res>0) {
			System.out.println(bookname+"������ �߰��Ͽ����ϴ�.");
		}else {
			System.out.println(bookname+"���� �߰� ����!!");
		}
	}

	@Override
	public void view() {
		// TODO Auto-generated method stub
		ArrayList<BookDTO> list = dao.listBook();
		for(BookDTO dto : list) {
			System.out.print(dto.getBookname()+"\t");
			System.out.print(dto.getWriter() + "\t");
			System.out.print(dto.getPublisher() + "\t");
			System.out.print(dto.getPrice()+"\t");
			System.out.println(dto.getIndate());
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		System.out.print("������ �������� �Է� : ");
		String bookname = in.next();
		int res = dao.deleteBook(bookname);
		if (res>0) {
			System.out.println(bookname+"������ �����Ͽ����ϴ�.");
		}else {
			System.out.println(bookname+"�������� ���� ������ �����ϴ�.");
		}
	}

	@Override
	public void search() {
		System.out.print("1.������ 2.������ 3.���ǻ� : ");
		int select = in.nextInt();
		System.out.print("ã�� �̸� : ");
		String searchString = in.next();
		ArrayList<BookDTO> list = null;
		switch(select) {
		case 1 : list = dao.searchBook("bookname", searchString); break;
		case 2 : list = dao.searchBook("writer", searchString); break;	
		case 3 : list = dao.searchBook("publisher", searchString); break;
		}
		for(BookDTO dto : list) {
			System.out.print(dto.getBookname()+"\t");
			System.out.print(dto.getWriter() + "\t");
			System.out.print(dto.getPublisher() + "\t");
			System.out.print(dto.getPrice()+"\t");
			System.out.println(dto.getIndate());
		}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		System.out.println("���α׷��� �����մϴ�.!!");
		System.exit(0);
	}

}
