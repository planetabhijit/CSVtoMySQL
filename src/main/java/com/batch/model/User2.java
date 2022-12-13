package com.batch.model;

public class User2 {

	private long User2Id;
	private String nameprefix;
	private String firstName;
	private String lastName;

	public User2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User2(long User2Id, String nameprefix, String firstName, String lastName) {
		super();
		this.User2Id = User2Id;
		this.nameprefix = nameprefix;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getUser2Id() {
		return User2Id;
	}

	public void setUser2Id(long User2Id) {
		this.User2Id = User2Id;
	}

	public String getNameprefix() {
		return nameprefix;
	}

	public void setNameprefix(String nameprefix) {
		this.nameprefix = nameprefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
