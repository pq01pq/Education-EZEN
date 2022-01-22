package sugang.server.repository;

import java.util.*;

public interface IDAO<E> {
	public boolean insert(E element);
	public ArrayList<E> select(String column, String key);
	public ArrayList<E> select(String primaryKey);
	public ArrayList<E> search(String column, String key);
	public ArrayList<E> search(String key);
	public ArrayList<E> searchAll();
	public boolean update(Hashtable<String, String> updateTable, String column, String key);
	public boolean delete(String column, String key);
	
}
