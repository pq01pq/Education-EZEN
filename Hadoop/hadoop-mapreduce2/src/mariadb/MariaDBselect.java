package mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MariaDBselect {
	
	public static void main(String[] args) throws SQLException {
		final String driver = "org.mariadb.jdbc.Driver";
		final String dbName = "teambitdb";
		final String url = "jdbc:mariadb://localhost:3306/" + dbName;
		final String user = "teambit";
		final String password = "teambit";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "insert into test(id,name,password) values"
					+ "(4,4,4),"
					+ "(5,5,5),"
					+ "(6,6,6),"
					+ "(7,7,7)";
			
			ps = con.prepareStatement(sql);
			int result = ps.executeUpdate();
			
			System.out.println("실행횟수 : " + result);
			
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)	rs.close();
			if(ps != null)	ps.close();
			if(con != null)	con.close();
		}
		
		System.out.println(airlinePerformanceCounter.DelayCounters.EARLY_ARRIVAL.compareTo(airlinePerformanceCounter.DelayCounters.EARLY_DEPARTURE));
	}

}
