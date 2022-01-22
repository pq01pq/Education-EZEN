package sugang.server.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import sugang.model.*;

public class SubjectDAO implements IDAO<SubjectVO> {
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SubjectDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean insert(SubjectVO element) {
		try {
			String sql = "insert into "
					+ "subjects(title,code,professor,room,schedule,limit) values(?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, element.getTitle());
			ps.setString(2, element.getCode());
			ps.setString(3, element.getProfessor());
			ps.setString(4, element.getRoom());
			ps.setString(5, element.getSchedule());
			ps.setInt(6, element.getLimit());
			
			if(ps.executeUpdate() < 1) {
				return false;
			}
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("insert sql 오류");
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public ArrayList<SubjectVO> select(String column, String key) {
		ArrayList<SubjectVO> subjects = new ArrayList<>();
		
		String sql = "select * from subjects where " + column + "=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				subjects.add(new SubjectVO(
						rs.getString("title"),
						rs.getString("code"),
						rs.getString("professor"),
						rs.getString("room"),
						rs.getString("schedule"),
						rs.getInt("limit")));
			}
		} catch(SQLException e) {
			System.out.println("select(primaryKey) sql 오류");
			e.printStackTrace();
		}
		
		return subjects;
	}
	
	@Override
	public ArrayList<SubjectVO> select(String primaryKey) {
		ArrayList<SubjectVO> subjects = new ArrayList<>();
		
		String sql = "select * from subjects where code=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, primaryKey);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				subjects.add(new SubjectVO(
						rs.getString("title"),
						rs.getString("code"),
						rs.getString("professor"),
						rs.getString("room"),
						rs.getString("schedule"),
						rs.getInt("limit")));
			}
		} catch(SQLException e) {
			System.out.println("select(primaryKey) sql 오류");
			e.printStackTrace();
		}
		
		return subjects;
	}
	
	@Override
	public ArrayList<SubjectVO> search(String column, String key) {
		ArrayList<SubjectVO> subjects = new ArrayList<>();
		
		String sql = "select * from subjects where " + column + " like ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				subjects.add(new SubjectVO(
						rs.getString("title"),
						rs.getString("code"),
						rs.getString("professor"),
						rs.getString("room"),
						rs.getString("schedule"),
						rs.getInt("limit")));
			}
		} catch(SQLException e) {
			System.out.println("select(column, key) sql 오류");
			e.printStackTrace();
		}
		
		return subjects;
	}
	
	@Override
	public ArrayList<SubjectVO> search(String key) {
		ArrayList<SubjectVO> subjects = new ArrayList<>();
		
		String sql = "select * from subjects where"
				+ " title like ?"
				+ " or code like ?"
				+ " or professor like ?"
				+ " or room like ?"
				+ " or schedule like ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, key);
			ps.setString(3, key);
			ps.setString(4, key);
			ps.setString(5, key);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				subjects.add(new SubjectVO(
						rs.getString("title"),
						rs.getString("code"),
						rs.getString("professor"),
						rs.getString("room"),
						rs.getString("schedule"),
						rs.getInt("limit")));
			}
		} catch(SQLException e) {
			System.out.println("select(key) sql 오류");
			e.printStackTrace();
		}
		
		return subjects;
	}
	
	// same as : select * from subjects where [column] like '%%';
	@Override
	public ArrayList<SubjectVO> searchAll() {
		ArrayList<SubjectVO> subjects = new ArrayList<>();
		
		String sql = "select * from subjects";
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				subjects.add(new SubjectVO(
						rs.getString("title"),
						rs.getString("code"),
						rs.getString("professor"),
						rs.getString("room"),
						rs.getString("schedule"),
						rs.getInt("limit")));
			}
		} catch(SQLException e) {
			System.out.println("select(all) sql 오류");
			e.printStackTrace();
		}
		
		return subjects;
	}

	@Override
	public boolean update(Hashtable<String, String> updateTable, String column, String key) {
		String[] columns = new String[updateTable.size()];
		String sql = "update subjects set ";
		Enumeration<String> en = updateTable.keys();
		int i = 0;
		while(en.hasMoreElements()) {
			columns[i] = new String(en.nextElement());
			sql += columns[i] + "=?";
			if(en.hasMoreElements()) {
				sql += ", ";
			}
			i++;
		}
		sql += "where " + column + "=?";
		try {
			ps = connection.prepareStatement(sql);
			for(i = 1; i <= updateTable.size(); i++) {
				if(columns[i - 1].equals("regNumber") || columns[i - 1].equals("limit")) {
					ps.setInt(i, Integer.parseInt(updateTable.get(columns[i - 1])));
				} else {
					ps.setString(i, updateTable.get(columns[i - 1]));
				}
			}
			ps.setString(i, key);
			
			if(ps.executeUpdate() < 1) {
				return false;
			}
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("update sql 오류");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String column, String key) {
		String sql = "delete from subjects where code=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, key);
			
			if(ps.executeUpdate() < 1) {
				return false;
			}
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
	

}
