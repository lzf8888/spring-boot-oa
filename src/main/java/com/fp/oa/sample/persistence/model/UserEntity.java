package com.fp.oa.sample.persistence.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="USER_TABLE")
public class UserEntity  {

	@EmbeddedId
	private UserPK id;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;

	public UserPK getId() {
		return id;
	}

	public void setId(UserPK id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	
}
