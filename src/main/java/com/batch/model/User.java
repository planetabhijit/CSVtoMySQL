package com.batch.model;

public class User {

	private String jobTitle;
	private String department;
	private String unit;
	private String gender;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String jobTitle, String department, String unit, String gender) {
		super();
		this.jobTitle = jobTitle;
		this.department = department;
		this.unit = unit;
		this.gender = gender;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
