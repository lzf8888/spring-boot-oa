package com.fp.oa.core.security;

public class CustomPrincipal {
	
	String userName;
	String email;
	String jobGrade;
	String age;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobGrade() {
		return jobGrade;
	}
	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	

}
