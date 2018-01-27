package com.fp.oa.sample.persistence.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SAMPLE_TABLE")
public class SampleEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sample")
	@SequenceGenerator(name="sample", sequenceName="id_sequence" )
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "START_TIME")
	private Timestamp startTime;
	
	@Column(name = "DESC")
	private String desc;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	

}
