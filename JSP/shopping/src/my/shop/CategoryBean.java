package my.shop;

import java.util.*;

import my.db.ConnectionPoolBean;

import java.sql.*;

public class CategoryBean {
	private ConnectionPoolBean pool;
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public int insert(CategoryDTO category) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "insert into category(cnum,code,cname) values(cate_seq.nextval,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, category.getCode());
			ps.setString(2, category.getCname());
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<CategoryDTO> searchAll() throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from category";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<CategoryDTO> select(String column, String key) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from category where " + column + "=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			return list(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	
	public int delete(int cnum) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "delete from category where cnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cnum);
			return ps.executeUpdate();
		} finally {
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	private List<CategoryDTO> list(ResultSet rs) throws SQLException {
		List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
		while(rs.next()) {
			CategoryDTO category = new CategoryDTO();
			category.setCnum(rs.getInt("cnum"));
			category.setCode(rs.getString("code"));
			category.setCname(rs.getString("cname"));
			categories.add(category);
		}
		return categories;
	}

	public ConnectionPoolBean getPool() {
		return pool;
	}

	public void setPool(ConnectionPoolBean pool) {
		this.pool = pool;
	}

}
