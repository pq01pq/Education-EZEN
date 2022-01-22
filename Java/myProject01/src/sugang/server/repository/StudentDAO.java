package sugang.server.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import sugang.model.*;

public class StudentDAO implements IDAO<StudentVO> {
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public StudentDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean insert(StudentVO element) {
		String sql = "insert into students(name,id) values(?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, element.getName());
			ps.setString(2, element.getId());
			
			if(ps.executeUpdate() < 1) {
				return false;
			}
			
			return true;
		} catch(SQLException e) {
			System.out.println("insert sql 오류");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public ArrayList<StudentVO> select(String column, String key) {
		String sql = "select * from students where " + column + "=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			
			ArrayList<StudentVO> students = new ArrayList<>();
			
			while(rs.next()) {
				StudentVO student = new StudentVO(rs.getString("name"), rs.getString("id"));
				String codeString = rs.getString("subjects");				
				if(codeString != null) {
					ArrayList<String> subjectCodes = new ArrayList<>();
					String[] subjectCode = codeString.split(",");
					for(int i = 0; i < subjectCode.length; i++) {
						subjectCodes.add(subjectCode[i]);
					}
					student.setSubjectCodes(subjectCodes);
				}
				
				students.add(student);
			}
			
			return students;
			
		} catch(SQLException e) {
			System.out.println("select sql 오류");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<StudentVO> select(String primaryKey) {
		String sql = "select * from students where id=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, primaryKey);
			rs = ps.executeQuery();
			
			ArrayList<StudentVO> students = new ArrayList<>();
			while(rs.next()) {
				StudentVO student = new StudentVO(rs.getString("name"), rs.getString("id"));
				String codeString = rs.getString("subjects");
				if(codeString != null) {
					if(!codeString.equals("")) {
						String[] subjectCode = codeString.split(",");
						ArrayList<String> subjectCodes = new ArrayList<>();
						for(int i = 0; i < subjectCode.length; i++) {
							subjectCodes.add(subjectCode[i]);
						}
						student.setSubjectCodes(subjectCodes);
					}
				}
				students.add(student);
			}
			
			System.out.println(students.size());
			
			return students;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<StudentVO> search(String column, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StudentVO> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StudentVO> searchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Hashtable<String, String> updateTable, String column, String key) {
		System.out.println(updateTable.get("subjects"));
		System.out.println(column);
		System.out.println(key);
		
		String[] columns = new String[updateTable.size()];
		String sql = "update students set ";
		Enumeration<String> en = updateTable.keys();
		int i = 0;
		while(en.hasMoreElements()) {
			columns[i] = en.nextElement();
			sql += columns[i] + "=?";
			if(en.hasMoreElements()) {
				sql += ",";
			}
			i++;
		}
		sql += " where " + column + "=?";
		
		try {
			ps = connection.prepareStatement(sql);
			for(i = 1; i <= columns.length; i++) {
				ps.setString(i, updateTable.get(columns[i - 1]));
			}
			ps.setString(i, key);
			
			if(ps.executeUpdate() < 1) {
				return false;
			}
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String column, String key) {
		// TODO Auto-generated method stub
		return false;
	}

}
