package my.shop.mall;

import java.util.*;
import java.sql.*;

import my.db.ConnectionPoolBean;
import my.shop.*;

public class ProductList {
	private Map<String, List<ProductDTO>> map = new Hashtable<>();
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private ConnectionPoolBean pool;
	
	public ProductDTO getProduct(String listKey, int pnum) {
		List<ProductDTO> products = getList(listKey);
		for(ProductDTO product : products) {
			if(product.getPnum() == pnum) {
				return product;
			}
		}
		return null;
	}
	
	public List<ProductDTO> getList(String listKey) {
		return map.get(listKey);
	}
	
	public List<ProductDTO> select(String column, String key) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from product where " + column + "=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			List<ProductDTO> productList = list(rs);
			map.put(key, productList);
			return productList;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	public List<ProductDTO> search(String column, String key) throws SQLException {
		try {
			con = pool.getConnection();
			String sql = "select * from product where " + column + " like ?";
			ps = con.prepareStatement(sql);
			if(column.equals("pcategory_fk")) {
				ps.setString(1, key + "%");
			}
			rs = ps.executeQuery();
			List<ProductDTO> productList = list(rs);
			map.put(key, productList);
			return productList;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) pool.returnConnection(con);
		}
	}
	
	private List<ProductDTO> list(ResultSet rs) throws SQLException {
		List<ProductDTO> categories = new ArrayList<>();
		while(rs.next()) {
			ProductDTO product = new ProductDTO();
			product.setPnum(rs.getInt("pnum"));
			product.setPname(rs.getString("pname"));
			product.setPcategory_fk(rs.getString("pcategory_fk"));
			product.setPcompany(rs.getString("pcompany"));
			product.setPimage(rs.getString("pimage"));
			product.setPqty(rs.getInt("pqty"));
			product.setPrice(rs.getInt("price"));
			product.setPspec(rs.getString("pspec"));
			product.setPcontents(rs.getString("pcontents"));
			product.setPoint(rs.getInt("point"));
			product.setPinputdate(rs.getString("pinputdate"));
			categories.add(product);
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
