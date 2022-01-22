package mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MariaDBinsert {
	
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
			
			String sql = "select * from test";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("id") + "," + rs.getString("name"));
			}
			
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)	rs.close();
			if(ps != null)	ps.close();
			if(con != null)	con.close();
		}
	}

}
