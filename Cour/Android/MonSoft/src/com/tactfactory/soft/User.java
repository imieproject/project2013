package com.tactfactory.soft;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -8414674031657121551L;
	
	protected int id;
	protected String firstname;
	protected String lastname;
	
	public User() {
	}
	
	public User(String fn, String ln) {
		this.firstname = fn;
		this.lastname = ln;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	

}
