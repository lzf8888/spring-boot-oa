package com.fp.oa.sample.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//sample for foreign key
@Embeddable
public class UserPK implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name = "COMPANY_ID")
	private String companyId;
	@Column(name = "USER_ID")
	private String userId;
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		UserPK target=(UserPK)obj;
		if (this.companyId == null) {
			if (target.getCompanyId() != null) {
				return false;
			}
		}else if (!this.companyId.equals(target.getCompanyId())) {
			return false;
		}
		
		if (this.userId == null) {
			if (target.getUserId() != null) {
				return false;
			}
		}else if (!this.userId.equals(target.getUserId())) {
			return false;
		}
		
		
		return true;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
