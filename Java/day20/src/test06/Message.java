package test06;

import java.io.*;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String message;
	private boolean isStatus;
	
	public Message(String name, String message) {
		this.name = name;
		this.message = message;
		this.isStatus = false;
	}

	public Message(String name, String message, boolean isStatus) {
		this.name = name;
		this.message = message;
		this.isStatus = isStatus;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isStatus() {
		return isStatus;
	}

	public void setStatus(boolean isStatus) {
		this.isStatus = isStatus;
	}

	@Override
	public String toString() {
		if(isStatus) {
			return name + " " + message;
		} else {
			return "[" + name + "] " + message;
		}
	}
	
}
