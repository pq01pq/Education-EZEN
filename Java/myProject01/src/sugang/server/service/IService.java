package sugang.server.service;

import java.util.*;

public interface IService<E> {
	public boolean insert(E element);
	public ArrayList<E> select(String column, String key);
	public ArrayList<E> select(String primaryKey);
	public ArrayList<E> search(String option, String key);
	public ArrayList<E> searchAll();
	public boolean update(E element, String column, String key);
	public E delete(String option, String key);
	
	public boolean register(E element);
	public boolean cancel(E element);
}
