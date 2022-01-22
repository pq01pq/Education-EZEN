package grade2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ManageGradeServer implements Manage {
	
	private LinkedList<Student> students;
	private Scanner scan;
	
	private String url, user, password;
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ManageGradeServer() {
		super();
		this.scan = new Scanner(System.in);
		
		if(students == null) {
			students = new LinkedList<>();
		}
		
		// database
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "bigdata3";
		password = "bigdata3";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean insert(Student student) {
		if(_findStudent(student.getName()) != null) {
			return false;
		}
		_insertStudent(student);
		
		return true;
	}
	
	public Student _findStudent(String name) {
		for(Student student : students) {
			if(name.equals(student.getName())) {
				return student;
			}
		}
		return null;	
	}
	
	public void _insertStudent(Student inStudent) {
		for(Student compStudent : students) {
			int compare = inStudent.getTotal() - compStudent.getTotal();
			if(compare > 0) {
				compStudent.decreaseRank();
			} else if(compare < 0) {
				inStudent.decreaseRank();
			}
		}
		
		// ordered by rank ascending
//		int index = 0;
//		for(Student compStudent : students) {
//			if(inStudent.getRank() >= compStudent.getRank()) {
//				index++;
//			}
//		}
//		students.add(index, inStudent);
		students.add(inStudent);
		
		try {
			String sql = "insert into grade values(?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, inStudent.getName());
			ps.setInt(2, inStudent.getKor());
			ps.setInt(3, inStudent.getEng());
			ps.setInt(4, inStudent.getTotal());
			ps.setInt(5, inStudent.getRank());
			
			if(ps.executeUpdate() > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("왜이래 이거");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String view() {
		LinkedList<String> viewList = toViewList();
		
		String display = "";
		for(String viewLine : viewList) {
			display += viewLine + "\n";
		}
		
		return display;
	}
	
	@Override
	public LinkedList<String> toViewList() {
		LinkedList<String> viewList = new LinkedList<>();
		for(Student student : students) {
			viewList.add(student.toString());
		}
		
		return viewList;
	}
	
	@Override
	public LinkedList<Student> sort(String sortCase) {
		switch(sortCase) {
		case "name":
			Collections.sort(students, new Comparator<Student>() {
				
				@Override
				public int compare(Student o1, Student o2) {
					return o1.compareTo(o2);
				}
				
			});
			break;
		case "kor":
			Collections.sort(students, new Comparator<Student>() {
				
				@Override
				public int compare(Student o1, Student o2) {
					return o2.getKor() - o1.getKor();
				}
				
			});
			break;
		case "eng":
			Collections.sort(students, new Comparator<Student>() {
				
				@Override
				public int compare(Student o1, Student o2) {
					return o2.getEng() - o1.getEng();
				}
				
			});
			break;
		case "total":
			Collections.sort(students, new Comparator<Student>() {
				
				@Override
				public int compare(Student o1, Student o2) {
					return o2.getTotal() - o1.getTotal();
				}
				
			});
			break;
		case "rank":
			Collections.sort(students, new Comparator<Student>() {
				
				@Override
				public int compare(Student o1, Student o2) {
					return o1.getRank() - o2.getRank();
				}
				
			});
			break;
			
		default :
		}
		
		return students;
	}

	@Override
	public boolean delete(String delName) {
		Student delStudent = _findStudent(delName);
		
		if(delStudent == null) {
			return false;
		}
		
		_deleteStudent(delStudent);
		
		return true;
	}
	
	public void _deleteStudent(Student delStudent) {
//		for(Student compStudent : students) {
//			if(delStudent.getRank() < compStudent.getRank()) {
//				compStudent.increaseRank();
//			}
//		}
		students.remove(delStudent);
		
		try {
			String sql = "delete from grade where name=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, delStudent.getName());
			
			if(ps.executeUpdate() > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("왜이래 이거");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 수정 방식은 기존의 내용을 지우고 새로운 내용을 삽입함
	@Override
	public void modify() {
		System.out.print("수정할 학생 이름: ");
		String modName = scan.next();
		Student delStudent = _findStudent(modName);
		if(delStudent == null) {
			System.out.println("해당 학생 없음");
			return;
		}
		
		modify: while(true) {
			System.out.print("수정할 점수(1.국어 2.영어 3.완료): ");
			switch(scan.nextInt()) {
			case 1:
				System.out.print("점수 입력: ");
				delStudent.setKor(scan.nextInt());
				System.out.println("국어점수 수정됨");
				break;
			case 2:
				System.out.print("점수 입력: ");
				delStudent.setEng(scan.nextInt());
				System.out.println("영어점수 수정됨");
				break;
			case 3:
				break modify;
			default:
				System.out.println("잘못 입력");
			}
		}
		
		Student inStudent = new Student(modName, delStudent.getKor(), delStudent.getEng());
		_deleteStudent(delStudent);
		_insertStudent(inStudent);
		System.out.println("수정 완료");
	}
}
