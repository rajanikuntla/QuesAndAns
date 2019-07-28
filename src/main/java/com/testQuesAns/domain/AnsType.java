package com.testQuesAns.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;


@Entity
@Immutable
@Table(name="ans_type")
public class AnsType {
	
	@Id
	@Column(name="ans_type_id")
	private Integer ansTypeId;
	
	@Column(name="ans_type_name")
	private String ansTypeName;
	
	public Integer getAnsTypeId() {
		return ansTypeId;
	}
	public void setAnsTypeId(Integer ansTypeId) {
		this.ansTypeId = ansTypeId;
	}
	public String getAnsTypeName() {
		return ansTypeName;
	}
	public void setAnsTypeName(String ansTypeName) {
		this.ansTypeName = ansTypeName;
	}

}
