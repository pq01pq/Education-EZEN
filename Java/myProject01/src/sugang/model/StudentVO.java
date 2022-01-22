package sugang.model;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String SEARCH = "search";
	public static final String REGISTER = "register";
	public static final String CANCEL = "cancel";
	
	private String name;
	private String id;
	
	private String function;
	private String option;
	private String searchKey;
	
	private String code;
	
	private ArrayList<String> subjectCodes;
	
	public StudentVO(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	// use at search
	public StudentVO(String name, String id, String function, String option, String searchKey) {
		this.name = name;
		this.id = id;
		this.function = function;
		this.option = option;
		this.searchKey = searchKey;
	}
	
	// use at register, cancel
	public StudentVO(String name, String id, String function, String option, String code, String searchKey) {
		this.name = name;
		this.id = id;
		this.function = function;
		this.option = option;
		this.code = code;
		this.searchKey = searchKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<String> getSubjectCodes() {
		return subjectCodes;
	}

	public void setSubjectCodes(ArrayList<String> subjectCodes) {
		this.subjectCodes = subjectCodes;
	}

	@Override
	public String toString() {
		return "StudentVO [name=" + name + ", id=" + id + ", function=" + function + ", option=" + option
				+ ", searchKey=" + searchKey + ", code=" + code + ", subjectCodes=" + subjectCodes + "]";
	}
	
}
