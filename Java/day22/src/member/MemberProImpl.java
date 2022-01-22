package member;

import java.sql.*;
import java.util.*;

public class MemberProImpl implements MemberPro{
//	HashSet hs;
	Scanner in;
	
	Connection con;
	String url, id, pw;
	PreparedStatement ps;
	ResultSet rs;
	
	public MemberProImpl() {
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
			
			System.out.print("�̸��� �Է� : ");
			String name = in.next();
			System.out.print("��ȭ��ȣ�� �Է� : ");
			String tel = in.next();
			
			String sql = "insert into java_member values(?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, tel);
			
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
			
			String sql = "select * from java_member";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				System.out.println("�̸�: " + name + " ��ȭ��ȣ: " + tel);
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
			
			System.out.print("������ ȸ�� : ");
			String name = in.next();
			
			String sql = "select name from java_member where name=?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			if(!rs.next()) {
				System.err.println("���� �̸�");
				Thread.sleep(1000);
				return;
			}
			
			sql = "delete from java_member where name=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("���� ����");
			} else {
				System.out.println("���� ���� : ���� �̸�");
			}
		} catch(SQLException | InterruptedException e) {
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
	public void update() {
		try {
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.print("������ ȸ�� : ");
			String name = in.next();
			
			String sql = "select name from java_member where name=?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(!rs.next()) {
				System.err.println("���� �̸�");
				Thread.sleep(1000);
				return;
			}
			
			System.out.print("������ ��ȭ��ȣ �Է� : ");
			String tel = in.next();
			
			sql = "update java_member set tel=? where name=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, tel);
			ps.setString(2, name);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("���� ����");
			} else {
				System.out.println("���� ����");
			}
		} catch (SQLException | InterruptedException e) {
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
