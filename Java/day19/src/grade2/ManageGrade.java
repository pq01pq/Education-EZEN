package grade2;

import java.io.*;
import java.util.*;

public class ManageGrade implements Manage {
	
	ArrayList<Student> students;
	Scanner scan;
	File gradeFile;
	
	public ManageGrade() {
		super();
		this.students = new ArrayList<>();
		this.scan = new Scanner(System.in);
		this.gradeFile = new File("dir" + File.separator + "grade2.txt");
	}
	
	@Override
	public boolean insert(String inName, int kor, int eng) {
		if(_findStudent(inName) != null) {
			return false;
		}
		
		Student inStudent = new Student(inName, kor, eng);
		_insertStudent(inStudent);
		
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
	}

	@Override
	public String view(String sortCase) {
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
		
		String display = "";
		for(Student student : students) {
			display += (student.toString() + "\n");
		}
		
		return display;
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

	// 객체 형식
	@Override
	public void save() {
		try {
			ObjectOutputStream oos =
					new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(gradeFile)));
			oos.writeObject(students);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 객체 형식
	@SuppressWarnings("unchecked")
	@Override
	public void load() {
		students.clear();
		try {
			ObjectInputStream ois =
					new ObjectInputStream(new BufferedInputStream(new FileInputStream(gradeFile)));
			students = (ArrayList<Student>)ois.readObject();
			ois.close();
		} catch(ClassNotFoundException e) {
			System.out.println("파일 로드 끝");
		} catch(EOFException e) {
			System.out.println("파일 로드 끝");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
