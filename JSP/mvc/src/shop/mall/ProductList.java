package shop.mall;

import java.util.*;

import javax.naming.*;
import javax.sql.*;

import java.sql.*;
import shop.*;

public class ProductList {
	private static DataSource ds;
	static {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");	// java:comp/env/ : 라이브러리를 찾아가는 경로
		} catch(NamingException e) {
			System.err.println("lookup실패" + e.getMessage());
		}
	}
	
	private Map<String, List<ProductDTO>> map;
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private ProductList() {
		map = new Hashtable<>();
	}
	private static ProductList instance = new ProductList();
	public static ProductList getInstance() {
		return instance;
	}
	
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
			con = ds.getConnection();
			String sql = "select * from product where " + column;
			if(column.equals("pcategory_fk")) {
				key += "%";
				sql += " like ?";
			} else {
				sql += "=?";
			}
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			List<ProductDTO> productList = list(rs);
			if(column.equals("pcategory_fk")) {
				key = key.substring(0, key.length() - 1);
			}
			map.put(key, productList);
			return productList;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}
	}
	
	public List<ProductDTO> search(String column, String key) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from product where " + column + " like ?";
			ps = con.prepareStatement(sql);
			key = "%" + key + "%";
			ps.setString(1, key);
			rs = ps.executeQuery();
			List<ProductDTO> productList = list(rs);
			map.put(key, productList);
			return productList;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
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

}
