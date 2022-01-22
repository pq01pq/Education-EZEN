package book_traditional;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class BookProImpl implements BookPro{
//	HashSet hs;
	Scanner in;
	
	Connection con;
	String url, id, pw;
	PreparedStatement ps;
	ResultSet rs;
	
	public BookProImpl() {
//		hs = new HashSet();
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		id = "bigdata3";
		pw = "bigdata3";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		in = new Scanner(System.in);
	}
	
	@Override
	public void input() {
		try {
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.print("������ : ");
			String bookName = in.next();
			System.out.print("������ : ");
			String writer = in.next();
			System.out.print("���ǻ� : ");
			String publisher = in.next();
			System.out.print("���� : ");
			int price = in.nextInt();
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String inDate = sdf.format(date);
			
			String sql = "insert into book values(?,?,?,?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, bookName);
			ps.setString(2, writer);
			ps.setString(3, publisher);
			ps.setInt(4, price);
			ps.setString(5, inDate);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("���� �Ϸ�");
			} else {
				System.out.println("���� ����");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
//		Member input = new Member(name,tel);
//		hs.add(input);
	}

	@Override
	public void view() {
		try {
			con = DriverManager.getConnection(url, id, pw);
			
			String sql = "select * from book";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String bookName = rs.getString(1);
				String writer = rs.getString(2);
				String publisher = rs.getString(3);
				int price = rs.getInt(4);
				String inDate = rs.getString(5);
				System.out.println("������: " + bookName);
				System.out.println("������: " + writer);
				System.out.println("���ǻ�: " + publisher);
				System.out.println("����: " + price);
				System.out.println("�԰���: " + inDate);
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Iterator it = hs.iterator();
//		while(it.hasNext()) {
//			Object obj = it.next();
//			Member view = (Member)obj;
//			view.disp();
//		}
	}

	@Override
	public void delete() {
		try {
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.print("������ å : ");
			String bookName = in.next();
			
			String sql = "delete from book where bookname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookName);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("���� ����");
			} else {
				System.err.println("���� ����");
				Thread.sleep(1000);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Iterator it = hs.iterator();
//		while(it.hasNext()) {
//			Object obj = it.next();
//			Member delete = (Member)obj;
//			if (name.equals(delete.getName())) {
//				hs.remove(delete);
//				System.out.println(name+"���� �����Ͽ����ϴ�.");
//				return;
//			}
//		}
//		System.out.println(name+"���� ���� ȸ���� �ƴմϴ�.");
	}

	@Override
	public void search() {
		try {
			con = DriverManager.getConnection(url, id, pw);
			
			String sql = "";
			String keyword = "";
			
			System.out.print("�˻� ��� 1.������ 2.������ 3.���ǻ�: ");
			int searchMethod = in.nextInt();
			switch(searchMethod) {
			case 1:
				sql = "select * from book where bookname like ?";
				System.out.print("������: ");
				break;
			case 2:
				sql = "select * from book where writer like ?";
				System.out.print("������: ");
				break;
			case 3:
				sql = "select * from book where publisher like ?";
				System.out.print("���ǻ�: ");
				break;
			}
			keyword = "%" + in.next() + "%";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, keyword);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String bookName = rs.getString(1);
				String writer = rs.getString(2);
				String publisher = rs.getString(3);
				int price = rs.getInt(4);
				String inDate = rs.getString(5);
				System.out.println("������: " + bookName);
				System.out.println("������: " + writer);
				System.out.println("���ǻ�: " + publisher);
				System.out.println("����: " + price);
				System.out.println("�԰���: " + inDate);
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Iterator it = hs.iterator();
//		while(it.hasNext()) {
//			Object obj = it.next();
//			Member update = (Member)obj;
//			if (name.equals(update.getName())) {
//				System.out.print("������ ��ȭ��ȣ �Է� : ");
//				String tel = in.next();
//				update.setTel(tel);
//				System.out.println(name+"���� ��ȭ��ȣ�� �����Ͽ����ϴ�.");
//				return;
//			}
//		}
//		System.out.println(name+"���� ���� ȸ���� �ƴմϴ�.");
	}
			

	@Override
	public void exit() {
		System.out.println("���α׷��� �����մϴ�.");
		System.exit(0);
		
	}

}
