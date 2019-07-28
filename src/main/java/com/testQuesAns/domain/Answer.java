package com.testQuesAns.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="answer")
public class Answer {
	
	@Id
	@GeneratedValue(strategy= GenerationType.TABLE)
	@Column(name="ans_id")
	private Long ansId;
	
	@Column(name="ans")
	private String ans;
	
	@JsonBackReference
	@JoinColumn(name="ques_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private Question ques;
	
	
	@JoinColumn(name="sub_ques_id", referencedColumnName= "ques_id")
	@OneToOne(cascade = CascadeType.ALL )
	private Question subQues;
	
	public Long getAnsId() {
		return ansId;
	}
	public void setAnsId(Long ansId) {
		this.ansId = ansId;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public Question getQues() {
		return ques;
	}
	public void setQues(Question ques) {
		this.ques = ques;
	}
	public Question getSubQues() {
		return subQues;
	}
	public void setSubQues(Question subQues) {
		this.subQues = subQues;
	}
	
	

}
