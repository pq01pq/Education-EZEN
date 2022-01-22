
import java.sql.*;

public class Test05 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("오라클 설치 완료");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "bigdata3", "bigdata3");
			// > conn bigdata3/bigdata3;					// id		// pw
			System.out.println("오라클 연결 성공");
		} catch(SQLException e) {
			System.out.println("오라클 연결 실패");
			e.printStackTrace();
		}
		
		// 쿼리 작성 : 동적 쿼리 방식만 사용 - PreparedStatement
		PreparedStatement ps = null;	// 전송용 클래스
		ResultSet rs = null;
		try {
//			String sql = "insert into test values(?,?,?)";
//			// 컬럼명은 풀네임을 써줘야하지만 값은 물음표로 처리할 수 있음
//			
//			ps = con.prepareStatement(sql);	// 준비를 시킨다
//			ps.setString(1, "홍길동");
//			ps.setString(2, "123-9999");
//			ps.setString(3, "마포구 아현동");
//			int res = ps.executeUpdate();
//			// insert, delete, update의 결과는 실행된 갯수가 나오므로 int형으로 받음
//			// 그리고 실행되는 메서드는 executeUpdate()
//			
//			// select일경우에는 ResultSet으로 받아야하고
//			// 실행되는 메서드는 exequteQuery()
//			
//			if(res > 0){
//				System.out.println("홍길동 입력 성공");
//			} else {
//				System.out.println("홍길동 입력 실패");
//			}
			/*
			String sql = "select * from test";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	// rs : 첫번째 결과값의 위치를 가리킴
			while(rs.next()) {
//				String name = rs.getString("name");
//				String tel = rs.getString("tel");
//				String addr = rs.getString("addr");
				String name = rs.getString(1);
				String tel = rs.getString(2);
				String addr = rs.getString(3);
				// 문자열로하면 컬럼명, 숫자로하면 컬럼 인덱스
				
				System.out.println(name + "\t" + tel + "\t" + addr);
			}
			*/
			
			String sql = "select max(i) from test4";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(1));
//			while(rs.next()) {
//				System.out.println(rs.getInt("max"));
//				System.out.println(rs.getInt(1));
//				System.out.println(rs.getString("max"));
//				System.out.println(rs.getString(1));
//			}
			
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
