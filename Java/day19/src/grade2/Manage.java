package grade2;

public interface Manage {
	
	public boolean insert(String name, int kor, int eng);
	public String view(String sortCase);
	public boolean delete(String name);
	public void modify();
	public void save();
	public void load();
}
