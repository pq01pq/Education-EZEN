package grade2;

import java.util.*;

public interface Manage {
	
	public boolean insert(Student student);
	public String view();
	public LinkedList<String> toViewList();
	public LinkedList<Student> sort(String sortCase);
	public boolean delete(String name);
	public void modify();
}
