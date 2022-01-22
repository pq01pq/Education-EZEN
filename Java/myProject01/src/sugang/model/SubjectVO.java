package sugang.model;

import java.io.Serializable;
import java.util.*;

public class SubjectVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String INSERT = "insert";
	public static final String SELECT = "select";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	private String title;
	private String code;
	private String professor;
	private String room;
	private String schedule;
	private int limit;
	
	private String[] students;
	private int regNumber;
	private Queue<String> standBy;
	
	private boolean registered;
	
	public SubjectVO(String title, String code,
			String professor, String room, String schedule, int limit, boolean registered) {
		this(title, code, professor, room, schedule, limit);
		this.registered = registered;
	}

	public SubjectVO(String title, String code,
			String professor, String room, String schedule, int limit) {
		super();
		this.title = title;
		this.code = code;
		this.professor = professor;
		this.room = room;
		this.schedule = schedule;
		this.limit = limit;

		students = new String[limit];
		standBy = new LinkedList<>();
		registered = false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String[] getStudents() {
		return students;
	}

	public void setStudents(String[] students) {
		this.students = students;
	}

	public int getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(int regNumber) {
		this.regNumber = regNumber;
	}

	public Queue<String> getStandBy() {
		return standBy;
	}

	public void setStandBy(Queue<String> standBy) {
		this.standBy = standBy;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	@Override
	public String toString() {
		return "SubjectVO [title=" + title + ", code=" + code + ", professor=" + professor + ", room=" + room
				+ ", schedule=" + schedule + ", limit=" + limit + ", regNumber=" + regNumber + "]";
	}

}
