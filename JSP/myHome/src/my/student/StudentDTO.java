package my.student;

public class StudentDTO {
	
	// table의 컬럼명으로 멤버필드를 만듬
	private String id;
	private String name;
	private String cname;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
}
