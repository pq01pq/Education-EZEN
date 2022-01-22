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
			
			System.out.print("이름을 입력 : ");
			String name = in.next();
			System.out.print("전화번호를 입력 : ");
			String tel = in.next();
			
			String sql = "insert into java_member values(?,?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, tel);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("저장 완료");
			} else {
				System.out.println("저장 실패");
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
				System.out.println("이름: " + name + " 전화번호: " + tel);
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
			
			System.out.print("삭제할 회원 : ");
			String name = in.next();
			
			String sql = "select name from java_member where name=?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			if(!rs.next()) {
				System.err.println("없는 이름");
				Thread.sleep(1000);
				return;
			}
			
			sql = "delete from java_member where name=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패 : 없는 이름");
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
//				System.out.println(name+"님을 삭제하였습니다.");
//				return;
//			}
//		}
//		System.out.println(name+"님은 저희 회원이 아닙니다.");

	}

	@Override
	public void update() {
		try {
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.print("수정할 회원 : ");
			String name = in.next();
			
			String sql = "select name from java_member where name=?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(!rs.next()) {
				System.err.println("없는 이름");
				Thread.sleep(1000);
				return;
			}
			
			System.out.print("수정할 전화번호 입력 : ");
			String tel = in.next();
			
			sql = "update java_member set tel=? where name=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, tel);
			ps.setString(2, name);
			
			if(ps.executeUpdate() > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
//		Iterator it = hs.iterator();
//		while(it.hasNext()) {
//			Object obj = it.next();
//			Member update = (Member)obj;
//			if (name.equals(update.getName())) {
//				System.out.print("수정할 전화번호 입력 : ");
//				String tel = in.next();
//				update.setTel(tel);
//				System.out.println(name+"님의 전화번호를 수정하였습니다.");
//				return;
//			}
//		}
//		System.out.println(name+"님은 저희 회원이 아닙니다.");
	}
			

	@Override
	public void exit() {
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);
		
	}

}
