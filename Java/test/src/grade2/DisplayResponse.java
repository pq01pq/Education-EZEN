package grade2;

import java.io.*;
import java.util.*;

public class DisplayResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LinkedList<String> orderByName;
	private LinkedList<String> orderByKor;
	private LinkedList<String> orderByEng;
	private LinkedList<String> orderByTotal;
	private LinkedList<String> orderByRank;
	
	public DisplayResponse() {
		orderByName = new LinkedList<>();
		orderByKor = new LinkedList<>();
		orderByEng = new LinkedList<>();
		orderByTotal = new LinkedList<>();
		orderByRank = new LinkedList<>();
	}
	
	public void clear() {
		orderByName.clear();
		orderByKor.clear();
		orderByEng.clear();
		orderByTotal.clear();
		orderByRank.clear();
	}
	
	public LinkedList<String> getOrderByName() {
		return orderByName;
	}
	public void setOrderByName(LinkedList<String> orderByName) {
		this.orderByName = orderByName;
	}
	public LinkedList<String> getOrderByKor() {
		return orderByKor;
	}
	public void setOrderByKor(LinkedList<String> orderByKor) {
		this.orderByKor = orderByKor;
	}
	public LinkedList<String> getOrderByEng() {
		return orderByEng;
	}
	public void setOrderByEng(LinkedList<String> orderByEng) {
		this.orderByEng = orderByEng;
	}
	public LinkedList<String> getOrderByTotal() {
		return orderByTotal;
	}
	public void setOrderByTotal(LinkedList<String> orderByTotal) {
		this.orderByTotal = orderByTotal;
	}
	public LinkedList<String> getOrderByRank() {
		return orderByRank;
	}
	public void setOrderByRank(LinkedList<String> orderByRank) {
		this.orderByRank = orderByRank;
	}
	
}
